/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tomas
 */
public class VODSystemFX extends Application {

    private Stage window;
    private Scene scene1, scene2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        launch(args); //launching application start method

//        List<Distributor> distributors = new ArrayList<>();
//        Distributor d1 = new Distributor();
//        System.out.println(d1.getName() + " " + d1.getFinance());
    }

    //Uruchomienie okna aplikacji
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        window = stage;

        window.setTitle("VOD System");

        window.setOnCloseRequest(e -> {
            e.consume(); //canceling prevoius action
            saveProgram();
        });
        //Zawartość okna wczytywana z pliku .fxml
        Scene scene = new Scene(root); // rozmiar okna można dać

//        Button button = new Button("Go to 2nd scene");
//        button1.setOnAction(e -> window.setScene(scene2)); przełączanie pomiędzy oknami
        window.setScene(scene);
        window.show(); // stage appears
    }

    //Jakoś, żeby tego nie powielać muszę z jednego pliku korzystać
    public void saveProgram() {
        System.out.println("Saving program state...");
        // here I can put "are you sure?" method
        window.close();
    }
}
