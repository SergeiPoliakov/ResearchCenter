/**
 * @author Kolesnikov
 */

// add regular check for input button of salary add
function regularAddSalary() {
	var welcomeCaseInput = document.getElementById("welcomeCaseInput");
	var matcherCostCase = new RegExp("^[0-9]{1,12}$");
	if (!welcomeCaseInput.value.match(matcherCostCase))
		return false;
}

// show-hide parent select on yes no toogles
var parent = document.getElementById("parentBlock");
var values = [];

for (var i = 0; i < parent.length; i++) {
	values[i] = parent.options[i].value;
	parent.options[i].value = 0;
}

function changeCheck(element) {
	var element = element.value;
	var typeYes = document.getElementById("yes");
	var typeNo = document.getElementById("no");

	if (element.valueOf() == 'yes'.valueOf()) {
		for (var i = 0; i < parent.length; i++) {
			parent.options[i].value = values[i];
		}
		typeNo.checked = undefined;
		typeYes.checked = "checked";
		parent.style.visibility = "visible";
	} else {
		for (var i = 0; i < parent.length; i++) {
			parent.options[i].value = 0;
		}
		typeYes.checked = undefined;
		typeNo.checked = "checked";
		parent.style.visibility = "hidden";
	}
}

// check created user cases on empty
var el = document.getElementById("parentBlock");
var options = el.options;
if (options.length == 0) {
	var option = document.createElement("option");
	option.value = '0';
	option.text = 'пусто';
	el.appendChild(option);
}

// block incorrect input date
var today = new Date();
var dd = today.getDate() + 1;
var mm = today.getMonth() + 1;
var yyyy = today.getFullYear();
if (dd < 10) {
	dd = "0" + dd;
}
if (mm < 10) {
	mm = "0" + mm;
}
var currentDate = yyyy + "-" + mm + "-" + dd;
document.getElementById("date_case").setAttribute("min", currentDate);

// use letters limitation as only numbers input
function validate(input) {
	var input1 = input;
	var element = window.event; // get screen page event
	var key = element.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]/; // regular
	if (!regex.test(key) || input1.value.length > 9) {
		if (element.preventDefault) { // cancel change by event screen
			element.preventDefault();
		} else {
			element.returnValue = false; // //cancel change by event screen
			// for ie8
		}
	}
}

// for check input case for regular
function checkRegExp() {

	var div = document.getElementById("createCaseDiv");
	var check = true;

	if (document.getElementById('nameError') != null)
		div.removeChild(document.getElementById('nameError'));

	if (document.getElementById('dateError') != null)
		div.removeChild(document.getElementById('dateError'));

	if (document.getElementById('costError') != null)
		div.removeChild(document.getElementById('costError'));

	var costCase = document.getElementById("cost_case");
	var dateCase = document.getElementById("date_case");
	var form = document.getElementById("createCaseForm");
	var nameCase = document.getElementById("nameCase");
	var element = element;
	var matcherNameCase = new RegExp("^[A-Za-zА-Яа-я1-9 ]{1,100}$");
	var matcherDate = new RegExp("^[0-9]*.");
	var matcherCostCase = new RegExp("^[0-9]{1,12}$");
	if (!nameCase.value.match(matcherNameCase)) {
		var error = document.createElement('label');
		error.style.display = 'block';
		error.style.marginTop = '2px';
		error.id = 'nameError';
		error.innerHTML = 'имя задачи должно состоять только из букв и цифр (не более 100 символов)';
		error.style.fontSize = '10pt';
		error.style.color = 'red';
		check = false;
		div.appendChild(error);
	}
	if (!dateCase.value.match(matcherDate)) {
		var error = document.createElement('label');
		error.style.display = 'block';
		error.style.marginTop = '2px';
		error.id = 'dateError';
		error.innerHTML = 'не выбрана дата окончания задачи';
		error.style.fontSize = '10pt';
		error.style.color = 'red';
		check = false;
		div.appendChild(error);
	}
	if (!costCase.value.match(matcherCostCase)) {
		var error = document.createElement('label');
		error.style.display = 'block';
		error.style.marginTop = '2px';
		error.id = 'costError';
		error.innerHTML = 'сумма должна состоять только из цифр (не более 12)';
		error.style.fontSize = '10pt';
		error.style.color = 'red';
		check = false;
		div.appendChild(error);
	}
	if (!check) {
		return false;
	}
}