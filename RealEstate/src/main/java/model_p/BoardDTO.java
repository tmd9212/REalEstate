package model_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class BoardDTO {
	int id , cnt, seq ,level, gid;
	String title,pname,pw ,content, upfile;
	Date reg_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getContent() {
		return content;
	}
	public String getContentBr() {
		return content.replaceAll("\n", "<br/>");
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpfile() {
		if(upfile==null || upfile.equals("null")) {
			return "";
		}
		return upfile;
	}
	
	public boolean isImgFile() {
		if(upfile==null || upfile.equals("null")) {
			return false;
		}
		return Pattern.matches(".*[.](jpg|jpeg|bmp|gif|png)", upfile.toLowerCase());
	}
	
	
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	
	public String getReg_dateStr() {
		return  sdf.format(reg_date);
	}
	
	public void setReg_dateStr(String reg_date) {
		try {
			this.reg_date = sdf.parse(reg_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "BoardDTO [id=" + id + ", cnt=" + cnt + ", seq=" + seq + ", level=" + level + ", gid=" + gid + ", title="
				+ title + ", pname=" + pname + ", pw=" + pw + ", content=" + content + ", upfile=" + upfile
				+ ", reg_date=" + reg_date + ", sdf=" + sdf + "]";
	}
	
	
}
