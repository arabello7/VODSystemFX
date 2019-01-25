/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private static volatile boolean stopWork;

    public static void stopWork() {
        stopWork = true;
    }
    
    public void run() {
        while (!stopWork) {
//            if (VODSystemFX.getAllUsers().size() * 2 < VODSystemFX.getAllProducts().size()) {
                try {
                    Thread.sleep(10000);
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
