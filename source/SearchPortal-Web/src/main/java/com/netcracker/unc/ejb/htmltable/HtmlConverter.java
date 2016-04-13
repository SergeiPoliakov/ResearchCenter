package com.netcracker.unc.ejb.htmltable;

import java.util.List;

import com.netcracker.unc.newmvc.ejb.models.*;

public class HtmlConverter {
	private static String addSimpleCategoryRow(CategoryModel cm){
		StringBuilder htmlString = new StringBuilder("<tr>");
		
		htmlString.append("<td>");
		htmlString.append(cm.getObjectName());
		htmlString.append("<td>");
		
		htmlString.append("<td>");
		htmlString.append(cm.getCoeficient());
		htmlString.append("</td>");
		
		htmlString.append("<td>");
		htmlString.append(cm.getMinPercent());
		htmlString.append("</td>");
		
		htmlString.append("<td>");
		htmlString.append(cm.getMaxPercent());
		htmlString.append("</td>");
		
		htmlString.append("</tr>");
		return htmlString.toString();
	}
	
	public static String toSimpleHtmlTable(List<CategoryModel> listModels){
		if (listModels != null && !listModels.isEmpty()) {
			StringBuilder htmlSB = new StringBuilder("<table class = \"table\">");
			//<tr><th>Название</th><th>Коэффицент</th><th>Мин. %</th><th>Макс. %</th></tr>
			for (Object object : listModels) {
				CategoryModel categoryModel = (CategoryModel) object;
				htmlSB.append(addSimpleCategoryRow(categoryModel));
			}
			
			htmlSB.append("</table>");
			return htmlSB.toString();
			}else {
				return "<h2>table is empty</h2>";
			}
	}
	
	public static String toHtmlTable(Class<?> clazz, List<?> listModels) {
		if (listModels != null && !listModels.isEmpty()) {
			StringBuilder htmlSB = new StringBuilder("<div id = \"html-model-table\"><table>");

			if (clazz == CategoryModel.class) {
				for (Object object : listModels) {
					CategoryModel categoryModel = (CategoryModel) object;
					htmlSB.append(CategoryHtmlRow.addRow(categoryModel));
				}
				htmlSB.append("<input type=\"button\" class=\"update-category-row-button\" value=\"Изменить\" />");
			}
			if (clazz == StatisticModel.class) {
				/**/
			}
			if (clazz == IncomeConsumptionModel.class) {
				/**/
			}
			// добавить кнопку изменить
			htmlSB.append("</table></div>");
			return htmlSB.toString();
		} else {
			return "<h2>table is empty</h2>";
		}
	}

	public static String toHtmlTableWithUpdateForms(Class<?> clazz, List<?> listModels, long userId) {
		if (listModels != null && !listModels.isEmpty()) {
			StringBuilder htmlSB = new StringBuilder("<div id = \"html-model-table\"><table>");

			if (clazz == CategoryModel.class) {
				for (Object object : listModels) {
					CategoryModel categoryModel = (CategoryModel) object;
					htmlSB.append(CategoryHtmlRow.addRow(categoryModel));
				}
				htmlSB.append("<input type=\"button\" class=\"update-row-button\" value=\"Изменить\" />");
			}
			if (clazz == StatisticModel.class) {
				/**/
			}
			if (clazz == IncomeConsumptionModel.class) {
				/**/
			}
			// добавить кнопку изменить
			htmlSB.append("</table></div>");
			return htmlSB.toString();
		} else {
			return "<h2>table is empty</h2>";
		}
	}
}