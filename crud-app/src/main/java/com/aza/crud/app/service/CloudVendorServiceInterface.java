package com.aza.crud.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aza.crud.app.model.CloudVendor;


public interface CloudVendorServiceInterface {

	public CloudVendor createVendor(CloudVendor vendor) throws Exception;
	public void deleteVendor(long vendorId);
	public CloudVendor updateVendor(long vendorId,  CloudVendor vendor);
	public CloudVendor getVendor(long vendorId);
	public List<CloudVendor> getAllVendor();
}
