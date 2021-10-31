/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos_sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Donald
 */
public class Metodos_sql {
    
    public static ConexionDB conexion = new ConexionDB();
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero = 0;
    
    
    public int guardar(int user_id,String firstname,String lastname,String user_name,String user_password_hash,String user_mail)
    {
        int resultado = 0;
        Connection conexion = null; 
        
        String sentencia_guardar= ("INSERT into users(user_id,firstname,lastname,user_name,user_password_hash,user_mail)"
                + "values(?,?,?,?,?,?)");
        try{
            conexion = ConexionDB.conectar();
        sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
        sentencia_preparada.setInt(1,user_id);
        sentencia_preparada.setString(2,firstname);
        sentencia_preparada.setString(3,lastname);
        sentencia_preparada.setString(4,user_name); 
        sentencia_preparada.setString(5,user_password_hash);
        sentencia_preparada.setString(6,user_mail);
        
        resultado = sentencia_preparada.executeUpdate();
        sentencia_preparada.close();    
     
        conexion.close();
        
        }catch (Exception e){
        
        }
        
        return resultado;
    
    }
    
    public static String buscarNombre(String correo){
       String busqueda_nombre = null;
       Connection conexion = null;
    try{
           conexion = ConexionDB.conectar();
           String sentencia_buscar = ("Nombre, Apellido FROM users where correo='"+correo + "'");
           sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
           resultado = sentencia_preparada.executeQuery();
           if (resultado.next()){
               String Nombre = resultado.getString("Nombre");
               String Apellido = resultado.getString("Apellidos");
               busqueda_nombre = (Nombre +""+ Apellido);
           }
           
           conexion.close();
       
       }catch(Exception e){
           System.out.println(e);
           
       }
        return busqueda_nombre;
    }
    
    public static String BuscarUsuarioRegistrado(String Correo,String Password){
        String busqueda_usuarios = null;
        Connection conexion = null;
        
        try {
            conexion = ConexionDB.conectar();
            String sentencia_buscar_usuario = ("SELECT Nombre,Correo,Password FROM users where correo='"+Correo+"'&&Password ='"+Password+"'"); 
            
            resultado = sentencia_preparada.executeQuery();
            if(resultado.next()){
                busqueda_usuarios = "usuario encontrado";
            }
            else{
                busqueda_usuarios = "Usuario no encontrado";
        }
            conexion.close();
        } catch (Exception e){
                System.out.println(e);
       
        }
        return busqueda_usuarios;
    }
    
    
}

