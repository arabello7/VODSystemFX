/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Tomasz Jurek
 */
public class Product {

    protected Image photo;
    protected String title;
    protected double price;
    protected String description;
    protected String productionDate;
    protected int duration;
    protected Distributor distributor;
    protected String[] prodCountries = new String[2];
    protected String[] actors = new String[3];
    protected double votesNumber = 0;
    protected double votesSum = 0;
    protected int[] monthlyViews = new int[13];
    protected final int COUNTRIESSIZE = 199; // Countries in text file
    protected final int TITLESIZE = 16; // Product titles in 3 files
    protected final int ACTORSSIZE = 188; // Actors' names

    /** Generating Product data
     */
    public void randomizeProduct() {
        
        //Generating photo
        try {
            photo = new Image("file:" + "images/cage" + Randomize.randomInt(0, 13) + ".jpeg"); // random photo of the applicatino sponsor 
        } catch (IllegalArgumentException e) {
            System.out.println("Photo not found!");
        }

        //Generating product title
        title = FileWorm.readFile(Randomize.randomInt(0, TITLESIZE), "title1.txt") + " ";
        title += FileWorm.readFile(Randomize.randomInt(0, TITLESIZE), "title2.txt") + " ";
        title += FileWorm.readFile(Randomize.randomInt(0, TITLESIZE), "title3.txt");

        price = Math.round(Math.random() * 20 * 100.0) / 100.0;
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

    /** Watching product by user generates view in the current month
     */
    public void displayProduct() {
        monthlyViews[Time.getMonth()]++;
    }

    /** Users are voting after watching the product
     * @param vote - random digit from 5 to 10
     */
    public void addVote(double vote) {
        this.votesSum += vote;
        this.votesNumber += 1;
    }
    
    /** Method is calculating users votes. I had to add 2 variables to manage this
     * @return calculated users rating or 0 if they haven't voted yet
     */
    public double getUsersRating() {
        if (votesNumber == 0) {
            return 0.0;
        } else {
            return Math.round(votesSum / votesNumber) * 100.0 / 100.0;
        }
    }

    public String getTitle() {
        return title;
    }
      
    public int getMonthlyViews(int month) {
        return monthlyViews[month];
    }

    public Image getPhoto() {
        return photo;
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
    
    public void changePrice(double percent) {
        this.price *= percent;
    }

    // Methods below were created so I can call all objects fields after getting f.ex. movie from all products list. 
    // I use them also to distinguish obejcts classes
    
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
    
    //@overridable Series
    public boolean isReleased() {
        return true;
    }

}
