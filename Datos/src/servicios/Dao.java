/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

/**
 *
 * @author pc
 */
 
import instrumento.entidades.Calibracion;
import instrumento.entidades.Instrumento;
import instrumento.entidades.Medida;
import instrumento.entidades.TipoInstrumento;
import java.sql.Date;
import java.sql.PreparedStatement;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
 
 

/**
 *
 * @author jsanchez
 */
public class Dao {
    RelDatabase db= new RelDatabase();;
    
    public Dao() throws ClassNotFoundException, SQLException{
        db= new RelDatabase();
    }
    public TipoInstrumento TipoInstrumentoGet(String codigo) throws Exception{
        String sql="select * from TipoInstrumento where codigo='%s'";
        sql = String.format(sql,codigo);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return tipoInstrumento(rs);
        }
        else{
            throw new Exception ("Tipo Instrumento no Existe");
        }
    }
    private TipoInstrumento tipoInstrumento(ResultSet rs){
        try {
            TipoInstrumento ec= new TipoInstrumento();
            ec.setNombre(rs.getString("nombre"));
            ec.setCodigo(rs.getString("codigo"));
            ec.setUnidad(rs.getString("unidad"));
            return ec;
        } catch (SQLException ex) {
            return null;
        }
    }

    public Collection<TipoInstrumento> TipoInstrumentoGetAll(){
        Vector<TipoInstrumento> estados=new Vector<TipoInstrumento>();
        try {
            String sql="select * from TipoInstrumento";
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(tipoInstrumento(rs));
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
    
    
    

    public Instrumento InstrumentoGet(String id) throws Exception{
        String sql="select * "+
                "from instrumento p inner join tipoinstrumento e on p.tipo=e.nombre "+
                "where p.tipo='%s'";
        sql = String.format(sql,id);
        ResultSet rs =  db.executeQuery(sql);
        if (rs.next()) {
            return instrumento(rs);
        }
        else{
            throw new Exception ("Instrumento no Existe");
        }
    }
    
    

    public void InstrumentoAdd(Instrumento p) throws Exception{
        String sql="insert into instrumento (serie , descripcion , minimo , maximo , tolerancia  ) "+
                "values(? ,? ,? ,? ,? )";
        PreparedStatement preparedStmt = db.cnx.prepareStatement(sql);
        preparedStmt.setString (1, p.getSerie());
        preparedStmt.setString (2, p.getDescripcion());
        preparedStmt.setInt(3, p.getMinimo());
        preparedStmt.setInt(4, p.getMaximo());
        preparedStmt.setInt(5, p.getTolerancia());
      
       preparedStmt.execute();
       
    }
    
    
    // medida tiene que ir aumentando si se repite no agrega
    public void agregarMedida(String instrumento ,int medida , int referencia , int lectura , Date fecha) throws SQLException, Exception{
    
        String des = "";
        String instru = "select serie from instrumentos.instrumento where descripcion = '%s' ";
        instru=String.format(instru,instrumento);
        ResultSet rs =  db.executeQuery(instru);
        
        if(rs.next()){
        
            des = rs.getString("serie");
        
        }
       
         
         System.out.println("desde dao instrumento serie " + des  );   
          System.out.println("desde dao instrumento FECH " + fecha  );   
           
         
          String numero = "";
        String instrus = "select numero from instrumentos.calibracion where instrumento = '%s' order by numero desc limit 1 ";
        instrus=String.format(instrus,des);
        ResultSet rss =  db.executeQuery(instrus);
        
        if(rss.next()){
        
            numero = rss.getString("numero");
        
        }
        
        System.out.println("DESDE DAO AGREGAR MEDIDA NUMERO : " + numero);
        
        
          String med = "";
        String instruss = "select medida from instrumentos.medida where calibracion = '%s' order by medida desc limit 1";
        instruss=String.format(instruss,numero);
        ResultSet rsss =  db.executeQuery(instruss);
        
        if(rsss.next()){
        
            med = rsss.getString("medida");
        
        }
       
        
        
        
        System.out.println("DESDE DAO AGREGAR MEDIDA , MEDIDA : " + med);
        
        int medi = 0;
        
        if(!med.equals("")){
        
          medi = Integer.parseInt(med);
        
        medi = medi + 1;
        } else
        {
              medi = Integer.parseInt("1");
        
        
        }
        
        
       
         System.out.println("desde dao instrumento numero " + numero  ); 
         
        String sql = "insert into medida(calibracion , medida , referencia, lectura) " +
                 "values('%s','%s','%s','%s' )";
        sql=String.format(sql,numero,medi,referencia , lectura);
        int count=db.executeUpdateWithKeys(sql);
        if (count==0){
            throw new Exception("No se agrego ya existe");
        }
        
        
        System.out.println("DESDE DAO 7 NOV NUM numero " + numero);
        
        
     
    String medz = "";
        String instrussz = "select count(calibracion) from instrumentos.medida where calibracion = '%s' ";
        instrussz=String.format(instrussz,numero);
        ResultSet rsssz =  db.executeQuery(instrussz);
        
        if(rsssz.next()){
        
            medz = rsssz.getString("count(calibracion)");
        
        }
        
        System.out.println("DESDE DAO 7 NOV NUM CALI " + medz);
        
        
        String sqls = "update instrumentos.calibracion set mediciones = '%s' where numero = '%s' ";
          sqls=String.format(sqls, medz , numero);
        int counts=db.executeUpdateWithKeys(sqls);
        
        
        
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
      public void agregarCalibracion(String a , Date fech) throws SQLException{
    
          
          
          
          String dess = "";
        String instrus = "select *from instrumentos.calibracion where fecha = '%s' ";
        instrus=String.format(instrus,fech);
        ResultSet rss =  db.executeQuery(instrus);
        
        if(rss.next()){
        
            dess = rss.getString("fecha");
        
        }
        
        System.out.println("DESDE DAO AGREGAR CALIBRACION : " + dess);
          
           
        String des = "";
        String instru = "select serie from instrumentos.instrumento where descripcion = '%s' ";
         instru=String.format(instru,a);
        ResultSet rs =  db.executeQuery(instru);
        
        if(rs.next()){
        
            des = rs.getString("serie");
        
        }
        
        
      
        String desq = "";
        String instruq = "select numero from instrumentos.calibracion order by numero desc limit 1";
        instruq=String.format(instruq);
        ResultSet rsq =  db.executeQuery(instruq);
        
        if(rsq.next()){
        
            
            desq = rsq.getString("numero");
        
        }
        
        
        
        
       int lastnum = Integer.parseInt(desq);
       System.out.println("DESDE DAO INSTRUMENTO LASTNUM HOY " + lastnum); 
         
        
          String ins = "";
        String instruss = "select instrumento from instrumentos.calibracion where fecha = '%s'   ";
        instruss=String.format(instruss , dess);
        ResultSet rsss =  db.executeQuery(instruss);
        
        if(rsss.next()){
        
            ins = rsss.getString("instrumento");
        
        }
        
        
        
        System.out.println("DESDE DAO INSTRUMENTO HOY " + ins);  
        System.out.println("desde dao string " + a);   
         System.out.println("desde dao instrumento " + des  );   
          System.out.println("desde dao fecha " + fech  );
          
          
          
          
           String desx = "";
        String instrux = "select instrumento from instrumentos.calibracion where fecha = '%s' and instrumento = '%s' ";
         instrux=String.format(instrux,fech , des);
        ResultSet rsx =  db.executeQuery(instrux);
        
        if(rsx.next()){
        
            desx = rsx.getString("instrumento");
        
        }
          
          
           System.out.println("COMPROBAR SI CALIBRACION EXISTE " + desx  );
          
         //if(!dess.equals("") && ins.equals(des)){
          //if(!desx.isEmpty()){
          //  String sqls = "update instrumentos.calibracion set mediciones = case when instrumentos.calibracion.instrumento = '%s' and instrumentos.calibracion.fecha = '%s' then mediciones = mediciones + 1 else mediciones  end;";
      //String sqls = "update instrumentos.calibracion set mediciones = mediciones + 1 where fecha = '%s' ";
     //     sqls=String.format(sqls, fech);
      //  int count=db.executeUpdateWithKeys(sqls);
      //    }
          
         
         
     
        String sql = "insert into instrumentos.calibracion(instrumento , fecha, mediciones) " +
                 "values('%s','%s','%s' )";
        sql=String.format(sql,des,fech,1);
        int count=db.executeUpdateWithKeys(sql);
    
        
          
         
         
          
    
    }
    
    // calibracion y medida tienen que ser los valores reales para poder agregar
    public void MedidaAdd(Medida p ) throws Exception{
        
         
        
        
        
        String sql="insert into medida (calibracion , medida , referencia , lectura ) "+
                "values('%s','%s','%s','%s' )";
        sql=String.format(sql,1 , "44", p.getReferencia() , p.getLectura());
         int count=db.executeUpdateWithKeys(sql);
       
    }
    
  
    
    
     public void TipoInstrumentoAdd(TipoInstrumento p) throws Exception{
        String sql="insert into instrumentos.tipoinstrumento (codigo , nombre, unidad ) "+
                "values('%s','%s','%s' )";
        sql=String.format(sql,p.getCodigo(),p.getNombre(),p.getUnidad());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Tipo Instrumento ya existe");
        }
    }
     
      public List<instrumento.entidades.TipoInstrumento> TipoInstrumentoSearch(instrumento.entidades.TipoInstrumento filtro , String des){
     
          System.out.println("DESDE DAO AGREGAR TIPO INST DES " + des);
           
          List<instrumento.entidades.TipoInstrumento> resultado = new ArrayList<instrumento.entidades.TipoInstrumento>();
       
          if(!des.isEmpty()){
          
              try {
            String sql="select * from instrumentos.tipoinstrumento where nombre like '%" + des + "%'";
           // sql=String.format(sql, filtro.getCodigo(),filtro.getNombre(),filtro.getUnidad());
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(tipoInstrumento(rs));
            }
        } catch (SQLException ex) { }
          
          }
          
          if(des.isEmpty()){
          
          try {
            String sql="select * from instrumentos.tipoinstrumento";
            sql=String.format(sql,filtro.getCodigo(),filtro.getNombre(),filtro.getUnidad());
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(tipoInstrumento(rs));
            }
        } catch (SQLException ex) { }
        
     }
         return resultado; 
      }
      
       
      
      
      public List<instrumento.entidades.Instrumento> InstrumentoSearch(instrumento.entidades.Instrumento filtro , String des){
        
           
          List<instrumento.entidades.Instrumento> resultado = new ArrayList<instrumento.entidades.Instrumento>();
       
          
           if(!des.isEmpty()){
          
              try {
            String sql="select * from instrumentos.instrumento where descripcion like '%" + des + "%'";
           // sql=String.format(sql, filtro.getCodigo(),filtro.getNombre(),filtro.getUnidad());
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(instrumento(rs));
            }
        } catch (SQLException ex) { }
          
          }
          
          
          
          if(des.isEmpty()){
          
          try {
            String sql="select * from instrumentos.instrumento";
            sql=String.format(sql,filtro.getSerie(),filtro.getDescripcion(),filtro.getMinimo() , filtro.getMaximo() , filtro.getTolerancia());
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(instrumento(rs));
            }
        } catch (SQLException ex) { }
          
          
          }
        return resultado;
            
    }
        
        
        
       // 1 nov - string a es el numero de calibracion con el que se hace la consulta para obtener referencia y lectura  
          public List<instrumento.entidades.Medida> MedidaSearch(  String a ,instrumento.entidades.Medida filtro ) throws SQLException{
        
            System.out.println("desde DAO MEDIDA a " + a.toString());
            
         List<instrumento.entidades.Medida> resultado = new ArrayList<instrumento.entidades.Medida>();
        try {
             String sql="select  *from instrumentos.medida where calibracion = '%s' ";
          //  String sql="select distinct fecha , mediciones from calibracion inner join instrumentos.intrumento on instrumentos.calibracion.instrumento = '%s'";
            sql=String.format(sql,a);
            ResultSet rss =  db.executeQuery(sql);
            while (rss.next()) {
                resultado.add(medida(rss));
            }
        } catch (SQLException ex) { }
        return resultado;
        
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        public List<instrumento.entidades.Calibracion> CalibracionSearch(  String a ,instrumento.entidades.Calibracion filtro ) throws SQLException{
        
            System.out.println("desde DAO MEDIDA a " + a.toString());
            
            String des = "";
        String instru = "select serie from instrumentos.instrumento where descripcion = '%s' ";
        instru=String.format(instru,a);
        ResultSet rs =  db.executeQuery(instru);
        
        if(rs.next()){
        
            des = rs.getString("serie");
        
        }
        
        System.out.println("desde DAO MEDIDA SEARCH " + des);
        
        
         List<instrumento.entidades.Calibracion> resultado = new ArrayList<instrumento.entidades.Calibracion>();
        try {
             String sql="select  *from instrumentos.calibracion where instrumento = '%s' ";
          //  String sql="select distinct fecha , mediciones from calibracion inner join instrumentos.intrumento on instrumentos.calibracion.instrumento = '%s'";
            sql=String.format(sql,des);
            ResultSet rss =  db.executeQuery(sql);
            while (rss.next()) {
                resultado.add(calibracion(rss));
            }
        } catch (SQLException ex) { }
        return resultado;
        
         
            
    }
    
      
      public String getDescripcion(Instrumento p) throws SQLException{
      
          String des; 
          
         
            String sql="select * from instrumentos.instrumento where descripcion = '%s' ";
            sql=String.format(sql,p.getDescripcion());
            ResultSet rs =  db.executeQuery(sql);
            
            des = rs.getString(1);
            
            return des;
      
      }
      
      public List<String> TipoInstrumentoGetAllNames(){
        List<String> estados=new ArrayList<String>();
        try {
            String sql="select * from instrumentos.tipoinstrumento";
            
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                estados.add(tipoInstrumento(rs).getNombre());
            }
        } catch (SQLException ex) { }
        return estados;        
    }
    
      
      
      
      public void TipoInstrumentoUpdate(TipoInstrumento p) throws Exception{
          
          System.out.println("Desde dao nombre " + p.getNombre());
           System.out.println("Desde dao codigo " + p.getCodigo());
            System.out.println("Desde dao unidad " + p.getUnidad());
            
            
        String sql="update instrumentos.tipoinstrumento set nombre='%s',unidad='%s' " +
                "where codigo='%s'";
        sql=String.format(sql,p.getNombre() , p.getUnidad(), p.getCodigo());
        
         
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Tipo Instrumento no existe");
        }
         
    }
      
      
      
       public void MedidaUpdate(int calibracion , int medida , int referencia , int lectura  ) throws Exception{
        String sql="update instrumentos.medida set referencia = case when instrumentos.medida.calibracion = '%s' and instrumentos.medida.medida = '%s' then '%s' else referencia  end;";
        sql=String.format(sql,calibracion , medida , referencia);
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Medida no existe");
        }
        
        
        String sqll="update instrumentos.medida set lectura = case when instrumentos.medida.calibracion = '%s' and instrumentos.medida.medida = '%s' then '%s' else referencia  end;";
        sqll=String.format(sqll,calibracion , medida , lectura);
        
        int countt=db.executeUpdate(sqll);
        if (countt==0){
            throw new Exception("Medida no existe");
        }
        
    }
      
      
      
      
      
       public void InstrumentoUpdate(Instrumento p) throws Exception{
        String sql="update instrumentos.instrumento set descripcion='%s', minimo='%s', maximo='%s' , tolerancia='%s'" +
                "where serie='%s'";
        sql=String.format(sql,p.getDescripcion(),
                p.getMinimo(),p.getMaximo(),p.getTolerancia() , p.getSerie());
        
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Instrumento no existe");
        }
    }
     
      public void TipoInstrumentoDelete(TipoInstrumento p) throws Exception{
        String sql="delete from instrumentos.tipoinstrumento where codigo='%s'";
        sql = String.format(sql,p.getCodigo());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Tipo Instrumento no existe");
        }
    }
      
      
       public void CalibracionDelete(Calibracion p) throws Exception{
        
           System.out.println("DESDE DAO CALIBRACIONES fecha " + p.getFecha());
           System.out.println("DESDE DAO CALIBRACIONES instrumento " + p.getMediciones());
           
            String desx = "";
        String instrux = "select numero from instrumentos.calibracion where fecha = '%s' and mediciones = '%s' ";
         instrux=String.format(instrux,p.getFecha() , p.getMediciones());
        ResultSet rsx =  db.executeQuery(instrux);
        
        if(rsx.next()){
        
            desx = rsx.getString("numero");
        
        }
           
        System.out.println("DESDE DAO CALIBRACIONES DELETE " + desx);
           
           
           String sql="delete from instrumentos.medida where calibracion='%s'";
        sql = String.format(sql,desx);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Medida no existe");
        }
        
        
          String sqlq="delete from instrumentos.calibracion where numero='%s'";
        sqlq = String.format(sqlq,desx);
        int countq=db.executeUpdate(sqlq);
        if (countq==0){
            throw new Exception("Calibracion no existe");
        }
        
        
        
    }
      
      
      
      
      public void InstrumentoDelete(Instrumento p) throws Exception{
        String sql="delete from instrumentos.instrumento where serie='%s'";
        sql = String.format(sql,p.getSerie());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Instrumento no existe");
        }
    }
      
      
       
    private Medida medida(ResultSet rs){
        try {
            Medida p= new Medida();
            p.setCalibracion(rs.getInt("calibracion"));
            p.setLectura(rs.getInt("lectura"));
            p.setMedida(rs.getInt("medida"));
            p.setReferencia(rs.getInt("referencia")); 
         
             
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    private Calibracion calibracion(ResultSet rs){
        try {
            Calibracion p= new Calibracion();
            p.setFecha(rs.getDate("fecha"));
            p.setNumero(rs.getString("numero"));
            p.setMediciones(rs.getInt("mediciones"));
           
         
             
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
    
      
    
    
     
    private Instrumento instrumento(ResultSet rs){
        try {
            Instrumento p= new Instrumento();
            p.setSerie(rs.getString("serie"));
            p.setTipo(tipoInstrumento(rs));
            p.setDescripcion(rs.getString("descripcion") );
            p.setMinimo(rs.getInt("minimo"));
            p.setMaximo(rs.getInt("maximo"));
            p.setTolerancia(rs.getInt("tolerancia"));
             
            return p;
        } catch (SQLException ex) {
            return null;
        }
    }
   public  void close(){
    }
   
   
   
   
   

    
}