package smapasab;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.border.Border;

public class recibos extends JInternalFrame implements Printable
{
    JButton Brut1,Brut2;
     // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnlboton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField textfemi;
    private javax.swing.JTextField textfemi1;
    private javax.swing.JTextField textfemi2;
    private javax.swing.JTextField txtconsumido;
    private javax.swing.JTextField txtconsumido1;
    private javax.swing.JTextField txtconsumido2;
    private javax.swing.JTextField txtconsumo;
    private javax.swing.JTextField txtconsumo1;
    private javax.swing.JTextField txtconsumo2;
    private javax.swing.JTextField txtcuen;
    private javax.swing.JTextField txtcuen1;
    private javax.swing.JTextField txtcuen2;
    private javax.swing.JTextField txtcuenta;
    private javax.swing.JTextField txtcuenta1;
    private javax.swing.JTextField txtcuenta2;
    private javax.swing.JTextField txtflimite;
    private javax.swing.JTextField txtflimite1;
    private javax.swing.JTextField txtflimite2;
    private javax.swing.JTextField txtlectAct;
    private javax.swing.JTextField txtlectAct1;
    private javax.swing.JTextField txtlectAct2;
    private javax.swing.JTextField txtlectAnt;
    private javax.swing.JTextField txtlectAnt1;
    private javax.swing.JTextField txtlectAnt2;
    private javax.swing.JTextField txtpma;
    private javax.swing.JTextField txtpma1;
    private javax.swing.JTextField txtpma2;
    private javax.swing.JTextField txtpmalc;
    private javax.swing.JTextField txtpmalc1;
    private javax.swing.JTextField txtpmalc2;
    private javax.swing.JTextField txtpmap;
    private javax.swing.JTextField txtpmap1;
    private javax.swing.JTextField txtpmap2;
    private javax.swing.JTextField txtpml;
    private javax.swing.JTextField txtpml1;
    private javax.swing.JTextField txtpml2;
    private javax.swing.JTextField txtreca;
    private javax.swing.JTextField txtreca1;
    private javax.swing.JTextField txtreca2;
    private javax.swing.JTextField txtrecalc;
    private javax.swing.JTextField txtrecalc1;
    private javax.swing.JTextField txtrecalc2;
    private javax.swing.JTextField txtrecap;
    private javax.swing.JTextField txtrecap1;
    private javax.swing.JTextField txtrecap2;
    private javax.swing.JTextField txtrecl;
    private javax.swing.JTextField txtrecl1;
    private javax.swing.JTextField txtrecl2;
    private javax.swing.JTextField txtreza;
    private javax.swing.JTextField txtreza1;
    private javax.swing.JTextField txtreza2;
    private javax.swing.JTextField txtrezalc;
    private javax.swing.JTextField txtrezalc1;
    private javax.swing.JTextField txtrezalc2;
    private javax.swing.JTextField txtrezap;
    private javax.swing.JTextField txtrezap1;
    private javax.swing.JTextField txtrezap2;
    private javax.swing.JTextField txtrezl;
    private javax.swing.JTextField txtrezl1;
    private javax.swing.JTextField txtrezl2;
    private javax.swing.JTextField txttot;
    private javax.swing.JTextField txttot1;
    private javax.swing.JTextField txttot2;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotal1;
    private javax.swing.JTextField txttotal2;
    private javax.swing.JTextField txtvence;
    private javax.swing.JTextField txtvence1;
    private javax.swing.JTextField txtvence2;
    String femi,fven;
    int [][] maximos=new int [4][4]; float [][] costos=new float [4][4];
    // End of variables declaration
    
    public recibos()
    {
        super("Recibos");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        entrar();
//        crearInterfaz();
        setSize(150,118);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }
    
    private void entrar()
    {
        int opc=1; String inst="",inst2="";
            do
            {
                inst=JOptionPane.showInputDialog(null,"Fecha de EMISIÓN ##-##-##","##-##-##");
                inst2=JOptionPane.showInputDialog(null,"Fecha de VENCIMIENTO ##-##-##","##-##-##");
                if(inst==null || inst2==null)
                {
                    opc=3;
                    //System.out.println("nulos");
                }
                else
                {
                  if(inst.contains("-") && inst2.contains("-") && inst.length()==8 && inst2.length()==8 && !inst.contains("#") && !inst2.contains("#"))
                  {
                    femi=inst;
                    fven=inst2;
                    opc=2;
                   // System.out.println("contiene");
                  }
                  else
                  {
                    opc=1;
                    //System.out.println("no contiene");
                  }
                }
            }while(opc==1);
        
        if(opc==2)
            crearInterfaz();
        else
            this.dispose();
    }
    
