/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package QLTV.DTO;

/**
 *
 * @author MSI
 */
public class NHACUNGCAP {
    public String MaNCC;
    public String TenNCC;

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public NHACUNGCAP(String MaNCC, String TenNCC) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
    }

    public NHACUNGCAP() {
    }
    
}
