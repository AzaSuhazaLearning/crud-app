package com.aza.crud.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aza.crud.app.model.VendorAddress;
import com.aza.crud.app.repository.CloudVendorRepositoryInterface;
import com.aza.crud.app.repository.VendorAddressRepositoryInterface;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VendorAddressService implements VendorAddressServiceInterface {

	private static final Logger log= LoggerFactory.getLogger(VendorAddressService.class);
	
	@Autowired
	private VendorAddressRepositoryInterface repo;
	
	@Override
	public VendorAddress createVendorAddress(VendorAddress addr) {
		
		return repo.save(addr);
	}

}
