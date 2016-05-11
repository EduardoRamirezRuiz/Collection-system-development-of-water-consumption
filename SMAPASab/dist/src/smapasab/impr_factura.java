package smapasab;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.Border;

public class impr_factura extends JInternalFrame implements Printable
{
    JButton Bguardar;
    
    // Variables declaration - do not modify
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel panelizq1;
    private javax.swing.JPanel panelizq;
    private javax.swing.JTextField txtadeudo;
    private javax.swing.JTextField txtadeudo1;
    private javax.swing.JTextField txtaño;
    private javax.swing.JTextField txtaño1;
    private javax.swing.JTextField txtconsumo;
    private javax.swing.JTextField txtconsumo1;
    private javax.swing.JTextField txtcontrato;
    private javax.swing.JTextField txtcontrato1;
    private javax.swing.JTextField txtcuenta;
    private javax.swing.JTextField txtcuenta1;
    private javax.swing.JTextField txtdomic;
    private javax.swing.JTextField txtdomic1;
    private javax.swing.JTextField txtmes;
    private javax.swing.JTextField txtmes1;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombre1;
    private javax.swing.JTextField txtrecibo;
    private javax.swing.JTextField txtrecibo1;
    private javax.swing.JTextField txtult;
    private javax.swing.JTextField txtult1;
    // End of variables declaration
    
    String cuenta="", name="",domicl="", ult="",consumo="",tar="",cont="",año="",mes="";
    float adeudo=0;
    
    public impr_factura()
    {
        super("Factura/Nota");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        desbloquear();
        setSize(450,470);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }
    
    public impr_factura(String cuenta, String name,String domicl, String ult,String consumo,float adeudo, String tar,String cont,String año,String mes)
    {
        super("Capturar contrato");
        
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        this.cuenta=cuenta; this.name=name;this.domicl=domicl; this.ult=ult;
        this.consumo=consumo;this.tar=tar; this.adeudo=adeudo;
        this.cont=cont;this.mes=mes;this.año=año;
        insertar();
        desbloquear();
        setSize(450,470);
        setClosable(true);
        setResizable(true);
        setVisible(true); 
    }
    
