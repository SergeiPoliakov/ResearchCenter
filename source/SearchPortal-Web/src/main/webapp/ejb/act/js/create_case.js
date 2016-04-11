/**
 * @author Kolesnikov
 */

// show-hide parent select on yes no toogles
var values = [];
var parent = document.getElementById("parentBlock");
for (var i = 0; i < parent.length; i++) {
	values.push(new Number(parent.options[i].value));
}
for (var i = 0; i < parent.length; i++) {
	parent.options[i].value = 0;
}

function changeCheck(element) {

	for (var i = 0; i < parent.length; i++) {
		parent.options[i].value = 0;
	}
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

// for check input case for regular
function checkRegExp() {

	var div = document.getElementById("createCaseDiv");
	var check = true;

	document.getElementById('nameError').innerHTML = '';
	document.getElementById('dateError').innerHTML = '';
	document.getElementById('costError').innerHTML = '';

	var costCase = document.getElementById("cost_case");
	var dateCase = document.getElementById("date_case");
	var form = document.getElementById("createCaseForm");
	var nameCase = document.getElementById("nameCase");
	var element = element;
	var matcherNameCase = new RegExp("^[A-Za-zА-Яа-я1-9 ]{1,100}$");
	var matcherDate = new RegExp("^[0-9]*.");
	var matcherCostCase = new RegExp("^[0-9]{1,12}$");
	if (!nameCase.value.match(matcherNameCase)) {
		var error = document.getElementById('nameError');
		error.setAttribute('class', 'createCaseError')
		error.innerHTML = 'имя задачи должно состоять только из букв и цифр (не более 100 символов)';
		check = false;
	}
	if (!dateCase.value.match(matcherDate)) {
		var error = document.getElementById('dateError');
		error.setAttribute('class', 'createCaseError')
		error.innerHTML = 'не выбрана дата окончания задачи';
		check = false;
	}
	if (!costCase.value.match(matcherCostCase)) {
		var error = document.getElementById('costError');
		error.setAttribute('class', 'createCaseError')
		error.innerHTML = 'сумма должна состоять только из цифр (не более 12)';
		check = false;
	}
	if (!check) {
		return false;
	}
}