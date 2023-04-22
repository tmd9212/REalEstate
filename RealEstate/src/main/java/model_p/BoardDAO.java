package model_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/zxcv");
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int total(){
		int res = 0;
		sql = "select count(*) from board";
		
		try {
			psmt = con.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			rs.next();
			res = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	public ArrayList<BoardDTO> list(PData pd){
		ArrayList<BoardDTO> res = new ArrayList<>();
		sql = "select * from board order by gid desc, seq limit ? , ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pd.getStart());
			psmt.setInt(2, pd.getLimit());
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLevel(rs.getInt("level"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setTitle(rs.getString("title"));
				dto.setPname(rs.getString("pname"));
//				dto.setPw(rs.getString("pw"));
//				dto.setContent(rs.getString("content"));
//				dto.setUpfile(rs.getString("upfile"));
				dto.setReg_dateStr(rs.getString("reg_date"));
				
				res.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	public void addCount(int id){
		BoardDTO dto = null;
		sql = "update board set cnt = cnt + 1 where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BoardDTO detail(int id){
		BoardDTO dto = null;
		sql = "select * from board where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLevel(rs.getInt("level"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setTitle(rs.getString("title"));
				dto.setPname(rs.getString("pname"));
				dto.setPw(rs.getString("pw"));
				dto.setContent(rs.getString("content"));
				dto.setUpfile(rs.getString("upfile"));
				dto.setReg_dateStr(rs.getString("reg_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return dto;
	}
	
	
	
	public int insert(BoardDTO dto ){
		int res = 0;
		sql = "insert into board (title, pname, pw, content, upfile, seq, level, cnt, reg_date ) values "
				+ " (?,?,?,?,?,0,0,-1,sysdate())";
		
		try {
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getPname());
			psmt.setString(3, dto.getPw());
			psmt.setString(4, dto.getContent());
			psmt.setString(5, dto.getUpfile());
			
			res = psmt.executeUpdate();	
			
			sql = "select max(id) from board";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			dto.setId(rs.getInt(1));
			
			sql = "update board set gid = ? where id = ?";
			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, dto.getId());
			psmt.setInt(2, dto.getId());
			
			psmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return res;
	}
	
	
	
	public int delete(BoardDTO dto ){
		int res = 0;
		sql = "delete from board where id = ? and  pw = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, dto.getId());
			psmt.setString(2, dto.getPw());
			
			res = psmt.executeUpdate();	
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return res;
	}
	
	
	public BoardDTO idPwChk(BoardDTO dto ){
		BoardDTO res = null;
		sql = "select * from board where id = ? and  pw = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, dto.getId());
			psmt.setString(2, dto.getPw());
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				res = new BoardDTO();
				res.setId(rs.getInt("id"));
				res.setGid(rs.getInt("gid"));
				res.setSeq(rs.getInt("seq"));
				res.setLevel(rs.getInt("level"));
				res.setCnt(rs.getInt("cnt"));
				res.setTitle(rs.getString("title"));
				res.setPname(rs.getString("pname"));
				res.setPw(rs.getString("pw"));
				res.setContent(rs.getString("content"));
				res.setUpfile(rs.getString("upfile"));
				res.setReg_dateStr(rs.getString("reg_date"));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return res;
	}
	
	
	public int modify(BoardDTO dto ){
		int res = 0;
		sql = "update board set title = ?, pname = ?, content = ?, upfile = ? where id = ? and  pw = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getPname());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getUpfile());
			psmt.setInt(5, dto.getId());
			psmt.setString(6, dto.getPw());
			
			res = psmt.executeUpdate();	
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return res;
	}
	
	
	public void fileDelete(BoardDTO dto ){
		
		sql = "update board set  upfile = null where id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, dto.getId());
			psmt.executeUpdate();	
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}	
	}
	
	
	
	
	public int reply(BoardDTO dto ){
		int res = 0;
		
		try {
			sql = "update board set seq = seq + 1 where gid = ? and seq > ?";
			psmt = con.prepareStatement(sql);
			
			psmt.setInt(1, dto.getGid());
			psmt.setInt(2, dto.getSeq());
			
			psmt.executeUpdate();	
			
			sql = "insert into board (title, pname, pw, content,gid, seq, level, cnt, reg_date ) values "
					+ " (?,?,?,?,?,?,?,-1,sysdate())";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getPname());
			psmt.setString(3, dto.getPw());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getGid());
			psmt.setInt(6, dto.getSeq()+1);
			psmt.setInt(7, dto.getLevel()+1);
			res = psmt.executeUpdate();
			
			sql = "select max(id) from board";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			dto.setId(rs.getInt(1));

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}	
		return res;
	}
	
	public void close() {
		if(rs!=null)try { rs.close();} catch (Exception e) {}
		if(psmt!=null)try { psmt.close();} catch (Exception e) {}
		if(con!=null)try { con.close();} catch (Exception e) {}
	}
	
	
}
