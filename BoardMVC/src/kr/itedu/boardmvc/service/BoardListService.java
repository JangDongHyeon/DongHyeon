package kr.itedu.boardmvc.service;

import java.util.ArrayList;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;
import kr.itedu.boardmvc.common.Test2;

public class BoardListService {

	public ArrayList<BoardVO> getBoardList(int page){
		
		ArrayList<BoardVO> result = null;
		
	/*	BoardDAO dao = BoardDAO.getInstance();
		
		
		result = dao.getBoardList(page);*/
	
		Test2 test2=new Test2();
		result=test2.list(page);
		
		return result;
	}
}
