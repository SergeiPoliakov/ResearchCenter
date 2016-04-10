package com.netcracker.unc.htmltable;


import com.netcracker.unc.newmvc.ejb.models.CreditModel;

public class CreditHtmlRow {
	public static String addRow(CreditModel model) {
		StringBuilder htmlString = new StringBuilder("<tr><td>");
		addViewForm(model);
		htmlString.append("</td></tr>");
		return htmlString.toString();
	}

	private static String addViewForm(CreditModel cm) {
		StringBuilder htmlString = new StringBuilder("<form class=\"view-category-form\">");

		if (cm.getCreditName() != null && !cm.getCreditName().isEmpty()) {
			htmlString.append(cm.getCreditName());
		}
		if (cm.getCreditValue() != 0) {
				htmlString.append("");	
		}
		/*if (cm.getMinPercent() != null) {
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
		htmlString.append("</form>");*/
		return htmlString.toString();
	}
}
