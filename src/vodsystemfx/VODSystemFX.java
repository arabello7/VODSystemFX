/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
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
import vodsystemfx.classes.Series;
import vodsystemfx.classes.User;

/**
 *
 * @author tomas
 */
public class VODSystemFX extends Application {

    private static Stage window;
    private Scene scene1, scene2;
    //public int DistributorsTotal = X zapewne
    private static List<Distributor> allDistributors = new ArrayList<>();
    private static List<User> allUsers = new ArrayList<>();
    private static List<Product> allProducts = new ArrayList<>();
    private static List<Movie> allMovies = new ArrayList<>();
//    private static List<Product> allAll = collect(new ArrayList<Movie>(), new ArrayList<Series>());

//    static List<Product> collect(List<? extends Product> a1, List<? extends Product> a2) {
//        List<Product> collected = new ArrayList<>();
//        collected.addAll(a1);
//        return collected;
//    }
//    public static List<Movie> getAllMovies() {
//        return allMovies;
//    }
//
//    public void addToAllMovies(Movie m) {
//        allMovies.add(m);
//    }
    public static List<Product> getAllProducts() {
        return allProducts;
    }

    // Return index of added element
    public static int addToAllProducts(Product p) {
        allProducts.add(p);
        return allProducts.lastIndexOf(p);
    }
    
//    public static void test () {
//        User u = new User();
//        
//        u.buySingleProduct(0); //musi byc produkt juz stworzony
//        u.buySingleProduct(1); //musi byc produkt juz stworzony
//        System.out.println("Product 0 bought");
////        removeProduct(0);
////        System.out.println("Prod 0 deleted");
//        System.out.println( VODSystemFX.getAllProducts().get(u.getProductList().get(0)).getTitle() + "- pierwszy produkt na liscie");
//             removeProduct(0);
//        System.out.println("Prod 0 deleted");
//        try{
//            System.out.println( VODSystemFX.getAllProducts().get(u.getProductList().get(0)).getTitle() + "- pierwszy produkt na liscie");
//        } catch (Exception ex) {
//            System.out.println("Error. List empty");
//        }
//        
//       
//    }

    //** Used when deleting product. Method goes through all Users and deletes product index on their prrivate lists.
    // Note that privateList[0] is not the same as on globalList[0]
    // When deleting f.ex. globalList[4] all next objects's index drops by -1 so I do exacly same on private lists.
    //@globalIndex - index of product on allProducts list
    //@removeIndex - index of product to remove from allProducts list
    //@localIndex - index on user's private list
    public static void removeProduct(int removeIndex) {
        allProducts.remove(removeIndex);
        for (User u : allUsers) {
            for (int localIndex = 0; localIndex < u.getProductList().size(); localIndex++) {
                if (u.getProductList().get(localIndex) > removeIndex) u.reduceProductIndex(localIndex);
                else if (u.getProductList().get(localIndex) == removeIndex) u.removeProduct(localIndex);
            }
        }
    }

    public static void addToAllDistributors(Distributor d) {
        allDistributors.add(d);
    }

    // ** Used when deleting distributor. Removing distributor also remove all its products
    // @global index - index of product on allProducts list
    public static void removeDistributor(int index) {
        for (int globalIndex : getOneDistributor(index).getProductList()) {
            removeProduct(globalIndex);
        }
        allDistributors.remove(index);
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

    public static void removeUser(int index) {
        allUsers.remove(index);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//throws FileNotFoundException {
        launch(args); //launching application start method
    }

    // Uruchomienie okna aplikacji
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        window = stage;

        window.setTitle("VOD System");

        window.setOnCloseRequest(e -> {
            e.consume(); //canceling prevoius action
            saveProgram();
        });
        // Zawartość okna wczytywana z pliku .fxml
        Scene scene = new Scene(root); // rozmiar okna można dać

//        Button button = new Button("Go to 2nd scene");
//        button1.setOnAction(e -> window.setScene(scene2)); przełączanie pomiędzy oknami
        window.setScene(scene);
        window.show();
    }

    //Jakoś, żeby tego nie powielać muszę z jednego pliku korzystać
    public static void saveProgram() {
        System.out.println("Saving program state...");
        // here I can put "are you sure?" alert method
        window.close();
    }
}
