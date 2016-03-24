<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<jsp:useBean id="invoicesController"
		class="com.netcracker.unc.newmvc.dao.InvoiceDAO" />
<div  id="add-sum">
		<div >Мгновенное попонение счёта</div>
 		 <div ><label>Текущий баланс:</label> ${ invoicesController.getSumBalance(sessionScope.user)}</div> 
		
<form action="IncomingServlet" method="get">
		      <input type="number" name="add-sum-val" value="0" min="0" size="5" step="50"/>
		      <input type="submit" value="добавить" />
		    </form>
		    
</div>	
</div>		
