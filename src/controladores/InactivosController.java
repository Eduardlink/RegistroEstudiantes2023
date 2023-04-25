/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Test.CRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelos.DataManager;

/**
 *
 * @author edu
 */
public class InactivosController {

    DataManager manejador;

    public InactivosController() {
        manejador = new DataManager();
    }

    public DefaultTableModel cargarEstudiantesInactivos() {
        try {
            String[] columnas = {
                "Cedula", "Nombre", "Apellido", "Telefono", "Direccion"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT * FROM estudiantes WHERE estado = 0;");
            String[] registro = new String[5];
            while (datos.next()) {
                registro[0] = datos.getString("cedula");
                registro[1] = datos.getString("nombre");
                registro[2] = datos.getString("apellido");
                registro[3] = datos.getString("telefono");
                registro[4] = datos.getString("direccion");
                modeloTabla.addRow(registro);
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }

    public void restaurar(String tabla, String cedula) {
        String sql = "UPDATE " + tabla + " SET estado='1' WHERE cedula='" + cedula + "';";
        new DataManager().ejecutarConsulta(sql);
    }

    public DefaultTableModel cargarUsuariosInactivos() {
        try {
            String[] columnas = {
                "Usuario", "Cedula", "Nombre", "Apellido", "Telefono", "Direccion", "Tipo Usuario"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT * FROM usuarios WHERE estado = 0;");
            String[] registro = new String[7];
            while (datos.next()) {
                registro[0] = datos.getString("usuario");
                registro[1] = datos.getString("cedula");
                registro[2] = datos.getString("nombre");
                registro[3] = datos.getString("apellido");
                registro[4] = datos.getString("telefono");
                registro[5] = datos.getString("direccion");
                registro[6] = datos.getString("root");
                if (registro[6].equals("1")) {
                    registro[6] = "Administrador/a";
                } else {
                    registro[6] = "Secretario/a";
                }
                modeloTabla.addRow(registro);
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }
}
