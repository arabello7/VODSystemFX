/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.User;

/**
 *
 * @author tomas
 */
public class FXMLVODSystemController implements Initializable {

    @FXML
    public ListView<?> distListView;
    public ListView<?> userListView;
    public ListView<?> productListView;
    private final ObservableList distObservableList = FXCollections.observableArrayList();
    private final ObservableList userObservableList = FXCollections.observableArrayList();
    private final ObservableList productObservableList = FXCollections.observableArrayList();
    
    public void distListViewAdd() {
        distListView.getItems().clear();
        for (Distributor d : VODSystemFX.getAllDistributors()) {
            distObservableList.addAll(d.getName());
        }
        distListView.setItems(distObservableList);
    }
    
    public void userListViewAdd(String text) {
        userObservableList.addAll(text);
        userListView.setItems(userObservableList);
    }
    
    public void productListViewAdd(Product p) {
        productObservableList.addAll(p);
        productListView.setItems(productObservableList);
    }

    public void handleDistributorClick() throws FileNotFoundException {
        Distributor d = new Distributor(); 
        if (AddNewDistributor.display("New Distributor", d) == true) {
            System.out.println("Changes accepted.");
//            distListViewAdd(d.getName()); //dodawanie do listView
            VODSystemFX.addToAllDistributors(d); // lista globalna
            distListViewAdd(); //dodawanie do ListView
        }
    }

    public void handleUserClick() throws FileNotFoundException {
        User u = new User();
        if (AddNewUser.display("New User", u) == true) {
            System.out.println("Changes accepted.");
            userListViewAdd(u.getCode()); //dodawanie do listView
            //dodawanie do ArrayList
        }
    }

    public void handleProductClick() throws FileNotFoundException {
        AddNewProductChoose.display("New Product");
//            productListViewAdd(wszystkie produkty)
    }

    public void handleExitClick() {
        saveProgram();
    }

    // Executing when closing program
    public void saveProgram() {
        System.out.println("Status saved.");
//        VODSystemFX.saveProgram(); -- nie mozna wywolac niestatyczna
//        window.close(); -- nie mozna sie odwolac
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Running some code on start...");
    }
}
