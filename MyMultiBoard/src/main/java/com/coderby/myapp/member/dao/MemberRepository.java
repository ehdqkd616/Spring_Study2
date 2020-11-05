package com.coderby.myapp.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import com.coderby.myapp.member.model.MemberVO;

@Controller
public class MemberRepository implements IMemberRepository{

	@Autowired
	MyJdbcTemplate jt;
	
	@Override
	public void insertMember(MemberVO mem) {
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
		jt.update(sql, mem.getUserId(), mem.getPassword(), mem.getUserName(), 
				mem.getEmail(), mem.getPhone(), mem.getNickname(), 1, 0, null, "SYSDATE", 1);
	}

	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, password, username, email, phone_number, "
				+ "nickname, enabled, report_count, profile_pic, authority from member m "
				+ "join authorities au "
				+ "on m.userid=au.userid "
				+ "where m.userid=?";
		return jt.query(sql, new ResultSetExtractor<MemberVO>() {
			@Override
			public MemberVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setPassword(rs.getString("password"));
					mem.setUserName(rs.getString("username"));
					mem.setEmail(rs.getString("email"));
					mem.setPhone(rs.getString("phone_number"));
					mem.setNickname(rs.getString("nickname"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setReportCount(rs.getInt("report_count"));
					mem.setProfilePic(rs.getBytes("profile_pic"));
					mem.setAuthority(rs.getInt("authority"));
					mem.setAuthorityName(rs.getString("authority_name"));
					return mem;
				}else {
					return null;
				}
			}
		},userId);
	}

	@Override
	public String getPassword(String userId) {
		String sql = "select password from member where userid=?";
		return jt.queryForNullableObject(sql, String.class, userId);
	}

	@Override
	public List<MemberVO> getMemberList(int page, String word) {
		String sql = null;
		int start = (page-1)*10+1;
		int end = start+9;
		if(word==null) {
			sql = "select rnum, userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority " + 
					"from" + 
					"(select rownum rnum, userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority " + 
					"from" + 
					"(select m.userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority " + 
					"from member m " + 
					"join authorities a " + 
					"on m.userid=a.userid " + 
					"order by m.userid)) " + 
					"where rnum between ? and ?";
			return jt.query(sql, new RowMapper<MemberVO>() {
				@Override
				public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setUserName(rs.getString("username"));
					mem.setEmail(rs.getString("email"));
					mem.setPhone(rs.getString("phone_number"));
					mem.setNickname(rs.getString("nickname"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setReportCount(rs.getInt("report_count"));
					mem.setProfilePic(rs.getBytes("profile_pic"));
					mem.setJoinDate(rs.getDate("join_date"));
					mem.setAuthority(rs.getInt("authority"));
					mem.setAuthorityName(rs.getString("authority_name"));
					return mem;
				}
			}, start, end);
		}else {
			sql = "select rnum, userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority " + 
					"from" + 
					"(select rownum rnum, userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority " + 
					"from" + 
					"(select m.userid, username, email, phone_number, nickname, enabled, report_count, profile_pic, join_date, authority " + 
					"from member m " + 
					"join authorities a " + 
					"on m.userid=a.userid " + 
					"order by m.userid)) " + 
					"where rnum between ? and ? and userid like '%"+word+"%' or name like '%"+word+"%'";
			return jt.query(sql, new RowMapper<MemberVO>() {
				@Override
				public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setUserName(rs.getString("username"));
					mem.setEmail(rs.getString("email"));
					mem.setPhone(rs.getString("phone_number"));
					mem.setNickname(rs.getString("nickname"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setReportCount(rs.getInt("report_count"));
					mem.setProfilePic(rs.getBytes("profile_pic"));
					mem.setJoinDate(rs.getDate("join_date"));
					mem.setAuthority(rs.getInt("authority"));
					mem.setAuthorityName(rs.getString("authority_name"));
					return mem;
				}
			}, start, end);
		}
		
	}
	
	@Override
	public void updateMember(MemberVO mem) {
		String sql = "update member set password=?, username=?, email=?, phone_number=?, nickname=? "
				+ "where userid=?";
		jt.update(sql, mem.getPassword(), mem.getUserName(), mem.getEmail(), 
				mem.getPhone(), mem.getNickname(), mem.getUserId());
	}
	
	@Override
	public void updateMemberEnable(String userId) {
		String sql = "update member set enabled=case when enabled=1 then 0 " + 
				"when enabled=0 then 1 end " + 
				"where userid=?";
		jt.update(sql,userId);
	}
	
	@Override
	public void deleteMember(String userId) {
		String sql = "delete from member where userid=?";
		jt.update(sql, userId);
	}

	
	@Override
	public void updateMemberAuth(String auth, String userId) {
		String sql = "update authorities set authority=? where userid=?";
		jt.update(sql,auth,userId);
	}
	
	@Override
	public Integer getMemberCount(String word) {
		String sql = null;
		if(word==null) {
			sql = "select count(*) from member";
			return jt.queryForNullableObject(sql, Integer.class);
		}else {
			sql = "select count(*) from member where userid like '%"+word+"%' or name like '%"+word+"%'";
			return jt.queryForNullableObject(sql, Integer.class);
		}
	}

	@Override
	public boolean checkId(String userId) {
		String sql = "select count(*) from member where userid=?";
		Integer a = jt.queryForNullableObject(sql, Integer.class, userId);
		return a==0 ? true : false;
	}

	@Override
	public boolean checkEmail(String email) {
		String sql = "select count(*) from member where email=?";
		Integer a = jt.queryForNullableObject(sql, Integer.class, email);
		return a==0 ? true : false;
	}

	@Override
	public boolean checkPhone(String phone) {
		String sql = "select count(*) from member where phone_number=?";
		Integer a = jt.queryForNullableObject(sql, Integer.class, phone);
		return a==0 ? true : false;
	}

	@Override
	public boolean checkNickname(String nickname) {
		String sql = "select count(*) from member where nickname=?";
		Integer a = jt.queryForNullableObject(sql, Integer.class, nickname);
		return a==0 ? true : false;
	}
	
	
	
}
