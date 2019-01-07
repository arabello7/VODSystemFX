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
public class AddNewProduct extends AddNewDistributor {

    private static Label label2;
    private static Label label3;
    private static Label label4;
    private static Label label6;
    private static Label label7;
    private static TextField input2;
    private static TextField input3;
    private static TextField input4;
    private static TextField input6;
    private static TextField input7;

    private static void movieBottom(Movie m) { //chyba T<?>
        //Trailer fields
        label6 = new Label("Trailer url:");
        input6 = new TextField(m.getTrailerUrl());

        //Viewing Period fields
        label7 = new Label("Viewing Period:         ");
        input7 = new TextField(String.valueOf(m.getViewingPeriod()));
    }

    private static void seriesBottom(Series s) {
        //Seasons fields
        label6 = new Label("Number of seasons:");
        input6 = new TextField(s.getName() + " - test"); //test

        //Episodes fields
        label7 = new Label("Episodes in season:     ");
        input7 = new TextField("###");
    }

    private static void streamBottom(Product p) {
        label6 = new Label("Streaming Date:          ");
        input6 = new TextField("#random");
        label7 = new Label();
        input7 = new TextField();
        input7.setVisible(false);
    }

    public static boolean display(String title) throws FileNotFoundException {
        displayStandard(title);

        Distributor d = new Distributor();
        d.setName("Spinka Studio");
        Movie p = new Movie(d);
        
        //Name fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField("#random");
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Price fields
        label1 = new Label("Price:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField(String.valueOf(p.getPrice()));
        input1.setEditable(false);
        GridPane.setConstraints(input1, 1, 1);

        //ProductionDate fields
        label2 = new Label("Production Date:");
        GridPane.setConstraints(label2, 0, 2);
        input2 = new TextField(String.valueOf(p.getProductionDate())); //jaki format
        input2.setEditable(false);
        GridPane.setConstraints(input2, 1, 2);

        //Duration fields
        label3 = new Label("Duration:");
        GridPane.setConstraints(label3, 0, 3);
        input3 = new TextField(String.valueOf(p.getDuration()));
        input3.setEditable(false);
        GridPane.setConstraints(input3, 1, 3);

        //Distributor fields
        label4 = new Label("Distributor:");
        GridPane.setConstraints(label4, 0, 4);
        input4 = new TextField(p.getDistributor().getName());
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

        if (title.compareTo("New Movie") == 0) {
            movieBottom(p);
        } else if (title.compareTo("New Series") == 0) {
//            seriesBottom(p);
        } else {
            streamBottom(p);
        }

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
