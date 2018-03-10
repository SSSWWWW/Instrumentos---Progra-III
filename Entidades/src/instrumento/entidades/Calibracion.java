/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumento.entidades;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Calibracion {
    
    String numero;
    Instrumento instrumento;
    Date fecha;
    int mediciones;

    public Calibracion(String numero, Instrumento instrumento, Date fecha, int mediciones) {
        this.numero = numero;
        this.instrumento = instrumento;
        this.fecha = fecha;
        this.mediciones = mediciones;
    }

     public Calibracion(  Date fecha, int mediciones , String numero) {
        
        this.fecha = fecha;
        this.mediciones = mediciones;
        this.numero = numero;
    }
    
    
    public Calibracion() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getMediciones() {
        return mediciones;
    }

    public void setMediciones(int mediciones) {
        this.mediciones = mediciones;
    }
    
    
    
    
}
