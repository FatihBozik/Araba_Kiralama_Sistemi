import java.sql.*;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "registerBean")

public class RegisterDetail
{
    RememberBean rememberBean;
    private String kullaniciAdi;
    private String sifre;
    private String isim;
    private String soyisim;
    private String email;
    private String telefon;
    Connection baglanti;
   
    public RegisterDetail()
    {
        try
        {
             // Bağlantı kuruluyor.
             baglanti = DriverManager.getConnection("jdbc:derby://localhost:1527/ArabaKiralamaDB;user=APP;password=APP;");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }  

    public String getKullaniciAdi() 
    {
            return kullaniciAdi;
    }
    public void setKullaniciAdi(String kullaniciAdi) 
    {
            this.kullaniciAdi = kullaniciAdi;
    }
    public String getSifre() 
    {
            return sifre;
    }
    public void setSifre(String sifre) 
    {
            this.sifre = sifre;
    }
    
    public String getIsim() 
    {
        return isim;
    }
    public void setIsim(String isim) 
    {
            this.isim = isim;
    }
    public String getSoyisim() 
    {
        return soyisim;
    }
    public void setSoyisim(String soyisim) 
    {
            this.soyisim = soyisim;
    }
    public String getEmail() 
    {
        return email;
    }
    public void setEmail(String email) 
    {
            this.email = email;
    }
    public String getTelefon() 
    {
            return telefon;
    }
    public void setTelefon(String telefon) 
    {
            this.telefon = telefon;
    }
   
    public boolean databasedeVarmi() throws SQLException
    {
        // Bağlantı üzerinden sorguyu çalıştır.
        Statement sta = baglanti.createStatement();
        ResultSet res = sta.executeQuery("select KullanıcıAdı, Email from Users");
        
        while (res.next()) 
        {
             if(kullaniciAdi.equals(res.getString("KullanıcıAdı")) || email.equals(res.getString("Email")) )
             {
                    return true;
             }
        }
        return false;
    }
     
    public void addDB() throws SQLException
    {
              if(!databasedeVarmi())
              {
                    String query = "INSERT INTO Users " + "VALUES('" + kullaniciAdi + "', " + "'" + sifre + "', " + "'" + isim + "', " + "'" + soyisim + "', " + "'" + email + "', " +  "'" + telefon + "')";
              
                    // Bağlantı üzerinden sorguyu çalıştır.
                    Statement sta = baglanti.createStatement();
                    sta.executeUpdate(query);
              }
              else
              {
                    // Veritabanına kullanıcıyı ekleme
              }

    }
    
   

}
