package QLTV.DTO;

public class THELOAI {
    private String MaTL;
    private String TenTL;
    private int SLTL;
    public THELOAI(){};
    public THELOAI(String MaTL,String TenTL,int SLTL){
        this.MaTL = MaTL;
        this.TenTL = TenTL;
        this.SLTL = SLTL;
    }
    public void setMaTL(String MaTL){
        this.MaTL = MaTL;
    }
    public void setTenTL(String TenTL){
        this.TenTL = TenTL;
    }
    public void setSLTL(int SLTL){
        this.SLTL = SLTL;
    }
    public String getMaTL(){
        return MaTL;
    }
    public String getTenTL(){
        return TenTL;
    }
    public int getSLTL(){
        return SLTL;
    }
}