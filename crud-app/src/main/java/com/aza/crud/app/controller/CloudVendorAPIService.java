package com.aza.crud.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aza.crud.app.model.CloudVendor;
import com.aza.crud.app.service.CloudVendorServiceInterface;


@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIService {
	
	private static final Logger logInfo = LoggerFactory.getLogger(CloudVendorAPIService.class);

	@Autowired
	private CloudVendorServiceInterface svc;
	
	@GetMapping("/all")
	public ResponseEntity<List<CloudVendor>> getAllVendors(){
		return new ResponseEntity<List<CloudVendor>>(svc.getAllVendor(),HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<CloudVendor> createNewVendor(@RequestBody CloudVendor vendors) throws Exception{
		CloudVendor createdCloudVendor= svc.createVendor(vendors);
		
		logInfo.info("successfully created new vendor: " + createdCloudVendor.toString());
		return new ResponseEntity<CloudVendor>(createdCloudVendor, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{vendorId}")
	public ResponseEntity<Void> deleteVendorById (@PathVariable("vendorId") Long vendorIdl){
		svc.deleteVendor(vendorIdl);
		
		logInfo.info("successfully delete one vendor: vendor ID=" + vendorIdl);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);	
	}
	
	@PutMapping("/update/{vendorId}")
	public ResponseEntity<CloudVendor> updateVendor (@PathVariable("vendorId") Long vendorIdl, @RequestBody CloudVendor uvendor){
		return new ResponseEntity<CloudVendor>(svc.updateVendor(vendorIdl, uvendor), HttpStatus.ACCEPTED);	
	}
	
	@GetMapping("/get/{vendorId}")
	public ResponseEntity<CloudVendor> getVendorById (@PathVariable("vendorId") Long vendorIdl){
		return new ResponseEntity<CloudVendor>(svc.getVendor(vendorIdl), HttpStatus.FOUND);
	}
}
