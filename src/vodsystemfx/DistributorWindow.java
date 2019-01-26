/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vodsystemfx.classes.Distributor;

/** Displays window used for creation and edition of distributor in application. Contains method with standard display.
 *
 * @author Tomasz Jurek
 */
public class DistributorWindow {
    
    protected static Stage window; 
    protected static boolean answer;
    protected static GridPane grid;
    protected static Label label0;
    protected static Label label1;
    protected static TextField input0;
    protected static TextField input1;
    protected static Button cancelButton;
    protected static Button applyButton;
    
    /** Displaying standard fields used in many classes
     * 
     * @param title - title of the window
     * @param ifEdit - true = accept button is changed for 'delete' button
     */
    protected static void displayStandard(String title, boolean ifEdit) {
        answer = false;
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // Focus on that window
        window.setTitle(title);

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        applyButton = new Button("Apply"); // When opening for creation
        if(ifEdit) applyButton.setText("Delete"); // When opening for edition
        applyButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
    }

    public static boolean display(String title, Distributor d, boolean ifEdit) {
        displayStandard(title, ifEdit);
         
        //Name fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField(d.getName());
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Finance fields
        label1 = new Label("Finance:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField(String.valueOf(d.getAccountBalance()));
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);
        
        GridPane.setConstraints(cancelButton, 0, 2);
        GridPane.setConstraints(applyButton, 1, 2);
        
        grid.getChildren().addAll(label0, input0, label1, input1, cancelButton, applyButton);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 300, 200); // Resolution
        window.setScene(scene);
        
        window.showAndWait();
        
        return answer;
    }
}
