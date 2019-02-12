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
public class EmailAttachment {

    private byte[] file;
    private String name;
    private String contentType;

    public EmailAttachment() {
    }

    public EmailAttachment(byte[] file, String name, String contentType) {
        this.file = file;
        this.name = name;
        this.contentType = contentType;
    }

    public byte[] getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

}
