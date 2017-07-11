

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final static Logger log = Logger.getLogger("mylog");    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//log.debug("Llamada POST recibida");
		String usuario=null;
		String pwd=null;
		
			usuario = request.getParameter("usuario");
			pwd = request.getParameter("pwd");
			
			log.debug("USUARIO Y PWD " + usuario + " " + pwd);
			
			EmpleadoServicio es = new EmpleadoServicio();
			boolean valido = es.validarUsuario(usuario,pwd);
			
			if (valido)
			{
				log.debug("USUARIO EXISTE");
				HttpSession sesion =request.getSession();
				sesion.setAttribute("nombre", usuario); //
				sesion.setAttribute("id", pwd); 
				
				//Se ha iniciado la sesion
				
				//Se actualiza el mapa del contexto introduciendo la nueva sesion y el nombre
				ServletContext sc= request.getServletContext();
				sc.getAttribute("mapa_nombre_sesion");
				Map<String, String> mapa_nombre_sesion = (Map<String, String>)sc.getAttribute("mapa_nombre_sesion");
				String clave_sesion = sesion.getId();
				mapa_nombre_sesion.put(clave_sesion, usuario);
				log.debug("Mapa de sesion actualizado");
				
				request.getRequestDispatcher("Inicio.jsp").include(request, response);
				request.getRequestDispatcher("MostrarSesiones.jsp").include(request, response);
				
				//request.getRequestDispatcher("Inicio.jsp").forward(request, response);
				
				
			}else{
				log.debug("USUARIO NO EXISTE");
				request.getRequestDispatcher("Error.jsp").forward(request, response);
				//response.sendRedirect("Error.jsp");
			}
	}

}
