package com.academia.appForum.model;

public enum CategoryEnum {
	PRODUCT("PR"), 
	SERVICE("SC"), 
	APP("AP"), 
	SCHEDULE("SC"), 
	DEPARTMENTS("DE"), 
	OTHER("OT");
	
	private String category;
	
	CategoryEnum(String category){
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}
}
