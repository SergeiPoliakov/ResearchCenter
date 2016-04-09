$(document).ready(
		function() {
			$(".percent-slider").slider(
					{
						values : [ 50, 67 ],
						min : 0,
						max : 100,
						range : true,
						step : 1,
						slide : function(event, ui) {
							$(this).parent().children(".range-percent").val(
									ui.values[0] + " % - " + ui.values[1]
											+ " %");

							$(this).parent().children(".range-min-percent")
									.val(ui.values[0] + " % - ");
							$(this).parent().children(".range-max-percent")
									.val(ui.values[1] + " %");
						}
					});
		});