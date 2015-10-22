
var metro_colors = [
    'black', 'white', 'lime', 'green', 'emerald', 'teal', 'blue', 'cyan', 'cobalt', 'indigo', 'violet',
    'pink', 'magenta', 'crimson', 'red', 'orange', 'amber', 'yellow', 'brown', 'olive',
    'steel', 'mauve', 'taupe', 'gray', 'dark', 'darker', 'darkBrown', 'darkCrimson',
    'darkMagenta', 'darkIndigo', 'darkCyan', 'darkCobalt', 'darkTeal', 'darkEmerald',
    'darkGreen', 'darkOrange', 'darkRed', 'darkPink', 'darkViolet', 'darkBlue',
    'lightBlue', 'lightRed', 'lightGreen', 'lighterBlue', 'lightTeal', 'lightOlive',
    'lightOrange', 'lightPink', 'grayDark', 'grayDarker', 'grayLight', 'grayLighter'

];

function ini(){
    "use strict";

   // $("<div/>").load("headers.html").insertBefore($(".page-content"));
   // $("<div/>").load("footers.html").insertAfter($(".page-content"));
    
    var fundos = ['fundo1', 'fundo2', 'fundo3', 'fundo4'];
	var indice = Math.floor(Math.random() * (fundos.length -1));
	// você também pode adicionar dentro de um elemento
   $('div#fundo').addClass(fundos[indice]);

}

$(function(){
    "use strict";

    setInterval(function(){
        $("h1 .nav-button").toggleClass('transform');
    }, 2000);

    setInterval(function(){
        $("#job").toggleClass('block-shadow-danger');
    }, 1000);
});
