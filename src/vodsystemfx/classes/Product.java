/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author tomas
 */
public class Product {

    protected String photo; //what type?
    protected String title;
    protected double price;
    protected String description; // Moze lorem ipsum
    protected String productionDate;
    protected int duration;
    protected Distributor distributor; //Losuj z dostepnych
    protected String[] prodCountries = new String[2]; //ma to sens?
    protected String[] actors;
    protected double usersRating; //Na poczatku zero, potem sie zmienia, ludzie losowo glosuja
    protected final int COUNTRIESSIZE = 199; // Countries in text file
    protected final int TITLESIZE = 16;
    //actors ?

    public String readFile(int line, String file) {
        BufferedReader reader;
        String text = "";
        try {
            reader = new BufferedReader(new FileReader("textfiles/" + file));
            while (line > 0) {
                reader.readLine();
                line--;
            }
            text = reader.readLine();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found!");
        } catch (IOException ex) {
            System.out.println("In/Out Exception");
        }
        return text;
    }

    public void randomizeProduct() {
        photo = "<not available";
        Random rand = new Random();

        //Generating product title
        int line = rand.nextInt(TITLESIZE);
        title = readFile(line, "title1.txt") + " ";
        line = rand.nextInt(TITLESIZE);
        title += readFile(line, "title2.txt") + " ";
        line = rand.nextInt(TITLESIZE);
        title += readFile(line, "title3.txt");

        price = Math.round(rand.nextDouble() * 20 * 100.0) / 100.0;
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam semper malesuada semper. Donec vestibulum ipsum non est aliquam dictum. Quisque."; // albo uniwersalna historia na podstawie wylosowanych pol

        //Generating countries
        line = rand.nextInt(COUNTRIESSIZE);
        prodCountries[0] = readFile(line, "countries.txt");
        line = rand.nextInt(COUNTRIESSIZE);
        prodCountries[1] = readFile(line, "countries.txt");

        int num = rand.nextInt(27) + 1;
        productionDate = Integer.toString(num) + ".";
        num = rand.nextInt(11) + 1;
        productionDate += Integer.toString(num) + ".";
        num = rand.nextInt(119) + 1900;
        productionDate += Integer.toString(num);
        num = rand.nextInt(200) + 40;
        duration = num;
    }

    public Product(Distributor d) {
        this.distributor = d;
        usersRating = 0.0;
        randomizeProduct();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        return usersRating;
    }

    public void setUsersRating(double usersRating) {
        this.usersRating = usersRating;
    }

}
