/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.User;

/**
 *
 * @author Tomasz Jurek
 */
public class VODSystemFX extends Application {

    private static Stage window;
    private static double systemAccountBalance = 1000.0;
    private static double basicSubscriptionPrice = 10; // Value paid once a month by user
    private static double familySubscriptionPrice = 20;
    private static double premiumSubscriptionPrice = 50;
    private static final List<Distributor> allDistributors = new ArrayList<>();
    private static final List<User> allUsers = new ArrayList<>();
    private static final List<Product> allProducts = new ArrayList<>();

    /** Collects money from user's subscriptions and paying distributors who chose to have Monthly Pricing type of agreement
     * 
     * @return monthly incomes - expenses
     */
    public static double monthlySettlement() {
        double cashFlow = 0;
        for (User u : allUsers) {
            systemAccountBalance += getSubscriptionPrice(u.getSubscriptionType());
            cashFlow += getSubscriptionPrice(u.getSubscriptionType());
        }
        for (Distributor d : allDistributors) {
            if("MontlyPricing".equals(d.getAgreementType())) {
                d.getMoneyTransfer(d.getSalary());
                systemAccountBalance -= d.getSalary();
                cashFlow -= d.getSalary();
            }
        }
        return cashFlow;
    }

    /** Subscriptions pricing can be changes by admin
     * 
     * @param type - type of subscription user chooses when creating account
     * @return - 0 means not having subscription - user pays for each product
     */
    public static double getSubscriptionPrice(String type) {
        switch (type) {
            case "basic":
                return basicSubscriptionPrice;
            case "family":
                return familySubscriptionPrice;
            case "premium":
                return premiumSubscriptionPrice;
        }
        return 0;
    }

    /** Adding product to 'global list'
     * 
     * @param p - product
     * @return - index of product on global list. Used for adding product to private lists
     */
    public static int addToAllProducts(Product p) {
        allProducts.add(p);
        return allProducts.lastIndexOf(p);
    }

    /** Used when deleting product. Method goes through all Users and deletes product index on their prrivate lists.
    * Note that privateList[0] is not the same as on globalList[0]
    * When deleting f.ex. globalList[4] all next objects's index drops by -1 so I do exacly same on private lists.
    * @param removeIndex - index of product to remove from allProducts list
    */
    public static void removeProduct(int removeIndex) {
        allProducts.remove(removeIndex);
        for (User u : allUsers) {
            for (int localIndex = 0; localIndex < u.getProductList().size(); localIndex++) {
                if (u.getProductList().get(localIndex) > removeIndex) {
                    u.reduceProductIndex(localIndex);
                } else if (u.getProductList().get(localIndex) == removeIndex) {
                    u.removeProduct(localIndex);
                }
            }
        }
    }

    /** Used when deleting distributor. Removing distributor also removes all it's products
     * @param index - index of product on allProducts list
    */
    public static void removeDistributor(int index) {
        for (int globalIndex : getOneDistributor(index).getProductList()) {
            removeProduct(globalIndex);
        }
        allDistributors.remove(index);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); //invocates application start method
    }

    // Launching application window
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        window = stage;

        window.setTitle("VOD System");

        window.setOnCloseRequest(e -> {
            e.consume(); //canceling prevoius action
            saveProgram();
        });
        
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
    }

    public static void saveProgram() {
        System.out.println("Saving program state...");
        // here I can put "are you sure?" alert method
        window.close();
    }
    
    public static void addToAllDistributors(Distributor d) {
        allDistributors.add(d);
    }
    
    public static void setBasicSubscriptionPrice(double newPrice) {
        VODSystemFX.basicSubscriptionPrice = newPrice;
    }

    public static void setFamilySubscriptionPrice(double newPrice) {
        VODSystemFX.familySubscriptionPrice = newPrice;
    }

    public static void setPremiumSubscriptionPrice(double newPrice) {
        VODSystemFX.premiumSubscriptionPrice = newPrice;
    }

    public static void payToSystem(double price) {
        systemAccountBalance += price;
    }

    public static List<Product> getAllProducts() {
        return allProducts;
    }
    
    public static double getSystemAccountBalance() {
        return Math.round(systemAccountBalance) * 100.0 / 100.0;
    }
    
    public static List<Distributor> getAllDistributors() {
        return allDistributors;
    }

    public static Distributor getOneDistributor(int i) {
        return allDistributors.get(i);
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void addToAllUsers(User u) {
        allUsers.add(u);
    }

    public static void removeUser(int index) {
        allUsers.remove(index);
    }
}
