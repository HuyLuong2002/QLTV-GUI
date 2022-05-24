package QLTV.BUS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import QLTV.DAO.ACCOUNTDAO;
import QLTV.DTO.ACCOUNT;

public class ACCOUNTBUS {
    public static ArrayList<ACCOUNT> dsacc;
    public static Set<ACCOUNT> htXoa = new HashSet<ACCOUNT>();
    public static ArrayList<ACCOUNT> htSua = new ArrayList<ACCOUNT>();

    public ACCOUNTBUS(){}

    public void docDSACC() throws Exception{
        ACCOUNTDAO data = new ACCOUNTDAO();
        if(dsacc == null)
            dsacc = new ArrayList<ACCOUNT>();
        dsacc = data.docAcccList();
    }

    public int them(ACCOUNT acc) throws Exception{
        if(KTID(acc.getID()) == 0){
            JOptionPane.showMessageDialog(null, "ID vừa nhập bị trùng. Mời nhập lại!","Lỗi",
            JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if(KTUSER(acc.getUsername()) == 0){
            JOptionPane.showMessageDialog(null, "User vừa nhập đã tồn tại. Mời nhập user khác!","Lỗi", 
            JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            int kt = -1;
            ACCOUNTDAO data = new ACCOUNTDAO();
            kt = data.them(acc);
            if(kt == 0){
                dsacc.add(acc);
            }
            return kt;
        }
    }
    public int hoantacXoa(ACCOUNT acc) throws Exception{
        if(KTID(acc.getID().trim()) == 0){
            JOptionPane.showMessageDialog(null, "ID vừa nhập bị trùng. Mời nhập lại!","Lỗi",
            JOptionPane.ERROR_MESSAGE);
            return -1;
        } else if(KTUSER(acc.getUsername().trim()) == 0){
            JOptionPane.showMessageDialog(null, "User vừa nhập đã tồn tại. Mời nhập user khác!","Lỗi", 
            JOptionPane.ERROR_MESSAGE);
            return -1;
        } else {
            ACCOUNTDAO data = new ACCOUNTDAO();
            data.hoantacXoa(acc);
            dsacc.add(acc);
            return 1;
        }
    }
    public void sua(ACCOUNT newacc, ACCOUNT oldacc, int i) throws Exception{
        ACCOUNTDAO data = new ACCOUNTDAO();
        data.sua(newacc, oldacc);
        dsacc.set(i, newacc);
    }
    public void xoa(String ID, int i) throws Exception {
        ACCOUNTDAO data = new ACCOUNTDAO();
        data.xoa(ID);
        dsacc.remove(i);
    }
    public int KTID(String NewID){
        for(ACCOUNT account : dsacc)
            if(account.getID().trim().equals(NewID))
                return 0;
        return 1;
    }
    public int KTUSER(String NewUser){
        for(ACCOUNT account : dsacc)
            if(account.getUsername().trim().equals(NewUser))
                return 0;
        return 1;
    }
    public int login(String username, String pass) throws Exception{
        int kt = -1;
        ACCOUNTDAO data = new ACCOUNTDAO();
        kt = data.DangNhap(username, pass);
        return kt;
    }
}
