/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.common;

/**
 *
 * @author nickn
 */
public class YTFileMeta {

    private String name;
    private String contentType;
    private byte[] bytes;
    private byte[] realBytes;

    public YTFileMeta() {
    }

    public YTFileMeta(String name, String contentType, byte[] bytes, byte[] realBytes) {
        this.name = name;
        this.contentType = contentType;
        this.bytes = bytes;
        this.realBytes = realBytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getRealBytes() {
        return realBytes;
    }

    public void setRealBytes(byte[] realBytes) {
        this.realBytes = realBytes;
    }

}
