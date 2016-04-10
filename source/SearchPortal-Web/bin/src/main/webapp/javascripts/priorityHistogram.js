google.charts.load('current', {
	'packages' : [ 'bar' ]
});
google.charts.setOnLoadCallback(drawStuff);

function drawStuff() {
	var data = new google.visualization.arrayToDataTable([
			[ 'Opening Move', 'Percentage', { role: 'style' }], [ "King's pawn (e4)", 44, 'color: green'],
			[ "Продукты", 31, 'color: #cbe8ba' ], [ "ЖКХ", 72, 'color: #cbe8ba' ],
			[ "Queen's bishop pawn (c4)", 10, 'color: #cbe8ba' ], [ 'Other', 3, 'color: #cbe8ba' ] ]);

	var options = {
		width : 900,
		legend : {
			position : 'none'
		},
		bars : 'horizontal', // Required for Material Bar Charts.
		axes : {
			x : {
				0 : {
					side : 'top',
					label : 'Проценты'
				}
			// Top x-axis.
			}
		},
		bar : {
			groupWidth : "90%"
		}
	};

	var chart = new google.charts.Bar(document.getElementById('top_x_div'));
	chart.draw(data, options);
};