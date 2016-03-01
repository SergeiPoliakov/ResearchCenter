package com.netcracker.unc.mvc.models;

public class AttributeModel {

    private String attributeName = null;
    private int finObjectTypeId;
    private int attributeId;

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getFinObjectTypeId() {
        return finObjectTypeId;
    }

    public void setFinObjectTypeId(int finObjectTypeId) {
        this.finObjectTypeId = finObjectTypeId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

}
