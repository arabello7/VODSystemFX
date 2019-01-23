/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Override
    public void run() {
        //kupowanie produktu
        //ogladanie
    }

    public final void randomizeUser() {
        code = Long.toHexString(Double.doubleToLongBits(Math.random()) / 1000);
        Random rand = new Random();
        birthDate = String.valueOf(rand.nextInt(29)) + "." + String.valueOf(rand.nextInt(12)) + "." + String.valueOf(rand.nextInt(105) + 1900);
        mail = code.substring(0, 8) + "@" + "mail.com";
        creditCard = String.valueOf((long) (Math.random() * 100000000000000L) + 5200000000000000L);
    }

    public User() {
        randomizeUser();
    }

    public void buySubscription() {
        //wybór taryfy
    }

    public void buySeries(Series s) {
//        userProductList.addAll(s.getSeasons()); //sprawdzic czy to nie usuwa starych twoich
    }

    // losuje indeks na globalnej i kupuje
    public void buySingleProduct(int globalIndex) {
        productList.add(globalIndex);
    }

    public List<Integer> getProductList() {
        return productList;
    }

    // Deletes element on local list using local index
    public void removeProduct(int localIndex) {
        productList.remove(localIndex);
    }

    //jeżeli będzie wolno dzialalo to sortowanie przy dodawani na liste
    // ** Changes value of element on local list by -1
    public void reduceProductIndex(int localIndex) {
        productList.set(localIndex, productList.get(localIndex)-1 );
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
