import java.util.InputMismatchException;
import com.mysql.cj.protocol.Resultset;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.sql.*;

//class program
public class Program {

    //Koneksi database
    static String url = "jdbc:mysql://localhost:3306/perpustakaan";
    static Connection con;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("\n|||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("||  THIS IS BOOK RETURNING PROGRAM, WELCOME ^_^  ||");
        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||");
        System.out.println("\n   Please log in as admin first");
        System.out.println("---------------------------------------------------");
        Admin();
        
    }
    
    private static void menu() throws IOException {
        
        boolean lanjutkan = true;
        int pilihan;
        int x = 0;

        //Exception
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,"root","");
            //System.out.println("Database Terhubung");

            //Perulangan While
            while (lanjutkan){
                System.out.println("");
                System.out.println("=========================================");
                System.out.println("             DAFTAR MENU PROGRAM         ");
                System.out.println("=========================================");
                System.out.println("1. Kembalikan Buku");
                System.out.println("2. Ubah Data Buku");
                System.out.println("3. Cek Daftar Buku yang akan Dikembalikan"); 
                System.out.println("4. Hapus data Buku yang akan Dikembalikan");
                System.out.println("5. Keluar dari Program");
                System.out.println("=========================================");
                System.out.println("");
                System.out.print("Masukkan Pilihan : ");
                pilihan = input.nextInt();
                System.out.println("-----------------------------------------\n");
            
            //Percabangan Switch Case
            switch (pilihan){

                case 1 :
                    Pengembali kembalikan = new Pengembali();
                    kembalikan.Pengembali();
                    kembalikan.JudulBuku();
                    kembalikan.NoBuku();
                    kembalikan.NamaPengarang();
                    kembalikan.telat();
                    kembalikan.denda();
                    kembalikan.tglPengembalian();
                    kembalikan.InsertDb1();
                    kembalikan.InsertDB2();
                    kembalikan.ShowAll();
                    
                    Console();
                    Clean clear = new Clean();
        
                    break;
                
                case 2 : 
                    CRUD edit = new CRUD();
                    edit.Edit();

                    Console();
                    Clean clear2 = new Clean();
                    
                    break;

                case 3 :
                    CRUD tampilkan = new CRUD();
                    tampilkan.Show();

                    Console();
                    Clean clear3 = new Clean();

                    break;
                
                case 4 : 
                    CRUD hapus = new CRUD();
                    hapus.Delete();
                    hapus.Show();

                    Console();
                    Clean clear4 = new Clean();

                    break;
                case 5 :
                    lanjutkan = false;
                    System.out.println("THANK YOU FOR USING THIS PROGRAM");
                    DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
                    DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
                    Date tanggal = new Date();
                    System.out.println("==========================================");
                    System.out.println("= Was being run on : "+formatTanggal.format(tanggal)+" =");
                    System.out.println("= Closed time      : "+formatJam.format(tanggal)+" WIB      =");
                    System.out.println("==========================================");


                    Console();
                    break;
                
                default :
                    System.out.println("MENU IS NOT AVAILABLE !!!!");
                    System.out.println("");
                    break;
                }
            }
        }catch(ClassNotFoundException ex) {
            System.err.println("Driver eror");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("Connection Unsuccessful");
        }
    }

    //methode Admin
    private static void Admin() throws SQLException, IOException{

        //Membuat objek HashMap baru
        Map<String, String> Login = new HashMap<String, String>();

        //Mengambil data dari database perpustakaan pada tabel admin
        String inputUsername, inputPassword;
        String sql = "SELECT * FROM admin";
        boolean relogin = true;
        con = DriverManager.getConnection(url,"root","");
        Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sql);

        //Perulangan While
        while (result.next())
        {
            //mengambil nilai di database dan menyimpannya ke dalam variable
            String username = result.getString("username");
            String password = result.getString("password");

            //input key dan value 
            Login.put(username, password);
        }

        //perulangan while
        while (relogin)
        {
            
            System.out.print("Username : ");
            inputUsername = input.nextLine();
            System.out.print("Password : ");
            inputPassword = input.nextLine();
            System.out.println("---------------------------------------------------");

            //percabangan if
            if (Login.containsValue(inputUsername)==true) //method bawaan HashMap
            {
                //percabangan if
                if (Login.get(inputUsername).equals(inputPassword)) //method bawaan HashMap dan method string
                {
                    System.out.println("Log in successful");
                    Console();
                    Clean clear5 = new Clean();
                    menu();
                    relogin = false;
                }
                else
                {
                    relogin = true;
                }
            }
            else
            {
                relogin = true;
            }
        }
        statement.close();
    }

    //Memberikan inputan untuk ke sesi selanjutnya
    private static void Console() throws IOException{
        System.out.print("\nEnter a character to next: ");
        // Read the char
        char ch = (char) System.in.read();
    }
    
}