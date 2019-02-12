/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.partnerverification.javaclass;

/**
 *
 * @author Hiep
 */
public class VerificationUploadResponse {

    private boolean success;
    private int width;
    private int height;

    public VerificationUploadResponse() {
    }

    public VerificationUploadResponse(boolean success, int width, int height) {
        this.success = success;
        this.width = width;
        this.height = height;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
