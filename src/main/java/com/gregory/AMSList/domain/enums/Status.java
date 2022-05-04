package com.gregory.AMSList.domain.enums;

public enum Status {
	
	WATCHING(0, "ROLE_WATCHING"),
	READING(1, "ROLE_READING"),
	COMPLETED(2, "ROLE_COMPLETED"),
	PLAN_TO_READ(3, "ROLE_PLANTOREAD"),
	PLAN_TO_WATCH(4, "ROLE_PLANTOWATCH"),
	DROPPED(5, "ROLE_DROPPED");
	
	private Integer code;
	private String description;
	
	private Status(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Status toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for (Status status : Status.values()) {
			
			if (code == status.getCode()) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Invalid status");
	}
	
}
