package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.NhanVien;

@Service
public interface NhanVienService {
	NhanVien 	getNhanVienByManv(int manv);
	NhanVien addNhanVien(NhanVien nhanvien);
	NhanVien UpdateNhanVien(NhanVien nhanvien);
	void DeleteNhanVien(int manv);
	List<NhanVien> getAllNhanVien();
	boolean IsEmptyNhanVien(int manv);
	NhanVien getUsernamePasswork(String username,String passwork);
	boolean CheckUsernamePasswork(String username,String passwork);
	List<NhanVien> searchNhanVien(String tennv);
}
