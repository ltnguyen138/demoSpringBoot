package com.QuanLyCuaHang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.QuanLyCuaHang.entity.NhanVien;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.repository.NhanVienRepository;
import com.QuanLyCuaHang.service.NhanVienService;

@Component
public class NhanVienServiceImpl implements NhanVienService {

	@Autowired
	NhanVienRepository nhanvienrepository;
	
	@Override
	public NhanVien getNhanVienByManv(int manv) {
		NhanVien nv= nhanvienrepository.findByManv(manv).orElse(null);
		return nv;
	}

	@Override
	public NhanVien addNhanVien(NhanVien nhanvien) {
		int manv=nhanvien.getManv();
		if(!IsEmptyNhanVien(manv))
			return null;
		else {
			return nhanvienrepository.save(nhanvien);
		}
	}

	@Override
	public NhanVien UpdateNhanVien(NhanVien nhanvien) {
		int manv=nhanvien.getManv();
		if(IsEmptyNhanVien(manv))
			throw new NotFoundException("Khong tim thay nhan vien");
		else {
			return nhanvienrepository.save(nhanvien);
		}
	}

	@Override
	public void DeleteNhanVien(int manv) {
		NhanVien nv=getNhanVienByManv(manv);
		if(nv==null)
			throw new NotFoundException("Khong tim thay nhan vien");
		else {
			nhanvienrepository.delete(nv);
		}
	}

	@Override
	public List<NhanVien> getAllNhanVien() {
		return (List<NhanVien>) nhanvienrepository.findAll();
	}

	@Override
	public boolean IsEmptyNhanVien(int manv) {
		NhanVien nv = getNhanVienByManv(manv);
		if(nv!=null)
			return false;
		else
			return true;
	}

	@Override
	public NhanVien getUsernamePasswork(String username, String passwork) {
		NhanVien nhanvien = nhanvienrepository.findByUsernameAndPassword(username, passwork).orElse(null);
		return nhanvien;
	}
	@Override
	public boolean CheckUsernamePasswork(String username, String passwork) {
		NhanVien nhanvien = getUsernamePasswork(username, passwork);
		if(nhanvien==null)
			return false;
		else
			return true;
	}

	@Override
	public List<NhanVien> searchNhanVien(String tennv) {
		return nhanvienrepository.findByTennvContaining(tennv);
	}

	

	
}
