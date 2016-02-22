/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.priorityModule;

import java.util.ArrayList;

/**
 *
 * @author Dmitry
 */
public class FillHTMLTable {

    public static String toHTMLString(ArrayList data) {
        StringBuilder htmlString = new StringBuilder("<div class = \"priority-table\">");
        htmlString.append("тестовый текст. Тут будет таблица приоритетов");
        htmlString.append("</div>");
        return htmlString.toString();
    }
    
    private static String addRow(){
        return "";
    }
    
    private static String useCategoryOption(){
        return "";
    }
    
    private static String useSubOption(){
        return "";
    }

    
}
