package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getFileList() {
		List<GalleryVo> gList = sqlSession.selectList("gallery.galleryList");
		return gList;
	}
	
	public void insert(GalleryVo vo) {
		System.out.println(vo.toString());
		sqlSession.insert("gallery.galleryInsert", vo);
		System.out.println("insert완료");
	}
	
	public void delete(int no) {
		sqlSession.delete("galleryDelete", no);
		System.out.println("delete완료");
	}
}
