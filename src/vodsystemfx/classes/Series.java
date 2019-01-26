/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/** Series is kind of Product extended by list of seasons and release date. Series can be bought by user any time, but user cannot watch it
 * before the release date.
 * @author Tomasz Jurek
 */
public final class Series extends Product {

    private final List<Season> seasons;
    private final GregorianCalendar releaseDate = new GregorianCalendar();

    /** Release date is calculated by adding 5 months to current system date
     */
    public Series() {
        super();
        this.seasons = new ArrayList<>();
        if (Time.getMonth() <= 7) {
            this.releaseDate.set(Time.getYear(), Time.getMonth() + 5, Time.getDay());
        } else {
            this.releaseDate.set(Time.getYear() + 1, Time.getMonth() - 7, Time.getDay());
        }
        generateSeasons(); 
    }

    /** Generating 5 seasons for the series
     */
    private void generateSeasons() {
        for (int i = 1; i < 6; i++) {
            Season s = new Season(); // Fresh new product
            s.inheritSeriesData(this, i); // Product (season) gets needed data from its Series
            seasons.add(s);
        }
    }

    // Returns true when Series is realeased. From this time Users can watch it
    @Override
    public boolean isReleased() {
        return Time.getMonth() > releaseDate.get(Calendar.MONTH) || (Time.getMonth() == releaseDate.get(Calendar.MONTH) && Time.getDay() >= releaseDate.get(Calendar.DAY_OF_MONTH));      
    }
    
    @Override
    public List<Season> getSeasons(){
        return seasons;
    }
}
