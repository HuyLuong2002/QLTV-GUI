package QLTV.DTO;

public class PHIEUMUON {
    private String MaDG;
    private String MaPM;
    private String Ngaymuon;
    private int SLtong;
    private String Ngaytra;
    public PHIEUMUON(){}
    public PHIEUMUON(String MaDG,String MaPM,String Ngaymuon,int SLtong,String Ngaytra){
        this.MaDG = MaDG;
        this.MaPM = MaPM;
        this.Ngaymuon = Ngaymuon;
        this.SLtong = SLtong;
        this.Ngaytra = Ngaytra;
    }
    public void setMaDG(String MaDG){
        this.MaDG = MaDG;
    }
    public void setMaPM(String MaPM){
        this.MaPM = MaPM;
    }
    public void setNgaymuon(String Ngaymuon){
        this.Ngaymuon = Ngaymuon;
    }
    public void setSLtong(int SLtong){
        this.SLtong = SLtong;
    }
    public void setNgaytra(String Ngaytra){
        this.Ngaytra = Ngaytra;
    }
    public String MaDG(){
        return MaDG;
    }
    public String MaPM(){
        return MaPM;
    }
    public String Ngaymuon(){
        return Ngaymuon;
    }
    public int SLtong(){
        return SLtong;
    }
    public String Ngaytra(){
        return Ngaytra;
    }
}
