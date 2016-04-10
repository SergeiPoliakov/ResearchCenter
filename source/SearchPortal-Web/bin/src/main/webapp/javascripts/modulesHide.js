function show_income(){
    document.getElementById('income').style.display='block'; 
    document.getElementById('consump').style.display = 'none';
	document.getElementById('stat').style.display = 'none';
}
function show_consump(){
    document.getElementById('income').style.display='none'; 
    document.getElementById('consump').style.display = 'block';
	document.getElementById('stat').style.display = 'none';
}
function show_income(){
    document.getElementById('income').style.display='none'; 
    document.getElementById('consump').style.display = 'none';
	document.getElementById('stat').style.display = 'block';
}
$(document)
		.ready(
				function() {
					$('#popover-event-hidden').popover({
						placement : 'top'
					});
					$('#popover-event-hidden')
							.on(
									'hidden.bs.popover',
									function() {
										alert("Popover была полностью скрыта от пользователя. Нажмите на кнопку ещё раз, чтобы снова увидеть popover.");
									});
				});
