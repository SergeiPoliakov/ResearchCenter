package com.netcracker.unc.mvc.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParameterModel {

    private String value = null;
    private SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
    private Date valueDate = null;
    private int finObjectId;
    private int attributeId;
    private java.util.Date oldDate = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        try {
            oldDate = parse.parse(valueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.valueDate = new Date(oldDate.getTime());
    }

    public int getFinObjectId() {
        return finObjectId;
    }

    public void setFinObjectId(int finObjectId) {
        this.finObjectId = finObjectId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

}
