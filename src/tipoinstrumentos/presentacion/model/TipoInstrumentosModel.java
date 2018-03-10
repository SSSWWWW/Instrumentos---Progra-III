/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoinstrumentos.presentacion.model;
 

 
import instrumento.entidades.TipoInstrumento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author pc
 */
public class TipoInstrumentosModel extends java.util.Observable{
    
 
    TipoInstrumento filter; 
    TipoInstrumentoTableModel instrumentos;
    HashMap<String,String> errores;
    String mensaje;
  

    public TipoInstrumentosModel() {
    }

    public void init(){ 
        filter = new TipoInstrumento();
        ArrayList<TipoInstrumento> rows = new ArrayList<TipoInstrumento>();
        this.setEstado(rows);
        clearErrors();
    }

    
    
    
    
    public void setEstado(List<TipoInstrumento> instrumentos){
        int[] cols={TipoInstrumentoTableModel.CODIGO , TipoInstrumentoTableModel.NOMBRE , TipoInstrumentoTableModel.UNIDAD};
        this.instrumentos = new TipoInstrumentoTableModel(cols , instrumentos); 
        setChanged();
        notifyObservers();        
    }
    
    public TipoInstrumento getFilter() {
        return filter;
    }
    
    public void setFilter(TipoInstrumento filter) {
        this.filter = filter;
    }
    
     public TipoInstrumentoTableModel getEstados() {
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
