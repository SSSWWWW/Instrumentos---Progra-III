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
import instrumento.entidades.TipoInstrumento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
 
public class TipoInstrumentosServicio {
   private final String tabla = "TIPO_INSTRUMENTO";
   public void guardar( TipoInstrumento   tipoinstrumento) throws SQLException{
    
       
             try
    {
      // create a mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/INSTRUMENTOS";
      Class.forName(myDriver);
                 // create a sql date object so we can use it in our INSERT statement
                 try (Connection conn = DriverManager.getConnection(myUrl, "stefvn", "goldy2001")) {
                     // create a sql date object so we can use it in our INSERT statement
                     Calendar calendar = Calendar.getInstance();
                     java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
                     
                     // the mysql insert statement
                     String query = " insert into TIPO_INSTRUMENTO (nombre, unidad)"
                             + " values (?, ?)";
                     
                     // create the mysql insert preparedstatement
                     PreparedStatement preparedStmt = conn.prepareStatement(query);
                     preparedStmt.setString (1, tipoinstrumento.getNombre());
                     preparedStmt.setString (2, tipoinstrumento.getUnidad());
                     
                     
                     // execute the preparedstatement
                     preparedStmt.execute();
                 }
    }
    catch (  ClassNotFoundException | SQLException e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
       
    
  }
       
    
  
       
       
       
   
   public TipoInstrumento recuperarPorId(Connection conexion, String codigo) throws SQLException {
      TipoInstrumento tipoinstrumento = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT nombre, unidad  FROM " + this.tabla + " WHERE codigo = ?" );
         consulta.setString(1, codigo);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            tipoinstrumento = new TipoInstrumento(codigo, resultado.getString("nombre"), resultado.getString("unidad"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return tipoinstrumento;
   }
   public void eliminar(Connection conexion, TipoInstrumento tipoinstrumento) throws SQLException{
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE codigo = ?");
         consulta.setString(1, tipoinstrumento.getCodigo());
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   public List<TipoInstrumento> recuperarTodas(Connection conexion) throws SQLException{
      List<TipoInstrumento> tipoinstrumentos = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT codigo, nombre, unidad  FROM " + this.tabla + " ORDER BY unidad");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            tipoinstrumentos.add(new TipoInstrumento(resultado.getString("id_tarea"), resultado.getString("titulo"), resultado.getString("descripcion") ));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return tipoinstrumentos;
   }
}