package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	// getList(String keyword)로 키워드를 받아서 처리
	// if문으로 "".equals(keyword) (키워드가 없으면)  return boardDao.getList(); -> 메서드 종료
	// else (키워드가 있으면) return boardDao.getSearch(String Keyword) -> 메서드 종료 ( getSearch(---)는 아직 안만들었음 ,,,)
	public List<BoardVo> getList(String searchValue){
		
		//검색기능
		if("".equals(searchValue)) {
			return boardDao.getList();
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("searchValue",searchValue);
			System.out.println("service : "+boardDao.getSearch(map));
			return boardDao.getSearch(map);
		}
	}

	public void write(BoardVo boardVo) {
		boardDao.write(boardVo);
		
	}

	public BoardVo getArticle(int no) {
		
		return boardDao.getArticle(no);
	}

	public BoardVo view(int no) {
		BoardVo boardVo = boardDao.getArticle(no);
		boardDao.view(no);
		return boardVo;
	}

	public void delete(int no) {
		boardDao.delete(no);
	}

	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
		
	}
}
