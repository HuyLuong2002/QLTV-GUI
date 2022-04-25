package QLTV.DTO;

public class PHIEUMUON {
    private String MaPM;
    private String Ngaymuon;
    private int SLtong;
    private String Ngaytra;
    private String TinhTrangMuon;
    private String MaDG;
    public PHIEUMUON(){}
    public PHIEUMUON(String MaPM,String MaDG,String Ngaymuon,int SLtong,String Ngaytra,String TinhTrangMuon){
        this.MaDG = MaDG;
        this.MaPM = MaPM;
        this.Ngaymuon = Ngaymuon;
        this.SLtong = SLtong;
        this.Ngaytra = Ngaytra;
        this.TinhTrangMuon = TinhTrangMuon;
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
    public void setTinhTrangMuon(String TinhTrangMuon) {
        this.TinhTrangMuon = TinhTrangMuon;
    }
    public String getMaDG(){
        return MaDG;
    }
    public String getMaPM(){
        return MaPM;
    }
    public String getNgaymuon(){
        return Ngaymuon;
    }
    public int getSLtong(){
        return SLtong;
    }
    public String getNgaytra(){
        return Ngaytra;
    }
    public String getTinhTrangMuon(){
        return TinhTrangMuon;
    }
}
