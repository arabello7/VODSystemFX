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
import static vodsystemfx.AddNewDistributor.displayStandard;
import static vodsystemfx.AddNewDistributor.window;

/**
 *
 * @author tomas
 */
public class AddNewUser extends AddNewDistributor {

    private static Label label2;
    private static Label label3;
    private static TextField input2;
    private static TextField input3;

    public static boolean display(String title) throws FileNotFoundException {
        displayStandard(title);
        System.out.println("Using User method");

        //UserName fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField("#random");
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Birthdate fields
        label1 = new Label("Birthdate:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField("#random");
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);

        //Mail fields
        label2 = new Label("Mail:");
        GridPane.setConstraints(label2, 0, 2);
        input2 = new TextField("#random");
        input2.setEditable(false);
        GridPane.setConstraints(input2, 1, 2);

        //CreditCard fields
        label3 = new Label("Credit Card:");
        GridPane.setConstraints(label3, 0, 3);
        input3 = new TextField("#random");
        input3.setEditable(false);
        GridPane.setConstraints(input3, 1, 3);

        GridPane.setConstraints(cancelButton, 0, 4);
        GridPane.setConstraints(applyButton, 1, 4);

        grid.getChildren().addAll(label0, input0, label1, input1, label2, input2, label3, input3, cancelButton, applyButton);

        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 400, 400); //resolution
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
