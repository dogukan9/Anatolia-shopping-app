import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author doğukan
 */
public class GirisDatabase {
       private String kul_adı="root";
    private String parola="";
    private String db_isim="login";
    private String host="localhost";
    private int port=3306;
    private String name="";
    private  Statement statement=null;
private  Connection con=null;
private  PreparedStatement preparedStatement=null;

    
    
    public void Ekle2(String ad,String soyad,String numara,String ödeme,String adres){
        String sorgu="Insert Into bilgiler (ad,soyad,telefon,odemeturu,adress) VALUES (?,?,?,?,?)";
        
           try {
               preparedStatement=con.prepareStatement(sorgu);
               preparedStatement.setString(1,ad);
                      preparedStatement.setString(2,soyad);
                             preparedStatement.setString(3,numara);
                                    preparedStatement.setString(4,ödeme);
                                    preparedStatement.setString(5,adres);
                            //     preparedStatement.setString(6,urun);
                              //      preparedStatement.setString(7,tutar);
                                            
                                    preparedStatement.executeUpdate();
                                          
                                           
           } catch (SQLException ex) {
               Logger.getLogger(GirisDatabase.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
    }
 

    public  void Ekle(String ad,String sifre,String epost) {
        String sorgu="Insert Into kullanıcılar (kullanıcı_ad,kullanıcı_sifre,eposta) VALUES (?,?,?)";
           try {
               preparedStatement=con.prepareStatement(sorgu);
               preparedStatement.setString(1, ad);
               preparedStatement.setString(2, sifre);
               preparedStatement.setString(3, epost);
               preparedStatement.executeUpdate();
           } catch (SQLException ex) {
               Logger.getLogger(GirisDatabase.class.getName()).log(Level.SEVERE, null, ex);
           }
        
       
    }
    public boolean Bilgi(String kullanıcı,String parolaa){
          String sorgu="Select *From kullanıcılar";
           try {
               statement=con.createStatement();
               ResultSet rs=statement.executeQuery(sorgu);
               while(rs.next()){
                   String ad=rs.getString("kullanıcı_ad");
                   String sifre=rs.getString("kullanıcı_sifre");
                
                   if(ad.equals(kullanıcı)){
                       if(sifre.equals(parolaa)){
                   
                   return true;
                  
               }
                   }
               }
              
           } catch (SQLException ex) {
               Logger.getLogger(GirisDatabase.class.getName()).log(Level.SEVERE, null, ex);
               
           }
    
    
    
        
     return false;
    }
      public boolean Bilgi2(String kullanıcı){
          String sorgu="Select *From kullanıcılar";
           try {
               statement=con.createStatement();
               ResultSet rs=statement.executeQuery(sorgu);
               while(rs.next()){
                   String ad=rs.getString("kullanıcı_ad");
                   
                
                   if(ad.equals(kullanıcı)){
                    
                   
                   return false;
                  
               }
               }
              
           } catch (SQLException ex) {
               Logger.getLogger(GirisDatabase.class.getName()).log(Level.SEVERE, null, ex);
               
           }
    
    
    
        
     return true;
    }
    
       
    

    public GirisDatabase() {
        String url="jdbc:mysql://"+host+":"+port+"/"+db_isim+"?useUnicode=true&characterEncoding=utf8";
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GirisDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=(Connection) DriverManager.getConnection(url,kul_adı, parola);
            System.out.println("Bağlantı başarılı");
        } catch (SQLException ex) {
            System.out.println("Bağlanti başarısız");
        }
    }

    
}
