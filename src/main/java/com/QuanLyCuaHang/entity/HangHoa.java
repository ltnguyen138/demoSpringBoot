package com.QuanLyCuaHang.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hanghoa")
public class HangHoa {
	@Id
	private long mahh;
	private String tenhh;
	private int mancc;
	private int soluong;
	private float dongianhap;
	private float dongiaxuat;
	private String dvt;
	private String xuatxu;
	
	public long getMahh() {
		return mahh;
	}

	public void setMahh(long mahh) {
		this.mahh = mahh;
	}

	public String getTenhh() {
		return tenhh;
	}

	public void setTenhh(String tenhh) {
		this.tenhh = tenhh;
	}

	public int getMancc() {
		return mancc;
	}

	public void setMancc(int mancc) {
		this.mancc = mancc;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public float getDongianhap() {
		return dongianhap;
	}

	public void setDongianhap(float dongianhap) {
		this.dongianhap = dongianhap;
	}

	public float getDongiaxuat() {
		return dongiaxuat;
	}

	public void setDongiaxuat(float dongiaxuat) {
		this.dongiaxuat = dongiaxuat;
	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public String getXuatxu() {
		return xuatxu;
	}

	public void setXuatxu(String xuatxu) {
		this.xuatxu = xuatxu;
	}
	
	public HangHoa(long mahh, String tenhh, int mancc, int soluong, float dongianhap, float dongiaxuat, String dvt,
			String xuatxu) {
		super();
		this.mahh = mahh;
		this.tenhh = tenhh;
		this.mancc = mancc;
		this.soluong = soluong;
		this.dongianhap = dongianhap;
		this.dongiaxuat = dongiaxuat;
		this.dvt = dvt;
		this.xuatxu = xuatxu;
	}
	
	@Override
	public String toString() {
		return "hanghoa [mahh=" + mahh + ", tenhh=" + tenhh + ", mancc=" + mancc + ", soluong=" + soluong
				+ ", dongianhap=" + dongianhap + ", dongiaxuat=" + dongiaxuat + ", dvt=" + dvt + ", xuatxu=" + xuatxu
				+ "]";
	}

	public HangHoa() {
		// TODO Auto-generated constructor stub
	}
	
}
