/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common.ytpackage;

/**
 *
 * @author nickn
 */
public class YTPackageColor {

    private String code;
    private String hexCode;

    public YTPackageColor() {
    }

    public YTPackageColor(String code, String hexCode) {
        this.code = code;
        this.hexCode = hexCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

}
