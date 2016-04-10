package com.netcracker.unc.ejb.htmltable;

import com.netcracker.unc.newmvc.ejb.models.CategoryModel;

public class CategoryHtmlRow {

	public static String addRow(CategoryModel categoryModel) {
		StringBuilder htmlString = new StringBuilder("<tr><td>");
		htmlString.append(addViewForm(categoryModel));
		htmlString.append("</td></tr>");
		return htmlString.toString();
	}

	public static String addRow(CategoryModel categoryModel, long userId) {
		StringBuilder htmlString = new StringBuilder("<tr><td>");
		htmlString.append(addViewForm(categoryModel));
		htmlString.append(addUpdateForm(categoryModel, userId));
		htmlString.append("</td></tr>");
		return htmlString.toString();
	}

	private static String addViewForm(CategoryModel cm) {
		StringBuilder htmlString = new StringBuilder("<form class=\"view-category-form\">");

		if (cm.getObjectName() != null && !cm.getObjectName().isEmpty()) {
			htmlString.append(cm.getObjectName());
		}
		if (cm.getCoeficient() != 0) {
			if (cm.getCoeficient() == 0.75)
				htmlString.append("Высокий");
			if (cm.getCoeficient() == 0.5)
				htmlString.append("Средний");
			if (cm.getCoeficient() == 0.25)
				htmlString.append("Низкий");
		}
		if (cm.getMinPercent() != 0) {
			htmlString.append("<input type=\"text\" class=\"min-percent-view\" value=\"");
			htmlString.append(cm.getMinPercent());
			htmlString.append("\" readonly />");
		}
		if (cm.getMaxPercent() != 0) {
			htmlString.append("<input type=\"text\" class=\"max-percent-view\" value=\"");
			htmlString.append(cm.getMaxPercent());
			htmlString.append("\" readonly />");
		}
		// htmlString.append("<input type=\"button\"
		// class=\"start-update-row-button\" value=\"Изменить\" />");
		// htmlString.append("<input type=\"button\" class=\"delete-row-button\"
		// value=\"Удалить\" />");
		htmlString.append("</form>");
		return htmlString.toString();
	}

	private static String addUpdateForm(CategoryModel categoryModel, long userId) {
		StringBuilder htmlString = new StringBuilder(
				"<form class=\"update-category-form\" action=\"CategoryServlet\" method=\"get\" />");
		htmlString.append("<input type = \"hidden\" name=\"action\" value=\"update\" />");
		htmlString.append("<input type=\"hidden\" name=\"objectid\" value=\"");
		htmlString.append(categoryModel.getObjectId());
		htmlString.append("\"/>");
		htmlString.append("<input type = \"hidden\" name=\"userid\" value=\"0\" />");

		htmlString.append("<input type=\"text\" name=\"categoryname\" class = \"category-name\" />");

		htmlString.append("<select name=\"coefficient\" >");
		htmlString.append("<option value=\"0.75\">Высокий</option>");
		htmlString.append("<option value=\"0.5\">Средний</option>");
		htmlString.append("<option value=\"0.25\">Низкий</option>");
		htmlString.append("</select>");

		htmlString.append("<input type=\"text\" name=\"minpercent\" class=\"range-min-percent\" readonly />");
		htmlString.append("<input type=\"text\" name=\"maxpercent\" class=\"range-max-percent\" readonly />");
		htmlString.append(" <div class=\"percent-slider\"></div>");

		htmlString.append("<input type=\"button\" class=\"update-row-button\" value=\"Обновить\" />");
		htmlString.append("<input type=\"button\" class=\"delete-row-button\" value=\"Удалить\" />");
		htmlString.append("</form>");
		return htmlString.toString();
	}
}
