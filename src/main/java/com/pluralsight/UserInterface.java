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
        //initialize the dealership
        init();

        //display the menu
        displayMenu();
    }

    private void init() {
        //initialize DealershipFileManager
        DealershipFileManager fileManager = new DealershipFileManager();
        //get the dealership object by calling getDealership method
        this.dealership = fileManager.getDealership();
    }

    private void displayMenu() {

        while (true) {


            //loop to keep the menu running
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nWelcome to " + dealership.getName());
            System.out.println("1. Find vehicles within a price range");
            System.out.println("2. Find vehicles by make and model");
            System.out.println("3. Find vehicles by year");
            System.out.println("4. Find vehicles by color");
            System.out.println("5. Find vehicles by mileage");
            System.out.println("6. Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7. List ALL vehicles");
            System.out.println("8. Add a vehicle");
            System.out.println("9. Remove a vehicle");
            System.out.println("10. Sell/Lease a vehicle");
            System.out.println("99. Quit");
            System.out.print("Enter your choice: ");

            //read user input
            int command = scanner.nextInt();

            //process the user's request based on their command
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
                case 10:
                    sellOrLeaseVehicle();
                case 99:
                    System.out.println("Exiting...");
                    return; //exit the loop and end the program
                default:
                    System.out.println("Invalid command, please try again.");
            }
        }

    }

    private void sellOrLeaseVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN of the vehicle: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                vehicle = v;
                break;
            }
        }

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("Customer Email: ");
        String email = scanner.nextLine();
        System.out.print("Contract Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Is this a Sale or Lease? (sale/lease): ");
        String type = scanner.nextLine().toLowerCase();

        Contract contract;

        if (type.equals("sale")) {
            System.out.print("Finance the vehicle? (yes/no): ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("yes");
            contract = new SalesContract(date, name, email, vehicle, 0, 0, 0, finance);
        } else if (type.equals("lease")) {
            int currentYear = java.time.LocalDate.now().getYear();
            if ((currentYear - vehicle.getYear()) > 3) {
                System.out.println("Cannot lease a vehicle older than 3 years.");
                return;
            }
            contract = new LeaseContract(date, name, email, vehicle, 0, 0, 0);
        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        ContractDataManager.saveContract(contract);
        dealership.removeVehicle(vehicle);
        DealershipFileManager.saveDealership(dealership);
        System.out.println("Contract saved and vehicle removed from inventory.");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v.toString());
            }
        }
    }


    public void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle type (e.g., SUV, sedan): ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        System.out.print("Enter make: ");
        String make = scanner.next();

        System.out.print("Enter model: ");
        String model = scanner.next();

        System.out.print("Enter vehicle type (e.g., SUV, sedan): ");
        String type = scanner.next();

        System.out.print("Enter color: ");
        String color = scanner.next();

        System.out.print("Enter odometer reading: ");
        int odometer = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully.");
    }

    public void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = scanner.nextInt();

        //search for the vehicle in the dealership's inventory
        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break; //exit the loop once the vehicle is found
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);  //remove the vehicle from the dealership
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}
