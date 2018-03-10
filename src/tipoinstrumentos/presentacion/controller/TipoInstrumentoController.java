/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoinstrumentos.presentacion.controller;

import instrumentos.logic.Model;
import instrumento.entidades.TipoInstrumento;
import java.util.List;
import applicationpresentacion.Application;
import instrumento.entidades.Instrumento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import servicios.TipoInstrumentosServicio;
import servicios.conexion;
import tipoinstrumentos.presentacion.model.TipoInstrumentoModel;
import tipoinstrumentos.presentacion.model.TipoInstrumentosModel;
import tipoinstrumentos.presentacion.view.TipoInstrumentoView;
import tipoinstrumentos.presentacion.view.TipoInstrumentosView;

/**
 *
 * @author pc
 */
public class TipoInstrumentoController {
    Model domainModel;    
    TipoInstrumentoView view;
    TipoInstrumentosView views;
    TipoInstrumentoModel model;
    TipoInstrumentosServicio servicio;
    TipoInstrumentosController instrumentosController;
    conexion conex;
   
    
    public TipoInstrumentoController(TipoInstrumentoView view, TipoInstrumentoModel model, Model domainModel) {
        model.init();
        this.domainModel= domainModel;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    
    
    
    
    
    
     
 
 

    public void guardar() throws SQLException, ClassNotFoundException, Exception{
        TipoInstrumentosModel instrumentosModel = Application.TIPOINSTRUMENTOS_VIEW.getModel();

        TipoInstrumento nueva = new TipoInstrumento();
       
        nueva = model.getCurrent();
        
        System.out.println("DESDE TINSTRUMNETO CONTROLLER CODIGO " + nueva.getCodigo());
        String codaux = nueva.getCodigo();
        System.out.println("DESDE TINSTRUMNETO CONTROLLER modo " + model.getModo());
   
        model.clearErrors();
        
        
        
       nueva.setCodigo(view.codigoFld.getText());
        if (view.codigoFld.getText().length()==0){
            model.getErrores().put("codigo", "codigo requerido");
        }
         
        
       nueva.setNombre(view.nombreFld.getText());
        if (view.nombreFld.getText().length()==0){
            model.getErrores().put("nombre", "nombre requerido");
        }
       
        
         nueva.setUnidad(view.unidadField.getText());
        if (view.unidadField.getText().length()==0){
            model.getErrores().put("unidad", "unidad requerido");
        }
        
        System.out.println(nueva.getCodigo());
        System.out.println(nueva.getNombre());
        System.out.println(nueva.getUnidad());
        
        if(model.getModo() == 1){
            
            
            
             nueva.setCodigo(codaux);
        
            System.out.println("entra editar");
                        domainModel.updateTipoInstrumento(nueva);
                        System.out.println("edita");
                        model.setMensaje("Tipo Instrumento MODIFICADADO");
                        
               
           
        }
         
         //domainModel.addTipoInstrumento(nueva);
        
         List<TipoInstrumento> instrumentos ;
        if (model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                      
                    case Application.MODO_AGREGAR:
                         System.out.println("entra");
                        domainModel.addTipoInstrumento1(nueva);
                        System.out.println("agrega");
                        model.setMensaje("Instrumento AGREGADO");
                        model.setCurrent(new TipoInstrumento());
                        
                        
                        instrumentosModel.clearErrors();
                        //instrumentos = domainModel.InstrumentoSerch(instrumentosModel.getFilter());
                       // instrumentosModel.setEstado(instrumentos);                        
                        break;
                    case Application.MODO_EDITAR:
                       
                        System.out.println("entra editar");
                        domainModel.updateTipoInstrumento(nueva);
                        System.out.println("edita");
                        model.setMensaje("Tipo Instrumento MODIFICADADO");
                       
                         

                        instrumentos = domainModel.searchTipoInstrumento(instrumentosModel.getFilter() , views.descripcionField.getText());
                        instrumentosModel.setEstado(instrumentos);
                        view.setVisible(false);
                        
                        break;
                        
                        
                        
                        
                }
            }
            catch(Exception e){
                model.getErrores().put("Tipo instrumento", "Tipo instrumento ya existe");
                model.setMensaje("Tipo instrumento YA EXISTE");
                model.setCurrent(nueva);
            }
        }
        else{
            model.setMensaje("HAY ERRORES ...");
            model.setCurrent(nueva);
        }
        
       
    }  

 
}
