package controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public  class Action extends HttpServlet {
	@Override
	public void doGet(// get방식
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}
	@Override
	protected void doPost(// post방식
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);	}

	private void requestPro(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		String command = "";
		try {			
			Method[] md = this.getClass().getMethods();  //reflection 방법 
		//	System.out.println(Arrays.asList(md));
			command = request.getRequestURI();  //url 
			command = command.replace(".do", "");			
			System.out.println("command:" + command);
			if (command.indexOf(request.getContextPath()) == 0) {

				command = command.substring(request.getContextPath().length());
				command = command.substring(command.lastIndexOf("/") + 1); // / ?��?�� 
			}
			System.out.println("command:" + command);  //list
		
			for (int i = 0; i < md.length; i++) {
				// System.out.println("md:"+md[i].getName()+"="+command);
				if (md[i].getName().equals(command)) {
					view = (String) md[i].invoke(this, request, response);
					System.out.println("md:" + md[i].getName() + "=" + command);
				}
			}
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (view != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		} else {
			String exc = "public String " + command
					+ "(HttpServletRequest request,"
					+ "\n HttpServletResponse response)  throws Throwable"
					+ " { \n return  \" \"; \n} \n \t 를 controller 에 추가 하세요";
			System.out.println(exc);

		}
	}
}
