
package Login;

import Main.MainJFrame;
import java.awt.Color;
import java.awt.Window;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Garces
 */
public class CLogin {
    private  int idUsuario;
    public int  id(String EmailTxt , String contraseña){
        
        int id = 0;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Login.CConectar objetoConexion = new Login.CConectar();

            String consulta = "select id_usuario from usuario where correo=(?) and contraseña=(?)";
            ps= objetoConexion.estableceConexion().prepareStatement(consulta);

            ps.setString(1, EmailTxt);
            ps.setString(2, contraseña);

            rs = ps.executeQuery();
           
            while(rs.next()){
                id = rs.getInt("id_usuario");
            }
            
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Error" + e.toString());

        }
         return id;
        
    }
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void validaUsuario(JTextField EmailTxt , JPasswordField Password_txt){
        try {
            ResultSet rs = null;
            PreparedStatement ps=null;

            Login.CConectar objetoConexion = new Login.CConectar();

            String consulta = "select * from usuario where correo=(?) and contraseña=(?)";
            ps= objetoConexion.estableceConexion().prepareStatement(consulta);

            String contra = String.valueOf(Password_txt.getPassword());

            ps.setString(1, EmailTxt.getText());
            ps.setString(2, contra);

            rs = ps.executeQuery();
            
            if(rs.next()){
                //System.out.println("Id" + rs.getInt("id_usuario"));
                
                Main.MainJFrame objetoMenu = new MainJFrame(rs.getInt("id_usuario"));
                objetoMenu.setVisible(true);

                Window window = SwingUtilities.getWindowAncestor(EmailTxt);
                window.dispose();
               
                
            }else{  
                JOptionPane.showMessageDialog(null,"el usuario es incorrecto");
                EmailTxt.requestFocus();
                EmailTxt.setText("");
                EmailTxt.setForeground(Color.BLACK);
                Password_txt.setText("******************");
                Password_txt.setForeground(Color.GRAY);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error"+e.toString());
        }
    } 
}
