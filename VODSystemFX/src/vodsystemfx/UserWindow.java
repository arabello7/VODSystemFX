/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import static vodsystemfx.DistributorWindow.displayStandard;
import static vodsystemfx.DistributorWindow.window;
import vodsystemfx.classes.User;

/** Displays window used for creation and edition of users in application
 *
 * @author Tomasz Jurek
 */
public class UserWindow extends DistributorWindow {

    private static Label label2;
    private static Label label3;
    private static TextField input2;
    private static TextField input3;

    public static boolean display(String title, User u, boolean ifEdit) {
        displayStandard(title, ifEdit);
        System.out.println("Using User method");

        //UserName fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField(u.getCode());
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Birthdate fields
        label1 = new Label("Birthdate:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField(u.getBirthDate());
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);

        //Mail fields
        label2 = new Label("Mail:");
        GridPane.setConstraints(label2, 0, 2);
        input2 = new TextField(u.getEmail());
        input2.setEditable(false);
        GridPane.setConstraints(input2, 1, 2);

        //CreditCard fields
        label3 = new Label("Credit Card:");
        GridPane.setConstraints(label3, 0, 3);
        input3 = new TextField(u.getCreditCard());
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
