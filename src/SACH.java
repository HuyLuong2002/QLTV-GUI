public class SACH{
      private String Masach;
      private String Tensach;
      private String MaNXB;
      private String MaTG;
      private String NamXB;
      private int SLtong;
      private int SL;
      private int Dongia;
      public SACH(){}
      public SACH(String Masach, String Tensach, String MaNXB, 
      String MaTG, String NamXB, int SLtong, int SL, int Dongia){
            this.Masach = Masach;
            this.Tensach = Tensach;
            this.MaNXB = MaNXB;
            this.MaTG = MaTG;
            this.NamXB = NamXB;
            this.SLtong = SLtong;
            this.SL = SL;
            this.Dongia = Dongia;
      }
      public void setMasach(String Masach) {
            this.Masach = Masach;
      }
      public void setTensach(String Tensach) {
            this.Tensach = Tensach;
      }
      public void setMaNXB(String MaNXB) {
            this.MaNXB = MaNXB;
      }
      public void setMaTG(String MaTG) {
            this.MaTG = MaTG;
      }
      public void setNamXB(String NamXB) {
            this.NamXB = NamXB;
      }
      public void setSLtong(int SLtong) {
            this.SLtong = SLtong;
      }
      public void setSL(int SL) {
            this.SL = SL;
      }
      public void setDongia(int Dongia){
            this.Dongia = Dongia;
      }
      public String getMasach() {
            return Masach;
      }
      public String getTensach() {
            return Tensach;
      }
      public String getMaNXB() {
            return MaNXB;
      }
      public String getMaTG() {
            return MaTG;
      }
      public String getNamXB() {
            return NamXB;
      }
      public int getSLtong() {
            return SLtong;
      }
      public int getSL() {
            return SL;
      }
      public int getDongia() {
            return Dongia;
      }
}
