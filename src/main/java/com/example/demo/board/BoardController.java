package com.example.demo.board;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.support.security.auth.UserAuthentication;

import lombok.Data;

@RestController
@RequestMapping("/hyeon")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/boardlist")
	public Object getBoardData(BoardSearchCondition searchCondition, Pageable pageable, @AuthenticationPrincipal UserAuthentication user) {
		int totalCount = boardService.getBoardDataCount(searchCondition);
		return Map.of(
				"totalCount", totalCount,
				"boardlist", (totalCount == 0 ) ? Collections.emptyList() : boardService.getBoardData(searchCondition, pageable));
	}
//				
//				
//				
//				ResponseEntity
//					.ok(totalCount, boardService.getBoardData(searchCondition, pageable));
//	}
//	new ApiResponse<List<BoardDto>>(ResultCode.SUCCESS, 
	//~ Inner class ================================================================================================
		@Data
		public static class BoardSearchCondition {
			private String title;
			private String user_name;
		}
	
}
