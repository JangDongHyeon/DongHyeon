package kr.itedu.boardmvc.common;

import static kr.itedu.boardmvc.common.DBConnector.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import kr.itedu.boardmvc.BoardVO;

public class BoardDAO {
	
	private static BoardDAO dao;
	
	private BoardDAO() {} //private 생성자
	
	public static BoardDAO getInstance() { //싱글톤 패턴
		
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public ArrayList<BoardVO> getBoardList(int page){
		
		ArrayList<BoardVO> result = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int[] a=max(page);
		System.out.println(a[0]);
		System.out.println(a[1]);
		
		
		try {
			con=DBConnector.getConn();
					String sql="select bid, btitle, to_char(bregdate,'yyyy-mm-dd hh24:mi:ss') as bregdate  from t_board  where bid BETWEEN ? AND ? order by bid desc";
			String sql1="select*from(select rownum as rnum, z.*from (select*from t_board order by bid desc) z where rownum <=?)where rnum >=?";
			ps = con.prepareStatement(sql1);
			ps.setInt(1, a[1]);
			ps.setInt(2, a[0]);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int bid = rs.getInt("bid");
				String btitle = rs.getString("btitle");
				String bregdate = rs.getString("bregdate");
				
				BoardVO b = new BoardVO();
				b.setBid(bid);
				b.setBtitle(btitle);
				b.setBregdate(bregdate);
				
				result.add(b);
			}
		}catch(Exception e1) {e1.printStackTrace();
			//TODO: 예외처리
		}finally {
			close(con, ps, rs);
			
		}
		return result;
	}
	public void insertBoard(BoardVO vo) {
		Connection con = null;
		
		
		PreparedStatement ps = null;
		
		
		try {
			con=getConn();
			String sql="insert into t_board values(AAA.NEXTVAL,?,?,sysdate)";
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
			
			
			
		} catch (Exception e) {e
		.printStackTrace();
			// TODO: handle exception
		}finally {
		close(ps);
		close(con);
		}
		
		
	}
	public int[] max(int page) {
		
		int max=0;
		int conutPage=10;
			int[] a=new int[2];
		int lon,lan=0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
try {
			
		
			con = getConn();
			
			String sql="select count(*) from t_board";
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				if(rs.next()) {
				max=rs.getInt(1);	
						
				}
			
				/*if(page==1) {
				lon=max;
				lan=lon-conutPage+1;
				}else {
				lon=max-(10*(page-1));
				lan=lon-conutPage+1;
					
				}*/
				lon=(page-1)*10+1;
				lan=lon+conutPage-1;
		
/*
				a[0]=lan;
				a[1]=lon;*/
				
				a[0]=lon;
				a[1]=lan;
}catch (Exception e) {e.printStackTrace();
	// TODO: handle exception
}finally {
	DBConnector.close(con, ps, rs);
}

return a;
	}
	public String in(int page) {
	
		int max=0;
		int conutPage=10;
		int countList=10;
		int totalpage=0;
		String q="";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
		
			
			
			con = getConn();
			
			String sql="select count(*) from t_board";
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				if(rs.next()) {
				max=rs.getInt(1);	
						
				}
		totalpage=max/countList;
		if(max%countList>0) {
			totalpage++;
		}
		if(totalpage<page) {
			page=totalpage;
		}
			
		int startPage=((page-1)/10)*10+1;
		int endPage=startPage+conutPage-1;
		
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		if(startPage>1) {
			q+="<a href=\"?page=1\">처음</a>";
			
		}
		if(page>1) {
			q+="<a href=\"?page="+(page-1)+"\">이전</a>";
		}
		for(int conut=startPage;conut<=endPage;conut++) {
			if(conut==page) {
				q+="<b>"+conut+"</b>";
			}else {
				q+="<a href=\"?page="+conut+"\">"+conut+"</a>";
			}
		}
		if(page<totalpage) {
			q+="<a href=\"?page="+(page+1)+"\">다음</a>";
		}
		if(endPage<totalpage) {
			q+="<a href=\"?page="+totalpage+"\">끝</a>";
		}
	
		
	}catch (Exception e) {
		// TODO: handle exception
	}finally {
	DBConnector.close(con, ps, rs);
	}
		
	return q;
		
	}
	
}
