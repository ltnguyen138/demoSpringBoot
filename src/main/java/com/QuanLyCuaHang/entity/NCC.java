package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ncc")
public class NCC {
	@Id
	private long mancc;
	private String tenncc;
	private String emailncc;
	private String diachincc;
	private String sdtncc;
	public NCC() {
		// TODO Auto-generated constructor stub
	}
	public long getMancc() {
		return mancc;
	}
	public void setMancc(long mancc) {
		this.mancc = mancc;
	}
	public String getTenncc() {
		return tenncc;
	}
	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}
	public String getEmailncc() {
		return emailncc;
	}
	public void setEmailncc(String emailncc) {
		this.emailncc = emailncc;
	}
	public String getDiachincc() {
		return diachincc;
	}
	public void setDiachincc(String diachincc) {
		this.diachincc = diachincc;
	}
	public String getSdtncc() {
		return sdtncc;
	}
	public void setSdtncc(String sdtncc) {
		this.sdtncc = sdtncc;
	}
	public NCC(long mancc, String tenncc, String emailncc, String diachincc, String sdtncc) {
		super();
		this.mancc = mancc;
		this.tenncc = tenncc;
		this.emailncc = emailncc;
		this.diachincc = diachincc;
		this.sdtncc = sdtncc;
	}
	@Override
	public String toString() {
		return "NCC [mancc=" + mancc + ", tenncc=" + tenncc + ", emailncc=" + emailncc + ", diachincc=" + diachincc
				+ ", sdtncc=" + sdtncc + "]";
	}
	
}
