/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.priorityModule;

/**
 *
 * @author Dmitry
 */
public class CalculationPriority {
    
    private int userId = 0;
    private float incomeCoefficient = 0;
    
    
    public void setUserID(int userId){
        this.userId = userId;
    }
    
    public int getUserID(){
        return userId;
    }
    
    private void requestData(int userId){
    
    }
    
    
    private float CompareIncomeCoefficient(){
        return incomeCoefficient;
    }
    
    @Override
    public String toString(){
        String statusString="Класс анализирует и возвращает статистику"
                + " по расходам пользователя.<br/> userID = " + userId;
        return statusString;
    }
    
    CalculationPriority(){
    
    }
    
    CalculationPriority(int userId){
        this.userId = userId;
    }
}
