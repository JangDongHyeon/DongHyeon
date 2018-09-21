package kr.itedu.boardmvc.service;

import kr.itedu.boardmvc.common.BoardDAO;
import kr.itedu.boardmvc.common.Test;
import kr.itedu.boardmvc.common.Test2;
import kr.itedu.boardmvc.common.Test3;

public class BoardPage {
	public String page(int page) {
		BoardDAO dao = BoardDAO.getInstance();
		Test3 test=new Test3();
		String q=test.pageList(page);
		return q;
	
	}
	
	
	
}
