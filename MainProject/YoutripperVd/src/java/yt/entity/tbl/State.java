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
@Table(name = "State", schema = "dbo")
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StateID", unique = true, nullable = false)
    private long stateID;

    @Column(name = "CountryID")
    private String countryID;

    @Column(name = "StateName")
    private String stateName;

    public State() {
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
    public final static Comparator<State> STATE_NAME_COMPARATOR = (State p1, State p2) -> {
        return p1.getStateName().compareTo(p2.getStateName());
    };
}
