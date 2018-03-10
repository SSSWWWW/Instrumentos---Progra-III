/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrumento.entidades;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Medida {
    
   
    int calibracion;
    int medida;
    int referencia;
    int lectura;

    public Medida(int calibracion, int medida, int referencia, int lectura) {
        this.calibracion = calibracion;
        this.medida = medida;
        this.referencia = referencia;
        this.lectura = lectura;
    }
    
     public Medida( ) {
       
    }

    public int getCalibracion() {
        return calibracion;
    }

    public void setCalibracion(int calibracion) {
        this.calibracion = calibracion;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public int getLectura() {
        return lectura;
    }

    public void setLectura(int lectura) {
        this.lectura = lectura;
    }
    
    
    
    
}
