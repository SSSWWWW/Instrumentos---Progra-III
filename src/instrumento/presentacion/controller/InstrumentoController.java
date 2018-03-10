/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumento.presentacion.controller;

import applicationpresentacion.Application;
import instrumento.entidades.Instrumento;
 
import instrumento.presentacion.model.InstrumentoModel;
import instrumento.presentacion.model.InstrumentosModel;
import instrumento.presentacion.view.InstrumentoView;
import instrumento.presentacion.view.InstrumentosView;
import instrumentos.logic.Model;
 
import java.util.List;
 
import tipoinstrumentos.presentacion.model.TipoInstrumentoModel;
 

/**
 *
 * @author pc
 */
public class InstrumentoController {
    Model domainModel;    
    InstrumentoView view;
    InstrumentosView views;
    InstrumentoModel model;
    TipoInstrumentoModel modeltp;
    
    public InstrumentoController(InstrumentoView view, InstrumentoModel model, Model domainModel) {
       // model.init(domainModel.getEstadosCiviles.toArray(new EstadoCivil[0]));
        
       model.init();
        
        this.domainModel= domainModel;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void iniciarCombo(){
     
   
        model.clearErrors();
        List<String> rows = domainModel.CargarComboBox();
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        
        for(int i = 0 ; i < rows.size() ; i++){
        
               view.tipoFld.addItem(rows.get(i));
        
        }
        
     
}
        
    
   

    public void guardar() throws Exception{
        InstrumentosModel instrumentosModel = Application.INSTRUMENTOS_VIEW.getModel();

        Instrumento nueva = new Instrumento();
        model.clearErrors();
        
        nueva = model.getCurrent();
        
        System.out.println("DESDE INSTRUMENTO CONTROLLER SERIE : " + nueva.getSerie());
        
        
        nueva.setSerie(view.serieFld.getText());
        if (view.serieFld.getText().length()==0){
            model.getErrores().put("serie", "serie requerido");
        }
        
         nueva.setDescripcion(view.descripcionField.getText());
        if (view.descripcionField.getText().length()==0){
            model.getErrores().put("descripcion", "Descripcion requerido");
        }
        
        nueva.setMinimo( Integer.parseInt(view.minimoField.getText()));
        if (view.minimoField.getText().length()==0){
            model.getErrores().put("Minimo", "Minimo requerido");
        }
        
        nueva.setMaximo(Integer.parseInt(view.maximoField.getText()));
        if (view.maximoField.getText().length()==0){
            model.getErrores().put("Maximo", "maximo requerido");
        }
        
         nueva.setTolerancia(Integer.parseInt(view.ToleranciaField.getText()));
        if (view.ToleranciaField.getText().length()==0){
            model.getErrores().put("Tolerancia", "Tolerancia requerido");
        }
        
        System.out.println("serie " + nueva.getSerie());
        System.out.println("descripcion " + nueva.getDescripcion());
        System.out.println("maximo " + nueva.getMaximo());
        System.out.println("minimo " + nueva.getMinimo());
        System.out.println("tolerancia " + nueva.getTolerancia());
        
         
        
         List<Instrumento> instrumentos;
        if (model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                    case Application.MODO_AGREGAR:
                         System.out.println("entra");
                        domainModel.addInstrumento(nueva);
                        System.out.println("agrega");
                        model.setMensaje("Instrumento AGREGADO");
                        model.setCurrent(new  Instrumento());
                        
                        instrumentosModel.clearErrors();
                        //instrumentos = domainModel.InstrumentoSerch(instrumentosModel.getFilter());
                       // instrumentosModel.setEstado(instrumentos);                        
                        break;
                    case Application.MODO_EDITAR:
                         System.out.println("entra editar");
                        domainModel.updateInstrumento(nueva);
                        System.out.println("edita");
                        
                        model.setMensaje("Instrumento MODIFICADADO");
                         

                        instrumentos = domainModel.searchInstrumento(instrumentosModel.getFilter() , views.descripcionField.getText());
                        instrumentosModel.setInstrumentos(instrumentos);
                        //view.setVisible(false);
                        
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
    

  
 

