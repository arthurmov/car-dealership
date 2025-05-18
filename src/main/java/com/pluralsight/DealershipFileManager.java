package com.pluralsight;

import java.io.*;
import java.util.regex.Pattern;

public class DealershipFileManager {
    //will be responsible for reading the dealership file,
    //parsing the data, and creating a Dealership object full of vehicles from the
    //file. It will also be responsible for saving a dealership and the vehicles back
    //into the file in the same pipe-delimited format

    public Dealership getDealership() {

        Dealership dealership = null;

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader reader = new BufferedReader((fileReader));

            String dataString;

            //read dealership info from the first line
            if ((dataString = reader.readLine()) != null) {
                //parse the dealership line
                String[] data = dataString.split(Pattern.quote("|"));

                String name = data[0].trim();
                String address = data[1].trim();
                String phone = data[2].trim();

                dealership = new Dealership(name, address, phone);
            }

            //read and add vehicles
            while ((dataString = reader.readLine()) != null) {
                if (dataString.trim().isEmpty()) continue;

                String[] parts = dataString.split(",");

                int vin = Integer.parseInt(parts[0].split(":")[1].trim());
                int year = Integer.parseInt(parts[1].split(":")[1].trim());
                String make = parts[2].split(":")[1].trim();
                String model = parts[3].split(":")[1].trim();
                String type = parts[4].split(":")[1].trim();
                String color = parts[5].split(":")[1].trim();
                int odometer = Integer.parseInt(parts[6].split(":")[1].trim());
                double price = Double.parseDouble(parts[7].split(":")[1].trim());

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);


        }
        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            // Write dealership info
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write each vehicle in readable format
            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicleLine = "VIN: " + v.getVin() +
                        ", Year: " + v.getYear() +
                        ", Make: " + v.getMake() +
                        ", Model: " + v.getModel() +
                        ", Type: " + v.getVehicleType() +
                        ", Color: " + v.getColor() +
                        ", Odometer: " + v.getOdometer() +
                        ", Price: " + v.getPrice();
                writer.write(vehicleLine);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving dealership inventory.", e);
        }
    }
}
