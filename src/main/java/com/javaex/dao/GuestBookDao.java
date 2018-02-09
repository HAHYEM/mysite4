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

	public List<GuestBookVo> getList() {

		return sqlSession.selectList("guestbook.getList"); // '?'�� ���� ���� �ϳ��� �޾ƿ´�
	}

	public void insert(GuestBookVo guestbookVo) {
		int result = sqlSession.insert("guestbook.insertContent", guestbookVo); // ?�� ���� ���� vo�� �޾ƿ��� �� ���� ���� �� ��ü�� ���ָ� �ȴ�.
		System.out.println(result + "�� ����");
	}
	
	public int apiInsert(GuestBookVo guestbookVo) {
		sqlSession.insert("guestbook.apiInsertByNo", guestbookVo);
	
		return guestbookVo.getNo();
	
	}
	
	public GuestBookVo apiSelectNo(int no) {
		GuestBookVo guestbookVo = sqlSession.selectOne("guestbook.selectOneByNo", no);
		return guestbookVo;
	}

	public void delete(int no, String password) {
		int result = sqlSession.delete("guestbook.deleteByNo", no);
		System.out.println(result + "�� ����");
	}
	
	public void apiDelete(int no) {
		int result = sqlSession.delete("guestbook.apiDeleteByNo", no);
		System.out.println(result + "�� ����");
	}

	public List<GuestBookVo> selectGuestbookListPage(int page) {
		return sqlSession.selectList("guestbook.selectListByPage", page);
	}
}
