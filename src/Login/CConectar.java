
package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConectar {
    Connection conectar;
    
    String usuario="root";
    String contraseña="emilioswer23";
    String bd="caducidad";
    String ip="localhost";
    String puerto="3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"problema conexion"+ e.toString());
        }
        return conectar;
}
}
