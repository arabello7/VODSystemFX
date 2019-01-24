/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Movie;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.Series;
import vodsystemfx.classes.User;

/**
 *
 * @author tomas
 */
public class FXMLVODSystemController implements Initializable {

    @FXML
    public ListView<?> distListView;
    public ListView<?> userListView;
    public ListView<?> movieListView;
    public ListView<?> productsListView;
    public TextField searchTextField;
    private static ObservableList distObservableList = FXCollections.observableArrayList();
    private final ObservableList userObservableList = FXCollections.observableArrayList();
    private final ObservableList movieObservableList = FXCollections.observableArrayList();
    private static ObservableList productsObservableList = FXCollections.observableArrayList();
    private ExecutorService executor = Executors.newCachedThreadPool(); // For managing many threads [.submit()] to use

    public void distListViewUpdate() {
        distListView.getItems().clear();
        if (!VODSystemFX.getAllDistributors().isEmpty()) {
            for (Distributor d : VODSystemFX.getAllDistributors()) {
                distObservableList.addAll(d.getName());
            }
        }
        distListView.setItems(distObservableList);
    }

    public void userListViewUpdate() {
        userListView.getItems().clear();
        for (User u : VODSystemFX.getAllUsers()) {
            userObservableList.addAll(u.getCode());
        }
        userListView.setItems(userObservableList);
    }

    public void productListViewUpdate() throws FileNotFoundException {
        productsListView.getItems().clear();
        for (Product p : VODSystemFX.getAllProducts()) {
            productsObservableList.addAll(p.getTitle());
        }
        productsListView.setItems(productsObservableList);
        System.out.println("Product list updated.");
    }

    // New Distributor Button
    public void handleDistributorClick() throws FileNotFoundException {
        Distributor d = new Distributor();
        if (AddNewDistributor.display("New Distributor", d, false)) {
            VODSystemFX.addToAllDistributors(d); // Dodawanie do globalnej listy
            System.out.println("Distributor added.");
            distListViewUpdate(); // Update listView
            executor.submit(d);
        }
    }

    // Distributor List Click
    public void handleDistributorListClick() {
        int selectedIndex = distListView.getSelectionModel().getSelectedIndex();
        try {
            if (AddNewDistributor.display("Distributor info", VODSystemFX.getAllDistributors().get(selectedIndex), true)) {
                VODSystemFX.removeDistributor(selectedIndex);
                System.out.println("Distributor deleted!");
                productListViewUpdate();
                System.out.println("All it's products deleted!");
                distListViewUpdate();
            }
        } catch (FileNotFoundException e) {
            //smth
        }
    }

    public void handleUserClick() throws FileNotFoundException {
        User u = new User();
        if (AddNewUser.display("New User", u, false) == true) {
            System.out.println("New user was added.");
            VODSystemFX.addToAllUsers(u); //dodawanie do globalnej listy
            userListViewUpdate(); // Update listView
        }
    }

    // Opens object info. Admin can delete object
    public void handleUserListClick() throws FileNotFoundException {
        int selectedIndex = userListView.getSelectionModel().getSelectedIndex();
        if (AddNewUser.display("User info", VODSystemFX.getAllUsers().get(selectedIndex), true)) {
            VODSystemFX.removeUser(selectedIndex);
            System.out.println("User deleted!");
            userListViewUpdate();
        }
    }

    // Adding new project by button
    public void handleProductClick() throws FileNotFoundException {
        AddNewProductChoose.display("New Product");
        try {
            productListViewUpdate();
        } catch (Exception e) {
            System.out.println("Error when updating product list!");
        }
    }

    public void handleProductListClick() throws FileNotFoundException {
//        int index = productsListView.getSelectionModel().getSelectedIndex();
        if (ProductWindow.display("Product info", VODSystemFX.getAllProducts().get(productsListView.getSelectionModel().getSelectedIndex()), true)) {
            VODSystemFX.removeProduct(productsListView.getSelectionModel().getSelectedIndex());
            productListViewUpdate();
        }
    }

    public void handleSearchActorClick() {
        productsListView.getItems().clear();
        for (Product p : VODSystemFX.getAllProducts()) {
            for (String actor : p.getActors()) {
                if (actor.toLowerCase().contains(searchTextField.getText().toLowerCase())) {
                    productsObservableList.addAll(p.getTitle());
                }
            }
        }
        productsListView.setItems(productsObservableList);
        System.out.println("Searching finished.");
    }

    public void handleSearchTitleClick() {
        productsListView.getItems().clear();
        for (Product p : VODSystemFX.getAllProducts()) {
            if (p.getTitle().toLowerCase().contains(searchTextField.getText().toLowerCase())) {
                productsObservableList.addAll(p.getTitle());
            }
        }
        productsListView.setItems(productsObservableList);
        System.out.println("Searching finished.");
    }

    public void handleSubscriptionButtonClick() {
        SubscriptionWindow.display("Subscription Pricing");
    }

    // When closing program
    public void handleExitClick() {
        saveProgram();
    }

    // Executing when closing program
    public void saveProgram() {
        System.out.println("Status saved.");
        VODSystemFX.saveProgram();
    }

    //musi byc w tle
    public void updateListViews() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            sleep(100);
            distListViewUpdate();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i = 2;
        while (i > 1) {
            System.out.println("creating user..");
            User u = new User();
            System.out.println("user new");
            VODSystemFX.addToAllUsers(u);
            System.out.println("added to users");
            userListViewUpdate();
            try {
                Distributor d = new Distributor();
//                new Thread(d).start();
                VODSystemFX.addToAllDistributors(d);
                distListViewUpdate();

                Movie m = new Movie(d);
                VODSystemFX.addToAllProducts(m);
                Movie m2 = new Movie(d);
                VODSystemFX.addToAllProducts(m2);
            } catch (Exception e) {
                System.out.println("cos sie zjebalo");
            }

//                System.out.println("Creating series...");
//                Series s = new Series(d);
//                System.out.println("Series info:");
//                System.out.println(s.getTitle());
//                System.out.println(s.getPrice());
//                System.out.println("Season info:");
//                System.out.println(s.getSeasons().get(0).getTitle());
//                System.out.println(s.getSeasons().get(0).getPrice());
////                
//            } catch (FileNotFoundException e){
//                System.out.println("Error when creating distributor");
//            } 
            i--;
        }
        System.out.println("Running some code on start...");

    }
}
