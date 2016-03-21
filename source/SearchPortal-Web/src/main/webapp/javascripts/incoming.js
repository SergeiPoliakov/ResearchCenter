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
	if (activeCases.style.visibility.valueOf() == 'hidden'.valueOf()) {
		activeCases.style.visibility = 'visible';
	} else {
		activeCases.style.visibility = 'hidden';
	}
}