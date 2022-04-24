package MyCustom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
public class MSSQLConnect {
    String host="DESKTOP-5IRG803\\SQLEXPRESS";
    String database="QLTV";
    String username="sa";
    String password="sa";
    String url = "jdbc:sqlserver://DESKTOP-5IRG803\\SQLEXPRESS:1433;databaseName=QLTV;trustServerCertificate=true;integratedSecurity=true;";
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    public MSSQLConnect(){

    }
    public MSSQLConnect(String host, String database, String username, String password){
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    protected void driverTest() throws Exception{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

    }
    public Connection getConnection() throws Exception{
        if(this.conn==null){
            driverTest();
            String url="jdbc:sqlserver://" + this.host + ":1433;databaseName=" + this.database + ";trustServerCertificate=true;integratedSecurity=true;";
            try {
                this.conn = DriverManager.getConnection(url,this.username,this.password);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Kết nối database thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return this.conn;
    }
    protected Statement getStatement() throws Exception {
        //Kiểm tra statement nếu = null hoặc đã đóng.
        if(this.st==null ? true : this.st.isClosed()) {
            this.st = this.getConnection().createStatement();
        }
        return this.st;
    }

    //Hàm thực thi câu lệnh select lấy dữ liệu từ CSDL
    public ResultSet excuteQuery(String query) throws Exception {
        try {
            this.rs=getStatement().executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return this.rs;
    }
    public int executeUpdate(String query) throws Exception {
        int res=Integer.MIN_VALUE;
        try {
            res=getStatement().executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            this.Close();
        }
        return res;
    }

    public void Close() throws Exception {
        if(this.rs!=null && !this.rs.isClosed()){
            this.rs.close();
            this.rs=null;
        }
        if(this.st!=null && !this.st.isClosed()){
            this.st.close();
            this.st=null;
        }
        if(this.conn!=null && !this.conn.isClosed()){
            this.conn.close();
            this.conn=null;
        }
    }
}
