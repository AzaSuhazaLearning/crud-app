package com.aza.crud.app.controller;

import java.util.List;

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

import com.aza.crud.app.custom.exception.BusinessException;
import com.aza.crud.app.custom.exception.ControllerException;
import com.aza.crud.app.model.CloudVendor;
import com.aza.crud.app.service.CloudVendorServiceInterface;


@RestController
@RequestMapping("/cloudvendor_unused")
public class CloudVendorAPIService_customExc {

	@Autowired
	private CloudVendorServiceInterface svc;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllVendors(){
		
		try {
			return new ResponseEntity<List<CloudVendor>>(svc.getAllVendor(),HttpStatus.OK);
		} catch (BusinessException e) {
			ControllerException ce= new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.SERVICE_UNAVAILABLE);
		} catch (Exception e) {
			ControllerException ce= new ControllerException("617", "error in controller layer "+ e.getMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);			
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createNewVendor(@RequestBody CloudVendor vendors){
		
		try {
			CloudVendor createdCloudVendor= svc.createVendor(vendors);
			return new ResponseEntity<CloudVendor>(createdCloudVendor, HttpStatus.CREATED);
		} catch (BusinessException be) {
			ControllerException ce = new ControllerException(be.getErrorCode(), be.getErrorMessage());
			
			switch(be.getErrorCode()) {
				case "613":
				case "614":
					return new ResponseEntity<ControllerException>(ce, HttpStatus.UNPROCESSABLE_ENTITY);
			case "615":
					return new ResponseEntity<ControllerException>(ce, HttpStatus.NOT_ACCEPTABLE);
			case "616":
					return new ResponseEntity<ControllerException>(ce, HttpStatus.SERVICE_UNAVAILABLE);
			}
					
		} catch (Exception e) {
			ControllerException ce = new ControllerException("618",  "error in controller layer "+ e.getMessage());
			return new ResponseEntity<Exception>(ce, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
	
	@DeleteMapping("/delete/{vendorId}")
	public ResponseEntity<?> deleteVendorById (@PathVariable("vendorId") Long vendorIdl){
		
		try {
			svc.deleteVendor(vendorIdl);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);				
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			
			switch(e.getErrorCode()) {
				case "601":
				case "602":
					return new ResponseEntity<Exception>(ce, HttpStatus.UNPROCESSABLE_ENTITY);
				case "603":
					return new ResponseEntity<Exception>(ce, HttpStatus.SERVICE_UNAVAILABLE);
			}
		} catch (Exception e) {
			ControllerException ce = new ControllerException("619",  "error in controller layer "+ e.getMessage());
			return new ResponseEntity<Exception>(ce, HttpStatus.BAD_REQUEST);			
		}
		return null;

	}
	
	@PutMapping("/update/{vendorId}")
	public ResponseEntity<?> updateVendor (@PathVariable("vendorId") Long vendorIdl, @RequestBody CloudVendor uvendor){
		
		try {
			return new ResponseEntity<CloudVendor>(svc.updateVendor(vendorIdl, uvendor), HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			
			switch(e.getErrorCode()) {
				case "604":
				case "605":
				case "607":
					return new ResponseEntity<Exception>(ce, HttpStatus.UNPROCESSABLE_ENTITY);
				case "606":
				case "608":
				case "609":	
					return new ResponseEntity<Exception>(ce, HttpStatus.SERVICE_UNAVAILABLE);
			}
		} catch (Exception e) {
			ControllerException ce = new ControllerException("620",  "error in controller layer "+ e.getMessage());
			return new ResponseEntity<Exception>(ce, HttpStatus.BAD_REQUEST);					
		}
		return null;
		
	}
	
	@GetMapping("/get/{vendorId}")
	public ResponseEntity<?> getVendorById (@PathVariable("vendorId") Long vendorIdl){
		
		try {
			return new ResponseEntity<CloudVendor>(svc.getVendor(vendorIdl), HttpStatus.FOUND);
		} catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			
			switch(e.getErrorCode()) {
				case "610":
					return new ResponseEntity<Exception>(ce, HttpStatus.UNPROCESSABLE_ENTITY);
				case "611":	
					return new ResponseEntity<Exception>(ce, HttpStatus.SERVICE_UNAVAILABLE);	
			}
		} catch (Exception e) {
			ControllerException ce = new ControllerException("621",  "error in controller layer "+ e.getMessage());
			return new ResponseEntity<Exception>(ce, HttpStatus.BAD_REQUEST);				
		}
		return null;
		
	}
}
