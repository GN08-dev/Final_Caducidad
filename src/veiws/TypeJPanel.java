package veiws;

import Login.CConectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TypeJPanel extends javax.swing.JPanel {

    private int idUsuario;
    
    Login.CConectar conec = new CConectar();
    Connection cn = conec.estableceConexion();
    public TypeJPanel(int id_usuario) {
        super();
        this.idUsuario = id_usuario;
        initComponents();
        MostrarProductos();
        // System.out.println("usuario "+ idusuario);
        lista();
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

    
    private void lista(){
        try {
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        
        Login.CConectar objetoConexion = new Login.CConectar();
        PreparedStatement ps = null;
        
        String consulta = "SELECT DISTINCT tipo FROM productos WHERE id_usuario = ?;";
        ps = objetoConexion.estableceConexion().prepareStatement(consulta);
        ps.setInt(1, idUsuario);
        ResultSet resultado = ps.executeQuery();

        // Crear un HashSet para almacenar los elementos únicos
        Set<String> elementosUnicos = new HashSet<>();
        while (resultado.next()) {
            elementosUnicos.add(resultado.getString("tipo"));
        }
        
        // Agregar los elementos del HashSet al modelo del JComboBox
        for (String elemento : elementosUnicos) {
            modelo.addElement(elemento);
        }

        cmbTipo.setModel(modelo);

        ps.close();
    } catch (SQLException ex) {
        // Manejar excepción
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
                    // Actualizar la tabla después de eliminar los registros
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
        Type_ProductJLabel = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        Btn_Buscar = new javax.swing.JButton();
        Btn_Eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Productos = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Type_ProductJLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        Type_ProductJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Type_ProductJLabel.setText("Tipos ");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        Btn_Buscar.setBackground(new java.awt.Color(0, 134, 190));
        Btn_Buscar.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        Btn_Buscar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Buscar.setText("Buscar");
        Btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BuscarActionPerformed(evt);
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
                "ID", "Tipo", "Nombre", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla_Productos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Type_ProductJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Btn_Eliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Buscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Type_ProductJLabel)
                        .addGap(32, 32, 32)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbTipoActionPerformed

    private void Btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_BuscarActionPerformed
        // TODO add your handling code here:
        String tipoSeleccionado = cmbTipo.getSelectedItem().toString();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(Tabla_Productos.getModel());
        Tabla_Productos.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(tipoSeleccionado, 2));
    }//GEN-LAST:event_Btn_BuscarActionPerformed

    private void Btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EliminarActionPerformed
        Eliminar();
        String tipoSeleccionado = cmbTipo.getSelectedItem().toString();
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(Tabla_Productos.getModel());
        Tabla_Productos.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter(tipoSeleccionado, 2));
    }//GEN-LAST:event_Btn_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Buscar;
    private javax.swing.JButton Btn_Eliminar;
    private javax.swing.JTable Tabla_Productos;
    private javax.swing.JLabel Type_ProductJLabel;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
