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

/**
 *
 * @author tomas
 */
public class FXMLVODSystemController implements Initializable {

    @FXML
    public ListView<?> distListView;
    private ObservableList observableList = FXCollections.observableArrayList();
    
    public void distListViewAdd(String text) {
        observableList.addAll(text);
        distListView.setItems(observableList);
    }

    public void handleDistributorClick() throws FileNotFoundException {
        System.out.println("Kliknięto");
        String n = "nic";
        Distributor di = new Distributor(); //wolałbym uniknąć
        if (AddNewDistributor.display("New Distributor", di) == true) distListViewAdd(di.getName()); // nie zmienia sie wartosc dist, nie jak w cpp
//        System.out.println("Apply changes: " + "");
    }

    public void handleUserClick() throws FileNotFoundException {
        boolean result = AddNewUser.display("New User");
        System.out.println("Apply changes: " + result);
    }

    public void handleProductClick() throws FileNotFoundException {
        AddNewProduct2.display("New Product");
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
