/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vodsystemfx.classes;

/** Movie is kind of product that can be purchased any time but user has 3 months to watch it as much as he wants 
 * before it is removed from user's list. It's extended by url of trailer. Check it out!
 *
 * @author Tomasz Jurek
 */
public class Movie extends Product {

    private String trailerUrl;

    @Override
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public Movie() {
        super();
        this.trailerUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    }
    
    
}
