package QLTV.DTO;

public class HDTIENPHAT {
    private String MaHD;
    private String Masach;
    private int Tienphat;
    private int SL;
    public HDTIENPHAT(){}
    public HDTIENPHAT(String MaHD,String Masach,int Tienphat,int SL){
        this.MaHD = MaHD;
        this.Masach = Masach;
        this.Tienphat = Tienphat;
        this.SL = SL;
    }
    public void setMaHD(String MaHD){
        this.MaHD = MaHD;
    }
    public void setMasach(String Masach){
        this.Masach = Masach;
    }
    public void setTienphat(int Tienphat){
        this.Tienphat = Tienphat;
    }
    public void setSL(int SL){
        this.SL = SL;
    }
    public String getMaHD(){
        return MaHD;
    }
    public String getMasach(){
        return Masach;
    }
    public int getTienphat(){
        return Tienphat;
    }
    public int getSL(){
        return SL;
    }
}
