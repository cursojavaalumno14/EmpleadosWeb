

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.net.aso.r;

public class BaseDatos {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;

		try {
			// registro el driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			// obtengo la conexión
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.40:1521:xe", "HR", "adalid");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "adalid");
			// creo el statement
			statement = connection.createStatement();
			// ejecuto la consulta
			resultset = statement.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME FROM EMPLOYEES");

			while (resultset.next()) {
				String id = resultset.getString("EMPLOYEE_ID");
				System.out.println(id);
				String nombre = resultset.getString("FIRST_NAME");
				System.out.println(nombre);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultset.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}