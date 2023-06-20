package com.QuanLyCuaHang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.QuanLyCuaHang.entity.NCC;
import com.QuanLyCuaHang.exception.NotFoundException;
import com.QuanLyCuaHang.repository.NCCRepository;
import com.QuanLyCuaHang.service.NCCService;

import jakarta.transaction.Transactional;

@Transactional
@Component
public class NCCServiceImpl implements NCCService {

	@Autowired
	NCCRepository nccrepository;
	
	@Override
	public NCC getNCCByMancc(long mancc) {
		NCC ncc=nccrepository.findByMancc(mancc).orElse(null);
		return ncc;
	}

	@Override
	public NCC addNCC(NCC ncc) {
		long mancc = ncc.getMancc();
		if(!IsEmptyNCC(mancc))
			return null;
		else {
			return nccrepository.save(ncc);
		}	
	}

	@Override
	public NCC UpdateNCC(NCC ncc) {
		long mancc = ncc.getMancc();
		if(IsEmptyNCC(mancc))
			throw new NotFoundException("Khong tim thay nha cung cap");
		else {
			return nccrepository.save(ncc);
		}
	}

	@Override
	public void DeleteNCC(long mancc) {
		NCC ncc=getNCCByMancc(mancc);
		if(ncc==null)
			throw new NotFoundException("Khong tim thay nha cung cap");
		else {
			nccrepository.delete(ncc);
		}	
	}

	@Override
	public List<NCC> getAllNCC() {
		// TODO Auto-generated method stub
		return (List<NCC>) nccrepository.findAll();
	}

	@Override
	public boolean IsEmptyNCC(long mancc) {
		NCC ncc = getNCCByMancc(mancc);
		if(ncc!=null)
			return false;
		else 
			return true;
	}

	@Override
	public List<NCC> searchNCC(String tenccn) {
		return nccrepository.findByTennccContaining(tenccn);
	}

	
}
