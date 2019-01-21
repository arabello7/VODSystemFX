/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

/**
 *
 * @author tomas
 */
public class Episode {

    private int number; //albo title 
    private int duration;
//    private int releaseDate;

    public void randomizeReleaseDate(){
//        
    }
    
    public Episode(int number, int duration) { //rel Date jedna dla sezonu, w zasadzie do klasy sezon dodac
        this.number = number;
        this.duration = duration;
    }
}
