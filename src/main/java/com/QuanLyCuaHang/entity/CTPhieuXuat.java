package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ctphieuxuat")
public class CTPhieuXuat {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
	private long maphieuxuat;
	
	private long mahanghoa;
	private int soluong;
	private float dongia;
	private String tenhh;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	
	public String getTenhh() {
		return tenhh;
	}
	public void setTenhh(String tenhh) {
		this.tenhh = tenhh;
	}
	public long getMaphieuxuat() {
		return maphieuxuat;
	}
	public void setMaphieuxuat(long maphieuxuat) {
		this.maphieuxuat = maphieuxuat;
	}
	public long getMahanghoa() {
		return mahanghoa;
	}
	public void setMahanghoa(long mahanghoa) {
		this.mahanghoa = mahanghoa;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public float getDongia() {
		return dongia;
	}
	public void setDongia(float dongia) {
		this.dongia = dongia;
	}
	public CTPhieuXuat(long maphieuxuat, long mahanghoa, int soluong, float dongia,String tenhh) {
		super();
		this.maphieuxuat = maphieuxuat;
		this.mahanghoa = mahanghoa;
		this.soluong = soluong;
		this.dongia = dongia;
		this.tenhh=tenhh;
	}
	@Override
	public String toString() {
		return "ctphieuxuat [maphieuxuat=" + maphieuxuat + ", mahanghoa=" + mahanghoa + ", soluong=" + soluong
				+ ", dongia=" + dongia + "]";
	}
	public CTPhieuXuat() {
		// TODO Auto-generated constructor stub
	}
}
