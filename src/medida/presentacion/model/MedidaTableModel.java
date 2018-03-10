/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medida.presentacion.model;

import instrumento.entidades.Medida;
import instrumento.entidades.TipoInstrumento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class MedidaTableModel extends AbstractTableModel{
    List<Medida> rows;
    int[] cols;

    public  MedidaTableModel(int[] cols, List<Medida> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int col){
        return colNames[cols[col]];
    }

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            case 0: case 1: case 2:
                return Integer.class;
            default: return super.getColumnClass(col);
        }    
    }    
    
    public int getRowCount() {
        return rows.size();
    }
    
    public Object getValueAt(int row, int col) {
        Medida obj = rows.get(row);
        switch (cols[col]){
            
            case CALIBRACION : return obj.getCalibracion();
            case MEDIDA: return obj.getMedida();
            case REFERENCIA: return obj.getReferencia();
            case LECTURA: return obj.getLectura();
            default: return "";
        }
    }    

    public boolean isCellEditable(int row, int col) {
        switch (cols[col]){
            case 0: return false;
            default: return true;
        }
    }	

    public void setValueAt(Object value, int row, int col) {
        Medida obj = rows.get(row);
        switch (cols[col]){
            case MEDIDA: {obj.setMedida( (int)value); break;}
            case REFERENCIA: {obj.setReferencia((int)value); break;}
            case LECTURA: {obj.setLectura( (int)value); break;}
            case CALIBRACION : {obj.setCalibracion((int)value); break;}
            default: ;
        }        
    }   
    
    public Medida getRowAt(int row) {
        return rows.get(row);
    }
    
    public static final int MEDIDA=0;
    public static final int REFERENCIA=1;
    public static final int LECTURA=2;
    public static final int CALIBRACION=3;

    
    String[] colNames = new String[11];
    private void initColNames(){
        colNames[MEDIDA]= "medida";
        colNames[REFERENCIA]= "Referencia";
        colNames[LECTURA]= "Lectura";
        colNames[CALIBRACION]= "calibracion";
    }
            
}
