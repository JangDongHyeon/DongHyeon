package kr.itedu.boardmvc.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.itedu.boardmvc.ActionForward;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;
import kr.itedu.boardmvc.common.Test;
import kr.itedu.boardmvc.common.Test2;
import kr.itedu.boardmvc.common.Test3;
import kr.itedu.boardmvc.common.Utils;
import kr.itedu.boardmvc.common.Var;
import kr.itedu.boardmvc.service.BoardListService;
import kr.itedu.boardmvc.service.BoardPage;


public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setPath(Var.TEMPLATE_1);
		forward.setRedirect(false);
		String con="";
		int page=0;
		if(request.getParameter("page")==null) {
			page=1;
		}else {
		page=Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("con")==null) {
			
		}else{
		
		}
		System.out.println(page);
		BoardListService service = new BoardListService();
			/*ArrayList<BoardVO> data = service.getBoardList(page);*/
		Test3 test2=new Test3();
			ArrayList<BoardVO> data=test2.list(page);
		for(BoardVO d: data) {
			System.out.println(d.getBid());
		}
			
			
			BoardPage page2=new BoardPage();
			String q=page2.page(page);
			
		request.setAttribute("data", data);
		
		request.setAttribute("page", q);
		
		return forward;
	}

}
