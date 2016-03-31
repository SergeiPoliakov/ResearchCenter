/**
 * @author Kolesnikov
 */

// regular for edit user module
function checkUpdateUserRegExp() {
	var check = true;
	var nameUser = document.getElementById("editUserInputName");
	var oldPas = document.getElementById("oldPas");
	var regExpName = new RegExp("^[^ ][A-Za-zА-Яа-я' ]{1,15}[^ ]$");
	var regExpPas = new RegExp("^[^ А-Яа-я]{4,10}$");
	var tbNameError = document.getElementById("updateUserTBody");
	var errorNameLabel = document.getElementById("errorNameLabel");
	var errorPasswordLabel = document.getElementById("errorPasswordLabel");
	var newPas = document.getElementById("newPas");
	var newPasTwice = document.getElementById("newPasTwice");

	if (!nameUser.value.match(regExpName)) {
		errorNameLabel.style.visibility = 'visible';
		errorNameLabel.innerHTML = 'имя должно состоять только из букв (не более 15 символов)';
		check = false;
	} else {
		errorNameLabel.style.visibility = 'hidden';
	}
	if (!oldPas.value.match(regExpPas) || !newPas.value.match(regExpPas)
			|| !newPasTwice.value.match(regExpPas)) {
		errorPasswordLabel.style.visibility = 'visible';
		errorPasswordLabel.innerHTML = 'пароль должен состоять из латинских букв, цифр и символов (от 4 до 10)';
		check = false;
	} else {
		errorPasswordLabel.style.visibility = 'hidden';
		errorPasswordLabel.innerHTML = "";
		if (newPas.value.valueOf() != newPasTwice.value.valueOf()) {
			errorPasswordLabel.innerHTML = 'новый и подтвержденный пароли не совпадают';
			errorPasswordLabel.style.visibility = 'visible';
			check = false;
		}
	}
	return check;
}

// to save show edit user module
var matches = document.cookie.match(new RegExp('errorUpdateUser=1'));
var errorPasswordLabel = document.getElementById("errorPasswordLabel");
errorPasswordLabel.innerHTML = "";
errorPasswordLabel.style.visibility = 'hidden';
if (matches) {
	errorPasswordLabel.innerHTML = 'старый пароль введен неверно';
	errorPasswordLabel.style.visibility = 'visible';
	// for delete cookie
	document.cookie = 'errorUpdateUser'
			+ '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}