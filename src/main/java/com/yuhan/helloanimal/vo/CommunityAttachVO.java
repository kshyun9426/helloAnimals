package com.yuhan.helloanimal.vo;

import lombok.ToString;

@ToString
public class CommunityAttachVO {
	
	private String uuid;
	private String uploadpath;
	private String imagename;
	private long community_no;
	
	public CommunityAttachVO() {
		
	}
	
	public CommunityAttachVO(String uuid, String uploadpath, String imagename, long community_no) {
		this.uuid = uuid;
		this.uploadpath = uploadpath;
		this.imagename = imagename;
		this.community_no = community_no;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUploadpath() {
		return uploadpath;
	}
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public long getCommunity_no() {
		return community_no;
	}
	public void setCommunity_no(long community_no) {
		this.community_no = community_no;
	}
}















