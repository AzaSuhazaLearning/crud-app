package com.aza.crud.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aza.crud.app.model.CloudVendor;

@Repository
public interface CloudVendorRepositoryInterface extends JpaRepository<CloudVendor, Long>{
	List<CloudVendor> findByVendorName(String vendorName);
}
