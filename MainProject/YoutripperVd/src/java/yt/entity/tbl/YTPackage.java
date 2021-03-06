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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * PartnerCountry generated by hbm2java
 */
@Entity
@Table(name = "YTPackage", schema = "dbo")
public class YTPackage implements Serializable {

    @Id
    @Column(name = "PackageID")
    private String packageID;

    @Column(name = "TempPackageID")
    private long tempPackageID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TempPackageID", insertable = false, updatable = false)
    private TemporaryPackage temporaryPackage;

    @Column(name = "PartnerID")
    private String partnerID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PartnerID", insertable = false, updatable = false)
    private Partner partner;

    @Column(name = "CategoryID")
    private String categoryID;

    @Column(name = "SubCategoryID")
    private String subCategoryID;

    @Column(name = "OtherSubCategory")
    private String otherSubCategory;

    @Column(name = "SuitableCouples")
    private boolean suitableCouples;

    @Column(name = "SuitableElderly")
    private boolean suitableElderly;

    @Column(name = "SuitableFamily")
    private boolean suitableFamily;

    @Column(name = "SuitableIndividual")
    private boolean suitableIndividual;

    @Column(name = "SuitableUniversal")
    private boolean suitableUniversal;

    @Column(name = "GenderSuitability")
    private String genderSuitability;

    @Column(name = "TargetLocationID")
    private Integer targetLocationID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TargetLocationID", insertable = false, updatable = false)
    private TargetLocation targetLocation;

    @Column(name = "ColorCode")
    private String colorCode;

    @Column(name = "ServingLanguage")
    private String servingLanguage;

    @Column(name = "CoverType")
    private String coverType;

    @Column(name = "FirstCover")
    private String firstCover;

    @Column(name = "SecondCover")
    private String secondCover;

    @Column(name = "ThirdCover")
    private String thirdCover;

    @Column(name = "VideoCover")
    private String videoCover;

    @Column(name = "PortraitPhoto")
    private String portraitPhoto;

    @Column(name = "FirstThumbnail")
    private String firstThumbnail;

    @Column(name = "SecondThumbnail")
    private String secondThumbnail;

    @Column(name = "ThirdThumbnail")
    private String thirdThumbnail;

    @Column(name = "FourthThumbnail")
    private String fourthThumbnail;

    @Column(name = "FifthThumbnail")
    private String fifthThumbnail;

    @Column(name = "AdditionalThumbnails")
    private String additionalThumbnails;

    @Column(name = "ServingType")
    private String servingType;

    @Column(name = "MinPersons")
    private Integer minPersons;

    @Column(name = "MaxPersons")
    private Integer maxPersons;

    @Column(name = "ServingTimes")
    private Integer servingTimes;

    @Column(name = "FreeAdmission")
    private String freeAdmission;

    @Column(name = "Discounts")
    private String discounts;

    @Column(name = "ExtraOption")
    private String extraOption;

    @Column(name = "DurationType")
    private String durationType;

    @Column(name = "BusinessDuration")
    private Integer businessDuration;

    @Column(name = "TimeSchedule")
    private String timeSchedule;

    @Column(name = "StartTime")
    private Integer startTime;

    @Column(name = "EndTime")
    private Integer endTime;

    @Column(name = "SlotInterval")
    private Integer slotInterval;

    @Column(name = "TimeSlots")
    private String timeSlots;

    @Column(name = "AdvancedBooking")
    private String advancedBooking;

    @Column(name = "AdvancedTime")
    private Integer advancedTime;

    @Column(name = "DateSchedule")
    private String dateSchedule;

    @Column(name = "StartDate")
    private Long startDate;

    @Column(name = "FrequencyServing")
    private int frequencyServing;

    @Column(name = "ClosedMonday")
    private boolean closedMonday;

    @Column(name = "ClosedTuesday")
    private boolean closedTuesday;

    @Column(name = "ClosedWednesday")
    private boolean closedWednesday;

    @Column(name = "ClosedThursday")
    private boolean closedThursday;

    @Column(name = "ClosedFriday")
    private boolean closedFriday;

    @Column(name = "ClosedSaturday")
    private boolean closedSaturday;

    @Column(name = "ClosedSunday")
    private boolean closedSunday;

    @Column(name = "ValidationWeeks")
    private Integer validationWeeks;

    @Column(name = "ValidationStartDate")
    private Long validationStartDate;

    @Column(name = "ValidationEndDate")
    private Long validationEndDate;

    @Column(name = "DateSlots")
    private String dateSlots;

    @Column(name = "QuotaType")
    private String quotaType;

    @Column(name = "SlotQuota")
    private Integer slotQuota;

    @Column(name = "PromotionQuota")
    private Integer promotionQuota;

    @Column(name = "PromotionPeriods")
    private String promotionPeriods;

    @Column(name = "OnPromotional")
    private boolean onPromotional;

