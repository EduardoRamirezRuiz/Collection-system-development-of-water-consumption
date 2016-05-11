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
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class lect_add extends JInternalFrame implements ActionListener
{
    //delcracion de variables
    JLabel Lcuenta,Lname,LDomic,LLAnt,LLNueva,Lruta,Lapell;
    JTextField Txtcuenta,Txtname,Txtdomic,TxtLAnt,TxtLNue,Txtapell;
    JComboBox Ctar,Crut; Object[] R={"1","2"};
    JButton Bbuscar,BGuardar;
    boolean duda_exitia=false; //saber si ya existia registro de algun pago del usuario
    //fin declaracion
    public lect_add()
    {
        super("Captura de Lectura");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        setSize(260,380);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }

    private void crearInterfaz() 
    {
        Border border=BorderFactory.createLineBorder(Color.CYAN);
        //creacion de labels
        Lcuenta=new JLabel("Cuenta");
        Lcuenta.setSize(100,20);
        Lcuenta.setLocation(20, 30);
        Lruta=new JLabel("Ruta");
        Lruta.setSize(100,20);
        Lruta.setLocation(20, 70);
        Lname=new JLabel("Nombre");
        Lname.setSize(100,20);
        Lname.setLocation(20, 100);
        Lapell=new JLabel("Apellidos");
        Lapell.setSize(100,20);
        Lapell.setLocation(20, 130);
        LDomic=new JLabel("Domicilio");
        LDomic.setSize(100,20);
        LDomic.setLocation(20, 160);
        LLAnt=new JLabel("L. Anterior");
        LLAnt.setSize(100,20);
        LLAnt.setLocation(20, 190);
        LLNueva=new JLabel("L. Nueva");
        LLNueva.setSize(100,20);
        LLNueva.setLocation(20, 220);
        
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
        Crut=new JComboBox(R);
        Crut.setSize(40,20);
        Crut.setLocation(90, 70);
        Crut.setEnabled(false);
        Txtname=new JTextField();
        Txtname.setSize(150,20);
        Txtname.setLocation(90, 100);
        Txtname.setEnabled(false);
        Txtname.setBorder(border);
        Txtapell=new JTextField();
        Txtapell.setSize(150,20);
        Txtapell.setLocation(90, 130);
        Txtapell.setEnabled(false);
        Txtapell.setBorder(border);
        Txtdomic=new JTextField();
        Txtdomic.setSize(150,20);
        Txtdomic.setLocation(90, 160);
        Txtdomic.setEnabled(false);
        Txtdomic.setBorder(border);
        TxtLAnt=new JTextField();
        TxtLAnt.setSize(50,20);
        TxtLAnt.setLocation(90, 190);
        TxtLAnt.setEnabled(false);
        TxtLAnt.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {
                                         if (TxtLAnt.getText().length()== 9) e.consume();
                                         char c= e.getKeyChar();
                                         if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        TxtLAnt.setBorder(border);
        TxtLNue=new JTextField();
        TxtLNue.setSize(100,20);
        TxtLNue.setLocation(90, 220);
        TxtLNue.setEnabled(false);
        TxtLNue.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {
                                         if (TxtLNue.getText().length()== 9) e.consume();
                                         char c= e.getKeyChar();
                                         if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        TxtLNue.setBorder(border);
        //Botones
        Bbuscar=new JButton("Buscar",new ImageIcon("src/recursos/im_search.png"));
        Bbuscar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bbuscar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bbuscar.setVerticalAlignment(SwingConstants.CENTER);
        Bbuscar.setSize(80, 70);
        Bbuscar.setLocation(20, 260);
        Bbuscar.addActionListener(this);
        
        BGuardar=new JButton("Guardar",new ImageIcon("src/recursos/im_save.png"));
        BGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        BGuardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        BGuardar.setVerticalAlignment(SwingConstants.CENTER);
        BGuardar.setSize(85, 70);
        BGuardar.setLocation(120, 260);
        BGuardar.addActionListener(this);
        
        //agregando componentes
        add(Lcuenta);
        add(Lname);
        add(LDomic);
        add(Lapell);
        add(Lruta);
        add(LLAnt);
        add(LLNueva);
        add(Txtcuenta);
        add(Txtname);
        add(Txtdomic);
        add(Txtapell);
        add(Crut);
        add(TxtLAnt);
        add(TxtLNue);
        add(Bbuscar);
        add(BGuardar);
    }
    
    public String getFecha()
    {
        String fecha="";
            Calendar Cal= Calendar.getInstance();
            fecha= Cal.get(Cal.DATE)+"-"+(Cal.get(Cal.MONTH)+1)+"-"+Cal.get(Cal.YEAR);
        //System.out.print(fecha);
        return fecha;
    }
    
    public void guardar()
    {
        String actualizar=""; int Lnueva=0,LAnt=0; boolean mayorque=false;
        try
        {
            Lnueva=Integer.parseInt(TxtLNue.getText());
            LAnt=Integer.parseInt(TxtLAnt.getText());
            if(Lnueva>=LAnt)
                mayorque=true;
        }
        catch(Exception ex)
        {}
        if(!TxtLAnt.getText().equals("") && !TxtLNue.getText().equals("") && mayorque)
        {
          int i =JOptionPane.showConfirmDialog(this,"¿Confirma guardar la lectura de "+Txtcuenta.getText().trim()+" con consumo de "+(Lnueva-LAnt)+"?\nL. Anterior "+LAnt+"\nL. Nueva  "+Lnueva,"Confirmar",JOptionPane.YES_NO_OPTION);
          if(i==0)
          {
            int filas=0;
            String fecha=getFecha();
            try
            {
                if(duda_exitia)
                    actualizar="update pago set lectAct="+TxtLNue.getText().trim()+",fEmision=GETDATE() where cuenta='"+Txtcuenta.getText().trim()+"'";
                else
                    actualizar="insert into pago values	('"+Txtcuenta.getText().trim()+"','"+fecha+"','"+fecha+"','"+fecha+"',"+LAnt+","+Lnueva+");";
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                filas=sentencia.executeUpdate(actualizar);
                if(filas>0)
                    JOptionPane.showMessageDialog(null, "Proceso completado","Captura",JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "No se pudo completar el proceso","Captura",JOptionPane.ERROR_MESSAGE);
                limpiar(true);
                Txtcuenta.setEnabled(true);
            }
            catch(SQLException | HeadlessException ex){}
          }
        }
        else
        {
            if(TxtLAnt.getText().equals("") || TxtLNue.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Asegurese de escibir las lecturas","Guardar",JOptionPane.ERROR_MESSAGE);
            else
                if(mayorque==false)
                        JOptionPane.showMessageDialog(null, "La nueva lectura debe ser mayor a la anterior","Guardar",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void limpiar(boolean bandera)
    {
        Txtcuenta.setText("");
        Txtcuenta.setEnabled(bandera);
        Txtname.setText("");
        Txtdomic.setText("");
        Txtapell.setText("");
        //Crut.setEnabled(bandera);
        TxtLAnt.setText("");
        TxtLNue.setText("");
        //TxtLNue.setEnabled(bandera);
    }
    
    public void buscar()
    {
        BGuardar.setEnabled(true);
        String clave="";
      if(!Txtcuenta.getText().equals(""))
      {
            clave=Txtcuenta.getText().trim();
        
        
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select u.cuenta Cue, u.nombre Name, u.domicilio Adre,u.apellidos Apell, u.cveRuta rut, p.lectAct Ante from pago p inner join usuario u on u.cuenta=p.cuenta where p.cuenta='"+clave+"'");
            limpiar(false);
                while(rs.next())
                {   
                    Txtcuenta.setText(rs.getString("Cue"));
                    Txtname.setText(rs.getString("Name"));
                    Txtdomic.setText(rs.getString("Adre"));
                    Txtapell.setText(rs.getString("Apell"));
                    String rut=rs.getString("rut");
                    if(rut.equalsIgnoreCase("1"))
                        Crut.setSelectedIndex(0);
                    else
                        Crut.setSelectedIndex(1);
                    Crut.setEnabled(false);
                    TxtLAnt.setText(rs.getString("Ante"));
                    TxtLNue.setEnabled(true);
                    
                    duda_exitia=true;
                }
           
                if(Txtname.getText().equals(""))
                    nohayPagoRegistrado(clave);
                    //JOptionPane.showMessageDialog(null, "No se encontraron registros","Busqueda",JOptionPane.ERROR_MESSAGE);
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {}
      }
      else
      {
            JOptionPane.showMessageDialog(null, "Indroduce clave","Busqueda",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public void nohayPagoRegistrado(String clave)
    {
        duda_exitia=false;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select cuenta,nombre,apellidos,domicilio,cveRuta from usuario where cuenta='"+clave+"'");
            limpiar(false);
                while(rs.next())
                {   
                    Txtcuenta.setText(rs.getString("cuenta"));
                    Txtname.setText(rs.getString("nombre"));
                    Txtdomic.setText(rs.getString("domicilio"));
                    Txtapell.setText(rs.getString("apellidos"));
                    String rut=rs.getString("cveRuta");
                    if(rut.equalsIgnoreCase("1"))
                        Crut.setSelectedIndex(0);
                    else
                        Crut.setSelectedIndex(1);
                    Crut.setEnabled(false);
                    TxtLAnt.setEnabled(true);
                    TxtLNue.setEnabled(true);
                    int opc=1,lant=0;
                    do
                    {
                        try
                        {
                            lant=Integer.parseInt(JOptionPane.showInputDialog("No hay registros sobre pagos de este usuario\nIntroduzca una lectura anterior\nSi no hay lectur anterior o es nuevo, introzca 0"));
                            opc=2;
                        }
                        catch(HeadlessException | NumberFormatException ex)
                        {opc=1;}
                        
                    }while(opc==1);
                    if(opc==2)
                        TxtLAnt.setText(""+lant);
                }
           
                if(Txtname.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Usuario no registrado","Busqueda",JOptionPane.ERROR_MESSAGE);
                    BGuardar.setEnabled(false);
                    limpiar(true);
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        //JOptionPane.showMessageDialog(null, "No se encontraron registros","Busqueda",JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        switch(e.getActionCommand())
       {
           case "Buscar": buscar();
               break;
           case "Guardar": guardar();
               break;
       }
    }
}
