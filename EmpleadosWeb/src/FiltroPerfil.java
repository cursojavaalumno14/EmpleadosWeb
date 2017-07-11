

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroPerfil
 */
@WebFilter("/ConsultaMiInfo")
public class FiltroPerfil implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroPerfil() {
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

		// pass the request along the filter chain
		
		//Compruebo si la peticion tiene asociada una sesión y si es así le dejo pasar al servicio. Si no, le redirijo al login.
		HttpServletRequest hr = (HttpServletRequest)request;
		HttpSession sesion = hr.getSession(false);
		
		if (sesion ==null)
		{
			//no tiene sesion
			//redirigimos al login
			HttpServletResponse hresp = (HttpServletResponse)response;
			System.out.println("SIN SESION VALIDA");
			hresp.sendRedirect("SinSesion.html");
			
			
		}else
		{
			//tiene sesion, le dejo pasar
			chain.doFilter(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
