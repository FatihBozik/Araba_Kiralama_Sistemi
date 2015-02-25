import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean( name = "kiralaBean" )

//@SessionScoped

public class Kirala 
{
    private String kiralamaModel;
    private double fiyat;
    private int stok;
    private int gun;

    Connection baglanti;
    private String tabloAdi;

    public Kirala() {
        try {
            // Bağlantı kuruluyor.
            baglanti = DriverManager.getConnection("jdbc:derby://localhost:1527/ArabaKiralamaDB;user=APP;password=APP;");
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getGun() 
    {
            return gun;
    }
    public void setGun(int gun) 
    {
            this.gun = gun;
    }
   
    public String getKiralamaModel() 
    {
            return kiralamaModel;
    }
    public void setKiralamaModel(String kiralamaModel) 
    {
            this.kiralamaModel = kiralamaModel;
    }
    
     public double getFiyat() 
    {
            return fiyat;
    }
    public void setFiyat(double fiyat) 
    {
            this.fiyat = fiyat;
    }
    
    public int getStok() 
    {
            return stok;
    }
    
    public void setTabloAdi(String tabloAdi) 
    {
            this.tabloAdi = tabloAdi;
    }
    public String getTabloAdi() 
    {
            return tabloAdi;
    }
    public void setStok(int stok) 
    {
            this.stok = stok;
    }

         
         public void kiraver(String kiralamaModel) throws SQLException
        {
            int stok;
            PreparedStatement preparedStatement = null;
            setKiralamaModel(kiralamaModel);
            // Bağlantı üzerinden sorguyu çalıştır.
            Statement sta = baglanti.createStatement();
            ResultSet res = sta.executeQuery("select * from " + getTabloAdi() + " where 'KiralamaModel' like '%" + getKiralamaModel() + "%' ");
            res.next();
            stok=res.getInt("STOK");
            stok--;
            preparedStatement = baglanti.prepareStatement("update APP." + getTabloAdi() +  " set STOK = ? where 'KiralamaModel' like '%" + getKiralamaModel() + "%'");
            preparedStatement.setInt(1, stok);
            preparedStatement.executeUpdate();
            
            
            
         
            
        }
         
        
   
   
         
     public void stokver(String kiralamaModel) throws SQLException {
        setKiralamaModel(kiralamaModel);
        // Bağlantı üzerinden sorguyu çalıştır.
        Statement sta = baglanti.createStatement();
        ResultSet res = sta.executeQuery("select * from " + getTabloAdi() + " where KiralamaModel like '%" + getKiralamaModel() + "%' ");
            res.next();
            setStok(res.getInt("STOK"));
     

    }
            
          
           public void fiyatver(String kiralamaModel) throws SQLException
        {
            setKiralamaModel(kiralamaModel);
            // Bağlantı üzerinden sorguyu çalıştır.
            Statement sta = baglanti.createStatement();
            ResultSet res = sta.executeQuery("select * from " + getTabloAdi() + " where 'KiralamaModel' like '%" + getKiralamaModel() + "%' ");
            res.next();
            double fiyat=res.getDouble("FIYAT");
            fiyat=fiyat*getGun();
            setFiyat(fiyat);
           
        }
}
