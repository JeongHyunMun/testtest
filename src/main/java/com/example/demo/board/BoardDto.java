package com.example.demo.board;

import lombok.Data;

@Data
public class BoardDto {
	private String board_no;
	private String user_no;
	private String user_name;
	private String title;
	private String content;
	private String category;
	private String view_count;
	private String created_date;
}
