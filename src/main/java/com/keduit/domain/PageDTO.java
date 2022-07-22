package com.keduit.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri;
	
	public PageDTO(int total, Criteria cri) {//Criteria은 객체타입
		this.total = total;
		this.cri = cri;
		
		//식을 계산하는 딱 떨이지지 않는 코드를 cell로 - 무조건 올림
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0) * 10);
		//한페이지에다가 1~10까지만 표시
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;//1페이지이면 이전페이지로 가는 버튼?은 필요없음
		this.next = this.endPage < realEnd;//마지막 페이지이면 다음으로 가는 버튼?은 필요없음
		
	}
	
	//public PageDTO(int total, Criteria cri)가 생성이 될 떄 만들기
}
