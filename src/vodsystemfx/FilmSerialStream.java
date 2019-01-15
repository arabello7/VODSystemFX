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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author tomas
 */
public class FilmSerialStream {

//    private static GridPane bottomGrid;
    
    public void bottomBorder() {
        
    }

    public static void display(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        window.setMaxHeight(450);
        window.setTitle(title);

        BorderPane borderPane = new BorderPane();

        GridPane topGrid = new GridPane();
        Text text0 = new Text("Name:");
        Text text1 = new Text("Price:");
        Text text2 = new Text("Release Date:");
        Text text3 = new Text("Duration:");
        Text text4 = new Text("Distributor:");
        Text text5 = new Text("Production Countries:");
        TextField nameTextField = new TextField();
        TextField priceTextField = new TextField();
        TextField dateTextField = new TextField();
        TextField durationTextField = new TextField();
        TextField distributorTextField = new TextField();
        TextField countriesTextField = new TextField();

        GridPane.setConstraints(text0, 0, 0);
        GridPane.setConstraints(text1, 0, 1);
        GridPane.setConstraints(text2, 0, 2);
        GridPane.setConstraints(text3, 0, 3);
        GridPane.setConstraints(text4, 0, 4);
        GridPane.setConstraints(text5, 0, 5);
        GridPane.setConstraints(nameTextField, 1, 0);
        GridPane.setConstraints(priceTextField, 1, 1);
        GridPane.setConstraints(dateTextField, 1, 2);
        GridPane.setConstraints(durationTextField, 1, 3);
        GridPane.setConstraints(distributorTextField, 1, 4);
        GridPane.setConstraints(countriesTextField, 1, 5);

        topGrid.setVgap(8);
        topGrid.setHgap(10);
        topGrid.setAlignment(Pos.CENTER);
        topGrid.getChildren().addAll(text0, text1, text2, text3, text4, text5, nameTextField, priceTextField, dateTextField, durationTextField, distributorTextField, countriesTextField);

        borderPane.setTop(topGrid);

        GridPane bottomGrid = new GridPane();
        
        TextField descriptionTextField = new TextField("lorem ipsum lorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsumlorem ipsum");
        TextField picture = new TextField("pic here");
        Button backButton = new Button("Back");
        Button confirmButton = new Button("Confirm");
        
        GridPane.setConstraints(descriptionTextField, 0, 0);
//        bottomGrid.setConstraints(picture, 0, 1);
        GridPane.setConstraints(backButton, 0, 1);
        GridPane.setConstraints(confirmButton, 1, 1);
        
        bottomGrid.setVgap(8);
        bottomGrid.setHgap(10);
        
        bottomGrid.getChildren().addAll(descriptionTextField, backButton, confirmButton);
        borderPane.setCenter(bottomGrid);
        
        
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(8);
//        grid.setHgap(10);
//
//        Text text = new Text("Chose product");
//        Button movieBtn = new Button("Movie");
//        Button seriesBtn = new Button("Series");
//        Button streamBtn = new Button("Live Stream");
//        Text freeSpace = new Text();
//        Button backBtn = new Button("Back");
//
//        grid.setPrefWidth(120);
//        movieBtn.setMinWidth(grid.getPrefWidth());
//        seriesBtn.setMinWidth(grid.getPrefWidth());
//        streamBtn.setMinWidth(grid.getPrefWidth());
//        backBtn.setMinWidth(grid.getPrefWidth());
//        
//        GridPane.setConstraints(text, 0, 0);
//        GridPane.setConstraints(movieBtn, 0, 1);
//        GridPane.setConstraints(seriesBtn, 0, 2);
//        GridPane.setConstraints(streamBtn, 0, 3);
//        GridPane.setConstraints(freeSpace, 0, 4);
//        GridPane.setConstraints(backBtn, 0, 5);
//        
//        seriesBtn.setOnAction(e -> {
//            try {
//                AddNewProduct.display("New Series");
//            } catch (FileNotFoundException ex) {
//                System.out.println("File Not Found"); //trzeba wtedy w tamtych tez obslugiwać?
//            }
//        });
//        
//        movieBtn.setOnAction(e -> {
//            try {
//                AddNewProduct.display("New Movie"); //*char m jako argument i potem fukncja dokladajaca pola od filmu
//            } catch (FileNotFoundException ex) {
//                System.out.println("File Not Found"); //trzeba wtedy w tamtych tez obslugiwać?
//            }
//        });
//        
//        backBtn.setOnAction(e -> window.close());
//        grid.setAlignment(Pos.CENTER);
//        .getChildren().addAll(text, movieBtn, seriesBtn, streamBtn, freeSpace, backBtn);
        Scene scene = new Scene(borderPane, 450, 500);

        window.setScene(scene);
        window.showAndWait();
    }
}
