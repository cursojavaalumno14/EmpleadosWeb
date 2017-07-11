

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFinSesion
 *
 */
@WebListener
public class EscuchaInicioYFinSesion implements HttpSessionListener {
	 
	private final static Logger log = Logger.getLogger("mylog");
    /**
     * Default constructor. 
     */
    public EscuchaInicioYFinSesion() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession sesion = arg0.getSession();
    	String id_sesion = sesion.getId();
    	log.debug("Sesion creada =  "+ id_sesion);
    	//Se suma 1 a la variable sesiones activas presente en el saquito del contexto.
    	ServletContext sc= sesion.getServletContext();
    	//Averiguamos el num de sesiones activas
    	int sesionesactivas = (int)sc.getAttribute("sesionesactivas");
    	sesionesactivas= sesionesactivas +1; //incremento
    	sc.setAttribute("sesionesactivas", sesionesactivas); //actualizamos los datos
    	log.debug("Sesiones activas = " + sesionesactivas);
    	
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	HttpSession sesion = arg0.getSession();
    	String id_sesion = sesion.getId();
    	log.debug("Sesion finalizada =  "+ id_sesion);
    	
    	//Se resta 1 a la variable sesiones activas presente en el saquito del contexto.
    	ServletContext sc= sesion.getServletContext();
    	//Averiguamos el num de sesiones activas
    	int sesionesactivas = (int)sc.getAttribute("sesionesactivas");
    	sesionesactivas= sesionesactivas-1; //restamos
    	sc.setAttribute("sesionesactivas", sesionesactivas); //actualizamos los datos
    	
    	//Actualizo el mapa de sesiones y elimino la que acaba de destruirse
    	Map<String, String> mapa_nombre_sesion = (Map<String, String>)sc.getAttribute("mapa_nombre_sesion");
    	mapa_nombre_sesion.remove(id_sesion);
    	log.debug("Sesion extraida del mapa");
    	
    	log.debug("Sesiones activas = " + sesionesactivas);
    	
    }
	
}
