package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

public class DonViDTO {
	private Long iddv;
	private String tendv;
	private List<DonViDTO> listResult = new ArrayList<>();
	public Long getIddv() {
		return iddv;
	}
	public void setIddv(Long iddv) {
		this.iddv = iddv;
	}
	public String getTendv() {
		return tendv;
	}
	public void setTendv(String tendv) {
		this.tendv = tendv;
	}
}
