/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.ArrayList;
import java.util.List;
import vodsystemfx.VODSystemFX;

/**
 *
 * @author tomas
 */
public class User implements Runnable {

    private String code; //must be also id and name?
    private String birthDate;
    private String mail;
    private String creditCard;
    private List<Integer> productList = new ArrayList<>();
    private String subscriptionType;

    @Override
    public void run() {
        //kupowanie produktu
        //ogladanie
    }

    public final void randomizeUser() {
        code = Long.toHexString(Double.doubleToLongBits(Math.random()) / 1000);
        this.birthDate = String.valueOf(Randomize.randomInt(1, 29)) + "." + String.valueOf(Randomize.randomInt(1, 12)) + "." + String.valueOf(Randomize.randomInt(1900, 2001));
        mail = code.substring(0, 8) + "@" + "mail.com";
        creditCard = String.valueOf((long) (Math.random() * 100000000000000L) + 5200000000000000L);
        int choose = Randomize.randomInt(0, 2);
        switch (choose) {
            case 0:
                this.subscriptionType = "basic";
            case 1:
                this.subscriptionType = "family";
            case 2:
                this.subscriptionType = "premium";
            default:
                this.subscriptionType = "none";
        }
    }

    public User() {
        randomizeUser();
    }

    // **Tries to purchase random product from globalList, if the product is already owned tries more
    public void chooseProductToBuy() {
        int alreadyBought = -1;
        while (alreadyBought != 0) {
            int globalIndex = Randomize.randomInt(0, VODSystemFX.getAllProducts().size());
            alreadyBought = 0;
            for (int index : productList) {
                if(index == globalIndex) alreadyBought++;
            }
            if (alreadyBought == 0) buyProduct(globalIndex);
        }
    }
    
    //** User without subscription pays for single product. With subscription pays once a month
    // Distributor gets his percent of price the rest goes to system account
    public void buyProduct(int globalIndex) {
        if ("none".equals(this.subscriptionType)) {
            double price = VODSystemFX.getAllProducts().get(globalIndex).getPrice();
            double percForDistributor = VODSystemFX.getAllProducts().get(globalIndex).getDistributor().getSalary();
            VODSystemFX.getAllProducts().get(globalIndex).getDistributor().getMoneyTransfer(price * percForDistributor);
            VODSystemFX.payToSystem(price * (1 - percForDistributor));
        }
        productList.add(globalIndex);
    }
    
    public void watchProduct() {
        int choose = Randomize.randomInt(0, productList.size());
        VODSystemFX.getAllProducts().get(choose); //.displayProduct();
        //losuje produkt z wlasnej listy
    }
    
//    // ** User pays once a month if he chose to subscribe application
//    public void payForSubcription (){
//        VODSystemFX.payToSystem(VODSystemFX.getSubscriptionPrice(this.subscriptionType));
//    }
//
    
    public String getSubscriptionType() {
        return subscriptionType;
    }

    public List<Integer> getProductList() {
        return productList;
    }

    // Deletes element on local list using local index
    public void removeProduct(int localIndex) {
        productList.remove(localIndex);
    }

    //jeżeli będzie wolno dzialalo to sortowanie przy dodawani na liste
    // ** Changes value of element on local list by -1. Used when deleting product from global list
    public void reduceProductIndex(int localIndex) {
        productList.set(localIndex, productList.get(localIndex) - 1);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return mail;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
