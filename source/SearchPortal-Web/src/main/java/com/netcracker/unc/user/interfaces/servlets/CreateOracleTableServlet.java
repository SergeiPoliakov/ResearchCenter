package com.netcracker.unc.user.interfaces.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netcracker.unc.mvc.connection.ConnectionFactory;

@WebServlet("/interface/CreateOracleTable")
public class CreateOracleTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection connect = ConnectionFactory.getConnection();
		// PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		try {
			System.out.println("�����");
			Statement statement = connect.createStatement();
			String query = new String(request.getParameter("select").getBytes("ISO-8859-1"), "UTF-8");
			ResultSet result = statement.executeQuery(query);
			boolean check = false;
			ResultSetMetaData metaData = null;
			String[] columns = null;
			String createTableHeaders = "";
			ArrayList<String> resultsArray = new ArrayList<String>();

			while (result.next()) {
				if (!check) {
					System.out.println("�����");
					metaData = result.getMetaData();
					columns = new String[metaData.getColumnCount()];
					for (int i = 0; i < columns.length; i++) {
						createTableHeaders += "<td>" + metaData.getColumnLabel(i + 1) + "</td>";
					}
					check = true;
				}
				String createResultLabels = "";
				for (int i = 0; i < columns.length; i++) {
					createResultLabels += "<td>" + result.getString(i + 1) + "</td>";
				}
				resultsArray.add("<tr>" + createResultLabels + "</tr>");
			}

			String allResults = "";
			for (int i = 0; i < resultsArray.size(); i++) {
				allResults += resultsArray.get(i);
			}

			if (!resultsArray.isEmpty()) {
				request.setAttribute("createTableHeaders", "<tr>" + createTableHeaders + "</tr>");
				request.setAttribute("viewResults", allResults);
			} else {
				request.setAttribute("noResults", "� ������� ����������� ������, ���� ������� ������ �����������");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("../interface/oracle_table.jsp");
			dispatcher.include(request, response);

		} catch (SQLException e) {
			request.setAttribute("noResults", "� ������� ����������� ������, ���� ������� ������ �����������");
			RequestDispatcher dispatcher = request.getRequestDispatcher("../interface/oracle_table.jsp");
			dispatcher.include(request, response);
		}
	}
}
