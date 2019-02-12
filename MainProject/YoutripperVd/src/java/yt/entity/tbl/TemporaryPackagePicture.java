/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nickn
 */
@Entity
@Table(name = "TemporaryPackagePicture", schema = "dbo")
public class TemporaryPackagePicture implements Serializable {

    @Id
    @Column(name = "PictureID")
    private long pictureID;

    @Column(name = "PictureName")
    private String pictureName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageID", insertable = false, updatable = false)
    private TemporaryPackage temporaryPackage;

    @Column(name = "packageID")
    private long packageID;

    public TemporaryPackagePicture() {
    }

    public long getPictureID() {
        return pictureID;
    }

    public void setPictureID(long pictureID) {
        this.pictureID = pictureID;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public TemporaryPackage getTemporaryPackage() {
        return temporaryPackage;
    }

    public void setTemporaryPackage(TemporaryPackage temporaryPackage) {
        this.temporaryPackage = temporaryPackage;
    }

    public long getPackageID() {
        return packageID;
    }

    public void setPackageID(long packageID) {
        this.packageID = packageID;
    }

}
