package com.netcracker.unc.mvc.models;

public class CaseTypeModel {

    private int finObjectTypeId;
    private String finObjectTypeName = null;

    public int getFinObjectTypeId() {
        return finObjectTypeId;
    }

    public void setFinObjectTypeId(int finObjectTypeId) {
        this.finObjectTypeId = finObjectTypeId;
    }

    public String getFinObjectTypeName() {
        return finObjectTypeName;
    }

    public void setFinObjectTypeName(String finObjectTypeName) {
        this.finObjectTypeName = finObjectTypeName;
    }
}
