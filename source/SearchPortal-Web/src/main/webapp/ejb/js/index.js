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