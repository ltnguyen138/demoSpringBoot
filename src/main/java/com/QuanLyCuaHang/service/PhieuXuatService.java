package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.PhieuXuat;

@Service
public interface PhieuXuatService {
	PhieuXuat 	getPhieuXuatByMaphieuxuat(long maphieuxuat);
	PhieuXuat addPhieuXuat();
	PhieuXuat XacNhanPhieuXuat(PhieuXuat phieuxuat);
	void DeletePhieuXuat(long maphieuxuat);
	List<PhieuXuat> getAllPhieuXuat();
	boolean IsEmptyPhieuXuat(long maphieuxuat);
	void UpdateThanhtienPX(long maphieuxuat,float thanhtien);
	
}
