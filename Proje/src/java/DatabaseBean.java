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

@ManagedBean(name = "databaseBean")

public class DatabaseBean 
{
    private String marka;
    private String kiralamaModeli;
    private int yil;
    private String motor;
    private String vites;
    private String kasaTipi;
    private String yakitTipi;
    private double fiyat;
    private int id;
    private String tabloAdi;
    private int stok;
   
    DataSource dataSource;
    public DatabaseBean()
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
    
    public void setStok(int stok)
    {
        this.stok = stok;
    }
    public int getStok()
    {
        return stok;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }
    public void setTabloAdi(String tabloAdi)
    {
        this.tabloAdi = tabloAdi;
    }
    public String getTabloAdi()
    {
        return tabloAdi;
    }
    public void setMarka(String marka)
    {
        this.marka = marka;
    }
    public String getMarka()
    {
        return marka;
    }
    
    public void setKiralamaModeli(String kiralamaModeli)
    {
        this.kiralamaModeli = kiralamaModeli;
    }
    public String getKiralamaModeli()
    {
        return kiralamaModeli;
    }
    
    public void setYil(int yil)
    {
        this.yil = yil;
    }
    public int getYil()
    {
        return yil;
    }
    
    public void setMotor(String motor)
    {
        this.motor = motor;
    }
    public String getMotor()
    {
        return motor;
    }
    
    public void setVites(String vites)
    {
        this.vites = vites;
    }
    public String getVites()
    {
        return vites;
    }
    
    public void setKasaTipi(String kasaTipi)
    {
        this.kasaTipi = kasaTipi;
    }
    public String getKasaTipi()
    {
        return kasaTipi;
    }
    
    public void setYakitTipi(String yakitTipi)
    {
        this.yakitTipi = yakitTipi;
    }
    public String getYakitTipi()
    {
        return yakitTipi;
    }
    
    public void setFiyat(double fiyat)
    {
        this.fiyat = fiyat;
    }
    public double getFiyat()
    {
        return fiyat;
    }
    
    public String save() throws SQLException
    {
    
        if ( dataSource == null )
        throw new SQLException( "Unable to obtain DataSource" );

   
        Connection connection = dataSource.getConnection();

   
        if ( connection == null )
        throw new SQLException( "Unable to connect to DataSource" );

        try
        {
            // create a PreparedStatement to insert a new address book entry
            PreparedStatement arabaEkle =
            connection.prepareStatement( "INSERT INTO " + getTabloAdi() + " (MARKA, KIRALAMAMODEL, YIL, MOTOR, VITES, KASATIPI, YAKITTIPI, STOK, FIYAT)"
            +  " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" );

            // specify the PreparedStatement's arguments
            arabaEkle.setString( 1, getMarka());
            arabaEkle.setString( 2, getKiralamaModeli() );
            arabaEkle.setInt(3, getYil());
            arabaEkle.setString( 4, getMotor());
            arabaEkle.setString( 5, getVites());
            arabaEkle.setString( 6, getKasaTipi());
            arabaEkle.setString( 7, getYakitTipi());
            arabaEkle.setInt(8, getStok());
            arabaEkle.setDouble(9, getFiyat());

            arabaEkle.executeUpdate(); // insert the entry

            return donus(); // go back to index.xhtml page
        } // end try
        finally
        {
        connection.close(); // return this connection to pool
        } // end finally
    } // end method save
    
   public String sil() throws SQLException
   {
    
        if ( dataSource == null )
        throw new SQLException( "Unable to obtain DataSource" );

   
        Connection connection = dataSource.getConnection();

   
        if ( connection == null )
        throw new SQLException( "Unable to connect to DataSource" );

        try
        {
            // create a PreparedStatement to insert a new address book entry
            PreparedStatement arabasil =
            connection.prepareStatement("DELETE FROM " + getTabloAdi() + " WHERE ID = " + getId());
            
            arabasil.executeUpdate(); // delete the entry
            
            return donus();
        } // end try
        finally
        {
        connection.close(); // return this connection to pool
        } // end finally
    } 
   
    public String donus()
    {
        if(tabloAdi.equals("Ekonomik"))
        {
            return "EkonomikAraclarTablosu.xhtml";
        }
        else if(tabloAdi.equals("EkonomikLuks"))
        {
            return "EkonomikLuksAracTablosu.xhtml";
        }
        else if(tabloAdi.equals("Luks"))
        {
            return "LuksAraclarTablosu.xhtml";
        }
        else if(tabloAdi.equals("Jeep"))
        {
            return "JeepKiralamaTablosu.xhtml";
        }
        else
        {
            return null;
        }
        
    }
    
}
