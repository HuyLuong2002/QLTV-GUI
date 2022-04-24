package QLTV.DTO;

public class NHACUNGCAP {
    private String MaNCC;
    private String TenNCC;
    public NHACUNGCAP(){}
    public NHACUNGCAP(String MaNCC,String TenNCC){
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
    }
    public void setMaNCC(String MaNCC){
        this.MaNCC = MaNCC;
    }
    public void setTenNCC(String TenNCC){
        this.TenNCC = TenNCC;
    }
    public String getMaNCC(){
        return MaNCC;
    }
    public String getTenNCC(){
        return TenNCC;
    }
}
