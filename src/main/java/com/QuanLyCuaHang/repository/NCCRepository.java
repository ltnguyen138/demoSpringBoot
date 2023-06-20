package com.QuanLyCuaHang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.QuanLyCuaHang.entity.NCC;

@Repository
public interface NCCRepository extends CrudRepository<NCC, Long>{
	Optional<NCC> findByMancc(long mancc);
	List<NCC> findByTennccContaining(String tenncc);
}
