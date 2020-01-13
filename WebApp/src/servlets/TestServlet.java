package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("3.TestServlet init()");
	}

	static {
		System.out.println("1.TestServlet loaded");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		System.out.println("2.TestServlet instance created");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("4. service method to delegate");
		// TODO Auto-generated method stub
		super.service(req, res);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // TODO Auto-generated method stub
	        response.setContentType("text/html;charset=UTF-8");
	        
	        String num1 = request.getParameter("NUM1");
	        String num2 = request.getParameter("NUM2");
	        
	        String show = request.getParameter("SHOW");
	        // 1. be NULL if it's not exists (User not send this param)
	        // 2. Can be AbraCadabra
	        // 3. Can be Yes/No/ true/ false/ TRUE/ FALSE
	        
	        String part = "<div class='space' id='extra'>" + 
                    "THE CONTENT IS DISABLED! PLEASE SET 'SHOW' PARAMETHER LIKE 'Yes / True ' " + 
                  "</div>";
	        
	        PrintWriter writer = response.getWriter();
	        writer.append("<!DOCTYPE html>");
	        writer.append("<html lang=\"en\">");
	        writer.append("<head>\r\n" + 
	                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
	                "    <title>Поля</title>\r\n" + 
	                "    <style type=\"text/css\">\r\n" + 
	                "        .space {\r\n" + 
	                "            padding: 20px; /* Поля */\r\n" + 
	                "            background: #E5D3BD; /* Цвет фона */\r\n" + 
	                "            border: 2px solid #E81E25; /* Параметры рамки */\r\n" + 
	                "        }\r\n" + 
	                "        #extra{\r\n" + 
	                "            background: gray;\r\n" + 
	                "        }\r\n" + 
	                "        P.line {\r\n" + 
	                "            border-left: 1px dotted red;\r\n" + 
	                "            padding: 10px;\r\n" + 
	                "        }\r\n" + 
	                "        .layer1, .layer2 {\r\n" + 
	                "            background: #F2EFE6;\r\n" + 
	                "            border: 1px solid #B25538;\r\n" + 
	                "            padding: 10px;\r\n" + 
	                "            margin: 20px;\r\n" + 
	                "        }\r\n" + 
	                "        div {\r\n" + 
	                "            width: 100%; /* Ширина */\r\n" + 
	                "            background: grey; /* Цвет фона */\r\n" + 
	                "            padding: 20px; /* Поля */\r\n" + 
	                "            -moz-box-sizing: border-box; /* Для Firefox */\r\n" + 
	                "            -webkit-box-sizing: border-box; /* Для Safari и Chrome */\r\n" + 
	                "            box-sizing: border-box; /* Для IE и Opera */\r\n" + 
	                "        }\r\n" + 
	                "        .wrap {\r\n" + 
	                "            width: 50%; /* Ширина */\r\n" + 
	                "        }\r\n" + 
	                "        .wrap div {\r\n" + 
	                "            background: #fc0;\r\n" + 
	                "            margin: 10px;\r\n" + 
	                "            padding: 20px;\r\n" + 
	                "            border: 1px solid #000;\r\n" + 
	                "        }\r\n" + 
	                "    </style>\r\n" + 
	                "</head>\r\n");
	                
	        //BODY
	        writer.append("<body>"); 
	        //CONTENT
	        boolean notShow = (show == null) || (show.toLowerCase().trim().equals("no")) || (show.toLowerCase().trim().equals("false"));
	        writer.append(
	        		notShow? part:
	        		"<div class=\"space\">\r\n" + 
	                "    Они шли, чтобы покорить нас, чтобы пытать нас и сжигать живьем на кострах,\r\n" + 
	                "    они шли, чтобы сделать с нами, вольными англичанами, то же самое, что\r\n" + 
	                "    Кортес сделал с индейцами Анауака.\r\n" + 
	                "</div>\r\n" + 
	                "<div class=\"space\" id=\"extra\">\r\n" + 
	                "    some txt.\r\n" + 
	                "</div>\r\n" + 
	                "<p class=\"line\">Лев ревет только в том случае, когда сообщает, что\r\n" + 
	                "    территория принадлежит ему или провозглашает себя царем природы.</p>\r\n" + 
	                "<div class=\"layer1\">Лев ревет только в том случае, когда сообщает, что\r\n" + 
	                "    территория принадлежит ему или провозглашает себя царем природы.</div>\r\n" + 
	                "<div class=\"layer2\">Охотничий участок льва может иметь длину и ширину\r\n" + 
	                "    до тридцати километров.</div>\r\n" + 
	                
	                "<div class=\"layer2\">PARAMS FROM CLIENT: A = "+num1+" , B = "+num2+".</div>\r\n" +
	                
	                "<div>Ширина блока 100%</div>\r\n" + 
	                "<div class=\"wrap\">\r\n" + 
	                "    <div>Ширина слоя 100%</div>\r\n" + 
	                "</div>");
	                
	        writer.append("</body>");
	        writer.append("</html>");
	        
	        
	        System.out.println("doGet() -> " + Thread.currentThread().getId());
	    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("5. Destroy Servlet");
	}

}
