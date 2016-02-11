/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $("#statistic-menu-button").click(function (e) {
        $("#select-module1").css("display", "block");
        $("#priority-module").css("display", "none");
    });
    $("#cost-menu-button").click(function (e) {
        $("#select-module1").css("display", "none");
        $("#priority-module").css("display", "block");
    });
});
