
//service_p/RealEstateMain.java
package service_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control_p.Service;

public class RealEstateMain implements Service {

 
 public void execute(HttpServletRequest request, HttpServletResponse response) 
 {
     // 메인 페이지 특별한 로직을 현제 정하지 않음
	 
	 	//PData pd = (PData)request.getAttribute("pd");
		//BoardDAO dao =new BoardDAO();
		//pd.setTotal(dao.total());	//total 메소드에서 close()가 없어서 
		//ArrayList<BoardDTO> mainData = dao.list(pd);	 // dao를 list에서 닫아버림	
		//System.out.println("BoardList.execute() 실행:"+mainData);

		//request.setAttribute("mainData", mainData);
 }
}
