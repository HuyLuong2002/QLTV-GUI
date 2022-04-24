package QLTV.DTO;

public class CHITIETPHIEUMUON {
    private String MaPM;
    private String Masach;
    private int SL;
    public CHITIETPHIEUMUON(){}
    public CHITIETPHIEUMUON(String MaPM, String Masach, int SL){
        this.MaPM = MaPM;
        this.Masach = Masach;
        this.SL = SL;
    }
    public void setMaPM(String MaPM){
        this.MaPM =MaPM;
    }
    public void setMasach(String Masach){
        this.Masach =Masach;
    }
    public void setSL(int SL){
        this.SL =SL;
    }
    public String getMaPM(){
        return MaPM;
    }
    public String getMasach(){
        return Masach;
    }
    public int getSL(){
        return SL;
    }
}
