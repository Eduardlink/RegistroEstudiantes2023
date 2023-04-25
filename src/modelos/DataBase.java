/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pc
 */
public class DataBase {

    private String url;
    private String driver;
    private String user;
    private String pass;
    private Connection conexion;

    public DataBase() {
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost/registroestudiantes";
        this.user="root";
        this.pass="";
    }

    protected void conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexion = DriverManager.getConnection(this.url,this.user,this.pass);
            if (!this.conexion.isClosed()) {
                    //System.out.println("Conectado");
            }
        } catch (Exception ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void cerrar(){
        try {
            if(!this.conexion.isClosed()){
                this.conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Connection getConexion() {
        return conexion;
    }
    
}
