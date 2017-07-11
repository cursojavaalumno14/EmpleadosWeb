

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ConsultaEmpleados
 */
@WebServlet("/ConsultaEmpleados")
public class ConsultaEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
   //logger mira la configuracion de mi fichero
    private final static Logger log = Logger.getLogger("mylog"); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long tiempo_inicial = System.currentTimeMillis();
		
		log.debug("Entro en el doGet de consulta de empleados");
		EmpleadoServicio es = new EmpleadoServicio();
		List<Empleado> le = es.getEmpleados();
		
		log.debug("Lista de empleados recuperada");
		Map<Integer, Empleado> m_empleados = new HashMap<Integer, Empleado>();
		
		for (Empleado e: le)//for each
		{
			System.out.println(e.getNombre() + " " + e.getId());
			m_empleados.put(e.getId(), e);
		}
		
		log.debug("MAPA EMPLEADOS CREADO");
		Set<Integer> conjunto_claves = m_empleados.keySet(); //Todas las claves van aquí
		Iterator<Integer> it= conjunto_claves.iterator();
		int clave = -1;
		Empleado eaux = null;
		while (it.hasNext())
		{
			clave = it.next();
			eaux = m_empleados.get(clave);
			System.out.println(eaux.getNombre() + " " + eaux.getId());
		}
		
		ServletContext sc =request.getServletContext(); //'Almacenamos' el mapa en el contexto para enviarlo a ConsultaMiInfo
		sc.setAttribute("mapae", m_empleados);
		log.debug("MAPA ALMACENADO EN EL CONTEXTO");
		
		
		//le pasa los empleados al jsp para hacer la lista
		log.debug("le pasa los empleados al jsp para hacer la lista");
		request.setAttribute("lempleados", le);
		request.getRequestDispatcher("ListaEmpleados.jsp").forward(request, response);	
	    //log.error("Hubo un error al recopilar los datos");
		
	    long tiempo_final = System.currentTimeMillis();
	    long tiempo_total = tiempo_final - tiempo_inicial;
	    long segundos = tiempo_total/1000;
	    log.debug("HA TARDADO " + segundos + " s");
	    log.debug("HA TARDADO " + tiempo_total + " ms");
	    
		//response.getWriter().append("Llamo a:").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
