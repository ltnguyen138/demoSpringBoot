package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ctphieunhap")
public class CTPhieuNhap {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
	private long maphieunhap;
	private String tenhh;
	public String getTenhh() {
		return tenhh;	
	}

	public void setTenhh(String tenhh) {
		this.tenhh = tenhh;
	}

	private long mahanghoa;
	private int soluong;
	private float dongia;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public long getMaphieu() {
		return maphieunhap;
	}

	public void setMaphieu(long maphieu) {
		this.maphieunhap = maphieu;
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

	public CTPhieuNhap(long maphieu, long mahanghoa,String tenhh, int soluong, float dongia) {
		super();
		this.maphieunhap = maphieu;
		this.mahanghoa = mahanghoa;
		this.tenhh=tenhh;
		this.soluong = soluong;
		this.dongia = dongia;
	}

	public CTPhieuNhap() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "s"+id+"ctphieunhap [maphieu=" + maphieunhap + ", mahanghoa=" + mahanghoa + ", soluong=" + soluong + ", dongia="
				+ dongia + "]";
	}
	
}
