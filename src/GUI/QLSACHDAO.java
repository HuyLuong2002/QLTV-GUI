package GUI;

import lib.MSSQLConnect;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class QLSACHDAO {
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    QLSACHDAO() throws Exception{
        MSSQLConnect connect = new MSSQLConnect();
        conn = connect.getConnection();
    }

}
