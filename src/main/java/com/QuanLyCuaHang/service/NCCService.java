package com.QuanLyCuaHang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.QuanLyCuaHang.entity.NCC;

@Service
public interface NCCService {
	NCC 	getNCCByMancc(long mancc);
	NCC addNCC(NCC ncc);
	NCC UpdateNCC(NCC ncc);
	void DeleteNCC(long mancc);
	List<NCC> getAllNCC();
	boolean IsEmptyNCC(long mancc);
	List<NCC> searchNCC(String tenccn);
}
