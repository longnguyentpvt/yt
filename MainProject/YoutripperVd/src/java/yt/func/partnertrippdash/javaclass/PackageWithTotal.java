/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnertrippdash.javaclass;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 *
 * @author Hiep
 */
public class PackageWithTotal {

    private String packageID;
    private String packageName;
    private BigDecimal totalPay;

    public PackageWithTotal() {
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public static Comparator<PackageWithTotal> TotalCompare
            = (PackageWithTotal booking1, PackageWithTotal booking2) -> {
                BigDecimal total1 = booking1.getTotalPay();
                BigDecimal total2 = booking2.getTotalPay();
                if (total1.compareTo(total2) == -1) {
                    return 1;
                }
                if (total1.compareTo(total2) == 1) {
                    return -1;
                }
                return 0;
            };
}
