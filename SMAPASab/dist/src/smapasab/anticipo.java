package smapasab;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class anticipo extends JInternalFrame implements ActionListener
{
    //delcracion de variables
    JLabel Lcuenta,Lname,Lanti,Ltari,Lcosto;
    JTextField Txtcuenta,Txtname,txttari,txtcosto;
////////////    JComboBox Ctar,Crut; Object[] R={"1","2"};
    JButton Bbuscar,BGuardar,Blimpiar;
    JComboBox CbxAnti;
    Object[] T={"select","Medio año","Año"};
    JCheckBox chname,chcuenta;
    TextAutoCompleter textAutoCompleter;
     int [][] maximos=new int [4][4]; float [][] costos=new float [4][4];
     float total=0;
////////////    boolean duda_exitia=false; //saber si ya existia registro de algun pago del usuario
    //fin declaracion
    
    public anticipo()
    {
        super("Anticipo");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        setSize(340,380);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }
    
    private void crearInterfaz() 
    {
        Border border=BorderFactory.createLineBorder(Color.CYAN);
        //checkbox
        JLabel ch1= new JLabel("Nombre"),ch2= new JLabel("Cuenta");
        ch1.setSize(100,20); ch2.setSize(100,20);
        ch1.setLocation(32, 35); ch2.setLocation(100, 35);
        add(ch1); add(ch2);
        chname=new JCheckBox("");
        chname.setSize(18, 20);
        chname.setLocation(10, 35);
        chname.setSelected(true);
        chname.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                chcuenta.setSelected(false);
                Txtcuenta.setEnabled(false);
                Txtname.setEnabled(true);
            }
        });
        add(chname);
        
        chcuenta=new JCheckBox("");
        chcuenta.setSize(18, 20);
        chcuenta.setLocation(80, 35);
        chcuenta.setSelected(false);
        chcuenta.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e) 
            {
                chname.setSelected(false);
                Txtcuenta.setEnabled(true);
                Txtname.setEnabled(false);
            }
        });
        add(chcuenta);
        
        //creacion de labels
        JLabel titulo= new JLabel("Buscar por: ");
        titulo.setSize(100,20);
        titulo.setLocation(20, 10);
        add(titulo);
        Lcuenta=new JLabel("Cuenta");
        Lcuenta.setSize(100,20);
        Lcuenta.setLocation(20, 100);
        Lname=new JLabel("Nombre");
        Lname.setSize(100,20);
        Lname.setLocation(20, 70);
        Lanti=new JLabel("Anticipo");
        Lanti.setSize(100,20);
        Lanti.setLocation(20, 130);
        Ltari=new JLabel("Tarifa");
        Ltari.setSize(100,20);
        Ltari.setLocation(20, 160);
        Lcosto=new JLabel("COSTO");
        Lcosto.setSize(140,20);
        Lcosto.setLocation(20, 190);
        
        Txtcuenta=new JTextField(6);
        Txtcuenta.setSize(100,20);
        Txtcuenta.setLocation(90, 100);
        Txtcuenta.setEnabled(false);
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
        Txtname=new JTextField();
        Txtname.setSize(150,20);
        Txtname.setLocation(90, 70);
        Txtname.setEnabled(true);
        Txtname.setBorder(border);
        CbxAnti=new JComboBox(T);
        CbxAnti.setSize(150,20);
        CbxAnti.setLocation(90, 130);
        CbxAnti.setEnabled(true);
        CbxAnti.setBorder(border);
        
        txttari=new JTextField();
        txttari.setSize(150,20);
        txttari.setLocation(90, 160);
        txttari.setEnabled(false);
        txttari.setBorder(border);
        txtcosto=new JTextField();
        txtcosto.setSize(50,20);
        txtcosto.setLocation(160, 190);
        txtcosto.setEnabled(false);
        txtcosto.setBorder(border);
        //Botones
        Bbuscar=new JButton("Buscar",new ImageIcon("src/recursos/im_search.png"));
        Bbuscar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bbuscar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bbuscar.setVerticalAlignment(SwingConstants.CENTER);
        Bbuscar.setSize(80, 70);
        Bbuscar.setLocation(20, 260);
        Bbuscar.addActionListener(this);
        
        BGuardar=new JButton("Pagar",new ImageIcon("src/recursos/money.png"));
        BGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        BGuardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        BGuardar.setVerticalAlignment(SwingConstants.CENTER);
        BGuardar.setSize(85, 70);
        BGuardar.setLocation(220, 260);
        BGuardar.addActionListener(this);
        
        Blimpiar=new JButton("Limpiar",new ImageIcon("src/recursos/im_clean.png"));
        Blimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
        Blimpiar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Blimpiar.setVerticalAlignment(SwingConstants.CENTER);
        Blimpiar.setSize(85, 70);
        Blimpiar.setLocation(120, 260);
        Blimpiar.addActionListener(this);
        
        //agregando componentes
        add(Lcuenta);
        add(Lname);
        add(Lanti);
        add(Ltari);
        add(Lcosto);
        add(Txtcuenta);
        add(Txtname);
        add(CbxAnti);
        add(txttari);
        add(txtcosto);
        
        add(Bbuscar);
        add(BGuardar);
        add(Blimpiar);
        
        llenarCuotas();
        //cargar nombres al textfield nombres para autocpletar
        cargarNombres();
    }
    
    public void cargarNombres()
    {
        int numCuentas=0,i=0;
        textAutoCompleter = new TextAutoCompleter(Txtname);
        textAutoCompleter.setMode(0);
        
        //sacar cantidad de cuentas en la tabla
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select COUNT(u.cuenta) cue from usuario u inner join pago p on p.cuenta=u.cuenta");
                while(rs.next())
                {  numCuentas=rs.getInt("cue"); }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {JOptionPane.showMessageDialog(null, "Ocurrio un problema al cargar","Cargar data",JOptionPane.ERROR_MESSAGE);}
      if(numCuentas!=0)
      {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select u.nombre name,u.apellidos apell from usuario u inner join pago p on p.cuenta=u.cuenta");
                while(rs.next())
                {   
                    textAutoCompleter.addItem(rs.getString("name")+"-"+rs.getString("apell"));
                }
            
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {JOptionPane.showMessageDialog(null, "Ocurrio un problema al cargar","Cargar data",JOptionPane.ERROR_MESSAGE);}
      }
      else
      {
          JOptionPane.showMessageDialog(null, "No se han cargado nombres\nBusque en base a cuenta","ERROR",JOptionPane.ERROR_MESSAGE);
          chcuenta.setSelected(true);
          chname.setSelected(false);
      }
     
    }
    
    private String getClave()
    {
        String clave="";
        if(chname.isSelected()==true)
        {
            String cadena=Txtname.getText();
            StringTokenizer st=new StringTokenizer(cadena, "-", false);
            while(st.hasMoreTokens()) 
            {
                try{
                clave="'"+st.nextToken()+"' and apellidos='"+st.nextToken()+"'";
                }catch(Exception ex){}
            }
        }
        if(chcuenta.isSelected()==true)
        {
            clave=Txtcuenta.getText().trim();
        }
        
        return clave;
    }
    
    private int getOpcion()
    {
        int clave=0;
        if(chname.isSelected()==true)
        {
            clave=1;
        }
        if(chcuenta.isSelected()==true)
        {
            clave=2;
        }
        
        return clave;
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
    
    public int getAnti()
    {
        int anti=0;
        switch(""+CbxAnti.getSelectedItem())
        {
            case "Medio año": anti=6;
                break;
            case "Año": anti=12;
                break;
            case "Select": JOptionPane.showMessageDialog(null, "Seleccione un tipo de anticipo","Selección",JOptionPane.ERROR_MESSAGE);
                break;
        }
        
        return anti;
    }
    
    public void buscar()
    {
      String cuenta="";
      String clave=getClave();
      int opcion=getOpcion();
      String query="";
      int anti=getAnti();
      if(!CbxAnti.getSelectedItem().equals("select"))
      {
        try
        {
            if(opcion==1)
                query="select usuario.nombre+'-'+usuario.apellidos name,usuario.cuenta cue,cuotas.cvecuota tar from usuario inner join cuotas on cuotas.cvecuota=usuario.cveTar where usuario.nombre="+clave;
            if (opcion==2)
                query="select usuario.nombre+'-'+usuario.apellidos name,usuario.cuenta cue,cuotas.cvecuota tar from usuario inner join cuotas on cuotas.cvecuota=usuario.cveTar where usuario.cuenta='"+clave+"'";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery(query);
            
                while(rs.next())
                {   
                    cuenta=rs.getString("cue");
                    Txtcuenta.setText(rs.getString("cue"));
                    Txtname.setText(rs.getString("name"));
                    CbxAnti.setEnabled(false);
                    txttari.setText(rs.getString("tar"));
                    
                    int tar=rs.getInt("tar");
                   // System.out.println(""+tar);
                    switch(tar)
                    {
                        case 1: txttari.setText(""+costos[0][0]);
                                    txtcosto.setText(""+costos[0][0]*anti);
                            break;
                        case 2:  txttari.setText(""+costos[1][0]);
                                    txtcosto.setText(""+costos[1][0]*anti);
                            break;
                        case 3:  txttari.setText(""+costos[2][0]);
                                    txtcosto.setText(""+costos[2][0]*anti);
                            break;
                        case 4: txttari.setText(""+maximos[3][0]);
                                txtcosto.setText(""+maximos[3][0]*anti);
                            break;
                    }
                    
                    Bbuscar.setEnabled(false);
                }
           
                if(Txtname.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "No se encontraron registros","Busqueda",JOptionPane.ERROR_MESSAGE);
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
      }
      else
      {
            JOptionPane.showMessageDialog(null, "Selecciona un tipo de anticipo","Busqueda",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    private void limpiar()
    {
        Txtname.setText("");
        Txtcuenta.setText("");
        txttari.setText("");
        txtcosto.setText("");
        CbxAnti.setSelectedItem(T[0]);
        CbxAnti.setEnabled(true);
        Bbuscar.setEnabled(true);
    }
    
    
    public boolean getExiste(String clave)
    {
        boolean duda_exitia=false; String r="";
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select cuenta from anticipo where cuenta='"+clave+"'");
                while(rs.next())
                {   
                    r=rs.getString("cuenta");
                }
           
                if(r.equals(""))
                {
                    duda_exitia=false;
                }
                else
                {
                    duda_exitia=true;
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        return duda_exitia;
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
                    System.out.print(r);
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        return r;
    }
    
    private void guardar()
    {
        String actualizar="";
        int anti=getAnti();
        String activo=getFuncional(Txtcuenta.getText());
        if(!Txtname.getText().equals("") && !Txtcuenta.getText().equals("") && activo.equalsIgnoreCase("2"))
        {
          int i =JOptionPane.showConfirmDialog(this,"¿Confirma el anticipo de "+Txtname.getText().trim()+" con clave de "+Txtcuenta.getText().trim()+"?\nAnticipo de: "+txtcosto.getText().trim()+"","Confirmar",JOptionPane.YES_NO_OPTION);
          if(i==0)
          {
            int filas=0;
            try
            {
                boolean existe=getExiste(Txtcuenta.getText());
                if(existe)
                {
                    actualizar="update anticipo set fTermino=DATEADD(mm,"+anti+",GETDATE()),activo=1 where cuenta='"+Txtcuenta.getText().trim()+"'";
                }
                else
                {
                    int tipo=0;
                    if(anti==6)
                        actualizar="insert into anticipo values('"+Txtcuenta.getText()+"','1',DATEADD(mm,6,GETDATE()),1);";
                    else
                        actualizar="insert into anticipo values('"+Txtcuenta.getText()+"','2',DATEADD(mm,6,GETDATE()),1);";
                }
                
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                filas=sentencia.executeUpdate(actualizar);
                if(filas>0)
                    JOptionPane.showMessageDialog(null, "Anticipio exitoso","Anticipo",JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "No se completo el anticipo","Anticipo",JOptionPane.ERROR_MESSAGE);
                
                sentencia.close();
                con.close();       
            }
            catch(SQLException | HeadlessException ex){}
            
                int j =JOptionPane.showConfirmDialog(this,"¿Desea imprimir nota por el pago?","Confirmar",JOptionPane.YES_NO_OPTION);
                if(j==0)
                {
                    JFrame aux=new JFrame();
                    aux.setTitle("Imprimir");
                    Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/agua.png")); aux.setIconImage(icon);
                    aux.setLayout(null);
                    aux.setVisible(true);
                    aux.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    aux.setSize(465,507);
                    
                    
                    JDesktopPane dskpanel;
                    dskpanel=new JDesktopPane();
                    aux.setContentPane(dskpanel);
        
                    String cuenta="", nombre="", domic="",consum="",mes="", año="",ulti="",tar="",cont="";
                    float adeudo=0;
                    try
                   {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
                    Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                    Statement sentencia=con.createStatement();
                    ResultSet rs=sentencia.executeQuery("select usuario.nombre+' '+ usuario.apellidos name,usuario.domicilio domic, usuario.cuenta cue, MONTH(GETDATE()) mes, YEAR(getdate()) año, usuario.telefono cont, usuario.cveTar tar,usuario.domicilio dom,pago.fUltimPago ult, DIFFERENCE(pago.lectAct,pago.lectAnte) con from usuario inner join pago on pago.cuenta=usuario.cuenta where pago.cuenta='"+Txtcuenta.getText().trim()+"'");
                    while(rs.next())
                    {   
                        cuenta=rs.getString("cue");
                        año=rs.getString("año");
                        mes=rs.getString("mes");
                        nombre=rs.getString("name");
                        domic=rs.getString("domic");
                        ulti=rs.getString("ult");
                        cont=rs.getString("cont");
                        consum=rs.getString("con");
                        adeudo=Float.parseFloat(txtcosto.getText());
                        tar=rs.getString("tar");
                    }
                  }
                  catch(ClassNotFoundException | SQLException ex){}
                  dskpanel.add(new impr_factura(cuenta, nombre,domic, ulti,consum,adeudo, tar,cont,año,mes));
                    
                }
                limpiar();
          }
        }
        else
        {
            if(activo.equalsIgnoreCase("1"))
            {
                JOptionPane.showMessageDialog(null, "El cliente ya contiene un anticipo","Anticipo",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error","Anticipo",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       switch(e.getActionCommand())
       {
           case "Buscar": buscar();
               break;
           case "Pagar": guardar();
               break;
           case "Limpiar": limpiar();
               break;
       }
    }

    
    
}
