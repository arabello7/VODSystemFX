/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import vodsystemfx.classes.Daemon;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.Time;
import vodsystemfx.classes.User;

/**
 *
 * @author tomas
 */
public class FXMLVODSystemController implements Initializable {

    @FXML
    public ListView<?> distListView;
    public ListView<?> userListView;
    public ListView<?> productsListView;
    public TextField searchTextField;
    public TextField systemAccountBalance;
    private static ObservableList distObservableList = FXCollections.observableArrayList();
    private static ObservableList userObservableList = FXCollections.observableArrayList();
    private static ObservableList productsObservableList = FXCollections.observableArrayList();
    private ExecutorService executor = Executors.newCachedThreadPool(); // For managing many threads

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
        if (!VODSystemFX.getAllUsers().isEmpty()) {
            for (User u : VODSystemFX.getAllUsers()) {
                userObservableList.addAll(u.getCode());
            }
        }
        userListView.setItems(userObservableList);
    }

    public void productListViewUpdate() {
        productsListView.getItems().clear();
        if (!VODSystemFX.getAllProducts().isEmpty()) {
            for (Product p : VODSystemFX.getAllProducts()) {
                productsObservableList.addAll(p.getTitle());
            }
        }
        productsListView.setItems(productsObservableList);
    }

    // New Distributor Button
    public void handleDistributorClick() {
        Distributor d = new Distributor();
        if (d.getName() == null) {
            System.out.println("Limit of distributors reached!");
        } else if (DistributorWindow.display("New Distributor", d, false)) {
            VODSystemFX.addToAllDistributors(d); // Dodawanie do globalnej listy
            System.out.println("Distributor added.");
            executor.submit(d);
            distListViewUpdate();
        }
    }

    // Distributor List Click
    public void handleDistributorListClick() {
        int selectedIndex = distListView.getSelectionModel().getSelectedIndex();
        try {
            if (DistributorWindow.display("Distributor info", VODSystemFX.getAllDistributors().get(selectedIndex), true)) {
                VODSystemFX.removeDistributor(selectedIndex);
                System.out.println("Distributor deleted!");
                distListViewUpdate();
            }
        } catch (Exception e) {
            //smth
        }
        updateListViews();
    }

    public void handleUserClick() throws FileNotFoundException {
        User u = new User();
        if (UserWindow.display("New User", u, false) == true) {
            System.out.println("New user was added.");
            VODSystemFX.addToAllUsers(u); //dodawanie do globalnej listy
            executor.submit(u);
        }
        updateListViews();
    }

    // Opens object info. Admin can delete object
    public void handleUserListClick() {
        try {
            int selectedIndex = userListView.getSelectionModel().getSelectedIndex();
            if (UserWindow.display("User info", VODSystemFX.getAllUsers().get(selectedIndex), true)) {
                VODSystemFX.removeUser(selectedIndex);
                System.out.println("User deleted!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //List null
        }
        updateListViews();
    }

    // Adding new project by button
    public void handleProductClick() {
        try {
            NewProductWindow.display("New Product");
            updateListViews();
        } catch (IllegalArgumentException ex) {
            System.out.println("First create Distributor!");
        }
    }

    public void handleProductListClick() {
        try {
            if (ProductWindow.display("Product info", VODSystemFX.getAllProducts().get(productsListView.getSelectionModel().getSelectedIndex()), true)) {
                VODSystemFX.removeProduct(productsListView.getSelectionModel().getSelectedIndex());
                productListViewUpdate();
            }
        } catch (IndexOutOfBoundsException e) {
            //List is null
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

    // Executing when closing program
    public void saveProgram() {
        //Serializacja
        System.out.println("Status saved.");
        VODSystemFX.saveProgram();
        Daemon.stopWork();
    }

    // Handling Refresh Button
    public void updateListViews() {
        try {
            productListViewUpdate();
            userListViewUpdate();
        } catch (NullPointerException e) {
            System.out.println("List is null.");
        }
        searchTextField.setText("");
        systemAccountBalance.setText(String.valueOf(VODSystemFX.getSystemAccountBalance()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Daemon daemon = new Daemon();
        executor.submit(daemon);
        Time time = new Time(2005, 1, 1);
        executor.submit(time);
        try {
            InputStream music = new FileInputStream(new File("file:music/take-on-me.wav"));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start();
        } catch (Exception e) {
            System.out.println("No sound!");
        }
    }
}
