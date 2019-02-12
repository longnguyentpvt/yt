/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.packageregistration.javaclass;

/**
 *
 * @author nickn
 */
public class CoverUploadResponse {

    private boolean success;
    private int width;
    private int height;
    private int coverIndex;
    private int range;

    public CoverUploadResponse() {
    }

    public CoverUploadResponse(boolean success, int width, int height, int coverIndex, int range) {
        this.success = success;
        this.width = width;
        this.height = height;
        this.coverIndex = coverIndex;
        this.range = range;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCoverIndex() {
        return coverIndex;
    }

    public void setCoverIndex(int coverIndex) {
        this.coverIndex = coverIndex;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
