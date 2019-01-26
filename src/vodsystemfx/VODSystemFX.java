/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vodsystemfx.classes.Daemon;
import vodsystemfx.classes.Serialization;

/** Main class launching application. Contains method used when closing.
 *
 * @author Tomasz Jurek
 */
public class VODSystemFX extends Application{

    private static Stage window;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); //invocates application start method
    }

    /** Launching application window
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        window = stage;

        window.setTitle("VOD System");

        window.setOnCloseRequest(e -> {
            e.consume(); //canceling prevoius action
            saveProgram();
        });

        Scene scene = new Scene(root);

        window.setScene(scene);
        window.show();
    }


     
    /** Safely saving program. Killing active threads. Saving current state
     */
    public static void saveProgram() {
        Daemon.stopAllThreads();
        try {
            Serialization.serialize();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(VODSystemFX.class.getName()).log(Level.SEVERE, null, ex);
        }
        window.close();
    }
}
