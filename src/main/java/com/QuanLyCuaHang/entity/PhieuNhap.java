package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="phieunhap")
public class PhieuNhap {
	@Id
	private long maphieunhap;
	private long mancc;
	private int manv;
	private String ngaylapphieu;
	private float thanhtien;
	private int trangthai;
	
	public int getTrangthai() {
		return trangthai;
	}


	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}


	public long getMaphieunhap() {
		return maphieunhap;
	}


	public void setMaphieunhap(long maphieunhap) {
		this.maphieunhap = maphieunhap;
	}


	public long getMancc() {
		return mancc;
	}


	public void setMancc(long mancc) {
		this.mancc = mancc;
	}


	public int getManv() {
		return manv;
	}


	public void setManv(int manv) {
		this.manv = manv;
	}


	public String getNgaylapphieu() {
		return ngaylapphieu;
	}


	public void setNgaylapphieu(String ngaylapphieu) {
		this.ngaylapphieu = ngaylapphieu;
	}


	public float getThanhtien() {
		return thanhtien;
	}


	public void setThanhtien(float thanhtien) {
		this.thanhtien = thanhtien;
	}
	

	public PhieuNhap(long maphieunhap, long mancc, int manv, String ngaylapphieu, float thanhtien,int trangthai) {
		super();
		this.maphieunhap = maphieunhap;
		this.mancc = mancc;
		this.manv = manv;
		this.ngaylapphieu = ngaylapphieu;
		this.thanhtien = thanhtien;
		this.trangthai=trangthai;
	}
	

	@Override
	public String toString() {
		return "PhieuNhap [maphieunhap=" + maphieunhap + ", mancc=" + mancc + ", mannv=" + manv + ", ngaylapphieu="
				+ ngaylapphieu + ", thanhtien=" + thanhtien + "]";
	}


	public PhieuNhap() {
		// TODO Auto-generated constructor stub
	}

}
