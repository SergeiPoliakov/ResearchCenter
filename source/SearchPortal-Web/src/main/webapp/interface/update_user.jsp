<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="interface/css/update_user.css" />
</head>
<body>
	<div class="updateUser">
		<form action="interface" onsubmit="return checkUpdateUserRegExp()">
			<input type="hidden" value="updateUser" name="interfaces" />
			<table cellspacing="2" rules="groups" border="2" height="180px">
				<thead>
					<tr>
						<th colspan="4">Редактировать свой профиль:</th>
					</tr>
				</thead>
				<tbody id="updateUserTBody">
					<tr>
						<td class="upUsLab"><label class="editUserLabel">Смена
								имени:</label></td>
						<td colspan="1" align="center"><input type="text" name="name"
							id="editUserInputName" /></td>
						<td colspan="2"><label id="errorNameLabel">имя должно
								состоять только из букв (не более 15 символов)</label></td>
					</tr>
					<tr>
						<td class="upUsLab"><label class="editUserLabel">Старый
								пароль:</label></td>
						<td colspan="1" align="center"><input type="password"
							name="oldPas" id="oldPas" class="upUseInp"/></td>
						<td colspan="2"><label id="errorPasswordLabel"></label></td>
					</tr>
					<tr>
						<td class="upUsLab"><label class="editUserLabel">Новый
								пароль:</label></td>
						<td align="center"><input type="password" name="newPas"
							id="newPas" class="upUseInp"/></td>
						<td><label style="margin-left: 1%">подтвердить:</label></td>
						<td align="left"><input type="password" name="newPasTwice"
							id="newPasTwice" class="upUseInp"/></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4"><input type="submit" value="Изменить"
							id="editUserSubmit" /></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

</body>

<script src="interface/js/updateUser.js"></script>

</html>