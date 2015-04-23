package edu.manatriya.banking;


public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            new ATMMachine().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
