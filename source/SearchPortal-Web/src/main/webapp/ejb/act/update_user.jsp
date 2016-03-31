<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
</head>
<body>
	<div>
		<form class="for,-inline" action="../int"
			onsubmit="return checkUpdateUserRegExp()">
			<input class="form-control" type="hidden" value="updateUser"
				name="interfaces" />
			<table cellspacing="2" rules="none" height="180px">
				<tbody id="updateUserTBody">
					<tr>
						<td class="upUsLab"><div class="form-group">
								<label class="editUserLabel">Смена имени:</label>
							</div></td>
						<td colspan="1" align="center"><input class="form-control"
							type="text" name="name" id="editUserInputName" /></td>
						<td colspan="2"><label id="errorNameLabel"
							class=errorUpdateUser style="visibility: hidden"></label></td>
					</tr>
					<tr>
						<td class="upUsLab"><div class="form-group">
								<label class="editUserLabel">Старый пароль:</label>
							</div></td>
						<td colspan="1" align="center"><input class="form-control"
							type="password" name="oldPas" id="oldPas" class="upUseInp" /></td>
						<td colspan="2"><label id="errorPasswordLabel"
							class=errorUpdateUser style="visibility: hidden"></label></td>
					</tr>
					<tr>
						<td class="upUsLab"><div class="form-group">
								<label class="editUserLabel">Новый пароль:</label>
							</div></td>
						<td align="center"><input class="form-control"
							type="password" name="newPas" id="newPas" class="upUseInp" /></td>
						<td><label style="margin-left: 1%">подтвердить:</label></td>
						<td><input class="form-control" type="password"
							name="newPasTwice" id="newPasTwice" /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4"><button class="btn" type="submit"
								id="editUserSubmit">Изменить</button></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

</body>

<script src="act/js/update_user.js"></script>

</html>