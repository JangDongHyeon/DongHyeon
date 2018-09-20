package kr.itedu.boardmvc.service;

import java.util.ArrayList;
import kr.itedu.boardmvc.BoardVO;
import kr.itedu.boardmvc.common.BoardDAO;

public class BoardListService {

	public ArrayList<BoardVO> getBoardList(int btype){
		
		ArrayList<BoardVO> result = null;
		BoardDAO dao = BoardDAO.getInstance();
		result = dao.getBoardList(btype);
		
		return result;
	}
}
