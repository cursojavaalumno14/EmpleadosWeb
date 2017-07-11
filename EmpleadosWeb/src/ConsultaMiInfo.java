

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConsultaMiInfo
 */
@WebServlet("/ConsultaMiInfo")
public class ConsultaMiInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaMiInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext sc =request.getServletContext(); //Invocamos el contexto para poder obtener el mapa
		Map<Integer, Empleado> m_empleados= (Map<Integer, Empleado>)sc.getAttribute("mapae");
		//Con e
		
		
		//Obtencion de la id del empleado
		HttpSession sesion = request.getSession(false); //Se hace para elegir la sesion que ya existe
		String id = (String)sesion.getAttribute("id");
		EmpleadoServicio es = new EmpleadoServicio();
		int id_empleado = Integer.parseInt(id);
		
		
		//Peticion de datos del empleado usando el mapa
		Empleado empleado = m_empleados.get(id_empleado); 
		
		//Peticion de datos del empleado a Empleado.java
		//Empleado empleado = es.obtenerInfoEmpleado(id_empleado);
		
		//Se envían los datos a MiPerfil.jsp
		request.setAttribute("empleado", empleado);
		request.getRequestDispatcher("MiPerfil.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
