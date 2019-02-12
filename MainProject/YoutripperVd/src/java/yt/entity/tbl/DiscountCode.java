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
 * @author nickn
 */
@Entity
@Table(name = "DiscountCode", schema = "dbo")
public class DiscountCode implements Serializable {

    @Id
    @Column(name = "Code", unique = true, nullable = false)
    private String code;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "ExpiredCode")
    private boolean expiredCode;

    @Column(name = "MultipleUse")
    private boolean multipleUse;

    @Column(name = "PartnerCode")
    private boolean partnerCode;

    @Column(name = "SpecificDiscount")
    private boolean specificDiscount;

    @Column(name = "CodeType")
    private String codeType;

    @Column(name = "PartnerList")
    private String partnerList;

    @Column(name = "PackageList")
    private String packageList;

    @Column(name = "CategoryList")
    private String categoryList;

    @Column(name = "SubCategoryList")
    private String subCategoryList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discountCode")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<DiscountCurrency> currencies;

    public DiscountCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isExpiredCode() {
        return expiredCode;
    }

    public void setExpiredCode(boolean expiredCode) {
        this.expiredCode = expiredCode;
    }

    public boolean isMultipleUse() {
        return multipleUse;
    }

    public void setMultipleUse(boolean multipleUse) {
        this.multipleUse = multipleUse;
    }

    public boolean isPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(boolean partnerCode) {
        this.partnerCode = partnerCode;
    }

    public boolean isSpecificDiscount() {
        return specificDiscount;
    }

    public void setSpecificDiscount(boolean specificDiscount) {
        this.specificDiscount = specificDiscount;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(String partnerList) {
        this.partnerList = partnerList;
    }

    public String getPackageList() {
        return packageList;
    }

    public void setPackageList(String packageList) {
        this.packageList = packageList;
    }

    public String getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(String categoryList) {
        this.categoryList = categoryList;
    }

    public String getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(String subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public Set<DiscountCurrency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<DiscountCurrency> currencies) {
        this.currencies = currencies;
    }

}
