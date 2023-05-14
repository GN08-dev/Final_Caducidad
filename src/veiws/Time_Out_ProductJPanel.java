/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package veiws;
import Login.CConectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Time_Out_ProductJPanel extends javax.swing.JPanel {

    private int idUsuario;
    
    Login.CConectar conec = new CConectar();
    Connection cn = conec.estableceConexion();
    
    public Time_Out_ProductJPanel(int id_usuario) {
        super();
        this.idUsuario = id_usuario;
        initComponents();
        MostrarProductos();
        Eliminar();
    }
    
    private void MostrarProductos(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Cantidad");
        model.addColumn("Tipo");
        model.addColumn("Nombre");
        model.addColumn("Fecha");
        Tabla_Productos.setModel(model);

        String consultasql = "SELECT * FROM productos WHERE id_usuario = ? AND fecha < ?";
        String data[] = new String[5];
        try {
            PreparedStatement ps = cn.prepareStatement(consultasql);
            ps.setInt(1, idUsuario);
            ps.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Fecha actual
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                data[0] = rs.getString(1); // id
                data[1] = rs.getString(4); // cantidad
                data[2] = rs.getString(3); // tipo
                data[3] = rs.getString(5); // nombre
                data[4] = rs.getString(6); // fecha
                model.addRow(data);
            }

            TableColumnModel tcm = Tabla_Productos.getColumnModel();
            TableColumn tc = tcm.getColumn(0);
            tc.setWidth(0);
            tc.setMinWidth(0);
            tc.setMaxWidth(0);

        } catch (Exception e) {
            System.out.println("Error al mostrar los productos: " + e.getMessage());
        }
    }

    private void Eliminar() {
        int[] filasSeleccionadas = Tabla_Productos.getSelectedRows();
        
        if (filasSeleccionadas.length > 0) {
            
            int respuesta = JOptionPane.showConfirmDialog(this, 
      "¿Estás seguro que deseas eliminar los productos seleccionados?",
      "Eliminar productos", JOptionPane.YES_NO_OPTION);
            
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
        jLabel1 = new javax.swing.JLabel();
        Btn_Eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Productos = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Producto Caducado");

        Btn_Eliminar.setBackground(new java.awt.Color(0, 134, 190));
        Btn_Eliminar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        Btn_Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Eliminar.setText("Eliminar ");
        Btn_Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                "ID", "Tipo", "Nombre", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla_Productos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void Btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EliminarActionPerformed
        Eliminar();

    }//GEN-LAST:event_Btn_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Eliminar;
    private javax.swing.JTable Tabla_Productos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
