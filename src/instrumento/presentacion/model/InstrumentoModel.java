/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumento.presentacion.model;

import instrumento.entidades.Instrumento;
import instrumento.entidades.TipoInstrumento;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author pc
 */
public class InstrumentoModel extends java.util.Observable {
    Instrumento current;
    ComboBoxModel<TipoInstrumento> tipoInstrumentos;
    HashMap<String,String> errores;
    String mensaje;
    int modo;    

    public InstrumentoModel() {
    }

    //public void init(TipoInstrumento[] estadosCiviles){
    public void init( ){
       // setEstadosCiviles(estadosCiviles);
        setCurrent(new Instrumento());
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
    public Instrumento getCurrent() {
        return current;
    }

    public void setCurrent(Instrumento current) {
        this.current = current;
        setChanged();
        notifyObservers();        
    }

    public ComboBoxModel<TipoInstrumento> getTipoInstrumentos() {
        return tipoInstrumentos;
    }

    public void setTipoInstrumentos(TipoInstrumento[] estadosCiviles) {
        this.tipoInstrumentos = new DefaultComboBoxModel(estadosCiviles);
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
