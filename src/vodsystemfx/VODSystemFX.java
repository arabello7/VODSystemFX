/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vodsystemfx.classes.Distributor;
import vodsystemfx.classes.Movie;
import vodsystemfx.classes.Product;
import vodsystemfx.classes.User;

/**
 *
 * @author tomas
 */
public class VODSystemFX extends Application {

    private Stage window;
    private Scene scene1, scene2;
    //public int DistributorsTotal = X zapewne
    private static List<Distributor> allDistributors = new ArrayList<>();
    private static List<User> allUsers = new ArrayList<>();
    private static List<Product> allProducts = new ArrayList<>();
    private static List<Movie> allMovies = new ArrayList<>();

    public static List<Movie> getAllMovies() {
        return allMovies;
    }

    public static void addToAllMovies(Movie m) {
        allMovies.add(m);
    }

    public static List<Product> getAllProducts() {
        return allProducts;
    }

    public static void addToAllProducts(Product p) {
        allProducts.add(p);
    }

    public static void addToAllDistributors(Distributor d) {
        allDistributors.add(d);
    }

    public static List<Distributor> getAllDistributors() {
        return allDistributors;
    }
    
    public static Distributor getOneDistributor(int i) {
        return allDistributors.get(i);
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    public static void addToAllUsers(User u) {
        allUsers.add(u);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//throws FileNotFoundException {

        launch(args); //launching application start method
        
//        int i = 15;
//        while (i > 0) {
//            Distributor d = new Distributor();
//            addToAllDistributors(d);
//            FXMLVODSystemController.distListViewUpdate();
//            Product p = new Movie(d);
//            addToAllProducts(p);
//            FXMLVODSystemController.productListViewUpdate();
//            i--;
//        }
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
