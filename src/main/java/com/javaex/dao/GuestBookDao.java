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

		return sqlSession.selectList("guestbook.getList"); // '?'가 없을 때는 하나만 받아온다
	}

	public void insert(GuestBookVo guestbookVo) {
		int result = sqlSession.insert("guestbook.insertContent", guestbookVo); // ?가 많을 때는 vo로 받아오고 한 개일 때는 그 객체를 써주면 된다.
		System.out.println(result + "건 삽입");
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
		System.out.println(result + "건 삭제");
	}
	
	public void apiDelete(int no) {
		int result = sqlSession.delete("guestbook.apiDeleteByNo", no);
		System.out.println(result + "건 삭제");
	}

	public List<GuestBookVo> selectGuestbookListPage(int page) {
		return sqlSession.selectList("guestbook.selectListByPage", page);
	}
}
