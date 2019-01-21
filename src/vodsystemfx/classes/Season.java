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
public class Season extends Product {
    private List<Episode> episodesList = new ArrayList<>();
    private int releaseDate;

    // Troche nie optymalne bo losuje nowe pola a potem zmienia od razu, ale nie moglem tego obejść
    public void inheritSeriesData(Series s){
        this.title = s.title;
        this.price = s.price / 4.0; //zaokraglenie poprawic
        this.duration = s.duration / 5;
        this.prodCountries = s.prodCountries;
        this.productionDate = s.productionDate;
        this.actors = s.actors;
        this.releaseDate = 999; //do poprawy z czasem
    }
            
    public Season(Distributor d) {
        super(d);
        generateEpisodes();
    }
    
    private void generateEpisodes() {
        //losuj liczbe 5-10, dla iproszczenia daje tearz 10, moze zmienie
        for (int i = 1; i < 11; i++) {
            Episode e = new Episode(i, this.duration / 10);
            episodesList.add(e);
        }
    }
}
