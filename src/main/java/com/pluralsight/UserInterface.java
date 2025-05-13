package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //will be responsible for all output to the screen, reading of
    //user input, and "dispatching" of the commands to the Dealership as
    //needed. (ex: when the user selects "List all Vehicles", UserInterface would
    //call the appropriate Dealership method and then display the vehicles it
    //returns.)

    private Dealership dealership;

    public UserInterface() {
        this.dealership = null;
    }

    public void display() {
        // Initialize the dealership
        init();

        // Display the menu
        displayMenu();
    }

    private void init() {
        // Initialize DealershipFileManager
        DealershipFileManager manager = new DealershipFileManager();
        // Get the dealership object by calling getDealership method
        this.dealership = manager.getDealership();
    }

    private void displayMenu() {

        while (true) {


            // Loop to keep the menu running
            Scanner scanner = new Scanner(System.in);

            System.out.println("Welcome to " + dealership.getName());
            System.out.println("1. Get vehicles by price");
            System.out.println("2. Get vehicles by make and model");
            System.out.println("3. Get vehicles by year");
            System.out.println("4. Get vehicles by color");
            System.out.println("5. Get vehicles by mileage");
            System.out.println("6. Get vehicles by type");
            System.out.println("7. View all vehicles");
            System.out.println("8. Add vehicle");
            System.out.println("9. Remove vehicle");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Read user's command
            int command = scanner.nextInt();

            // Process the user's request based on their command
            switch (command) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return; // Exit the loop and end the program
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }

    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    public void processGetByPriceRequest() {
        // to do for processing the get by price request
    }

    public void processGetByMakeModelRequest() {
        // to do for processing the get by make and model request
    }

    public void processGetByYearRequest() {
        // to do for processing the get by year request
    }

    public void processGetByColorRequest() {
        // to do for processing the get by color request
    }

    public void processGetByMileageRequest() {
        // to do for processing the get by mileage request
    }

    public void processGetByVehicleTypeRequest() {
        // to do for processing the get by vehicle type request
    }

    public void processGetAllVehiclesRequest() {
        // Get all vehicles from the dealership and display them
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest() {
        // to do for adding a vehicle
    }

    public void processRemoveVehicleRequest() {
        // to do removing a vehicle
    }
}
