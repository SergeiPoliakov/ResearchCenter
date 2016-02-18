/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.mvc.models;

/**
 *
 * @author Dmitry
 */
public class CategoryModel {

    public final String Type = "Категория";
    private String objectId;
    private String objectName;
    private float minPercent;
    private float maxPercent;
    private String finalDate;
    private float coeficient;
    private int sumCategory = 0;

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

    public void setMinPercent(float minPercent) {
        this.minPercent = minPercent;
    }

    public float getMaxPercent() {
        return maxPercent;
    }

    public void setMaxPercent(float maxPercent) {
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
        return "Объектный тип <Категория>. "
                + "Имеет доп. поля, которые при потребности "
                + "заполняются суммой детей категории";
    }

    public CategoryModel() {
    }

    public CategoryModel(String objectId, String objectName, float minPercent,
            float maxPercent, String finalDate, float coeficient, int sumCategory) {
        this.objectId = objectId;
        this.objectName = objectName;
        this.minPercent = minPercent;
        this.maxPercent = maxPercent;
        this.finalDate = finalDate;
        this.coeficient = coeficient;
    }
    
}
