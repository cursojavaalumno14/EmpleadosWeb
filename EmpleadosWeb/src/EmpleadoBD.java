import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Esta es una clase que interactua con las otras, recoge datos de la base de datos

public class EmpleadoBD {
	/*
	//La sección static se usa para inicializar elementos de la clase que se usan sólo una vez
	static{
		// registro el driver (solo se hace una vez)
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public Empleado obtenerEmpleadoBD(int id)
	{
		Empleado empleado = null;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
		try{
			conexion = Pool.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL FROM EMPLOYEES WHERE EMPLOYEE_ID=" + id);
			rs.next();
			int nempleado=rs.getInt("EMPLOYEE_ID");
			String fname=rs.getString("FIRST_NAME");
			String lname=rs.getString("LAST_NAME");
			String email=rs.getString("EMAIL");
			empleado = new Empleado(nempleado, fname, lname, email);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return empleado;
	}
	
	public boolean existeEmpleadoEnBD(String nombre, String pwd)
	{
		boolean existe = false;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
			try{
				int ipwd = Integer.parseInt(pwd);
				conexion = Pool.getConnection();
				st = conexion.createStatement();
				rs= st.executeQuery("SELECT EMPLOYEE_ID FROM EMPLOYEES WHERE FIRST_NAME = '" + nombre + "' AND EMPLOYEE_ID = " + ipwd);
				existe = rs.next();
			}catch(Exception e){
				//TODO usar el log
				e.printStackTrace();
			}finally{
				Pool.liberarRecursos(conexion, st, rs);
			}
		
		return existe;
	}
	
	public  List<Empleado> getEmpleadosBD ()
	{
		List<Empleado> l_empleados = null;
		
			//Esto es casi identico al main de BaseDatos

			Connection connection = null;
			Statement statement = null;
			ResultSet resultset = null;
			
			try {
				
				// obtengo la conexión (esto se hace con cada petición)
				connection = Pool.getConnection(); //DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "adalid");
				// creo el statement
				statement = connection.createStatement();
				// ejecuto la consulta, resultset devuelve los registros de la base de datos
				resultset = statement.executeQuery("SELECT FIRST_NAME, EMPLOYEE_ID FROM EMPLOYEES ORDER BY EMPLOYEE_ID ASC");
				
				
				//Se crean empleados consultando de la base de datos
				l_empleados = new ArrayList<Empleado>();
				Empleado i_auxiliar;
				Empleado e_auxiliar= null;
				while (resultset.next()) {
					String id = resultset.getString("EMPLOYEE_ID");
					i_auxiliar = new Empleado(id);
					l_empleados.add(i_auxiliar);
					String nombre = resultset.getString("FIRST_NAME");
					e_auxiliar = new Empleado(nombre);
					l_empleados.add(e_auxiliar);
					//System.out.println(nombre);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					//Se va cerrando todo
					//resultset.close();
					//statement.close();
					Pool.liberarRecursos(connection, statement, resultset);
					//connection.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		return l_empleados;
	}
}
