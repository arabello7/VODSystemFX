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
    private double finance;
    private final int FILESIZE = 16; //input file for randomizer

    public Distributor() {
        this.name = FileWorm.readFile(Randomize.randomInt(0, FILESIZE), "distributors.txt");
        this.finance = Math.round(Math.random() * 2000000 + 25000 * 100.0) / 100.0;
    }

    @Override
    public void run() {
        while (true) { //warunek przeładowania dystrybutorów
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }

            int i = Randomize.randomInt(0, 4);
            switch (i) {
                case 0:
//                //negocjuj cene randomowego produktu z listy
                case 1:
                    Movie m = new Movie(this);
                    addProduct(VODSystemFX.addToAllProducts(m));
                    System.out.println(this.getName() + " added new movie.");
                case 2:
                    Series s = new Series(this);
                    addProduct(VODSystemFX.addToAllProducts(s));
                    System.out.println(this.getName() + " added new series.");
                case 3:
                    Stream st = new Stream(this);
                    addProduct(VODSystemFX.addToAllProducts(st));
                    System.out.println(this.getName() + " added new stream.");
                default:
                    System.out.println(this.getName() + " is doing nothing at all.");
            }
        }
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

    public double getFinance() {
        return finance;
    }

    public void makeAgreement() {
    }
//
//    public void addMovie(Movie m) {
////        productList.add(m);
//    }

    public void addProduct(int globalIndex) {
        productList.add(globalIndex);
    }

    //
    public void removeProduct(int index) {
        productList.remove(index);
    }

    public void setDiscount() {
        // tylko dla Streama i Filmu
        // tu nie wiem czy Dystrybutor nie powinien implementować Promotion?
    }
}
