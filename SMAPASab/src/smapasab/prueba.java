/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smapasab;

import java.util.Calendar;

/**
 *
 * @author Eduardo
 */
public class prueba 
{
    public prueba()
    {
        Calendar Cal= Calendar.getInstance();
        String fec= Cal.get(Cal.DATE)+"-"+(Cal.get(Cal.MONTH)+1)+"-"+Cal.get(Cal.YEAR);
        System.out.println(fec);
    }
    
}
