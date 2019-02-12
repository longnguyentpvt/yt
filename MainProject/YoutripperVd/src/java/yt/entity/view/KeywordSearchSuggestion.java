/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.entity.view;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author Hiep
 */
@Entity
@Immutable
@Table(name = "KeywordSearchSuggestion (NOEXPAND)", schema = "dbo")
public class KeywordSearchSuggestion implements Serializable {

    @Id
    @Column(name = "Keyword")
    private String keyword;

    @Id
    @Column(name = "CategoryID")
    private String categoryID;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "LanguageCode")
    private String languageCode;

    @Column(name = "NoFound")
    private Long noFound;

    public KeywordSearchSuggestion() {

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Long getNoFound() {
        return noFound;
    }

    public void setNoFound(Long noFound) {
        this.noFound = noFound;
    }

}
