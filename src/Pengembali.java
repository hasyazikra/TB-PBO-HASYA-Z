import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.InputMismatchException;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

//Class "Pengembali" inheritance class "Buku"
public class Pengembali extends Buku{

    String pengembali;
    int id, id2;
    int x = 1;

    @Override
    public void Pengembali() {
        System.out.print("Masukkan Nama Pegembali\t\t: ");
        pengembali = input.nextLine(); 
        System.out.print("Masukkan ID Anggota\t\t: ");
        id = input.nextInt();
        input.nextLine();
    }

    //Methode String Date
    public String tglPengembalian() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    //Menyimpan data pengembali kedalam tabel "daftar_pengembali"
    public void InsertDB2() throws SQLException{
        String sql = "INSERT INTO daftar_pengembali (id_anggota,nama,no_buku,denda,tanggal_pengembalian) VALUES ('"+id+"','"+pengembali+"', '"+noBuk+"','"+denda+"','"+tglPengembalian()+"')";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        statement.execute(sql);
        //System.out.println("\nDATA BERHASIL DI INPUT !!!");    
    }
    
    //Mengambil data pada tabel "buku" dan "daftar_pengembali"
    public void ShowAll() throws SQLException{

        String sql = "SELECT buku.no_buku, buku.judul_buku, buku.nama_pengarang, daftar_pengembali.nama, daftar_pengembali.id_anggota, daftar_pengembali.denda, daftar_pengembali.tanggal_pengembalian FROM buku, daftar_pengembali  WHERE buku.no_buku = '"+noBuk+"' AND daftar_pengembali.no_buku = '"+noBuk+"'";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        
        if (result.next()){
        System.out.println("\n\t\tINFO!!!!");
        System.out.print("Nama Pengembali    \t\t: ");
        System.out.println(result.getString("daftar_pengembali.nama"));
        System.out.print("Nomor ID Anggota Perpustakaan \t: ");
        System.out.println(result.getInt("daftar_pengembali.id_anggota"));
        System.out.print("Nomor Buku yang dikembalikan  \t: ");
        System.out.println(result.getInt("buku.no_buku"));
        System.out.print("Judul Buku \t\t\t: "); 
        System.out.println(result.getString("buku.judul_buku"));
        System.out.print("Nama Pengarang \t\t\t: ");
        System.out.println(result.getString("buku.nama_pengarang"));
        System.out.print("Denda Keterlambatan \t\t: ");
        System.out.println(result.getString("daftar_pengembali.denda"));
        System.out.print("Tanggal Pengembalian \t\t: ");
        System.out.println(result.getDate("daftar_pengembali.tanggal_pengembalian"));
        }
    }

}
