google.charts.load('current', {
	'packages' : [ 'bar' ]
});
google.charts.setOnLoadCallback(drawStuff);

function drawStuff() {
	var data = new google.visualization.arrayToDataTable([
			[ 'Opening Move', 'Percentage' ], [ "King's pawn (e4)", 44 ],
			[ "Продукты", 31 ], [ "ЖКХ", 72 ],
			[ "Queen's bishop pawn (c4)", 10 ], [ 'Other', 3 ] ]);

	var options = {
		title : 'Chess opening moves',
		width : 900,
		legend : {
			position : 'none'
		},
		bars : 'horizontal', // Required for Material Bar Charts.
		axes : {
			x : {
				0 : {
					side : 'top',
					label : 'Percentage'
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