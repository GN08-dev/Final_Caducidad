package Main;

import Login.CConectar;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Calendar;
import javax.swing.JFileChooser;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CReporte {

    private int idUsuario;
    private DefaultTableModel modelVigentes, modelCaducados,modelProximosAcaducar;
    Login.CConectar conec = new CConectar();
    Connection cn = conec.estableceConexion();

    public CReporte(int idUsuario) {
        this.idUsuario = idUsuario;

    }

    public void Reporte() {
        modelVigentes = new DefaultTableModel();
        modelVigentes.addColumn("ID");
        modelVigentes.addColumn("Cantidad");
        modelVigentes.addColumn("Tipo");
        modelVigentes.addColumn("Nombre");
        modelVigentes.addColumn("Fecha de Vencimiento");

        modelCaducados = new DefaultTableModel();
        modelCaducados.addColumn("ID");
        modelCaducados.addColumn("Cantidad");
        modelCaducados.addColumn("Tipo");
        modelCaducados.addColumn("Nombre");
        modelCaducados.addColumn("Fecha de Caducidad");
        
        modelProximosAcaducar = new DefaultTableModel();
        modelProximosAcaducar.addColumn("ID");
        modelProximosAcaducar.addColumn("Cantidad");
        modelProximosAcaducar.addColumn("Tipo");
        modelProximosAcaducar.addColumn("Nombre");
        modelProximosAcaducar.addColumn("Fecha de caducidad");

        // Consultar productos vigentes
        String consulsql = "SELECT * FROM productos WHERE id_usuario=? AND fecha >=?";
        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        try {
            PreparedStatement ps = cn.prepareStatement(consulsql);
            ps.setInt(1, idUsuario);
            ps.setTimestamp(2, fechaActual);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] data = new String[5];
                data[0] = rs.getString(1);
                data[1] = rs.getString(4); // cantidad
                data[2] = rs.getString(3); // tipo
                data[3] = rs.getString(5); // nombre_producto
                data[4] = rs.getString(6); // fecha_vencimiento
                modelVigentes.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar productos vigentes: " + e.getMessage());
        }

        // Consultar productos caducados
        consulsql = "SELECT * FROM productos WHERE id_usuario=? AND fecha <?";
        try {
            PreparedStatement ps = cn.prepareStatement(consulsql);
            ps.setInt(1, idUsuario);
            ps.setTimestamp(2, fechaActual);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] data = new String[5];
                data[0] = rs.getString(1);
                data[1] = rs.getString(4); // cantidad
                data[2] = rs.getString(3); // tipo
                data[3] = rs.getString(5); // nombre_producto
                data[4] = rs.getString(6); // fecha_caducidad
                modelCaducados.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar productos caducados: " + e.getMessage());
        }
        //consultar los proximos a caducar
        
        consulsql = "SELECT * FROM productos WHERE id_usuario=? AND fecha BETWEEN ? AND ?";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(fechaActual.getTime());
        calendar.add(Calendar.DATE, 10); // Agrega 10 días a la fecha actua
        try {
            PreparedStatement ps = cn.prepareStatement(consulsql);
            ps.setInt(1, idUsuario);
            ps.setTimestamp(2, fechaActual);
            ps.setTimestamp(3, new Timestamp(calendar.getTimeInMillis()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] data = new String[5];
                data[0] = rs.getString(1);
                data[1] = rs.getString(4); // cantidad
                data[2] = rs.getString(3); // tipo
                data[3] = rs.getString(5); // nombre_producto
                data[4] = rs.getString(6); // fecha_caducidad
                modelProximosAcaducar.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar productos caducados: " + e.getMessage());
        }
        
        
        
        
        // Crear el objeto JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Definir el título del diálogo de selección
        fileChooser.setDialogTitle("Guardar reporte");

        // Mostrar el diálogo de selección de archivo
        int userSelection = fileChooser.showSaveDialog(null);

        // Si el usuario selecciona un archivo y presiona "Guardar"
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener la ruta del archivo seleccionado
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Verificar si la extensión ".pdf" está incluida en el nombre de archivo,
            // y añadirla si no está presente
            if (!filePath.endsWith(".pdf")) {
                filePath += ".pdf";
            }

            Document document = new Document(PageSize.A4);
            try {
                // Escribir el documento en un archivo
                PdfWriter.getInstance(document, new FileOutputStream(filePath));

                // Abrir el documento
                document.open();

                // Agregar un título
                document.add(new Paragraph("Reporte de productos"));

                // Agregar una tabla para los productos vigentes
                document.add(new Paragraph("\n\nProductos vigentes\n\n"));
                PdfPTable tableVigentes = new PdfPTable(5);
                tableVigentes.addCell("ID");
                tableVigentes.addCell("Cantidad");
                tableVigentes.addCell("Tipo");
                tableVigentes.addCell("Nombre");
                tableVigentes.addCell("Fecha de Vencimiento");
                for (int i = 0; i < modelVigentes.getRowCount(); i++) {
                    for (int j = 0; j < modelVigentes.getColumnCount(); j++) {
                        tableVigentes.addCell(modelVigentes.getValueAt(i, j).toString());
                    }
                }
                document.add(tableVigentes);

                // Agregar una tabla para los productos caducados
                document.add(new Paragraph("\n\nProductos caducados\n\n"));
                PdfPTable tableCaducados = new PdfPTable(5);
                tableCaducados.addCell("ID");
                tableCaducados.addCell("Cantidad");
                tableCaducados.addCell("Tipo");
                tableCaducados.addCell("Nombre");
                tableCaducados.addCell("Fecha de Caducidad");
                for (int i = 0; i < modelCaducados.getRowCount(); i++) {
                    for (int j = 0; j < modelCaducados.getColumnCount(); j++) {
                        tableCaducados.addCell(modelCaducados.getValueAt(i, j).toString());
                    }
                }
                document.add(tableCaducados);
                
                //nuevo documento de tabla
                document.add(new Paragraph("\n\nProducto próximo a caducar\n\n"));
                PdfPTable tablaProximosAcaducar = new PdfPTable(5);
                tablaProximosAcaducar.addCell("ID");
                tablaProximosAcaducar.addCell("Cantidad");
                tablaProximosAcaducar.addCell("Tipo");
                tablaProximosAcaducar.addCell("Nombre");
                tablaProximosAcaducar.addCell("Fecha de Caducidad");
                for (int i = 0; i < modelProximosAcaducar.getRowCount(); i++) {
                    for (int j = 0; j < modelProximosAcaducar.getColumnCount(); j++) {
                        tablaProximosAcaducar.addCell(modelProximosAcaducar.getValueAt(i, j).toString());
                    }
                }
                document.add(tablaProximosAcaducar);
                // Cerrar el documento
                document.close();
                JOptionPane.showMessageDialog(null, "Se ha generado el reporte correctamente.");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage());
            }

        }
       

    }
}
