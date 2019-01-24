/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodsystemfx.VODSystemFX;

/**
 *
 * @author tomas
 */
public class Daemon implements Runnable {

    private final ExecutorService executor = Executors.newCachedThreadPool(); // For managing many threads

    public void run() {
        while (true) {
//            if (VODSystemFX.getAllUsers().size() * 2 < VODSystemFX.getAllProducts().size()) {
                try {
                    Thread.sleep(50000);
                    User u = new User();
                    VODSystemFX.addToAllUsers(u);
                    System.out.println("New User created account.");
                    executor.submit(u);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Daemon.class.getName()).log(Level.SEVERE, null, ex);
                }
//            }
        }
    }
}
