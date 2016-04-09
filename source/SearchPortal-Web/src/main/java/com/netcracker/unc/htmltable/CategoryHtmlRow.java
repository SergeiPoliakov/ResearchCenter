package com.netcracker.unc.htmltable;

import com.netcracker.unc.newmvc.dao.models.CategoryModel;

public class CategoryHtmlRow {

	public static String addRow(CategoryModel categoryModel) {
		StringBuilder htmlString = new StringBuilder("<tr><td>");
		addViewForm(categoryModel);
		htmlString.append("</td></tr>");
		return htmlString.toString();
	}

	private static String addViewForm(CategoryModel cm) {
		StringBuilder htmlString = new StringBuilder("<form class=\"view-category-form\">");

		if (cm.getObjectName() != null && !cm.getObjectName().isEmpty()) {
			htmlString.append(cm.getObjectName());
		}
		if (cm.getCoeficient() != null) {
			if (cm.getCoeficient().doubleValue() == 0.75)
				htmlString.append("Высокий");
			if (cm.getCoeficient().doubleValue() == 0.5)
				htmlString.append("Средний");
			if (cm.getCoeficient().doubleValue() == 0.25)
				htmlString.append("Низкий");
		}
		if (cm.getMinPercent() != null) {
			htmlString.append("<input type=\"text\" class=\"min-percent-view\" value=\"");
			htmlString.append(cm.getMinPercent());
			htmlString.append("\" readonly />");
		}
		if (cm.getMaxPercent() != null) {
			htmlString.append("<input type=\"text\" class=\"max-percent-view\" value=\"");
			htmlString.append(cm.getMaxPercent());
			htmlString.append("\" readonly />");
		}
		htmlString.append("<input type=\"button\" class=\"start-update-row-button\" value=\"Изменить\" />");
		htmlString.append("<input type=\"button\" class=\"delete-row-button\" value=\"Удалить\" />");
		htmlString.append("</form>");
		return htmlString.toString();
	}
}
