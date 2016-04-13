/**
 * 
 */

// use letters limitation as only numbers input
function validate(input) {
	var input1 = input;
	var element = window.event; // get screen page event
	var key = element.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]/; // regular
	if (!regex.test(key) || input1.value.length > 3) {
		if (element.preventDefault) { // cancel change by event screen
			element.preventDefault();
		} else {
			element.returnValue = false; // //cancel change by event screen
			// for ie8
		}
	}
}

// to change background when user is bankrupt
if (document.getElementById('bankrupt') != null) {
	var footer = document.querySelector('.container-footer');
	var header1 = document.querySelector('.navbar-collapse');
	var header2 = document.querySelector('.navbar-header');
	var header3 = document.querySelector('#Insert_logo');
	var headerm = document.querySelectorAll('.block-title');
	var logo = document.getElementById('Insert_logo');
	header1.style.backgroundColor = 'red';
	header2.style.backgroundColor = 'red';
	header3.style.backgroundColor = 'red';

	for (var i = 0; i < headerm.length; i++) {
		headerm[i].style.backgroundColor = 'red';
		headerm[i].style.color = '#000';
	}

	footer.style.backgroundColor = 'red';
	logo.setAttribute("src", "../img/logo-red.png");
}

// action for red big button
function resetBalance() {
	var resetDiv = document.getElementById('resetBalanceDiv');

	if (resetDiv.style.display.valueOf() == 'none'.valueOf()) {
		resetDiv.style.display = '';
	} else
		resetDiv.style.display = 'none';
}

// add regular check for input button of salary add
function regularAddSalary() {
	var welcomeCaseInput = document.getElementById("welcomeCaseInput");
	var matcherCostCase = new RegExp("^[0-9]{1,12}$");
	if (!welcomeCaseInput.value.match(matcherCostCase))
		return false;
}

// add animation for check-salary module
if (document.getElementById("helloCase") != null) {
	var helloCase = document.getElementById("helloCase");
	var addSalaryPlace = -250;
	helloCase.style.right = addSalaryPlace + 'px';
	var animationAddCase = setInterval(function() {
		if (addSalaryPlace != 0) {
			addSalaryPlace += 2;
			helloCase.style.right = addSalaryPlace + 'px';
		} else {
			clearInterval(animationAddCase);
		}

	}, 5);
}

// animation for control salary
if (document.getElementById("controlSalaryMain") != null) {
	var controlSal = document.getElementById("controlSalaryMain");
	var addSalaryControlPlace = -25;
	controlSal.style.right = addSalaryControlPlace + '%';
	var animationControlSalary = setInterval(function() {
		if (addSalaryControlPlace < 0) {
			addSalaryControlPlace += 1;
			controlSal.style.right = addSalaryControlPlace + '%';
		} else {
			clearInterval(animationControlSalary);
		}
	}, 20);
}

// get left buttons icons
var span1 = document.getElementById("butspan1");
var span2 = document.getElementById("butspan2");
var span3 = document.getElementById("butspan3");
var span4 = document.getElementById("butspan4");
var span5 = document.getElementById("butspan5");

