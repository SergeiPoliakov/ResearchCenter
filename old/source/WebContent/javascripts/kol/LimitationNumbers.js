/**
 * use letters limitation as only numbers input
 * 
 * @author Kolesnikov
 */

function validate() {
	var element = window.event; // get screen page event
	var key = element.which;
	key = String.fromCharCode(key);
	var regex = /[0-9]/; //regular
	if (!regex.test(key)) {
		if (element.preventDefault) { // cancel change by event screen
			element.preventDefault();
		} else {
			element.returnValue = false; // //cancel change by event screen
			// for ie8
		}
	}
}