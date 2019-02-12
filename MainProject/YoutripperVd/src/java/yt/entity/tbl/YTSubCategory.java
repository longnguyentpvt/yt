package yt.entity.tbl;
// Generated Jan 13, 2017 3:31:54 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * PartnerCountry generated by hbm2java
 */
@Entity
@Table(name = "YTSubCategory", schema = "dbo")
public class YTSubCategory implements Serializable {

    @Id
    @Column(name = "SubCategoryID", unique = true, nullable = false)
    private String subCategoryID;

    @Column(name = "CategoryID")
    private String categoryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", insertable = false, updatable = false)
    private YTCategory ytCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytSubCategory")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTSubCategoryContent> contents;

    public YTSubCategory() {
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public Set<YTSubCategoryContent> getContents() {
        return contents;
    }

    public void setContents(Set<YTSubCategoryContent> contents) {
        this.contents = contents;
    }

    public YTCategory getYtCategory() {
        return ytCategory;
    }

    public void setYtCategory(YTCategory ytCategory) {
        this.ytCategory = ytCategory;
    }

}