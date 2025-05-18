package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {

    public static void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter("contracts.csv", true)) {
            String contractData = "";

            Vehicle v = contract.getVehicleSold();

            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;
                contractData = String.format( "SALE|%s|%s|%s|%d|\n%s|%s|%s|%s|%s|%.2f|%.2f|%d|%d|%.2f|%s|%.2f\n",
                        sale.getCustomerName(),
                        sale.getCustomerEmail(),
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice(),
                        sale.getSalesTax(),
                        sale.getRecordingFee(),
                        sale.getProcessingFee(),
                        sale.getTotalPrice(),
                        sale.isFinance() ? "YES" : "NO",
                        sale.getMonthlyPayment()
                );

            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;
                contractData = String.format("LEASE|%s|%s|%s|%d|\n%s|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                        lease.getCustomerName(),
                        lease.getCustomerEmail(),
                        v.getVin(),
                        v.getYear(),
                        v.getMake(),
                        v.getModel(),
                        v.getVehicleType(),
                        v.getColor(),
                        v.getOdometer(),
                        v.getPrice(),
                        lease.getExpectedEndingValue(),
                        lease.getLeaseFee(),
                        lease.getTotalPrice(),
                        lease.getMonthlyPayment()
                );


            } writer.write(contractData);

        } catch (IOException e) {
            System.out.println("Failed to write contract: " + e.getMessage());
        }
    }
}
