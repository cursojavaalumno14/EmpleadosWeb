//Version normal

import java.util.List;

public class EmpleadoServicio {
	
	public  List<Empleado> getEmpleados ()
	{
		List<Empleado> l_empleados = null;
			
			EmpleadoBD empleadoBD = new EmpleadoBD();
			l_empleados = empleadoBD.getEmpleadosBD(); //Llamamos a getEmpleado de la clase EmpleadoBD
			
			
		return l_empleados;
	}
	
	public boolean validarUsuario (String nombre, String pwd)
	{
		boolean valido = false;
		EmpleadoBD empleadoBD = null;
		
			empleadoBD = new EmpleadoBD();
			valido= empleadoBD.existeEmpleadoEnBD(nombre, pwd);
		
		return valido;
	}
	
	public Empleado obtenerInfoEmpleado(int id)
	{
		Empleado empleado= null;
		EmpleadoBD empleadoBD =null;
		
			empleadoBD = new EmpleadoBD();
			empleado = empleadoBD.obtenerEmpleadoBD(id);
		return empleado;
	}
	
}


//Version que usa BDDepartamento
/*
import java.util.List;

public class EmpleadoServicio {
	
	public  List<Empleado>  getEmpleados ()
	{
		List<Empleado> l_empleados = null;
			
			EmpleadoBDDepartamento empleadoBD = new EmpleadoBDDepartamento();
			l_empleados = empleadoBD.getEmpleadosBDDepartamento(); //Llamamos a getEmpleado de la clase EmpleadoBD
			
			
		return l_empleados;
	}
}
*/
