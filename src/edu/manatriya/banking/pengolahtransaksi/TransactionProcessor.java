package edu.manatriya.banking.pengolahtransaksi;

import edu.manatriya.banking.akunbanking.Account;
import edu.manatriya.banking.plugintransaksi.Transaction;
import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.LocalVariableNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by Alberttriadrian on 4/12/2015.
 */

public class TransactionProcessor {
    private Queue<Object> pendingTrans;
    private List<Object> runningTrans;
    private Account acc;

    //Constructor
    public TransactionProcessor(){}

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
            Scanner scanner = new Scanner(System.in);
            Class classTransaction = Class.forName("edu.manatriya.banking.plugintransaksi." + TransactionType).asSubclass(Transaction.class);
            @SuppressWarnings("unchecked")
            Constructor constructorTransaction = classTransaction.getConstructors()[0];
            Parameter[] parameterTransaction = constructorTransaction.getParameters();
            List<String> parameterNames = getParameterNames(constructorTransaction);
            //Type[] parameterTypes = constructorTransaction.getGenericParameterTypes();
            Object[] paramContent = new Object[parameterTransaction.length];
            int i = 0;
            for (Parameter param : parameterTransaction) {
                if (param.getType().getSimpleName().equalsIgnoreCase("Account"))
                    paramContent[i++] = this.getAccount();
                else {
                    System.out.print(parameterNames.get(i) + " : ");
                    if (param.getType().getSimpleName().equalsIgnoreCase("long")) {
                        String a = scanner.nextLine();
                        paramContent[i++] = Long.parseLong(a);
                    } else {
                        paramContent[i++] = scanner.nextLine();
                    }
                }
            }
            //ERROR NOT RESOLVED//
            Transaction tr = (Transaction)constructorTransaction.newInstance(paramContent);
            pendingTrans.add(tr);
            scanner.close();
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

    /*public void generateForms(String TransactionType){
        try {
            //Mendapatkan class plugin yang ingin dipakai misalnya : deposit, Transfer, PayElectricity, dan lain- lain
            Class pluginTransaction = Class.forName("plugintransaksi." + TransactionType);

            try {
                //Mendapatkan Constructor dan parameter-parameter dari Constructor plugin yang didapatkan oleh "pluginTransaction"
                Constructor constructorList[] = pluginTransaction.getDeclaredConstructors();
                Constructor pluginConstructor = constructorList[0];

                System.out.println("name = " + pluginConstructor.getName());
                System.out.println("decl class = " + pluginConstructor.getDeclaringClass());

                Class parameterConstructor[] = pluginConstructor.getParameterTypes();
                for (int j = 0; j < parameterConstructor.length; j++)
                    System.out.println("param #" + j + " " + parameterConstructor[j]);

                try {
                    //Memanggil Konstruktor plugin dengan reflection
                    Object plugin = pluginConstructor.newInstance(parameterConstructor);

                    try {
                        //Mendapatkan method run yang ada pada plugin
                        Method pluginMethodRun = pluginTransaction.getDeclaredMethod("start", new Class[]{});
                        try{
                            //Menjalankan method run pada plugin yang didapat
                            pluginMethodRun.invoke(plugin,new Object[]{});
                        }
                        catch(InvocationTargetException e){
                            System.out.println("Invocation is failed");
                        }
                    }
                    catch (NoSuchMethodException e) {
                        System.out.println("Method tidak ditemukan.");
                    }

                } catch (IllegalAccessException e) {
                    System.out.println("Error 1 saat menghidupkan plugin");
                } catch (InstantiationException e) {
                    System.out.println("Error 2 saat menghidupkan plugin");
                }
            }
            catch(Throwable e){
                System.out.println("Gagal mendapatkan parameter Constructor");
            }
        }
        catch(ClassNotFoundException e){
            System.out.println("Plugin tidak ditemukan.");
        }
    }*/

    public void startAll() throws Exception {
        for (int i = 0; i < pendingTrans.size(); i++ ) {
            runningTrans.add(pendingTrans.remove());
            runningTrans.get(runningTrans.size() - 1).getClass().getMethod("start").invoke(new Thread(), null);
        }
    }

	/*
    public void start(int pendingTransID){
        pendingTrans.remove().run();
    }*/

	//Menghilangkan Transaction dari runningTrans
    public void stop(int runningTransID){
        runningTrans.remove(runningTransID);
    }

    
    public void addTranstoQueue(Transaction transaction){
		pendingTrans.add(transaction);
	}

}
