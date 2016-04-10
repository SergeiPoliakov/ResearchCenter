function showIncoming() {
	var activeCases = document.getElementById('incoming');
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
	}
}


function showInvoices() {
	var activeCases = document.getElementById('invoices');
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
	}
}

function addInvoice() {
	var activeCases = document.getElementById('add-invoice');
	alert(activeCases.style.display);
	if (activeCases.style.display.valueOf() == 'none'.valueOf()) {
		activeCases.style.display = '';
	} else {
		activeCases.style.visibility = 'none';
	}
	return false;
}
	
function deleteInvoice() {
		var activeCases = document.getElementById('delete-invoice');
		if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
			activeCases.style.visibility = 'visible';
		} else {
			activeCases.style.visibility = 'hidden';
		}
}

function showIncomes() {
		var activeCases = document.getElementById('incomes');
		if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
			activeCases.style.visibility = 'visible';
		} else {
				activeCases.style.visibility = 'hidden';
		}
		}

function addIncome() {
	var activeCases = document.getElementById('add-income');
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
	}
}

function deleteIncome() {
	var activeCases = document.getElementById('delete-income');
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
	}
}
	