    @Column(name = "PromotionSchedule")
    private boolean promotionSchedule;

    @Column(name = "approvedDateTime")
    private Long approvedDateTime;

    @Column(name = "PackageStatus", nullable = false)
    private String packageStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytPackage")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTPackageContent> contents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytPackage")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTPackagePrice> prices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ytPackage")
    @Cascade(value = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<YTBookingOrder> bookingOrders;

    public YTPackage() {
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public long getTempPackageID() {
        return tempPackageID;
    }

    public void setTempPackageID(long tempPackageID) {
        this.tempPackageID = tempPackageID;
    }

    public TemporaryPackage getTemporaryPackage() {
        return temporaryPackage;
    }

    public void setTemporaryPackage(TemporaryPackage temporaryPackage) {
        this.temporaryPackage = temporaryPackage;
    }

    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(String subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public String getOtherSubCategory() {
        return otherSubCategory;
    }

    public void setOtherSubCategory(String otherSubCategory) {
        this.otherSubCategory = otherSubCategory;
    }

    public boolean isSuitableCouples() {
        return suitableCouples;
    }

    public void setSuitableCouples(boolean suitableCouples) {
        this.suitableCouples = suitableCouples;
    }

    public boolean isSuitableElderly() {
        return suitableElderly;
    }

    public void setSuitableElderly(boolean suitableElderly) {
        this.suitableElderly = suitableElderly;
    }

    public boolean isSuitableFamily() {
        return suitableFamily;
    }

    public void setSuitableFamily(boolean suitableFamily) {
        this.suitableFamily = suitableFamily;
    }

    public boolean isSuitableIndividual() {
        return suitableIndividual;
    }

    public void setSuitableIndividual(boolean suitableIndividual) {
        this.suitableIndividual = suitableIndividual;
    }

    public boolean isSuitableUniversal() {
        return suitableUniversal;
    }

    public void setSuitableUniversal(boolean suitableUniversal) {
        this.suitableUniversal = suitableUniversal;
    }

    public String getGenderSuitability() {
        return genderSuitability;
    }

    public void setGenderSuitability(String genderSuitability) {
        this.genderSuitability = genderSuitability;
    }

    public Integer getTargetLocationID() {
        return targetLocationID;
    }

    public void setTargetLocationID(Integer targetLocationID) {
        this.targetLocationID = targetLocationID;
    }

    public TargetLocation getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(TargetLocation targetLocation) {
        this.targetLocation = targetLocation;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getServingLanguage() {
        return servingLanguage;
    }

    public void setServingLanguage(String servingLanguage) {
        this.servingLanguage = servingLanguage;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getFirstCover() {
        return firstCover;
    }

    public void setFirstCover(String firstCover) {
        this.firstCover = firstCover;
    }

    public String getSecondCover() {
        return secondCover;
    }

    public void setSecondCover(String secondCover) {
        this.secondCover = secondCover;
    }

    public String getThirdCover() {
        return thirdCover;
    }

    public void setThirdCover(String thirdCover) {
        this.thirdCover = thirdCover;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getPortraitPhoto() {
        return portraitPhoto;
    }

    public void setPortraitPhoto(String portraitPhoto) {
        this.portraitPhoto = portraitPhoto;
    }

    public String getFirstThumbnail() {
        return firstThumbnail;
    }

    public void setFirstThumbnail(String firstThumbnail) {
        this.firstThumbnail = firstThumbnail;
    }

    public String getSecondThumbnail() {
        return secondThumbnail;
    }

    public void setSecondThumbnail(String secondThumbnail) {
        this.secondThumbnail = secondThumbnail;
    }

    public String getThirdThumbnail() {
        return thirdThumbnail;
    }

    public void setThirdThumbnail(String thirdThumbnail) {
        this.thirdThumbnail = thirdThumbnail;
    }

    public String getFourthThumbnail() {
        return fourthThumbnail;
    }

    public void setFourthThumbnail(String fourthThumbnail) {
        this.fourthThumbnail = fourthThumbnail;
    }

    public String getFifthThumbnail() {
        return fifthThumbnail;
    }

    public void setFifthThumbnail(String fifthThumbnail) {
        this.fifthThumbnail = fifthThumbnail;
    }

    public String getAdditionalThumbnails() {
        return additionalThumbnails;
    }

    public void setAdditionalThumbnails(String additionalThumbnails) {
        this.additionalThumbnails = additionalThumbnails;
    }

    public String getServingType() {
        return servingType;
    }

    public void setServingType(String servingType) {
        this.servingType = servingType;
    }

    public Integer getMinPersons() {
        return minPersons;
    }

    public void setMinPersons(Integer minPersons) {
        this.minPersons = minPersons;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public Integer getServingTimes() {
        return servingTimes;
    }

    public void setServingTimes(Integer servingTimes) {
        this.servingTimes = servingTimes;
    }

    public String getFreeAdmission() {
        return freeAdmission;
    }

    public void setFreeAdmission(String freeAdmission) {
        this.freeAdmission = freeAdmission;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public String getExtraOption() {
        return extraOption;
    }

    public void setExtraOption(String extraOption) {
        this.extraOption = extraOption;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public Integer getBusinessDuration() {
        return businessDuration;
    }

    public void setBusinessDuration(Integer businessDuration) {
        this.businessDuration = businessDuration;
    }

    public String getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(String timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getSlotInterval() {
        return slotInterval;
    }

    public void setSlotInterval(Integer slotInterval) {
        this.slotInterval = slotInterval;
    }

    public String getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(String timeSlots) {
        this.timeSlots = timeSlots;
    }

    public String getAdvancedBooking() {
        return advancedBooking;
    }

    public void setAdvancedBooking(String advancedBooking) {
        this.advancedBooking = advancedBooking;
    }

    public Integer getAdvancedTime() {
        return advancedTime;
    }

    public void setAdvancedTime(Integer advancedTime) {
        this.advancedTime = advancedTime;
    }

    public String getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(String dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public int getFrequencyServing() {
        return frequencyServing;
    }

    public void setFrequencyServing(int frequencyServing) {
        this.frequencyServing = frequencyServing;
    }

    public boolean isClosedMonday() {
        return closedMonday;
    }

    public void setClosedMonday(boolean closedMonday) {
        this.closedMonday = closedMonday;
    }

    public boolean isClosedTuesday() {
        return closedTuesday;
    }

    public void setClosedTuesday(boolean closedTuesday) {
        this.closedTuesday = closedTuesday;
    }

    public boolean isClosedWednesday() {
        return closedWednesday;
    }

    public void setClosedWednesday(boolean closedWednesday) {
        this.closedWednesday = closedWednesday;
    }

    public boolean isClosedThursday() {
        return closedThursday;
    }

    public void setClosedThursday(boolean closedThursday) {
        this.closedThursday = closedThursday;
    }

    public boolean isClosedFriday() {
        return closedFriday;
    }

    public void setClosedFriday(boolean closedFriday) {
        this.closedFriday = closedFriday;
    }

    public boolean isClosedSaturday() {
        return closedSaturday;
    }

    public void setClosedSaturday(boolean closedSaturday) {
        this.closedSaturday = closedSaturday;
    }

    public boolean isClosedSunday() {
        return closedSunday;
    }

    public void setClosedSunday(boolean closedSunday) {
        this.closedSunday = closedSunday;
    }

    public Integer getValidationWeeks() {
        return validationWeeks;
    }

    public void setValidationWeeks(Integer validationWeeks) {
        this.validationWeeks = validationWeeks;
    }

    public Long getValidationStartDate() {
        return validationStartDate;
    }

    public void setValidationStartDate(Long validationStartDate) {
        this.validationStartDate = validationStartDate;
    }

    public Long getValidationEndDate() {
        return validationEndDate;
    }

    public void setValidationEndDate(Long validationEndDate) {
        this.validationEndDate = validationEndDate;
    }

    public String getDateSlots() {
        return dateSlots;
    }

    public void setDateSlots(String dateSlots) {
        this.dateSlots = dateSlots;
    }

    public String getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }

    public Integer getSlotQuota() {
        return slotQuota;
    }

    public void setSlotQuota(Integer slotQuota) {
        this.slotQuota = slotQuota;
    }

    public Integer getPromotionQuota() {
        return promotionQuota;
    }

    public void setPromotionQuota(Integer promotionQuota) {
        this.promotionQuota = promotionQuota;
    }

    public String getPromotionPeriods() {
        return promotionPeriods;
    }

    public void setPromotionPeriods(String promotionPeriods) {
        this.promotionPeriods = promotionPeriods;
    }

    public boolean isOnPromotional() {
        return onPromotional;
    }

    public void setOnPromotional(boolean onPromotional) {
        this.onPromotional = onPromotional;
    }

    public boolean isPromotionSchedule() {
        return promotionSchedule;
    }

    public void setPromotionSchedule(boolean promotionSchedule) {
        this.promotionSchedule = promotionSchedule;
    }

    public Long getApprovedDateTime() {
        return approvedDateTime;
    }

    public void setApprovedDateTime(Long approvedDateTime) {
        this.approvedDateTime = approvedDateTime;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public Set<YTPackageContent> getContents() {
        return contents;
    }

    public void setContents(Set<YTPackageContent> contents) {
        this.contents = contents;
    }

    public Set<YTPackagePrice> getPrices() {
        return prices;
    }

    public void setPrices(Set<YTPackagePrice> prices) {
        this.prices = prices;
    }

    public Set<YTBookingOrder> getBookingOrders() {
        return bookingOrders;
    }

    public void setBookingOrders(Set<YTBookingOrder> bookingOrders) {
        this.bookingOrders = bookingOrders;
    }

}
