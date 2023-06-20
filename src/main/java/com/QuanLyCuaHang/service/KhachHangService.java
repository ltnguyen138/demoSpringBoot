package com.QuanLyCuaHang.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import com.QuanLyCuaHang.entity.KhachHang;
import com.QuanLyCuaHang.repository.KhachHangRepository;
import jakarta.validation.Valid;

@Service
public interface KhachHangService {

	KhachHang 	getKhachHangByMakh(long makh);
	KhachHang addKhachHang(KhachHang khachhang);
	KhachHang UpdateKhachHang(KhachHang khachhang);
	void DeleteKhachHang(long makh);
	List<KhachHang> getAllKhachHang();
	boolean IsEmptyKhachHang(long makh);
	List<KhachHang> searchKhachHang(String tenkh);
}
