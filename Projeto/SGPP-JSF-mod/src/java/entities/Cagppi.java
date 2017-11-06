/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author CristianoSilva
 */
@Entity
@DiscriminatorValue(value="Cagppi")
public class Cagppi extends Usuario implements Serializable{
    private static final long serialVersionUID = 5953225846505938118L;
    
    public Cagppi() {
    }    
}
