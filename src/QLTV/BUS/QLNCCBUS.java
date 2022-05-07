/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QLTV.BUS;

import java.util.ArrayList;
import QLTV.DAO.QLNCCDAO;
import QLTV.DTO.NHACUNGCAP;

/**
 *
 * @author MSI
 */
public class QLNCCBUS {
    public static ArrayList<NHACUNGCAP> a= new ArrayList<>();
    public void add (NHACUNGCAP nxb) throws Exception
    {
        QLNCCDAO dataDao = new QLNCCDAO();
        dataDao.them(nxb);
        
    }
    public NHACUNGCAP findid(String id) throws Exception
    {
        
        QLNCCDAO dataDao= new QLNCCDAO();
        dataDao.tim(id);
       return dataDao.tim(id);
    }
    public void update(NHACUNGCAP nxb) throws Exception 
    {
        QLNCCDAO dataDao= new QLNCCDAO();
       
            dataDao.sua(nxb);
        
           
        
        
    }
    public void delete (String id) throws Exception
    {
        QLNCCDAO dataDao= new QLNCCDAO();
         dataDao.xoa(id);
    }
}
