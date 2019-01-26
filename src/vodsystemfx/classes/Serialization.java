/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/** Class is responsible for saving program state when closing program and for bringing it up after launching.
 *
 * @author Tomasz Jurek
 */
public abstract class Serialization {
    private static int distNumber;
    private static int usersNumber;
    private static int productNumber;
    
    /** Goes through lists of products, distributors and users and saves objects. Includes also current state fo system finance.
     * Objects are saved into XML files
     * 
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void serialize() throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("[Encoding]");
        
        distNumber = SystemManager.getAllDistributors().size();
        try (XMLEncoder distEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("serialization/distributors.xml")))) {
            distEncoder.writeObject(distNumber);
            SystemManager.getAllDistributors().forEach((d) -> {
                distEncoder.writeObject(d);
            });
        }
        
        usersNumber = SystemManager.getAllUsers().size();
        try (XMLEncoder userEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("serialization/users.xml")))) {
            userEncoder.writeObject(usersNumber);
            SystemManager.getAllUsers().forEach((u) -> {
                userEncoder.writeObject(u);
            });
        }
           
        productNumber = SystemManager.getAllProducts().size();
        try (XMLEncoder productEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("serialization/products.xml")))) {
            productEncoder.writeObject(productNumber);
            SystemManager.getAllProducts().forEach((p) -> {
                productEncoder.writeObject(p);
            });
        }

        // Saving static fields of System Manager class
        try ( 
                XMLEncoder systemEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("serialization/systemInfo.xml")))) {
            systemEncoder.writeObject(SystemManager.getSystemAccountBalance());
            systemEncoder.writeObject(SystemManager.getBasicSubscriptionPrice());
            systemEncoder.writeObject(SystemManager.getFamilySubscriptionPrice());
            systemEncoder.writeObject(SystemManager.getPremiumSubscriptionPrice());
        }
        
        System.out.println("[Encoding finished]");
        System.out.println("[System status saved]");
    }
    
    /** Getting data from XML files
     * @throws java.io.FileNotFoundException
     */
    public static void deserialize() throws FileNotFoundException {
        System.out.println("[Decoding]");
        
        // Loading list of distributors
        try {
            XMLDecoder distDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("serialization/distributors.xml")));       
            int number = (int) distDecoder.readObject();
            for (int i = 0; i < number - 1; i++) {
                Distributor d = (Distributor) distDecoder.readObject();
                SystemManager.addToAllDistributors(d);
            }
            distDecoder.close();
        } catch (Exception e){
            System.out.println("<No distributors to load>");
        }

        // Loading list of users
        try {
            XMLDecoder userDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("serialization/users.xml")));
            int number = (int) userDecoder.readObject();
            for (int i = 0; i < number - 1; i++) {
                User u = (User) userDecoder.readObject();
                SystemManager.addToAllUsers(u);
            }
            userDecoder.close();
        } catch (Exception e){
            System.out.println("<No users to load>");
        }
        
        // Loading list of products
        try {
            XMLDecoder productDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("serialization/products.xml")));    
            int number = (int) productDecoder.readObject();
            for (int i = 0; i < number - 1; i++) {
                Product p = (Product) productDecoder.readObject();
                SystemManager.addToAllProducts(p);
            }
            productDecoder.close();
        } catch (Exception e){
            System.out.println("<No products to load>");
        }
        
        // Loading system data 
        try {
            XMLDecoder systemDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("serialization/systemInfo.xml")));
            SystemManager.setSystemAccountBalance((double) systemDecoder.readObject());
            SystemManager.setBasicSubscriptionPrice((double) systemDecoder.readObject());
            SystemManager.setFamilySubscriptionPrice((double) systemDecoder.readObject());
            SystemManager.setPremiumSubscriptionPrice((double) systemDecoder.readObject());
            systemDecoder.close();
        } catch (Exception e) {
            System.out.println("<No system data to load>");
        }
        
        System.out.println("[Decoding finished]");
    }
}
