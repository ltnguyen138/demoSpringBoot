package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.QuanLyCuaHang.entity.CTPhieuXuat;
@Transactional
@Repository
public interface CTPhieuXuatRepository extends CrudRepository<CTPhieuXuat, Long> {
	Optional<CTPhieuXuat> findByMaphieuxuatAndMahanghoa(long maphieuxuat, long mahanghoa);
	List<CTPhieuXuat> findByMaphieuxuat(long maphieuxuat);
	@Modifying
	@Query(value="delete from ctphieuxuat a where a.maphieuxuat= :maphieuxuat and a.mahanghoa=:mahanghoa", nativeQuery = true)
	void deleteCTPhieuXuat(@Param("maphieuxuat") long maphieuxuat, @Param("mahanghoa")long mahanghoa);
	void deleteByMaphieuxuat(long maphieuxuat);
}