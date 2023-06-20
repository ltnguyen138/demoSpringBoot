package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.HangHoa;

@Service
public interface HangHoaService {
	HangHoa 	getHangHoaByMahh(long mahh);
	HangHoa addHangHoa(HangHoa hanghoa);
	HangHoa UpdateHangHoa(HangHoa hanghoa);
	void DeleteHangHoa(long mahh);
	List<HangHoa> getAllHangHoa();
	boolean IsEmptyHangHoa(long mahh);
	void UpdateSoluongHH(long mahh, int soluong);
	int getSoLuongHH(long mahh);
	List<HangHoa> searchHH(String search);
}
