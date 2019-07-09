package com.cafe24.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.shop.repository.CommentDAO;
import com.cafe24.shop.vo.BoardVO;
import com.cafe24.shop.vo.CommentVO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO commentDao;
	
	//댓글은 페이징 처리 X
	public List<CommentVO> getList(Long boardNo) {
		return commentDao.getList(boardNo);
	}
	
	public boolean insert(CommentVO cvo) {
		return commentDao.insert(cvo);
	}
	
}
