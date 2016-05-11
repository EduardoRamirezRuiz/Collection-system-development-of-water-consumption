package smapasab;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Inicio extends JFrame implements ActionListener

{
    //Inicio declaracion de variables
    JMenuBar menuBar;
    JMenu Marchivo,Mpago,Mimpresos,Mutil,Mlect,Mayuda;
    JMenuItem itmcaptura, itmconsulta,itmcambios,itmbajas,itmreporte,itmcf,itmdome,itmcomer,itmindus,
               itmrecargo,itmcierre,itmcorte,itmcapLect,itmconsuLect,itmreportLect,itmrepPago,itmmayor,
                itmFact,itmrecibos,calculadora,itmsobre,itmanti;
    JDesktopPane dskpanel;
    //Fin Declaracion de variables
    public Inicio() 
    {
        super("S.M.A.P.A.");
        //UIManager.put("nimbusBase", new Color(220, 240, 255));
        UIManager.getBorder(this);
        UIManager.put("nimbusBlueGrey",new Color(220, 240, 255)); //Color.GRAY); //new Color(220, 240, 255)
        UIManager.put("control", new Color(245, 245, 245));
        try {

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
        
        dskpanel=new JDesktopPane();
        setContentPane(dskpanel);
        conecta();
        crearMenu();
        setJMenuBar(menuBar);
        //conectar();
        
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/agua.png")); setIconImage(icon);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        
    }
    private void conecta()
    {
        try
        {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            } catch (ClassNotFoundException ex) {}
            
            int filas=0;
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                filas=sentencia.executeUpdate("update anticipo set activo=2 where fTermino <= GETDATE()");
                if(filas>0)
                    System.out.print(""+filas);
                else
                    System.out.print(""+filas);
                
                sentencia.close();
                con.close();       
        }
        catch(SQLException | HeadlessException ex){}
    }
    
    public void crearMenu()
    {
        //variables locales (iconos de los menus)
        ImageIcon ImArch= new ImageIcon("src/recursos/folder.png");
        ImageIcon ImCap= new ImageIcon("src/recursos/add_costum.png");
        ImageIcon ImCon= new ImageIcon("src/recursos/report_consult.png");
        ImageIcon ImCam= new ImageIcon("src/recursos/page_refresh.png");
        ImageIcon ImBaj= new ImageIcon("src/recursos/page_delete.png");
        ImageIcon ImRep= new ImageIcon("src/recursos/report_user.png");
        ImageIcon ImPag= new ImageIcon("src/recursos/pago.png");
        //fin variables locales
        
        //crea la barra de menu
        menuBar=new JMenuBar();
        //comienza submenu archivo
        Marchivo=new JMenu();
        Marchivo.setIcon(ImArch);
        Marchivo.setText("Contratos");
        //Items de Archivo
        itmcaptura=new JMenuItem();
        itmcaptura.setIcon(ImCap);
        itmcaptura.setText("Captura");
        itmcaptura.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        itmcaptura.addActionListener(this);
        
        itmconsulta=new JMenuItem();
        itmconsulta.setIcon(ImCon);
        itmconsulta.setText("Consulta");
        itmconsulta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itmconsulta.addActionListener(this);
        
        itmcambios=new JMenuItem();
        itmcambios.setIcon(ImCam);
        itmcambios.setText("Cambios");
        itmcambios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itmcambios.addActionListener(this);
        
        itmbajas=new JMenuItem();
        itmbajas.setIcon(ImBaj);
        itmbajas.setText("Bajas");
        itmbajas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        itmbajas.addActionListener(this);
        
        itmreporte=new JMenuItem();
        itmreporte.setIcon(ImRep);
        itmreporte.setText("Reporte");
        itmreporte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        itmreporte.addActionListener(this);
        
        Marchivo.add(itmcaptura);
        Marchivo.add(itmconsulta);
        Marchivo.add(itmcambios);
        Marchivo.add(itmbajas);
        Marchivo.add(itmreporte);
        //termina de crear submenu archivo
        
        //menu de lecturas
        Mlect=new JMenu();
        Mlect.setIcon(new ImageIcon("src/recursos/lect.png"));
        Mlect.setText("Lectura");
        
        itmcapLect=new JMenuItem();
        itmcapLect.setText("Captura Lectura");
        itmcapLect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        itmcapLect.addActionListener(this);
        itmcapLect.setIcon(new ImageIcon("src/recursos/lectu.png"));
        itmconsuLect=new JMenuItem();
        itmconsuLect.setText("Modificar Lectura");
        itmconsuLect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        itmconsuLect.addActionListener(this);
        itmconsuLect.setIcon(new ImageIcon("src/recursos/lect_modif.png"));
        itmreportLect=new JMenuItem();
        itmreportLect.setText("Reporte Lectura");
        itmreportLect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        itmreportLect.addActionListener(this);
        itmreportLect.setIcon(new ImageIcon("src/recursos/lect_report.png"));
        //menu lectura
        Mlect.add(itmcapLect);
        Mlect.add(itmconsuLect);
        Mlect.add(itmreportLect);
        
        //Menu PAGOS
        Mpago=new JMenu();
        Mpago.setIcon(new ImageIcon("src/recursos/money.png"));
        Mpago.setText("Pagos");
        
        itmcierre=new JMenuItem();
        itmcierre.setIcon(new ImageIcon("src/recursos/money_mas.png"));
        itmcierre.setText("Capturar");
        itmcierre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        itmcierre.addActionListener(this);
        itmcorte=new JMenuItem();
        itmcorte.setText("Faltantes");
        itmcorte.setIcon(new ImageIcon("src/recursos/money_falt.png"));
        itmcorte.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itmcorte.addActionListener(this);
        itmrepPago=new JMenuItem();
        itmrepPago.setText("Modificación");
        itmrepPago.setIcon(new ImageIcon("src/recursos/money_modif.png"));
        itmrepPago.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        itmrepPago.addActionListener(this);        
        
        //agregar al menu bimestre
        Mpago.add(itmcierre);
        Mpago.add(itmcorte);
        Mpago.add(itmrepPago);
                
        //menu utilerias
        Mutil=new JMenu();
        Mutil.setIcon(new ImageIcon("src/recursos/util.png"));
        Mutil.setText("Utilerias");
        //items utileria
        itmcf=new JMenuItem();
        itmcf.setIcon(new ImageIcon("src/recursos/coins.png"));
        itmcf.setText("CF,%,IVA");
        itmcf.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        itmcf.addActionListener(this);
        itmdome=new JMenuItem();
        itmdome.setIcon(new ImageIcon("src/recursos/home.png"));
        itmdome.setText("T. Domestica");
        itmdome.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itmdome.addActionListener(this);
        itmcomer=new JMenuItem();
        itmcomer.setIcon(new ImageIcon("src/recursos/store.png"));
        itmcomer.setText("T. Comercial");
        itmcomer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        itmcomer.addActionListener(this);
        itmindus=new JMenuItem();
        itmindus.setIcon(new ImageIcon("src/recursos/indus.png"));
        itmindus.setText("T. Industrial");
        itmindus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        itmindus.addActionListener(this);
        itmanti=new JMenuItem();
        itmanti.setIcon(new ImageIcon("src/recursos/anti.png"));
        itmanti.setText("Anticipo");
        itmanti.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        itmanti.addActionListener(this);
        itmrecargo=new JMenuItem();
        itmrecargo.setIcon(new ImageIcon("src/recursos/recargo.png"));
        itmrecargo.setText("Recargo");
        itmrecargo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        itmrecargo.addActionListener(this);
        itmFact=new JMenuItem();
        itmFact.setIcon(new ImageIcon("src/recursos/im_fact.png"));
        itmFact.setText("Factura");
        itmFact.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        itmFact.addActionListener(this);      
        calculadora=new JMenuItem();
        calculadora.setIcon(new ImageIcon("src/recursos/calc.png"));
        calculadora.setText("Calculadora");
        calculadora.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        calculadora.addActionListener(this);      
        
        Mutil.add(itmcf);
        Mutil.add(itmdome);
        Mutil.add(itmcomer);
        Mutil.add(itmindus);
        Mutil.add(itmrecargo);
        Mutil.add(itmanti);
        Mutil.add(itmFact);
        Mutil.add(calculadora);
        //menu impresos
        Mimpresos=new JMenu();
        Mimpresos.setIcon(new ImageIcon("src/recursos/printer.png"));
        Mimpresos.setText("Impresos");
        itmrecibos=new JMenuItem();
        itmrecibos.setText("Recibos");
        itmrecibos.setIcon(new ImageIcon("src/recursos/print_recib.png"));
        itmrecibos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        itmrecibos.addActionListener(this);        
        itmmayor=new JMenuItem();
        itmmayor.setText("Adeudo mayor");
        itmmayor.setIcon(new ImageIcon("src/recursos/print_mayor.png"));
        itmmayor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        itmmayor.addActionListener(this);        
        
        Mimpresos.add(itmrecibos);
        Mimpresos.add(itmmayor);
        
        //menu ayuda
        Mayuda=new JMenu();
        Mayuda.setIcon(new ImageIcon("src/recursos/help.png"));
        Mayuda.setText("Ayuda");
        itmsobre=new JMenuItem();
        itmsobre.setText("Acerca de");
        itmsobre.setIcon(new ImageIcon("src/recursos/sobre.png"));
        itmsobre.addActionListener(this);        
        
        Mayuda.add(itmsobre);
        
        //agregue de menus
        menuBar.add(Marchivo);
        menuBar.add(Mlect);
        menuBar.add(Mpago);
        menuBar.add(Mimpresos);
        menuBar.add(Mutil);
        menuBar.add(Mayuda);
        
    }   

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        switch(e.getActionCommand())
        {
            case "Captura": dskpanel.add(new user_add()); //new prueba();
                break;
            case "Consulta": dskpanel.add(new User_consulta());
                break;
            case "Cambios": dskpanel.add(new user_change());
                break;
            case "Bajas": dskpanel.add(new user_delete());
                break;
            case "Reporte": dskpanel.add(new user_reports());
                break;
            case "CF,%,IVA": dskpanel.add(new CuotaF());
                break;
            case "T. Domestica":  dskpanel.add(new CuotaD());
                break;
            case "T. Comercial":  dskpanel.add(new cuotaC());
                break;
            case "T. Industrial":  dskpanel.add(new cuotaI());
                break;
            case "Recargo":  dskpanel.add(new recargo());
                break;
            case "Captura Lectura": dskpanel.add(new lect_add());
                break;
            case "Modificar Lectura": dskpanel.add(new lect_change());
                break;
            case "Reporte Lectura": dskpanel.add(new lect_report());
                break;
            case "Capturar": dskpanel.add(new pay_add());
                break;
            case "Faltantes": dskpanel.add(new pay_miss());
                break;
            case "Modificación": dskpanel.add(new modif_ult_pago());
                break;
            case "Adeudo mayor": dskpanel.add(new mayorA());
                break;
            case "Factura": //System.out.println("agua");
                                dskpanel.add(new impr_factura());
                break;
            case "Calculadora": try
                                {
                                    Runtime obj = Runtime.getRuntime();
                                    obj.exec("C:\\WINDOWS\\system32\\CALC.EXE");
                                }                   
                                catch(Exception ex)
                                {
                                    JOptionPane.showMessageDialog(this, ex.getMessage());
                                }
                break;
            case "Acerca de": JOptionPane.showMessageDialog(this,
                                "          ''SMAPA El SABINO''" +
                                "\nSISTEMA DE PAGOS PARA LA S.M.A.P.A.S."+
                                "\nEL SABINO, GTO."+
                                "\nVERSION: 1.0"+
                                "\nPROHIBIDA LA REPRODUCCION TOTAL O PARCIAL,"+
                                "\nSIN LA AUTORIZACION POR ESCRITO DEL AUTOR",
                                "ACERCA DE...",
                                JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Anticipo":  dskpanel.add(new anticipo());
                break;
            case "Recibos":  dskpanel.add(new recibos());
                break;
        }
    }
    
}
