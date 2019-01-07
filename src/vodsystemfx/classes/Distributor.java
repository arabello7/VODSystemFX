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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author tomas
 */
public class Distributor {

    private List<Product> productList = new ArrayList<>();
    private String name;
    private double finance;
    private final int FILESIZE = 16; //input file for randomizer

    public final void randomizeDistributor() throws FileNotFoundException {      
        Random rand = new Random();
        int index = rand.nextInt(FILESIZE);
        System.out.println(index);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("textfiles/distributors.txt"));
            while (index > 0){
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

    public String getName() {
        return name;
    }
    
    public void setName (String name){
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

    public void addMovie() {
        String trailerUrl = "...";
        int viewingPeriod = 0;
        Product p = new Movie(this);
        productList.add(p);
    }

    /*
    public void addSeries() {
        int seasons=0, episodes=0; //pobierane z tabelki (od razu z realease date można)
        Product p = new Series(this, seasons, episodes);
        for (int i = 0; i < episodes; i++) {
            int realease = 12; //pobierane z okna/losowo
            Product p2 = new Episode(this, realease);
        }
    }*/

    //addEpisode tylko w ramach serialu
    /*
    public void addStream() {
        Product p = new LiveStream();
        productList.add(p);
    }*/
    public void removeProduct(Product p) {
        productList.remove(p);
    }

    public void setDiscount() {
        // tylko dla Streama i Filmu
        // tu nie wiem czy Dystrybutor nie powinien implementować Promotion?
    }
}
