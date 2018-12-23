/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author tomas
 */
public class AddNewProduct extends AddNewDistributor {

    private static Label label2;
    private static Label label3;
    private static Label label4;
    private static TextField input2;
    private static TextField input3;
    private static TextField input4;
    
//    protected String photo; //how to do it?
//    protected String name; - 
//    protected double price; -
//    protected String description;
//    protected int productionDate; //type?
//    protected int duration;
//    protected Distributor distributor; --file random
//    protected String[] prodCountries;
//    protected double usersRating;

    public static boolean display(String title) throws FileNotFoundException {
        displayStandard(title);
        System.out.println("Using User method");

        //Name fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField("#random");
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Price fields
        label1 = new Label("Price:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField("#random");
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);

        //ProductionDate fields
        label2 = new Label("Production Date:");
        GridPane.setConstraints(label2, 0, 2);
        input2 = new TextField("#random");
        input2.setEditable(false);
        GridPane.setConstraints(input2, 1, 2);

        //Duration fields
        label3 = new Label("Duration:");
        GridPane.setConstraints(label3, 0, 3);
        input3 = new TextField("#random");
        input3.setEditable(false);
        GridPane.setConstraints(input3, 1, 3);
        
        //Distributor fields
        label4 = new Label("Distributor:");
        GridPane.setConstraints(label4, 0, 4);
        input4 = new TextField("#random");
        input4.setEditable(false);
        GridPane.setConstraints(input4, 1, 4);
        
        //Users rating 0.0
        
        //Description bigger
        
        //Production countries bigger
        
        //Duration fields
        label3 = new Label("Duration:");
        GridPane.setConstraints(label3, 0, 5);
        input3 = new TextField("#random");
        input3.setEditable(false);
        GridPane.setConstraints(input3, 1, 5);

        GridPane.setConstraints(cancelButton, 0, 6);
        GridPane.setConstraints(applyButton, 1, 6);

        grid.getChildren().addAll(label0, input0, label1, input1, label2, input2, label3, input3, label4, input4, cancelButton, applyButton);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 400, 400); //resolution
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
