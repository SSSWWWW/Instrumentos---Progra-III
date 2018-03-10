/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumento.presentacion.model;

import instrumento.entidades.Instrumento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author pc
 */
public class InstrumentosModel extends java.util.Observable{
    Instrumento filter; 
    InstrumentoTableModel instrumentos;
    HashMap<String,String> errores;
    String mensaje;

    public InstrumentosModel() {
    }

    public void init(){ 
        filter = new Instrumento();
        List<Instrumento> rows = new ArrayList<Instrumento>();
        this.setInstrumentos(rows);
        clearErrors();
    }
    
    public void setInstrumentos(List<Instrumento> personas){
        int[] cols={InstrumentoTableModel.SERIE, InstrumentoTableModel.DESCRIPCION,InstrumentoTableModel.MINIMO,InstrumentoTableModel.MAXIMO , InstrumentoTableModel.TOLERANCIA};
        this.instrumentos =new InstrumentoTableModel(cols,personas);  
        setChanged();
        notifyObservers();        
    }
    
    public Instrumento getFilter() {
        return filter;
    }
    
    public void setFilter(Instrumento filter) {
        this.filter = filter;
    }
    
     public InstrumentoTableModel getInstrumentos() {
        return instrumentos;
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
