package br.gov.ac.seap.pga.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ac.seap.pga.enumerator.EnumTipoArquivo;
import br.gov.ac.seap.pga.model.Bairro;
import br.gov.ac.seap.pga.model.CadeiaProdutiva;
import br.gov.ac.seap.pga.model.Cidade;
import br.gov.ac.seap.pga.model.Ocupacao;
import br.gov.ac.seap.pga.model.Produtor;
import br.gov.ac.seap.pga.model.RegionalEstadual;
import br.gov.ac.seap.pga.model.RegionalMunicipal;
import br.gov.ac.seap.pga.service.BairroService;
import br.gov.ac.seap.pga.service.CadeiaProdutivaService;
import br.gov.ac.seap.pga.service.CidadeService;
import br.gov.ac.seap.pga.service.ProdutorService;
import br.gov.ac.seap.pga.util.DBConection;
import br.gov.ac.seap.pga.util.RelatorioUtil;

@Controller
@ManagedBean
@Scope("view")
public class RelatorioProdutorController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private ProdutorService produtorService;
	@Autowired
	private CidadeService cidadeService;	
	@Autowired
	private BairroService bairroService;
	
	@Autowired
	private CadeiaProdutivaService cadeiaProdutivaService;
	
	
	
	private Connection conn = null;
	
	private EnumTipoArquivo tipoarquivo;
	private Produtor produtor;
	
	private CadeiaProdutiva cadeiaProdutiva;	
	private RegionalEstadual regionalEstadual;	
	private Cidade cidade = null;
	private RegionalMunicipal  regionalMunicipal;
	private Bairro bairro = null;
	private Ocupacao ocupacao = null;
	
	private String grupo_select = "NENHUM";

	private List<Cidade> listaselectcidade;

	private List<Bairro> listaselectbairro;
	
	
	public void selecionaProdutor(){						
		this.produtor = produtorService.findById(this.produtor.getId());		
			 			 
			
	}
	
	  public void imprimirprodutor() throws IOException, JRException {
		  
		  conn = DBConection.getConnection();	        
	        String caminhorelatorio = 	"/app/relatorios/produtor/formulario/frmcadastro.jasper";
	        String nomerelatorio = "CadastroProdutor";
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        
	        parameters.put("produtorid", this.produtor.getId());
	        
	        RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio,nomerelatorio,  conn);  
	        
	    }
	
	  public void imprimirlistaprodutor() throws IOException, JRException {
		  conn = DBConection.getConnection();
		  String caminhorelatorio =  "/app/relatorios/produtor/lista/listacadastro.jasper";
	      String nomerelatorio = "listadeprodutores";
		  Map<String, Object> parameters = new HashMap<String, Object>();
	      String titulofiltros = "Filtro por ";
	      String titulogrupos = "Agrupado por ";
		  
	      if(!cadeiaProdutiva.getId().equals(0L)){
	    	  parameters.put("cadeiaprodutiva", cadeiaProdutiva.getId());
	    	  titulofiltros += "| Cadeia Produtiva "+cadeiaProdutiva.getDescricao();
	    	  parameters.put("titulofiltros", titulofiltros);
	      }
	      
	     
	      
//	      if(!regionalEstadual.getId().equals(0L)){
//	    	  parameters.put("regionalestadual", regionalEstadual.getId());
//	    	  titulofiltros += "| Regional Estadual ";
//	    	  parameters.put("titulofiltros", titulofiltros);
//	      }
	      
	      if(!cidade.getId().equals(0L)){
	    	  parameters.put("cidade", cidade.getId());
	    	  titulofiltros += "| Cidade "+cidade.getNome();
	    	  parameters.put("titulofiltros", titulofiltros);
	      }
//	      
//	      if(!regionalMunicipal.getId().equals(0L)){
//	    	  parameters.put("regionalmunicipal", regionalMunicipal.getId());
//	    	  titulofiltros += "| Regional Municipal ";
//	    	  parameters.put("titulofiltros", titulofiltros);
//	      }
	      
	      if(!bairro.getId().equals(0L)){
	    	  parameters.put("bairro", bairro.getId());
	    	  titulofiltros += "| Bairro "+bairro.getNome() ;
	    	  parameters.put("titulofiltros", titulofiltros);
	      }
	      
	      if(this.grupo_select.equals("NENHUM")){

	    	  parameters.put("titulofiltros", titulofiltros);	    	  
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio,nomerelatorio,  conn);  
	      }
	      
	      if(this.grupo_select.equals("CIDADE")){
	    	  titulogrupos += " CIDADE ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cidade.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio, nomerelatorio, conn);  
	      }
	      	      
	      
	      if(this.grupo_select.equals("CIDADEBAIRRO")){
	    	  titulogrupos += " CIDADE - BAIRRO ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cidade_bairro.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio, nomerelatorio, conn);  
	      }
	      
	      if(this.grupo_select.equals("CIDADECADEIAPRODUTIVA")){
	    	  titulogrupos += " CIDADE - CADEIA PRODUTIVA ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cidade_cadeiaprodutiva.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio, nomerelatorio, conn);  
	      }
	      
	      if(this.grupo_select.equals("CIDADEBAIRROCADEIAPRODUTIVA")){
	    	  titulogrupos += " CIDADE - BAIRRO - CADEIA PRODUTIVA ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cidade_bairro_cadeiaprodutiva.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio,nomerelatorio, conn);  
	      }
	      if(this.grupo_select.equals("CADEIAPRODUTIVA")){
	    	  titulogrupos += " CADEIA PRODUTIVA ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cadeiaprodutiva.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio,nomerelatorio, conn);  
	      }
	      if(this.grupo_select.equals("CADEIAPRODUTIVACIDADE")){
	    	  titulogrupos += " CADEIA PRODUTIVA - CIDADE ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cadeiaprodutiva_cidade.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio,nomerelatorio, conn);  
	      }
	      if(this.grupo_select.equals("CADEIAPRODUTIVACIDADEBAIRRO")){
	    	  titulogrupos += " CADEIA PRODUTIVA - CIDADE - BAIRRO ";
	    	  parameters.put("titulofiltros", titulofiltros);
	    	  parameters.put("titulogrupos", titulogrupos);
	    	  
	    	  caminhorelatorio = "/app/relatorios/produtor/lista/listacadastro_cadeiaprodutiva_cidade_bairro.jasper";
	    	  RelatorioUtil.criaRelatoriodb(parameters, caminhorelatorio,nomerelatorio, conn);  
	      }
	      
		  
	  }
	  
	  
	  
	  public List<SelectItem> getSelectItemsCidade() {
			List<SelectItem> toReturn = new LinkedList<SelectItem>();
			Cidade c = new Cidade();
			c.setId(0L);
			c.setNome("TODAS");
			toReturn.add(new SelectItem(c, c.getNome()));
			listaselectcidade = cidadeService.findListByEstadoId(1L);
			if (listaselectcidade != null) {
				for (Cidade cid : listaselectcidade) {
					toReturn.add(new SelectItem(cid, cid.getNome()));
				}
			}
			return toReturn;
		}

		public void handleCidadeChange() {

			if (cidade.getId() != 0L) {
				this.listaselectbairro = this.bairroService.findListByCidade(this.cidade);
			} else {
				this.listaselectbairro = null;
				this.getSelectItemsBairro().clear();
			}

		}

		public List<SelectItem> getSelectItemsBairro() {
			List<SelectItem> toReturn = new LinkedList<SelectItem>();
			Bairro b = new Bairro();
			b.setId(0L);
			b.setNome("TODOS");
			toReturn.add(new SelectItem(b, b.getNome()));
			if (listaselectbairro != null) {
				for (Bairro x : listaselectbairro) {
					toReturn.add(new SelectItem(x, x.getNome()));
				}
			}
			return toReturn;
		}
		
		public List<SelectItem> getSelectItemsCadeiaProdutiva() {
			List<SelectItem> retorno = new ArrayList<SelectItem>();
			CadeiaProdutiva c = new CadeiaProdutiva();
			c.setId(0L);
			c.setName("TODOS");
			retorno.add(new SelectItem(c,c.getName()));
			for (CadeiaProdutiva p : this.cadeiaProdutivaService.findAll()) {
				retorno.add(new SelectItem(p, p.getName()));

			}
			return retorno;
		}
	public EnumTipoArquivo getTipoarquivo() {
		return tipoarquivo;
	}

	public void setTipoarquivo(EnumTipoArquivo tipoarquivo) {
		this.tipoarquivo = tipoarquivo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public CadeiaProdutiva getCadeiaProdutiva() {
		return cadeiaProdutiva;
	}

	public void setCadeiaProdutiva(CadeiaProdutiva cadeiaProdutiva) {
		this.cadeiaProdutiva = cadeiaProdutiva;
	}

	public RegionalEstadual getRegionalEstadual() {
		return regionalEstadual;
	}

	public void setRegionalEstadual(RegionalEstadual regionalEstadual) {
		this.regionalEstadual = regionalEstadual;
	}

	public RegionalMunicipal getRegionalMunicipal() {
		return regionalMunicipal;
	}

	public void setRegionalMunicipal(RegionalMunicipal regionalMunicipal) {
		this.regionalMunicipal = regionalMunicipal;
	}

	public String getGrupo_select() {
		return grupo_select;
	}

	public void setGrupo_select(String grupo_select) {
		this.grupo_select = grupo_select;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	  
	
	
}
