/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.unc.priorityModule;

import java.util.ArrayList;
import java.util.List;
import com.netcracker.unc.mvc.models.CategoryModel;
/**
 *
 * @author Dmitry
 */
public class FillHTMLTable {

    public static String toHTMLString(List<Object> data) {
        StringBuilder htmlString = new StringBuilder("<div class = \"priority-table\">");
        CategoryModel categoryModel =  (CategoryModel) data.get(1);
        htmlString.append(categoryModel.getObjectId());
        htmlString.append("<br/>");
        htmlString.append(categoryModel.getObjectName());
        htmlString.append("<br/>");
        htmlString.append(categoryModel.getMaxPercent());
        htmlString.append("<br/>");
        htmlString.append(categoryModel.getMinPercent());
        htmlString.append("<br/>");
        //htmlString.append(categoryModel.getCoeficient());
        //htmlString.append(categoryModel.getSumCategory());
        htmlString.append(categoryModel.getFinalDate());
        
        //htmlString.append(data.get(2).toString());
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
