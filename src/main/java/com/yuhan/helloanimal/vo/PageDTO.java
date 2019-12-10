package com.yuhan.helloanimal.vo;

import lombok.Getter;
import lombok.ToString;

public class PageDTO {
	
	private int startPage; //시작페이지
	private int endPage; //끝 페이지
	private boolean prev, next; //전, 후
	
	private int total; //전체갯수
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = endPage - 9;
		
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount())); //진짜 마지막 페이지번호
		if(realEnd < this.endPage)
			this.endPage = realEnd;
		
		this.prev = startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	@Override
	public String toString() {
		return "startPage:" + startPage + ", endPage:" + endPage + ", total:" + total + ", cri:" + cri;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
}


















