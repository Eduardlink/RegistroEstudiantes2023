/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazGrafica;

import java.sql.*;
import javax.swing.JOptionPane;
import paneles.CambiaPanel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import menu.MenuItem;
import paneles.crudEstudiante;

/**
 *
 * @author spc
 */
public class paginaPrincipal_Admin extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;
    int xMouse, yMouse;

    /**
     * Creates new form Login
     */
    public paginaPrincipal_Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        new CambiaPanel(jpPanelUsuarios, new paneles.Bienvenida());
        execute();

    }

    private void execute() {

        //----------------------------ICONOS----------------------------------------------------------
        //Iconos Menu Principal
        ImageIcon iconoInicio = new ImageIcon(getClass().getResource("/imagenesFrames/casa-inteligente.png"));
        ImageIcon iconoAdmin = new ImageIcon(getClass().getResource("/imagenesFrames/admin.png"));
        ImageIcon iconoReportes = new ImageIcon(getClass().getResource("/imagenesFrames/dashboard.png"));
        ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/imagenesFrames/salir.png"));

        //Iconos Menu Admin
        ImageIcon iconoUsuarios = new ImageIcon(getClass().getResource("/imagenesFrames/agregar-usuario.png"));
        ImageIcon iconoEstudiantes = new ImageIcon(getClass().getResource("/imagenesFrames/estudiantes.png"));
        ImageIcon iconoEstudiantesInactivos = new ImageIcon(getClass().getResource("/imagenesFrames/est_inac.png"));
        ImageIcon iconoUsuariosInactivos = new ImageIcon(getClass().getResource("/imagenesFrames/usuario-inac.png"));

        //Iconos Reportes
        ImageIcon iconoReporteGeneral = new ImageIcon(getClass().getResource("/imagenesFrames/general.png"));
        ImageIcon iconoReporteCedula = new ImageIcon(getClass().getResource("/imagenesFrames/porcedula.png"));
        ImageIcon iconoReporteDetalleApellido = new ImageIcon(getClass().getResource("/imagenesFrames/detalleApellido.png"));
        ImageIcon iconoReporteGrafico = new ImageIcon(getClass().getResource("/imagenesFrames/RGrafico.png"));

        //----------------------------MENU ITEM----------------------------------------------------------
        //Menu Admin
        MenuItem menuAgregarUsuarios = new MenuItem(iconoUsuarios, "Usuarios", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.crudAdmin());
            }
        });
        MenuItem menuAgregarEstudiantes = new MenuItem(iconoEstudiantes, "Estudiantes", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.crudEstudiante());
            }
        });
        MenuItem menuUsuarioInactivo = new MenuItem(iconoUsuariosInactivos, "Usuarios Inactivos", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.usuariosInactivos());
            }
        });
        MenuItem menuEstudianteInactivo = new MenuItem(iconoEstudiantesInactivos, "Estudiantes Inactivos", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.estudiantesInactivos());
            }
        });

        //Menu Reportes
        MenuItem menuReporteGeneral = new MenuItem(iconoReporteGeneral, "General", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.reporteGeneral());
            }
        });
        MenuItem menuReporteCedula = new MenuItem(iconoReporteCedula, "Por Cédula", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.reporteCedula());
            }
        });
        MenuItem menuReporteDetalleApelido = new MenuItem(iconoReporteDetalleApellido, "Detalle por Apellido", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.reporteDetalleApellido());
            }
        });
        MenuItem menuReporteGrafico = new MenuItem(iconoReporteGrafico, "Gráfico", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.ReporteGrafico());
            }
        });

        //----------------------------MENU----------------------------------------------------------
        //Menu Principal
        MenuItem menuInicio = new MenuItem(iconoInicio, "Inicio", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CambiaPanel(jpPanelUsuarios, new paneles.Bienvenida());
            }
        });
        MenuItem menuAdmin = new MenuItem(iconoAdmin, "Administrar", null, menuAgregarUsuarios, menuAgregarEstudiantes, menuUsuarioInactivo, menuEstudianteInactivo);
        MenuItem menuReportes = new MenuItem(iconoReportes, "Reportes", null, menuReporteGeneral, menuReporteCedula, menuReporteDetalleApelido, menuReporteGrafico);
        MenuItem menuSalir = new MenuItem(iconoSalir, "Salir", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });

        addMenu(menuInicio, menuAdmin, menuReportes, menuSalir);

    }


    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            MenusAdmin.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        MenusAdmin.revalidate();
    }

    private void cerrar() {
        InicioPrincipal loguin = new InicioPrincipal();
        loguin.setVisible(true);
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jpFooter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        imgLogoFisei = new javax.swing.JLabel();
        imgLogoUta = new javax.swing.JLabel();
        jpHeader = new javax.swing.JPanel();
        imgLogoEncab = new javax.swing.JLabel();
        RegDocen2 = new javax.swing.JLabel();
        jpPanelUsuarios = new javax.swing.JPanel();
        PanelMenuAdmin = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MenusAdmin = new javax.swing.JPanel();
        jpOpBar = new javax.swing.JPanel();
        jpCerrar = new javax.swing.JPanel();
        X = new javax.swing.JLabel();
        jpMinim = new javax.swing.JPanel();
        Minim = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1500, 590));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1500, 850));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpFooter.setBackground(new java.awt.Color(217, 217, 217));
        jpFooter.setPreferredSize(new java.awt.Dimension(1230, 70));
        jpFooter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Universidad Técnica de Ambato - FISEI");
        jpFooter.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jPanel5.setBackground(new java.awt.Color(169, 169, 169));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgLogoFisei.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/Logo_FISEI.png"))); // NOI18N
        jPanel5.add(imgLogoFisei, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 70));

        imgLogoUta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogoUta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/Logo UTA.png"))); // NOI18N
        jPanel5.add(imgLogoUta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 70));

        jpFooter.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 180, 90));

        jPanel2.add(jpFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 760, 1500, 90));

        jpHeader.setBackground(new java.awt.Color(236, 71, 71));
        jpHeader.setPreferredSize(new java.awt.Dimension(1250, 100));
        jpHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jpHeaderMouseDragged(evt);
            }
        });
        jpHeader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpHeaderMousePressed(evt);
            }
        });
        jpHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imgLogoEncab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogoEncab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenesFrames/registroEstudiantil.png"))); // NOI18N
        jpHeader.add(imgLogoEncab, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 93, 74));

        RegDocen2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 32)); // NOI18N
        RegDocen2.setForeground(new java.awt.Color(255, 255, 255));
        RegDocen2.setText("REGISTRO ESTUDIANTIL");
        jpHeader.add(RegDocen2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, -1, -1));

        jPanel2.add(jpHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1500, 140));

        jpPanelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jpPanelUsuarios.setLayout(new javax.swing.BoxLayout(jpPanelUsuarios, javax.swing.BoxLayout.LINE_AXIS));
        jPanel2.add(jpPanelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 1250, 590));

        PanelMenuAdmin.setBackground(new java.awt.Color(63, 78, 79));

        jScrollPane1.setBorder(null);

        MenusAdmin.setBackground(new java.awt.Color(63, 78, 79));
        MenusAdmin.setLayout(new javax.swing.BoxLayout(MenusAdmin, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(MenusAdmin);

        javax.swing.GroupLayout PanelMenuAdminLayout = new javax.swing.GroupLayout(PanelMenuAdmin);
        PanelMenuAdmin.setLayout(PanelMenuAdminLayout);
        PanelMenuAdminLayout.setHorizontalGroup(
            PanelMenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        PanelMenuAdminLayout.setVerticalGroup(
            PanelMenuAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuAdminLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(PanelMenuAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 250, 590));

        jpOpBar.setBackground(new java.awt.Color(204, 204, 204));
        jpOpBar.setPreferredSize(new java.awt.Dimension(1250, 30));
        jpOpBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpCerrar.setBackground(new java.awt.Color(255, 51, 51));
        jpCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpCerrarMouseExited(evt);
            }
        });

        X.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        X.setForeground(new java.awt.Color(255, 255, 255));
        X.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        X.setText("X");
        X.setToolTipText("");
        X.setMaximumSize(new java.awt.Dimension(17, 39));
        X.setMinimumSize(new java.awt.Dimension(17, 39));
        X.setPreferredSize(new java.awt.Dimension(17, 39));
        X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                XMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpCerrarLayout = new javax.swing.GroupLayout(jpCerrar);
        jpCerrar.setLayout(jpCerrarLayout);
        jpCerrarLayout.setHorizontalGroup(
            jpCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCerrarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(X, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpCerrarLayout.setVerticalGroup(
            jpCerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCerrarLayout.createSequentialGroup()
                .addComponent(X, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jpOpBar.add(jpCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 0, 50, -1));

        jpMinim.setBackground(new java.awt.Color(114, 96, 96));
        jpMinim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpMinimMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpMinimMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jpMinimMouseExited(evt);
            }
        });

        Minim.setFont(new java.awt.Font("Tahoma", 0, 32)); // NOI18N
        Minim.setForeground(new java.awt.Color(255, 255, 255));
        Minim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minim.setText("_");
        Minim.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jpMinimLayout = new javax.swing.GroupLayout(jpMinim);
        jpMinim.setLayout(jpMinimLayout);
        jpMinimLayout.setHorizontalGroup(
            jpMinimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Minim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        jpMinimLayout.setVerticalGroup(
            jpMinimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMinimLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Minim, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpOpBar.add(jpMinim, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 0, 50, 30));

        jPanel2.add(jpOpBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 30));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 1500, 850);
    }// </editor-fold>//GEN-END:initComponents

    private void jpHeaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpHeaderMousePressed
        this.xMouse = evt.getX();
        this.yMouse = evt.getY();
    }//GEN-LAST:event_jpHeaderMousePressed

    private void jpHeaderMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpHeaderMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - this.xMouse, y - this.yMouse);
    }//GEN-LAST:event_jpHeaderMouseDragged

    private void jpMinimMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpMinimMouseExited
        this.jpMinim.setBackground(new Color(114, 96, 96));
    }//GEN-LAST:event_jpMinimMouseExited

    private void jpMinimMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpMinimMouseEntered
        this.jpMinim.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_jpMinimMouseEntered

    private void jpMinimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpMinimMouseClicked
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jpMinimMouseClicked

    private void jpCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpCerrarMouseClicked
        //System.exit(0);

    }//GEN-LAST:event_jpCerrarMouseClicked

    private void jpCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpCerrarMouseEntered
        this.jpCerrar.setBackground(Color.white);
        this.X.setForeground(Color.red);
    }//GEN-LAST:event_jpCerrarMouseEntered

    private void jpCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpCerrarMouseExited
        this.jpCerrar.setBackground(new Color(255, 0, 0));
        this.X.setForeground(Color.white);
    }//GEN-LAST:event_jpCerrarMouseExited

    private void XMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XMouseClicked
        System.exit(0);
    }//GEN-LAST:event_XMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(paginaPrincipal_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(paginaPrincipal_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(paginaPrincipal_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(paginaPrincipal_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new paginaPrincipal_Admin().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenusAdmin;
    private javax.swing.JLabel Minim;
    private javax.swing.JPanel PanelMenuAdmin;
    private javax.swing.JLabel RegDocen2;
    private javax.swing.JLabel X;
    private javax.swing.JLabel imgLogoEncab;
    private javax.swing.JLabel imgLogoFisei;
    private javax.swing.JLabel imgLogoUta;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpCerrar;
    private javax.swing.JPanel jpFooter;
    private javax.swing.JPanel jpHeader;
    private javax.swing.JPanel jpMinim;
    private javax.swing.JPanel jpOpBar;
    private javax.swing.JPanel jpPanelUsuarios;
    // End of variables declaration//GEN-END:variables
}
