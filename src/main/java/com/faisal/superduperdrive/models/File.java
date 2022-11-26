package com.faisal.superduperdrive.models;

import java.util.Base64;

public class File {

    public File(Integer id, String filename, String contentType, String filesize, int userId, byte[] fileData) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.filesize = filesize;
        this.userId = userId;
        this.fileData = fileData;
    }

    private Integer id;
    private String filename;
    private String contentType;
    private String filesize;
    private int userId;
    private byte[] fileData;

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String viewFile(){
        return Base64.getEncoder().encodeToString(this.getFileData());
    }
}
