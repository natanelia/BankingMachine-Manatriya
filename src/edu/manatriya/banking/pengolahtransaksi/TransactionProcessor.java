package edu.manatriya.banking.pengolahtransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.plugintransaksi.PaymentForm;
import edu.manatriya.banking.plugintransaksi.Transaction;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.LocalVariableNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Alberttriadrian on 4/12/2015.
 */

public class TransactionProcessor {
    private Queue<Transaction> pendingTrans;
    private List<Transaction> runningTrans;
    private Account acc;

    //Constructor
    public TransactionProcessor(Account _acc){
        acc = _acc;
        pendingTrans = new LinkedList<Transaction>();
        runningTrans = new LinkedList<Transaction>();

    }

    //Gettter and Setter
    public Account getAccount(){
        return acc;
    }

    public Transaction getRunningTrans(int runningTransID ){
        return (Transaction)runningTrans.get(runningTransID);
    }

    public Transaction getPendingTrans(){
        return (Transaction)pendingTrans.element();
    }

    public void setAccount(Account _acc){
        acc = _acc;
    }

    public void generateForm(String TransactionType) throws Exception {
        try {
            //Scanner scanner = new Scanner(System.in);
            if (TransactionType.equalsIgnoreCase("Payment")){
                PaymentForm paymentForm = new PaymentForm();
                synchronized (paymentForm){
                    paymentForm.wait();
                }
                TransactionType = paymentForm.getKindOfPayment();
                paymentForm.dispose();
            }

            Class classTransaction = Class.forName("edu.manatriya.banking.plugintransaksi." + TransactionType).asSubclass(Transaction.class);
            @SuppressWarnings("unchecked")
            Constructor constructorTransaction = classTransaction.getConstructors()[0];
            Parameter[] parameterTransaction = constructorTransaction.getParameters();
            List<String> parameterNames = getParameterNames(constructorTransaction);
            List<String> paramFriendlyNames = new ArrayList<String>();
            for (String p : parameterNames) {
                paramFriendlyNames.add(p.replace("_", " ").toUpperCase());
            }
            Type[] parameterTypes = constructorTransaction.getGenericParameterTypes();
            Object[] paramContent = new Object[parameterTransaction.length];
            int i = 0;
            paramContent[i++] = acc;
            String[] arrParam;

            TransactionForm transactionForm = new TransactionForm(TransactionType, paramFriendlyNames);
            synchronized (transactionForm) {
                try {
                    transactionForm.wait();
                } catch (InterruptedException e) {
                    /* do nothing */
                }

                arrParam = transactionForm.getUserInputs();
                if ( !arrParam[0].isEmpty() ){
                    for (String strParam : arrParam) {
                        if (parameterTypes[i].getTypeName().equals("double")) {
                            paramContent[i++] = Double.parseDouble(strParam);
                        } else if (parameterTypes[i].getTypeName().equals("int")) {
                            paramContent[i++] = Integer.parseInt(strParam);
                        } else {
                            paramContent[i++] = strParam;
                        }
                    }
                }
            }
            transactionForm.dispose();

            if ( !arrParam[0].isEmpty()){
                Transaction tr = (Transaction)constructorTransaction.newInstance(paramContent);
                pendingTrans.add(tr);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Returns a list containing one parameter name for each argument accepted
     * by the given constructor. If the class was compiled with debugging
     * symbols, the parameter names will match those provided in the Java source
     * code. Otherwise, a generic "arg" parameter name is generated ("arg0" for
     * the first argument, "arg1" for the second...).
     *
     * This method relies on the constructor's class loader to locate the
     * bytecode resource that defined its class.
     *
     * @param constructor
     * @return
     * @throws IOException
     */
    public static List<String> getParameterNames(Constructor<?> constructor) throws IOException {
        Class<?> declaringClass = constructor.getDeclaringClass();
        ClassLoader declaringClassLoader = declaringClass.getClassLoader();
        jdk.internal.org.objectweb.asm.Type declaringType = jdk.internal.org.objectweb.asm.Type.getType(declaringClass);
        String constructorDescriptor = jdk.internal.org.objectweb.asm.Type.getConstructorDescriptor(constructor);
        String url = declaringType.getInternalName() + ".class";

        InputStream classFileInputStream = declaringClassLoader.getResourceAsStream(url);
        if (classFileInputStream == null) {
            throw new IllegalArgumentException("The constructor's class loader cannot find the bytecode that defined the constructor's class (URL: " + url + ")");
        }

        ClassNode classNode;
        try {
            classNode = new ClassNode();
            ClassReader classReader = new ClassReader(classFileInputStream);
            classReader.accept(classNode, 0);
        } finally {
            classFileInputStream.close();
        }

        @SuppressWarnings("unchecked")
        List<MethodNode> methods = classNode.methods;
        for (MethodNode method : methods) {
            if (method.name.equals("<init>") && method.desc.equals(constructorDescriptor)) {
                jdk.internal.org.objectweb.asm.Type[] argumentTypes = jdk.internal.org.objectweb.asm.Type.getArgumentTypes(method.desc);
                List<String> parameterNames = new ArrayList<String>(argumentTypes.length);

                @SuppressWarnings("unchecked")
                List<LocalVariableNode> localVariables = method.localVariables;
                for (int i = 0; i < argumentTypes.length; i++) {
                    // The first local variable actually represents the "this" object
                    parameterNames.add(localVariables.get(i + 1).name);
                }

                return parameterNames;
            }
        }

        return null;
    }

    public void startAll() throws Exception {
        for (int i = 0; i < pendingTrans.size(); i++ ) {
            runningTrans.add(pendingTrans.remove());
            runningTrans.get(runningTrans.size() - 1).start();
        }
    }

    //Menghilangkan Transaction dari runningTrans
    public void stop(int runningTransID){
        runningTrans.get(runningTransID).interrupt();
        runningTrans.remove(runningTransID);
    }


    public void addTranstoQueue(Transaction transaction){
        pendingTrans.add(transaction);
    }

}
