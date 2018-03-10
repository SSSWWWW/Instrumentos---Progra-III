/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumentos.logic;

import instrumento.entidades.Calibracion;
import instrumento.entidades.Instrumento;
import instrumento.entidades.Medida;
import instrumento.entidades.TipoInstrumento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
 
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import servicios.Dao;
 

/**
 *
 * @author Escinf
 */
public class Model {
   private final Dao dao;
    private HashMap<String,TipoInstrumento> list=new HashMap();
    private HashMap<String,Instrumento> list1=new HashMap();
    private HashMap<String,Calibracion> listcalibracion=new HashMap();
    private HashMap<String,Medida> listmedida=new HashMap();
     
    private static Model uniqueInstance;
    
    public static Model instance() throws ClassNotFoundException, SQLException{
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }
    private Model() throws ClassNotFoundException, SQLException{
       
         dao = new Dao();
    }
    
    
    
      

    public void MedidaBorrar(Medida ti) throws Exception{
        if (listmedida.get(ti.getReferencia())!=null){
            listmedida.remove(ti.getReferencia());
        }
        else{
            throw new Exception(" Calibracion No Existe");
        }
    }    
    
  
    
   
    
    
    
      
    public List<Calibracion> calibracionSerch(Calibracion filtro){
        List<Calibracion> resultado;
        resultado = new ArrayList<Calibracion>();
        for (Calibracion ti: listcalibracion.values()){
            if (ti.getNumero().contains(filtro.getNumero())){
                resultado.add(ti);
            }
        }
        return resultado;        
    } 
    
    public void CalibracionAgregar(Calibracion ti) throws Exception{
        if (listcalibracion.get(ti.getNumero())==null){
            listcalibracion.put(ti.getNumero(), ti);
        }
        else{
            throw new Exception(" Calibracion ya Existe");
        }
    }
    
    public void CalibracionActualzar(Calibracion ti) throws Exception{
        if (listcalibracion.get(ti.getNumero())!=null){
            listcalibracion.put(ti.getNumero(), ti);
        }
        else{
            throw new Exception("  Calibracion No Existe");
        }
    }

    public void CalibracionBorrar(Calibracion ti) throws Exception{
       
           dao.CalibracionDelete(ti);
        
    }    
    
    
    
    
    
    
    
    
   public List<instrumento.entidades.TipoInstrumento> searchTipoInstrumento(instrumento.entidades.TipoInstrumento filtro , String des){
        return dao.TipoInstrumentoSearch(filtro , des);
    }
    
   
    public List<Instrumento> searchInstrumento(Instrumento filtro , String des){
        return dao.InstrumentoSearch(filtro , des);
        
    }
    
     public List<String> CargarComboBox()
      {
       
         
        return dao.TipoInstrumentoGetAllNames();
       
     }
    
     public String getDescripcion(Instrumento s) throws SQLException{
     
         return dao.getDescripcion(s);
     
     }
    
 

    public void tipoInstrumentoBorrar(TipoInstrumento ti) throws Exception{
        if (list.get(ti.getCodigo())!=null){
            list.remove(ti.getCodigo());
        }
        else{
            throw new Exception(" Instrumento No Existe");
        }
    }    
    
    
    
    
    

    public List<Instrumento> InstrumentoSerch(Instrumento filtro){
        List<Instrumento> resultado;
        resultado = new ArrayList<Instrumento>();
        for (Instrumento ti: list1.values()){
            if (ti.getDescripcion().contains(filtro.getDescripcion())){
                resultado.add(ti);
            }
        }
        return resultado;        
    } 
    
    public void InstrumentoAgregar(Instrumento ti) throws Exception{
        if (list1.get(ti.getSerie())==null){
            list1.put(ti.getSerie(), ti);
            
             
        }
        else{
            throw new Exception("  Instrumento ya Existe");
        }
    }
    
    public void InstrumentoActualzar(Instrumento ti) throws Exception{
        if (list1.get(ti.getSerie())!=null){
            list1.put(ti.getSerie(), ti);
        }
        else{
            throw new Exception("  Instrumento No Existe");
        }
    }

    public void  InstrumentoBorrar( Instrumento ti) throws Exception{
        if (list1.get(ti.getSerie())!=null){
            list1.remove(ti.getSerie());
        }
        else{
            throw new Exception("  Instrumento No Existe");
        }
    }    
    
    
    
    
      public Collection<TipoInstrumento> getTipoInstrumentos(){
        return dao.TipoInstrumentoGetAll();
       
    }
    
    public  TipoInstrumento getTipoInstrumento(String codigo) throws Exception{
        return dao.TipoInstrumentoGet(codigo);
    }

    public Instrumento getInstrumento(String id) throws Exception{
        return dao.InstrumentoGet(id);
    }
    
    public void deleteInstrumento(Instrumento p) throws Exception{
        dao.InstrumentoDelete(p);
    }

    public void addInstrumento(Instrumento p) {
       try {
           dao.InstrumentoAdd(p);
       } catch (Exception ex) {
           Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void addMedida(Medida p ) {
       try {
           dao.MedidaAdd(p );
       } catch (Exception ex) {
           Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void agregarMedida(String instrumento ,int medida , int referencia , int lectura , Date fecha) throws SQLException, Exception{
    
    
        dao.agregarMedida(instrumento, medida, referencia, lectura , fecha);
    
    }
    
    public List<Calibracion> searchCalibracion(  String a , instrumento.entidades.Calibracion filtro) throws SQLException{
        return dao.CalibracionSearch(a , filtro);
        
    }
    
    
    public List<Medida> searchMedida(  String a , instrumento.entidades.Medida filtro) throws SQLException{
        return dao.MedidaSearch(a , filtro);
        
    }
     
    
    public void addTipoInstrumento1(TipoInstrumento p ){
    
        
        
       try {
           dao.TipoInstrumentoAdd(p);
       } catch (Exception ex) {
           Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
       }
    
    
    }
    
    public void addCalibracion(String a , Date fech) throws SQLException{
    
        dao.agregarCalibracion(a ,   fech);
    
    }
     
     
     public void deleteTipoInstrumento(TipoInstrumento p) throws Exception{
        dao.TipoInstrumentoDelete(p);
    }
     
     
      public void updateTipoInstrumento(TipoInstrumento i) throws Exception{
        dao.TipoInstrumentoUpdate(i);
    }
      
      public void updateMedida(int calibracion , int medida , int referencia , int lectura  ) throws Exception{
        dao.MedidaUpdate(calibracion , medida , referencia , lectura);
    }
     

    public void updateInstrumento(Instrumento i) throws Exception{
        dao.InstrumentoUpdate(i);
    }
    
   

    public void close(){
        dao.close();
    }

    
    
    
    
    
    
    
    
    
    
}
