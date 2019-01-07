/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author tomas
 */
public class AddNewProductChoose {
    
    public void handleMovieClick() {
//        boolean bool = AddNewMovie.display("New Movie");
    }
    
    public static void display(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(400);
        window.setMaxHeight(450);
        window.setTitle(title);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Text text = new Text("Chose product");
        Button movieBtn = new Button("Movie");
        Button seriesBtn = new Button("Series");
        Button streamBtn = new Button("Live Stream");
        Text freeSpace = new Text();
        Button backBtn = new Button("Back");

        grid.setPrefWidth(120);
        movieBtn.setMinWidth(grid.getPrefWidth());
        seriesBtn.setMinWidth(grid.getPrefWidth());
        streamBtn.setMinWidth(grid.getPrefWidth());
        backBtn.setMinWidth(grid.getPrefWidth());
        
        GridPane.setConstraints(text, 0, 0);
        GridPane.setConstraints(movieBtn, 0, 1);
        GridPane.setConstraints(seriesBtn, 0, 2);
        GridPane.setConstraints(streamBtn, 0, 3);
        GridPane.setConstraints(freeSpace, 0, 4);
        GridPane.setConstraints(backBtn, 0, 5);
        
        seriesBtn.setOnAction(e -> {
            try {
                AddNewProduct.display("New Series");
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found"); //trzeba wtedy w tamtych tez obslugiwać?
            }
        });
        
        movieBtn.setOnAction(e -> {
            try {
                AddNewProduct.display("New Movie"); //*char m jako argument i potem fukncja dokladajaca pola od filmu
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found"); //trzeba wtedy w tamtych tez obslugiwać?
            }
        });
        
        streamBtn.setOnAction(e -> {
            try {
                AddNewProduct.display("New Stream");
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found"); //trzeba wtedy w tamtych tez obslugiwać?
            }
        });
        
        backBtn.setOnAction(e -> window.close());

        grid.setAlignment(Pos.CENTER);
        grid.getChildren().addAll(text, movieBtn, seriesBtn, streamBtn, freeSpace, backBtn);
        Scene scene = new Scene(grid, 300, 350);

        window.setScene(scene);
        window.showAndWait();
    }
}
