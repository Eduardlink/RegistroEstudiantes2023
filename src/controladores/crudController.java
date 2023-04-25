/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.table.DefaultTableModel;
import modelos.DataManager;
import Test.CRUD;

/**
 *
 * @author Pc
 */
public class crudController {

    private DataManager manejador;
    private EncriptadorAES encrypt;

    public crudController() {
        encrypt = new EncriptadorAES();
        manejador = new DataManager();
    }

    //ESTUDIANTES
    //create
    public void agregarEstudiante(String cedula, String nombre, String apellido, String telefono, String direccion) {

        String sql = "INSERT INTO estudiantes(cedula,nombre,apellido,telefono,direccion,estado) "
                + "VALUES ('" + cedula + "','" + nombre + "','" + apellido + "','" + telefono + "','" + direccion + "','1');";
        manejador.ejecutarConsulta(sql);
    }

    //read
    public DefaultTableModel cargarTablaEstudiantes() {
        try {
            String[] columnas = {
                "Cedula", "Nombre", "Apellido", "Telefono", "Direccion"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT cedula, nombre, apellido, telefono, direccion,estado FROM estudiantes WHERE estado='1';");
            String[] registro = new String[6];
            while (datos.next()) {
                registro[0] = datos.getString("cedula");
                registro[1] = datos.getString("nombre");
                registro[2] = datos.getString("apellido");
                registro[3] = datos.getString("telefono");
                registro[4] = datos.getString("direccion");
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }

    //update
    public void updateEstudiante(String cedula, String nombre, String apellido, String telefono, String direccion) {
        manejador.ejecutarConsulta("UPDATE estudiantes SET nombre='" + nombre + "', apellido = '" + apellido + "',"
                + "telefono = '" + telefono + "', direccion ='" + direccion + "' WHERE cedula='" + cedula + "';");
    }

    //delete
    public void eliminarEstudiante(String cedula) {
        manejador.ejecutarConsulta("UPDATE estudiantes SET estado='0' WHERE cedula='" + cedula + "';");
    }

    //restaurar estudiante
    public void actualizarEstadoEstudiante(String cedula) {
        manejador.ejecutarConsulta("UPDATE estudiantes SET estado= 1 WHERE cedula='" + cedula + "';");
    }

    //buscarEstudiante
    public DefaultTableModel buscarEstudiante(String cedula) {
        try {
            String[] columnas = {
                "Cedula", "Nombre", "Apellido", "Telefono", "Direccion"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT cedula, nombre, apellido, telefono, direccion,estado FROM estudiantes WHERE cedula like '" + cedula + "%' AND estado='1';");
            String[] registro = new String[6];
            while (datos.next()) {
                registro[0] = datos.getString("cedula");
                registro[1] = datos.getString("nombre");
                registro[2] = datos.getString("apellido");
                registro[3] = datos.getString("telefono");
                registro[4] = datos.getString("direccion");
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //USUARIOS
    //create
    public String crearUsuario(String nombre, String apellido, String cedula) {
        nombre = nombre.toLowerCase().substring(0, 1);
        apellido = apellido.toLowerCase();
        cedula = cedula.substring(6, 10);
        return nombre + apellido + cedula;
    }

    public void agregarUsuario(String[] usuario) {
        //usuario[]=[cedula],[usuario],[clave],[nombre],[apellido],[telefono],[direccion],[root]
        /*
        try {
            System.out.println(usuario[2]);
            usuario[2] = new EncriptadorAES().encriptar(usuario[2], "SisTech");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        String sql = "UPDATE usuarios SET usuario='" + usuario[1] + "',clave='" + usuario[2] + "',nombre='" + usuario[3] + "',apellido='" + usuario[4]
                + "',telefono='" + usuario[5] + "',direccion='" + usuario[6] + "',root='" + usuario[7] + "',estado='1' WHERE cedula='" + usuario[0] + "';";
        manejador.ejecutarConsulta(sql);
    }

    public boolean existeUsuario(String cedula, String nombre, String apellido) {
        String userName = crearUsuario(nombre, apellido, cedula);
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado("SELECT cedula FROM usuarios WHERE estado='1' AND usuario='" + userName + "';");
        if (datos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    //read
    public DefaultTableModel cargarTablaUser() {
        try {
            String[] columnas = {
                "Usuario", "Cedula", "Nombre", "Apellido", "Telefono", "Direccion", "Tipo Usuario"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT * FROM usuarios;");
            String[] registro = new String[7];
            while (datos.next()) {
                registro[0] = datos.getString("usuario");
                registro[1] = datos.getString("cedula");
                registro[2] = datos.getString("nombre");
                registro[3] = datos.getString("apellido");
                registro[4] = datos.getString("telefono");
                registro[5] = datos.getString("direccion");
                registro[6] = datos.getString("root");

                if (registro[6].equals("0")) {
                    registro[6] = "Secretario/a";
                } else if (registro[6].equals("1")) {
                    registro[6] = "Administrador/a";
                }

                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error: " + ex);
            return null;
        }
    }

    //UPDATE USER
    public void actualizarUsuario(String[] usuario) {
        //usuario[]=[cedula],[usuario],[clave],[nombre],[apellido],[telefono],[direccion],[root]
      /*  try {
            System.out.println(usuario[2]);
            usuario[2] = new EncriptadorAES().encriptar(usuario[2], "SisTech");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
        }
*/
        
        String sql = "UPDATE usuarios SET usuario='" + usuario[1] + "',clave='" + usuario[2] + "',nombre='" + usuario[3] + "',apellido='" + usuario[4]
                + "',telefono='" + usuario[5] + "',direccion='" + usuario[6] + "',root='" + usuario[7] + "',estado='1' WHERE cedula='" + usuario[0] + "';";
        manejador.ejecutarConsulta(sql);
    }

    //DELETE USER
    public void eliminarUsuario(String cedula) {
        new DataManager().ejecutarConsulta("DELETE FROM usuarios WHERE cedula='" + cedula + "';");
    }

    public void removerUsuario(String cedula) {
        String sql = "UPDATE usuarios SET estado='0' WHERE cedula='" + cedula + "';";
        manejador.ejecutarConsulta(sql);
    }
    
    public void eliminarDatosIncompletos(){
        String sql = "DELETE FROM usuarios WHERE usuario IS NULL;";
        manejador.ejecutarConsulta(sql);
    }

    public String[] buscarCedula(String cedula) {
        try {
            ResultSet datos = new DataManager().obtenerDatos("SELECT * FROM usuarios  WHERE cedula = '" + cedula + "' AND estado='1';");
            String[] usuario = new String[8];
            while (datos.next()) {
                usuario[0] = datos.getString("cedula");
                usuario[1] = datos.getString("nombre");
                usuario[2] = datos.getString("apellido");
                usuario[3] = datos.getString("telefono");
                usuario[4] = datos.getString("direccion");
                usuario[5] = datos.getString("usuario");
                usuario[6] = datos.getString("clave");
                usuario[7] = datos.getString("root");
            }
            return usuario;
        } catch (Exception ex) {
            Logger.getLogger(crudController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            return null;
        }
    }

    //BUSCAR USUARIO
    //buscarEstudiante
    public DefaultTableModel buscarUsuario(String cedula) {
        try {
            String[] columnas = {
                "Usuario", "Cedula", "Nombre", "Apellido", "Telefono", "Direccion", "Tipo Usuario"
            };
            DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
            DataManager manejador = new DataManager();
            ResultSet datos = manejador.obtenerDatos("SELECT * FROM usuarios WHERE cedula like '" + cedula + "%' AND estado='1';");
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
                if (datos.getString("estado").equals("1")) {
                    modeloTabla.addRow(registro);
                }
            }
            manejador.cerrar();
            return modeloTabla;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean existeUsuario(String cedula, String tablaBD) {
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado("SELECT cedula FROM " + tablaBD + " WHERE cedula='" + cedula + "'");
        if (datos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

        public boolean existeUsuarioSinHuella(String cedula, String tablaBD) {
        ArrayList<Object> datos = new ArrayList<>();
        datos = manejador.resultado("SELECT cedula FROM " + tablaBD + " WHERE cedula='" + cedula + "' AND usuario IS NULL");
        if (datos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
    

}
