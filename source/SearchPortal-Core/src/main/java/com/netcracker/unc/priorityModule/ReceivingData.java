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
public class ReceivingData {

    private int UserId = 0;
    
    public void getFinData(){
        
    }
    
    private void UserCosts(){
        
    }
    
    private void UserIncome(){
        
    }
    
    public ReceivingData() {
    }
    
    ReceivingData(int userId){
        this.UserId = userId;
    }
    
    @Override
    public String toString(){
        String statusString = "Класс получает строки доходов и расходов юзера "
                + "и возвразает их на анализ."
                + "<br/> userID = " + UserId;
        return statusString;
    }
}
