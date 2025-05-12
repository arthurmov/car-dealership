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

        Dealership dealership;

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader reader = new BufferedReader((fileReader));

            String dataString;

            if((dataString = reader.readLine()) != null) {
                String[] data = dataString.split(Pattern.quote("|"));
                String name = data[0].trim();
                String address = data[1].trim();
                String phone = data[2].trim();

//                dealership = getDealershipFromEncodedString(dataString);
            }

            while ((dataString = reader.readLine()) != null) {
                    Vehicle v = getVehicleFromLine(dataString);
//                    dealership.addVehicle();
            }

            } catch(IOException e){
                throw new RuntimeException(e);


        } return null;
    }

    public static Dealership getDealershipFromEncodedString(String encodedDealership) {

        String[] temp = encodedDealership.split(Pattern.quote("|"));

        String name = temp[0].trim();
        String address = temp[1].trim();
        String phone = temp[2].trim();

        return new Dealership(name, address, phone);
    }

    public static Vehicle getVehicleFromLine(String encodedInventory){
        String[] temp = encodedInventory.split(Pattern.quote("|"));

        int vin = Integer.parseInt(temp[0]);
        int year = Integer.parseInt(temp[1]);
        String make = temp[3];
        String model = temp[4];
        String vehicleType = temp[5];
        String color = temp[6];
        int odometer = Integer.parseInt(temp[7]);
        double price = Double.parseDouble(temp[8]);

        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }

    public void saveDealership(Dealership dealership) {

    }
}
