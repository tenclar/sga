
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

PrimeFaces.locales['pt'] = {
        closeText: 'Fechar',
        prevText: 'voltar',
        nextText: 'proximo',
        currentText: 'hoje',
        monthNames: ['janeiro','fevereiro','Março','Abril','Maio','Junho',
            'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['jan','fev','mar','Abr','Mai','jun',
            'jul','Ago','set','out','nov','dez'],
        dayNames: ['domingo','Segunda-feira','Terça-feira','Quarta-feira','Quinta-Feira','Sexta-feira','Sábado'],
        dayNamesShort: ['dom','seg','ter','qua','qui','sex','sab'],
        dayNamesMin: ['do','se','te','qa','qi','sx','sa'],
        weekHeader: 'Hf',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        month: 'Mês',
        week: 'semana',
        day: 'dia',
        allDayText : 'dia Inteiro'
    };