// show-hide general modules
var leftMenuButtons = document.getElementsByClassName('leftMenuButton');
function showHideModule(element) {
	var personalAreaUserDiv = document.getElementById('personalAreaUserDiv');
	var button = element;
	var generalModules = document.getElementsByClassName('generalModule');
	// document.getElementById('leftMenuDiv').style.display = 'none';
	switch (button.id.valueOf()) {
	case 'consumption'.valueOf():
		if (document.getElementById('createCase').style.display.valueOf() == 'none'
				.valueOf()
				&& document.getElementById('editCase').style.display.valueOf() == 'none'
						.valueOf()
				&& document.getElementById('consumptionA').style.display
						.valueOf() == 'none'.valueOf()
				&& document.getElementById('credit-module').style.display
						.valueOf() == 'none'.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			document.getElementById('createCase').style.display = '';
			showLeftMenuDiv();

			span1.setAttribute("class", "glyphicon glyphicon-plus-sign");
			span2.setAttribute("class", "glyphicon glyphicon-pencil");
			span3.setAttribute("class", "glyphicon glyphicon-briefcase");
			span4.setAttribute("class", "glyphicon glyphicon-hdd");

			leftMenuButtons[0].setAttribute("title", "Создание задачи");
			leftMenuButtons[1].setAttribute("title", "Активные задачи");
			leftMenuButtons[2].setAttribute("title", "Управление расходами");
			leftMenuButtons[3].setAttribute("title", "Кредиты");
			leftMenuButtons[0].setAttribute("onclick",
					"showConsumptionDivs(this)");
			leftMenuButtons[1].setAttribute("onclick",
					"showConsumptionDivs(this)");
			leftMenuButtons[2].setAttribute("onclick",
					"showConsumptionDivs(this)");
			leftMenuButtons[3].setAttribute("onclick",
					"showConsumptionDivs(this)");

			leftMenuButtons[0].style.display = '';
			leftMenuButtons[1].style.display = '';
			leftMenuButtons[2].style.display = '';
			leftMenuButtons[3].style.display = '';

		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	case 'personalArea'.valueOf():
		if (document.getElementById('editUser').style.display.valueOf() == 'none'
				.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			span1.setAttribute("class", "glyphicon glyphicon-credit-card");

			showLeftMenuDiv();
			leftMenuButtons[0].setAttribute("title", "Редактирование профиля");
			document.getElementById('editUser').style.display = '';
			leftMenuButtons[0].setAttribute("onclick",
					'showPersonalAreaDivs(this)');
			leftMenuButtons[0].style.display = '';
		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	case 'statistic'.valueOf():
		if (document.getElementById('priority-module').style.display.valueOf() == 'none'
				.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			showLeftMenuDiv();
			document.getElementById('priority-module').style.display = '';

			span1.setAttribute("class", "glyphicon glyphicon-upload");
			span2.setAttribute("class", "glyphicon glyphicon-signal");

			leftMenuButtons[0].setAttribute("title", "Приоритеты");
			leftMenuButtons[1].setAttribute("title", "Статистика бюджета");
			leftMenuButtons[0].setAttribute("onclick",
					'showStatisticDivs(this)');
			leftMenuButtons[1].setAttribute("onclick",
					'showStatisticDivs(this)');
			leftMenuButtons[0].style.display = '';
			leftMenuButtons[1].style.display = '';
		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	case 'resource'.valueOf():
		if (document.getElementById('category-module').style.display.valueOf() == 'none'
				.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			showLeftMenuDiv();
			document.getElementById('category-module').style.display = '';

			span1.setAttribute("class", "glyphicon glyphicon-send");
			span2.setAttribute("class", "glyphicon glyphicon-transfer");

			leftMenuButtons[0].setAttribute("title", "Категории");
			leftMenuButtons[1].setAttribute("title", "Управление счетами");
			leftMenuButtons[0]
					.setAttribute("onclick", 'showResourceDivs(this)');
			leftMenuButtons[1]
					.setAttribute("onclick", 'showResourceDivs(this)');
			leftMenuButtons[0].style.display = '';
			leftMenuButtons[1].style.display = '';
		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	case 'incomePlus'.valueOf():
		if (document.getElementById('incoming').style.display.valueOf() == 'none'
				.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			showLeftMenuDiv();

			span1.setAttribute("class", "glyphicon glyphicon-plus");

			document.getElementById('incoming').style.display = '';

			leftMenuButtons[0].setAttribute("title",
					"Пополнение денежных средств");
			leftMenuButtons[0].setAttribute("onclick",
					'showIncomePlusDivs(this)');
			leftMenuButtons[0].style.display = '';
		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	case 'incomeMinus'.valueOf():
		if (document.getElementById('incomingMinus').style.display.valueOf() == 'none'
				.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			showLeftMenuDiv();

			span1.setAttribute("class", "glyphicon glyphicon-minus");

			document.getElementById('incomingMinus').style.display = '';
			leftMenuButtons[0].setAttribute("onclick",
					'showIncomeMinusDivs(this)');
			leftMenuButtons[0].setAttribute("title",
					"Списание денежных средств");
			leftMenuButtons[0].style.display = '';
		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	case 'incomes'.valueOf():
		if (document.getElementById('incomeA').style.display.valueOf() == 'none'
				.valueOf()) {
			stopLeftMenuAnimation();
			document.getElementById('leftMenuDiv').style.marginLeft = '-10px';
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
			showLeftMenuDiv();

			span1
					.setAttribute("class",
							"glyphicon glyphicon-registration-mark");

			document.getElementById('incomeA').style.display = '';
			leftMenuButtons[0].setAttribute("onclick", 'showIncomesDivs(this)');
			leftMenuButtons[0].setAttribute("title", "Управление доходами");
			leftMenuButtons[0].style.display = '';
		} else {
			for (var i = 0; i < generalModules.length; i++) {
				generalModules[i].style.display = 'none';
			}
			for (var i = 0; i < leftMenuButtons.length; i++) {
				leftMenuButtons[i].style.display = 'none';
			}
		}
		break;
	}
}

// show divs for incomes button
function showIncomesDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('incomeA').style.display = '';
	}
}

// show divs for plus button
function showIncomePlusDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('incoming').style.display = '';
	}
}

// show divs for minus button
function showIncomeMinusDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('incomingMinus').style.display = '';
	}
}

