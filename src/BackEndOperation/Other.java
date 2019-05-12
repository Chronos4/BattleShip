/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEndOperation;

/**
 *
 * @author georg
 */
public class Other extends Ship {

    public Other() {
        this.setSize(6);
        this.setType("Other");
        this.boatArray = new String[this.getSize()];
    }
}
