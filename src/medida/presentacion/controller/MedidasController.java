/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medida.presentacion.controller;

import applicationpresentacion.Application;
import instrumento.entidades.Medida;
 
import instrumentos.logic.Model;
import java.util.List;
import medida.presentacion.model.MedidaModel;
import medida.presentacion.model.MedidasModel;
import medida.presentacion.view.MedidasView;
 

/**
 *
 * @author pc
 */
public class MedidasController {
    
    Model domainModel;
    MedidasView view;
    MedidasModel model;
    
    String instru;

    public String getInstru() {
        return instru;
    }

    public void setInstru(String instru) {
        this.instru = instru;
    }
    
    
    

    public MedidasController(MedidasView view, MedidasModel model, Model domainModel  ) {
        
        model.init();
        
        this.domainModel = domainModel;
        this.view = view;
        this.model = model;
        
        view.setControl(this);
        view.setModel(model);
        
        
        
    }
    
   
      public void preAgregar(){
        MedidaModel medidaModel = Application.MEDIDA_VIEW.getModel();
        medidaModel.clearErrors();
        medidaModel.setModo (Application.MODO_AGREGAR);
        medidaModel.setCurrent(new Medida());
        Application.MEDIDA_VIEW.setVisible(true);
    } 
    
    public void buscar(){
    
         model.getFilter().setReferencia(Integer.getInteger(view.descripcionField.getText()));
        model.clearErrors();
    //    List<Medida> rows = domainModel.medidaSerch(model.getFilter());
        
         
           
   //     if (rows.isEmpty()){
            model.getErrores().put("descripcionField", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
      //  }
     //   model.setEstado(rows);
          
    
    }
    
      public void editar(int row){
        MedidaModel medidaModel = Application.MEDIDA_VIEW.getModel();
        medidaModel.clearErrors();
        Medida seleccionada = model.getMedidas().getRowAt(row); 
        medidaModel.setModo(Application.MODO_EDITAR);
        medidaModel.setCurrent(seleccionada);
        Application.MEDIDA_VIEW.setVisible(true);
        
       
    }
    
     public void borrar(int row) throws Exception {
       
        
        MedidasModel instrumentoModel = Application.MEDIDAS_VIEW.getModel();
        instrumentoModel.clearErrors();
        Medida seleccionada = model.getMedidas().getRowAt(row); 
        domainModel.MedidaBorrar(seleccionada );
    }
    
     
    
    
}
