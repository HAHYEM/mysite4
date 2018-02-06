package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList(){
		
		return boardDao.getList();
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
