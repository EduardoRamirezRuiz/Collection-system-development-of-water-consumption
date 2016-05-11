package smapasab;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class pay_miss extends JInternalFrame implements ActionListener
{
    //delcracion de variables
    JButton Bruta1,Bruta2, Bimpr;//Btodos;
    JTable tblusers;
    DefaultTableModel modelo;
    Object[][] datos=new Object[0][5];
    Object FilasDatos[] = new Object[5];
    JScrollPane panel;
    String[] nombColumnas = {"no.","Cuenta", "Nombre", "Tarifa", "Adeudo"};
    int cont=1;
    int [][] maximos=new int [4][4]; float [][] costos=new float [4][4];
    //fin declaracion de variables
    public pay_miss()
    {
        super("Reporte de pagos faltantes");
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
        columnModel.getColumn(1).setPreferredWidth(60);
        columnModel.getColumn(2).setPreferredWidth(170);
        columnModel.getColumn(3).setPreferredWidth(18);
        columnModel.getColumn(4).setPreferredWidth(60);
        tblusers.setBackground(Color.WHITE);
        //agregar
        //add(Btodos);
        add(Bruta1);
        add(Bruta2);
        add(Bimpr);
        
        llenarCuotas();
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
                case 2: query="select DATEDIFF (mm,p.fEmision,GETDATE()) bim, r.descripcion rut, u.cuenta cue, u.nombre name, u.cveTar tar, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta='1' order by u.cuenta";
                    break;
                case 3: query="select DATEDIFF (mm,p.fEmision,GETDATE()) bim, r.descripcion rut, u.cuenta cue, u.nombre name, u.cveTar tar, u.apellidos ape, p.lectAnte ante,p.lectAct act, DATEDIFF (mm,p.fEmision,GETDATE()) bim, DATEDIFF (mm,p.fUltimPago,p.fEmision) ult from usuario u inner join pago p on p.cuenta=u.cuenta inner join ruta r on r.cveRuta=u.cveRuta where u.cveRuta='2' order by u.cuenta";
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
                
                int bim=rs.getInt("bim");
                int ult=rs.getInt("ult");
                int ruta=rs.getInt("tar");
                int consumo=rs.getInt("act")-rs.getInt("ante");
                String cuenta=rs.getString("cue");
                float total=getUltimo(ult,cuenta,ruta,consumo,bim);
                FilasDatos[4]=""+total;
                
                String activo=getFuncional(cuenta);
                if(activo.equalsIgnoreCase("1"))
                    total=0;
                
                if(total!=0)
                    modelo.addRow(FilasDatos);
                cont++;
            }
        }
        catch(Exception ex){}
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
                  //  System.out.print(r);
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        return r;
    }
    
    private float getRecargo() 
    {
        float reca=0;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select costo from recargo where cveRecargo='0'");
            
                while(rs.next())
                {   
                    reca=rs.getFloat("costo");
                }
                
            con.close();
            sentencia.close();
            rs.close();
        }
        catch(ClassNotFoundException | SQLException | HeadlessException ex) {System.out.println(ex);}
        
        return reca;
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
    
    private float getUltimo(int ult, String cuenta,int tarifa, int consumo,int bim)
    {
        
        float recargo=getRecargo();
        float total=0;
        
        if(tarifa==1 || tarifa==2 || tarifa==3)
        {
            if(consumo<maximos[tarifa-1][0])
                    {
                        total=2*costos[tarifa-1][0];
                        total+=(2*maximos[3][1]);
                        if(bim>2)
                            total+=((bim-2)*recargo);
                        total+=ult*costos[tarifa-1][0];
                        total+=ult*recargo;
                        total+=(ult*maximos[3][1]);
                    }
                    else
                    {
                         if(consumo>maximos[tarifa-1][0] && consumo<maximos[tarifa-1][1])
                         {
                             total=2*costos[tarifa-1][0];
                             total+=(2*maximos[3][1]);
                             consumo-=maximos[tarifa-1][0];
                             if(bim>2)
                                total+=((bim-2)*recargo);
                            total+=ult*costos[tarifa-1][0];
                            total+=ult*recargo;
                            total+=consumo*costos[tarifa-1][1];
                            total+=(ult*maximos[3][1]);
                         }
                         else
                         {
                             if(consumo>maximos[tarifa-1][1] && consumo<maximos[tarifa-1][2])
                             {
                                 total=2*costos[tarifa-1][0];
                                 total+=(2*maximos[3][1]);
                                 consumo-=maximos[tarifa-1][1];
                                 if(bim>2)
                                    total+=((bim-2)*recargo);
                                 total+=ult*costos[tarifa-1][0];
                                 total+=ult*recargo;
                                 total+=consumo*costos[tarifa-1][2];
                                 total+=(ult*maximos[3][1]);
                             }
                             else
                             {
                                 if(consumo>maximos[tarifa-1][2] && consumo<maximos[tarifa-1][3])
                                 {
                                    total=2*costos[tarifa-1][0];
                                    total+=(2*maximos[3][1]);
                                    consumo-=maximos[tarifa-1][2];
                                    if(bim>2)
                                        total+=((bim-2)*recargo);
                                    total+=ult*costos[tarifa-1][0];
                                    total+=ult*recargo;
                                    total+=consumo*costos[tarifa-1][3];
                                    total+=(ult*maximos[3][1]);
                                }
                                else
                                 {
                                     total=2*costos[tarifa-1][0];
                                    total+=(2*maximos[3][1]);
                                    consumo-=maximos[tarifa-1][3];
                                    if(bim>2)
                                        total+=((bim-2)*recargo);
                                    total+=ult*costos[tarifa-1][0];
                                    total+=ult*recargo;
                                    total+=consumo*costos[tarifa-1][3];
                                    total+=(ult*maximos[3][1]);
                                 }
                             }
                         }
                    }
        }
        else
        {
            total=2*maximos[3][0];
            total+=(2*maximos[3][1]);
            if(bim>2)
               total+=((bim-2)*recargo);
            total+=ult*maximos[3][0];
            total+=ult*recargo;
            total+=(ult*maximos[3][1]);
        }
        
        if(consumo==0)
            total=0;
        int cve,costo; cve=costo=0;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Cargar driver
            Connection con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SMAPASab","sa","sa");
            Statement sentencia=con.createStatement();
            ResultSet rs=sentencia.executeQuery("select * from instalación where cuenta='"+cuenta+"'");
            while(rs.next())
            {   
               cve=rs.getInt("revision");
               costo=rs.getInt("costo");
            }
        }
        catch(ClassNotFoundException | SQLException ex){}
        if(cve==2)
        {
            total+=costo;
        }
        return total;
    }
    
    public void imprimir()
    {
         try 
         {
           // tblusers.print(); // Imprime el jTable
              MessageFormat headerFormat = new MessageFormat("SMAPA El Sabino. Faltantes de pago.");
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
