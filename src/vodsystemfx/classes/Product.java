/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

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
    protected String[] prodCountries;
    protected double usersRating; //Na poczatku zero, potem sie zmienia, ludzie losowo glosuja
    //actors ?

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