    private void insertar()
    {
        txtcuenta.setText(cuenta);
        txtcontrato.setText(cont);
        txtnombre.setText(name);
        txtdomic.setText(domicl);
        txtmes.setText(mes);
        txtaño.setText(año);
        txtult.setText(ult);
        txtconsumo.setText(consumo);
        txtadeudo.setText(""+adeudo);
        jTextField3.setText(""+adeudo);
    }
    
    
    public void crearInterfaz()
    {
        Border border=BorderFactory.createLineBorder(Color.BLACK);
        
        contenedor = new javax.swing.JPanel();
        panelizq = new javax.swing.JPanel();
        panelizq1=new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcuenta = new javax.swing.JTextField();
        txtcontrato = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtnombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtdomic = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtmes = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        txtaño = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        txtult = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txtconsumo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        txtadeudo = new javax.swing.JTextField();
        txtrecibo = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        panelizq1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtcuenta1 = new javax.swing.JTextField();
        txtcontrato1 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtnombre1 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        txtdomic1 = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        txtmes1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        txtaño1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        txtult1 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        txtconsumo1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        txtadeudo1 = new javax.swing.JTextField();
        txtrecibo1 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        contenedor.setBackground(new java.awt.Color(204, 204, 204));
        contenedor.setLayout(null);

        panelizq.setBackground(new java.awt.Color(255, 255, 255));
        panelizq.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("SISTEMA MUNICIPAL");
        panelizq.add(jLabel1);
        jLabel1.setBounds(140, 0, 170, 19);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("DE AGUA POTABLE");
        panelizq.add(jLabel2);
        jLabel2.setBounds(150, 20, 150, 19);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("EL SABINO, GTO.");
        panelizq.add(jLabel3);
        jLabel3.setBounds(160, 40, 140, 19);

        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelizq.add(jLabel4);
        jLabel4.setBounds(130, 0, 160, 60);

        jLabel5.setText("Cuenta:");
        panelizq.add(jLabel5);
        jLabel5.setBounds(0, 70, 60, 14);

        jLabel6.setText("Contrato No.");
        panelizq.add(jLabel6);
        jLabel6.setBounds(150, 70, 70, 14);

        txtcuenta.setBorder(null);
        panelizq.add(txtcuenta);
        txtcuenta.setBounds(70, 60, 70, 20);

        txtcontrato.setBorder(null);
        panelizq.add(txtcontrato);
        txtcontrato.setBounds(220, 60, 60, 20);
        panelizq.add(jSeparator2);
        jSeparator2.setBounds(70, 80, 70, 10);
        panelizq.add(jSeparator5);
        jSeparator5.setBounds(220, 80, 60, 10);

        jLabel7.setText("Recibimos de:");
        panelizq.add(jLabel7);
        jLabel7.setBounds(0, 90, 90, 14);
        panelizq.add(jSeparator1);
        jSeparator1.setBounds(80, 100, 200, 10);

        txtnombre.setBorder(null);
        panelizq.add(txtnombre);
        txtnombre.setBounds(80, 80, 200, 20);

        jLabel8.setText("Domicilio:");
        panelizq.add(jLabel8);
        jLabel8.setBounds(0, 110, 60, 14);
        panelizq.add(jSeparator3);
        jSeparator3.setBounds(50, 120, 230, 10);

        txtdomic.setBorder(null);
        panelizq.add(txtdomic);
        txtdomic.setBounds(60, 100, 220, 20);
        panelizq.add(jSeparator4);
        jSeparator4.setBounds(30, 140, 40, 10);

        jLabel9.setText("Mes:");
        panelizq.add(jLabel9);
        jLabel9.setBounds(0, 130, 35, 14);

        txtmes.setBorder(null);
        panelizq.add(txtmes);
        txtmes.setBounds(30, 120, 40, 20);

        jLabel10.setText("Año:");
        panelizq.add(jLabel10);
        jLabel10.setBounds(80, 130, 30, 14);
        panelizq.add(jSeparator6);
        jSeparator6.setBounds(110, 140, 40, 10);

        txtaño.setBorder(null);
        panelizq.add(txtaño);
        txtaño.setBounds(110, 120, 40, 20);

        jLabel11.setText("U. Pago:");
        panelizq.add(jLabel11);
        jLabel11.setBounds(160, 130, 70, 14);
        panelizq.add(jSeparator7);
        jSeparator7.setBounds(220, 140, 50, 10);

        txtult.setBorder(null);
        panelizq.add(txtult);
        txtult.setBounds(220, 120, 50, 20);

        jLabel12.setText("Consumo:");
        panelizq.add(jLabel12);
        jLabel12.setBounds(0, 150, 70, 14);
        panelizq.add(jSeparator8);
        jSeparator8.setBounds(60, 160, 40, 10);

        txtconsumo.setBorder(null);
        panelizq.add(txtconsumo);
        txtconsumo.setBounds(60, 140, 40, 20);

        jLabel13.setText("Adeudo:");
        panelizq.add(jLabel13);
        jLabel13.setBounds(110, 150, 50, 14);
        panelizq.add(jSeparator9);
        jSeparator9.setBounds(160, 160, 50, 10);

        txtadeudo.setBorder(null);
        panelizq.add(txtadeudo);
        txtadeudo.setBounds(170, 140, 40, 20);

        txtrecibo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtrecibo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECIBO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.red));
        panelizq.add(txtrecibo);
        txtrecibo.setBounds(10, 10, 100, 55);

        jTextField2.setToolTipText("");
        panelizq.add(jTextField2);
        jTextField2.setBounds(170, 170, 110, 20);
        panelizq.add(jTextField3);
        jTextField3.setBounds(170, 410, 110, 20);
        panelizq.add(jTextField4);
        jTextField4.setBounds(170, 190, 110, 20);
        panelizq.add(jTextField5);
        jTextField5.setBounds(170, 210, 110, 20);
        panelizq.add(jTextField6);
        jTextField6.setBounds(170, 230, 110, 20);
        panelizq.add(jTextField7);
        jTextField7.setBounds(170, 250, 110, 20);
        panelizq.add(jTextField8);
        jTextField8.setBounds(170, 270, 110, 20);
        panelizq.add(jTextField9);
        jTextField9.setBounds(170, 290, 110, 20);
        panelizq.add(jTextField10);
        jTextField10.setBounds(170, 310, 110, 20);
        panelizq.add(jTextField11);
        jTextField11.setBounds(170, 330, 110, 20);
        panelizq.add(jTextField12);
        jTextField12.setBounds(170, 350, 110, 20);
        panelizq.add(jTextField13);
        jTextField13.setBounds(170, 370, 110, 20);
        panelizq.add(jTextField14);
        jTextField14.setBounds(170, 390, 110, 20);

        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelizq.add(jLabel14);
        jLabel14.setBounds(70, 170, 100, 240);

        jLabel15.setText("C. Medidor");
        panelizq.add(jLabel15);
        jLabel15.setBounds(80, 170, 110, 14);

        jLabel16.setText("Cuota fija");
        panelizq.add(jLabel16);
        jLabel16.setBounds(80, 190, 110, 14);

        jLabel17.setText("Conex. Agua");
        panelizq.add(jLabel17);
        jLabel17.setBounds(80, 210, 110, 14);

        jLabel18.setText("Material");
        panelizq.add(jLabel18);
        jLabel18.setBounds(80, 230, 110, 14);

        jLabel19.setText("Rezagos");
        panelizq.add(jLabel19);
        jLabel19.setBounds(80, 250, 110, 14);

        jLabel20.setText("Recargados");
        panelizq.add(jLabel20);
        jLabel20.setBounds(80, 270, 110, 14);

        jLabel21.setText("Cooperación");
        panelizq.add(jLabel21);
        jLabel21.setBounds(80, 290, 110, 14);

        jLabel22.setText("Reconexión");
        panelizq.add(jLabel22);
        jLabel22.setBounds(80, 310, 110, 14);

        jLabel23.setText("Deudores Con.");
        panelizq.add(jLabel23);
        jLabel23.setBounds(80, 330, 110, 14);

        jLabel24.setText("Deudores de Frac.");
        panelizq.add(jLabel24);
        jLabel24.setBounds(80, 350, 110, 14);

        jLabel25.setText("Sansión");
        panelizq.add(jLabel25);
        jLabel25.setBounds(80, 370, 110, 14);

        jLabel26.setText("Varios");
        panelizq.add(jLabel26);
        jLabel26.setBounds(80, 390, 110, 14);

        jLabel27.setText("S U M A    $");
        panelizq.add(jLabel27);
        jLabel27.setBounds(80, 410, 110, 14);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/sello.png"))); // NOI18N
        jLabel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelizq.add(jLabel28);
        jLabel28.setBounds(0, 170, 70, 240);

        contenedor.add(panelizq);
        panelizq.setBounds(0, 0, 300, 440);

        panelizq1.setBackground(new java.awt.Color(255, 255, 255));
        panelizq1.setLayout(null);

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel29.setText("SISTEMA MUNICIPAL");
        panelizq1.add(jLabel29);
        jLabel29.setBounds(140, 0, 170, 19);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel30.setText("DE AGUA POTABLE");
        panelizq1.add(jLabel30);
        jLabel30.setBounds(150, 20, 150, 19);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel31.setText("EL SABINO, GTO.");
        panelizq1.add(jLabel31);
        jLabel31.setBounds(160, 40, 140, 19);

        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelizq1.add(jLabel32);
        jLabel32.setBounds(130, 0, 160, 60);

        jLabel33.setText("Cuenta:");
        panelizq1.add(jLabel33);
        jLabel33.setBounds(0, 70, 60, 14);

        jLabel34.setText("Contrato No.");
        panelizq1.add(jLabel34);
        jLabel34.setBounds(150, 70, 70, 14);

        txtcuenta1.setBorder(null);
        txtcuenta1.setEnabled(false);
        panelizq1.add(txtcuenta1);
        txtcuenta1.setBounds(70, 60, 70, 20);

        txtcontrato1.setBorder(null);
        txtcontrato1.setEnabled(false);
        panelizq1.add(txtcontrato1);
        txtcontrato1.setBounds(220, 60, 60, 20);
        panelizq1.add(jSeparator10);
        jSeparator10.setBounds(70, 80, 70, 10);
        panelizq1.add(jSeparator11);
        jSeparator11.setBounds(220, 80, 60, 10);

        jLabel35.setText("Recibimos de:");
        panelizq1.add(jLabel35);
        jLabel35.setBounds(0, 90, 90, 14);
        panelizq1.add(jSeparator12);
        jSeparator12.setBounds(80, 100, 200, 10);

        txtnombre1.setBorder(null);
        txtnombre1.setEnabled(false);
        panelizq1.add(txtnombre1);
        txtnombre1.setBounds(80, 80, 200, 20);

        jLabel36.setText("Domicilio:");
        panelizq1.add(jLabel36);
        jLabel36.setBounds(0, 110, 60, 14);

        jSeparator13.setEnabled(false);
        panelizq1.add(jSeparator13);
        jSeparator13.setBounds(50, 120, 230, 10);

        txtdomic1.setBorder(null);
        txtdomic1.setEnabled(false);
        panelizq1.add(txtdomic1);
        txtdomic1.setBounds(60, 100, 220, 20);
        panelizq1.add(jSeparator14);
        jSeparator14.setBounds(30, 140, 40, 10);

        jLabel37.setText("Mes:");
        panelizq1.add(jLabel37);
        jLabel37.setBounds(0, 130, 35, 14);

        txtmes1.setBorder(null);
        txtmes1.setEnabled(false);
        panelizq1.add(txtmes1);
        txtmes1.setBounds(30, 120, 40, 20);

        jLabel38.setText("Año:");
        panelizq1.add(jLabel38);
        jLabel38.setBounds(80, 130, 30, 14);
        panelizq1.add(jSeparator15);
        jSeparator15.setBounds(110, 140, 40, 10);

        txtaño1.setBorder(null);
        txtaño1.setEnabled(false);
        panelizq1.add(txtaño1);
        txtaño1.setBounds(110, 120, 40, 20);

        jLabel39.setText("U. Pago:");
        panelizq1.add(jLabel39);
        jLabel39.setBounds(160, 130, 70, 14);
        panelizq1.add(jSeparator16);
        jSeparator16.setBounds(220, 140, 50, 10);

        txtult1.setBorder(null);
        txtult1.setEnabled(false);
        panelizq1.add(txtult1);
        txtult1.setBounds(220, 120, 50, 20);

        jLabel40.setText("Consumo:");
        panelizq1.add(jLabel40);
        jLabel40.setBounds(0, 150, 70, 14);
        panelizq1.add(jSeparator17);
        jSeparator17.setBounds(60, 160, 40, 10);

        txtconsumo1.setBorder(null);
        txtconsumo1.setEnabled(false);
        panelizq1.add(txtconsumo1);
        txtconsumo1.setBounds(60, 140, 40, 20);

        jLabel41.setText("Adeudo:");
        panelizq1.add(jLabel41);
        jLabel41.setBounds(110, 150, 50, 14);
        panelizq1.add(jSeparator18);
        jSeparator18.setBounds(160, 160, 50, 10);

        txtadeudo1.setBorder(null);
        txtadeudo1.setEnabled(false);
        panelizq1.add(txtadeudo1);
        txtadeudo1.setBounds(170, 140, 40, 20);

        txtrecibo1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtrecibo1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RECIBO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.red));
        txtrecibo1.setEnabled(false);
        panelizq1.add(txtrecibo1);
        txtrecibo1.setBounds(10, 10, 100, 55);

        jTextField15.setToolTipText("");
        jTextField15.setEnabled(false);
        panelizq1.add(jTextField15);
        jTextField15.setBounds(170, 170, 110, 20);

        jTextField16.setEnabled(false);
        panelizq1.add(jTextField16);
        jTextField16.setBounds(170, 410, 110, 20);

        jTextField17.setEnabled(false);
        panelizq1.add(jTextField17);
        jTextField17.setBounds(170, 190, 110, 20);

        jTextField18.setEnabled(false);
        panelizq1.add(jTextField18);
        jTextField18.setBounds(170, 210, 110, 20);

        jTextField19.setEnabled(false);
        panelizq1.add(jTextField19);
        jTextField19.setBounds(170, 230, 110, 20);

        jTextField20.setEnabled(false);
        panelizq1.add(jTextField20);
        jTextField20.setBounds(170, 250, 110, 20);

        jTextField21.setEnabled(false);
        panelizq1.add(jTextField21);
        jTextField21.setBounds(170, 270, 110, 20);

        jTextField22.setEnabled(false);
        panelizq1.add(jTextField22);
        jTextField22.setBounds(170, 290, 110, 20);

        jTextField23.setEnabled(false);
        panelizq1.add(jTextField23);
        jTextField23.setBounds(170, 310, 110, 20);

        jTextField24.setEnabled(false);
        panelizq1.add(jTextField24);
        jTextField24.setBounds(170, 330, 110, 20);

        jTextField25.setEnabled(false);
        panelizq1.add(jTextField25);
        jTextField25.setBounds(170, 350, 110, 20);

        jTextField26.setEnabled(false);
        panelizq1.add(jTextField26);
        jTextField26.setBounds(170, 370, 110, 20);

        jTextField27.setEnabled(false);
        panelizq1.add(jTextField27);
        jTextField27.setBounds(170, 390, 110, 20);

        jLabel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelizq1.add(jLabel42);
        jLabel42.setBounds(70, 170, 100, 240);

        jLabel43.setText("C. Medidor");
        panelizq1.add(jLabel43);
        jLabel43.setBounds(80, 170, 110, 14);

        jLabel44.setText("Cuota fija");
        panelizq1.add(jLabel44);
        jLabel44.setBounds(80, 190, 110, 14);

        jLabel45.setText("Conex. Agua");
        panelizq1.add(jLabel45);
        jLabel45.setBounds(80, 210, 110, 14);

        jLabel46.setText("Material");
        panelizq1.add(jLabel46);
        jLabel46.setBounds(80, 230, 110, 14);

        jLabel47.setText("Rezagos");
        panelizq1.add(jLabel47);
        jLabel47.setBounds(80, 250, 110, 14);

        jLabel48.setText("Recargados");
        panelizq1.add(jLabel48);
        jLabel48.setBounds(80, 270, 110, 14);

        jLabel49.setText("Cooperación");
        panelizq1.add(jLabel49);
        jLabel49.setBounds(80, 290, 110, 14);

        jLabel50.setText("Reconexión");
        panelizq1.add(jLabel50);
        jLabel50.setBounds(80, 310, 110, 14);

        jLabel51.setText("Deudores Con.");
        panelizq1.add(jLabel51);
        jLabel51.setBounds(80, 330, 110, 14);

        jLabel52.setText("Deudores de Frac.");
        panelizq1.add(jLabel52);
        jLabel52.setBounds(80, 350, 110, 14);

        jLabel53.setText("Sansión");
        panelizq1.add(jLabel53);
        jLabel53.setBounds(80, 370, 110, 14);

        jLabel54.setText("Varios");
        panelizq1.add(jLabel54);
        jLabel54.setBounds(80, 390, 110, 14);

        jLabel55.setText("S U M A    $");
        panelizq1.add(jLabel55);
        jLabel55.setBounds(80, 410, 110, 14);

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/sello.png"))); // NOI18N
        jLabel56.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelizq1.add(jLabel56);
        jLabel56.setBounds(0, 170, 70, 240);

        contenedor.add(panelizq1);
        panelizq1.setBounds(0, 440, 300, 440);

        getContentPane().add(contenedor);
        contenedor.setBounds(0, 0, 300, 890);
        
        
        
