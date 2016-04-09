package com.netcracker.unc.htmltable;

import java.util.List;

import com.netcracker.unc.newmvc.dao.models.CategoryModel;
import com.netcracker.unc.newmvc.ejb.models.*;

public class HtmlConverter {
	public static String toHtmlTable(List<?> listModels) {
		if (listModels != null && !listModels.isEmpty()) {
			StringBuilder htmlSB = new StringBuilder("<div id = \"html-model-table\"><table>");

			String modelType = checkModelType(listModels.get(0));

			if (modelType == "CategoryModel")
				for (Object object : listModels) {
					CategoryModel categoryModel = (CategoryModel) object;
					CategoryHtmlRow.addRow(categoryModel);
				}
			if (modelType == "StatisticModel")
				for (Object object : listModels) {
					StatisticModel statisticModel = (StatisticModel) object;
					// addRow(statisticModel);
				}
			// добавить кнопку изменить
			htmlSB.append("</table></div>");
			return htmlSB.toString();
		} else {
			return "<h2>table is empty</h2>";
		}
	}

	private static String checkModelType(Object object) {
		String modelType = "Такого типа нет";
		if (object instanceof CategoryModel)
			modelType = "CategoryModel";
		if (object instanceof ActiveCasesModel)
			modelType = "ActiveCasesModel";
		if (object instanceof SalaryModel)
			modelType = "SalaryModel";
		if (object instanceof StatisticModel)
			modelType = "StatisticModel";
		return modelType;
	}
}