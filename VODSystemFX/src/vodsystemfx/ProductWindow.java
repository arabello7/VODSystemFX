/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import vodsystemfx.classes.Product;

/** Displays window with details of product.
 *
 * @author Tomasz Jurek
 */
public class ProductWindow extends DistributorWindow {

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

    /** Only for movie
     */
    private static void movieBottom(Product m) {
        //Trailer fields
        label6 = new Label("Trailer url:");
        input6 = new TextField(m.getTrailerUrl());

        //Viewing Period fields
        label7 = new Label("Viewing Period:         ");
        input7 = new TextField(String.valueOf("3 months"));
    }

    /** Only for series
     */
    private static void seriesBottom(Product s) {
        //Seasons fields
        label6 = new Label("Number of seasons:");
        input6 = new TextField(String.valueOf(s.getSeasons().size()));

        //Episodes fields
        label7 = new Label("Episodes in season:     ");
        input7 = new TextField("10?");
    }

    /** Only for stream
     */
    private static void streamBottom(Product st) {
        label6 = new Label("Streaming Date:          ");
        input6 = new TextField(st.getStreamingDate());
        label7 = new Label();
        input7 = new TextField();
        input7.setVisible(false);
    }

    public static boolean display(String title, Product p, boolean ifEdit) {
        displayStandard(title, ifEdit);
        
        //Image field
        ImageView imageView = new ImageView();
        imageView.setImage(p.getPhoto());
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setCache(true); //improve performance   
        
        //Creating chart
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Views chart");
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series();
        for (int i = 1; i < 13; i++) {
            series.getData().add(new XYChart.Data(i, p.getMonthlyViews(i)));
        }
        lineChart.getData().add(series);
        
        //Name fields
        label0 = new Label("Name:");
        GridPane.setConstraints(label0, 0, 0);
        input0 = new TextField(p.getTitle());
        input0.setEditable(false);
        GridPane.setConstraints(input0, 1, 0);

        //Price fields
        label1 = new Label("Price [$]:");
        GridPane.setConstraints(label1, 0, 1);
        input1 = new TextField(String.valueOf(p.getPrice()));
        input1.setEditable(true);
        GridPane.setConstraints(input1, 1, 1);

        //ProductionDate fields
        label2 = new Label("Production Date:");
        GridPane.setConstraints(label2, 0, 2);
        input2 = new TextField(String.valueOf(p.getProductionDate()));
        input2.setEditable(false);
        GridPane.setConstraints(input2, 1, 2);

        //Duration fields
        label3 = new Label("Duration [min]:");
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
        
        //Actors fields
        Label label8 = new Label("Actors:");
        GridPane.setConstraints(label8, 0, 6);
        TextField input8 = new TextField(p.getActors()[0] + ", " + p.getActors()[1] + ", " + p.getActors()[2]);
        input8.setEditable(false);
        GridPane.setConstraints(input8, 1, 6);
        
        //Ratings fields
        Label label9 = new Label("Rating:");
        GridPane.setConstraints(label9, 0, 7);
        TextField input9 = new TextField(String.valueOf(p.getUsersRating()));
        input9.setEditable(false);
        GridPane.setConstraints(input9, 1, 7);

        grid.getColumnConstraints().add(0, new ColumnConstraints(150));
        grid.getColumnConstraints().add(1, new ColumnConstraints(250));
        grid.getChildren().addAll(label0, input0, label1, input1, label2, input2, label3, input3, input4, label4, label5, input5, label8, input8, input9, label9);
        grid.setAlignment(Pos.CENTER);

        // Displaying bottom grid depending on object class
        if (p.getTrailerUrl() != null) {
            movieBottom(p);
        } else if (p.getSeasons() != null) {
            seriesBottom(p);
        } else {
            streamBottom(p);
        }

        GridPane bottomGrid = new GridPane();
        bottomGrid.getColumnConstraints().add(0, new ColumnConstraints(150));
        bottomGrid.getColumnConstraints().add(1, new ColumnConstraints(250));
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
        vbox.getChildren().addAll(imageView, lineChart, grid, bottomGrid);
        vbox.setAlignment(Pos.CENTER);
        
        VBox.setMargin(imageView, new Insets(10, 0, 0, 0)); //margin top
        VBox.setMargin(bottomGrid, new Insets(0, 0, 10, 0)); //margin bottom

        Scene scene = new Scene(vbox, 550, 750); //resolution
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
    
    /** When opening product for editing
     */
    public void drawEditableFields() {
        GridPane rightGrid = new GridPane();
        
    }
}
