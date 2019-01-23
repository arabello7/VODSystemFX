/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodsystemfx.FXMLVODSystemController;
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

    @Override
    public void run() {
        while (true) { //warunek przeładowania dystrybutorów
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
//        int i = (int) Math.random() * 3;
////        System.out.println(i + "- liczba");
//        switch (i) {
//            case 0: 
//                //negocjuj cene randomowego produktu z listy
//            case 1:
                   Movie m = new Movie(this);
//                   addProduct(m); //moze potem do usuwania
                   addProduct(VODSystemFX.addToAllProducts(m));
                   System.out.println(this.getName() + " added new product.");
//            case 2:

//            FXMLVODSystemController.class
        }
    }

    public final void randomizeDistributor() throws FileNotFoundException {
        Random rand = new Random();
        int index = rand.nextInt(FILESIZE);
        System.out.println(index);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("textfiles/distributors.txt"));
            while (index > 0) {
                reader.readLine();
                index--;
            }
            name = reader.readLine();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found!");
        } catch (IOException ex) {
            System.out.println("In/Out Exception");
        }
        finance = Math.round(Math.random() * 2000000 + 25000 * 100.0) / 100.0;
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

    public Distributor() throws FileNotFoundException {
        randomizeDistributor();
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
