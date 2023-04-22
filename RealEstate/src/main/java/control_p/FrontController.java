package control_p;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model_p.PData;

@WebServlet("/realestate/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//new PData(request);
		
		String serviceStr = request.getRequestURI().substring(
				                (request.getContextPath()+"/realestate/").length());

		request.setAttribute("mainUrl", "main/"+serviceStr+".jsp");

		try {
			Service service = (Service)Class.forName("service_p."+serviceStr).newInstance();
			service.execute(request, response);

			if(!serviceStr.equals("FileDown")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/template.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
