package QLTV.BUS;

import java.util.ArrayList;

import MyCustom.MSSQLConnect;
import QLTV.DAO.QLNVDAO;
import QLTV.DTO.DOCGIA;

public class QLNVBUS {
    public static ArrayList<DOCGIA> dsdg;
    public static ArrayList<DOCGIA> htXoa = new ArrayList<DOCGIA>();

    public QLNVBUS() {

    }

    public void docDS() throws Exception {
        QLNVDAO data = new QLNVDAO();
        if (dsdg == null)
            dsdg = new ArrayList<DOCGIA>();
        dsdg = data.docDS();       
    }
}
