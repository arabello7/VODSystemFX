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
    protected String name;
    protected double price;
    protected String description; // Moze lorem ipsum
    protected int productionDate; //type?
    protected int duration;
    protected Distributor distributor; //Losuj z dostepnych
    protected String[] prodCountries = new String[3]; //dobrze?
    protected double usersRating; //Na poczatku zero, potem sie zmienia, ludzie losowo glosuja
    
    protected final int COUNTRIESSIZE = 199; // Countires in text file
    //actors ?

    public String readFile(int line) {
        BufferedReader reader;
        String text = "";
        try {
            reader = new BufferedReader(new FileReader("textfiles/countires.txt"));
            while (line > 0){
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
            System.out.println("Random product:");
        photo = "?";
        //name - randomowe 3 slowa
        Random rand = new Random();
        price = rand.nextDouble() * 20;
            System.out.println("price: " + price);
        description = "lorem ipsum..."; // albo uniwersalna historia na podstawie wylosowanych pol
        //dystrybutor wybór z listy dostępnych
        
        //prod countires losowanie 3 z pliku
        int line1 = rand.nextInt(199);
        int line2 = rand.nextInt(199);
        int line3 = rand.nextInt(199);
        
        prodCountries[0] = readFile(line1);
        prodCountries[1] = readFile(line2);
        prodCountries[2] = readFile(line3);

        usersRating = 0.0;
    }

    public Product() {
//        UserInterface.getProductInfo(photo, name, price, ..., productionDate, duration, prodCountries, ...);
        //this.distributor = dist; -- po dodaniu dist jako argumentu metody nie działało, i tak trzeba bylo w dziedziczących
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
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

    public int getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(int productionDate) {
        this.productionDate = productionDate;
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
