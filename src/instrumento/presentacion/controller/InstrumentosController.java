/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumento.presentacion.controller;

import applicationpresentacion.Application;
import calibracion.presentacion.model.CalibracionModel;
import calibracion.presentacion.model.CalibracionesModel;
import instrumento.entidades.Calibracion;
import instrumento.entidades.Instrumento;
 
import instrumento.presentacion.model.InstrumentoModel;
import instrumento.presentacion.model.InstrumentosModel;
import instrumento.presentacion.view.InstrumentosView;
import instrumentos.logic.Model;
import java.util.List;
import javax.swing.SwingUtilities;
 

/**
 *
 * @author pc
 */
public class InstrumentosController {
    Model domainModel;
    InstrumentosView view;
    InstrumentosModel model;
    
    public InstrumentosController(InstrumentosView view, InstrumentosModel model, Model domainModel) {
        model.init();
        this.domainModel= domainModel;
        
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void buscar(){
        model.getFilter().setDescripcion(view.descripcionField.getText());
        model.clearErrors();
        List<Instrumento> rows = domainModel.searchInstrumento(model.getFilter() , view.descripcionField.getText());
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setInstrumentos(rows);
    }

    public void preAgregar(){
        InstrumentoModel instrumentoModel = Application.INSTRUMENTO_VIEW.getModel();
        instrumentoModel.clearErrors();
        instrumentoModel.setModo(Application.MODO_AGREGAR);
        instrumentoModel.setCurrent(new Instrumento());
        Application.INSTRUMENTO_VIEW.serieFld.setEditable(true);
        Application.INSTRUMENTO_VIEW.setVisible(true);
    }
    
     public void editar1(int row){
        
        InstrumentoModel instrumentosModel = Application.INSTRUMENTO_VIEW.getModel();
        instrumentosModel.clearErrors();
        Instrumento seleccionada = model.getInstrumentos().getRowAt(row);
        instrumentosModel.setModo(Application.MODO_EDITAR);
        instrumentosModel.setCurrent(seleccionada);
        Application.INSTRUMENTO_VIEW.setVisible(true);
      
        
         
        
        
        
      /*  
        InstrumentoModel instrumentoModel = Application.INSTRUMENTO_VIEW.getModel();
        instrumentoModel.clearErrors();
        Instrumento seleccionada = model.getInstrumentos().getRowAt(row); 
        instrumentoModel.setModo(Application.MODO_EDITAR);
        instrumentoModel.setCurrent(seleccionada);
        Application.INSTRUMENTO_VIEW.setVisible(true); */
    }
    
    
    
    public void editar(int row){
        
        CalibracionesModel calibracionesModel = Application.CALIBRACIONES_VIEW.getModel();
        calibracionesModel.clearErrors();
        Instrumento seleccionada = model.getInstrumentos().getRowAt(row);
        calibracionesModel.setModo(Application.MODO_EDITAR);
        calibracionesModel.setCurrent(seleccionada);
      
        
        Application.CALIBRACIONES_VIEW.setVisible(true);
        
        
        
      /*  
        InstrumentoModel instrumentoModel = Application.INSTRUMENTO_VIEW.getModel();
        instrumentoModel.clearErrors();
        Instrumento seleccionada = model.getInstrumentos().getRowAt(row); 
        instrumentoModel.setModo(Application.MODO_EDITAR);
        instrumentoModel.setCurrent(seleccionada);
        Application.INSTRUMENTO_VIEW.setVisible(true); */
    }

     public void borrar(int row) throws Exception {
       
        Instrumento seleccionada = model.getInstrumentos().getRowAt(row); 
        try {
            domainModel.deleteInstrumento(seleccionada);
        } catch (Exception ex) { }
        this.buscar();
    }
    
}
