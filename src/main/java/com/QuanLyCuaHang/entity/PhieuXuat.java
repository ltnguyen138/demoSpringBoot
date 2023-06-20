package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="phieuxuat")
public class PhieuXuat {
	@Id
	private long maphieuxuat;
	private long makh;
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


	public long getMaphieuxuat() {
		return maphieuxuat;
	}


	public void setMaphieuxuat(long maphieuxuat) {
		this.maphieuxuat = maphieuxuat;
	}


	public long getMakh() {
		return makh;
	}


	public void setMakh(long makh) {
		this.makh = makh;
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
		
	public PhieuXuat(long maphieuxuat, long makh, int manv, String ngaylapphieu, float thanhtien,int trangthai) {
		super();
		this.maphieuxuat = maphieuxuat;
		this.makh = makh;
		this.manv = manv;
		this.ngaylapphieu = ngaylapphieu;
		this.thanhtien = thanhtien;
		this.trangthai=trangthai;
	}

	public PhieuXuat() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "phieuxuat [maphieuxuat=" + maphieuxuat + ", makh=" + makh + ", manv=" + manv + ", ngaylapphieu="
				+ ngaylapphieu + ", thanhtien=" + thanhtien + "]";
	}
	
}
