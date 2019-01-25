/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author tomas
 */
public class Product {

    protected Image photo;
    protected String title;
    protected double price;
    protected String description;
    protected String productionDate;
    protected int duration;
    protected Distributor distributor; //Losuj z dostepnych
    protected String[] prodCountries = new String[2]; //ma to sens?
    protected String[] actors = new String[3];
    protected double votesNumber = 0;
    protected double votesSum = 0;
    protected int[] monthlyViews = new int[13];
    protected final int COUNTRIESSIZE = 199; // Countries in text file
    protected final int TITLESIZE = 16;
    protected final int ACTORSSIZE = 188;

    public void randomizeProduct() {
        try {
            photo = new Image("file:" + "images/cage" + Randomize.randomInt(0, 13) + ".jpeg");
        } catch (IllegalArgumentException e) {
            System.out.println("Photo not found!");
        }

        //Generating product title
        title = FileWorm.readFile(Randomize.randomInt(0, TITLESIZE), "title1.txt") + " ";
        title += FileWorm.readFile(Randomize.randomInt(0, TITLESIZE), "title2.txt") + " ";
        title += FileWorm.readFile(Randomize.randomInt(0, TITLESIZE), "title3.txt");

        Random rand = new Random(); //jak wiecej razy to metoda w Randomize
        price = Math.round(rand.nextDouble() * 20 * 100.0) / 100.0;
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam semper malesuada semper. Donec vestibulum ipsum non est aliquam dictum. Quisque."; // albo uniwersalna historia na podstawie wylosowanych pol

        //Generating countries
        prodCountries[0] = FileWorm.readFile(Randomize.randomInt(0, COUNTRIESSIZE), "countries.txt");
        prodCountries[1] = FileWorm.readFile(Randomize.randomInt(0, COUNTRIESSIZE), "countries.txt");

        productionDate = Integer.toString(Randomize.randomInt(1, 28)) + ".";
        productionDate += Integer.toString(Randomize.randomInt(1, 12)) + ".";
        productionDate += Integer.toString(Randomize.randomInt(1900, 2019));

        duration = Randomize.randomInt(40, 240);

        //Generating actors
        actors[0] = "Nicolas Cage";
        actors[1] = FileWorm.readFile(Randomize.randomInt(0, ACTORSSIZE), "actors.txt");
        actors[2] = FileWorm.readFile(Randomize.randomInt(0, ACTORSSIZE), "actors.txt");

        //Monthly views
        for (int i = 0; i < 13; i++) {
            monthlyViews[i] = 0;
        }
    }

    public Product(Distributor d) {
        randomizeProduct();
        this.distributor = d;
    }

    // When user watches product
    public void displayProduct() {
        monthlyViews[Time.getMonth()]++;
    }

    public int getMonthlyViews(int month) {
        return monthlyViews[month];
    }

    public Image getPhoto() {
        return photo;
    }

    public void addVote(double vote) {
        this.votesSum += vote;
        this.votesNumber += 1;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String[] getActors() {
        return actors;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
    }

    public String[] getProdCountries() {
        return prodCountries;
    }

    public void setProdCountries(String[] prodCountries) {
        this.prodCountries = prodCountries;
    }

    public double getUsersRating() {
        if (votesNumber == 0) {
            return 0.0;
        } else {
            return votesSum / votesNumber;
        }
    }

    // *Poniższe metody zastosowałem, aby móc odwołać się do pól np. filmu po pobraniu go z listy produktów
    //@overridable Movie
    public String getTrailerUrl() {
        return null;
    }

    //@overridable Movie
    public int getViewingPeriod() {
        return -1;
    }

    //@overridable Series
    public List<Season> getSeasons() {
        return null;
    }

    //@overridable Stream
    public String getStreamingDate() {
        return null;
    }
    
//    //@overridable Stream
//    public int getGlobalIndex() {
//        return -1;
//    }

}
