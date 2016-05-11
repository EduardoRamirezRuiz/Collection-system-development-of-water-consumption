package smapasab;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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

public class user_change extends JInternalFrame implements ActionListener
{
    //Inicio delcracion de variables
    JLabel Lcuenta,Lname,Ltar,LDomic,Ltel,Lapell,Lruta,LNum,LfInst;
    JTextField Txtcuenta,Txtname,Txtdomic,Txttel,Txtapell,TxtNum;
    JButton Bbuscar,Bguardar,Bcancel;
    JFormattedTextField TFfInst; MaskFormatter mascara = null;  //fecha de instalacion
    JComboBox Ctar,Crut;
    Object[] T={"C.F.","Dom.","Com.","Ind."};
    Object[] R={"1","2"};
    //Fin declaracion de variabes
    public user_change()
    {
        super("Actualizacion de contratos");
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
        Lruta.setLocation(170, 70);
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
        Txtcuenta.setBorder(border);
        Ctar=new JComboBox(T);
        Ctar.setSize(65,20);
        Ctar.setLocation(75, 70);
        Ctar.setEnabled(false);
        Crut=new JComboBox(R);
        Crut.setSize(40,20);
        Crut.setLocation(210, 70);
        Crut.setEnabled(false);
        
        Txtname=new JTextField();
        Txtname.setSize(150,20);
        Txtname.setLocation(90, 100);
        Txtname.setEnabled(false);
        Txtname.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtcuenta.getText().length()== 15) e.consume();}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txtname.setBorder(border);
        Txtapell=new JTextField();
        Txtapell.setSize(150,20);
        Txtapell.setLocation(90, 130);
        Txtapell.setEnabled(false);
        Txtapell.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtcuenta.getText().length()== 20) e.consume();}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txtapell.setBorder(border);
        Txtdomic=new JTextField();
        Txtdomic.setSize(150,20);
        Txtdomic.setLocation(90, 160);
        Txtdomic.setEnabled(false);
        Txtdomic.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txtcuenta.getText().length()== 25) e.consume();}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txtdomic.setBorder(border);
        TxtNum=new JTextField();
        TxtNum.setSize(50,20);
        TxtNum.setLocation(90, 190);
        TxtNum.setEnabled(false);
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
        Txttel.setEnabled(false);
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
        try {mascara = new MaskFormatter("####-##-##"); } catch (ParseException ex) { } 
        TFfInst=new JFormattedTextField(mascara);
        TFfInst.setSize(100,20);
        TFfInst.setLocation(90, 250);
        TFfInst.setEnabled(false);
        TFfInst.setBorder(border);
        try {TFfInst.commitEdit(); } catch (ParseException ex1) { } 
        
        //Botones
        Bbuscar=new JButton("Buscar",new ImageIcon("src/recursos/im_search.png"));
        Bbuscar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bbuscar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bbuscar.setVerticalAlignment(SwingConstants.CENTER);
        Bbuscar.setSize(80, 70);
        Bbuscar.setLocation(20, 280);
        Bbuscar.addActionListener(this);
        
        Bguardar=new JButton("Actualizar",new ImageIcon("src/recursos/im_refresh.png"));
        Bguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bguardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bguardar.setVerticalAlignment(SwingConstants.CENTER);
        Bguardar.setSize(80, 70);
        Bguardar.setLocation(115, 280);
        Bguardar.addActionListener(this);
        
        Bcancel=new JButton("Cancelar",new ImageIcon("src/recursos/im_cancel.png"));
        Bcancel.setHorizontalTextPosition(SwingConstants.CENTER);
        Bcancel.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bcancel.setVerticalAlignment(SwingConstants.CENTER);
        Bcancel.setSize(85, 70);
        Bcancel.setLocation(210, 280);
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
        add(Bbuscar);
        add(Bguardar);
        add(Bcancel);
    }
    public void cancelar()
    {
        if(Txtcuenta.getText().equals(""))
            this.dispose();
        else
        {
            int i =JOptionPane.showConfirmDialog(this,"¿Salir sin guardar cambios?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(i==0)
               this.dispose();
        }
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
    
    public void guardar()
    {
        if(!Txtname.getText().equals("") && !TFfInst.getText().equals(""))
        {
          int filas=0;
         try
         {
            String tarifa=getTarifa();
            String actualizar="update usuario set nombre='"+Txtname.getText().trim()+"', apellidos='"+Txtapell.getText().trim()+"', domicilio='"+Txtdomic.getText().trim()+"',numero='"+TxtNum.getText().trim()+"',cveRuta='"+Crut.getSelectedItem().toString()+"',cveTar='"+tarifa+"',telefono='"+Txttel.getText().trim()+"',fInst='"+TFfInst.getText()+"' where cuenta='"+Txtcuenta.getText().trim()+"'";
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            filas=sentencia.executeUpdate(actualizar);
            if(filas>0)
                JOptionPane.showMessageDialog(null, "Actualización completa","Actualización",JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No se pudo completar la actualización","Actualización",JOptionPane.ERROR_MESSAGE);
            limpiar();
            Txtcuenta.setEnabled(true);
         }
         catch(SQLException | HeadlessException ex){}
        }
        else
            JOptionPane.showMessageDialog(null, "Asegurese de escibir un nombre y una fecha de instalación","Actualización",JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrar()
    {
        Txtcuenta.setEnabled(false);
        Txtname.setEnabled(true);
        Ctar.setEnabled(true);
        Txtdomic.setEnabled(true);
        Txttel.setEnabled(true);
        Txtapell.setEnabled(true);
        Crut.setEnabled(true);
        TxtNum.setEnabled(true);
        TFfInst.setEnabled(true);
    }
    
    public void limpiar()
    {
        Txtcuenta.setText("");
        Txtname.setText("");
        Ctar.setEnabled(false);
        Txtdomic.setText("");
        Txttel.setText("");
        Txtapell.setText("");
        Crut.setEnabled(false);
        TxtNum.setText("");
        TFfInst.setText("");
    }
    public void buscar()
    {
        String clave="";
      if(!Txtcuenta.getText().equals(""))
      {
            clave=Txtcuenta.getText().trim();
        
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select usuario.cuenta Cue,usuario.nombre Name,usuario.apellidos LN, cuotas.cvecuota Tar, ruta.cveRuta Rut, usuario.domicilio doc,usuario.numero num, usuario.telefono tel, usuario.fInst fecha from usuario inner join cuotas on usuario.cveTar=cuotas.cvecuota inner join ruta on usuario.cveRuta=ruta.cveRuta where usuario.cuenta='"+clave+"'");
            limpiar();
                while(rs.next())
                {   
                    Txtcuenta.setText(rs.getString("Cue"));
                    Txtname.setText(rs.getString("Name"));
                    Txtapell.setText(rs.getString("LN"));
                    String tar=rs.getString("Tar");
                    String rut=rs.getString("Rut");
                    Txtdomic.setText(rs.getString("doc"));
                    TxtNum.setText(rs.getString("num"));
                    Txttel.setText(rs.getString("tel"));
                    TFfInst.setText(rs.getString("fecha"));
                    
                    if(tar.equalsIgnoreCase("1"))
                        Ctar.setSelectedIndex(1);
                    if(tar.equalsIgnoreCase("2"))
                        Ctar.setSelectedIndex(2);
                    if(tar.equalsIgnoreCase("3"))
                        Ctar.setSelectedIndex(3);
                    if(tar.equalsIgnoreCase("4"))
                        Ctar.setSelectedIndex(0);
                    if(rut.equalsIgnoreCase("1"))
                        Crut.setSelectedIndex(0);
                    else
                        Crut.setSelectedIndex(1);
                }
           
                if(Txtname.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "No se encontraron registros","Busqueda",JOptionPane.ERROR_MESSAGE);
                else
                    mostrar();
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {/*System.out.println(ex);*/}
      }
      else
      {
            JOptionPane.showMessageDialog(null, "Indroduce clave","Busqueda",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       switch(e.getActionCommand())
       {
           case "Buscar": buscar();
               break;
           case "Actualizar": guardar();
               break;
           case "Cancelar": cancelar();
               break;
       }
    }
}