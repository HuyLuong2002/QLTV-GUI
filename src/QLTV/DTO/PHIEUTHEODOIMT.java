package QLTV.DTO;

public class PHIEUTHEODOIMT {
    private String MaDG;
    public static int TiencocCB = 50000;
    private int Tiencoc;
    private int Tongmuon;
    public PHIEUTHEODOIMT(){}
    public PHIEUTHEODOIMT(String MaDG,int Tiencoc,int Tongmuon){
        this.MaDG = MaDG;
        this.Tiencoc = Tiencoc;
        this.Tongmuon = Tongmuon;
    }
    public void setMaDG(String MaDG){
        this.MaDG = MaDG;
    }
    public void setTiencocCB(int TiencocCB){
        PHIEUTHEODOIMT.TiencocCB = TiencocCB;
    }
    public void setTiencoc(int Tiencoc){
        this.Tiencoc = Tiencoc;
    }
    public void setTongmuon(int Tongmuon){
        this.Tongmuon = Tongmuon;
    }
    public String getMaDG(){
        return MaDG;
    }
    public int getTiencocCB(){
        return TiencocCB;
    }
    public int getTiencoc(){
        return Tiencoc;
    }
    public int getTongmuon(){
        return Tongmuon;
    }
}
