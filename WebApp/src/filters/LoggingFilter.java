package filters;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter(filterName= "LOG", urlPatterns = "/*")
	public class LoggingFilter implements Filter {
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("LoggingFilter init()");
	}

    /**
     * Default constructor. 
     */
    public LoggingFilter() {
    	System.out.println("LoggingFilter()");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("destroy()");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		if( request instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest) request;
			System.out.println("SERVER TIME:" + new Date()+ " .Request URI:" + req.getRequestURI());
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}



}
