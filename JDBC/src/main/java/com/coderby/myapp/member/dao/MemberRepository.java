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
		String sql = "insert into member values(?,?,?,?,?,?)";
		jt.update(sql, mem.getUserId(), mem.getName(), mem.getPassword(), 
				mem.getEmail(), mem.getAddress(), 1);
	}

	@Override
	public void insertAuth(String userId) {
		String sql = "insert into authorities values(?,?)";
		jt.update(sql, userId, "ROLE_USER");
	}

	@Override
	public MemberVO getMember(String userId) {
		String sql = "select m.userid, name, password, email, address, "
				+ "enabled, authority from member m "
				+ "join authorities au "
				+ "on m.userid=au.userid "
				+ "where m.userid=?";
		return jt.query(sql, new ResultSetExtractor<MemberVO>() {
			@Override
			public MemberVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					MemberVO mem = new MemberVO();
					mem.setUserId(rs.getString("userid"));
					mem.setName(rs.getString("name"));
					mem.setPassword(rs.getString("password"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
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
			sql = "select rnum, userid, name, email, address, enabled, authority " + 
					"from" + 
					"(select rownum rnum, userid, name, email, address, enabled, authority " + 
					"from" + 
					"(select m.userid, name, email, address, enabled, authority " + 
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
					mem.setName(rs.getString("name"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				}
			}, start, end);
		}else {
			sql = "select rnum, userid, name, email, address, enabled, authority " + 
					"from" + 
					"(select rownum rnum, userid, name, email, address, enabled, authority " + 
					"from" + 
					"(select m.userid, name, email, address, enabled, authority " + 
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
					mem.setName(rs.getString("name"));
					mem.setEmail(rs.getString("email"));
					mem.setAddress(rs.getString("address"));
					mem.setEnabled(rs.getInt("enabled"));
					mem.setAuth(rs.getString("authority"));
					return mem;
				}
			}, start, end);
		}
		
	}
	
	@Override
	public void updateMember(MemberVO mem) {
		String sql = "update member set password=?, name=?, email=?, address=? "
				+ "where userid=?";
		jt.update(sql, mem.getPassword(), mem.getName(), mem.getEmail(), 
				mem.getAddress(), mem.getUserId());
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
	public void deleteAuth(String userId) {
		String sql = "delete from authorities where userid=?";
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
	
	
//	private class MemMapper implements RowMapper<MemberVO>{
//	public MemberVO mapRow(ResultSet rs, int count) throws
//	SQLException {
//		MemberVO mem = new MemberVO();
//		mem.setUserId(rs.getString("userid"));
//		mem.setName(rs.getString("name"));
//		mem.setEmail(rs.getString("email"));
//		mem.setAddress(rs.getString("address"));
//		mem.setEnabled(rs.getInt("enabled"));
//		mem.setAuth(rs.getString("authority"));
//		return mem;
//	}
//}

//	RowMapper<MemberAuthVO> authMapper = new RowMapper<MemberAuthVO>() {
//	@Override
//	public MemberAuthVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//		MemberAuthVO auth = new MemberAuthVO();
//		auth.setAuthority(rs.getString("authority"));
//		return auth;
//	}
//};

//	@Override
//	public List<MemberVO> getMemList() {
//		String sql = "select m.userid, name, email, address, "
//				+ "enabled, authority from member m "
//				+ "join authorities au "
//				+ "on m.userid=au.userid ";
//		return jt.query(sql, new MemMapper());
//	}
	
//	@Override
//	public void updateAuth(String role, String userId) {
//		String sql = "update authorities set authority=? where userid=?";
//		jt.update(sql, role, userId);
//	}
	
//	@Override
//	public List<MemberAuthVO> getAuthList() {
//		String sql = "select distinct authority from authorities";
//		return jt.query(sql, authMapper);
//	}

//	@Override
//	public void updateEnabled(String userId) {
//		String sql = "update member set enabled=(case when enabled=0 then 1 "
//				+ "when enabled=1 then 0 end) where userId=?";
//		jt.update(sql, userId);
//		
//	}
	
}
