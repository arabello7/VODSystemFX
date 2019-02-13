/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Movie;
import vodsystemfx.classes.Randomize;
import vodsystemfx.classes.Series;
import vodsystemfx.classes.Stream;
import vodsystemfx.classes.SystemManager;

/** Displays window used for choosing type of product to create
 *
 * @author Tomasz Jurek
 */
public class NewProductWindow {

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

        Distributor d = SystemManager.getAllDistributors().get(Randomize.randomInt(0, SystemManager.getAllDistributors().size() - 1));

        seriesBtn.setOnAction(e -> {
            Series s = new Series();
            s.setDistributor(d);
            if (ProductWindow.display("New Series", s, false) == true) {
                d.addProduct(SystemManager.addToAllProducts(s));
                System.out.println("Series accepted.");
            }
        });

        movieBtn.setOnAction(e -> {
            Movie m = new Movie();
            m.setDistributor(d);
            if (ProductWindow.display("New Movie", m, false) == true) {
                d.addProduct(SystemManager.addToAllProducts(m));
                System.out.println("Movie accepted.");
            }
        });

        streamBtn.setOnAction(e -> {
            Stream st = new Stream();
            st.setDistributor(d);
            if (ProductWindow.display("New Stream", st, false) == true) {
                int index = SystemManager.addToAllProducts(st);
                d.addProduct(index);
                st.setGlobalIndex(index); //So stream can delete itself
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
