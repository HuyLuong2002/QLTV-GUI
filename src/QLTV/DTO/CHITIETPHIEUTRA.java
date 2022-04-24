package QLTV.DTO;

public class CHITIETPHIEUTRA {
    private String MaPT;
    private String Masach;
    private int SL;
    public CHITIETPHIEUTRA(){}
    public CHITIETPHIEUTRA(String MaPT,String Masach,int SL){
        this.MaPT = MaPT;
        this.Masach = Masach;
        this.SL = SL;
    }
    public void setMaPT(String MaPT){
        this.MaPT = MaPT;
    }
    public void setMasach(String Masach){
        this.Masach = Masach;
    }
    public void setSL(int SL){
        this.SL = SL;
    }
    public String getMaPT(){
        return MaPT;
    }
    public String getMasach(){
        return Masach;
    }
    public int getSL(){
        return SL;
    }
}
