/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IDD_LENOVO
 */
@Entity
@Table(name = "PartnerState", schema = "dbo")
public class PartnerState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StateID", unique = true, nullable = false)
    private long stateID;

    @Column(name = "CountryID")
    private String countryID;

    @Column(name = "StateName")
    private String stateName;

    public PartnerState() {
    }

    public long getStateID() {
        return stateID;
    }

    public void setStateID(long stateID) {
        this.stateID = stateID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Sort by Alphabetically
     */
    public final static Comparator<PartnerState> STATE_NAME_COMPARATOR = (PartnerState p1, PartnerState p2) -> {
        return p1.getStateName().compareTo(p2.getStateName());
    };
}
