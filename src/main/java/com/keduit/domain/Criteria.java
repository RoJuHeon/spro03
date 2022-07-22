package com.keduit.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {//담아서 전달하기 위함
	
	private int pageNum;//페이지 개수
	private int amount;//한 페이지에 개수
	
	private String type;//T-title, C-count, TC-totalContent, W-writer, TCW, CW
	private String keyword;//사용자가 입력한 검색어
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;		
	}
	
	public String[] getTypeArr() {
		return type == null?new String[] {}:type.split("");
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.type)
				.queryParam("keyword", this.keyword);
		return builder.toUriString();
		
	}
}
