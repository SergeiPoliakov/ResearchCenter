$(document).ready(function(){
  $( "#slider" ).slider({
  value : 50,
  min : 0,
  max : 100,
  step : 1,
  create: function( event, ui ) {
   val = $( "#slider" ).slider("value");
  $( "#contentSlider" ).html( val );
 },
 slide: function( event, ui ) {
  $( "#contentSlider" ).html( ui.value );
            }
        });
});

//src="http://code.jquery.com/jquery-latest.js"
//href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet"
//src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"
