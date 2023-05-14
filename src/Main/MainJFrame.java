package Main;

import Login.CConectar;
import Login.loguin;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import veiws.New_ProductJPanel;
import veiws.Main_Jpanel;
import veiws.Proximos_Caducar;
import veiws.Time_Out_ProductJPanel;
import veiws.TypeJPanel;

/**
 *
 * @author monse
 */
public class MainJFrame extends javax.swing.JFrame {
    private int id_usuario;
    
    Login.CConectar conec = new CConectar();
    Connection cn = conec.estableceConexion();
    
    public MainJFrame(int id_usuario) {
        initComponents();
        this.id_usuario = id_usuario;

        this.setLocationRelativeTo(null);
        setDate();
        InitContent();
        Alerta();
    }
    private void InitContent() {
    veiws.Main_Jpanel panel = new Main_Jpanel();
    panel.setPreferredSize(new Dimension(750, 490));
    
    contenido.setLayout(new BorderLayout());
    contenido.add(panel, BorderLayout.CENTER);
    contenido.revalidate();
    contenido.repaint();
}
    
    private void Alerta() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.DATE, 10); // Agrega 10 días a la fecha actual

        String consulsql = "SELECT COUNT(*) FROM productos WHERE id_usuario=? AND fecha BETWEEN ? AND ?";
        try {
            PreparedStatement ps = cn.prepareStatement(consulsql);
            ps.setInt(1, id_usuario);
            ps.setTimestamp(2, new Timestamp(new Date().getTime()));
            ps.setTimestamp(3, new Timestamp(calendar.getTimeInMillis()));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "Tiene " + count + " productos próximos a caducar.");
                    veiws.Proximos_Caducar prox = new Proximos_Caducar(id_usuario);
                    //veiws.Proximos_Caducar prox = new Proximos_Caducar(id_usuario);
                    prox.setSize(750, 490);
                    prox.setLocation(0, 0);

                    contenido.removeAll();
                    contenido.add(prox, BorderLayout.CENTER);
                    contenido.revalidate();
                    contenido.repaint();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar productos próximos a caducar: " + e.getMessage());
        }
    }
    
    
    
    private void setDate(){
    LocalDate now = LocalDate.now();
    Locale spanishLocale = new Locale("es", "ES");
    dateText.setText(now.format(DateTimeFormatter.ofPattern("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy", spanishLocale)));
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        MenuPanel = new javax.swing.JPanel();
        MaintButton = new javax.swing.JButton();
        New_ProductButton = new javax.swing.JButton();
        Type_ProductsButton = new javax.swing.JButton();
        Producto_Proximo_Acaducar = new javax.swing.JButton();
        Time_Out_ProductButton = new javax.swing.JButton();
        Btn_Descargar = new javax.swing.JButton();
        Btn_cerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textSeparator = new javax.swing.JSeparator();
        dateText = new javax.swing.JLabel();
        contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(255, 255, 255));

        MenuPanel.setBackground(new java.awt.Color(13, 71, 161));

        MaintButton.setBackground(new java.awt.Color(21, 101, 192));
        MaintButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        MaintButton.setForeground(new java.awt.Color(255, 255, 255));
        MaintButton.setText("Principal");
        MaintButton.setBorder(new javax.swing.border.MatteBorder(null));
        MaintButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MaintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintButtonActionPerformed(evt);
            }
        });

        New_ProductButton.setBackground(new java.awt.Color(21, 101, 192));
        New_ProductButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        New_ProductButton.setForeground(new java.awt.Color(255, 255, 255));
        New_ProductButton.setText("Captura de productos");
        New_ProductButton.setBorder(new javax.swing.border.MatteBorder(null));
        New_ProductButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        New_ProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                New_ProductButtonActionPerformed(evt);
            }
        });

        Type_ProductsButton.setBackground(new java.awt.Color(21, 101, 192));
        Type_ProductsButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Type_ProductsButton.setForeground(new java.awt.Color(255, 255, 255));
        Type_ProductsButton.setText("Categoria");
        Type_ProductsButton.setBorder(new javax.swing.border.MatteBorder(null));
        Type_ProductsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Type_ProductsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type_ProductsButtonActionPerformed(evt);
            }
        });

        Producto_Proximo_Acaducar.setBackground(new java.awt.Color(21, 101, 192));
        Producto_Proximo_Acaducar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Producto_Proximo_Acaducar.setForeground(new java.awt.Color(255, 255, 255));
        Producto_Proximo_Acaducar.setText("Producto a caducar");
        Producto_Proximo_Acaducar.setBorder(new javax.swing.border.MatteBorder(null));
        Producto_Proximo_Acaducar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Producto_Proximo_Acaducar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Producto_Proximo_AcaducarActionPerformed(evt);
            }
        });

        Time_Out_ProductButton.setBackground(new java.awt.Color(21, 101, 192));
        Time_Out_ProductButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Time_Out_ProductButton.setForeground(new java.awt.Color(255, 255, 255));
        Time_Out_ProductButton.setText("Producto caducado");
        Time_Out_ProductButton.setBorder(new javax.swing.border.MatteBorder(null));
        Time_Out_ProductButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Time_Out_ProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Time_Out_ProductButtonActionPerformed(evt);
            }
        });

        Btn_Descargar.setBackground(new java.awt.Color(21, 101, 192));
        Btn_Descargar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Btn_Descargar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Descargar.setText("Descargar PDF");
        Btn_Descargar.setBorder(new javax.swing.border.MatteBorder(null));
        Btn_Descargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DescargarActionPerformed(evt);
            }
        });

        Btn_cerrar.setBackground(new java.awt.Color(21, 101, 192));
        Btn_cerrar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        Btn_cerrar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_cerrar.setText("Cerrar secion");
        Btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_cerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MaintButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(New_ProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Type_ProductsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Producto_Proximo_Acaducar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Time_Out_ProductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Btn_Descargar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Btn_cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(120, 120, 120))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(MaintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(New_ProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Type_ProductsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Producto_Proximo_Acaducar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Time_Out_ProductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Descargar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(Btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel1.setBackground(new java.awt.Color(25, 118, 210));

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Time Out Home");

        textSeparator.setForeground(new java.awt.Color(255, 255, 255));

        dateText.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        dateText.setForeground(new java.awt.Color(255, 255, 255));
        dateText.setText("Hoy es {dayname} {day} de {month} de {year}");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(textSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addGap(246, 246, 246))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(dateText)
                .addContainerGap())
        );

        contenido.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenido, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MaintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintButtonActionPerformed
        // TODO add your handling code here:
        Main_Jpanel Prin = new Main_Jpanel();
        Prin.setSize(750, 490);
        Prin.setLocation(0, 0);

        contenido.removeAll();
        contenido.add(Prin, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }//GEN-LAST:event_MaintButtonActionPerformed

    private void New_ProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_New_ProductButtonActionPerformed
        // TODO add your handling code here:
        New_ProductJPanel Prod = new New_ProductJPanel(this.id_usuario);
        Prod.setSize(750, 490);
        Prod.setLocation(0,0);

        contenido.removeAll();
        contenido.add(Prod, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }//GEN-LAST:event_New_ProductButtonActionPerformed

    private void Type_ProductsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Type_ProductsButtonActionPerformed
        // TODO add your handling code here:
        TypeJPanel Tipos = new TypeJPanel(this.id_usuario);
        Tipos.setSize(750, 490);
        Tipos.setLocation(0,0);

        contenido.removeAll();
        contenido.add(Tipos, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }//GEN-LAST:event_Type_ProductsButtonActionPerformed

    private void Producto_Proximo_AcaducarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Producto_Proximo_AcaducarActionPerformed
        // TODO add your handling code here:
        veiws.Proximos_Caducar prox = new Proximos_Caducar(id_usuario);
        //veiws.Proximos_Caducar prox = new Proximos_Caducar(id_usuario);
        prox.setSize(750, 490);
        prox.setLocation(0,0);

        contenido.removeAll();
        contenido.add(prox, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }//GEN-LAST:event_Producto_Proximo_AcaducarActionPerformed

    private void Time_Out_ProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Time_Out_ProductButtonActionPerformed
        // TODO add your handling code here:
        Time_Out_ProductJPanel caducados= new Time_Out_ProductJPanel(id_usuario);
        caducados.setSize(750, 490);
        caducados.setLocation(0,0);

        contenido.removeAll();
        contenido.add(caducados, BorderLayout.CENTER);
        contenido.revalidate();
        contenido.repaint();
    }//GEN-LAST:event_Time_Out_ProductButtonActionPerformed

    private void Btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_cerrarActionPerformed
        // TODO add your handling code here:
        this.dispose(); // cierra la ventana actual

        // crea una nueva instancia del JFrame de Login y la hace visible
        Login.loguin log = new loguin();
        log.setVisible(true);
    }//GEN-LAST:event_Btn_cerrarActionPerformed

    private void Btn_DescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DescargarActionPerformed
        // TODO add your handling code here:
        CReporte objetoLogin = new CReporte(id_usuario);
        objetoLogin.Reporte();
    }//GEN-LAST:event_Btn_DescargarActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton Btn_Descargar;
    private javax.swing.JButton Btn_cerrar;
    private javax.swing.JButton MaintButton;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JButton New_ProductButton;
    private javax.swing.JButton Producto_Proximo_Acaducar;
    private javax.swing.JButton Time_Out_ProductButton;
    private javax.swing.JButton Type_ProductsButton;
    private javax.swing.JPanel contenido;
    private javax.swing.JLabel dateText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator textSeparator;
    // End of variables declaration//GEN-END:variables
}
