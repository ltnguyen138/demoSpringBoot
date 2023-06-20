package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.CTPhieuXuat;

@Service
public interface CTPhieuXuatService {
	CTPhieuXuat 	getCTPhieuXuatByMapxAndMahh(long maphieuxuat, long mahh);
	CTPhieuXuat addCTPhieuXuat(CTPhieuXuat ctphieuxuat);
	CTPhieuXuat UpdateCTPhieuXuat(CTPhieuXuat ctphieuxuat);
	void DeleteCTPhieuXuat(long maphieunhap, long mahh);
	List<CTPhieuXuat> getAllCTPhieuXuat();
	List<CTPhieuXuat> getCTPhieuXuatByMpx(long maphieuxuat);
	boolean IsEmptyCTPhieuXuat(long maphieunhap, long mahh);
	void DeleteCTPhieuXuatBympx(long maphieunhap);
}
