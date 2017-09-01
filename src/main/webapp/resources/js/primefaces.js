function handleRequest(dlgUsuario, args) {
    if (args.validationFailed) {
        /* jQuery('#dlgUsuario').effect("shake", { times:3 }, 100);  */
        dlgUsuario.show();
    } else {
        dlgUsuario.hide();
        /*jQuery('#loginLink').fadeOut();  */
    }
}

function handleNewProdutor(args) {
    if (!args.validationFailed) {
        if (args.duplicado == false) {

        	PF('panelList').close();
        	PF('panelForm').show();
        	PF('dlg_new_produtor').hide();

        }
    }

}


