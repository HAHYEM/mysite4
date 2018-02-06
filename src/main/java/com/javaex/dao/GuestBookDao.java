package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;
@Repository
public class GuestBookDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getList(){
		
		return sqlSession.selectList("guestbook.getList");		//'?'�� ���� ���� �ϳ��� �޾ƿ´�
	}

	public void insert(GuestBookVo guestbookVo) {
		
		int result = sqlSession.insert("guestbook.insertContent", guestbookVo);	//?�� ���� ���� vo�� �޾ƿ��� �� ���� ���� �� ��ü�� ���ָ� �ȴ�.
		System.out.println(result + "�� ����");
	}

	public void delete(int no, String password) {
		int result = sqlSession.delete("guestbook.deleteByNo", no);
		System.out.println(result + "�� ����");
	}

	
}