// show divs for statistic button
function showStatisticDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('priority-module').style.display = '';
		document.getElementById('statPie').style.display = 'none';
		break;
	case 'butid2'.valueOf():
		document.getElementById('priority-module').style.display = 'none';
		document.getElementById('statPie').style.display = '';
		break;
	}
}

// show divs for statistic button
function showResourceDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('category-module').style.display = '';
		document.getElementById('invoices').style.display = 'none';
		break;
	case 'butid2'.valueOf():
		document.getElementById('category-module').style.display = 'none';
		document.getElementById('invoices').style.display = '';
	}
}

// show divs for consumption button
function showConsumptionDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('createCase').style.display = '';
		document.getElementById('editCase').style.display = 'none';
		document.getElementById('consumptionA').style.display = 'none';
		document.getElementById('credit-module').style.display = 'none';
		break;
	case 'butid2'.valueOf():
		document.getElementById('createCase').style.display = 'none';
		document.getElementById('editCase').style.display = '';
		document.getElementById('consumptionA').style.display = 'none';
		document.getElementById('credit-module').style.display = 'none';
		break;
	case 'butid3'.valueOf():
		document.getElementById('createCase').style.display = 'none';
		document.getElementById('editCase').style.display = 'none';
		document.getElementById('consumptionA').style.display = '';
		document.getElementById('credit-module').style.display = 'none';
		break;
	case 'butid4'.valueOf():
		document.getElementById('createCase').style.display = 'none';
		document.getElementById('editCase').style.display = 'none';
		document.getElementById('consumptionA').style.display = 'none';
		document.getElementById('credit-module').style.display = '';
		break;
	}
}

// show divs for personal area button
function showPersonalAreaDivs(element) {
	switch (element.id.valueOf()) {
	case 'butid1'.valueOf():
		document.getElementById('editUser').style.display = '';
		break;
	}
}
// animation for left menu buttons
var animationLeftMenu;
function showLeftMenuDiv() {
	var leftMenuDiv = document.getElementById('leftMenuDiv');
	leftMenuDiv.style.display = '';
	var posLeftMenu = -10;
	animationLeftMenu = setInterval(function() {
		if (posLeftMenu < 10) {
			posLeftMenu += 1;
			leftMenuDiv.style.marginLeft = posLeftMenu + 'px';
		} else {
			clearInterval(animationLeftMenu);
		}
	}, 50);
}
function stopLeftMenuAnimation() {
	clearInterval(animationLeftMenu);
}

// left menu button choose
function leftMenuChoose(element) {
	switch (element.innerHTML.valueOf()) {
	case '1'.valueOf():
		document.getElementById('createCase').style.display = '';
		document.getElementById('editCase').style.display = 'none';
		break;
	case '2'.valueOf():
		document.getElementById('editCase').style.display = '';
		document.getElementById('createCase').style.display = 'none';
		break;
	}
}