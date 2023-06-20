package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.QuanLyCuaHang.entity.KhachHang;

public interface KhachHangRepository extends CrudRepository<KhachHang, Long> {
	Optional<KhachHang> findByMakh(long makh);
	List<KhachHang> findByTenkhContaining(String tenkh);
	
}
