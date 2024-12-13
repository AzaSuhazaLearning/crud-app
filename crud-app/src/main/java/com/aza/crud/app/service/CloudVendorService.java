package com.aza.crud.app.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import com.aza.crud.app.custom.exception.BusinessException;
import com.aza.crud.app.custom.exception.DBException;
import com.aza.crud.app.custom.exception.EmptyInputException;
import com.aza.crud.app.custom.exception.NullRecordException;
import com.aza.crud.app.model.CloudVendor;
import com.aza.crud.app.model.VendorAddress;
import com.aza.crud.app.repository.CloudVendorRepositoryInterface;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CloudVendorService implements CloudVendorServiceInterface {
 
	private static final Logger log= LoggerFactory.getLogger(CloudVendorService.class);
	
	@Autowired
	private CloudVendorRepositoryInterface repo;

	@Autowired
	private VendorAddressServiceInterface addrService;
	
	@Override
	public void deleteVendor(long vendorId) {
		
		log.debug("vendorID: " + vendorId);
		
		if(vendorId <=0) {
			throw new EmptyInputException("601", "vendorId is not valid :deleteVendor() ");
		}
		
		try {
			repo.deleteById(vendorId);
		} catch (IllegalArgumentException e) {
			throw new NullRecordException("602", "vendor id is null :deleteVendor() " +e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603", "error in service layer " + e.getMessage());
		}	
	}

	/** 
	 * java.util.NoSuchElementException: No value present
	 * */
	@Override
	public CloudVendor updateVendor(long vendorId, CloudVendor vendor) {
		
		log.debug("vendorID: " + vendorId);
		log.debug("vendor: " + vendor.toString());
		
		CloudVendor updatedVendor = null;
		
		if(vendorId <=0 || vendor == null) {
			throw new EmptyInputException("604", "vendorId/vendor is null :updateVendor() " );
		}
		
		try {
			updatedVendor = repo.findById(vendorId).get();
			log.debug("updatedVendor: " + updatedVendor.toString());
			updatedVendor.setVendorAddress(vendor.getVendorAddress());
			updatedVendor.setVendorName(vendor.getVendorName());
			updatedVendor.setVendorPhoneNumber(vendor.getVendorPhoneNumber());			
		} catch(IllegalArgumentException e) {
			throw new NullRecordException("605", "updatedVendor is null :updateVendor() " + e.getMessage());
		} catch(Exception e) {
			throw new BusinessException("606", "error in service layer. " + e.getMessage());
		}

		
		try {
			repo.save(updatedVendor);
			return updatedVendor;			
		} catch(IllegalArgumentException e) {
			throw new NullRecordException("607", "not able to save, updatedVendor is null :updateVendor() " + e.getMessage());
		} catch(OptimisticLockingFailureException  e) {
			throw new DBException("608", "optimistic locking :updateVendor() " + e.getMessage());
		} catch(Exception e) {
			throw new BusinessException("609", "error in service layer "+ e.getMessage());
		}

	}

	@Override
	public CloudVendor getVendor(long vendorId) {
		
		log.debug("vendorID: " + vendorId);
		try {
			return repo.findById(vendorId).get();
			
		} catch(NoSuchElementException e) {
			throw new NullRecordException("610","vendor ID not exists in DB, pick another one. " + e.getMessage());
		} catch(Exception e) {
			throw new BusinessException("611","error in service layer "+ e.getMessage());
		}
	}

	@Override
	public List<CloudVendor> getAllVendor() {
		
		try {
			List<CloudVendor> vendorList = repo.findAll();
			
			if(vendorList.isEmpty()) {
				throw new EmptyInputException("612", "no records exist");
			}
			log.debug("vendorList: " + vendorList.toString());
			return vendorList;
		} catch(Exception e) {
			throw new BusinessException("612", "error in service layer "+ e.getMessage());
		}
		
	}

	@Override
	@Transactional
	public CloudVendor createVendor(CloudVendor vendor) throws Exception {
		
		log.debug("vendor: " + vendor.toString());
		if(vendor.getVendorName().isEmpty() || vendor.getVendorName().length()==0 ) {
			throw new EmptyInputException("613", "vendor name is not provided");
		}
		
		try {
			CloudVendor savedVendor = repo.save(vendor);
			
			if(savedVendor.getVendorName().contains("code")) {
				throw new Exception();
			}	
			
			VendorAddress addr = new VendorAddress();
			addr.setId(1L);
			addr.setLocation("Level 8, Wisma Standard Chartered, KL");
			addr.setVendor(vendor);
			addrService.createVendorAddress(addr);
			
			return savedVendor;
			
		} catch(IllegalArgumentException e) {
			throw new NullRecordException("614", "vendor is null " + e.getMessage());
		} catch (OptimisticLockingFailureException e) {
			throw new DBException("615", "optimistic locking violation " + e.getMessage());
		}
		catch(Exception e) {
			throw new BusinessException("616", "something is wrong in service layer " + e.getMessage());
		}
		
	}

}
