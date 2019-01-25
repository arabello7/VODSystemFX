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
 * @author Tomasz Jurek
 */

// Te klase chcialem bardziej rozbudowac ale zabraklo czasu
public class Season extends Product {
    private final List<Episode> episodesList;

    // **Season gets similiar data as it's series
    // It's a bit not optimal generating new data and then changes for Series's but I counldn't make it around
    public void inheritSeriesData(Series s, int number){
        this.title = s.title + " s" + number;
        this.price = s.price / 4.0; // round
        this.duration = s.duration / 5;
        this.prodCountries = s.prodCountries;
        this.productionDate = s.productionDate;
        this.actors = s.actors;
    }

    public Season(Distributor d) {
        super(d);
        this.episodesList = new ArrayList<>();
        generateEpisodes();
    }
    
    private void generateEpisodes() {
        for (int i = 1; i < 11; i++) {
            Episode e = new Episode(i, this.duration / 10);
            episodesList.add(e);
        }
    }
}
