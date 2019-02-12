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
 * @author Hiep
 */
@Entity
@Table(name = "YTCategoryContent", schema = "dbo")
public class YTCategoryContent implements Serializable {

    @Id
    @Column(name = "CategoryID")
    private String categoryID;

    @Id
    @Column(name = "LanguageCode")
    private String languageCode;

    @Column(name = "CategoryName")
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", insertable = false, updatable = false)
    private YTCategory ytCategory;

    public YTCategoryContent() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public YTCategory getYtCategory() {
        return ytCategory;
    }

    public void setYtCategory(YTCategory ytCategory) {
        this.ytCategory = ytCategory;
    }

}
