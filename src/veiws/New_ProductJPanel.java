/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package veiws;

import Login.CConectar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class New_ProductJPanel extends javax.swing.JPanel {

    private int idUsuario;

    Login.CConectar conec = new CConectar();
    Connection cn = conec.estableceConexion();

    public New_ProductJPanel(int id_usuario) {
        super();
        this.idUsuario = id_usuario;
        initComponents();
        MostrarProductos();
        Eliminar();
    }

    private void MostrarProductos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Cantidad");
        model.addColumn("Tipo");
        model.addColumn("Nombre");
        model.addColumn("Fecha");
        
        Tabla_Productos.setModel(model);
        ListSelectionModel selectionModel = Tabla_Productos.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        String consultasql = "SELECT * FROM productos WHERE id_usuario=? AND fecha >= ?";
        String data[] = new String[5];
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        try {
            PreparedStatement ps = cn.prepareStatement(consultasql);
            ps.setInt(1, idUsuario);
            ps.setTimestamp(2, fechaActual);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                data[0] = rs.getString(1);
                data[1] = rs.getString(4); // cantidad
                data[2] = rs.getString(3); // tipo
                data[3] = rs.getString(5); // nombre_producto
                data[4] = rs.getString(6); // fecha
                model.addRow(data);
            }
            // Ocultar la columna del ID
            TableColumnModel tcm = Tabla_Productos.getColumnModel();
            TableColumn tc = tcm.getColumn(0);
            tc.setWidth(0);
            tc.setMinWidth(0);
            tc.setMaxWidth(0);

        } catch (SQLException e) {
            System.out.println("fallo conexion base" + e);
        }
    }
    
    private void Eliminar(){
        
        int[] filasSeleccionadas = Tabla_Productos.getSelectedRows();
        
        if (filasSeleccionadas.length > 0) {
            
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas eliminar los productos seleccionados?", "Eliminar productos", JOptionPane.YES_NO_OPTION);
            
            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    for (int i = filasSeleccionadas.length - 1; i >= 0; i--) {
                        String valor = Tabla_Productos.getValueAt(filasSeleccionadas[i], 0).toString();
                        String consultasql = "DELETE FROM productos WHERE id_producto ='" + valor + "' ";
                        PreparedStatement ps = cn.prepareStatement(consultasql);
                        ps.executeUpdate();
                    }
                    
                    MostrarProductos();
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el registro: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Debe seleccionar una o más filas para eliminar.");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        cantidadLabel = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        SeparatorID = new javax.swing.JSeparator();
        TypeJlabel = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        NameJLabel = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        SeparatorName = new javax.swing.JSeparator();
        TimeJLabel = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        SeparatorTime = new javax.swing.JSeparator();
        BtnAgregar = new javax.swing.JButton();
        Btn_Eliminar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Productos = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        titulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Productos nuevos");

        cantidadLabel.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        cantidadLabel.setText("Cantidad");

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(204, 204, 204));
        txtCantidad.setText("Introduce cantidad");
        txtCantidad.setBorder(null);
        txtCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCantidadMousePressed(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        SeparatorID.setForeground(new java.awt.Color(0, 0, 0));

        TypeJlabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TypeJlabel.setText("Tipo");

        cmbTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medicamento", "Comestible", "Enlatados", "Otros..." }));
        cmbTipo.setToolTipText("");
        cmbTipo.setOpaque(true);
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        cmbTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoKeyPressed(evt);
            }
        });

        NameJLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        NameJLabel.setText("Nombre");

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(204, 204, 204));
        txtNombre.setText("Introduce el nombre");
        txtNombre.setBorder(null);
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNombreMousePressed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        SeparatorName.setForeground(new java.awt.Color(0, 0, 0));

        TimeJLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TimeJLabel.setText("Fecha caducidad");

        txtFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(204, 204, 204));
        txtFecha.setText("YY/MM/DD");
        txtFecha.setBorder(null);
        txtFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtFechaMousePressed(evt);
            }
        });
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaKeyTyped(evt);
            }
        });

        SeparatorTime.setForeground(new java.awt.Color(0, 0, 0));

        BtnAgregar.setBackground(new java.awt.Color(0, 134, 190));
        BtnAgregar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        BtnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregar.setText("Agregar");
        BtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        Btn_Eliminar.setBackground(new java.awt.Color(0, 134, 190));
        Btn_Eliminar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        Btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Eliminar.setText("Eliminar");
        Btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_EliminarActionPerformed(evt);
            }
        });

        Tabla_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(Tabla_Productos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeparatorID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TypeJlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeparatorName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TimeJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeparatorTime, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Eliminar)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cantidadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeparatorID, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TypeJlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NameJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeparatorName, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TimeJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeparatorTime, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantidadMousePressed

        if (txtCantidad.getText().equals("Introduce cantidad")){
            txtCantidad.setText("");
            txtCantidad.setForeground(Color.BLACK);
        }
        if(txtNombre.getText().isEmpty()){
            txtNombre.setText("Introduce el nombre");
            txtNombre.setForeground(Color.GRAY);
        }
        if(txtFecha.getText().isEmpty()){
            txtFecha.setText("YY/MM/DD");
            txtFecha.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_txtCantidadMousePressed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed

    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTipo.requestFocus();

        }

    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        boolean numero = key >= 48 && key <= 57;
        if(!numero){
            evt.consume();
        }
        if(txtCantidad.getText().trim().length()==20){
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbTipoActionPerformed

    private void cmbTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtNombre.requestFocus();
            txtNombre.setText("");
            txtNombre.setForeground(Color.black);

        }
    }//GEN-LAST:event_cmbTipoKeyPressed

    private void txtNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMousePressed

        if(txtNombre.getText().equals("Introduce el nombre")){
            txtNombre.setText("");
            txtNombre.setForeground(Color.BLACK);
        }
        if(txtCantidad.getText().isEmpty()){
            txtCantidad.setText("Introduce cantidad");
            txtCantidad.setForeground(Color.GRAY);
        }
        if(txtFecha.getText().isEmpty()){
            txtFecha.setText("YY/MM/DD");
            txtFecha.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_txtNombreMousePressed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtFecha.requestFocus();
            txtFecha.setText("");
            txtFecha.setForeground(Color.black);

        }
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtFechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFechaMousePressed

        if(txtFecha.getText().equals("YY/MM/DD")){
            txtFecha.setText("");
            txtFecha.setForeground(Color.BLACK);
        }
        if(txtNombre.getText().isEmpty()){
            txtNombre.setText("Introduce el nombre");
            txtNombre.setForeground(Color.GRAY);
        }
        if(txtCantidad.getText().isEmpty()){
            txtCantidad.setText("Introduce cantidad");
            txtCantidad.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_txtFechaMousePressed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed

    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtFechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnAgregar.doClick();
        }
    }//GEN-LAST:event_txtFechaKeyPressed

    private void txtFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyReleased
        // TODO add your handling code here:
        String text = txtFecha.getText();
        if (text.length() == 2 || text.length() == 5) {
            text += "/";
            txtFecha.setText(text);
            txtFecha.setCaretPosition(text.length());
        }
        if (text.length() > 10 && evt.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            txtFecha.setText(text.substring(0, 10));
            txtFecha.setCaretPosition(10);
        }
    }//GEN-LAST:event_txtFechaKeyReleased

    private void txtFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnAgregar.doClick();
        }
    }//GEN-LAST:event_txtFechaKeyTyped

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        String tipo = cmbTipo.getSelectedItem().toString();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        String nombre = txtNombre.getText();
        String fecha = txtFecha.getText();

        try {
            Login.CConectar objetoConexion = new Login.CConectar();
            PreparedStatement ps = null;

            String consulta = "insert into productos (id_usuario, tipo, cantidad, nombre_producto, fecha) values (?, ?, ?, ?, ?)";
            ps = objetoConexion.estableceConexion().prepareStatement(consulta);

            ps.setInt(1, idUsuario);
            ps.setString(2, tipo);
            ps.setInt(3, cantidad);
            ps.setString(4, nombre);
            ps.setString(5, fecha);

            ps.execute(); ps.close();

            txtCantidad.requestFocus();
            txtCantidad.setText("");
            txtNombre.setText("Introduce el nombre");
            txtNombre.setForeground(Color.GRAY);
            txtFecha.setText("YY/MM/DD");
            txtFecha.setForeground(Color.GRAY);

            MostrarProductos();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void Btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EliminarActionPerformed
        // TODO add your handling code here:
        Eliminar();
        txtCantidad.requestFocus();
        txtCantidad.setText("");
        txtCantidad.setForeground(Color.BLACK);
    }//GEN-LAST:event_Btn_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnAgregar;
    private javax.swing.JButton Btn_Eliminar;
    private javax.swing.JLabel NameJLabel;
    private javax.swing.JSeparator SeparatorID;
    private javax.swing.JSeparator SeparatorName;
    private javax.swing.JSeparator SeparatorTime;
    public javax.swing.JTable Tabla_Productos;
    private javax.swing.JLabel TimeJLabel;
    private javax.swing.JLabel TypeJlabel;
    private javax.swing.JLabel cantidadLabel;
    public javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel titulo;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

}
