package yt.entity.tbl;
// Generated Jan 13, 2017 3:31:54 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * YTSubCategoryContent
 */
@Entity
@Table(name = "YTSubCategoryContent", schema = "dbo")
public class YTSubCategoryContent implements Serializable {

    @Id
    @Column(name = "SubCategoryID")
    private String subCategoryID;

    @Id
    @Column(name = "LanguageCode")
    private String languageCode;

    @Column(name = "SubCategoryName")
    private String subCategoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubCategoryID", insertable = false, updatable = false)
    private YTSubCategory ytSubCategory;

    public YTSubCategoryContent() {
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public YTSubCategory getYtSubCategory() {
        return ytSubCategory;
    }

    public void setYtSubCategory(YTSubCategory ytSubCategory) {
        this.ytSubCategory = ytSubCategory;
    }

}
