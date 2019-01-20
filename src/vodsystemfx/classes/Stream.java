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
public class Stream extends Product {
    private int streamingDate;

    public Stream(Distributor d) {
        super(d);
        streamingDate = 0; //RAND
    }
}
