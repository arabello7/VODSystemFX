/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tomas
 */
public class Series extends Product {

    private List<Season> seasons = new ArrayList<>();

    public Series(Distributor dist) {
        super(dist);
        System.out.println("generating seasons...");
        generateSeasons(); 
    }

    private void generateSeasons() {
        System.out.println("Season s = new ...");
        Season s = new Season(this.distributor); // Fresh new product
        System.out.println("inherit data...");
        s.inheritSeriesData(this); // Product gets needed data from its Series
        System.out.println("adding to list");
        seasons.add(s);
    }
    
//    public void addSeason (Season se) {
//        seasons.add(se);
//    }
    
    public List<Season> getSeasons(){
        return seasons;
    }
}
