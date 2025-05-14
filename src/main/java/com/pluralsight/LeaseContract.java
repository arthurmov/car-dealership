package com.pluralsight;

public class LeaseContract extends Contract {

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, String vehicleSold) {
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
