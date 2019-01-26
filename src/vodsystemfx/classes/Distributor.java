/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Distributor is responsible for adding new products (movie, series or stream) to the system. It can be created only manualy. 
 * Each distributor has his own list of product which represents index of each product on global products list.
 * There are 2 types of agreements: 1) Getting salary every month form system owner 2) Getting his % of incomes from each product bought by user.
 * Distributor is often negotiating agreement and gradualy increases his incomes.
 * From time to time Distributor makes promotion for all his products or raises prices.
 * @author Tomasz Jurek
 */
public class Distributor implements Runnable, Serializable {

    private List<Integer> productList = new ArrayList<>();
    private String name;
    private double accountBalance;
    private String agreementType; // Monthly salary or for each product purchase
    private double salary; // % of purchase price or amount of money depending on agreementType
    private final int FILESIZE = 16; //input file for randomizer
    private int[] nameControl = new int[FILESIZE];
    private static volatile boolean stopWork;
    private boolean haveDiscount;

    public void stopWork() {
        stopWork = true;
    }

    //do poprawy
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

    /** Name is taken from line of text file. Type of agreement is random also.
     */
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
        while (!stopWork) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }

            int i = Randomize.randomInt(0, 5);
            switch (i) {
                case 0:
                    negotiateAgreement();
                    break;
                case 1:
                    Movie m = new Movie();
                    m.setDistributor(this);
                    addProduct(SystemManager.addToAllProducts(m));
                    System.out.println(this.getName() + " added new movie.");
                    break;
                case 2:
                    Series s = new Series();
                    s.setDistributor(this);
                    addProduct(SystemManager.addToAllProducts(s));
                    System.out.println(this.getName() + " added new series.");
                    break;
                case 3:
                    Stream st = new Stream();
                    st.setDistributor(this);
                    addProduct(SystemManager.addToAllProducts(st));
                    System.out.println(this.getName() + " added new stream.");
                    break;
                case 4:
                    setDiscount();
                    System.out.println(this.getName() + " changed products prices.");
                default:
                    System.out.println(this.getName() + " is doing nothing.");
            }
        }
    }

    /** Changing price of products. Distributor lowers prices but next time raises and so on.
     * 
     */
    public void setDiscount() {
        double percent = 0.95 + Math.random() * (0.95 - 0.5);
        if (haveDiscount) {
            for (int i = 0; i < productList.size(); i++) {
                SystemManager.getAllProducts().get(i).changePrice(1 / percent);
                haveDiscount = false;
            }
        } else {
            for (int i = 0; i < productList.size(); i++) {
                SystemManager.getAllProducts().get(i).changePrice(percent);
                haveDiscount = true;
            }
        }
    }
    
    // Used when deleting product
    public void reduceProductIndex(int localIndex) {
        productList.set(localIndex, productList.get(localIndex) - 1);
    }

    // Only used with agreementType monthlyPricing
    public void collectSalary() {
        this.accountBalance += this.salary;
    }

    // Used when user pays for purchase
    public void getMoneyTransfer(double money) {
        this.accountBalance += money;
    }
    
    // Raise percentage of incomes by 1.05
    public void negotiateAgreement() {
        salary *= 1.05;
        System.out.println(this.getName() + " finished negotiations.");
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

    public void addProduct(int globalIndex) {
        productList.add(globalIndex);
    }

    public void removeProduct(int index) {
        productList.remove(index);
    }

    public String getAgreementType() {
        return agreementType;
    }

    public int[] getNameControl() {
        return nameControl;
    }

    public void setNameControl(int[] nameControl) {
        this.nameControl = nameControl;
    }

    public static boolean isStopWork() {
        return stopWork;
    }

    public static void setStopWork(boolean stopWork) {
        Distributor.stopWork = stopWork;
    }

    public boolean isHaveDiscount() {
        return haveDiscount;
    }

    public void setHaveDiscount(boolean haveDiscount) {
        this.haveDiscount = haveDiscount;
    }

    public void setProductList(List<Integer> productList) {
        this.productList = productList;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getFILESIZE() {
        return FILESIZE;
    }
    
    
}
