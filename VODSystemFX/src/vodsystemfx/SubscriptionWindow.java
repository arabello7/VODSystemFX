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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import static vodsystemfx.DistributorWindow.applyButton;
import static vodsystemfx.DistributorWindow.label0;
import vodsystemfx.classes.SystemManager;

/** Displays window used for changing Subscription pricing in application
 *
 * @author Tomasz Jurek
 */
class SubscriptionWindow extends DistributorWindow {
    
    public static boolean display(String title) {
        displayStandard(title, false);
        
        GridPane mainGrid = new GridPane();
        mainGrid.getColumnConstraints().add(0, new ColumnConstraints(150)); // column 0 is 150 wide
        mainGrid.getColumnConstraints().add(1, new ColumnConstraints(100)); // column 1 is 100 wide
        mainGrid.getColumnConstraints().add(2, new ColumnConstraints(100)); // column 2 is 100 wide
        mainGrid.getColumnConstraints().add(3, new ColumnConstraints(100)); // column 3 is 100 wide
        
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
        TextField textField0 = new TextField(String.valueOf(SystemManager.getSubscriptionPrice("basic")));
        GridPane.setConstraints(textField0, 1, 1);
        TextField textField1 = new TextField(String.valueOf(SystemManager.getSubscriptionPrice("family")));
        GridPane.setConstraints(textField1, 2, 1);
        TextField textField2 = new TextField(String.valueOf(SystemManager.getSubscriptionPrice("premium")));
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
        
        // Updating prices of subscriptions
        applyButton.setOnAction(e -> {
            try {
                SystemManager.setBasicSubscriptionPrice(Double.valueOf(textField0.getText()));
                SystemManager.setFamilySubscriptionPrice(Double.valueOf(textField1.getText()));
                SystemManager.setPremiumSubscriptionPrice(Double.valueOf(textField2.getText()));
                window.close();
            } catch (NumberFormatException ex) {
                System.out.println("Wrong type! Values not updated.");
            }
        });

        mainGrid.getChildren().addAll(label0, label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, textField0, textField1, textField2, cancelButton, applyButton);
        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.setVgap(8);
        mainGrid.setHgap(10);
        

        Scene scene = new Scene(mainGrid, 500, 200); //resolution widthxheigh
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
