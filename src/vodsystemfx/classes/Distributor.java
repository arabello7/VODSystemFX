/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodsystemfx.VODSystemFX;

/**
 *
 * @author tomas
 */
public class Distributor implements Runnable {

    private List<Integer> productList = new ArrayList<>();
    private String name;
    private double accountBalance;
    private String agreementType; // Monthly salary or for each product purchase
    private double salary; // % of purchase price or amount of money depending on agreementType
    private final int FILESIZE = 16; //input file for randomizer
    private int[] nameControl = new int[FILESIZE];

    //** If all names from file are in use returns null
    //* else returns random name
    public String getNewName() {
        String name = null;
        int check = 0;
        for (int i = 0; i < FILESIZE; i++) {
            if (nameControl[i] == 0) {
                check++;
            }
        }
        if (check == 0) {
            name = null;
        } else {
            check = 0;
            while (check == 0) {
                int randomName = Randomize.randomInt(0, FILESIZE);
                if (nameControl[randomName] == 0) {
                    check = 1;
                    nameControl[randomName] = 1;
                    name = FileWorm.readFile(randomName, "distributors.txt");
                }
            }
        }
        return name;
    }

    public Distributor() {
        this.name = getNewName();
        this.accountBalance = Math.round(Math.random() * 2000000 + 25000 * 100.0) / 100.0;
        int choose = Randomize.randomInt(0, 1);
        switch (choose) {
            case 0:
                this.agreementType = "ProductPricing";
                this.salary = 0.5;
            case 1:
                this.agreementType = "MonthlyPricing";
                this.salary = 1000;
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }

            int i = Randomize.randomInt(0, 6);
            switch (i) {
                case 0:
                    negotiateAgreement();
                    break;
                case 1:
                    Movie m = new Movie(this);
                    addProduct(VODSystemFX.addToAllProducts(m));
                    System.out.println(this.getName() + " added new movie.");
                    break;
                case 2:
                    Series s = new Series(this);
                    addProduct(VODSystemFX.addToAllProducts(s));
                    System.out.println(this.getName() + " added new series.");
                    break;
                case 3:
                    Stream st = new Stream(this);
                    addProduct(VODSystemFX.addToAllProducts(st));
                    System.out.println(this.getName() + " added new stream.");
                    break;
                default:
                    System.out.println(this.getName() + " is doing nothing.");
            }
        }
    }

    // Only used with agreementType monthlyPricing
    public void collectSalary() {
        this.accountBalance += this.salary;
    }

    // Used when user pays for purchase
    public void getMoneyTransfer(double money) {
        this.accountBalance += money;
    }

    public double getSalary() {
        return salary;
    }

    public List<Integer> getProductList() {
        return productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    // Raise percentage of incomes by 1.05
    public void negotiateAgreement() {
        salary *= 1.05;
        System.out.println(this.getName() + " finished negotiations.");
    }

    public void addProduct(int globalIndex) {
        productList.add(globalIndex);
    }

    //
    public void removeProduct(int index) {
        productList.remove(index);
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setDiscount() {
        // tylko dla Streama i Filmu
        // tu nie wiem czy Dystrybutor nie powinien implementować Promotion?
    }
}
