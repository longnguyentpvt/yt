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
@Table(name = "YTPackageReview", schema = "dbo")
public class YTPackageReview implements Serializable {

    @Id
    @Column(name = "reviewToken", unique = true, nullable = false)
    private String ReviewToken;

    @Column(name = "PackageID")
    private String packageID;

    @Column(name = "YTRate")
    private Integer ytRate;

    @Column(name = "PackageRate")
    private Integer packageRate;

    @Column(name = "TripperComment")
    private String tripperComment;

    @Column(name = "Reviewed")
    private Boolean reviewed;

    @Column(name = "ReviewDate")
    private Long reviewDate;

    @Column(name = "ExpirationDate")
    private Long expirationDate;

    @Column(name = "Anonymous")
    private Boolean anonymous;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytPackageReview")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTBookingOrder> bookingOrders;

    public YTPackageReview() {
    }

    public String getReviewToken() {
        return ReviewToken;
    }

    public void setReviewToken(String ReviewToken) {
        this.ReviewToken = ReviewToken;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public Integer getYtRate() {
        return ytRate;
    }

    public void setYtRate(Integer ytRate) {
        this.ytRate = ytRate;
    }

    public Integer getPackageRate() {
        return packageRate;
    }

    public void setPackageRate(Integer packageRate) {
        this.packageRate = packageRate;
    }

    public String getTripperComment() {
        return tripperComment;
    }

    public void setTripperComment(String tripperComment) {
        this.tripperComment = tripperComment;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public Long getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Long reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Set<YTBookingOrder> getBookingOrders() {
        return bookingOrders;
    }

    public void setBookingOrders(Set<YTBookingOrder> bookingOrders) {
        this.bookingOrders = bookingOrders;
    }

}
