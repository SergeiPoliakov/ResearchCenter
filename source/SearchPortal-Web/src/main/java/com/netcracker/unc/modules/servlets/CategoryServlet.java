package com.netcracker.unc.modules.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		int objectId = 0;
		String action = null;
		double minPercent = 0;
		double maxPercent = 0;
		double coefficient = 0;

		objectId = Integer.parseInt(request.getParameter("objectid"));
		action = request.getParameter("action");
		response.getWriter().append("Served at: ").append(request.getContextPath()).append(action);

		if ((action == "update" || action == "delete") && objectId != 0) {
			try {

				if (action == "update") {
					response.getWriter().append("надо изменять");
				}
				if (action == "delete") {
					response.getWriter().append("надо удалять");
				}
			} catch (Exception e) {
				e.getMessage();
			} finally {
				action = null;
				objectId = 0;
			}
		}
		
		if(action == "create" ){
			
		}

	}

}
