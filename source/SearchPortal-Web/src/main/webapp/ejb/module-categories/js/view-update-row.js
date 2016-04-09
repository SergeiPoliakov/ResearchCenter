$(document)
		.ready(
				function() {

					$(".start-update-row-button").click(
							function() {
								// передача процентов
								$(this).parent().parent().children(
										".update-category-form").children(
										".range-min-percent").val(
										$(this).parent().children(
												".min-percent-view").val());
								$(this).parent().parent().children(
										".update-category-form").children(
										".range-max-percent").val(
										$(this).parent().children(
												".max-percent-view").val());
								// смена форм в строке
								$(this).parent().css("display", "none");
								$(this).parent().parent().children(
										".update-category-form").css("display",
										"block");
							});

					$(".update-row-button")
							.click(
									function() {
										if (($(this).parent().children(
												'.category-name').val().length > 3)
												&& ($(this).parent().children(
														'.category-name' < 50)
														.val().length)) {
											$(this).parent().submit();
										} else {
											$(this)
													.append(
															'<p class="err-validation">Название от 3 до 50 символов</p>');
											// если имя <3; >50; дублируется с
											// именем другого юзера в любом
											// регистре
										}

									});

					/*
					 * $(".update-category-form").validate({
					 * 
					 * rules : {
					 * 
					 * categoryname : { required : true, minlength : 3,
					 * maxlength : 50, }, },
					 * 
					 * messages : { categoryname : { required : "Это поле
					 * обязательно для заполнения", minlength : "Логин должен
					 * быть минимум 4 символа", maxlength : "Максимальное число
					 * символо - 50", }, }
					 * 
					 * });
					 */
				});