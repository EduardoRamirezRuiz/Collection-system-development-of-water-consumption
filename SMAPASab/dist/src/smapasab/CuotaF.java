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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class CuotaF extends JInternalFrame implements ActionListener
{
    //variables globales
    JLabel Lmax,Lmin, Lmt;
    JTextField Txt1,Txt2,Txt3,Txt4;
    JButton Bguardar,Bcancel;
    String clave="";
    //fin variables globales
    public CuotaF()
    {
        super("Cuota Fija,%Int, IVA");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        setSize(260,380);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }
     public void crearInterfaz()
    {
        Border border=BorderFactory.createLineBorder(Color.CYAN);
        
        Lmax=new JLabel("S.M.A.P.A. Sabino");
        Lmax.setSize(100,20);
        Lmax.setLocation(20, 30);
        Lmin=new JLabel("C. Fija");
        Lmin.setSize(100,20);
        Lmin.setLocation(20, 70);
        Lmt=new JLabel("Drenaje");
        Lmt.setSize(100,20);
        Lmt.setLocation(20, 110);
        add(Lmt);
        Lmt=new JLabel("Interés");
        Lmt.setSize(100,20);
        Lmt.setLocation(20, 150);
        add(Lmt);
        Lmt=new JLabel("IVA");
        Lmt.setSize(100,20);
        Lmt.setLocation(20, 190);
        add(Lmt);
        
        Txt1=new JTextField(6);
        Txt1.setSize(60,20);
        Txt1.setLocation(85, 70);
        Txt1.setEnabled(false);
        Txt1.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txt1.getText().length()== 7) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                                            e.consume();}
                                        if (c == '.' && Txt1.getText().contains(".")) {
                                            e.consume();}
                                     }
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txt2=new JTextField(6);
        Txt2.setSize(60,20);
        Txt2.setLocation(85, 110);
        Txt2.setEnabled(false);
        Txt2.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txt2.getText().length()== 7) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                                            e.consume();}
                                        if (c == '.' && Txt2.getText().contains(".")) {
                                            e.consume();}
                                        }
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txt3=new JTextField(6);
        Txt3.setSize(45,20);
        Txt3.setLocation(85, 150);
        Txt3.setEnabled(false);
        Txt3.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txt3.getText().length()== 7) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                                            e.consume();}
                                        if (c == '.' && Txt3.getText().contains(".")) {
                                            e.consume();}
                                     }
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txt4=new JTextField(6);
        Txt4.setSize(60,20);
        Txt4.setLocation(85, 190);
        Txt4.setEnabled(false);
        Txt4.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txt4.getText().length()== 7) e.consume();
                                        char c = e.getKeyChar();
                                        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                                            e.consume();}
                                        if (c == '.' && Txt4.getText().contains(".")) {
                                            e.consume();}
                                     }
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txt1.setBorder(border);
        Txt2.setBorder(border);
        Txt3.setBorder(border);
        Txt4.setBorder(border);
        
        //Botones
        Bguardar=new JButton("Guardar",new ImageIcon("src/recursos/im_save.png"));
        Bguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bguardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bguardar.setVerticalAlignment(SwingConstants.CENTER);
        Bguardar.setSize(80, 70);
        Bguardar.setLocation(20, 240);
        Bguardar.addActionListener(this);
        
        
        Bcancel=new JButton("Cancelar",new ImageIcon("src/recursos/im_cancel.png"));
        Bcancel.setHorizontalTextPosition(SwingConstants.CENTER);
        Bcancel.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bcancel.setVerticalAlignment(SwingConstants.CENTER);
        Bcancel.setSize(85, 70);
        Bcancel.setLocation(110, 240);
        Bcancel.addActionListener(this);
        
        //agregar componentes
        add(Lmax);
        add(Lmin);
        add(Txt1);
        add(Txt2);
        add(Txt3);
        add(Txt4);
        add(Bguardar);
        add(Bcancel);
        
        //datos de la cuota, obtener de la BD
        cargar_data();
    }
     
    private void cargar_data() 
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select * from cuotas where cvecuota='4'");
                while(rs.next())
                {   
                    clave=rs.getString("cvecuota");
                    Txt1.setText(rs.getString("max1"));
                    Txt2.setText(rs.getString("max2"));
                    Txt3.setText(rs.getString("max3"));
                    Txt4.setText(rs.getString("max4"));
                }
           
                if(clave.equals(""))
                    JOptionPane.showMessageDialog(null, "No se ha podido cargar los datos","Cuota Fija",JOptionPane.ERROR_MESSAGE);
                else
                    mostrar();
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
      
    }
    public void mostrar()
    {
        Txt1.setEnabled(true);
        Txt2.setEnabled(true);
        Txt3.setEnabled(true);
        Txt4.setEnabled(true);
    }
    
    public void tapar()
    {
        Txt1.setEnabled(false);
        Txt2.setEnabled(false);
        Txt3.setEnabled(false);
        Txt4.setEnabled(false);
    }
    public void cancelar()
    {
            int i =JOptionPane.showConfirmDialog(this,"¿Salir sin guardar?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(i==0)
               this.dispose();
    }
    
    public void guardar()
    {
        boolean bandera=false;
        if(!Txt1.equals("") && !Txt2.equals("") && !Txt3.equals("") && !Txt4.equals(""))
            bandera=true;
        else
            JOptionPane.showMessageDialog(null, "Asegúrese de llenar todos los campos","Cuota Fija",JOptionPane.ERROR_MESSAGE);
        if(bandera)
        {
            int filas=0;
            try
            {
                String actualiza="update cuotas set max1="+Txt1.getText().trim()+",max2="+Txt2.getText().trim()+",max3="+Txt3.getText().trim()+",max4="+Txt4.getText().trim()+",tari1="+0+",tari2="+0+",tari3="+0+",tari4="+0+" where cvecuota='4'";
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                System.out.print(filas);
                filas=sentencia.executeUpdate(actualiza);
                if(filas>0){
                    JOptionPane.showMessageDialog(null, "Guardado completo","Cuota Fija",JOptionPane.INFORMATION_MESSAGE);
                    Bcancel.setEnabled(false);
                    Bguardar.setEnabled(false);
                    cargar_data();
                    tapar();
                }
                else
                    JOptionPane.showMessageDialog(null, "No se pudo guardar","Cuota Fija",JOptionPane.ERROR_MESSAGE);
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
           case "Cancelar": cancelar();
               break;
       }
    }
}
