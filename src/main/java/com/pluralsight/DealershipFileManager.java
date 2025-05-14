package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            if((dataString = reader.readLine()) != null) {
                //parse the dealership line
                String[] data = dataString.split(Pattern.quote("|"));

                String name = data[0].trim();
                String address = data[1].trim();
                String phone = data[2].trim();

                dealership = new Dealership(name, address, phone);
            }

            //read and add vehicles
            while ((dataString = reader.readLine()) != null) {
                //parse the vehicle line
                String[] vehicleData = dataString.split(Pattern.quote("|"));

                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String vehicleType = vehicleData[4];
                String color = vehicleData[5];
                int odometer = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                //create a new Vehicle object and add it to the dealership's inventory
                Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(v);
            }

            } catch(IOException e){
                throw new RuntimeException(e);


        } return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}
