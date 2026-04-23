package com.example.demo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.board.BoardController.BoardSearchCondition;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/**
	 * 게시판 조회
	 * @param searchCondition
	 * @param pageable
	 * @return
	 */
	public Object getBoardData(BoardSearchCondition searchCondition, Pageable pageable) {
		return boardMapper.getBoardData(searchCondition, pageable);
	}
	
	/**
	 * 게시판 건수 조회
	 * @param searchCondition
	 * @param pageable
	 * @return
	 */
	public int getBoardDataCount(BoardSearchCondition searchCondition) {
		return boardMapper.getBoardDataCount(searchCondition);
	}

}
