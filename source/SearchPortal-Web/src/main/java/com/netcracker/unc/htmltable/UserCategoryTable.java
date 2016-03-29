package com.netcracker.unc.htmltable;

import java.util.List;

import com.netcracker.unc.newmvc.dao.models.CategoryModel;

public class UserCategoryTable {

	public static String toHtmlTable(List<CategoryModel> categoryList) {
		StringBuilder htmlSB = new StringBuilder("<div class = \"categories-table\"><table>");

		for (CategoryModel cm : categoryList) {
			htmlSB.append(addRow(cm));
		}

		htmlSB.append("</table></div>");
		return htmlSB.toString();
	}

	private static String addRow(CategoryModel cm) {

		StringBuilder htmlString = new StringBuilder("<tr>");

		htmlString.append(addUpdateForm(cm.getObjectId()));
		htmlString.append(addViewForm(cm));

		htmlString.append("</tr>");
		return htmlString.toString();

	}

	private static String addUpdateForm(int objectId) {
		StringBuilder htmlString = new StringBuilder("<td><form id=\"update-category-form\">");

		htmlString.append("<input type=\"hidden\" name=\"objectid\" value=\"");
		htmlString.append(objectId);
		htmlString.append("\"/>");

		htmlString.append("<input type=\"text\" name=\"categoryname\"/>");

		htmlString.append("<select>");
		htmlString.append("<option value=\"0.75\">Высокий</option>");
		htmlString.append("<option value=\"0.5\">Средний</option>");
		htmlString.append("<option value=\"0.25\">Низкий</option>");
		htmlString.append("</select>");

		htmlString.append("<input type=\"button\" id=\"update-row\" value=\"Обновить\" />");

		htmlString.append("</form></td>");
		return htmlString.toString();
	}

	private static String addViewForm(CategoryModel cm) {
		StringBuilder htmlString = new StringBuilder("<td><form id=\"view-category-form\">");

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
			htmlString.append(cm.getMinPercent());
		}

		if (cm.getMaxPercent() != null) {
			htmlString.append(cm.getMaxPercent());
		}

		htmlString.append("<input type=\"button\" id=\"start-update-row\" value=\"Изменить\" />");
		htmlString.append("<input type=\"button\" id=\"delete-row\" value=\"Удалить\" />");

		htmlString.append("</form></td>");
		return htmlString.toString();
	}
}
