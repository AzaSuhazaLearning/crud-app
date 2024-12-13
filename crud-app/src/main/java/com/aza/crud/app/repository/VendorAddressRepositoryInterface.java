package com.aza.crud.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aza.crud.app.model.VendorAddress;

@Repository
public interface VendorAddressRepositoryInterface extends JpaRepository<VendorAddress, Long> {

}
