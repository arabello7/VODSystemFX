/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
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
import vodsystemfx.classes.Product;

/**
 *
 * @author tomas
 */
public class AddNewDistributor {
    
    protected static Stage window; //
    protected static boolean answer;
    protected static GridPane grid;
    protected static Label label0;
    protected static Label label1;
    protected static TextField input0;
    protected static TextField input1;
    protected static Button cancelButton;
    protected static Button applyButton;
    //pododawane wszystkie labele z AddnewProduct
    
    protected static <T extends Product> void drawProductWindow (T p){
        //Name fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField(p.getTitle());
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Price fields
        label1 = new Label("Price:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField(String.valueOf(p.getPrice()));
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);

        //ProductionDate fields
        Label label2 = new Label("Production Date:");
        GridPane.setConstraints(label2, 0, 2);
        TextField input2 = new TextField(p.getProductionDate());
        input2.setEditable(false);
        GridPane.setConstraints(input2, 1, 2);

        //Duration fields
        Label label3 = new Label("Duration:");
        GridPane.setConstraints(label3, 0, 3);
        TextField input3 = new TextField(String.valueOf(p.getDuration()));
        input3.setEditable(false);
        GridPane.setConstraints(input3, 1, 3);

        //Distributor fields
        Label label4 = new Label("Distributor:");
        GridPane.setConstraints(label4, 0, 4);
        TextField input4 = new TextField(p.getDistributor().getName());
        input4.setEditable(false);
        GridPane.setConstraints(input4, 1, 4);

        //Countries fields
        Label label5 = new Label("Production Countries:");
        GridPane.setConstraints(label5, 0, 5);
        TextField input5 = new TextField(String.valueOf(p.getProdCountries()[0] + ", " + p.getProdCountries()[1]));
        input5.setEditable(false);
        GridPane.setConstraints(input5, 1, 5);

        grid.getChildren().addAll(label0, input0, label1, input1, label2, input2, label3, input3, input4, label4, label5, input5);
        grid.setAlignment(Pos.CENTER);
    }
    
    protected static void displayStandard(String title) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); // focus on that window
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(450);

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
    }

    public static boolean display(String title, Distributor d) throws FileNotFoundException {
        displayStandard(title);
        
        System.out.println("Using distributor method");
         
        //Name fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField(d.getName());
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Finance fields
        label1 = new Label("Finance:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField(String.valueOf(d.getFinance()));
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);
        
        GridPane.setConstraints(cancelButton, 0, 2);
        GridPane.setConstraints(applyButton, 1, 2);
        
        grid.getChildren().addAll(label0, input0, label1, input1, cancelButton, applyButton);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 300, 200); //resolution
        window.setScene(scene);
        
        window.showAndWait(); //waiting for?
        
//        dist = d;
        return answer;
    }

    private boolean isInt(TextField input) {
        try {
            int number = Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            //podkreślenie błędnego pola
            return false;
        }
    }
}
