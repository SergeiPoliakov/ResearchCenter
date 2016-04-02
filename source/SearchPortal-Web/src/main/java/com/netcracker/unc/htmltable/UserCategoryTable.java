package com.netcracker.unc.htmltable;

import java.util.List;

import com.netcracker.unc.newmvc.dao.models.CategoryModel;

public class UserCategoryTable {

	public static String toHtmlTable(List<CategoryModel> categoryList) {
		StringBuilder htmlSB = new StringBuilder("<div id = \"categories-table\"><table>");

		for (CategoryModel cm : categoryList) {
			htmlSB.append(addRow(cm));
		}

		htmlSB.append("</table></div>");
		return htmlSB.toString();
	}

	private static String addRow(CategoryModel cm) {

		StringBuilder htmlString = new StringBuilder("<tr><td>");

		htmlString.append(addUpdateForm(cm.getObjectId()));
		htmlString.append(addViewForm(cm));

		htmlString.append("</td></tr>");
		return htmlString.toString();

	}

	private static String addUpdateForm(int objectId) {
		StringBuilder htmlString = new StringBuilder(
				"<form class=\"update-category-form\" action=\"CategoryServlet\" method=\"get\" />");
		htmlString.append("<input type = \"hidden\" name=\"action\" value=\"update\" />");
		htmlString.append("<input type=\"hidden\" name=\"objectid\" value=\"");
		htmlString.append(objectId);
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

		htmlString.append("</form>");
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
