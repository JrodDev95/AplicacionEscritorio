    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos_sql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Donald
 */
public class ConexionDB {

    public static String url = "jdbc:mysql://localhost//dbproyectophp";
    public static String usuario = "root";
    public static String contraseña = "root";
    public static String clase = "com.mysql.jdbc.Driver";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            System.out.print("Conexion Establecida");
        } catch (Exception e) {

        }
        return conexion;
    }

}
