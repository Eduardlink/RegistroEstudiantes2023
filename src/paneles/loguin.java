/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import controladores.LoguinController;
import interfazGrafica.InicioPrincipal;
import interfazGrafica.paginaPrincipal_Admin;
import interfazGrafica.paginaPrincipal_Secre;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelos.DataManager;

/**
 *
 * @author spc
 */
public class loguin extends javax.swing.JPanel {

    private InicioPrincipal loguin;

    //Varible que permite iniciar el dispositivo de lector de huella conectado
// con sus distintos metodos.
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

//Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
// y poder estimar la creacion de un template de la huella para luego poder guardarla
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

//Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
// o verificarla con alguna guardada en la BD
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

//Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
// necesarias de la huella si no ha ocurrido ningun problema
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";

    /**
     * Creates new form docente
     */
    public loguin(InicioPrincipal loguin) {
        initComponents();
        jlblVer.setVisible(true);
        jlblOcultar.setVisible(false);
        this.loguin = loguin;
        jlblHuella.setIcon(new ImageIcon(getClass().getResource("/imagenesFrames/huellaNegro.png")));
        //jtxtaStatus.setEditable(false);
        Iniciar();
        start();
        EstadoHuellas();
    }

    protected void Iniciar() {
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            EnviarTexto("La Huella Digital ha sido Capturada");
                            ProcesarCaptura(e.getSample());
                        } catch (IOException ex) {
                            Logger.getLogger(loguin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El Sensor de Huella Digital esta Activado o Conectado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El Sensor de Huella Digital esta Desactivado o no Conectado");
                    }
                });
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
                        jlblHuella.setIcon(new ImageIcon(getClass().getResource("/imagenesFrames/huellaAzul.png")));
                        repaint();
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("El dedo ha sido quitado del Lector de Huella");
                        jlblHuella.setIcon(new ImageIcon(getClass().getResource("/imagenesFrames/huellaNegro.png")));
                        repaint();
                    }
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter() {
            public void errorReader(final DPFPErrorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        EnviarTexto("Error: " + e.getError());
                    }
                });
            }
        });
    }

    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    public void ProcesarCaptura(DPFPSample sample) throws IOException {
        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
        if (featuresinscripcion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

                // Dibuja la huella dactilar capturada.
                //Image image = CrearImagenHuella(sample);
                //DibujarHuella(image);
                //btnVerificar.setEnabled(true);
                //btnIdentificar.setEnabled(true);
                String[] pass = identificarHuella();
                if (pass[2].equals("1")) {
                    if(pass[3].equals("1")){
                    if (pass[1].equals("1")) {
                        //admin
                        stop();
                        paginaPrincipal_Admin adm = new paginaPrincipal_Admin();
                        adm.setVisible(true);
                        this.loguin.setVisible(false);
                    } else {
                        //comon user
                        stop();
                        paginaPrincipal_Secre u = new paginaPrincipal_Secre(pass[0]);
                        this.loguin.setVisible(false);
                        u.setVisible(true);
                    }                        
                    }else{
                        JOptionPane.showMessageDialog(this, "Usted no puede ingresar al sistema.\nContactese con administracion parasolucionar el problema.");
                    }
                } else {
                    EnviarTexto("Usuario no encontrado, Intente de nuevo");
                }
            } catch (DPFPImageQualityException ex) {
                System.err.println("Error: " + ex.getMessage());
            } finally {
                EstadoHuellas();
                // Comprueba si la plantilla se ha creado.
                switch (Reclutador.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
                        stop();
                        setTemplate(Reclutador.getTemplate());
                        EnviarTexto("La Plantilla de la Huella ha Sido Creada, ya puede Verificarla o Identificarla");
                        //btnIdentificar.setEnabled(false);
                        //btnVerificar.setEnabled(false);
                        //btnGuardar.setEnabled(true);
                        //btnGuardar.grabFocus();
                        break;

                    case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                        Reclutador.clear();
                        stop();
                        EstadoHuellas();
                        setTemplate(null);
                        //JOptionPane.showMessageDialog(CapturaHuella.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
            }
        }

    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public String[] identificarHuella() throws IOException {
        DataManager manejador = new DataManager();
        //[usuario,root,huella]
        String[] datos = {"", "", "0",""};
        try {
            //Establece los valores para la sentencia SQL
            //Connection c = con.conectar();

            //Obtiene todas las huellas de la bd
            String sql = "SELECT usuario,root,huella,estado FROM usuarios;";
            ResultSet rs = manejador.obtenerDatos(sql);
            //PreparedStatement identificarStmt = c.prepareStatement("SELECT huenombre,huehuella FROM somhue");
            //ResultSet rs = identificarStmt.executeQuery();

            //Si se encuentra el nombre en la base de datos
            while (rs.next()) {
                //Lee la plantilla de la base de datos
                byte templateBuffer[] = rs.getBytes("huella");
                datos[0] = rs.getString("usuario");
                datos[1] = String.valueOf(rs.getInt("root"));
                datos[3] = String.valueOf(rs.getInt("estado"));
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);

                // Compara las caracteriticas de la huella recientemente capturda con la
                // alguna plantilla guardada en la base de datos que coincide con ese tipo
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                //compara las plantilas (actual vs bd)
                //Si encuentra correspondencia dibuja el mapa
                //e indica el nombre de la persona que coincidió.
                if (result.isVerified()) {
                    //crea la imagen de los datos guardado de las huellas guardadas en la base de datos
                    //JOptionPane.showMessageDialog(null, "Las huella capturada es de " + nombre, "Verificacion de Huella", JOptionPane.INFORMATION_MESSAGE);
                    datos[2] = "1";
                    return datos;
                }
            }
            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje

            JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella, Ingrese su usuario y contraseña", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            jlblHuella.setIcon(new ImageIcon(getClass().getResource("/imagenesFrames/huellaRojo.png")));
            repaint();
            setTemplate(null);
        } catch (SQLException e) {
            //Si ocurre un error lo indica en la consola
            System.err.println("Error al identificar huella dactilar." + e.getMessage());
        } finally {
            //con.desconectar();
            manejador.cerrar();
        }
        return datos;
    }

    public void EstadoHuellas() {
        EnviarTexto("Muestra de Huellas Necesarias para Guardar Template " + Reclutador.getFeaturesNeeded());
    }

    public void start() {
        Lector.startCapture();
        EnviarTexto("Utilizando el Lector de Huella Dactilar ");
    }

    public void stop() {
        Lector.stopCapture();
        EnviarTexto("No se está usando el Lector de Huella Dactilar ");
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

    public void EnviarTexto(String string) {
        //jtxtaStatus.append(string + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jtxtuser = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jtxtpass = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jbtnInicio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlblHuella = new javax.swing.JLabel();
        jlblOcultar = new javax.swing.JLabel();
        jlblVer = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1250, 590));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 30)); // NOI18N
        jLabel3.setText("Usuario");

        jtxtuser.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jtxtuser.setBorder(null);
        jtxtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtuserActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 30)); // NOI18N
        jLabel1.setText("Contraseña");

        jtxtpass.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 24)); // NOI18N
        jtxtpass.setBorder(null);

        jbtnInicio.setBackground(new java.awt.Color(236, 71, 71));
        jbtnInicio.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jbtnInicio.setForeground(new java.awt.Color(255, 255, 255));
        jbtnInicio.setText("Iniciar sesion");
        jbtnInicio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jbtnInicio.setBorderPainted(false);
        jbtnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jbtnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnInicioMouseExited(evt);
            }
        });
        jbtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInicioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 1, 40)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Bienvenido");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/Loguin_Docente.png"))); // NOI18N

        jlblOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/hide50.png"))); // NOI18N
        jlblOcultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblOcultarMouseClicked(evt);
            }
        });

        jlblVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/view50.png"))); // NOI18N
        jlblVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlblVerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jtxtpass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jbtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblOcultar)
                                            .addComponent(jlblVer))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jlblHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel4)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblHuella, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jtxtuser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15)
                                .addComponent(jbtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblVer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(151, 151, 151)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtuserActionPerformed

    private void jbtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInicioActionPerformed
        if (jtxtuser.getText().trim().isEmpty() || String.valueOf(jtxtpass.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Espacios invalidos o incompletos");
        } else {
            iniciarSesion();
        }

    }//GEN-LAST:event_jbtnInicioActionPerformed

    private void jbtnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnInicioMouseEntered
        jbtnInicio.setBackground(Color.BLACK);
    }//GEN-LAST:event_jbtnInicioMouseEntered

    private void jbtnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnInicioMouseExited
        jbtnInicio.setBackground(new Color(236, 71, 71));
    }//GEN-LAST:event_jbtnInicioMouseExited

    private void jlblVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblVerMouseClicked
        jlblVer.setVisible(false);
        jlblOcultar.setVisible(true);
        jtxtpass.setEchoChar((char)0);
    }//GEN-LAST:event_jlblVerMouseClicked

    private void jlblOcultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlblOcultarMouseClicked
        jlblVer.setVisible(true);
        jlblOcultar.setVisible(false);
        jtxtpass.setEchoChar('•');
    }//GEN-LAST:event_jlblOcultarMouseClicked
    /*
    public void validarBoton() {
        LocalTime horaActual = LocalTime.now();
        LocalTime inicio = LocalTime.parse("06:50");
        LocalTime fin = LocalTime.parse("07:15");
        LocalTime incioTarde = LocalTime..parse("14:00");
        LocalTime finTarde = LocalTime.parse("20:00");

        boolean hora1 = horaActual.isAfter(inicio);
        boolean hora2 = horaActual.isBefore(fin);

        //  JOptionPane.showMessageDialog(null, hora1);
        // JOptionPane.showMessageDialog(null, hora2);
        if (jbtnInicio.isSelected()) {
            jbtnInicio.setBackground(new Color(32, 146, 25));
            jbtnInicio.setText("Registrar Entrada");
        } else {
            jbtnInicio.setBackground(new Color(180, 35, 35));
            jbtnInicio.setText("Registrar Salida");
        }

    }
     */
    public void iniciarSesion() {
        LoguinController controlador = new LoguinController();
        boolean[] verificado = controlador.verificar(jtxtuser.getText(), String.valueOf(jtxtpass.getPassword()));
        System.out.println(verificado[0] + "--" + verificado[1]);
        if (verificado[0] == false && verificado[1] == false) {
            JOptionPane.showMessageDialog(this, "No existe el usuario" + " " + jtxtuser.getText());
        }
        if (verificado[0] == true && verificado[1] == false) {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta intente de nuevo");
        }
        if (verificado[0] == true && verificado[1] == true) {
            if (verificado[2] == true) {
                //admin
                paginaPrincipal_Admin adm = new paginaPrincipal_Admin();
                adm.setVisible(true);
                this.loguin.setVisible(false);
                stop();
            } else {
                //comon user
                paginaPrincipal_Secre u = new paginaPrincipal_Secre(jtxtuser.getText());
                this.loguin.setVisible(false);
                u.setVisible(true);
                stop();
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnInicio;
    private javax.swing.JLabel jlblHuella;
    private javax.swing.JLabel jlblOcultar;
    private javax.swing.JLabel jlblVer;
    private javax.swing.JPasswordField jtxtpass;
    private javax.swing.JTextField jtxtuser;
    // End of variables declaration//GEN-END:variables
}
