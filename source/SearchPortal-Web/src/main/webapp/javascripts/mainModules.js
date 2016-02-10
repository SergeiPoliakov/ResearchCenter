/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $("#statistic-menu-button").click(function (e) {
        //$(this).text("pushed");
        $("#select-module1").css("visibility", "visible");
        
        

        $("#priority-module").css("visibility", "hidden");
    });
    $("#cost-menu-button").click(function (e) {
        //$(this).text("pushed");
        $("#select-module1").css("visibility", "hidden");
        
        

        $("#priority-module").css("visibility", "visible");
    });
});
