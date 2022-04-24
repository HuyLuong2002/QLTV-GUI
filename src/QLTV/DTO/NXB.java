package QLTV.DTO;

public class NXB {
    private String MaNXB;    
    private String TenNXB;
    public NXB(){}
    public NXB(String MaNXB,String TenNXB){
        this.MaNXB = MaNXB;
        this.TenNXB = TenNXB;
    }
    public void setMaNXB(String MaNXB){
        this.MaNXB = MaNXB;
    }
    public void setTenNXB(String TenNXB){
        this.TenNXB = TenNXB;
    }
    public String getMaNXB(){
        return MaNXB;
    }
    public String getTenNXB(){
        return TenNXB;
    }
}
