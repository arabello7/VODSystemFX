/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import vodsystemfx.FXMLVODSystemController;
import vodsystemfx.VODSystemFX;

/** Time class is managing time of the system. 
 *
 * @author Tomasz Jurek
 */
public class Time implements Runnable {

    private static GregorianCalendar calendar;
    private static volatile boolean stopWork;

    public Time(int year, int month, int day) {
        calendar = new GregorianCalendar(year, month, day);
    }
    
    public static void stopWork() {
        stopWork = true;
    }

    /** Time thread is responsible for running system time and checking stop condition. One day in system is 0.5 seconds in real
     * Every month system displays current date and invokes finance settlement. If system is not profitable for 3 months in a row
     * simulation is ending and dramatic music appears!
     */
    @Override
    public void run() {
        int checkSum = 0; // For checking profitability of the system 
        double cashFlow;
        while (!stopWork) {
            // One month
            for (int day = 0; day < 30; day++) {
                try {
                    Thread.sleep(500); //one day passes
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // Displaying system info
            System.out.println("[Current date:" + calendar.get(Calendar.YEAR) + "." + calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.DAY_OF_MONTH) + "]");
            System.out.println("[System starts the monthly settlement]");
            cashFlow = SystemManager.monthlySettlement();
            System.out.println("[System cash flow in month: " + cashFlow + "]");
            
            // Saving profitability
            if (cashFlow < 0) {
                checkSum++;
            } else {
                checkSum = 0;
            }

            // System is not profitable for 3 months still. Simulation ends.
            if (checkSum == 3) {
                System.out.println("[System is not profitable!]");
                stopWork = true; //Suicide
                FXMLVODSystemController.playDramaticMusic(); //Worth it!
                Daemon.stopAllThreads();
                VODSystemFX.saveProgram();
            }
        }
    }
    
    public static int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear() {
        return calendar.get(Calendar.YEAR);
    }
}
