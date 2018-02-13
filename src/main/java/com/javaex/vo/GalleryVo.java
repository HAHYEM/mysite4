package com.javaex.vo;

public class GalleryVo {
	int no;
	String orgName;
	String saveName;
	String exName;
	String filePath;
	long fileSize;

	public GalleryVo() {
	}

	public GalleryVo(int no, String orgName, String saveName, String exName, String filePath, long fileSize) {
		super();
		this.no = no;
		this.orgName = orgName;
		this.saveName = saveName;
		this.exName = exName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "FileUploadVo [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", exName=" + exName
				+ ", filePath=" + filePath + ", fileSize=" + fileSize + "]";
	}

}
