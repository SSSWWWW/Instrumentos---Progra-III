/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calibracion.presentacion.controller;

import applicationpresentacion.Application;
import calibracion.presentacion.model.CalibracionModel;
import calibracion.presentacion.model.CalibracionesModel;
import calibracion.presentacion.view.CalibracionView;
import instrumento.entidades.Calibracion;
import instrumento.entidades.Instrumento;
import instrumento.entidades.Medida;
import instrumento.entidades.TipoInstrumento;
import instrumentos.logic.Model;
import java.util.List;
import medida.presentacion.model.MedidasModel;

/**
 *
 * @author pc
 */
public class CalibracionController {
    Model domainModel;    
    CalibracionView view;
    CalibracionModel model;
    
    
    public CalibracionController(CalibracionView view, CalibracionModel model, Model domainModel) {
       // model.init(domainModel.getEstadosCiviles.toArray(new EstadoCivil[0]));
        model.init();
        this.domainModel= domainModel;
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void guardar(){
        CalibracionesModel calibracionesModel = Application.CALIBRACIONES_VIEW.getModel();

        Calibracion nueva = new Calibracion();
        model.clearErrors();
        
          MedidasModel medidasModel = Application.MEDIDAS_VIEW.getModel();

       Medida nuevamed = new Medida();
         model.clearErrors();
        
        
        nueva.setNumero(view.numeroCalibracionFld.getText());
        if (view.numeroCalibracionFld.getText().length()==0){
            model.getErrores().put("numero calibracion", "numero calibracion");
        }
        
      
         List<Calibracion> calibraciones;
        if (model.getErrores().isEmpty()){
            try{
                switch(model.getModo()){
                    case Application.MODO_AGREGAR:
                        domainModel.CalibracionAgregar(nueva);
                        model.setMensaje("Instrumento AGREGADO");
                        model.setCurrent(new Calibracion());
                        
                        calibracionesModel.clearErrors();
                        calibraciones = domainModel.calibracionSerch(calibracionesModel.getFilter());
                        calibracionesModel.setCalibraciones(calibraciones);                        
                        break;
                    case Application.MODO_EDITAR:
                        domainModel.CalibracionActualzar(nueva);
                        
                        model.setMensaje("Instrumento MODIFICADADO");
                         

                        calibraciones = domainModel.calibracionSerch(calibracionesModel.getFilter());
                        calibracionesModel.setCalibraciones(calibraciones);
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

