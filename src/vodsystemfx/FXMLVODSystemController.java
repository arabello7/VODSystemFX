/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Movie;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.User;

/**
 *
 * @author tomas
 */
public class FXMLVODSystemController implements Initializable {

    @FXML
    public static ListView<?> distListView;
    public ListView<?> userListView;
    public ListView<?> movieListView;
    public static ListView<?> productsListView;
    private static ObservableList distObservableList = FXCollections.observableArrayList();
    private final ObservableList userObservableList = FXCollections.observableArrayList();
    private final ObservableList movieObservableList = FXCollections.observableArrayList(); 
    private static ObservableList productsObservableList = FXCollections.observableArrayList(); 
    
    public static void distListViewUpdate() {
        distListView.getItems().clear();
        for (Distributor d : VODSystemFX.getAllDistributors()) {
            distObservableList.addAll(d.getName());
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
    
    public static void productListViewUpdate() throws FileNotFoundException {
        productsListView.getItems().clear();
        for (Product p : VODSystemFX.getAllProducts()) {
            productsObservableList.addAll(p.getTitle());
        }
        productsListView.setItems(productsObservableList);
        System.out.println("Product list updated.");
    }

    public void handleDistributorClick() throws FileNotFoundException {
        Distributor d = new Distributor(); 
        if (AddNewDistributor.display("New Distributor", d) == true) {
            System.out.println("New distributor was added.");
            VODSystemFX.addToAllDistributors(d); // Dodawanie do globalnej listy
            distListViewUpdate(); // Update listView
        }
    }

    public void handleUserClick() throws FileNotFoundException {
        User u = new User();
        if (AddNewUser.display("New User", u) == true) {
            System.out.println("New user was added.");
            VODSystemFX.addToAllUsers(u); //dodawanie do globalnej listy
            userListViewUpdate(); // Update listView
        }
    }

    public void handleProductClick() throws FileNotFoundException {
        AddNewProductChoose.display("New Product");
        try {
            productListViewUpdate();
        } catch (Exception e){
            System.out.println("Error when updating product list!");
        }
        System.out.println(VODSystemFX.getAllMovies().get(0).getTitle() + " is the title");
    }
    
    public void handleUserListClick() throws FileNotFoundException {
        AddNewUser.display("User info", VODSystemFX.getAllUsers().get(userListView.getSelectionModel().getSelectedIndex())); 
        System.out.println("User clicked!");
    }
    
    public void handleProductListClick() throws FileNotFoundException {
        String tit = VODSystemFX.getAllProducts().get(productsListView.getSelectionModel().getSelectedIndex()).getTitle();
        System.out.println(tit);
//        Distributor d = new Distributor();
//        Product p = new Product(d);
        VODSystemFX.getAllProducts().get(productsListView.getSelectionModel().getSelectedIndex());
//        System.out.println(p.getViewingPeriod() + " - nowy");
    }

    // When closing program
    public void handleExitClick() {
        saveProgram();
    }

    // Executing when closing program
    public void saveProgram() {
        System.out.println("Status saved.");
//        VODSystemFX.saveProgram(); //-- nie mozna wywolac niestatyczna
//        window.close(); //-- nie mozna sie odwolac
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i = 5;
        while (i > 0) {
            User u = new User();
            VODSystemFX.addToAllUsers(u);
            userListViewUpdate();

            try {
                Distributor d = new Distributor();
                VODSystemFX.addToAllDistributors(d);
//                distListViewUpdate();
                
            } catch (FileNotFoundException e){
                System.out.println("Error when creating distributor");
            }
            i--;
        }
        
//            VODSystemFX.addToAllDistributors(d);
//            FXMLVODSystemController.distListViewUpdate();
            System.out.println("Running some code on start...");
            
    }
}
