/**
 * 
 */

function changeToReg() {
	var authDiv = document.getElementById('authorizationUser');
	var registrDiv = document.getElementById('registrationUser');
	var restoreDiv = document.getElementById('restoreUser');

	if (registrDiv.style.display.valueOf() == 'none'.valueOf()) {
		authDiv.style.display = 'none';
		registrDiv.style.display = '';
		restoreDiv.style.display = 'none';
	}
}

function changeToAuth() {
	var authDiv = document.getElementById('authorizationUser');
	var registrDiv = document.getElementById('registrationUser');
	var restoreDiv = document.getElementById('restoreUser');

	if (authDiv.style.display.valueOf() == 'none'.valueOf()) {
		authDiv.style.display = '';
		registrDiv.style.display = 'none';
		restoreDiv.style.display = 'none';
	}
}

// check registration for regular
function checkRegistrRegular() {
	var loginReg = /^[A-Za-z0-9]{1,15}$/;
	var passwordReg = /^[^ А-Яа-я]{4,10}$/;
	var emailReg = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
	var nameReg = new RegExp("^[^ ][A-Za-zА-Яа-я' ]{1,15}[^ ]$");
	var login = document.getElementById("login");
	var password = document.getElementById("password");
	var name = document.getElementById("name");
	var email = document.getElementById("email");
	var errorLogLabel = document.getElementById('errorRegLogLabel');
	var errorPasLabel = document.getElementById('errorRegPasLabel');
	var errorNameLabel = document.getElementById('errorRegNameLabel');
	var errorRegEmailLabel = document.getElementById("errorRegEmailLabel");
	var check = true;
	errorLogLabel.innerHTML = "";
	errorLogLabel.style.display = 'none';
	errorPasLabel.innerHTML = "";
	errorPasLabel.style.display = 'none';
	errorNameLabel.innerHTML = "";
	errorNameLabel.style.display = 'none';
	errorRegEmailLabel.innerHTML = "";
	errorRegEmailLabel.style.display = 'none';
	if (!login.value.match(loginReg)) {
		errorLogLabel.innerHTML = 'Логин должен состоять только из латинских букв и цифр (не более 15 символов без пробелов)';
		errorLogLabel.style.display = 'block';
		check = false;
	}
	if (!password.value.match(passwordReg)) {
		errorPasLabel.innerHTML = 'Пароль должен состоять только из латинских букв, цифр и символов (от 4 до 10)';
		errorPasLabel.style.display = 'block';
		check = false;
	}
	if (name.value.valueOf() != "".valueOf()) {
		if (!name.value.match(nameReg)) {
			errorNameLabel.innerHTML = 'Имя должно состоять только из букв (не более 15 символов)';
			errorNameLabel.style.display = 'block';
			check = false
		}
	}
	if (!email.value.match(emailReg)) {
		errorRegEmailLabel.innerHTML = 'Введите корректную почту';
		errorRegEmailLabel.style.display = 'block';
		check = false;
	}
	return check;
}