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

public class cuotaC extends JInternalFrame implements ActionListener
{
    //variables globales
    JLabel Lmax,Ltar,Lmin, Lmt;
    JTextField Txmx1,Txmx2,Txmx3,Txmx4,Txt1,Txt2,Txt3,Txt4;
    JButton Bguardar,Bcancel;
    String clave="";
    //fin variables globales
    public cuotaC()
    {
        super("Cuota Comercial");
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
        
        Lmax=new JLabel("Máximo");
        Lmax.setSize(100,20);
        Lmax.setLocation(20, 30);
        Ltar=new JLabel("Tarifa");
        Ltar.setSize(100,20);
        Ltar.setLocation(85, 30);
        Lmin=new JLabel("Minima");
        Lmin.setSize(100,20);
        Lmin.setLocation(140, 70);
        Lmt=new JLabel("Por metro");
        Lmt.setSize(100,20);
        Lmt.setLocation(140, 110);
        add(Lmt);
        Lmt=new JLabel("Por metro");
        Lmt.setSize(100,20);
        Lmt.setLocation(140, 150);
        add(Lmt);
        Lmt=new JLabel("Por metro");
        Lmt.setSize(100,20);
        Lmt.setLocation(140, 190);
        add(Lmt);
        
        Txmx1=new JTextField(6);
        Txmx1.setSize(40,20);
        Txmx1.setLocation(20, 70);
        Txmx1.setEnabled(false);
        Txmx1.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {
                                         if (Txmx1.getText().length()== 4) e.consume();
                                         char c= e.getKeyChar();
                                         if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txmx2=new JTextField(6);
        Txmx2.setSize(40,20);
        Txmx2.setLocation(20, 110);
        Txmx2.setEnabled(false);
        Txmx2.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txmx2.getText().length()== 4) e.consume();
                                         char c= e.getKeyChar();
                                         if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txmx3=new JTextField(6);
        Txmx3.setSize(40,20);
        Txmx3.setLocation(20, 150);
        Txmx3.setEnabled(false);
        Txmx3.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txmx3.getText().length()== 5) e.consume();
                                            char c= e.getKeyChar();
                                            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txmx4=new JTextField(6);
        Txmx4.setSize(40,20);
        Txmx4.setLocation(20, 190);
        Txmx4.setEnabled(false);
        Txmx4.addKeyListener(new KeyListener()
                                 {
                                     public void keyTyped(KeyEvent e) {if (Txmx4.getText().length()== 5) e.consume();
                                            char c= e.getKeyChar();
                                            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                                            e.consume();}}
                                     public void keyPressed(KeyEvent arg0) {} 
                                     public void keyReleased(KeyEvent arg0) {}
                                 });
        Txmx1.setBorder(border);
        Txmx2.setBorder(border);
        Txmx3.setBorder(border);
        Txmx4.setBorder(border);
        
        Txt1=new JTextField(6);
        Txt1.setSize(45,20);
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
        Txt2.setSize(45,20);
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
        Txt4.setSize(45,20);
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
        add(Ltar);
        add(Lmin);
        add(Txmx1);
        add(Txmx2);
        add(Txmx3);
        add(Txmx4);
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
            ResultSet rs=sentencia.executeQuery("select * from cuotas where cvecuota='2'");
                while(rs.next())
                {   
                    clave=rs.getString("cvecuota");
                    Txmx1.setText(rs.getString("max1"));
                    Txmx2.setText(rs.getString("max2"));
                    Txmx3.setText(rs.getString("max3"));
                    Txmx4.setText(rs.getString("max4"));
                    Txt1.setText(rs.getString("tari1"));
                    Txt2.setText(rs.getString("tari2"));
                    Txt3.setText(rs.getString("tari3"));
                    Txt4.setText(rs.getString("tari4"));
                }
           
                if(clave.equals(""))
                    JOptionPane.showMessageDialog(null, "No se ha podido cargar los datos","Cuota Comercial",JOptionPane.ERROR_MESSAGE);
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
        Txmx1.setEnabled(true);
        Txmx2.setEnabled(true);
        Txmx3.setEnabled(true);
        Txmx4.setEnabled(true);
        Txt1.setEnabled(true);
        Txt2.setEnabled(true);
        Txt3.setEnabled(true);
        Txt4.setEnabled(true);
    }
    
    public void tapar()
    {
        Txmx1.setEnabled(false);
        Txmx2.setEnabled(false);
        Txmx3.setEnabled(false);
        Txmx4.setEnabled(false);
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
        if(!Txmx1.equals("") && !Txmx2.equals("") && !Txmx3.equals("") && !Txmx4.equals("") && !Txt1.equals("") && !Txt2.equals("") && !Txt3.equals("") && !Txt4.equals(""))
            bandera=true;
        else
            JOptionPane.showMessageDialog(null, "Asegúrese de llenar todos los campos","Cuota Comercial",JOptionPane.ERROR_MESSAGE);
        if(bandera)
        {
            int filas=0;
            try
            {
                String actualiza="update cuotas set max1="+Txmx1.getText().trim()+",max2="+Txmx2.getText().trim()+",max3="+Txmx3.getText().trim()+",max4="+Txmx4.getText().trim()+",tari1="+Txt1.getText().trim()+",tari2="+Txt2.getText().trim()+",tari3="+Txt3.getText().trim()+",tari4="+Txt4.getText().trim()+" where cvecuota='2'";
                Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
                Statement sentencia=con.createStatement();
                System.out.print(filas);
                filas=sentencia.executeUpdate(actualiza);
                if(filas>0){
                    JOptionPane.showMessageDialog(null, "Guardado completo","Cuota Comercial",JOptionPane.INFORMATION_MESSAGE);
                    Bcancel.setEnabled(false);
                    Bguardar.setEnabled(false);
                    cargar_data();
                    tapar();
                }
                else
                    JOptionPane.showMessageDialog(null, "No se pudo guardar","Cuota Comercial",JOptionPane.ERROR_MESSAGE);
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
