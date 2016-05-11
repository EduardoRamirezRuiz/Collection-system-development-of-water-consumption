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

public class user_reports extends JInternalFrame implements ActionListener
{
    //delcracion de variables
    JButton Btodos,Bruta1,Bruta2, Bimpr;
    JTable tblusers;
    DefaultTableModel modelo;
    Object[][] datos=new Object[0][5];
    Object FilasDatos[] = new Object[5];
    JScrollPane panel;
    String[] nombColumnas = {"no.","Cuenta", "Nombre", "Tarifa", "Domicilio"};
    int cont=1;
    //fin declaracion de variables
    public user_reports()
    {
        super("Reporte de contratos");
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
        Btodos=new JButton("Todos",new ImageIcon("src/recursos/group.png"));
        Btodos.setHorizontalTextPosition(SwingConstants.CENTER);
        Btodos.setVerticalTextPosition(SwingConstants.BOTTOM) ;
        Btodos.setVerticalAlignment(SwingConstants.CENTER);
        Btodos.setSize(80, 70);
        Btodos.setLocation(20, 30);
        Btodos.addActionListener(this);
        
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
        columnModel.getColumn(1).setPreferredWidth(51);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(5);
        columnModel.getColumn(4).setPreferredWidth(120);
        tblusers.setBackground(Color.WHITE);
        //agregar
        add(Btodos);
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
                case 2: query="select r.descripcion rut, u.cuenta cue, u.nombre name, u.cveTar tar, u.domicilio doc, u.apellidos ape from usuario u inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta=1 order by doc";
                    break;
                case 3: query="select r.descripcion rut, u.cuenta cue, u.nombre name, u.cveTar tar, u.domicilio doc, u.apellidos ape from usuario u inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta=2 order by doc";
                    break;
            }
            
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery(query);
            while(rs.next())
            {
                FilasDatos[0]=cont;
                FilasDatos[1]=rs.getString("rut")+"-"+rs.getString("cue");
                FilasDatos[2]=rs.getString("name")+" "+rs.getString("ape");
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
                FilasDatos[4]=rs.getString("doc");
                modelo.addRow(FilasDatos);
                cont++;
            }
        }
        catch(Exception ex){System.out.println("no "+ex);}
    }
    
    public void imprimir()
    {
         try 
         {
           // tblusers.print(); // Imprime el jTable
              MessageFormat headerFormat = new MessageFormat("SMAPA El Sabino. Reporte de usuarios.");
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
