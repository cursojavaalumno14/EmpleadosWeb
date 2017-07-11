

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class EscuchaInicioYFinContexto
 *
 */
@WebListener
public class EscuchaInicioYFinContexto implements ServletContextListener {

	 private final static Logger log = Logger.getLogger("mylog");
    /**
     * Default constructor. 
     */
    public EscuchaInicioYFinContexto() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	//Se invoca al finalizar el contexto
    	log.debug("Contexto Finalizado");    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	//Se invoca al iniciar el contexto
    	log.debug("Contexto Iniciado");
    	
    	//Clase que coge el Context
    	ServletContext sc = arg0.getServletContext();
    	int numsesiones = 0;
    	//Al contexto le añadimos un atributo llamado sesionesactivas al que añadiremos el numero de sesiones 
    	sc.setAttribute("sesionesactivas", numsesiones);
    	
    	Map<String, String> mapa_nombre_sesion = new HashMap<String, String>();    	
    	//En el contexto metemos el mapa
    	sc.setAttribute("mapa_nombre_sesion", mapa_nombre_sesion);
    	log.debug("Mapa de sesiones vacio en el contexto");
    	
    	
   
    }
	
}
