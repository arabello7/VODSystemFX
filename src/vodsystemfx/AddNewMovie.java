/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Movie;
import vodsystemfx.classes.Product;

/**
 *
 * @author tomas
 */
public class AddNewMovie extends AddNewDistributor {

    private static Label label6;
    private static Label label7;
    private static TextField input6;
    private static TextField input7;

//    private static void streamBottom(Product p) {
//        label6 = new Label("Streaming Date:          ");
//        input6 = new TextField("#random");
//        label7 = new Label();
//        input7 = new TextField();
//        input7.setVisible(false);
//    }

    public static boolean display(String title, Movie m) throws FileNotFoundException { //potem M na T<>
        displayStandard(title); //-wziete z ADDNew Disst czy warto?     
        Product p = m;
        drawProductWindow(p);
        
        //Trailer fields
        label6 = new Label("Trailer url:");
        input6 = new TextField(m.getTrailerUrl());

        //Viewing Period fields
        label7 = new Label("Viewing Period:         ");
        input7 = new TextField(String.valueOf(m.getViewingPeriod()));

        GridPane bottomGrid = new GridPane();
        GridPane.setConstraints(label6, 0, 0);
        GridPane.setConstraints(input6, 1, 0);
        GridPane.setConstraints(label7, 0, 1);
        GridPane.setConstraints(input7, 1, 1);

        //Description
        GridPane.setConstraints(cancelButton, 0, 2);
        GridPane.setConstraints(applyButton, 1, 2);

        bottomGrid.getChildren().addAll(label6, input6, label7, input7, cancelButton, applyButton);
        bottomGrid.setAlignment(Pos.CENTER);
        bottomGrid.setVgap(8);
        bottomGrid.setHgap(10);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(grid, bottomGrid);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 500, 500); //resolution
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
}
