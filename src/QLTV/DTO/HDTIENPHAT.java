package QLTV.DTO;

public class HDTIENPHAT {
    private String MaHD;
    private String MADG;
    private int Tienphat;
    private int SL;
    public HDTIENPHAT(){}
    public HDTIENPHAT(String MaHD,String MADG,int Tienphat,int SL){
        this.MaHD = MaHD;
        this.MADG = MADG;
        this.Tienphat = Tienphat;
        this.SL = SL;
    }
    public void setMaHD(String MaHD){
        this.MaHD = MaHD;
    }
    public void setMaDG(String MADG){
        this.MADG = MADG;
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
    public String getMaDG(){
        return MADG;
    }
    public int getTienphat(){
        return Tienphat;
    }
    public int getSL(){
        return SL;
    }
}
