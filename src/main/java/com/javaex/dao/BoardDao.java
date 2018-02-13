package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int selectTotalCount(String searchValue) {
		int count = sqlSession.selectOne("board.totalCount", searchValue);
		return count;
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> boardList = sqlSession.selectList("board.getList");
		return boardList;
	}
	
	public List<BoardVo> selectBoardList(int startRnum, int endRnum, String searchValue){
		Map<String, Object> mapCri = new HashMap<String, Object>();
		mapCri.put("startRnum", startRnum);
		mapCri.put("endRnum", endRnum);
		mapCri.put("searchValue", searchValue);
		System.out.println("dao" + mapCri.toString());
		List<BoardVo> boardList =  sqlSession.selectList("board.selectList", mapCri);
		return boardList;
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

	public List<BoardVo> getSearch(Map<String, String> map) {
		return sqlSession.selectList("board.getSearch", map);//("board.getSearch", searchValue);
	}
}
