package QLTV.DTO;

public class CHITIETPHIEUNHAP {
    private String MaPN;
    private String MAsach;
    private int SL;
    public CHITIETPHIEUNHAP(){}
    public CHITIETPHIEUNHAP(String MaPN,String MAsach,int SL){
        this.MaPN = MaPN;
        this.MAsach = MAsach;
        this.SL = SL;
    }
    public void setMaPN(String MaPN){
        this.MaPN = MaPN;
    }
    public void setMAsach(String MAsach){
        this.MAsach = MAsach;
    }
    public void setSL(int SL){
        this.SL = SL;
    }
    public String getMaPN(){
        return MaPN;
    }
    public String getMAsach(){
        return MAsach;
    }
    public int getSL(){
        return SL;
    }
}
