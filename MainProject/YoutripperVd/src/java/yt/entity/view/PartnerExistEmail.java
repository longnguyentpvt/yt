/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author macnew
 */
@Entity
@Immutable
@Table(name = "PartnerExistEmail (NOEXPAND)", schema = "dbo")
public class PartnerExistEmail implements Serializable {

    @Id
    @Column(name = "Email")
    private String email;

    @Column(name = "PartnerID")
    private String partnerID;

    public PartnerExistEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

}
