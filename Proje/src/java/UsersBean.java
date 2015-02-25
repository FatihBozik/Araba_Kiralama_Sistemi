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

@ManagedBean( name="usersBean" )
 
public class UsersBean
{
     
    DataSource dataSource;
    
    public UsersBean()
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

    public ResultSet getKullanicilar() throws SQLException
    {
            
            if ( dataSource == null )
                throw new SQLException( "Unable to obtain DataSource" );

           
            Connection connection = dataSource.getConnection();

            
            if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );

            try
            {

            
                PreparedStatement luksAraclariGetir = connection.prepareStatement(
                "SELECT * FROM " +
                "Users");
                 CachedRowSet rowSet = new com.sun.rowset.CachedRowSetImpl();
                 rowSet.populate( luksAraclariGetir.executeQuery() );
                 return rowSet;
            }
            finally
            {
                connection.close(); 
            } 
    } 
     
}

