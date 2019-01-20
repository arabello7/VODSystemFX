/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.List;

/**
 *
 * @author tomas
 */
public class Series extends Product{
    private List<Season> seasons; //lista Sezonów, dodać klase Sezon z listą Odcinków

    public Series(Distributor dist) {
        super(dist);
//        this.seasons = 
    }
}
