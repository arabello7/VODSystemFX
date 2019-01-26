/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import vodsystemfx.classes.Daemon;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.Serialization;
import vodsystemfx.classes.SystemManager;
import vodsystemfx.classes.Time;
import vodsystemfx.classes.User;

/** Controller of the FXML file. Manages buttons, lists and fields in main application window.
 *
 * @author Tomasz Jurek
 */
public class FXMLVODSystemController implements Initializable {

    @FXML
    public ListView<?> distListView;
    public ListView<?> userListView;
    public ListView<?> productsListView;
    public TextField searchTextField;
    public TextField systemAccountBalance;
    private static final ObservableList distObservableList = FXCollections.observableArrayList();
    private static final ObservableList userObservableList = FXCollections.observableArrayList();
    private static final ObservableList productsObservableList = FXCollections.observableArrayList();
    private final ExecutorService executor = Executors.newCachedThreadPool(); // For managing many threads

    /** Updating list of distributors
     */
    public void distListViewUpdate() {
        distListView.getItems().clear();
        if (!SystemManager.getAllDistributors().isEmpty()) {
            for (Distributor d : SystemManager.getAllDistributors()) {
                distObservableList.addAll(d.getName());
            }
        }
        distListView.setItems(distObservableList);
    }

    /** Updating list of users
     */
    public void userListViewUpdate() {
        userListView.getItems().clear();
        if (!SystemManager.getAllUsers().isEmpty()) {
            for (User u : SystemManager.getAllUsers()) {
                userObservableList.addAll(u.getCode());
            }
        }
        userListView.setItems(userObservableList);
    }

    /** Updating list of products
     */
    public void productListViewUpdate() {
        productsListView.getItems().clear();
        if (!SystemManager.getAllProducts().isEmpty()) {
            for (Product p : SystemManager.getAllProducts()) {
                productsObservableList.addAll(p.getTitle());
            }
        }
        productsListView.setItems(productsObservableList);
    }

    /** 'New Distributor' used for manual creation of distributors
     */
    public void handleDistributorClick() {
        Distributor d = new Distributor();
        if (d.getName() == null) {
            System.out.println("Limit of distributors reached!");
        } else if (DistributorWindow.display("New Distributor", d, false)) {
            SystemManager.addToAllDistributors(d); // Dodawanie do globalnej listy
            System.out.println("Distributor added.");
            executor.submit(d);
            distListViewUpdate();
        }
    }

    /** Clicking the list opens distributor info. Admin can delete element.
     */
    public void handleDistributorListClick() {
        int selectedIndex = distListView.getSelectionModel().getSelectedIndex();
        try {
            if (DistributorWindow.display("Distributor info", SystemManager.getAllDistributors().get(selectedIndex), true)) {
                SystemManager.removeDistributor(selectedIndex);
                System.out.println("Distributor deleted!");
                distListViewUpdate();
            }
        } catch (Exception e) {
            System.out.println("Distributor list click error!");
        }
        updateListViews();
    }

    /** 'New User' used for manual creation of users
     */
    public void handleUserClick() throws FileNotFoundException {
        User u = new User();
        if (UserWindow.display("New User", u, false) == true) {
            System.out.println("New user was added.");
            SystemManager.addToAllUsers(u); //dodawanie do globalnej listy
            executor.submit(u);
        }
        updateListViews();
    }

    /** Clicking the list opens user info. Admin can delete element.
     */
    public void handleUserListClick() {
        try {
            int selectedIndex = userListView.getSelectionModel().getSelectedIndex();
            if (UserWindow.display("User info", SystemManager.getAllUsers().get(selectedIndex), true)) {
                SystemManager.removeUser(selectedIndex);
                System.out.println("User deleted!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //List null
        }
        updateListViews();
    }

    /** 'New Product' used for manual creation of products
     */
    public void handleProductClick() {
        try {
            NewProductWindow.display("New Product");
            updateListViews();
        } catch (IllegalArgumentException ex) {
            System.out.println("First create Distributor!");
        }
    }

    /** Clicking the list opens product info. Admin can delete element.
     */
    public void handleProductListClick() {
        try {
            if (ProductWindow.display("Product info", SystemManager.getAllProducts().get(productsListView.getSelectionModel().getSelectedIndex()), true)) {
                SystemManager.removeProduct(productsListView.getSelectionModel().getSelectedIndex());
                productListViewUpdate();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Products list is empty yet.");
        }
    }

    /** Goes though product list and shows elements which contain needed actor
     */
    public void handleSearchActorClick() {
        productsListView.getItems().clear();
        for (Product p : SystemManager.getAllProducts()) {
            for (String actor : p.getActors()) {
                if (actor.toLowerCase().contains(searchTextField.getText().toLowerCase())) {
                    productsObservableList.addAll(p.getTitle());
                }
            }
        }
        productsListView.setItems(productsObservableList);
        System.out.println("Searching finished.");
    }

    /** Goes though product list and shows elements which has needed title. It can be also part of title.
     */
    public void handleSearchTitleClick() {
        productsListView.getItems().clear();
        for (Product p : SystemManager.getAllProducts()) {
            if (p.getTitle().toLowerCase().contains(searchTextField.getText().toLowerCase())) {
                productsObservableList.addAll(p.getTitle());
            }
        }
        productsListView.setItems(productsObservableList);
        System.out.println("Searching finished.");
    }

    /** Subscription button is used for changing subscriptions pricing. It's the amount of money user pays monthly for using application
     * if he chose to subscribe when creating account
     */
    public void handleSubscriptionButtonClick() {
        SubscriptionWindow.display("Subscription Pricing");
    }

    /** Executing when closing program by clicking 'Exit'. Invokes saving method in SystemManager class
     */
    public void saveProgram() {
        VODSystemFX.saveProgram();
    }
    
    /** Easter egg. Only when special condition occurs
     */
    public static void playDramaticMusic() {
        try {
            File file = new File("music/i-just-died-short.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
        }
        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Sound not working!");
        }
    }

    /** Clicking 'Refresh' button. Clearing input field used for searching
     */
    public void updateListViews() {
        try {
            productListViewUpdate();
            userListViewUpdate();
        } catch (NullPointerException e) {
            System.out.println("List is null.");
        }
        searchTextField.setText("");
        systemAccountBalance.setText(String.valueOf(SystemManager.getSystemAccountBalance()));
    }
    
    /** Confirmation alert appears. OK - loading status using serialization
     */
     public boolean ifLoadState() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to restore program state?");
        alert.setContentText("Application will come back to latest saved status. Click cancel if you want it fresh new.");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /** Eecuting when program is starting. Starts time, thread creating users and uses serialization
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Daemon daemon = new Daemon();
            executor.submit(daemon);
            Time time = new Time(2005, 1, 1);
            executor.submit(time);
            if (ifLoadState()) {
                Serialization.deserialize();
                updateListViews();
                distListViewUpdate();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLVODSystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
