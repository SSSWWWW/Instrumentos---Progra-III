/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoinstrumentos.presentacion.model;

import instrumento.entidades.TipoInstrumento;
import java.util.HashMap;
 

/**
 *
 * @author pc
 */
public class TipoInstrumentoModel extends java.util.Observable {
    TipoInstrumento current;
     
    HashMap<String,String> errores;
    String mensaje;
    int modo;    

    public TipoInstrumentoModel() {
    }

    public void init( ){
        
        setCurrent(new TipoInstrumento());
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
    public TipoInstrumento getCurrent() {
        return current;
    }

    public void setCurrent(TipoInstrumento current) {
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
