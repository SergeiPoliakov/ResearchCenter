/**
 * 
 */

var nameCases = [];
var costCases = [];
var dateCases = [];
var priorityCases = [];

var allRows = document.getElementById('tableBody').getElementsByTagName('tr');

// for add title in help input
var helpInput = document.getElementById('inputUpdateCase');
helpInput.value = 'Быстрый поиск';
helpInput.style.color = 'grey';
function refreshHelpInput() {
	if (helpInput.value.valueOf() == ''.valueOf()) {
		helpInput.value = 'Быстрый поиск';
		helpInput.style.color = 'grey';
	} else if (helpInput.style.color.valueOf() == 'grey'.valueOf()) {
		helpInput.value = '';
		helpInput.style.color = 'black';
	}
}

// for save update case div on page
var cookie = document.cookie;
var matches = document.cookie.match(new RegExp('caseUpdate=1'));
if (matches) {
	var activeCases = document.getElementById('updateCase');
	activeCases.style.visibility = 'visible';
	document.cookie = 'caseUpdate' + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

// for save column values
for (var i = 0; i < allRows.length; i++) {
	nameCases.push(allRows[i].getElementsByTagName('td')[0].innerHTML);
	costCases.push(allRows[i].getElementsByTagName('td')[1].innerHTML);
	dateCases.push(allRows[i].getElementsByTagName('td')[3].innerHTML);
	priorityCases.push(allRows[i].getElementsByTagName('td')[5].innerHTML);
}

function changeCase(element) {

	var row = element.parentElement.parentElement;
	var cells = row.getElementsByTagName('td');
	var hide = cells[6].getElementsByTagName('input')[0];
	ref = cells[6].getElementsByTagName('label')[0];
	if (ref.innerHTML.valueOf() == 'изменить'.valueOf()) {
		// for 1 column
		hide.name = 'caseId';
		var name = cells[0].innerHTML;
		var expect1 = /(?!&nbsp;)+[ ].*/;
		var expect2 = /(?!&nbsp;)+[0-9.]+/;
		var expect3 = /(?!&nbsp;)+[0-9-]+/;
		var expect4 = /(?!&nbsp;)+[А-Яа-я]+/;
		name = name.match(expect1);
		cells[0].innerHTML = '';
		var input = document.createElement('input');
		input.setAttribute("type", "text");
		input.setAttribute("name", "nameCase");
		input.setAttribute("value", name);
		cells[0].appendChild(input);
		// //
		// for 2 column
		name = cells[1].innerHTML;
		name = name.match(expect2);
		cells[1].innerHTML = '';
		input = document.createElement('input');
		input.setAttribute("type", "text");
		input.setAttribute("name", "costCase");
		input.setAttribute("value", name);
		cells[1].appendChild(input);
		// //
		// for 4 column
		name = cells[3].innerHTML;
		name = name.match(expect3);
		cells[3].innerHTML = '';
		input = document.createElement('input');
		input.setAttribute("type", "date");
		input.setAttribute("name", "dateCase");
		// block date
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
		//
		input.setAttribute("min", currentDate);
		input.value = name;
		cells[3].appendChild(input);
		// //
		// for 6 column
		name = cells[5].innerHTML;
		name = name.match(expect4);
		cells[5].innerHTML = '';
		input = document.createElement('select');
		input.name = 'priorityCase';
		var option = document.createElement('option');
		option.value = name;
		option.innerHTML = name;
		input.appendChild(option);
		option = document.createElement('option');
		if (name.valueOf() == 'высокий'.valueOf()) {
			option = document.createElement('option');
			option.value = 'средний';
			option.innerHTML = 'средний';
			input.appendChild(option);
			option = document.createElement('option');
			option.value = 'низкий';
			option.innerHTML = 'низкий';
			input.appendChild(option);
		}
		if (name.valueOf() == 'средний'.valueOf()) {
			option = document.createElement('option');
			option.value = 'высокий';
			option.innerHTML = 'высокий';
			input.appendChild(option);
			option = document.createElement('option');
			option.value = 'низкий';
			option.innerHTML = 'низкий';
			input.appendChild(option);
		}
		if (name.valueOf() == 'низкий'.valueOf()) {
			option = document.createElement('option');
			option.value = 'высокий';
			option.innerHTML = 'высокий';
			input.appendChild(option);
			option = document.createElement('option');
			option.value = 'средний';
			option.innerHTML = 'средний';
			input.appendChild(option);
		}
		cells[5].appendChild(input);
	}
	// for redirect to servlet
	if (ref.innerHTML.valueOf() == 'применить'.valueOf())
		ref.onclick = new function() {
			var form = document.getElementById('updateCaseTable');
			form.setAttribute('action', 'UpdateCase');
			form.setAttribute('method', 'get');
			form.submit();
		};
	ref.innerHTML = 'применить';

	// for return values if cancel change
	for (var i = 0; i < allRows.length; i++) {
		if (allRows[i].getElementsByTagName('td')[6]
				.getElementsByTagName('label')[0].innerHTML.valueOf() == 'применить'
				.valueOf()
				&& allRows[i].getElementsByTagName('td')[6]
						.getElementsByTagName('label')[0] != element) {

			allRows[i].getElementsByTagName('td')[6]
					.getElementsByTagName('input')[0].name = undefined;
			var cell = allRows[i].getElementsByTagName('td')[0];
			var input = cell.getElementsByTagName('input')[0];
			cell.removeChild(input);
			cell.innerHTML = nameCases[i];

			cell = allRows[i].getElementsByTagName('td')[1];
			input = cell.getElementsByTagName('input')[0];
			cell.removeChild(input);
			cell.innerHTML = costCases[i];

			cell = allRows[i].getElementsByTagName('td')[3];
			input = cell.getElementsByTagName('input')[0];
			cell.removeChild(input);
			cell.innerHTML = dateCases[i];

			cell = allRows[i].getElementsByTagName('td')[5];
			input = cell.getElementsByTagName('select')[0];
			cell.removeChild(input);
			cell.innerHTML = priorityCases[i];

			cell = allRows[i].getElementsByTagName('td')[6];
			var label = cell.getElementsByTagName('label')[0];
			label.innerHTML = 'изменить';
			label.onclick = changeCase(this);
		}
	}
}
// //

// for refresh ative cases
function refreshActiveCases() {
	for (var i = 0; i < allRows.length; i++) {
		if (allRows[i].getElementsByTagName('td')[6]
				.getElementsByTagName('label')[0].innerHTML.valueOf() == 'применить'
				.valueOf()) {
			var cell = allRows[i].getElementsByTagName('td')[0];
			var input = cell.getElementsByTagName('input')[0];
			cell.removeChild(input);
			cell.innerHTML = nameCases[i];

			cell = allRows[i].getElementsByTagName('td')[1];
			input = cell.getElementsByTagName('input')[0];
			cell.removeChild(input);
			cell.innerHTML = costCases[i];

			cell = allRows[i].getElementsByTagName('td')[3];
			input = cell.getElementsByTagName('input')[0];
			cell.removeChild(input);
			cell.innerHTML = dateCases[i];

			cell = allRows[i].getElementsByTagName('td')[5];
			input = cell.getElementsByTagName('select')[0];
			cell.removeChild(input);
			cell.innerHTML = priorityCases[i];

			cell = allRows[i].getElementsByTagName('td')[6];
			var label = cell.getElementsByTagName('label')[0];
			label.innerHTML = 'изменить';
		}
	}
	var tableBody = document.getElementById("tableBody");
	for (var i = 0; i < tableBody.rows.length; i++) {
		tableBody.rows[i].style.visibility = '';
		var input = document.getElementById("inputUpdateCase");
		input.value = '';
	}
}

// check backspace for help
function backspaceHelp() {
	var key = event.keyCode;
	if (key == 8) {
		help();
	}
}

// help to find cases by user in database
function help() {
	var input = document.getElementById("inputUpdateCase");
	var text = input.value;
	var tableBody = document.getElementById("tableBody");
	var element = window.event; // get screen page event
	var key = element.which;

	var rows = [];

	for (var i = 0; i < tableBody.rows.length; i++) {
		rows.push(tableBody.rows[i].getElementsByTagName('td'));
	}
	if (text.length >= 3) {
		for (var i = 0; i < rows.length; i++) {
			var name = rows[i][0].innerHTML;
			// check regex
			if (key != 8)
				var regular = new RegExp(".*" + text.toLowerCase()
						+ String.fromCharCode(key) + ".*");
			else
				var regular = new RegExp(".*" + text.toLowerCase() + ".*");

			if (name.match(regular)) {
				tableBody.rows[i].style.visibility = 'visible';
			} else
				tableBody.rows[i].style.visibility = 'hidden';
		}
	}
	if (key == 8) {
		if (text.length - 2 < 3)
			for (var i = 0; i < rows.length; i++) {
				tableBody.rows[i].style.visibility = 'visible';
			}
	}
}