package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTax;
    private int recordingFee;
    private int processingFee;
    private boolean isFinance;


    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold,
                         double salesTax, int recordingFee, int processingFee, boolean isFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinance = isFinance;
    }

    public double getSalesTax() {
        if(this.salesTax == 0) {
            this.salesTax = this.getVehicleSold().getPrice() * 0.05;
        }
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getRecordingFee() {
        if(this.recordingFee == 0) {
            recordingFee = 100;
        }
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }

    @Override
    public double getTotalPrice() {
        if(getVehicleSold().getPrice() < 10000) {
            processingFee = 295;
        } else processingFee = 495;
        return processingFee;
    }

    @Override
    public double getMonthlyPayment() {

        return 0;
    }
}
