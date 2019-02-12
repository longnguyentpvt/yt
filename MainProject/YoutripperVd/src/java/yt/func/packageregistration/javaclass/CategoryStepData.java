/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.javaclass;

import java.util.List;
import java.util.Map;
import javaclass.common.ytpackage.YTPackageColor;
import yt.entity.tbl.TargetLocationContent;
import yt.entity.tbl.TemporaryPackage;

/**
 *
 * @author nickn
 */
public class CategoryStepData {

    private Map<String, YTPackageColor> pkgColors;
    private List<TargetLocationContent> targetLocations;
    private TemporaryPackage ytPackage;

    public CategoryStepData() {
    }

    public CategoryStepData(Map<String, YTPackageColor> pkgColors, List<TargetLocationContent> targetLocations, TemporaryPackage ytPackage) {
        this.pkgColors = pkgColors;
        this.targetLocations = targetLocations;
        this.ytPackage = ytPackage;
    }

    public Map<String, YTPackageColor> getPkgColors() {
        return pkgColors;
    }

    public void setPkgColors(Map<String, YTPackageColor> pkgColors) {
        this.pkgColors = pkgColors;
    }

    public List<TargetLocationContent> getTargetLocations() {
        return targetLocations;
    }

    public void setTargetLocations(List<TargetLocationContent> targetLocations) {
        this.targetLocations = targetLocations;
    }

    public TemporaryPackage getYtPackage() {
        return ytPackage;
    }

    public void setYtPackage(TemporaryPackage ytPackage) {
        this.ytPackage = ytPackage;
    }

}
