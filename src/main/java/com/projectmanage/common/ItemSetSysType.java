package com.projectmanage.common;

public enum ItemSetSysType {
	/**
	 * 86, "32位系统"
	 */
	X86(86, "32位系统"),
	/**
	 * 64, "64位系统"
	 */
	X64(64, "64位系统");

	private Integer value;
	private String des;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	private ItemSetSysType(Integer value, String des) {
		this.setValue(value);
		this.des = des;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	public static String toJsonStr() {
		String str = new String("{");
		for (ItemSetSysType val : ItemSetSysType.values()) {
			str += "\"" + val.getValue() + "\":\"" + val.getDes() + "\",";
		}
		str += "}";
		return str;
	}
}
