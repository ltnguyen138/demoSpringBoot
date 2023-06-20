package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.QuanLyCuaHang.entity.NhanVien;

@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, Integer>{
	Optional<NhanVien> findByManv(int manv);
	Optional<NhanVien> findByUsernameAndPassword(String username, String password);
	List<NhanVien> findByTennvContaining(String tennv);
}
