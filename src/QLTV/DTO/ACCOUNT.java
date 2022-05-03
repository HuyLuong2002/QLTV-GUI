package QLTV.DTO;

public class ACCOUNT {
    private String ID;
    private String Username;
    private String Password;
    private String HoLot;
    private String Ten;
    private String NgaySinh;
    private String GioiTinh;
    private String SDT;
    private int PhanQuyen;
    public ACCOUNT(){}
    public ACCOUNT(String ID, String Username, String Password, String HoLot, String Ten, String NgaySinh, String GioiTinh
    ,String SDT,int PhanQuyen){
        this.ID = ID;
        this.Username = Username;
        this.Password = Password;
        this.HoLot = HoLot;
        this.Ten = Ten;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.PhanQuyen = PhanQuyen;
    }
    public String getID(){
        return ID;
    }
    public String getUsername(){
        return Username;
    }
    public String getPassword(){
        return Password;
    }
    public String getHoLot(){
        return HoLot;
    }
    public String getTen(){
        return Ten;
    }
    public String getNgaySinh(){
        return NgaySinh;
    }
    public String getGioiTinh(){
        return GioiTinh;
    }
    public String getSDT(){
        return SDT;
    }
    public int getPhanQuyen(){
        return PhanQuyen;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    public void setUsername(String Username){
        this.Username = Username;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
    public void setHoLot(String HoLot){
        this.HoLot = HoLot;
    }
    public void setTen(String Ten){
        this.Ten = Ten;
    }
    public void setNgaySinh(String NgaySinh){
        this.NgaySinh = NgaySinh;
    }
    public void setGioiTinh(String GioiTinh){
        this.GioiTinh = GioiTinh;
    }
    public void setSDT(String SDT){
        this.SDT = SDT;
    }
    public void setPhanQuyen(int PhanQuyen){
        this.PhanQuyen = PhanQuyen;
    }
}
