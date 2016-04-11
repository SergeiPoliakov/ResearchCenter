package com.netcracker.unc.ejb.htmltable;

import java.util.List;

import com.netcracker.unc.newmvc.ejb.models.*;

public class HtmlConverter {
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