package filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import impl.RoleDAOImpl;
import model.Role;
import model.User;
import utils.AppConstants;
import utils.ServletUtils;
import utils.UserRolesMappingUtil;
import static utils.ServletUtils.*;
import static utils.AppConstants.*;
import static utils.UserRolesMappingUtil.*;

/**
 * Servlet Filter implementation class CheckAuthFilter
 */
@WebFilter(filterName = "CHECK", urlPatterns = "/*")
public class CheckAuthFilter implements Filter {
	private Map<Role, List<String>> roleURLMap = null;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		roleURLMap = UserRolesMappingUtil.getRoleURLMap();
	}

	/**
	 * Default constructor.
	 */
	public CheckAuthFilter() {
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
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String path = req.getServletPath();// "/admin
        String uri = req.getRequestURI(); // "/admin, /editUser"
        

        //allow to run CSS & JS files
        if (uri.endsWith(".css") || uri.endsWith(".js")||uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }
        // Wrong URL checking
        if (!isContains(path, roleURLMap)) {
            storeReqAttr(req, ERROR_MSG, "BAD URL");
            storeReqAttr(req, ERROR_DETAIL_MSG, "DETAIL TXT");
            forward(req, resp, "error.jsp");
            return;
        }
  
        //is user logged? 
        User loggedUser = (User)ServletUtils.loadSessionAtt(req, AppConstants.LOGGED_USER);
        if(loggedUser != null) {
        	if(!loggedUser.getIsActive()){
        		req.getSession().invalidate();
                // if user deactivated
                storeReqAttr(req, ERROR_MSG, "NOT AUTHORIZED");
                storeReqAttr(req, ERROR_DETAIL_MSG, "PLEASE CONTACT YOUR ADMIN");
                forward(req, resp, "error.jsp");
                return;
        	}
            Role loggedUserRole = loggedUser.getRole();
            
            ArrayList<String> paths = new ArrayList<String>(roleURLMap.get(loggedUserRole));
            if(paths.contains(path) || loggedUserRole.getName().equals("SU")) {
                // pass the request along the filter chain
                chain.doFilter(request, response);
                return;
            } else {
                    // if role not suitable
                    storeReqAttr(req, ERROR_MSG, "NOT AUTHORIZED");
                    storeReqAttr(req, ERROR_DETAIL_MSG, "DETAIL TXT");
                    forward(req, resp, "error.jsp");
                    return;
            }
            
        } else {
            Role defRole = new RoleDAOImpl().getRoleByName(new Role("NO_PERMISSIONS"));
            ArrayList<String> paths = new ArrayList<String>(roleURLMap.get(defRole));
            if(paths.contains(path)) {
                // pass the request along the filter chain
                chain.doFilter(request, response);
                return;
            } else {
                forward(req, resp, "login");
                return;
            }
        }
	}}

