package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="nhanvien")
public class NhanVien {
	@Id
	private int manv;
	private String tennv;
	private String sdtnv;
	private String username;
	private String password;
	private String vaitro;
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
	public int getManv() {
		return manv;
	}
	public void setManv(int manv) {
		this.manv = manv;
	}
	public String getTennv() {
		return tennv;
	}
	public void setTennv(String tennv) {
		this.tennv = tennv;
	}
	public String getSdtnv() {
		return sdtnv;
	}
	public void setSdtnv(String sdtnv) {
		this.sdtnv = sdtnv;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVaitro() {
		return vaitro;
	}
	public void setVaitro(String vaitro) {
		this.vaitro = vaitro;
	}
	public NhanVien(int manv, String tennv, String sdtnv, String username, String password, String vaitro) {
		super();
		this.manv = manv;
		this.tennv = tennv;
		this.sdtnv = sdtnv;
		this.username = username;
		this.password = password;
		this.vaitro = vaitro;
	}
	@Override
	public String toString() {
		return "NhanVien [manv=" + manv + ", tennv=" + tennv + ", sdtnv=" + sdtnv + ", username=" + username
				+ ", password=" + password + ", vaitro=" + vaitro + "]";
	}
	
}
