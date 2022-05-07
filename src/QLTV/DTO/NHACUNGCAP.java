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
    public String id;
    public String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public NHACUNGCAP(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public NHACUNGCAP() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    }
    
}
