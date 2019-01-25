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
public class User implements Runnable {

    private String code; //must be also id and name?
    private String birthDate;
    private String mail;
    private String creditCard;
    private List<Integer> productList = new ArrayList<>();
    private String subscriptionType;

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            int choose = Randomize.randomInt(0, 2);
            switch (choose) {
                case 0:
                    chooseProductAndBuy();
                    break;
                case 1:
                    watchProductAndVote();
                    break;
                case 2:
                    watchProductAndVote();
                    break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void randomizeUser() {
        code = Long.toHexString(Double.doubleToLongBits(Math.random()) / 1000);
        this.birthDate = String.valueOf(Randomize.randomInt(1, 29)) + "." + String.valueOf(Randomize.randomInt(1, 12)) + "." + String.valueOf(Randomize.randomInt(1900, 2001));
        mail = code.substring(0, 8) + "@" + "mail.com";
        creditCard = String.valueOf((long) (Math.random() * 100000000000000L) + 5200000000000000L);
        int choose = Randomize.randomInt(0, 5);
        switch (choose) {
            case 0:
                this.subscriptionType = "basic";
                break;
            case 1:
                this.subscriptionType = "family";
                break;
            case 2:
                this.subscriptionType = "premium";
                break;
            default:
                this.subscriptionType = "none";
        }
    }

    public User() {
        randomizeUser();
    }

    // **Tries to purchase random product from globalList, if the product is already owned tries more
    public void chooseProductAndBuy() {
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
    
    //** User without subscription pays for single product. With subscription pays once a month so not here
    // User pays for stream no matter if he has subscription
    // Depending on type of agreement Distributor gets his percentage of price or monthly salary so not here
    public void buyProduct(int globalIndex) {
        Product p = VODSystemFX.getAllProducts().get(globalIndex);
        if ("none".equals(this.subscriptionType) || p.getStreamingDate() != null) {
            double price = p.getPrice();
            if ("ProductPricing".equals(p.getDistributor().getAgreementType())) {
                double percForDistributor = p.getDistributor().getSalary();
                p.getDistributor().getMoneyTransfer(price * percForDistributor);
                VODSystemFX.payToSystem(price * (1 - percForDistributor));
            } else {
                VODSystemFX.payToSystem(price);
            }
        }
        productList.add(globalIndex);
        System.out.println(this.getCode() + " purchased " + p.getTitle());
    }
    
    public void watchProductAndVote() {
        int choose = Randomize.randomInt(0, productList.size());
        VODSystemFX.getAllProducts().get(choose).displayProduct();
        System.out.println(this.code + " watched " + VODSystemFX.getAllProducts().get(choose).getTitle() + " and voted.");
        VODSystemFX.getAllProducts().get(choose).addVote(Randomize.randomInt(5, 10));
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
