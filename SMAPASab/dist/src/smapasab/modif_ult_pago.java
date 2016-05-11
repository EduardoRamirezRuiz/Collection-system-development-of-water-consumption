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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

public class modif_ult_pago extends JInternalFrame implements ActionListener
{
    //variables globales
    JLabel Lmax,Lmin,Lcuenta;
    JTextField Txtcuenta;
    JFormattedTextField Txt1;
    MaskFormatter mascara = null;
    JButton Bguardar,Bcancel;
    String clave="";
    
    //fin variables globales
    public modif_ult_pago()
    {
        super("Modificación");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        setSize(260,240);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
        entrar();
    }
    
    private void entrar()
    {
        int opc=1; String inst="";
        int i =JOptionPane.showConfirmDialog(null,"SOLO ADMNISTRADORES\nEste apartado es para modificar la ultima fecha de pago de algún cliente\n¿Cuenta con una contraseña?","Confirmar",JOptionPane.YES_NO_OPTION);
        if(i==0)
        {
            do
            {
                inst=JOptionPane.showInputDialog(null,"Introduce contraseña","CONTRASEÑA");
                if(inst.equalsIgnoreCase("sab"))
                    opc=2;
                else
                {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta","Error",JOptionPane.ERROR_MESSAGE);
                    int j =JOptionPane.showConfirmDialog(null,"SOLO ADMNISTRADORES\nEste apartado es para modificar la ultima fecha de pago de algún cliente\n¿Reintentar?","Confirmar",JOptionPane.YES_NO_OPTION);
                    if(j==0)
                    {
                        opc=1;
                    }
                    else
                    {
                        opc=3;
                    }
                }
            }while(opc==1);
        }
        if(opc==2)
            crearInterfaz();
        else
            this.dispose();
    }
    
    public void crearInterfaz()
    {
        Border border=BorderFactory.createLineBorder(Color.CYAN);
        Lmax=new JLabel("S.M.A.P.A. Sabino");
        Lmax.setSize(100,20);
        Lmax.setLocation(20, 15);
        Lcuenta=new JLabel("Cuenta");
        Lcuenta.setSize(100,20);
        Lcuenta.setLocation(20, 42);
        Lmin=new JLabel("Ultimo pago");
        Lmin.setSize(100,20);
        Lmin.setLocation(20, 70);
        Txtcuenta=new JTextField(6);
        Txtcuenta.setSize(100,20);
        Txtcuenta.setLocation(105, 42);
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
        try {mascara = new MaskFormatter("####-##-##"); } catch (ParseException ex) { } 
        Txt1=new JFormattedTextField(mascara);
        Txt1.setSize(100,20);
        Txt1.setLocation(105, 70);
        Txt1.setEnabled(false);
        Txt1.setBorder(border);
        try {Txt1.commitEdit(); } 
        catch (ParseException ex1) { } 
        //Botones
        Bguardar=new JButton("Guardar",new ImageIcon("src/recursos/im_save.png"));
        Bguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bguardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bguardar.setVerticalAlignment(SwingConstants.CENTER);
        Bguardar.setSize(80, 70);
        Bguardar.setLocation(20, 100);
        Bguardar.addActionListener(this);
        
        
        Bcancel=new JButton("Buscar",new ImageIcon("src/recursos/im_search.png"));
        Bcancel.setHorizontalTextPosition(SwingConstants.CENTER);
        Bcancel.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bcancel.setVerticalAlignment(SwingConstants.CENTER);
        Bcancel.setSize(85, 70);
        Bcancel.setLocation(110, 100);
        Bcancel.addActionListener(this);
        
        //agregar componentes
        add(Lmax);
        add(Lmin);
        add(Txt1);
        add(Bguardar);
        add(Bcancel);
        add(Lcuenta);
        add(Txtcuenta);
    }
    
     private void cargar_data() 
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select fUltimPago f from pago where cuenta='"+Txtcuenta.getText().trim()+"'");
                while(rs.next())
                {   
                    clave=Txtcuenta.getText().trim();
                    Txtcuenta.setEnabled(false);
                    Txt1.setEnabled(true);
                    Txt1.setText(rs.getString("f"));
                }
           
                if(Txt1.getText().equals("    -  -  "))
                    JOptionPane.showMessageDialog(null, "No se ha podido cargar los datos","Modifcar",JOptionPane.ERROR_MESSAGE);
                
            con.close();
            sentencia.close();
            rs.close();
            Bguardar.setEnabled(true);
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
      
    }
    
    public void guardar()
    {
        boolean bandera=false;
        if(!Txt1.equals("    -  -  "))
            bandera=true;
        else
            JOptionPane.showMessageDialog(null, "Asegúrese de llenar todos los campos","Cuota Fija",JOptionPane.ERROR_MESSAGE);
        if(bandera)
        {
            int filas=0;
            try
            {  
                String actualiza="update pago set fUltimPago='"+Txt1.getText()+"' where cuenta='"+clave+"'";
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                System.out.print(filas);
                filas=sentencia.executeUpdate(actualiza);
                if(filas>0){
                    JOptionPane.showMessageDialog(null, "Guardado completo","Modificar",JOptionPane.INFORMATION_MESSAGE);
                    Txtcuenta.setText("");
                    Txtcuenta.setEnabled(true);
                    Txt1.setText("");
                    Txt1.setEnabled(false);
                    Bguardar.setEnabled(false);
                }
                else
                    JOptionPane.showMessageDialog(null, "No se pudo guardar","Recargo",JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException | HeadlessException ex){}
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       switch(e.getActionCommand())
       {
           case "Guardar": guardar();
               break;
           case "Buscar": cargar_data();
               break;
       }
    }
}
