function checkLogEmpty() {
		var login = document.getElementById("logLogin");
		var pas = document.getElementById("logPas");
		var errorLabel = document.getElementById('logErrorLabel');
		errorLabel.innerHTML = "";

		if (login.value.valueOf() == "".valueOf()
				|| pas.value.valueOf() == "".valueOf()) {
			errorLabel.innerHTML = 'Поля логин и пароль не могут быть пустыми';
			return false;
		}
		return true;
	}

	function restorePassword() {
		var errorLabel = document.getElementById("errorRestorePasLasbel");
		var restorePasswordLabel = document
				.getElementById('restorePasswordLabel');
		restorePasswordLabel.style.display = 'none';

		var loginTable = document.getElementById('loginTable');
		var restoreTable = document.getElementById('restoreTable');
		if (restoreTable.style.display.valueOf() == 'none'.valueOf()) {
			loginTable.style.display = 'none';
			restoreTable.style.display = '';
		} else {
			loginTable.style.display = '';
			restoreTable.style.display = 'none';
			errorLabel.style.display = 'none';
		}

	}

	function checkEmptyRestoreInput() {
		var login = document.getElementById("restorePasLogin");
		var email = document.getElementById("restorePasEmail");
		var errorLabel = document.getElementById("errorRestorePasLasbel");
		if (login.value.valueOf() == "".valueOf()
				|| email.value.valueOf() == "".valueOf()) {
			errorLabel.style.display = 'block';
			return false;
		} else
			return true;
	}
