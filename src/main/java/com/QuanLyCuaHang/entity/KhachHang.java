package com.QuanLyCuaHang.entity;

import jakarta.persistence.*;

@Entity
@Table(name="khachhang")
public class KhachHang {
	@Id
	private long makh;
	private String tenkh;
	private String emailkh;
	private String sdtkh;
	public long getMakh() {
		return makh;
	}
	public void setMakh(long makh) {
		this.makh = makh;
	}
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public String getEmailkh() {
		return emailkh;
	}
	public void setEmailkh(String emailkh) {
		this.emailkh = emailkh;
	}
	public String getSdtkh() {
		return sdtkh;
	}
	public void setSdtkh(String sdtkh) {
		this.sdtkh = sdtkh;
	}
	public KhachHang(long makh, String tenkh, String emailkh, String sdtkh) {
		super();
		this.makh = makh;
		this.tenkh = tenkh;
		this.emailkh = emailkh;
		this.sdtkh = sdtkh;
	}
	public KhachHang() {
		super();
	}
	@Override
	public String toString() {
		return "KhachHang [makh=" + makh + ", tenkh=" + tenkh + ", emailkh=" + emailkh + ", sdtkh=" + sdtkh + "]";
	}
	
}