//        txtrecibo.setBorder(border);
//        txtrecibo1.setBorder(border);
        jTextField2.setBorder(border);
        jTextField3.setBorder(border);
        jTextField4.setBorder(border);
        jTextField5.setBorder(border);
        jTextField6.setBorder(border);
        jTextField7.setBorder(border);
        jTextField8.setBorder(border);
        jTextField9.setBorder(border);
        jTextField10.setBorder(border);
        jTextField11.setBorder(border);
        jTextField12.setBorder(border);
        jTextField13.setBorder(border);
        jTextField14.setBorder(border);
        jTextField15.setBorder(border);
        jTextField16.setBorder(border);
        jTextField17.setBorder(border);
        jTextField18.setBorder(border);
        jTextField19.setBorder(border);
        jTextField20.setBorder(border);
        jTextField21.setBorder(border);
        jTextField22.setBorder(border);
        jTextField23.setBorder(border);
        jTextField24.setBorder(border);
        jTextField25.setBorder(border);
        jTextField26.setBorder(border);
        jTextField27.setBorder(border);

        pack();
        
        Bguardar=new JButton("Imprimir",new ImageIcon("src/recursos/printer.png"));
        Bguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bguardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bguardar.setVerticalAlignment(SwingConstants.CENTER);
        Bguardar.setSize(100, 70);
        Bguardar.setLocation(320, 30);
        Bguardar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                mandarAImpresora();
            }
        });
        add(Bguardar);
        
        
        getRecibo();
        
    }
    
    private void getRecibo()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select * from recibo");
                while(rs.next())
                {   
                    txtrecibo.setText(rs.getString("numero"));
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
    }
    
    private void guardarRecibo()
    {
        try
        {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            
            int filas=0;
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                filas=sentencia.executeUpdate("update recibo set numero=numero+1");
                if(filas>0)
                    System.out.print(""+filas);
                else
                    System.out.print(""+filas);
                
                sentencia.close();
                con.close();       
        }
        catch(SQLException | HeadlessException ex){}
    }
    
    private void mandarAImpresora()
    {
        igualar();
        
        try
        {
           PrinterJob job= PrinterJob.getPrinterJob();
           PageFormat formaPagina = new PageFormat();
           formaPagina.setOrientation( PageFormat.PORTRAIT); //Establecemos la orientación de la página
           Paper papel = new Paper();
          // papel.setSize(8.3*72, 11.7*72); //Tamaños en 1/72 de inch, aquí hemos puesto el A4
           //formaPagina.setPaper (papel); //Establecemos el tamaño del papel para el trabajo
           job.setPrintable(this, formaPagina);
                    //gab.setPrintable();
           boolean top=job.printDialog();
           if(top)
           {
               job.print();
           }
           
           guardarRecibo();
        }
        catch(IllegalArgumentException | HeadlessException | PrinterException ex)
        {
                    System.err.print(""+ex);
        }
    }
    
    @Override
    public int print(Graphics graf, PageFormat pageFormat, int Index) throws PrinterException 
    {
        if(Index>0)
        {
            return NO_SUCH_PAGE;
        }
        
        Graphics2D hub= (Graphics2D) graf;
        hub.translate(pageFormat.getImageableX() + 0, pageFormat.getImageableY() +0);
        hub.scale(0.75, 0.75);
        
        contenedor.paintAll(graf);
        return PAGE_EXISTS;
    }
    
    public void igualar()
    {
        txtcuenta1.setText(txtcuenta.getText());
        txtcontrato1.setText(txtcontrato.getText());
        txtnombre1.setText(txtnombre.getText());
        txtdomic1.setText(txtdomic.getText());
        txtmes1.setText(txtmes.getText());
        txtaño1.setText(txtaño.getText());
        txtult1.setText(txtult.getText());
        txtconsumo1.setText(txtconsumo.getText());
        txtadeudo1.setText(txtadeudo.getText());
        txtrecibo1.setText(txtrecibo.getText());
        jTextField15.setText(jTextField2.getText());
        jTextField16.setText(jTextField3.getText());
        jTextField17.setText(jTextField4.getText());
        jTextField18.setText(jTextField5.getText());
        jTextField19.setText(jTextField6.getText());
        jTextField20.setText(jTextField7.getText());
        jTextField21.setText(jTextField8.getText());
        jTextField22.setText(jTextField9.getText());
        jTextField23.setText(jTextField10.getText());
        jTextField24.setText(jTextField11.getText());
        jTextField25.setText(jTextField12.getText());
        jTextField26.setText(jTextField13.getText());
        jTextField27.setText(jTextField14.getText());
    }

    private void desbloquear() 
    {
        txtcuenta1.setEnabled(true);
        txtcontrato1.setEnabled(true);
        txtnombre1.setEnabled(true);
        txtdomic1.setEnabled(true);
        txtmes1.setEnabled(true);
        txtaño1.setEnabled(true);
        txtult1.setEnabled(true);
        txtconsumo1.setEnabled(true);
        txtadeudo1.setEnabled(true);
        txtrecibo1.setEnabled(true);
        jTextField15.setEnabled(true);
        jTextField16.setEnabled(true);
        jTextField17.setEnabled(true);
        jTextField18.setEnabled(true);
        jTextField19.setEnabled(true);
        jTextField20.setEnabled(true);
        jTextField21.setEnabled(true);
        jTextField22.setEnabled(true);
        jTextField23.setEnabled(true);
        jTextField24.setEnabled(true);
        jTextField25.setEnabled(true);
        jTextField26.setEnabled(true);
        jTextField27.setEnabled(true);
    }
}