    private void crearInterfaz()
    {
        Border border=BorderFactory.createLineBorder(Color.GRAY);
        pnlboton = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpmap = new javax.swing.JTextField();
        txtvence = new javax.swing.JTextField();
        txtlectAnt = new javax.swing.JTextField();
        txtlectAct = new javax.swing.JTextField();
        txtconsumo = new javax.swing.JTextField();
        textfemi = new javax.swing.JTextField();
        txtrezap = new javax.swing.JTextField();
        txtrecap = new javax.swing.JTextField();
        txtrezalc = new javax.swing.JTextField();
        txtrecalc = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txtpmalc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcuenta = new javax.swing.JTextField();
        txtflimite = new javax.swing.JTextField();
        txtconsumido = new javax.swing.JTextField();
        txtcuen = new javax.swing.JTextField();
        txtreza = new javax.swing.JTextField();
        txtreca = new javax.swing.JTextField();
        txtpma = new javax.swing.JTextField();
        txtrezl = new javax.swing.JTextField();
        txtrecl = new javax.swing.JTextField();
        txtpml = new javax.swing.JTextField();
        txttot = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtpmap1 = new javax.swing.JTextField();
        txtvence1 = new javax.swing.JTextField();
        txtlectAnt1 = new javax.swing.JTextField();
        txtlectAct1 = new javax.swing.JTextField();
        txtconsumo1 = new javax.swing.JTextField();
        textfemi1 = new javax.swing.JTextField();
        txtrezap1 = new javax.swing.JTextField();
        txtrecap1 = new javax.swing.JTextField();
        txtrezalc1 = new javax.swing.JTextField();
        txtrecalc1 = new javax.swing.JTextField();
        txttotal1 = new javax.swing.JTextField();
        txtpmalc1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtcuenta1 = new javax.swing.JTextField();
        txtflimite1 = new javax.swing.JTextField();
        txtconsumido1 = new javax.swing.JTextField();
        txtcuen1 = new javax.swing.JTextField();
        txtreza1 = new javax.swing.JTextField();
        txtreca1 = new javax.swing.JTextField();
        txtpma1 = new javax.swing.JTextField();
        txtrezl1 = new javax.swing.JTextField();
        txtrecl1 = new javax.swing.JTextField();
        txtpml1 = new javax.swing.JTextField();
        txttot1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtpmap2 = new javax.swing.JTextField();
        txtvence2 = new javax.swing.JTextField();
        txtlectAnt2 = new javax.swing.JTextField();
        txtlectAct2 = new javax.swing.JTextField();
        txtconsumo2 = new javax.swing.JTextField();
        textfemi2 = new javax.swing.JTextField();
        txtrezap2 = new javax.swing.JTextField();
        txtrecap2 = new javax.swing.JTextField();
        txtrezalc2 = new javax.swing.JTextField();
        txtrecalc2 = new javax.swing.JTextField();
        txttotal2 = new javax.swing.JTextField();
        txtpmalc2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtcuenta2 = new javax.swing.JTextField();
        txtflimite2 = new javax.swing.JTextField();
        txtconsumido2 = new javax.swing.JTextField();
        txtcuen2 = new javax.swing.JTextField();
        txtreza2 = new javax.swing.JTextField();
        txtreca2 = new javax.swing.JTextField();
        txtpma2 = new javax.swing.JTextField();
        txtrezl2 = new javax.swing.JTextField();
        txtrecl2 = new javax.swing.JTextField();
        txtpml2 = new javax.swing.JTextField();
        txttot2 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 102));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel1.setText("SISTEMA MUNICIPAL DE AGUA POTABLE Y ALCANTARIRALLADOS, EL SABINO,GTO.");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 0, 484, 14);

        txtpmap.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel2.add(txtpmap);
        txtpmap.setBounds(450, 70, 94, 40);

        txtvence.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Vence"));
        jPanel2.add(txtvence);
        txtvence.setBounds(90, 20, 94, 39);

        txtlectAnt.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Lect. Anterior"));
        jPanel2.add(txtlectAnt);
        txtlectAnt.setBounds(180, 20, 94, 39);

        txtlectAct.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Lect. Actual"));
        jPanel2.add(txtlectAct);
        txtlectAct.setBounds(270, 20, 94, 39);

        txtconsumo.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Consumo"));
        jPanel2.add(txtconsumo);
        txtconsumo.setBounds(360, 20, 94, 39);

        textfemi.setText("hola");
        textfemi.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Fecha emisión"));
        jPanel2.add(textfemi);
        textfemi.setBounds(0, 20, 94, 40);

        txtrezap.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos AP"));
        jPanel2.add(txtrezap);
        txtrezap.setBounds(270, 70, 94, 40);

        txtrecap.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos AP"));
        jPanel2.add(txtrecap);
        txtrecap.setBounds(360, 70, 94, 40);

        txtrezalc.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos ALC"));
        jPanel2.add(txtrezalc);
        txtrezalc.setBounds(270, 110, 94, 40);

        txtrecalc.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos ALC"));
        jPanel2.add(txtrecalc);
        txtrecalc.setBounds(360, 110, 94, 40);

        txttotal.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Total"));
        jPanel2.add(txttotal);
        txttotal.setBounds(450, 160, 94, 40);

        txtpmalc.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel2.add(txtpmalc);
        txtpmalc.setBounds(450, 110, 94, 40);

        jLabel2.setText("S.M.A.P.A.");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(560, 0, 90, 14);

        txtcuenta.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Cuenta"));
        jPanel2.add(txtcuenta);
        txtcuenta.setBounds(450, 20, 94, 39);

        txtflimite.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Fecha Limite"));
        jPanel2.add(txtflimite);
        txtflimite.setBounds(560, 20, 94, 39);

        txtconsumido.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Consumo"));
        jPanel2.add(txtconsumido);
        txtconsumido.setBounds(650, 20, 94, 39);

        txtcuen.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Cuenta"));
        jPanel2.add(txtcuen);
        txtcuen.setBounds(740, 20, 94, 39);

        txtreza.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos AP"));
        jPanel2.add(txtreza);
        txtreza.setBounds(560, 70, 94, 40);

        txtreca.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos AP"));
        jPanel2.add(txtreca);
        txtreca.setBounds(650, 70, 94, 40);

        txtpma.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel2.add(txtpma);
        txtpma.setBounds(740, 70, 94, 40);

        txtrezl.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos ALC"));
        
        jPanel2.add(txtrezl);
        txtrezl.setBounds(560, 110, 94, 40);

        txtrecl.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos ALC"));
        jPanel2.add(txtrecl);
        txtrecl.setBounds(650, 110, 94, 40);

        txtpml.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel2.add(txtpml);
        txtpml.setBounds(740, 110, 94, 40);

        txttot.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Total"));
        jPanel2.add(txttot);
        txttot.setBounds(740, 160, 94, 40);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(20, 160, 180, 10);

        jLabel7.setText("SELLO Y FIRMA DEL CAJERO");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 170, 230, 14);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 850, 210);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(null);

        jLabel3.setText("SISTEMA MUNICIPAL DE AGUA POTABLE Y ALCANTARIRALLADOS, EL SABINO,GTO.");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 0, 484, 14);

        txtpmap1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel3.add(txtpmap1);
        txtpmap1.setBounds(450, 70, 94, 40);

        txtvence1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Vence"));
        jPanel3.add(txtvence1);
        txtvence1.setBounds(90, 20, 94, 39);

        txtlectAnt1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Lect. Anterior"));
        jPanel3.add(txtlectAnt1);
        txtlectAnt1.setBounds(180, 20, 94, 39);

        txtlectAct1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Lect. Actual"));
        jPanel3.add(txtlectAct1);
        txtlectAct1.setBounds(270, 20, 94, 39);

        txtconsumo1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Consumo"));
        jPanel3.add(txtconsumo1);
        txtconsumo1.setBounds(360, 20, 94, 39);

        textfemi1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Fecha emisión"));
        jPanel3.add(textfemi1);
        textfemi1.setBounds(0, 20, 94, 40);

        txtrezap1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos AP"));
        jPanel3.add(txtrezap1);
        txtrezap1.setBounds(270, 70, 94, 40);

        txtrecap1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos AP"));
        jPanel3.add(txtrecap1);
        txtrecap1.setBounds(360, 70, 94, 40);

        txtrezalc1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos ALC"));
        jPanel3.add(txtrezalc1);
        txtrezalc1.setBounds(270, 110, 94, 40);

        txtrecalc1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos ALC"));
        jPanel3.add(txtrecalc1);
        txtrecalc1.setBounds(360, 110, 94, 40);

        txttotal1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Total"));
        jPanel3.add(txttotal1);
        txttotal1.setBounds(450, 160, 94, 40);

        txtpmalc1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel3.add(txtpmalc1);
        txtpmalc1.setBounds(450, 110, 94, 40);

        jLabel4.setText("S.M.A.P.A.");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(560, 0, 90, 14);

        txtcuenta1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Cuenta"));
        jPanel3.add(txtcuenta1);
        txtcuenta1.setBounds(450, 20, 94, 39);

        txtflimite1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Fecha Limite"));
        jPanel3.add(txtflimite1);
        txtflimite1.setBounds(560, 20, 94, 39);

        txtconsumido1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Consumo"));
        jPanel3.add(txtconsumido1);
        txtconsumido1.setBounds(650, 20, 94, 39);

        txtcuen1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Cuenta"));
        jPanel3.add(txtcuen1);
        txtcuen1.setBounds(740, 20, 94, 39);

        txtreza1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos AP"));
        jPanel3.add(txtreza1);
        txtreza1.setBounds(560, 70, 94, 40);

        txtreca1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos AP"));
        jPanel3.add(txtreca1);
        txtreca1.setBounds(650, 70, 94, 40);

        txtpma1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel3.add(txtpma1);
        txtpma1.setBounds(740, 70, 94, 40);

        txtrezl1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos ALC"));
        jPanel3.add(txtrezl1);
        txtrezl1.setBounds(560, 110, 94, 40);

        txtrecl1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos ALC"));
        jPanel3.add(txtrecl1);
        txtrecl1.setBounds(650, 110, 94, 40);

        txtpml1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel3.add(txtpml1);
        txtpml1.setBounds(740, 110, 94, 40);

        txttot1.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Total"));
        jPanel3.add(txttot1);
        txttot1.setBounds(740, 160, 94, 40);

        jLabel8.setText("SELLO Y FIRMA DEL CAJERO");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(40, 170, 270, 14);
        jPanel3.add(jSeparator2);
        jSeparator2.setBounds(30, 160, 180, 10);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 210, 850, 210);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabel5.setText("SISTEMA MUNICIPAL DE AGUA POTABLE Y ALCANTARIRALLADOS, EL SABINO,GTO.");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(10, 0, 484, 14);

        txtpmap2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel4.add(txtpmap2);
        txtpmap2.setBounds(450, 70, 94, 40);

        txtvence2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Vence"));
        jPanel4.add(txtvence2);
        txtvence2.setBounds(90, 20, 94, 39);

        txtlectAnt2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Lect. Anterior"));
        jPanel4.add(txtlectAnt2);
        txtlectAnt2.setBounds(180, 20, 94, 39);

        txtlectAct2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Lect. Actual"));
        jPanel4.add(txtlectAct2);
        txtlectAct2.setBounds(270, 20, 94, 39);

        txtconsumo2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Consumo"));
        jPanel4.add(txtconsumo2);
        txtconsumo2.setBounds(360, 20, 94, 39);

        textfemi2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Fecha emisión"));
        jPanel4.add(textfemi2);
        textfemi2.setBounds(0, 20, 94, 40);

        txtrezap2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos AP"));
        jPanel4.add(txtrezap2);
        txtrezap2.setBounds(270, 70, 94, 40);

        txtrecap2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos AP"));
        jPanel4.add(txtrecap2);
        txtrecap2.setBounds(360, 70, 94, 40);

        txtrezalc2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos ALC"));
        jPanel4.add(txtrezalc2);
        txtrezalc2.setBounds(270, 110, 94, 40);

        txtrecalc2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos ALC"));
        jPanel4.add(txtrecalc2);
        txtrecalc2.setBounds(360, 110, 94, 40);

        txttotal2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Total"));
        jPanel4.add(txttotal2);
        txttotal2.setBounds(450, 160, 94, 40);

        txtpmalc2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel4.add(txtpmalc2);
        txtpmalc2.setBounds(450, 110, 94, 40);

        jLabel6.setText("S.M.A.P.A.");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(560, 0, 90, 14);

        txtcuenta2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Cuenta"));
        jPanel4.add(txtcuenta2);
        txtcuenta2.setBounds(450, 20, 94, 39);

        txtflimite2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Fecha Limite"));
        jPanel4.add(txtflimite2);
        txtflimite2.setBounds(560, 20, 94, 39);

        txtconsumido2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Consumo"));
        jPanel4.add(txtconsumido2);
        txtconsumido2.setBounds(650, 20, 94, 39);

        txtcuen2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Cuenta"));
        jPanel4.add(txtcuen2);
        txtcuen2.setBounds(740, 20, 94, 39);

        txtreza2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos AP"));
        jPanel4.add(txtreza2);
        txtreza2.setBounds(560, 70, 94, 40);

        txtreca2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos AP"));
        jPanel4.add(txtreca2);
        txtreca2.setBounds(650, 70, 94, 40);

        txtpma2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel4.add(txtpma2);
        txtpma2.setBounds(740, 70, 94, 40);

        txtrezl2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Rezagos ALC"));
        jPanel4.add(txtrezl2);
        txtrezl2.setBounds(560, 110, 94, 40);

        txtrecl2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Recargos ALC"));
        jPanel4.add(txtrecl2);
        txtrecl2.setBounds(650, 110, 94, 40);

        txtpml2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Presente mes"));
        jPanel4.add(txtpml2);
        txtpml2.setBounds(740, 110, 94, 40);

        txttot2.setBorder(javax.swing.BorderFactory.createTitledBorder(border,"Total"));
        jPanel4.add(txttot2);
        txttot2.setBounds(740, 160, 94, 40);
        jPanel4.add(jSeparator3);
        jSeparator3.setBounds(20, 160, 180, 10);

        jLabel9.setText("SELLO Y FIRMA DEL CAJERO");
        jPanel4.add(jLabel9);
        jLabel9.setBounds(30, 170, 260, 14);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 420, 850, 220);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(130, 116, 845, 653);

        javax.swing.GroupLayout pnlbotonLayout = new javax.swing.GroupLayout(pnlboton);
        pnlboton.setLayout(pnlbotonLayout);
        pnlbotonLayout.setHorizontalGroup(
            pnlbotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        pnlbotonLayout.setVerticalGroup(
            pnlbotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(pnlboton);
        pnlboton.setBounds(20, 10, 110, 100);

        pack();
        
        Brut1=new JButton("R 1",new ImageIcon("src/recursos/gr1.png"));
        Brut1.setHorizontalTextPosition(SwingConstants.CENTER);
        Brut1.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Brut1.setVerticalAlignment(SwingConstants.CENTER);
        Brut1.setSize(50, 50);
        Brut1.setLocation(0, 0);
        Brut1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                buscar(2);
            }
        });
        pnlboton.add(Brut1);
        
        Brut2=new JButton("R 2",new ImageIcon("src/recursos/gr2.png"));
        Brut2.setHorizontalTextPosition(SwingConstants.CENTER);
        Brut2.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Brut2.setVerticalAlignment(SwingConstants.CENTER);
        Brut2.setSize(50, 50);
        Brut2.setLocation(50, 0);
        Brut2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                buscar(3);
            }
        });
        pnlboton.add(Brut2);
        
        
        llenarCuotas();
        
    }
    
    public void limpiar()
    {
                                    textfemi.setText("");
                                    txtvence.setText("");
                                    txtflimite.setText("");
                                    txtlectAnt.setText("");
                                    txtlectAct.setText("");
                                    txtconsumo.setText("");
                                    txtconsumido.setText("");
                                    txtcuenta.setText("");
                                    txtcuen.setText("");
                                    txtrezap.setText("");
                                    txtrecap.setText("");
                                    txtpmap.setText("");
                                    txtpmalc.setText("");
                                    txttotal.setText("");
                                    txtreza.setText("");
                                    txtreca.setText("");
                                    txtpma.setText("");
                                    txtpml.setText("");
                                    txttot.setText("");
                                    
                                    textfemi1.setText("");
                                    txtvence1.setText("");
                                    txtflimite1.setText("");
                                    txtlectAnt1.setText("");
                                    txtlectAct1.setText("");
                                    txtconsumo1.setText("");
                                    txtconsumido1.setText("");
                                    txtcuenta1.setText("");
                                    txtcuen1.setText("");
                                    txtrezap1.setText("");
                                    txtrecap1.setText("");
                                    txtpmap1.setText("");
                                    txtpmalc1.setText("");
                                    txttotal1.setText("");
                                    txtreza1.setText("");
                                    txtreca1.setText("");
                                    txtpma1.setText("");
                                    txtpml1.setText("");
                                    txttot1.setText("");
                                    
                                    textfemi2.setText("");
                                    txtvence2.setText("");
                                    txtflimite2.setText("");
                                    txtlectAnt2.setText("");
                                    txtlectAct2.setText("");
                                    txtconsumo2.setText("");
                                    txtconsumido2.setText("");
                                    txtcuenta2.setText("");
                                    txtcuen2.setText("");
                                    txtrezap2.setText("");
                                    txtrecap2.setText("");
                                    txtpmap2.setText("");
                                    txtpmalc2.setText("");
                                    txttotal2.setText("");
                                    txtreza2.setText("");
                                    txtreca2.setText("");
                                    txtpma2.setText("");
                                    txtpml2.setText("");
                                    txttot2.setText("");
    }
    
    private void guardar(int opc)
    {
        int filas=0; //update pago set fEmision='10-10-14', fVencimiento='11-09-14' where cuenta in (select cuenta from usuario where cveRuta='1')
        try
        { 
            String query="";
            switch(opc)
            {
                case 2: query="update pago set fEmision='"+femi+"', fVencimiento='"+fven+"' where cuenta in (select cuenta from usuario where cveRuta='1')";
                    break;
                case 3: query="update pago set fEmision='"+femi+"', fVencimiento='"+fven+"' where cuenta in (select cuenta from usuario where cveRuta='2')";
                    break;
            }
            
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            filas=sentencia.executeUpdate(query);
            if(filas>0)
                JOptionPane.showMessageDialog(null, "Comenzará la impresión","Actualización",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Error al comenzar la impresión","Actualización",JOptionPane.ERROR_MESSAGE);
            
        }
        catch(Exception ex){}
    }
    
    public void buscar(int opc)
    {
        //limpiar();
        guardar(opc);
        try
        { 
            String query=""; String r="";
            switch(opc)
            {
                case 1: query="select r.descripcion rut, u.cuenta cue, u.nombre name, u.apellidos ape, u.cveTar tar, u.domicilio doc from usuario u inner join ruta r on r.cveRuta=u.cveRuta order by doc";
                    break;
                case 2: query="select DATEDIFF (mm,p.fEmision,GETDATE()) bim, r.descripcion rut, u.cuenta cue, u.cveTar tar, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta='1' order by u.cuenta";
                        r="1";
                    break;
                case 3: query="select DATEDIFF (mm,p.fEmision,GETDATE()) bim, r.descripcion rut, u.cuenta cue,  u.cveTar tar, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta='2' order by u.cuenta";
                        r="2";
                    break;
            }
            
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery(query);
            
            int cont=1;
            
            while(rs.next())
            {
//                FilasDatos[1]=rs.getString("rut")+"-"+rs.getString("cue");
//                FilasDatos[2]=rs.getString("name")+" "+rs.getString("ape");
                String cuenta=rs.getString("cue");
                int tarifa=rs.getInt("tar");
                String premes="";
                switch(tarifa)
                    {
                        case 1: premes=""+costos[0][0];
                            break;
                        case 2: premes=""+costos[1][0];
                            break;
                        case 3:  premes=""+costos[2][0];
                            break;
                        case 4: premes=""+maximos[3][0];
                            break;
                    }
                
                int bim=rs.getInt("bim");
                int ult=rs.getInt("ult");
                int ruta=rs.getInt("tar");
                int consumo=rs.getInt("act")-rs.getInt("ante");
                String ante=rs.getString("ante");
                String act=rs.getString("act");
                
                float total=getUltimo(ult,cuenta,ruta,consumo,bim);
                
                String activo=getFuncional(cuenta);
                if(activo.equalsIgnoreCase("1"))
                    total=0;
                
                float recargo=getRecargo();
                float drenaje=maximos[3][1];
                
                if(total!=0)
                {
                    switch(cont)
                    {
                        case 1:
                                    textfemi.setText(femi);
                                    txtvence.setText(fven);
                                    txtflimite.setText(fven);
                                    txtlectAnt.setText(ante);
                                    txtlectAct.setText(act);
                                    txtconsumo.setText(""+consumo);
                                    txtconsumido.setText(""+consumo);
                                    txtcuenta.setText(r+"-"+cuenta);
                                    txtcuen.setText(r+"-"+cuenta);
                                    txtrezap.setText(""+(recargo+drenaje));
                                    txtrecap.setText(""+recargo);
                                    txtpmap.setText(premes);
                                    txtpmalc.setText(""+drenaje);
                                    txttotal.setText(""+total);
                                    txtreza.setText(""+(recargo+drenaje));
                                    txtreca.setText(""+recargo);
                                    txtpma.setText(premes);
                                    txtpml.setText(""+drenaje);
                                    txttot.setText(""+total);
                                    
                                    cont+=1;
                            break;
                        case 2:
                                   textfemi1.setText(femi);
                                   txtvence1.setText(fven);
                                   txtflimite1.setText(fven);
                                   txtlectAnt1.setText(ante);
                                   txtlectAct1.setText(act);
                                   txtconsumo1.setText(""+consumo);
                                   txtconsumido1.setText(""+consumo);
                                   txtcuenta1.setText(r+"-"+cuenta);
                                   txtcuen1.setText(r+"-"+cuenta);
                                   txtrezap1.setText(""+(recargo+drenaje));
                                   txtrecap1.setText(""+recargo);
                                   txtpmap1.setText(premes);
                                   txtpmalc1.setText(""+drenaje);
                                   txttotal1.setText(""+total);
                                   txtreza1.setText(""+(recargo+drenaje));
                                    txtreca1.setText(""+recargo);
                                    txtpma1.setText(premes);
                                    txtpml1.setText(""+drenaje);
                                    txttot1.setText(""+total);
                                   
                                   cont+=1;
                            break;
                        case 3:     
                                    textfemi2.setText(femi);
                                    txtvence2.setText(fven);
                                    txtflimite2.setText(fven);
                                    txtlectAnt2.setText(ante);
                                    txtlectAct2.setText(act);
                                    txtconsumo2.setText(""+consumo);
                                    txtconsumido2.setText(""+consumo);
                                    txtcuenta2.setText(r+"-"+cuenta);
                                    txtcuen2.setText(r+"-"+cuenta);
                                    txtrezap2.setText(""+(recargo+drenaje));
                                    txtrecap2.setText(""+recargo);
                                    txtpmap2.setText(premes);
                                    txtpmalc2.setText(""+drenaje);
                                    txttotal2.setText(""+total);
                                    txtreza2.setText(""+(recargo+drenaje));
                                    txtreca2.setText(""+recargo);
                                    txtpma2.setText(premes);
                                    txtpml2.setText(""+drenaje);
                                    txttot2.setText(""+total);
                                    
                                    mandarAImpresora();
                                    cont=1;
                                    limpiar();
                            break;
                    }
                }
            }
           checarSiAunHay();
           Brut2.setEnabled(false);
           Brut1.setEnabled(false);
        }
        catch(Exception ex){}
    }
    
    private void checarSiAunHay()
    {
        if(!txtcuenta.getText().equals(""))
        {    
            if(txtcuenta1.getText().equals("") && txtcuenta2.getText().equals(""))
            {
                jPanel3.setVisible(false);
                jPanel4.setVisible(false);
            }
            if(!txtcuenta1.getText().equals("") && txtcuenta2.getText().equals(""))
                jPanel4.setVisible(false);
            jPanel1.setBackground(new Color(255,255,255));
            
            mandarAImpresora();
            limpiar();
        }
    }
    
    public String getFuncional(String clave)
    {
        String r="";
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select activo from anticipo where cuenta='"+clave+"'");
                while(rs.next())
                {   
                    r=rs.getString("activo");
                  //  System.out.print(r);
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        return r;
    }
    
    private float getRecargo() 
    {
        float reca=0;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select costo from recargo where cveRecargo='0'");
            
                while(rs.next())
                {   
                    reca=rs.getFloat("costo");
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        return reca;
    }
    
    private void llenarCuotas()
    {
      try
      {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select * from cuotas where cvecuota='1'");
            
                while(rs.next())
                {   
                    maximos[0][0]=rs.getInt("max1");
                    maximos[0][1]=rs.getInt("max2");
                    maximos[0][2]=rs.getInt("max3");
                    maximos[0][3]=rs.getInt("max4");
                    costos[0][0]=rs.getFloat("tari1");
                    costos[0][1]=rs.getFloat("tari2");
                    costos[0][2]=rs.getFloat("tari3");
                    costos[0][3]=rs.getFloat("tari4");
                }
            
            rs=sentencia.executeQuery("select * from cuotas where cvecuota='2'");
            
                while(rs.next())
                {   
                    maximos[1][0]=rs.getInt("max1");
                    maximos[1][1]=rs.getInt("max2");
                    maximos[1][2]=rs.getInt("max3");
                    maximos[1][3]=rs.getInt("max4");
                    costos[1][0]=rs.getFloat("tari1");
                    costos[1][1]=rs.getFloat("tari2");
                    costos[1][2]=rs.getFloat("tari3");
                    costos[1][3]=rs.getFloat("tari4");
                }    
                
            rs=sentencia.executeQuery("select * from cuotas where cvecuota='3'");
            
                while(rs.next())
                {   
                    maximos[2][0]=rs.getInt("max1");
                    maximos[2][1]=rs.getInt("max2");
                    maximos[2][2]=rs.getInt("max3");
                    maximos[2][3]=rs.getInt("max4");
                    costos[2][0]=rs.getFloat("tari1");
                    costos[2][1]=rs.getFloat("tari2");
                    costos[2][2]=rs.getFloat("tari3");
                    costos[2][3]=rs.getFloat("tari4");
                }
            rs=sentencia.executeQuery("select * from cuotas where cvecuota='4'");
            
                while(rs.next())
                {   
                    maximos[3][0]=rs.getInt("max1");
                    maximos[3][1]=rs.getInt("max2");
                    maximos[3][2]=rs.getInt("max3");
                    maximos[3][3]=rs.getInt("max4");
                    costos[3][0]=rs.getFloat("tari1");
                    costos[3][1]=rs.getFloat("tari2");
                    costos[3][2]=rs.getFloat("tari3");
                    costos[3][3]=rs.getFloat("tari4");
                }
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
    }
    
    private float getUltimo(int ult, String cuenta,int tarifa, int consumo,int bim)
    {
        
        float recargo=getRecargo();
        float total=0;
        
        if(tarifa==1 || tarifa==2 || tarifa==3)
        {
            if(consumo<maximos[tarifa-1][0])
                    {
                        total=2*costos[tarifa-1][0];
                        total+=(2*maximos[3][1]);
                        if(bim>2)
                            total+=((bim-2)*recargo);
                        total+=ult*costos[tarifa-1][0];
                        total+=ult*recargo;
                        total+=(ult*maximos[3][1]);
                    }
                    else
                    {
                         if(consumo>maximos[tarifa-1][0] && consumo<maximos[tarifa-1][1])
                         {
                             total=2*costos[tarifa-1][0];
                             total+=(2*maximos[3][1]);
                             consumo-=maximos[tarifa-1][0];
                             if(bim>2)
                                total+=((bim-2)*recargo);
                            total+=ult*costos[tarifa-1][0];
                            total+=ult*recargo;
                            total+=consumo*costos[tarifa-1][1];
                            total+=(ult*maximos[3][1]);
                         }
                         else
                         {
                             if(consumo>maximos[tarifa-1][1] && consumo<maximos[tarifa-1][2])
                             {
                                 total=2*costos[tarifa-1][0];
                                 total+=(2*maximos[3][1]);
                                 consumo-=maximos[tarifa-1][1];
                                 if(bim>2)
                                    total+=((bim-2)*recargo);
                                 total+=ult*costos[tarifa-1][0];
                                 total+=ult*recargo;
                                 total+=consumo*costos[tarifa-1][2];
                                 total+=(ult*maximos[3][1]);
                             }
                             else
                             {
                                 if(consumo>maximos[tarifa-1][2] && consumo<maximos[tarifa-1][3])
                                 {
                                    total=2*costos[tarifa-1][0];
                                    total+=(2*maximos[3][1]);
                                    consumo-=maximos[tarifa-1][2];
                                    if(bim>2)
                                        total+=((bim-2)*recargo);
                                    total+=ult*costos[tarifa-1][0];
                                    total+=ult*recargo;
                                    total+=consumo*costos[tarifa-1][3];
                                    total+=(ult*maximos[3][1]);
                                }
                                else
                                 {
                                     total=2*costos[tarifa-1][0];
                                    total+=(2*maximos[3][1]);
                                    consumo-=maximos[tarifa-1][3];
                                    if(bim>2)
                                        total+=((bim-2)*recargo);
                                    total+=ult*costos[tarifa-1][0];
                                    total+=ult*recargo;
                                    total+=consumo*costos[tarifa-1][3];
                                    total+=(ult*maximos[3][1]);
                                 }
                             }
                         }
                    }
        }
        else
        {
            total=2*maximos[3][0];
            total+=(2*maximos[3][1]);
            if(bim>2)
               total+=((bim-2)*recargo);
            total+=ult*maximos[3][0];
            total+=ult*recargo;
            total+=(ult*maximos[3][1]);
        }
        
        if(consumo==0)
            total=0;
        int cve,costo; cve=costo=0;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select * from instalación where cuenta='"+cuenta+"'");
            while(rs.next())
            {   
               cve=rs.getInt("revision");
               costo=rs.getInt("costo");
            }
        }
        catch(ClassNotFoundException | SQLException ex){}
        if(cve==2)
        {
            total+=costo;
        }
        return total;
    }
    
    
    private void mandarAImpresora()
    {
//        igualar();
        
        try
        {
           PrinterJob job= PrinterJob.getPrinterJob();
           PageFormat formaPagina = new PageFormat();
           formaPagina.setOrientation( PageFormat.LANDSCAPE); //Establecemos la orientación de la página
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
           
         //  guardarRecibo();
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
        
        jPanel1.paintAll(graf);
        return PAGE_EXISTS;
    }
}
