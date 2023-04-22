package model_p;

import javax.servlet.http.HttpServletRequest;

public class PData {
	int limit = 3, pageLimit=4, start, nowPage;
	int startPage, endPage, totalPage;
	
	public PData(HttpServletRequest request) {
		nowPage = 1;
		if(request.getParameter("nowPage")!=null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		request.setAttribute("pd", this);
	}

	public void setTotal(int total) {
		totalPage = total / limit;
		if(total % limit != 0) {
			totalPage++;
		}
		start = (nowPage-1) * limit;
		startPage = (nowPage-1)/ pageLimit * pageLimit +1;
		endPage = startPage + pageLimit - 1;
		
		if(endPage>totalPage) {
			endPage = totalPage;
		}
	}



	public int getLimit() {
		return limit;
	}

	public int getStart() {
		return start;
	}

	public int getNowPage() {
		return nowPage;
	}
	
	

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	

}
