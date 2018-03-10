/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationpresentacion;

import calibracion.presentacion.controller.CalibracionController;
import calibracion.presentacion.controller.CalibracionesController;
 
import calibracion.presentacion.model.CalibracionModel;
import calibracion.presentacion.model.CalibracionesModel;
 
import calibracion.presentacion.view.CalibracionView;
import calibracion.presentacion.view.CalibracionesView;
 
import instrumento.presentacion.controller.InstrumentoController;
import instrumento.presentacion.controller.InstrumentosController;
import instrumento.presentacion.model.InstrumentoModel;
import instrumento.presentacion.model.InstrumentosModel;
import instrumento.presentacion.view.InstrumentoView;
import instrumento.presentacion.view.InstrumentosView;
import instrumentos.logic.Model;
import java.sql.SQLException;
import medida.presentacion.controller.MedidaController;
import medida.presentacion.controller.MedidasController;
import medida.presentacion.model.MedidaModel;
import medida.presentacion.model.MedidasModel;
import medida.presentacion.view.MedidaView;
import medida.presentacion.view.MedidasView;
import tipoinstrumentos.presentacion.controller.TipoInstrumentoController;
import tipoinstrumentos.presentacion.controller.TipoInstrumentosController;
import tipoinstrumentos.presentacion.model.TipoInstrumentoModel;
import tipoinstrumentos.presentacion.model.TipoInstrumentosModel;
import tipoinstrumentos.presentacion.view.TipoInstrumentoView;
import tipoinstrumentos.presentacion.view.TipoInstrumentosView;

/**
 *
 * @author pc
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        
        
        principal appView = new principal();
        appView.setVisible(true);
        
        Model domainModel = Model.instance();
        
         TipoInstrumentosModel tipoInstrumentosModel = new TipoInstrumentosModel();
         TipoInstrumentoModel tipoInstrumentoModel = new TipoInstrumentoModel();
         
         InstrumentosModel InstrumentosModel = new InstrumentosModel();
         InstrumentoModel InstrumentoModel = new InstrumentoModel();
         
         CalibracionesModel calibracionesModel = new CalibracionesModel();
         CalibracionModel calibracionModel = new CalibracionModel();
         
         MedidasModel medidasModel = new MedidasModel();
         MedidaModel medidaModel = new MedidaModel();
         
       
         
         
         
         
         MedidasView MedidasView= new MedidasView();
        MEDIDAS_VIEW=MedidasView;
        MedidasController medidascontroller = new MedidasController(MedidasView,medidasModel , domainModel);
        MedidasView.setVisible(false);
        
         MedidaView MedidaView= new MedidaView(appView,true);
        MEDIDA_VIEW=MedidaView;
         MedidaController medidacontroller = new MedidaController(MedidaView,medidaModel , domainModel);
         
         
          CalibracionesView CalibracionesView= new CalibracionesView(appView,false);
        CALIBRACIONES_VIEW=CalibracionesView;
        CalibracionesController calibracionescontroller = new CalibracionesController(CalibracionesView,calibracionesModel , domainModel);
       CalibracionesView.setVisible(false);
        
         CalibracionView CalibracionView= new CalibracionView(appView,true);
        CALIBRACION_VIEW=CalibracionView;
         CalibracionController calibracioncontroller = new CalibracionController(CalibracionView,calibracionModel , domainModel);
        CalibracionView.setVisible(false);
         
         
       InstrumentosView InstrumentosView= new InstrumentosView();
        INSTRUMENTOS_VIEW=InstrumentosView;
        InstrumentosController instrumentoscontroller = new InstrumentosController(InstrumentosView,InstrumentosModel , domainModel);
       InstrumentosView.setVisible(false);
        
        InstrumentoView InstrumentoView= new InstrumentoView(appView,true);
        INSTRUMENTO_VIEW=InstrumentoView;
        InstrumentoController instrumentocontroller = new InstrumentoController(InstrumentoView,InstrumentoModel , domainModel);
         
         
         
        TipoInstrumentosView tipoInstrumentosView= new TipoInstrumentosView();
        TIPOINSTRUMENTOS_VIEW=tipoInstrumentosView;
        TipoInstrumentosController personascontroller = new TipoInstrumentosController(tipoInstrumentosView,tipoInstrumentosModel , domainModel);
      tipoInstrumentosView.setVisible(false);
        
         TipoInstrumentoView tipoInstrumentoView= new TipoInstrumentoView(appView,true);
        TIPOINSTRUMENTO_VIEW=tipoInstrumentoView;
        TipoInstrumentoController tipoinstrumentocontroller = new TipoInstrumentoController(tipoInstrumentoView,tipoInstrumentoModel , domainModel);
         

          appView.contenedor.add(MedidasView);
        
          appView.contenedor.add(InstrumentosView);
          appView.contenedor.add(tipoInstrumentosView);
           
        
         
     }
    
   
    
    public static TipoInstrumentosView TIPOINSTRUMENTOS_VIEW;
    public static TipoInstrumentoView TIPOINSTRUMENTO_VIEW;
    public static InstrumentosView INSTRUMENTOS_VIEW;
    public static InstrumentoView INSTRUMENTO_VIEW;
    
    public static CalibracionView CALIBRACION_VIEW;
    public static CalibracionesView CALIBRACIONES_VIEW;
    
     public static MedidaView MEDIDA_VIEW;
    public static MedidasView  MEDIDAS_VIEW;
    
    
     public static  final int  MODO_AGREGAR=0;
    public static final int MODO_EDITAR=1;
    public static final int MODO_BORRAR=2;
}
