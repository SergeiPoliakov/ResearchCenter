package com.netcracker.unc.priorityModule;

import java.util.List;

import com.netcracker.unc.newmvc.dao.CategoryModel;

public class FillHTMLTable {

	public static String toHtmlString(List<CategoryModel> categoryList) {
		StringBuilder htmlString = new StringBuilder("<div class = \"priority-table\"><table>");
		if (categoryList != null && !categoryList.isEmpty()) {
			for (CategoryModel cm : categoryList) {
				htmlString.append(addCategoryString(cm));
			}
		}
		htmlString.append("</table></div>");
		return htmlString.toString();
	}

	private static String addCategoryString(CategoryModel categoryModel) {
		StringBuilder categoryString = new StringBuilder("<tr><td>");

		categoryString.append(categoryModel.getObjectName());
		categoryString.append("</td><td>");
		categoryString.append(categoryModel.getPercent());
		categoryString.append("</td></tr>");
		return categoryString.toString();
	}
}
