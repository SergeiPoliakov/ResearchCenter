package com.netcracker.unc.mvc.models;

public class CaseModel {

    private int finObjectId;
    private Integer parentId = null;
    private String objectName = null;
    private int finObjectTypeId;
    private int userId;

    public int getFinObjectId() {
        return finObjectId;
    }

    public void setFinObjectId(int finObjectId) {
        this.finObjectId = finObjectId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName.toLowerCase();
    }

    public int getFinObjectTypeId() {
        return finObjectTypeId;
    }

    public void setFinObjectTypeId(int finObjectTypeId) {
        this.finObjectTypeId = finObjectTypeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
