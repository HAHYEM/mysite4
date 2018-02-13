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
	// getList(String keyword)�� Ű���带 �޾Ƽ� ó��
	// if������ "".equals(keyword) (Ű���尡 ������)  return boardDao.getList(); -> �޼��� ����
	// else (Ű���尡 ������) return boardDao.getSearch(String Keyword) -> �޼��� ���� ( getSearch(---)�� ���� �ȸ������ ,,,)
	public Map<String, Object> getList(String searchValue, int crtPage){
				
		int listCnt = 10;
		crtPage = (crtPage <= 0) ? crtPage=1 : crtPage;
		
		int startRnum = (crtPage - 1) * listCnt;	//0, 10, 20
		int endRnum = startRnum + listCnt;			//10, 20, 30
		
		System.out.println("startRnum : " + startRnum);
		System.out.println("endRnum : " + endRnum);
		
		List<BoardVo> boardList = boardDao.selectBoardList(startRnum, endRnum, searchValue);

		//��������ư����
		
		//��ü �� ����
		int totalCount = boardDao.selectTotalCount(searchValue);
		System.out.println("totalCount : " +totalCount);
		
		//������ �� ��ư ����
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
		//�˻����
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
