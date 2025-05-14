package com.pluralsight;

public class Main {
    //will be responsible for starting the application via its main()
    //method and then creating the user interface and getting it started

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        UserInterface ui = new UserInterface();
        ui.display();
    }
}