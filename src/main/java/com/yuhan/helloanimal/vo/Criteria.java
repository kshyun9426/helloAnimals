package com.yuhan.helloanimal.vo;

public class Criteria {
	
	private int pageNum; //페이지번호
	private int amount; //각 페이지에 보여줄 정보갯수
	
	
	public Criteria() {
		this(1,9);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "pageNum:" + pageNum + ", amount:" + amount;
	}
	
}
