<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<jsp:useBean id="invoicesController"
		class="com.netcracker.unc.newmvc.dao.InvoiceDAO" />
<div  id="add-sum">
		<div >Мгновенное попонение счёта</div>
 		 <div ><label>Текущий баланс:</label> ${ invoicesController.getSumBalance(sessionScope.user)}</div> 
		
<form action="IncomingServlet" method="get">
              <select name="invoiceNumber">
		        <option disabled>Счёт</option>
		          <c:forEach var="invoice"
			        items="${invoicesController.getAllInvoice(sessionScope.user)}">
		            <option value = "${invoice.getInvoiceId()}">${invoice.getInvoiceName()}</option> 
		         </c:forEach>
		      </select>
        	  <!-- <option selected value = "true">да</option>
        	  <option selected value = "false">нет</option></select>  -->
		      <input type="number" name="add-sum-val" value="0" min="0" size="5" step="50"/>
		      <input type="submit" value="добавить" />
		    </form>
		    
</div>	
</div>		
