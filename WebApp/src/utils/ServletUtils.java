package utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtils {
	
	public static void redirect(HttpServletResponse response, String path){
		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void storeReqAttr(HttpServletRequest request,String attrName, Object value){
    	request.setAttribute(attrName, value);
    }
    
    public static Object loadReqAtt(HttpServletRequest request,String attrName){
    	return request.getAttribute(attrName) ;
    }
    
    public static void storeSessionAttr(HttpServletRequest request,String attrName, Object value){
    	HttpSession session = request.getSession();
    	session.setAttribute(attrName, value);
    }
    
    public static Object loadSessionAtt(HttpServletRequest request,String attrName){
    	HttpSession session = request.getSession();
    	return session.getAttribute(attrName) ;
    }
    
}
