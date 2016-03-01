/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.mvc.models;


public class CategoryModel {

    public final String Type = "Категория";
    private String objectId;
    private String objectName;
    private int minPercent;
    private int maxPercent;
    private String finalDate;
    private float coeficient;
    private int sumCategory;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public float getMinPercent() {
        return minPercent;
    }

    public void setMinPercent(int minPercent) {
        this.minPercent = minPercent;
    }

    public float getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(int maxPercent) {
        this.maxPercent = maxPercent;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public float getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(float coeficient) {
        this.coeficient = coeficient;
    }

    public int getSumCategory() {
        return sumCategory;
    }

    public void setSumCategory(int sumCategory) {
        this.sumCategory = sumCategory;
    }
    
    @Override
    public String toString(){
        return "Объектный тип <Категория>.<br/> objectId = " + objectId +"; "
                + "objectName = " + objectName;
    }

    public CategoryModel() {
    }

    public CategoryModel(String objectId, String objectName, int minPercent,
            int maxPercent, String finalDate, float coeficient, int sumCategory) {
        this.objectId = objectId;
        this.objectName = objectName;
        this.minPercent = minPercent;
        this.maxPercent = maxPercent;
        this.finalDate = finalDate;
        this.coeficient = coeficient;
    }
}
