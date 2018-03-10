/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calibracion.presentacion.controller;

import applicationpresentacion.Application;
import calibracion.presentacion.model.CalibracionModel;
import calibracion.presentacion.model.CalibracionesModel;
import calibracion.presentacion.view.CalibracionesView;
import instrumento.entidades.Calibracion;
import instrumento.entidades.Instrumento;
import instrumento.entidades.Medida;
import instrumento.presentacion.model.InstrumentoModel;
import instrumento.presentacion.model.InstrumentosModel;
import instrumento.presentacion.view.InstrumentosView;
import instrumentos.logic.Model;
import java.sql.Date;
 
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
 
import java.util.List;
import medida.presentacion.model.MedidaModel;
import medida.presentacion.model.MedidasModel;

/**
 *
 * @author pc
 */
public class CalibracionesController {
    Model domainModel;
    CalibracionesView view;
    CalibracionesModel model;
    
    MedidaModel modelmedida;
    
    public CalibracionesController(CalibracionesView view, CalibracionesModel model, Model domainModel) {
        model.init();
        this.domainModel= domainModel;
        
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    
    public void buscar(){
        model.getFilter().setNumero(view.descripcionField.getText());
        model.clearErrors();
        List<Calibracion> rows = domainModel.calibracionSerch(model.getFilter());
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUN REGISTRO COINCIDE");
        }
        model.setCalibraciones(rows);
    }
    
    
    
    
    
       public void buscarMedidas(String a) throws SQLException, ParseException{
    
     //   model.getFilter().setDescripcion(view.descripcionField.getText());
        model.clearErrors();
        Medida med = new Medida();
        
        List<Medida> rows = domainModel.searchMedida(a , med);
      
       //  DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
      // java.sql.Date sqlDate = new java.sql.Date(df.parse("02-04-2015").getTime());
      
      // Calibracion e = new Calibracion(sqlDate , 1);
      // Calibracion w = new Calibracion(sqlDate , 2);
      
         // rowss.add(e);
         // rowss.add(w);
         
        System.out.println("VERIFICAR LISTA SE ENVIO DESDE TAO EN CALIBRACIONES CONTROLLER");
        rows.forEach(System.out::println);
        
        if (rows.isEmpty()){
            model.getErrores().put("nombreFld", "Ningun registro coincide");
            model.setMensaje("NINGUNA CALIBRACION COINCIDE");
        }
        model.setMedidas(rows);
    
    
    }
    
    
    
    
    public void addRowsMedidas(int a){
    
        List<Medida> rows = new ArrayList<>();
        
        for(int i = 0 ; i < a ; i++){
        
            rows.add(new Medida());
        
        }
        
        model.setMedidas(rows);
    
    
    
    }
    
    
    
    public void buscarCalibraciones(String a) throws SQLException, ParseException{
    
     //   model.getFilter().setDescripcion(view.descripcionField.getText());
        model.clearErrors();
        List<Calibracion> rows = domainModel.searchCalibracion(a , model.getFilter());
         List<Calibracion> rowss = new  ArrayList<Calibracion>();
         
       //  DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
      // java.sql.Date sqlDate = new java.sql.Date(df.parse("02-04-2015").getTime());
      
      // Calibracion e = new Calibracion(sqlDate , 1);
      // Calibracion w = new Calibracion(sqlDate , 2);
      
         // rowss.add(e);
         // rowss.add(w);
         
        System.out.println("VERIFICAR LISTA SE ENVIO DESDE TAO EN CALIBRACIONES CONTROLLER");
        rows.forEach(System.out::println);
        
     //   if (rows.isEmpty()){
      //      model.getErrores().put("nombreFld", "Ningun registro coincide");
     //       model.setMensaje("NINGUNA CALIBRACION COINCIDE");
     //   }
        model.setCalibraciones(rows);
    
    
    }

    public void preAgregar(){
        
        MedidaModel medidaModel = Application.MEDIDA_VIEW.getModel();
        medidaModel.clearErrors();
        medidaModel.setModo (Application.MODO_AGREGAR);
        medidaModel.setCurrent(new Medida());
        Application.MEDIDA_VIEW.setVisible(true);
        
         
    }
    
    public void editar(int row){
        CalibracionModel calibracionModel = Application.CALIBRACION_VIEW.getModel();
        calibracionModel.clearErrors();
        Calibracion seleccionada = model.getCalibraciones().getRowAt(row); 
        calibracionModel.setModo(Application.MODO_EDITAR);
        calibracionModel.setCurrent(seleccionada);
        
        
        
        Application.CALIBRACION_VIEW.setVisible(true);
    }

     public void borrar(int row) throws Exception {
       
        
        CalibracionesModel calibracionesModel = Application.CALIBRACIONES_VIEW.getModel();
        calibracionesModel.clearErrors();
        Calibracion seleccionada = model.getCalibraciones().getRowAt(row); 
        domainModel.CalibracionBorrar(seleccionada);
    }
     
     public void agregarCalibracion(String instrumento, Date d) throws SQLException{
     
         domainModel.addCalibracion(instrumento, d);
     
     }
     
     public void updateMedida(int calibracion , int medida , int referencia , int lectura ) throws Exception{
     
         domainModel.updateMedida(calibracion, medida, referencia, lectura);
     
     }
     
     public void iniciarDescripcion(Instrumento s) throws SQLException{
     
        String des =  domainModel.getDescripcion(s);
     
      view.descripcionField.setText(des);
        
     }
     
     public void agregarMedida(String instrumento ,int medida , int referencia , int lectura , Date fecha) throws SQLException, Exception{
     
         domainModel.agregarMedida(instrumento, medida, referencia, lectura , fecha);
     
     }
    
}
