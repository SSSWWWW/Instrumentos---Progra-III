/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoinstrumentos.presentacion.model;

 
import instrumento.entidades.TipoInstrumento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class TipoInstrumentoTableModel extends AbstractTableModel{
    List<TipoInstrumento> rows;
    int[] cols;

    public  TipoInstrumentoTableModel(int[] cols, List<TipoInstrumento> rows){
        this.cols=cols;
        this.rows = rows;
        initColNames();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int col){
        return colNames[cols[col]];
    }

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
          //  case CODIGO: return Icon.class; solo en caso de lo que se recibe es una imagen
          //  case NOMBRE: return Boolean.class;
           // case UNIDAD: return Icon.class;
            default: return super.getColumnClass(col); // default para strings
        }    
    }    
    
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        TipoInstrumento instrumento = rows.get(row);
        switch (cols[col]){
            case CODIGO: return instrumento.getCodigo();
            case NOMBRE: return instrumento.getNombre();
            case UNIDAD : return instrumento.getUnidad();
           
             
            default: return "";
        }
    }    

    

    public TipoInstrumento getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int CODIGO=0;
    public static final int NOMBRE=1;
    public static final int UNIDAD=2;
      
    
    String[] colNames = new String[12];
    private void initColNames(){
        colNames[CODIGO]= "Codigo";
        colNames[NOMBRE]= "Nombre";
        colNames[UNIDAD]= "Unidad";
         
    }
            
}
