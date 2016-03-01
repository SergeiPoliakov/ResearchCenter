package com.netcracker.unc.beans.custom.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;

import com.netcracker.unc.mvc.connection.ConnectionFactory;
import com.netcracker.unc.mvc.models.UserModel;

@Stateless
public class IncomeConsumptionModel {

    private long fullIncome, maxIncome, minIncome, avgIncome;
    private long fullConsumption, maxConsumption, minConsumption, avgConsumption;
    private String maxIncomeName, minIncomeName;
    private String maxConsumptionName, minConsumptionName;
    private Connection connect = null;
    private PreparedStatement prepare = null;
    private ResultSet result = null;

    // name columns of database
    private String typeIncome = "Доход";
    private String checkRegularIncome = "true";
    private String typeConsumption = "Задача";
    private String attributeIncome = "Сумма дохода";
    private String attributeConsumption = "Стоимость";

    public long getFullIncome() {
        return fullIncome;
    }

    public void setFullIncome(long fullIncome) {
        this.fullIncome = fullIncome;
    }

    public long getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(long maxIncome) {
        this.maxIncome = maxIncome;
    }

    public long getMinIncome() {
        return minIncome;
    }

    public void setMinIncome(long minIncome) {
        this.minIncome = minIncome;
    }

    public long getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(long avgIncome) {
        this.avgIncome = avgIncome;
    }

    public long getFullConsumption() {
        return fullConsumption;
    }

    public void setFullConsumption(long fullConsumption) {
        this.fullConsumption = fullConsumption;
    }

    public long getMaxConsumption() {
        return maxConsumption;
    }

    public void setMaxConsumption(long maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public long getMinConsumption() {
        return minConsumption;
    }

    public void setMinConsumption(long minConsumption) {
        this.minConsumption = minConsumption;
    }

    public long getAvgConsumption() {
        return avgConsumption;
    }

    public void setAvgConsumption(long avgConsumption) {
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

    public void procentForBar(UserModel user) {
        // refresh

        int id = user.getUserId();
        connect = ConnectionFactory.getConnection();
        // System.out.println(user.getUserId());
        // column names: 1) maxCos 2) minCost 3) maxName 4) minName 5) avgCost
        // 6) sumCosts
        String query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) over() "
                + "as mincost, first_value(q2.object_name) over(order by to_number(q1.VALUE) desc) "
                + "as maxname, first_value(q2.object_name) over(order by to_number(q1.VALUE)) as "
                + "minname, sum(to_number(q1.VALUE)) over() as sumcost from SP_PARAMS q1 right join "
                + "(select c1.FIN_OBJECT_ID, c3.FIN_OBJECT_TYPE_NAME, c2.OBJECT_NAME from SP_PARAMS c1 "
                + "inner join SP_FIN_OBJECTS c2 on c1.FIN_OBJECT_ID = c2.FIN_OBJECT_ID inner join "
                + "SP_FIN_OBJECT_TYPES c3 on c2.FIN_OBJECT_TYPE_ID = c3.FIN_OBJECT_TYPE_ID where "
                + "lower(c3.FIN_OBJECT_TYPE_NAME) = ? and c2.USER_ID = ? and c1.VALUE = ?) q2 "
                + "on q1.FIN_OBJECT_ID = q2.fin_object_id inner join SP_ATTRIBUTES q3 on "
                + "q1.ATTRIBUTE_ID = q3.ATTRIBUTE_ID where lower(q3.ATTRIBUTE_NAME) = ?";

        try {
            // for income
            prepare = connect.prepareStatement(query);
            prepare.setString(1, typeIncome.toLowerCase());
            prepare.setInt(2, id);
            prepare.setString(3, checkRegularIncome.toLowerCase());
            prepare.setString(4, attributeIncome.toLowerCase());
            result = prepare.executeQuery();
            while (result.next()) {
                setMaxIncome(result.getLong("MAXCOST"));
                setMinIncome(result.getLong("MINCOST"));
                setMaxIncomeName(result.getString("MAXNAME"));
                setMinIncomeName(result.getString("MINNAME"));
                // setAvgIncome(result.getLong("AVGCOST"));
                setFullIncome(result.getLong("SUMCOST"));
            }
            prepare.close();

            query = "select avg(w1.VALUE) as avgcost from sp_params w1 inner join SP_FIN_OBJECTS w2 "
                    + "on w1.FIN_OBJECT_ID = w2.FIN_OBJECT_ID inner join SP_FIN_OBJECT_TYPES w3 on "
                    + "w2.FIN_OBJECT_TYPE_ID = w3.FIN_OBJECT_TYPE_ID inner join SP_ATTRIBUTES w4 on "
                    + "w1.ATTRIBUTE_ID = w4.ATTRIBUTE_ID where lower(w3.FIN_OBJECT_TYPE_NAME) = ? "
                    + "and lower(w4.ATTRIBUTE_NAME) = ? and w2.USER_ID = ?";

            prepare = connect.prepareStatement(query);
            prepare.setString(1, typeIncome.toLowerCase());
            prepare.setString(2, attributeIncome.toLowerCase());
            prepare.setInt(3, id);
            result = prepare.executeQuery();
            while (result.next()) {
                setAvgIncome(result.getLong("AVGCOST"));
            }
            prepare.close();

            // for consumption
            query = "select max(to_number(q1.VALUE)) over() as maxcost, min(to_number(q1.VALUE)) over() "
                    + "as mincost, first_value(q2.object_name) over(order by to_number(q1.VALUE) desc) "
                    + "as maxname, first_value(q2.object_name) over(order by to_number(q1.VALUE)) "
                    + "as minname, sum(to_number(q1.VALUE)) over() as sumcost, avg(to_number(q1.VALUE)) "
                    + "over() as avgcost from SP_PARAMS q1 right join (select z1.VALUE_DATE, "
                    + "z1.fin_object_id, z2.object_name from sp_params z1 inner join SP_FIN_OBJECTS z2 "
                    + "on z1.fin_object_id = z2.fin_object_id inner join SP_FIN_OBJECT_TYPES z3 on "
                    + "z2.FIN_OBJECT_TYPE_ID = z3.FIN_OBJECT_TYPE_ID where z1.value_date is not null and "
                    + "lower(z3.FIN_OBJECT_TYPE_NAME) = ? and z1.value_date > sysdate and "
                    + "z2.USER_ID = ?) q2 on q1.FIN_OBJECT_ID = q2.fin_object_id inner join "
                    + "SP_ATTRIBUTES q3 on q1.ATTRIBUTE_ID = q3.ATTRIBUTE_ID where lower(q3.ATTRIBUTE_NAME) = ?";

            prepare = connect.prepareStatement(query);
            prepare.setString(1, typeConsumption.toLowerCase());
            prepare.setInt(2, id);
            prepare.setString(3, attributeConsumption.toLowerCase());
            result = prepare.executeQuery();

            while (result.next()) {
                setMaxConsumption(result.getInt("MAXCOST"));
                setMinConsumption(result.getInt("MINCOST"));
                setMaxConsumptionName(result.getString("MAXNAME"));
                setMinConsumptionName(result.getString("MINNAME"));
                setAvgConsumption(result.getInt("AVGCOST"));
                setFullConsumption(result.getInt("SUMCOST"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
