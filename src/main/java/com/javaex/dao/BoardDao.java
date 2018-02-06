package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList(){
		
		return sqlSession.selectList("board.getList");
	}

	public void write(BoardVo boardVo) {
		int result = sqlSession.insert("board.writeByTitleContent", boardVo);
		System.out.println(result + "건 글쓰기 성공");
	}

	public BoardVo getArticle(int no) {
		
		return sqlSession.selectOne("board.getArticle",no);
		
	}

	public void view(int no) {
		
		sqlSession.update("board.hitUpdate", no);
	}

	public void delete(int no) {
		sqlSession.delete("board.deleteNo", no);
	}

	public void modify(BoardVo boardVo) {
		int result = sqlSession.update("board.updateContent", boardVo);
		System.out.println(result + "건 수정 성공");
		
	}

	
	
}
