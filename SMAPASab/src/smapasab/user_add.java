package smapasab;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

public class user_add extends JInternalFrame implements ActionListener
{
    //Inicio delcracion de variables
    JLabel Lcuenta,Lname,Ltar,LDomic,Ltel,Lapell,Lruta,LNum,LfInst;
    JTextField Txtcuenta,Txtname,Txtdomic,Txttel,Txtapell,TxtNum; //txttel es para contrato
    JFormattedTextField TFfInst; MaskFormatter mascara = null;  //fecha de instalacion
    JButton Bguardar,Bcancel;
    JComboBox Ctar,Crut;
    Object[] T={"C.F.","Dom.","Com.","Ind."};
    Object[] R={"1","2"};
    //Fin declaracion de variabes
    public user_add()
    {
        super("Capturar contrato");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        setSize(330,400);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }
    
    public void crearInterfaz()
    {
        Border border=BorderFactory.createLineBorder(Color.CYAN);
        //creacion de labels
        Lcuenta=new JLabel("Cuenta");
        Lcuenta.setSize(100,20);
        Lcuenta.setLocation(20, 30);
        Ltar=new JLabel("Tarifa");
        Ltar.setSize(100,20);
        Ltar.setLocation(20, 70);
        Lruta=new JLabel("Ruta");
        Lruta.setSize(100,20);
        Lruta.setLocation(150, 70);
        Lname=new JLabel("Nombre");
        Lname.setSize(100,20);
        Lname.setLocation(20, 100);
        Lapell=new JLabel("Apellidos");
        Lapell.setSize(100,20);
        Lapell.setLocation(20, 130);
        LDomic=new JLabel("Domicilio");
        LDomic.setSize(100,20);
        LDomic.setLocation(20, 160);
        LNum=new JLabel("Numero");
        LNum.setSize(100,20);
        LNum.setLocation(20, 190);
        Ltel=new JLabel("Contrato");
        Ltel.setSize(100,20);
        Ltel.setLocation(20, 220);
        LfInst=new JLabel("Fecha inst.");
        LfInst.setSize(100,20);
        LfInst.setLocation(20, 250);
        
        Txtcuenta=new JTextField(6);
        Txtcuenta.setSize(100,20);
        Txtcuenta.setBorder(border);//BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        Txtcuenta.setLocation(90, 30);
        Txtcuenta.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtcuenta.getText().length()== 6) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                                            e.consume();}
                                        if (c == '.' && Txtcuenta.getText().contains(".")) {
                                            e.consume();}
                                     }
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Ctar=new JComboBox(T);
        Ctar.setSize(65,20);
        Ctar.setLocation(75, 70);
        
        Crut=new JComboBox(R);
        Crut.setSize(40,20);
        Crut.setLocation(190, 70);
        
        Txtname=new JTextField();
        Txtname.setSize(150,20);
        Txtname.setLocation(90, 100);
        Txtname.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtname.getText().length()== 15) e.consume();}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txtname.setBorder(border);
        Txtapell=new JTextField();
        Txtapell.setSize(150,20);
        Txtapell.setLocation(90, 130);
        Txtapell.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtapell.getText().length()== 20) e.consume();}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txtapell.setBorder(border);
        Txtdomic=new JTextField();
        Txtdomic.setSize(150,20);
        Txtdomic.setLocation(90, 160);
        Txtdomic.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtdomic.getText().length()== 25) e.consume();}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txtdomic.setBorder(border);
        TxtNum=new JTextField();
        TxtNum.setSize(50,20);
        TxtNum.setLocation(90, 190);
        TxtNum.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (TxtNum.getText().length()== 4) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        TxtNum.setBorder(border);
        Txttel=new JTextField();
        Txttel.setSize(100,20);
        Txttel.setLocation(90, 220);
        Txttel.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txttel.getText().length()== 5) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txttel.setBorder(border);
        try {mascara = new MaskFormatter("##-##-####"); } catch (ParseException ex) { } 
        TFfInst=new JFormattedTextField(mascara);
        TFfInst.setSize(100,20);
        TFfInst.setLocation(90, 250);
        TFfInst.setBorder(border);
        try {TFfInst.commitEdit(); } catch (ParseException ex1) { } 
        
        //Botones
        Bguardar=new JButton("Guardar",new ImageIcon("src/recursos/im_save.png"));
        Bguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bguardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bguardar.setVerticalAlignment(SwingConstants.CENTER);
        Bguardar.setSize(80, 70);
        Bguardar.setLocation(20, 280);
        Bguardar.addActionListener(this);
        
        
        Bcancel=new JButton("Cancelar",new ImageIcon("src/recursos/im_cancel.png"));
        Bcancel.setHorizontalTextPosition(SwingConstants.CENTER);
        Bcancel.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bcancel.setVerticalAlignment(SwingConstants.CENTER);
        Bcancel.setSize(85, 70);
        Bcancel.setLocation(110, 280);
        Bcancel.addActionListener(this);
        
        //agregando componentes
        add(Lcuenta);
        add(Lname);
        add(Ltar);
        add(LDomic);
        add(Ltel);
        add(Lapell);
        add(Lruta);
        add(LNum);
        add(LfInst);
        add(Txtcuenta);
        add(Txtname);
        add(Ctar);
        add(Txtdomic);
        add(Txttel);
        add(Txtapell);
        add(Crut);
        add(TxtNum);
        add(TFfInst);
        add(Bguardar);
        add(Bcancel);
    }
    public void cancelar()
    {
        if(Txtcuenta.getText().equals(""))
            this.dispose();
        else
        {
            int i =JOptionPane.showConfirmDialog(this,"¿Salir sin guardar?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(i==0)
               this.dispose();
        }
    }
    public void limpiar()
    {
        Txtcuenta.setText("");
        Txtname.setText("");
        Txtdomic.setText("");
        Txttel.setText("");
        Txtapell.setText("");
        TxtNum.setText("");
        TFfInst.setText("");
    }
    
    
    public String fecha()
    {
        String fecha="";
        if(!TFfInst.getText().equals("  -  -    "))
            fecha=TFfInst.getText();
        else
        {
            Calendar Cal= Calendar.getInstance();
            fecha= Cal.get(Cal.DATE)+"-"+(Cal.get(Cal.MONTH)+1)+"-"+Cal.get(Cal.YEAR);
        }
        //System.out.print(fecha);
        return fecha;
    }
    
    public String getTarifa()
    {
        String tarifa="";
        if(Ctar.getSelectedItem().equals("C.F."))
            tarifa="4";
        if(Ctar.getSelectedItem().equals("Dom."))
            tarifa="1";
        if(Ctar.getSelectedItem().equals("Com."))
            tarifa="2";
        if(Ctar.getSelectedItem().equals("Ind."))
            tarifa="3";
        
        return tarifa;
    }
    
    public void agregar()
    {
      if(!Txtname.getText().equals("") && !Txtcuenta.getText().equals(""))
      {
          
        String cuent0=Txtcuenta.getText().trim(); //obetener cuenta
        String fecha0=fecha();
        String tarifa=getTarifa();
        int i =JOptionPane.showConfirmDialog(this,"¿Confirma guardar la cuenta "+cuent0+" a nombre de "+Txtname.getText().trim()+"?","Confirmar",JOptionPane.YES_NO_OPTION);
        if(i==0)
        {
            int filas=0;
            try
            {
                //String agregar="insert into usuario values ('"+cuent0+"','"+Txtname.getText().trim()+"','"+Txtapell.getText().trim()+"','"+Txtdomic.getText().trim()+"','"+TxtNum.getText().trim()+"','"+tarifa+"','"+Crut.getSelectedItem()+"','"+Txttel.getText().trim()+"','"+fecha0+"')";
                String agregar="insert into usuario values ('"+cuent0+"','"+Txtname.getText().trim()+"','"+Txtapell.getText().trim()+"','"+Txtdomic.getText().trim()+"','"+TxtNum.getText().trim()+"','"+tarifa+"','"+Crut.getSelectedItem().toString()+"','"+Txttel.getText().trim()+"','"+fecha0+"')";
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                filas=sentencia.executeUpdate(agregar);
                if(filas>0)
                    JOptionPane.showMessageDialog(null, "Captura completa","Captura",JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "No se pudo completar la captura","Captura",JOptionPane.ERROR_MESSAGE);
                limpiar();
                
                instalacion(cuent0); //para el cobro por la instacion si es nuevo
            }
            catch(SQLException | HeadlessException ex){}
        }
      }
      else
      {
            JOptionPane.showMessageDialog(null, "Introduce cuenta y nombre para continuar","Captura",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public void instalacion(String cuenta)
    {
        int opc=1,inst=0;
        do
        {
           try
           {
              inst=Integer.parseInt(JOptionPane.showInputDialog("Costo de la instalación: "));
              opc=2;
           }
           catch(HeadlessException | NumberFormatException ex)
           {opc=1;}
           
           int i =JOptionPane.showConfirmDialog(null,"¿Confima el precio de "+inst+"?","Confirmar",JOptionPane.YES_NO_OPTION);
           if(i==0)
               opc=2;
           else
               opc=1;
           
        }while(opc==1);
        
        int filas=0;
            try
            {
                //String agregar="insert into usuario values ('"+cuent0+"','"+Txtname.getText().trim()+"','"+Txtapell.getText().trim()+"','"+Txtdomic.getText().trim()+"','"+TxtNum.getText().trim()+"','"+tarifa+"','"+Crut.getSelectedItem()+"','"+Txttel.getText().trim()+"','"+fecha0+"')";
                String agregar="insert into instalación values ('"+cuenta+"',"+inst+",'2');";
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                System.out.print(filas);
                filas=sentencia.executeUpdate(agregar);
                if(filas>0)
                    JOptionPane.showMessageDialog(null, "Captura completa","Captura",JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "No se pudo completar la captura","Captura",JOptionPane.ERROR_MESSAGE);
                
            }
            catch(SQLException | HeadlessException ex){}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       switch(e.getActionCommand())
       {
           case "Guardar": agregar();
               break;
           case "Cancelar": cancelar();
               break;
       }
    }
}
