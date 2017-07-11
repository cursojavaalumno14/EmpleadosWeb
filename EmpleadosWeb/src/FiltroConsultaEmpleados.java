
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FiltroConsultaEmpleados
 */
@WebFilter("/ConsultaEmpleados")
public class FiltroConsultaEmpleados implements Filter {
	long tiempo_acumulado=0;
	//logger mira la configuracion de mi fichero
    private final static Logger log = Logger.getLogger("mylog"); 
    /**
     * Default constructor. 
     */
    public FiltroConsultaEmpleados() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		ServletContext sc = request.getServletContext();
		//Esto convierte peticiones en un integer
		Integer peticiones = (Integer)sc.getAttribute("peticiones");
		
		
		if (peticiones == null)
		{
			log.debug("No existen peticiones");
			sc.setAttribute("peticiones", 0);
		} else
		{
			peticiones = peticiones + 1;
			sc.setAttribute("peticiones", peticiones);
			log.debug("Peticiones = " + peticiones);
		}
		
		
		// pass the request along the filter chain
		//MENSAJE DE LOG INICIAL
		long tiempo_inicial = System.currentTimeMillis();
		log.debug("Filtro al iniciar. Han pasado " + tiempo_inicial + " ms");
		
		 
		chain.doFilter(request, response);
		
		//MENSAJE DE LOG FINAL
		long tiempo_final = System.currentTimeMillis();
		log.debug("Filtro al acabar. Han pasado " + tiempo_final + " ms");
		long tiempo_servicio = tiempo_final - tiempo_inicial;
		System.out.println("La aplicacion ha durado " + tiempo_servicio + " ms");
		
		if (peticiones == null)
		{
			tiempo_acumulado=tiempo_servicio;
			log.debug("El tiempo medio es " + tiempo_acumulado + " ms");
		} else
		{
			tiempo_acumulado=tiempo_acumulado+tiempo_servicio;
			long tiempo_medio = tiempo_acumulado / peticiones;
			log.debug("El tiempo medio es " + tiempo_medio + " ms");
		}
		
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
