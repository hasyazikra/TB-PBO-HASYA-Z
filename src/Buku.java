import java.util.*;
import java.text.*;
import java.io.*;
import java.sql.*;

//class Buku yang mengimplementasikan Interface Pustaka
public class Buku implements Pustaka{

    //Koneksi Database
    Connection con;
    String url = "jdbc:mysql://localhost:3306/perpustakaan";

    Scanner input = new Scanner(System.in);
    int noBuk, telat;
    String juBuk, Pengarang, denda;

    
    @Override
    public void NoBuku() {
        System.out.print("Masukkan Nomor Buku\t\t: ");
        noBuk = input.nextInt();
        input.nextLine();
    }

    @Override
    public void JudulBuku() {
        System.out.print("Masukkan Judul Buku\t\t: ");
        juBuk = input.nextLine(); 
    }

    @Override
    public void NamaPengarang() {
        System.out.print("Nama Pengarang\t\t\t: ");
        Pengarang = input.nextLine(); 
    }

    @Override
    public void telat() {
        System.out.print("Total Keterlambatan (hari)\t: ");
        telat = input.nextInt();
        input.nextLine();
    }

    @Override
    public void denda() {

        //percabangan
        if (telat == 1 ){
            denda = "RP15000"; 
        }
        else if (telat == 2){
            denda = "Rp20000";
        }
        else if (telat == 3){
            denda = "Rp300000";
        }
        else if (telat >=4){
            denda = "1 Buku";
            System.out.println("(Bebas Buku apa saja)");
        }
    }

    @Override
    public void Pengembali() {
    }

    //Menyimpan data varibel kedalam tabel "barang"
    public void InsertDb1() throws SQLException{
        String sql = "INSERT INTO buku (no_buku,judul_buku,nama_pengarang) VALUES ('"+noBuk+"', '"+juBuk+"','"+Pengarang+"')";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        statement.execute(sql);   
        System.out.println("\nDATA BERHASIL DI INPUT !!!"); 
    }
}
