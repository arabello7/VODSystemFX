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

    private int releaseDate;

    public Episode(Distributor dist, int release) {
//        this.distributor = dist;
        this.releaseDate = release;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReleaseDate() {
        return releaseDate;
    }
}
