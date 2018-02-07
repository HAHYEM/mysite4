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
	// getList(String keyword)�� Ű���带 �޾Ƽ� ó��
	// if������ "".equals(keyword) (Ű���尡 ������)  return boardDao.getList(); -> �޼��� ����
	// else (Ű���尡 ������) return boardDao.getSearch(String Keyword) -> �޼��� ���� ( getSearch(---)�� ���� �ȸ������ ,,,)
	public List<BoardVo> getList(String searchValue){
		
		//�˻����
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
