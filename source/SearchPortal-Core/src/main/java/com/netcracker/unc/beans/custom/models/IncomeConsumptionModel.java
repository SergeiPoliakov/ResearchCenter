package com.netcracker.unc.beans.custom.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;

import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.dao.UserDAO;
import com.netcracker.unc.mvc.models.UserModel;

@Stateless
public class IncomeConsumptionModel {

	int fullIncome, maxIncome, minIncome, avgIncome;
	int fullConsumption, maxConsumption, minConsumption, avgConsumption;
	String maxIncomeName, minIncomeName;
	String maxConsumptionName, minConsumptionName;

	Connection connect = null;
	PreparedStatement prepare = null;
	ResultSet result = null;
	UserModel user = new UserModel();
	UserDAO userDAO = null;

	public int getFullIncome() {
		return fullIncome;
	}

	public void setFullIncome(int fullIncome) {
		this.fullIncome = fullIncome;
	}

	public int getMaxIncome() {
		return maxIncome;
	}

	public void setMaxIncome(int maxIncome) {
		this.maxIncome = maxIncome;
	}

	public int getMinIncome() {
		return minIncome;
	}

	public void setMinIncome(int minIncome) {
		this.minIncome = minIncome;
	}

	public int getAvgIncome() {
		return avgIncome;
	}

	public void setAvgIncome(int avgIncome) {
		this.avgIncome = avgIncome;
	}

	public int getFullConsumption() {
		return fullConsumption;
	}

	public void setFullConsumption(int fullConsumption) {
		this.fullConsumption = fullConsumption;
	}

	public int getMaxConsumption() {
		return maxConsumption;
	}

	public void setMaxConsumption(int maxConsumption) {
		this.maxConsumption = maxConsumption;
	}

	public int getMinConsumption() {
		return minConsumption;
	}

	public void setMinConsumption(int minConsumption) {
		this.minConsumption = minConsumption;
	}

	public int getAvgConsumption() {
		return avgConsumption;
	}

	public void setAvgConsumption(int avgConsumption) {
		this.avgConsumption = avgConsumption;
	}

	public String getMaxIncomeName() {
		return maxIncomeName;
	}

	public void setMaxIncomeName(String maxIncomeName) {
		this.maxIncomeName = maxIncomeName;
	}

	public String getMinIncomeName() {
		return minIncomeName;
	}

	public void setMinIncomeName(String minIncomeName) {
		this.minIncomeName = minIncomeName;
	}

	public String getMaxConsumptionName() {
		return maxConsumptionName;
	}

	public void setMaxConsumptionName(String maxConsumptionName) {
		this.maxConsumptionName = maxConsumptionName;
	}

	public String getMinConsumptionName() {
		return minConsumptionName;
	}

	public void setMinConsumptionName(String minConsumptionName) {
		this.minConsumptionName = minConsumptionName;
	}

	public void procentForBar() {
		userDAO = new UserDAO();
		user.set_login("aspron1x");
		user = (UserModel) userDAO.getObject(user);
		connect = ConnectionFactory.getConnection();

		// column names: 1) maxCos 2) minCost 3) maxName 4) minName 5) avgCost
		// 6) sumCosts
		String query = "select * from(select distinct max(to_number(a1.value)) over(partition by a2.USER_ID) as maxCost, min(to_number(a1.value)) over(partition by a2.USER_ID) as minCost, FIRST_VALUE(a2.OBJECT_NAME) over(partition by a2.user_id order by a1.value) as maxName, LAST_VALUE(a2.OBJECT_NAME) over(partition by a2.user_id order by a1.value) as minName, round(avg(a1.value) over (partition by a2.user_id), 0) as avgCost, sum(a1.value) over(partition by a2.user_id) as sumCosts from sp_params a1 inner join sp_fin_objects a2 on a1.FIN_OBJECT_ID=a2.FIN_OBJECT_ID inner join sp_fin_object_types a3 on a2.FIN_OBJECT_TYPE_ID=a3.FIN_OBJECT_TYPE_ID inner join sp_ATTRIBUTES a4 on a1.ATTRIBUTE_ID=a4.ATTRIBUTE_ID where lower(a4.ATTRIBUTE_NAME) = ? and a2.USER_ID = ?) where rownum = 1";
		// for income
		String where1 = "сумма дохода";
		// for consumption
		String where2 = "стоимость";

		try {
			prepare = connect.prepareStatement(query);
			prepare.setString(1, where1);
			prepare.setInt(2, user.get_user_id());
			result = prepare.executeQuery();
			result.next();
			setMaxIncome(result.getInt("MAXCOST"));
			setMinIncome(result.getInt("MINCOST"));
			setMaxIncomeName(result.getString("MAXNAME"));
			setMinIncomeName(result.getString("MINNAME"));
			setAvgIncome(result.getInt("AVGCOST"));
			setFullIncome(result.getInt("SUMCOSTS"));

			prepare.setString(1, where2);
			result = prepare.executeQuery();
			result.next();
			setMaxConsumption(result.getInt("MAXCOST"));
			setMinConsumption(result.getInt("MINCOST"));
			setMaxConsumptionName(result.getString("MAXNAME"));
			setMinConsumptionName(result.getString("MINNAME"));
			setAvgConsumption(result.getInt("AVGCOST"));
			setFullConsumption(result.getInt("SUMCOSTS"));

			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
