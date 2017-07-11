import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class Pool {
	
	private static DataSource datasource;
	
	private static final String RUTA_XML = "java:comp/env/jdbc/pool"; //Ruta al fichero context
	
	private Pool ()
	{
		try{
			InitialContext ic = new InitialContext();
			datasource = (DataSource)ic.lookup(RUTA_XML);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	static
	{
		new Pool();
	}
	
	public static Connection getConnection()
	{
		Connection conexion = null;
		try{
			conexion = datasource.getConnection();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conexion;
	}
	
	/*
	public static void cerrarConexion(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	*/
	
	public static void liberarRecursos(Connection connection, Statement statement, ResultSet resultset)
	{
		try {
			resultset.close();
			statement.close();
			connection.close();	
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
	}
	
}
