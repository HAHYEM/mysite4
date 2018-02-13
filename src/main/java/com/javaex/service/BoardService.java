package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	// getList(String keyword)로 키워드를 받아서 처리
	// if문으로 "".equals(keyword) (키워드가 없으면)  return boardDao.getList(); -> 메서드 종료
	// else (키워드가 있으면) return boardDao.getSearch(String Keyword) -> 메서드 종료 ( getSearch(---)는 아직 안만들었음 ,,,)
	public Map<String, Object> getList(String searchValue, int crtPage){
				
		int listCnt = 10;
		crtPage = (crtPage <= 0) ? crtPage=1 : crtPage;
		
		int startRnum = (crtPage - 1) * listCnt;	//0, 10, 20
		int endRnum = startRnum + listCnt;			//10, 20, 30
		
		System.out.println("startRnum : " + startRnum);
		System.out.println("endRnum : " + endRnum);
		
		List<BoardVo> boardList = boardDao.selectBoardList(startRnum, endRnum, searchValue);

		//페이지버튼관련
		
		//전체 글 개수
		int totalCount = boardDao.selectTotalCount(searchValue);
		System.out.println("totalCount : " +totalCount);
		
		//페이지 당 버튼 개수
		int pageBtnCount = 5;
		
		int endPageBtnNo = (int)(Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount);
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		boolean next = false;
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		}else {
			endPageBtnNo = (int)(Math.ceil(totalCount/(double)listCnt));
		}
		
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev =true;
		}
		
		Map<String, Object> bmap = new HashMap<String, Object>();
		bmap.put("boardList",boardList);
		bmap.put("prev",prev);
		bmap.put("startPageBtnNo",startPageBtnNo);
		bmap.put("endPageBtnNo",endPageBtnNo);
		bmap.put("next",next);
		bmap.put("crtPage",crtPage);
		
		return bmap;
		
/*		
		//검색기능
		if("".equals(searchValue)) {
			return boardDao.getList();
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("searchValue",searchValue);
			System.out.println("service : "+boardDao.getSearch(map));
			return boardDao.selectBoardList(startRnum, endRnum);
		}*/
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
	
	@Transactional
	public BoardVo getBoard(int no) {
	       
	        boardDao.view(no);
	        BoardVo boardVo = boardDao.getArticle(no);

	        return boardVo;
	}
	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
		
	}
}
