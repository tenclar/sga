
$(function() {
	var form = $(".login-form");

	form.css({
		opacity : 1,
		"-webkit-transform" : "scale(1)",
		"transform" : "scale(1)",
		"-webkit-transition" : ".5s",
		"transition" : ".5s"
	});
});

function ini() {

	var fundos = [ 'fundo1', 'fundo2', 'fundo3', 'fundo4' ];
	var indice = Math.floor(Math.random() * (fundos.length - 1));
	// você também pode adicionar dentro de um elemento
	$('div#wrap').addClass(fundos[indice]);

}

$(function() {
	$("#datapiker2").datepicker({
		todayBtn : true,
		language : "pt-BR",
		autoclose : true,
		todayHighlight : true,

	});
});