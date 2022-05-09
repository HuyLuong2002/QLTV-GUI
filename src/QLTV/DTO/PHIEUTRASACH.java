package QLTV.DTO;

public class PHIEUTRASACH {
    private String MaPT;
    private String MaPM;
    private String Tinhtrangsach;
    private String Ngaytra;
    public static int Tienthue = 3000;
    private int Thanhtien;

    public PHIEUTRASACH() {
    }

    public PHIEUTRASACH(String MaPT, String MaPM, String Tinhtrangsach, String Ngaytra, int Tienthue, int Thanhtien) {
        this.MaPT = MaPT;
        this.MaPM = MaPM;
        this.Tinhtrangsach = Tinhtrangsach;
        this.Ngaytra = Ngaytra;
        PHIEUTRASACH.Tienthue = Tienthue;
        this.Thanhtien = Thanhtien;
    }

    public void setMaPT(String MaPT) {
        this.MaPT = MaPT;
    }

    public void setMaPM(String MaPM) {
        this.MaPM = MaPM;
    }

    public void setTinhtrangsach(String Tinhtrangsach) {
        this.Tinhtrangsach = Tinhtrangsach;
    }

    public void setNgaytra(String Ngaytra) {
        this.Ngaytra = Ngaytra;
    }

    public void setTienthue(int Tienthue) {
        PHIEUTRASACH.Tienthue = Tienthue;
    }

    public void setThanhtien(int Thanhtien) {
        this.Thanhtien = Thanhtien;
    }

    public String getMaPT() {
        return MaPT;
    }

    public String getMaPM() {
        return MaPM;
    }

    public String getTinhtrangsach() {
        return Tinhtrangsach;
    }

    public String getNgaytra() {
        return Ngaytra;
    }

    public int getTienthue() {
        return Tienthue;
    }

    public int getThanhtien() {
        return Thanhtien;
    }
}
