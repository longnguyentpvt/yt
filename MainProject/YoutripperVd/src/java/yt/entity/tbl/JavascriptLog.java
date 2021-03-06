package yt.entity.tbl;
// Generated Jan 13, 2017 3:31:54 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PartnerCountry generated by hbm2java
 */
@Entity
@Table(name = "JavascriptLog", schema = "dbo")
public class JavascriptLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ErrorID", unique = true, nullable = false)
    private long errorID;

    @Column(name = "ErrorDateTime")
    private Long errorDateTime;

    @Column(name = "PageID")
    private String pageID;

    @Column(name = "PageURL")
    private String pageURL;

    @Column(name = "BrowserType")
    private String browserType;

    @Column(name = "BrowserVersion")
    private String browserVersion;

    @Column(name = "DeviceInfo")
    private String deviceInfo;

    @Column(name = "MobileAccesss")
    private Boolean mobileAccesss;

    @Column(name = "MobileInfo")
    private String mobileInfo;

    public JavascriptLog() {
    }

    public long getErrorID() {
        return errorID;
    }

    public void setErrorID(long errorID) {
        this.errorID = errorID;
    }

    public Long getErrorDateTime() {
        return errorDateTime;
    }

    public void setErrorDateTime(Long errorDateTime) {
        this.errorDateTime = errorDateTime;
    }

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public Boolean getMobileAccesss() {
        return mobileAccesss;
    }

    public void setMobileAccesss(Boolean mobileAccesss) {
        this.mobileAccesss = mobileAccesss;
    }

    public String getMobileInfo() {
        return mobileInfo;
    }

    public void setMobileInfo(String mobileInfo) {
        this.mobileInfo = mobileInfo;
    }

}
