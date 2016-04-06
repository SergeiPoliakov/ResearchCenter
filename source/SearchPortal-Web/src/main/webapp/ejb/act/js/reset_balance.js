/**
 * 
 */

// for change reset balance select
var select = document.getElementById('balSelect');
document.getElementById('balHidden').setAttribute("value", select.value);
if (document.getElementsByClassName('balLabel') != null) {
	var labels = document.getElementsByClassName('balLabel');
	if (document.getElementById('balHidden').value.valueOf() != 'error'
			.valueOf()) {
		labels[0].style.display = '';
	}
}

function checkBalReset() {
	if (document.getElementById('balHidden').value.valueOf() != 'error'
			.valueOf())
		return true;
	else
		return false;
}

function changeBalCost() {
	var labels = document.getElementsByClassName('balLabel');
	document.getElementById('balHidden').setAttribute("value", select.value);
	for (var i = 0; i < labels.length; i++) {
		if (labels[i].id.valueOf() == ("bal".concat(select.value)).valueOf()) {
			labels[i].style.display = '';
			document.getElementById('balHidden').setAttribute("value",
					select.value);
		} else
			labels[i].style.display = 'none';
	}
}

// check input balance empty
var helpBal = document.getElementById('balTextCost');
helpBal.value = 'введите число';
helpBal.style.fontSize = '10px';
helpBal.style.color = 'grey';
function checkBalanceEmptyHelp(element) {
	if (helpBal.value.valueOf() == ''.valueOf()) {
		helpBal.value = 'введите число';
		helpBal.style.color = 'grey';
	} else if (helpBal.style.color.valueOf() == 'grey'.valueOf()) {
		helpBal.value = '';
		helpBal.style.color = 'black';
	}
}

function checkBalanceEmpty() {
	var select = document.getElementById('balSelect');
	var helpBal = document.getElementById('balTextCost');
	if (select.value.valueOf() != 'error'.valueOf()
			&& helpBal.value.valueOf() != 'введите число'.valueOf())
		return true;
	else
		return false;
}