package com.example.demo.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.example.demo.board.BoardController.BoardSearchCondition;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> getBoardData (@Param("sc") BoardSearchCondition searchCondition, Pageable pageable);

	int getBoardDataCount(@Param("sc") BoardSearchCondition searchCondition);
}
