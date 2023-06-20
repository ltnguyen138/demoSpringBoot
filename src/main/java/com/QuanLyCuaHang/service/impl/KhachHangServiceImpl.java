package com.QuanLyCuaHang.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.QuanLyCuaHang.entity.KhachHang;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.repository.KhachHangRepository;
import com.QuanLyCuaHang.service.KhachHangService;

import jakarta.transaction.Transactional;

@Transactional
@Component
public class KhachHangServiceImpl implements KhachHangService{

	@Autowired
	KhachHangRepository khachhangrepository;
	
	public KhachHang getKhachHangByMakh(long makh) {
		 
		KhachHang kh =khachhangrepository.findByMakh(makh).orElse(null);
		
		return kh;
	}
	public boolean IsEmptyKhachHang(long makh) {
		KhachHang khachhang=getKhachHangByMakh(makh);
		if(khachhang!=null)
			return false;
		else 
			return true;
	}
	
	public KhachHang addKhachHang(KhachHang khachhang) {
		long makh=khachhang.getMakh();
		if(!IsEmptyKhachHang(makh))
			return null;
		else {
			return khachhangrepository.save(khachhang);
		}
	}
	public KhachHang UpdateKhachHang(KhachHang khachhang) {
		long makh=khachhang.getMakh();
		if(IsEmptyKhachHang(makh))
			throw new NotFoundException("Khong tim thay khach hang");
		else {
			return khachhangrepository.save(khachhang);
		}
	}
	public void DeleteKhachHang(long makh) {
		KhachHang kh=getKhachHangByMakh(makh);
		if(kh==null)
			throw new NotFoundException("Khong tim thay khach hang");
		else 
			khachhangrepository.delete(kh);
	}
	public List<KhachHang> getAllKhachHang(){
		return (List<KhachHang>) khachhangrepository.findAll();
	}
	@Override
	public List<KhachHang> searchKhachHang(String tenkh) {
		return khachhangrepository.findByTenkhContaining(tenkh);
	}
}
