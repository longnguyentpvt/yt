/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.javaclass;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author nickn
 */
public class DescriptionStepData {

    private HashMap<String, DescriptionPackageContent> contentHM;
    private String[] servingLanguages;
    private List<String[]> descriptionLanguages;
    private List<String[]> ytServingLanguages;
    private String activityMarker;
    private String pickupMarker;
    private String departureMarker;
    public boolean disableEditing;

    public DescriptionStepData() {
    }

    public DescriptionStepData(HashMap<String, DescriptionPackageContent> contentHM,
            String[] servingLanguages, List<String[]> descriptionLanguages,
            List<String[]> ytServingLanguages, boolean disableEditing, String activityMarker, String pickupMarker, String departureMarker) {
        this.contentHM = contentHM;
        this.servingLanguages = servingLanguages;
        this.descriptionLanguages = descriptionLanguages;
        this.ytServingLanguages = ytServingLanguages;
        this.disableEditing = disableEditing;
        this.activityMarker = activityMarker;
        this.pickupMarker = pickupMarker;
        this.departureMarker = departureMarker;
    }

    public HashMap<String, DescriptionPackageContent> getContentHM() {
        return contentHM;
    }

    public void setContentHM(HashMap<String, DescriptionPackageContent> contentHM) {
        this.contentHM = contentHM;
    }

    public String[] getServingLanguages() {
        return servingLanguages;
    }

    public void setServingLanguages(String[] servingLanguages) {
        this.servingLanguages = servingLanguages;
    }

    public List<String[]> getDescriptionLanguages() {
        return descriptionLanguages;
    }

    public void setDescriptionLanguages(List<String[]> descriptionLanguages) {
        this.descriptionLanguages = descriptionLanguages;
    }

    public List<String[]> getYtServingLanguages() {
        return ytServingLanguages;
    }

    public void setYtServingLanguages(List<String[]> ytServingLanguages) {
        this.ytServingLanguages = ytServingLanguages;
    }

    public String getActivityMarker() {
        return activityMarker;
    }

    public void setActivityMarker(String activityMarker) {
        this.activityMarker = activityMarker;
    }

    public String getPickupMarker() {
        return pickupMarker;
    }

    public void setPickupMarker(String pickupMarker) {
        this.pickupMarker = pickupMarker;
    }

    public String getDepartureMarker() {
        return departureMarker;
    }

    public void setDepartureMarker(String departureMarker) {
        this.departureMarker = departureMarker;
    }

    public boolean isDisableEditing() {
        return disableEditing;
    }

    public void setDisableEditing(boolean disableEditing) {
        this.disableEditing = disableEditing;
    }

}
