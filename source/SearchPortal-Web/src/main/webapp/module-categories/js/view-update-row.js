$(document).ready(
		function() {

			$(".start-update-row-button").click(
					function() {
						$(this).parent().css("display", "none");
						$(this).parent().parent().children(
								".update-category-form")
								.css("display", "block");
					});

			$(".update-row-button").click(
					function() {
						$(this).parent().css("display", "none");
						$(this).parent().parent().children(
								".view-category-form").css("display", "block");
					});
		});