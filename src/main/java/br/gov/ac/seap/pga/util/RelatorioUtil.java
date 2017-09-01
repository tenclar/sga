/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.ac.seap.pga.util;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

/**
 *
 * @author admin
 */
public abstract class RelatorioUtil {
    
		
	
     public static void criaRelatorioColectionParam(List<?> listas, String caminhorelatorio, String nomerelatorio,Map<String, Object> parameters ) throws IOException, JRException {

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath(caminhorelatorio);
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listas);
        

        response.setHeader("Content-Disposition", "inline; filename=" + nomerelatorio);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
     public static void criaRelatorioColection(List<?> listas, String caminhorelatorio, String nomerelatorio ) throws IOException, JRException {

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();

        String relJasper = scontext.getRealPath(caminhorelatorio);
        //InputStream inputStream = getClass().getResourceAsStream(relJasper);
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();
        ServletOutputStream responseStream = response.getOutputStream();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listas);
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        response.setHeader("Content-Disposition", "inline; filename=" + nomerelatorio);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, ds);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
     public static void criaRelatoriodb(Map<String, Object> parameters, String caminhorelatorio, String nomerelatorio, Connection conn ) throws IOException, JRException {

        FacesContext fcontext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();           
        
        HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();              
        response.setHeader("Content-Disposition", "inline; filename="+nomerelatorio);
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/pdf");
        
        ServletOutputStream responseStream = response.getOutputStream();
        
        String relJasper = scontext.getRealPath(caminhorelatorio);	
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, conn);
        JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
        
        byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
        
        response.getOutputStream().write(x1);
        responseStream.flush();
        responseStream.close();
        
        fcontext.renderResponse();
        fcontext.responseComplete();


    }
     public static void criaRelatoriodbexcel(Map<String, Object> parameters, String caminhorelatorio,Connection conn ) throws IOException, JRException {

         FacesContext fcontext = FacesContext.getCurrentInstance();
         ServletContext scontext = (ServletContext) fcontext.getExternalContext().getContext();           
         
         HttpServletResponse response = (HttpServletResponse) fcontext.getExternalContext().getResponse();              
         response.setHeader("Content-Disposition", "inline; filename=rel_quantitativo");
         response.setHeader("Cache-Control", "no-cache");
         response.setContentType("application/xls");
         
         ServletOutputStream responseStream = response.getOutputStream();
         
         String relJasper = scontext.getRealPath(caminhorelatorio);	
         
         JasperPrint jasperPrint = JasperFillManager.fillReport(relJasper, parameters, conn);
         JasperExportManager.exportReportToPdfStream(jasperPrint, responseStream);
         
         	
         JRXlsExporter exporter = new JRXlsExporter();         
         exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,  relJasper);
//         exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,         "C://sample_report.xls");

         exporter.exportReport();
         
         byte x1[] = JasperExportManager.exportReportToPdf(jasperPrint);
         
         response.getOutputStream().write(x1);
         responseStream.flush();
         responseStream.close();
         
         fcontext.renderResponse();
         fcontext.responseComplete();


     }
    
}
