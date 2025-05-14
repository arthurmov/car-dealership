package com.pluralsight;

public class SalesContract extends Contract {


    public SalesContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
    }

    @Override
    public double getTotalPrice() {

        return 0;
    }

    @Override
    public double getMonthlyPayment() {

        return 0;
    }
}
