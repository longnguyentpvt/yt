/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.javaclass;

import javaclass.common.ytpackage.YTPackageMultiDescription;
import javaclass.common.ytpackage.YTPackageLocation;
import java.util.List;

/**
 *
 * @author nickn
 */
public class DescriptionPackageContent {

    private String languageCode;
    private String packageName;
    private String googleDescription;
    private List<YTPackageMultiDescription> multiDescriptions;
    private List<YTPackageLocation> activityLocations;
    private List<YTPackageLocation> pickupLocations;
    private List<YTPackageLocation> departureLocations;
    private List<String> keywords;
    private boolean registered;

    public DescriptionPackageContent() {
    }

    public DescriptionPackageContent(String languageCode, String packageName, String googleDescription, List<YTPackageMultiDescription> multiDescriptions, List<YTPackageLocation> activityLocations, List<YTPackageLocation> pickupLocations, List<YTPackageLocation> departureLocations, List<String> keywords, boolean registered) {
        this.languageCode = languageCode;
        this.packageName = packageName;
        this.googleDescription = googleDescription;
        this.multiDescriptions = multiDescriptions;
        this.activityLocations = activityLocations;
        this.pickupLocations = pickupLocations;
        this.departureLocations = departureLocations;
        this.keywords = keywords;
        this.registered = registered;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getGoogleDescription() {
        return googleDescription;
    }

    public void setGoogleDescription(String googleDescription) {
        this.googleDescription = googleDescription;
    }

    public List<YTPackageMultiDescription> getMultiDescriptions() {
        return multiDescriptions;
    }

    public void setMultiDescriptions(List<YTPackageMultiDescription> multiDescriptions) {
        this.multiDescriptions = multiDescriptions;
    }

    public List<YTPackageLocation> getActivityLocations() {
        return activityLocations;
    }

    public void setActivityLocations(List<YTPackageLocation> activityLocations) {
        this.activityLocations = activityLocations;
    }

    public List<YTPackageLocation> getPickupLocations() {
        return pickupLocations;
    }

    public void setPickupLocations(List<YTPackageLocation> pickupLocations) {
        this.pickupLocations = pickupLocations;
    }

    public List<YTPackageLocation> getDepartureLocations() {
        return departureLocations;
    }

    public void setDepartureLocations(List<YTPackageLocation> departureLocations) {
        this.departureLocations = departureLocations;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

}
