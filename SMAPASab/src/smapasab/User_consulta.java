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

public class User_consulta extends JInternalFrame implements ActionListener
{
    //Inicio delcracion de variables
    JLabel Lcuenta,Lname,Ltar,LDomic,Ltel,Lapell,Lruta,LNum,LfInst;
    JTextField Txtcuenta,Txtname,Txttar,Txtdomic,Txttel,Txtapell,Txtruta,TxtNum;
    JButton Bbuscar,Blimpiar, Bcancel;
    JFormattedTextField TFfInst; MaskFormatter mascara = null;  //fecha de instalacion
    //Fin declaracion de variabes
    public User_consulta()
    {
        super("Consulta de contratos");
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
        Lruta.setLocation(140, 70);
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
        Txttar=new JTextField(2);
        Txttar.setSize(40,20);
        Txttar.setLocation(90, 70);
        Txttar.setEnabled(false);
        Txttar.setBorder(border);
        Txtruta=new JTextField(2);
        Txtruta.setSize(20,20);
        Txtruta.setLocation(180, 70);
        Txtruta.setEnabled(false);
        Txtruta.setBorder(border);
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
        TxtNum=new JTextField();
        TxtNum.setSize(50,20);
        TxtNum.setLocation(90, 190);
        TxtNum.setEnabled(false);
        TxtNum.setBorder(border);
        Txttel=new JTextField();
        Txttel.setSize(100,20);
        Txttel.setLocation(90, 220);
        Txttel.setEnabled(false);
        Txttel.setBorder(border);
        //try {mascara = new MaskFormatter("##-##-####"); } catch (ParseException ex) { } 
        TFfInst=new JFormattedTextField();
        TFfInst.setSize(100,20);
        TFfInst.setLocation(90, 250);
        TFfInst.setBorder(border);
        try {TFfInst.commitEdit(); } catch (ParseException ex1) { } 
        TFfInst.setEnabled(false);
        
        //Botones
        Bbuscar=new JButton("Buscar",new ImageIcon("src/recursos/im_search.png"));
        Bbuscar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bbuscar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bbuscar.setVerticalAlignment(SwingConstants.CENTER);
        Bbuscar.setSize(80, 70);
        Bbuscar.setLocation(20, 280);
        Bbuscar.addActionListener(this);
        
        Blimpiar=new JButton("Limpiar",new ImageIcon("src/recursos/im_clean.png"));
        Blimpiar.setHorizontalTextPosition(SwingConstants.CENTER);
        Blimpiar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Blimpiar.setVerticalAlignment(SwingConstants.CENTER);
        Blimpiar.setSize(80, 70);
        Blimpiar.setLocation(115, 280);
        Blimpiar.addActionListener(this);
        
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
        add(Txttar);
        add(Txtdomic);
        add(Txttel);
        add(Txtapell);
        add(Txtruta);
        add(TxtNum);
        add(TFfInst);
        add(Bbuscar);
        add(Blimpiar);
        add(Bcancel);
    }

    public void limpiar()
    {
        Txtcuenta.setText("");
        Txtname.setText("");
        Txttar.setText("");
        Txtdomic.setText("");
        Txttel.setText("");
        Txtapell.setText("");
        Txtruta.setText("");
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
                    Txttar.setText(rs.getString("Tar"));
                    if(Txttar.getText().equals("1"))
                        Txttar.setText("Dom");
                    if(Txttar.getText().equals("2"))
                        Txttar.setText("Com");
                    if(Txttar.getText().equals("3"))
                        Txttar.setText("Ind");
                    if(Txttar.getText().equals("4"))
                        Txttar.setText("CF");
                    Txtruta.setText(rs.getString("Rut"));
                    Txtdomic.setText(rs.getString("doc"));
                    TxtNum.setText(rs.getString("num"));
                    Txttel.setText(rs.getString("tel"));
                    TFfInst.setText(rs.getString("fecha"));
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
            JOptionPane.showMessageDialog(null, "Indroduce clave","Busqueda",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public void cancelar()
    {
        this.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       switch(e.getActionCommand())
       {
           case "Buscar": buscar();
               break;
           case "Limpiar": limpiar();
               break;
           case "Cancelar": cancelar();
               break;
       }
    }
}
