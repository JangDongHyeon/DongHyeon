package kr.itedu.boardmvc.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.itedu.boardmvc.BoardVO;

public class Test {
		
	public ArrayList<BoardVO> list(int page) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardVO> result = new ArrayList<BoardVO>();
		int max=maxCount(page);
		int countList=10,startpage=0,endpage=0;
	/*	if(page==1) {
		startpage=max;
		endpage=startpage-countList+1;
		}else {
		startpage=max-(10*(page-1));
		endpage=startpage-countList+1;
			
		}*/
		startpage=(page-1)*10+1;
		endpage=startpage+countList-1;
		
		String sql="select bid,btitle,to_char(bregdate,'yyyy-mm-dd hh24:mi:ss') as bregdate from t_board where bid BETWEEN ? and ? order by bid desc";
		String sql1="select*from(select rownum as rnum, z.*from (select*from t_board order by bid desc) z where rownum <=?)where rnum >=?";
		
		try {
			conn=DBConnector.getConn();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, endpage);
			pstmt.setInt(2, startpage);
			rs=pstmt.executeQuery();
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
			DBConnector.close(conn, pstmt, rs);
		}
		
		
		
		
		return result;
		
		
		
		
	}
public int maxCount(int page) {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int max=0;
	String sql="select count(*) from t_board";
	try {
		conn=DBConnector.getConn();
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			max=rs.getInt(1);
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return max;
	
	
}
public String pagecon(int page) {
	String q="";
	int max,countList=10,countPage=10,total;
	max=maxCount(page);
	total=max/countList;
	if(max%countList>0) {
		total++;
	}
	if(page>total) {
		page=total;
	}
	int startpage=((page-1)/10)*10+1;
	int endpage=startpage+countPage-1;
	
	if(total<endpage) {
		endpage=total;
	}
	if(startpage>1) {
		q+="<a href=\"?page=1\">처음</a>";
	}
	if(page>1) {
		q+="<a href=\"?page"+(page-1)+"\">이전</a>";
	}
	for(int count=startpage;count<=endpage;count++) {
		if(count==page) {
			q+="<b>"+count+"</b>";
		}else {
			q+="<a href=\"?page="+count+"\">"+count+"</a>";
		}
		
	}
	if(page<total) {
		q+="<a href=\"?page="+(page+1)+"\">다음</a>";
	}
	if(endpage<total) {
		q+="<a href=\"?page="+total+"\">끝</a>";
	}
	
	
	return q;
	
	
	
	
}
	
	
	
}
