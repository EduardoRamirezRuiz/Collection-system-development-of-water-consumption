package smapasab;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.StyledEditorKit.BoldAction;


public class login extends JFrame implements ActionListener
{
    JLabel Lmin,fondo;
    //JTextField 
    JPasswordField Txt1;
    JButton Bguardar;
    String clave="";
    JDesktopPane dskpanel;
    public login() 
    {
        super("S.M.A.P.A.");
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
        crearInterfaz();
        //conectar();
        
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/agua.png")); setIconImage(icon);
        setSize(310,280);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    
    public void crearInterfaz()
    {
        fondo=new JLabel(new ImageIcon("/recursos/SilverLight.png"));
        fondo.setSize(310,280);
        fondo.setLocation(0,0);
        getContentPane().add(fondo);
        //Border border=BorderFactory.createLineBorder(Color.CYAN);
        Font f=new Font("Tahoma", Font.BOLD, 38);
        Lmin=new JLabel("Contraseña:");
        Lmin.setFont(f);
        Lmin.setForeground(new Color(220, 240, 255));
        Lmin.setSize(250,40);
        Lmin.setLocation(20, 30);
        Font fe=new Font("Tahoma", Font.BOLD, 32);
        Txt1=new JPasswordField();
        Txt1.setSize(250,40);
        Txt1.setLocation(20, 80);
        Txt1.setFont(fe);
        Txt1.setForeground(Color.red);
        //Txt1.setBorder(border);
        //Botones
        Bguardar=new JButton("Iniciar",new ImageIcon("src/recursos/status.png"));
        Bguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        Bguardar.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bguardar.setVerticalAlignment(SwingConstants.CENTER);
        Bguardar.setSize(80, 70);
        Bguardar.setLocation(115, 140);
        Bguardar.addActionListener(this);
        
        
        //agregar componentes
        add(Lmin);
        add(Txt1);
        add(Bguardar);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String pass="";
        char [] passw=Txt1.getPassword();
        for(int x=0;x<passw.length;x++)
            pass+=passw[x];
        
        if(pass.equalsIgnoreCase("sab"))
        {
            new Inicio();
            this.dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta","Contraseña",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
