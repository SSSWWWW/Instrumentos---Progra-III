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
public class MedidaModel extends java.util.Observable {
    Medida current;
     
    HashMap<String,String> errores;
    String mensaje;
    int modo;    

    public MedidaModel() {
    }

    public void init( ){
        
        setCurrent(new Medida());
        clearErrors();
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
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
    public Medida getCurrent() {
        return current;
    }

    public void setCurrent(Medida current) {
        this.current = current;
        setChanged();
        notifyObservers();        
    }

    
 
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers();
    }
}
