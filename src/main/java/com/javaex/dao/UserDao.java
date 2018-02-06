package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlsession;	
	
	public UserVo getUser(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		
		return sqlsession.selectOne("user.selectUserByEmailPW", map);
	}
	
	public UserVo getUser(int no) {
		UserVo userVo = sqlsession.selectOne("user.selectUserByNo", no);
		return userVo;
	}
	
	public void insert(UserVo userVo) {
		int result = sqlsession.insert("user.insertUser", userVo);
		System.out.println(result + "건 회원가입 성공");		
	}

	public void update(UserVo userVo) {
		int result = sqlsession.update("user.updateUserByEmail", userVo);
		System.out.println(result + "건 수정 성공");
	}
	
}
