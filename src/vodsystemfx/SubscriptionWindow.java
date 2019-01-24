/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static vodsystemfx.AddNewDistributor.applyButton;
import static vodsystemfx.AddNewDistributor.label0;

/**
 *
 * @author tomas
 */
class SubscriptionWindow extends AddNewDistributor {
    
    public static boolean display(String title) {
        displayStandard(title, false);
        
        GridPane grid = new GridPane();
        grid.getColumnConstraints().add(0, new ColumnConstraints(150)); // column 0 is 100 wide
        grid.getColumnConstraints().add(1, new ColumnConstraints(100)); // column 0 is 100 wide
        grid.getColumnConstraints().add(2, new ColumnConstraints(100)); // column 0 is 100 wide
        grid.getColumnConstraints().add(3, new ColumnConstraints(100)); // column 0 is 100 wide
        
        // Subscription names
        label0 = new Label("Basic:");
        GridPane.setConstraints(label0, 1, 0);
        label1 = new Label("Family:");
        GridPane.setConstraints(label1, 2, 0);
        Label label2 = new Label("Premium:");
        GridPane.setConstraints(label2, 3, 0);
        
        // Subscription price
        Label label3 = new Label("Monthly Price[$]:");
        GridPane.setConstraints(label3, 0, 1);
        TextField textField0 = new TextField(String.valueOf(VODSystemFX.getSubscriptionPrice("basic")));
        GridPane.setConstraints(textField0, 1, 1);
        TextField textField1 = new TextField(String.valueOf(VODSystemFX.getSubscriptionPrice("family")));
        GridPane.setConstraints(textField1, 2, 1);
        TextField textField2 = new TextField(String.valueOf(VODSystemFX.getSubscriptionPrice("premium")));
        GridPane.setConstraints(textField2, 3, 1);
        
        // Number of devices
        Label label4 = new Label("Number of devices:");
        GridPane.setConstraints(label4, 0, 2);
        Label label5 = new Label("1");
        GridPane.setConstraints(label5, 1, 2);
        Label label6 = new Label("5");
        GridPane.setConstraints(label6, 2, 2);
        Label label7 = new Label("5");
        GridPane.setConstraints(label7, 3, 2);
        
        // Resolution
        Label label8 = new Label("Image Quality:");
        GridPane.setConstraints(label8, 0, 3);
        Label label9 = new Label("fullHD");
        GridPane.setConstraints(label9, 1, 3);
        Label label10 = new Label("fullHD");
        GridPane.setConstraints(label10, 2, 3);
        Label label11 = new Label("4K");
        GridPane.setConstraints(label11, 3, 3);
        
        // Buttons
        GridPane.setConstraints(cancelButton, 1, 4);
        GridPane.setConstraints(applyButton, 2, 4);
        
//        applyButton = new Button("Apply");
        applyButton.setOnAction(e -> {
            try {
                VODSystemFX.setBasicSubscriptionPrice(Double.valueOf(textField0.getText()));
                VODSystemFX.setFamilySubscriptionPrice(Double.valueOf(textField1.getText()));
                VODSystemFX.setPremiumSubscriptionPrice(Double.valueOf(textField2.getText()));
                window.close();
            } catch (NumberFormatException ex) {
                System.out.println("Wrong type! Values not updated.");
            }
        });

        grid.getChildren().addAll(label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, textField0, textField1, textField2, cancelButton, applyButton);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(8);
        grid.setHgap(10);
        

        Scene scene = new Scene(grid, 500, 200); //resolution widthxheigh
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
