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
		StringBuilder htmlString = new StringBuilder("<td>");

		if (cm.getObjectName() != null && !cm.getObjectName().isEmpty()) {
			htmlString.append("<td>");
			htmlString.append(cm.getObjectName());
			htmlString.append("</td>");
		} else {
			htmlString.append("<td>Ошибка</td>");
		}

		if (cm.getMinPercent() != null) {
			htmlString.append("<td>");
			htmlString.append(cm.getMinPercent());
			htmlString.append("</td>");
		} else {
			htmlString.append("<td>Ошибка</td>");
		}

		if (cm.getMaxPercent() != null) {
			htmlString.append("<td>");
			htmlString.append(cm.getMaxPercent());
			htmlString.append("</td>");
		} else {
			htmlString.append("<td>Ошибка</td>");
		}
		
		//if(cm.getCoefficient())

		if (cm.getObjectId() != null) {

			/*htmlString.append("<td>");
			htmlString.append("<form action=\"CategoryServlet\" method=\"get\">");
			htmlString.append("<input type=\"hidden\" name=\"action\" value=\"update\" />");
			htmlString.append("<input type=\"hidden\" name=\"objectid\" value=\"");
			htmlString.append(cm.getObjectId());
			htmlString.append("\"/>");
			htmlString.append("<input type=\"submit\" id=\"update-submit\" value=\"Изменить\" />");
			htmlString.append("</form>");
			htmlString.append("</td>");*/
			htmlString.append("<td>");
			htmlString.append("<input type=\"hidden\" name=\"objectid\" value=\"");
			htmlString.append(cm.getObjectId());
			htmlString.append("\"/>");
			htmlString.append("<input type=\"button\" id =\"start-update-row1\" value =\"Изменить\" >");
			htmlString.append("</td>");
			
			htmlString.append("<td>");
			htmlString.append("<form action=\"CategoryServlet\" method=\"get\">");
			htmlString.append("<input type=\"hidden\" name=\"action\" value=\"delete\" />");
			htmlString.append("<input type=\"hidden\" name=\"objectid\" value=\"");
			htmlString.append(cm.getObjectId());
			htmlString.append("\"/>");
			htmlString.append("<input type=\"submit\" value=\"Удалить\" />");
			htmlString.append("</form>");
			htmlString.append("</td>");
		}
		htmlString.append("</tr>");
		return htmlString.toString();
	}

}
