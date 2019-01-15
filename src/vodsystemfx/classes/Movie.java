/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

import java.security.SecureRandom;

/**
 *
 * @author tomas
 */
public class Movie extends Product /*implements Promotion*/ { //interfejs

    private String trailerUrl;
    private int viewingPeriod; //how long you can view after buying
    private String genre;
    private enum Genre {
        action, dramma, comedy, forKids, documentary, sensational
    }
    private static final SecureRandom random = new SecureRandom();
    
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public int getViewingPeriod() {
        return viewingPeriod;
    }

    public void setViewingPeriod(int viewingPeriod) {
        this.viewingPeriod = viewingPeriod;
    }

    public Movie(Distributor dist) {
        super(dist);
        this.trailerUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
//        randomEnum(Genre.class); //nie wiem co z tym
        this.viewingPeriod = 0; //random
    }
    
/*
    @Override
    public void setStartDate(int date) {
//        startDate = date;
    }

    @Override
    public void setFinishDate(int date) {
       // jakoś musi to działać, że po upływie tego czasu setDiscount (0)
    }

    @Override
    public void setDiscount(int discount) {
        // jakaś informacja, że jest "DISCOUNT " + discount + "%"
        this.price = price - price * discount;
    }
*/
}
