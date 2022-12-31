import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.InputMismatchException;
import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class CRUD extends Buku{

    //koneksi database
    String url = "jdbc:mysql://localhost:3306/perpustakaan";
    Connection con;

    Scanner input = new Scanner(System.in);
    String pengembali;
    int id, id2;

    //Mengedit data yang sudah disimpan kedalam database
    public void Edit() throws SQLException{
        Show();

        //try
        try {
            System.out.print("\nMasukkan Nomor Buku yang akan di Ubah : ");
            id2 = 0;
            id2 = input.nextInt();
            input.nextLine();
            System.out.println("-----------------------------------------\n");

            String sql = "SELECT buku.no_buku, buku.judul_buku, buku.nama_pengarang, daftar_pengembali.nama FROM buku, daftar_pengembali  WHERE buku.no_buku = '"+id2+"' AND daftar_pengembali.no_buku = '"+id2+"'";
            con = DriverManager.getConnection(url,"root","");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            //Percabangan IF
            if (result.next()){
                System.out.print("Nama Pengembali ["+result.getString("daftar_pengembali.nama")+"]\t : ");
                pengembali = input.nextLine();

                System.out.print("Judul Buku ["+result.getString("buku.judul_buku")+"]\t\t : ");
                juBuk = input.nextLine();

                System.out.print("Nama PEngarang ["+result.getString("buku.nama_pengarang")+"]\t : ");
                Pengarang = input.nextLine();

                sql = "UPDATE buku, daftar_pengembali SET buku.judul_buku ='"+juBuk+"', buku.nama_pengarang='"+Pengarang+"', daftar_pengembali.nama='"+pengembali+"' WHERE buku.no_buku='"+id2+"' AND daftar_pengembali.no_buku='"+id2+"'";
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data ");
                }
            }
            statement.close();  
        //exception SQL      
        } catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
    }

    //Menampilkan data yang sudah disimpan kedalam database
    public void Show() throws SQLException{
        int x = 1;
        String sql = "SELECT no_buku, judul_buku FROM buku";
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        //Perulangan While
        while(result.next()){
            System.out.print(x+". ");
            System.out.print("Nomor Buku :");
            System.out.print(result.getString("no_buku"));
            System.out.print("\tJudul Buku :");
            System.out.println(result.getString("judul_buku"));
            x++;
        }
    }

    //menghapus data yang sudah disimpan kedalam database
    public void Delete() throws SQLException{

        String text4 = "\nHapus Data Buku";
		System.out.println(text4.toUpperCase());
		
        //try
		try{
	        Show();
	        System.out.print("\nMasukan Nomor Buku : ");
	        Integer Id2= Integer.parseInt(input.nextLine());
	        
            String sql = "DELETE FROM buku WHERE no_buku = "+Id2;
            
	        con = DriverManager.getConnection(url,"root","");
	        Statement statement = con.createStatement();

	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus buku (Nomor "+Id2+")");
	        }
	   }
        //exception SQL
		catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }

        //exception inputan tidak sesuai
        catch(Exception e){
            System.out.println("masukan data yang benar");
        }
	}  

    
}
