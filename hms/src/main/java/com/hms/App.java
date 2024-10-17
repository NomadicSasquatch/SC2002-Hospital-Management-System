package com.hms;

/**
 * Hello world!
 */
public final class App {

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        People person = new People("John Doe", "123456", true, 30);
        System.out.println(person.getName()); // test output
    }
}
