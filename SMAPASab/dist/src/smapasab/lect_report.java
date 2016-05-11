package smapasab;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class lect_report extends JInternalFrame implements ActionListener
{
    //delcracion de variables
    JButton Bruta1,Bruta2, Bimpr;//Btodos;
    JTable tblusers;
    DefaultTableModel modelo;
    Object[][] datos=new Object[0][6];
    Object FilasDatos[] = new Object[6];
    JScrollPane panel;
    String[] nombColumnas = {"no.","Cuenta", "Nombre", "Tarifa", "Lect. Ant","Lect. Act."};
    int cont=1;
    //fin declaracion de variables
    public lect_report()
    {
        super("Reporte de lecturas");
        ImageIcon icono=new ImageIcon(this.getClass().getResource("/recursos/agua2.png"));
        setFrameIcon(icono);
        setLayout(null);
        crearInterfaz();
        tblusers.setShowGrid(false);
        setSize(550,600);
        setClosable(true);
        setResizable(false);
        setVisible(true); 
    }
    
    private void crearInterfaz() 
    {
        //creacion de variables
        
        Bruta1=new JButton("Ruta 1",new ImageIcon("src/recursos/gr1.png"));
        Bruta1.setHorizontalTextPosition(SwingConstants.CENTER);
        Bruta1.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bruta1.setVerticalAlignment(SwingConstants.CENTER);
        Bruta1.setSize(80, 70);
        Bruta1.setLocation(115, 30);
        Bruta1.addActionListener(this);
        
        Bruta2=new JButton("Ruta 2",new ImageIcon("src/recursos/gr2.png"));
        Bruta2.setHorizontalTextPosition(SwingConstants.CENTER);
        Bruta2.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bruta2.setVerticalAlignment(SwingConstants.CENTER);
        Bruta2.setSize(80, 70);
        Bruta2.setLocation(210, 30);
        Bruta2.addActionListener(this);
        
        Bimpr=new JButton("Imprimir",new ImageIcon("src/recursos/printer.png"));
        Bimpr.setHorizontalTextPosition(SwingConstants.CENTER);
        Bimpr.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Bimpr.setVerticalAlignment(SwingConstants.CENTER);
        Bimpr.setSize(90, 70);
        Bimpr.setLocation(305, 30);
        Bimpr.addActionListener(this);
        
        tblusers=new JTable();
        tblusers.setSize(500, 400);
        tblusers.setLocation(130, 220);
        add(tblusers);
        
        //tblalumnos.setFont(new Font("Monospaced", Font.ITALIC+Font.BOLD, 14));
        //tblalumnos.setForeground(Color.white);
        modelo=new DefaultTableModel(datos,nombColumnas);
        tblusers.setModel(modelo);
        //tblalumnos.setBackground(Color.DARK_GRAY);
        panel = new JScrollPane(tblusers);
        panel.setSize(500, 400);
        panel.setLocation(20, 105);
        add(panel);
        
        TableColumnModel columnModel = tblusers.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(5);
        columnModel.getColumn(1).setPreferredWidth(62);
        columnModel.getColumn(2).setPreferredWidth(170);
        columnModel.getColumn(3).setPreferredWidth(18);
        columnModel.getColumn(4).setPreferredWidth(55);
        columnModel.getColumn(5).setPreferredWidth(55);
        
        tblusers.setBackground(Color.WHITE);
        
        //agregar
        //add(Btodos);
        add(Bruta1);
        add(Bruta2);
        add(Bimpr);
    }
    
    public void limpiar()
    {
        for (int i = tblusers.getRowCount() -1; i >= 0; i--)
            modelo.removeRow(i);
        cont=1;
    }
    
    public void buscar(int opc)
    {
        limpiar();
        try
        { 
            String query="";
            switch(opc)
            {
                case 1: query="select r.descripcion rut, u.cuenta cue, u.nombre name, u.apellidos ape, u.cveTar tar, u.domicilio doc from usuario u inner join ruta r on r.cveRuta=u.cveRuta order by doc";
                    break;
                case 2: query="select r.descripcion rut, u.cuenta cue, u.nombre name, u.apellidos app, u.cveTar tar, p.lectAnte lan, p.lectAct lac from usuario u inner join pago p on u.cuenta=p.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta=1 and DATEDIFF(MM,p.fEmision,GETDATE())>2 order by u.cuenta";
                    break;
                case 3: query="select r.descripcion rut, u.cuenta cue, u.nombre name, u.apellidos app, u.cveTar tar, p.lectAnte lan, p.lectAct lac from usuario u inner join pago p on u.cuenta=p.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta=2 and DATEDIFF(MM,p.fEmision,GETDATE())>2 order by u.cuenta";
                    break;
            }
            
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery(query);
            while(rs.next())
            {
                FilasDatos[0]=cont;
                FilasDatos[1]=rs.getString("rut")+"-"+rs.getString("cue");
                FilasDatos[2]=rs.getString("name")+" "+rs.getString("app");
                String tarifa=rs.getString("tar");
                if(tarifa.equals("1"))
                        tarifa="Dom";
                if(tarifa.equals("2"))
                        tarifa="Com";
                if(tarifa.equals("3"))
                        tarifa="Ind";
                if(tarifa.equals("4"))
                        tarifa="CF";
                FilasDatos[3]=tarifa;
                FilasDatos[4]=rs.getString("lan");
                FilasDatos[5]=rs.getString("lac");
                modelo.addRow(FilasDatos);
                cont++;
            }
            noTienenLectPeroDebenAparecerEnReporte(opc);
        }
        catch(Exception ex){}
    }
    
    public void noTienenLectPeroDebenAparecerEnReporte(int opc)
    {
        //select cuenta,nombre,cveTar from usuario where not exists (select * from pago where usuario.cuenta=pago.cuenta)
        try
        { 
            String query="";
            if(opc==2)
               query="select u.cuenta cue,u.nombre name,u.cveTar tar,r.descripcion rut,u.apellidos app from usuario u inner join ruta r on r.cveRuta=u.cveRuta where not exists (select * from pago where u.cuenta=pago.cuenta) and u.cveRuta='1'";
            else
                query="select u.cuenta cue,u.nombre name,u.cveTar tar,r.descripcion rut,u.apellidos app from usuario u inner join ruta r on r.cveRuta=u.cveRuta where not exists (select * from pago where u.cuenta=pago.cuenta) and u.cveRuta='2'";
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery(query); 
            while(rs.next())
            {
                FilasDatos[0]=cont;
                FilasDatos[1]=rs.getString("rut")+"-"+rs.getString("cue");
                FilasDatos[2]=rs.getString("name")+" "+rs.getString("app");
                String tarifa=rs.getString("tar");
                if(tarifa.equals("1"))
                        tarifa="Dom";
                if(tarifa.equals("2"))
                        tarifa="Com";
                if(tarifa.equals("3"))
                        tarifa="Ind";
                if(tarifa.equals("4"))
                        tarifa="CF";
                FilasDatos[3]=tarifa;
                String nada=".";
                FilasDatos[4]=nada;
                FilasDatos[5]=nada;
                modelo.addRow(FilasDatos);
                cont++;
            }
        }
        catch(Exception ex){}
    }
    
    public void imprimir()
    {
         try 
         {
           // tblusers.print(); // Imprime el jTable
              MessageFormat headerFormat = new MessageFormat("SMAPA El Sabino. Faltantes de lectura.");
              MessageFormat footerFormat = new MessageFormat("- Página {0} -");
              tblusers.print(PrintMode.FIT_WIDTH, headerFormat, footerFormat);
         } catch (PrinterException ex) { }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        switch(e.getActionCommand())
        {
            case "Todos": buscar(1);
                break;
            case "Ruta 1": buscar(2);
                break;
            case "Ruta 2": buscar(3);
                break;
            case "Imprimir": imprimir();
                break;
        }
    }
}
