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

/** Stream is kind of Product that can be purchased by user before its streaming date. When the day occurs all users having this stream
 * are watching it and then series is deleted from system.
 *
 * @author Tomasz Jurek
 */
public class Stream extends Product implements Runnable {

    private final GregorianCalendar streamingDate = new GregorianCalendar();
    private static volatile boolean stopWork;
    private int globalIndex;

    public static void stopWork() {
        stopWork = true;
    }
    
    /** Streaming date is always 1 month from date of adding product to the system
     */
    public Stream() {
        super();
        if (Time.getMonth() != 12) {
            streamingDate.set(Time.getYear(), Time.getMonth() + 1, Time.getDay());
        } else {
            streamingDate.set(Time.getYear() + 1, 1, Time.getDay());
        }
        this.globalIndex = -1;

        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start(); //I know I shouldn't add thread in constructor but after adding stream to product list I cant run it anymore cause Product is not runnable
    }
    
    /** When streaming date passed: month now is greater or is the same but day now is greater or even - stream 'is playing'
    *  for every user that has purchased it and then dissapears
    */
    @Override
    public void run() {
        while(!stopWork) {
            try {
                Thread.sleep(500); //Checks every day
                if(Time.getMonth() > streamingDate.get(Calendar.MONTH) || (Time.getMonth() == streamingDate.get(Calendar.MONTH) && Time.getDay() >= streamingDate.get(Calendar.DAY_OF_MONTH))) {
                    SystemManager.removeProduct(globalIndex);
                    stopWork = true;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Stream.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IndexOutOfBoundsException ex) {}
        }
    }

    @Override
    public String getStreamingDate() {
        return streamingDate.get(Calendar.YEAR) + "." + streamingDate.get(Calendar.MONTH) + "." + streamingDate.get(Calendar.DAY_OF_MONTH);
    }
    
    @Override
    public void setGlobalIndex(int index) {
        this.globalIndex = index;
    }
    
    @Override
    public int getGlobalIndex() {
        return globalIndex;
    }
}
