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
    
    public void detailsRow(){
        /*
        будет выводить детей строки если они есть.
        */
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
    
    
    public class ResultCategoryRow{
        /*
        как вариант, создать несколько вложенных классов и отдавать лист без 
        generic типа. Вложенные классы будут отличаться полями.
        
        */
        private String Type;
        
        //for categories info
        private String objectId;
        private String objectName;
        private String minPercent;
        private String maxPercent;
        private String finalDate;
        private String sumCategory;
        
        
        
        
        @Override
        public String toString(){
            String statusString = "Экземпляры этого класса - строки запроса "
                    + "финансовых объектов пользователя, "
                    + "возможно за некоторый период, по которым расставляются"
                    + " приоритеты расходов. Объектный тип указывается при "
                    + "добавлении тем или иным методом.";
            return statusString;
        }
    }
    
    public class ResultIncomeRow{
        /*
        строка суммарного дохода.
        */
        private String Type;
        
        //for categories info
        private String objectId;
        private String objectName;
        private String minPercent;
        private String maxPercent;
        private String finalDate;
        private String sumCategory;
        
        
        
        
        @Override
        public String toString(){
            String statusString = "Экземпляры этого класса - строки запроса "
                    + "финансовых объектов пользователя, "
                    + "возможно за некоторый период, по которым расставляются"
                    + " приоритеты расходов. Объектный тип указывается при "
                    + "добавлении тем или иным методом.";
            return statusString;
        }
    }
}
