/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medida.presentacion.model;

import instrumento.entidades.Medida;
import instrumento.entidades.TipoInstrumento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;
import tipoinstrumentos.presentacion.model.TipoInstrumentoTableModel;

/**
 *
 * @author pc
 */
public class MedidasModel extends java.util.Observable{
    
 
    Medida filter; 
    MedidaTableModel medidas;
    HashMap<String,String> errores;
    String mensaje;
  

    public MedidasModel() {
    }

    public void init(){ 
        filter = new Medida();
        ArrayList<Medida> rows = new ArrayList<Medida>();
        this.setMedidas(rows);
        clearErrors();
    }

    
    
    
    
    public void setMedidas(List<Medida> medidas){
        int[] cols={  MedidaTableModel.REFERENCIA , MedidaTableModel.LECTURA};
        this.medidas = new MedidaTableModel(cols , medidas); 
        setChanged();
        notifyObservers();        
    }
    
    public Medida getFilter() {
        return filter;
    }
    
    public void setFilter(Medida filter) {
        this.filter = filter;
    }
    
     public MedidaTableModel getMedidas() {
        return medidas;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public HashMap<String, String> getErrores() {
        return errores;
    }

    public void setErrores(HashMap<String, String> errores) {
        this.errores = errores;
    }
    
    public void clearErrors(){
        setErrores(new HashMap<String,String>());
        setMensaje(""); 
    }
    
}
