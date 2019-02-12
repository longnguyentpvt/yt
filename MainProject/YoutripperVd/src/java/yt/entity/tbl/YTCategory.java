/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.tbl;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Hiep
 */
@Entity
@Table(name = "YTCategory", schema = "dbo")
public class YTCategory implements Serializable {

    @Id
    @Column(name = "CategoryID", unique = true, nullable = false)
    private String categoryID;

    @Column(name = "CategoryOrder")
    private Integer categoryOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytCategory")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTCategoryContent> contents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytCategory")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTSubCategory> ytSubCategories;

    public YTCategory() {
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public Set<YTCategoryContent> getContents() {
        return contents;
    }

    public void setContents(Set<YTCategoryContent> contents) {
        this.contents = contents;
    }

    public Set<YTSubCategory> getYtSubCategories() {
        return ytSubCategories;
    }

    public void setYtSubCategories(Set<YTSubCategory> ytSubCategories) {
        this.ytSubCategories = ytSubCategories;
    }

}
