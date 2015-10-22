package br.gov.ac.seap.pga.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumActionState;
import br.gov.ac.seap.pga.model.Authority;
import br.gov.ac.seap.pga.model.User;
import br.gov.ac.seap.pga.service.AuthorityService;
import br.gov.ac.seap.pga.service.UserService;
import br.gov.ac.seap.pga.util.FacesUtils;

@Controller
@ManagedBean
@ViewScoped
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;

	private User user;

	private List<User> list = null;

	private FacesUtils facesUtils;

	private EnumActionState actionstate = EnumActionState.PESQUISA;

	private UIForm form;

	private Authority authority;

	private String argumento;

	private String tipopesquisa = "cpf";

	private boolean pescpf = false;

	private boolean pesnome = true;

	public UserController() {

	}

	public void handleSelectPesquisa() {
		if ("cpf".equals(this.tipopesquisa)) {
			pescpf = true;
			pesnome = false;
			
			this.argumento = new String();

		}

		if ("nome".equals(this.tipopesquisa)) {
			pescpf = false;
			pesnome = true;

			this.argumento = new String();

		}
		limpa();
	}

	public boolean isPescpf() {
		return pescpf;
	}

	public void setPescpf(boolean pescpf) {
		this.pescpf = pescpf;
	}

	public boolean isPesnome() {
		return pesnome;
	}

	public void setPesnome(boolean pesnome) {
		this.pesnome = pesnome;
	}

	public void salvar() {
		facesUtils = new FacesUtils();
		try {
			this.userService.save(user);

			facesUtils.info(facesUtils.mensages("message.save.success"));

			limpa();

		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("message.save.error") + e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	public void novo() {
		this.user = new User();

		this.authority = new Authority();
		this.setActionstate(EnumActionState.FORM);
	}

	public void editar() {
		user = this.userService.findById(user.getId());
		this.setActionstate(EnumActionState.FORM);
	}

	public void lista() {
		list = this.userService.findAll();
		this.setActionstate(EnumActionState.PESQUISA);
	}

	private void limpa() {
		facesUtils = new FacesUtils();
		this.user = new User();
		this.list = null;

		// facesUtils.cleanSubmittedValues(form);
	}

	public void volta() {
		limpa();
		lista();
		// this.setActionstate(EnumActionState.PESQUISA);
	}

	public void addAuth() {
		this.authority = this.authorityService.findById(this.authority.getId());
		this.user.getAuthorities().add(this.authority);
		this.authority = new Authority();
	}

	public void carregatLista() {
		this.list = this.userService.findAll();
	}

	public void pesquisa() {
		facesUtils = new FacesUtils();
		limpa();
		try {

			if ("nome".equals(this.tipopesquisa)) {
				this.list = this.userService.findListByFullNameLike(argumento);
			}
			if ("cpf".equals(this.tipopesquisa)) {
				this.list = this.userService.findListByCpf(argumento);
			}

			if (list.isEmpty()) {
				throw new Exception();
			}
		} catch (Exception e) {
			facesUtils.erro(facesUtils.mensages("search.not.found") + e.getMessage());
		}

	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getTipopesquisa() {
		return tipopesquisa;
	}

	public void setTipopesquisa(String tipopesquisa) {
		this.tipopesquisa = tipopesquisa;
	}

	public boolean isPesquisando() {
		return EnumActionState.PESQUISA.equals(actionstate);
	}

	public boolean isAddEdit() {
		return EnumActionState.FORM.equals(actionstate);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getList() {
		return list;

	}

	public void setActionstate(EnumActionState actionstate) {
		this.actionstate = actionstate;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}
