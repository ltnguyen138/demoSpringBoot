package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.CTPhieuNhap;
import com.QuanLyCuaHang.entity.PhieuNhap;

@Service
public interface CTPhieuNhapService {
	CTPhieuNhap 	getCTPhieuNhapByMapnAndMahh(long maphieunhap, long mahh);
	CTPhieuNhap addCTPhieuNhap(CTPhieuNhap ctphieunhap);
	CTPhieuNhap UpdateCTPhieuNhap(CTPhieuNhap ctphieunhap);
	void DeleteCTPhieuNhap(long maphieunhap, long mahh);
	List<CTPhieuNhap> getAllCTPhieuNhap();
	List<CTPhieuNhap> getCTPhieuNhapByMpn(long maphieunhap);
	boolean IsEmptyCTPhieuNhap(long maphieunhap, long mahh);
	void DeleteCTPhieuNhapBymapn(long maphieunhap);
}