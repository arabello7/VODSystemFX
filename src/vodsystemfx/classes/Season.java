/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.ArrayList;
import java.util.List;

/** Season is part of every Series. It has similiar title and most fields. It contains list of episodes.
 *
 * @author Tomasz Jurek
 */

// Te klase chcialem bardziej rozbudowac ale zabraklo czasu
public class Season extends Product {
    private final List<Episode> episodesList;

    /** Season gets similiar data as it's series
    * It's a bit not optimal generating new data and then changes for Series's but I counldn't make it around
     * @param s means Series that is having this season
     * @param number is number of season in series
    */
    public void inheritSeriesData(Series s, int number){
        this.title = s.title + " s" + number;
        this.distributor = s.distributor;
        this.price = s.price / 4.0; // round
        this.duration = s.duration / 5;
        this.prodCountries = s.prodCountries;
        this.productionDate = s.productionDate;
        this.actors = s.actors;
    }

    public Season() {
        super();
        this.episodesList = new ArrayList<>();
        generateEpisodes();
    }
    
    /** Generating 10 episodes for each season
     */
    private void generateEpisodes() {
        for (int i = 1; i < 11; i++) {
            Episode e = new Episode(i, this.duration / 10);
            episodesList.add(e);
        }
    }
}
