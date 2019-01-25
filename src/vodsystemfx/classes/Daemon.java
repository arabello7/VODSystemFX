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
 * @author Tomasz Jurek
 */
public class Daemon implements Runnable {

    private final ExecutorService executor = Executors.newCachedThreadPool(); // For managing many threads
    private static volatile boolean stopWork; // For safely stoping threads

    public void stopWork() {
        stopWork = true;
    }
    
    /** Method is used for safely stoping threads of all users and distributors.
     */
    public static void stopAllThreads() {
        VODSystemFX.getAllDistributors().forEach((d) -> {
            d.stopWork();
        });
        VODSystemFX.getAllUsers().forEach((u) -> {
            u.stopWork();
        });
        System.out.println("[Simulation is ending]");
        stopWork = true;
    }
    
    /** Daemon thread is responsible for creating new users
     */
    @Override
    public void run() {
        while (!stopWork) {
            try {
                Thread.sleep(5000);
                User u = new User();
                VODSystemFX.addToAllUsers(u);
                System.out.println("New User created account.");
                executor.submit(u);
            } catch (InterruptedException ex) {
                Logger.getLogger(Daemon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
