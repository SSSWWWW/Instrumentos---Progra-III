/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoinstrumentos.presentacion.controller;

import instrumentos.logic.Model;
import instrumento.entidades.TipoInstrumento;
 
import java.util.List;
import tipoinstrumentos.presentacion.model.TipoInstrumentosModel;
import tipoinstrumentos.presentacion.view.TipoInstrumentosView;
import applicationpresentacion.Application;
import tipoinstrumentos.presentacion.model.TipoInstrumentoModel;

/**
 *
 * @author pc
 */

public class TipoInstrumentosController {
    
    Model domainModel;
    TipoInstrumentosView view;
    TipoInstrumentosModel model;
    

    public TipoInstrumentosController(TipoInstrumentosView view, TipoInstrumentosModel model, Model domainModel  ) {
        
        model.init();
        
        this.domainModel = domainModel;
        this.view = view;
        this.model = model;
        
        view.setControl(this);
        view.setModel(model);
        
        
        
    }
    
   
      public void preAgregar(){
        TipoInstrumentoModel instrumentoModel = Application.TIPOINSTRUMENTO_VIEW.getModel();
        instrumentoModel.clearErrors();
        instrumentoModel.setModo (Application.MODO_AGREGAR);
        instrumentoModel.setCurrent(new TipoInstrumento());
        Application.TIPOINSTRUMENTO_VIEW.setVisible(true);
    } 
    
     public void buscar(){
         
         
        model.getFilter().setNombre(view.descripcionField.getText());
        model.clearErrors();
        List<TipoInstrumento> rows = domainModel.searchTipoInstrumento(model.getFilter() , view.descripcionField.getText());
        if (rows.isEmpty()){
            model.getErrores().put("tipoInstrumento", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setEstado(rows);
    }
    
      public void editar(int row){
        TipoInstrumentoModel instrumentoModel = Application.TIPOINSTRUMENTO_VIEW.getModel();
        instrumentoModel.clearErrors();
        TipoInstrumento seleccionada = model.getEstados().getRowAt(row); 
        instrumentoModel.setModo(Application.MODO_EDITAR);
        instrumentoModel.setCurrent(seleccionada);
        Application.TIPOINSTRUMENTO_VIEW.codigoFld.setEditable(false);
        Application.TIPOINSTRUMENTO_VIEW.setVisible(true);
        
       
    }
    
     public void borrar(int row) throws Exception {
       
          TipoInstrumento seleccionada = model.getEstados().getRowAt(row); 
        try {
            domainModel.deleteTipoInstrumento(seleccionada);
        } catch (Exception ex) { }
        this.buscar();
        
        
    }
    
     
    
    
}
