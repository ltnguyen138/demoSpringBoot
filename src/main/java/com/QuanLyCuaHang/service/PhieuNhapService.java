package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.PhieuNhap;



@Service
public interface PhieuNhapService {
	PhieuNhap 	getPhieuNhapByMaphieunhap(long maphieunhap);
	PhieuNhap addPhieuNhap();
	PhieuNhap XacNhanPhieuNhap(PhieuNhap phieunhap);
	PhieuNhap UpdatePhieuNhap(PhieuNhap phieunhap);
	void DeletePhieuNhap(long maphieunhap);
	List<PhieuNhap> getAllPhieuNhap();
	
	boolean IsEmptyPhieuNhap(long maphieunhap);
	void UpdateThanhtienPN(long maphieunhap,float thanhtien);
}
