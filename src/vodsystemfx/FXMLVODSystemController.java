/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author tomas
 */
public class FXMLVODSystemController implements Initializable {

    public Button button;

    public void handleButtonClick() {
        System.out.println("Run some code user doesnt see");
        button.setText("Stop touching me!");
    }

    public void handleDistributorClick() throws FileNotFoundException {
        System.out.println("Kliknięto");
        boolean result = AddNewDistributor.display("New Distributor");
        System.out.println("Apply changes: " + result);
    }

    public void handleUserClick() throws FileNotFoundException {
        boolean result = AddNewUser.display("New User");
        System.out.println("Apply changes: " + result);
    }

    public void handleProductClick() throws FileNotFoundException {
        AddNewProduct2.display("New Product");
    }

//    public void setOnCloseRequest(){
//        saveProgram();
//    }
    public void handleExitClick() {
        saveProgram();
    }

    // Executing when closing program
    public void saveProgram() {
        System.out.println("Status saved.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Running some code on start...");
    }
}
