package kr.itedu.boardmvc.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.itedu.boardmvc.BoardVO;

public class Test3 {

	public ArrayList<BoardVO> list(int page) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int countPage=10;
		
		int startPage=(page-1)*10+1;
		int endPage=startPage+countPage-1;
		
		ArrayList<BoardVO> result=new ArrayList<>();
		
	String sql="select*from(select rownum as rnum, z.*from (select*from t_board order by bid desc) z where rownum <=?)where rnum >=?";

	try {

		conn=DBConnector.getConn();
		ps=conn.prepareStatement(sql);
		ps.setInt(1,endPage);
		ps.setInt(2,startPage);
		rs=ps.executeQuery();
		
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
			
		} catch (Exception e) {e.printStackTrace();
			// TODO: handle exception
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return result;
}
	
	public int maxPage(int page) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int max=0;
		String sql="select count(*) from t_board";
		
		try {
			conn=DBConnector.getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				max=rs.getInt(1);
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return max;
		
		
		
	}
	
	
public String pageList(int page) {
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int max=0,countList=10,countPage=10,total=0;
	String q="";
	max=maxPage(page);
	
	total=max/countList;
	
	if(max%countList>0) {
		total++;
	}
	if(page>total) {
		page=total;
	}
	int startPage=((page-1)/10)*10+1;
	int endPage=startPage+countPage-1;
	
	
	if(endPage>total) {
		endPage=total;
	}
	
	if(startPage>1) {
		q+="<a href=\"?page="+1+"\">처음</a>";
	}
	if(page>1) {
		q+="<a href=\"?page="+(page-1)+"\">이전</a>";
		
	}
	for(int count=startPage;count<=endPage;count++) {
		if(page==count) {
			q+="<b>"+count+"</b>";
			
		}else {
			q+="<a href=\"?page="+count+"\"> "+count+" </a>";
		}
		
		
	}
	if(page<total) {
		q+="<a href=\"?page="+(page+1)+"\">다음</a>  ";
		q+="<a href=\"?page="+total+"\">끝</a>";
		
		
	}
	
	
	
	
	
	return q;
	
}
	
	
}