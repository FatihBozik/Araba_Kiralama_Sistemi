import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean( name="hesabimBean" )
 
public class Hesabim
{
    String kullaniciAdi;
    int kiralananArabaSayisi;
    double borc;
    private DataSource dataSource;
    
    
    
    
    public Hesabim()
    {
        try
        {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/ArabaKiralamaDB");
        }
        catch (NamingException e) 
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
     public double getBorc() 
    {
            return borc;
    }
    public void setKullaniciAdi(double borx) 
    {
            this.borc = borc;
    }
}

