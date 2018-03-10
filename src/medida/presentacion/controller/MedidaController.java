/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medida.presentacion.controller;

import applicationpresentacion.Application;
import calibracion.presentacion.controller.CalibracionesController;
import calibracion.presentacion.model.CalibracionesModel;
import calibracion.presentacion.view.CalibracionesView;
import instrumento.entidades.Calibracion;
import instrumento.entidades.Instrumento;
import instrumento.entidades.Medida;
 
 
import instrumentos.logic.Model;
import java.util.List;
import medida.presentacion.model.MedidaModel;
import medida.presentacion.model.MedidasModel;
import medida.presentacion.view.MedidaView;
 

/**
 *
 * @author pc
 */
public class MedidaController {
    Model domainModel;    
    MedidaView view;
    MedidaModel model;
    
      CalibracionesController control;
    
    
    CalibracionesModel calmodel; ;
   
    
    public MedidaController(MedidaView view, MedidaModel model, Model domainModel) {
        model.init();
        this.domainModel= domainModel;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void guardar(){
        MedidasModel medidasModel = Application.MEDIDAS_VIEW.getModel();

       Medida nueva = new Medida();
         model.clearErrors();
        
         
          
        
         
        System.out.println("set de numero");
        
        nueva.setReferencia(Integer.getInteger(view.referenciaFld.getText()));
        if (view.referenciaFld.getText().length()==0){
            model.getErrores().put("referencia", "Referencia requerido");
        }
        
        nueva.setLectura(Integer.getInteger(view.lecturaField.getText()));
        if (view.lecturaField.getText().length()==0){
            model.getErrores().put("lectura", "Lectura requerido");
        }
        
        
         
        List<Medida> medidas;
        if (model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                    
                    case Application.MODO_AGREGAR:
                        System.out.println("entra agregar YES");
                        domainModel.addMedida(nueva );
                        System.out.println("entra agregar YES bien");
                        model.setMensaje("Instrumento AGREGADO");
                        model.setCurrent(new Medida());
                        
                          medidasModel.clearErrors();
                    //    medidas = domainModel.medidaSerch(medidasModel.getFilter());
                   //     medidasModel.setEstado(medidas);                        
                        break;
                    case Application.MODO_EDITAR:
                     //   domainModel.MedidaActualzar(nueva);
                        
                        model.setMensaje("Instrumento MODIFICADADO");
                         

                    //    medidas = domainModel.medidaSerch(medidasModel.getFilter());
                    //    medidasModel.setEstado(medidas);
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