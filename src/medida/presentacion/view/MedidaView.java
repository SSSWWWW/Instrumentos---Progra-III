/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medida.presentacion.view;

import calibracion.presentacion.controller.CalibracionesController;
import instrumento.entidades.Medida;
import instrumento.entidades.TipoInstrumento;
import java.util.Observable;
import medida.presentacion.controller.MedidaController;
import medida.presentacion.model.MedidaModel;
import tipoinstrumentos.presentacion.controller.TipoInstrumentoController;
import tipoinstrumentos.presentacion.model.TipoInstrumentoModel;

/**
 *
 * @author pc
 */
public class MedidaView extends javax.swing.JDialog implements java.util.Observer {

    /**
     * Creates new form MedidaView
     */
    public MedidaView(java.awt.Frame parent, boolean modal) {
        super(parent,modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lecturaField = new javax.swing.JTextField();
        idLbl = new javax.swing.JLabel();
        nombreLbl = new javax.swing.JLabel();
        sexoLbl = new javax.swing.JLabel();
        guardarFld = new javax.swing.JButton();
        cancelarFld = new javax.swing.JButton();
        InstrumentoField = new javax.swing.JTextField();
        referenciaFld = new javax.swing.JTextField();

        setTitle("Medida");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        idLbl.setText("Instrumento");

        nombreLbl.setText("Referencia");

        sexoLbl.setText("Lectura");

        guardarFld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/view/icons/save.png"))); // NOI18N
        guardarFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarFldActionPerformed(evt);
            }
        });

        cancelarFld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/presentation/view/icons/cancel.png"))); // NOI18N
        cancelarFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarFldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sexoLbl)
                    .addComponent(nombreLbl)
                    .addComponent(idLbl))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(guardarFld, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(cancelarFld, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(referenciaFld, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(lecturaField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(InstrumentoField))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLbl)
                    .addComponent(InstrumentoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLbl)
                    .addComponent(referenciaFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sexoLbl)
                    .addComponent(lecturaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarFld)
                    .addComponent(cancelarFld, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarFldActionPerformed
        this.controller.guardar();
    }//GEN-LAST:event_guardarFldActionPerformed

    private void cancelarFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarFldActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_cancelarFldActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

       

        

// TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MedidaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MedidaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MedidaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MedidaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField InstrumentoField;
    public javax.swing.JButton cancelarFld;
    public javax.swing.JButton guardarFld;
    public javax.swing.JLabel idLbl;
    public javax.swing.JTextField lecturaField;
    public javax.swing.JLabel nombreLbl;
    public javax.swing.JTextField referenciaFld;
    public javax.swing.JLabel sexoLbl;
    // End of variables declaration//GEN-END:variables

    
     MedidaController controller;
    MedidaModel model;
    
      CalibracionesController control;
    
    
    
     public MedidaController getController() {
        return controller;
    }

    public void setController(MedidaController controller) {
        this.controller = controller;
    }

    public MedidaModel getModel() {
        return model;
    }

    public void setModel(MedidaModel model) {
        this.model = model;
        model.addObserver(this);
    }
    
     
    
    @Override
    public void update(Observable o, Object o1) {
        
         Medida medidaCurrent = model.getCurrent();
        
       
       
       InstrumentoField.setText(String.valueOf(medidaCurrent.getLectura()));
       if (model.getErrores().get("Numero")!=null){
            
            idLbl.setToolTipText(model.getErrores().get("Numero"));
        }
        else{
            idLbl.setBorder(null);
            idLbl.setToolTipText("");
        }
       
         referenciaFld.setText(String.valueOf(medidaCurrent.getReferencia()));
       if (model.getErrores().get("Referencia")!=null){
            
            idLbl.setToolTipText(model.getErrores().get("Referencia"));
        }
        else{
            idLbl.setBorder(null);
            idLbl.setToolTipText("");
        }
       
         lecturaField.setText(String.valueOf(medidaCurrent.getLectura()));
       if (model.getErrores().get("Lectura")!=null){
            
            idLbl.setToolTipText(model.getErrores().get("Lectura"));
        }
        else{
            idLbl.setBorder(null);
            idLbl.setToolTipText("");
        }
        
    }
}
