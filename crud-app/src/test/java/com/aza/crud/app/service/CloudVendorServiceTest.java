package com.aza.crud.app.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.aza.crud.app.model.CloudVendor;
import com.aza.crud.app.model.VendorAddress;
import com.aza.crud.app.repository.CloudVendorRepositoryInterface;

@ExtendWith(MockitoExtension.class)
public class CloudVendorServiceTest {
	
	@InjectMocks
	private CloudVendorService svc;

	@Mock
	private CloudVendorRepositoryInterface repo;
	
	AutoCloseable autoCloseable;
	CloudVendor cloudVendor;

	@BeforeEach
	void setUp() {

		autoCloseable = MockitoAnnotations.openMocks(CloudVendorServiceTest.class);
		svc = new CloudVendorService(repo);
		cloudVendor = new CloudVendor((long) 1, "MYDIN", "Jln Masjid India", "03-989002");
	}
	
	@AfterEach
	void tearDown() throws Exception{
		Mockito.reset(repo);
		autoCloseable.close();
	}
	
	@Test
	void testDeleteVendor() {
		
		//repo.deleteById(vendorId);
		mock(CloudVendorRepositoryInterface.class);
		mock(CloudVendor.class);
		
		doNothing().when(repo).deleteById(cloudVendor.getId());  
		svc.deleteVendor(1L);
		
		verify(repo, times(1)).deleteById((long) 1);
	}
	
	@Test 
	void testUpdateVendor(){
		
		// repo.findById(vendorId).get();
		// repo.save(updatedVendor);

		mock(CloudVendor.class);
		mock(CloudVendorRepositoryInterface.class);
			
		when(repo.findById(cloudVendor.getId())).thenReturn(Optional.of(cloudVendor));
		when(repo.save(cloudVendor)).thenReturn(cloudVendor);
		
		//VendorAddress addr= new VendorAddress((long) 1, "MYDIN", "Jln Masjid India"));
		//CloudVendor updateVendor = svc.updateVendor(1L, new CloudVendor((long) 1, "MYDIN", "Jln Masjid India", "03-989002"));
		//assertEquals(updateVendor,cloudVendor);
	}

	@Test
	void testGetVendor() {
		
		// repo.findById(vendorId).get()
		mock(CloudVendor.class);
		mock(CloudVendorRepositoryInterface.class);
		
		when(repo.findById(cloudVendor.getId())).thenReturn(Optional.of(cloudVendor));
				
		CloudVendor newVendor = svc.getVendor(1L);
		assertEquals(newVendor, cloudVendor);
	}
	
	@Test
	void testGetAllVendor() {
		
		// List<CloudVendor> vendorList = repo.findAll();
		mock(CloudVendor.class);
		mock(CloudVendorRepositoryInterface.class);

		List<CloudVendor> users = new ArrayList<CloudVendor>(Collections.singleton(cloudVendor));
		
		when(repo.findAll()).thenReturn(users);
		List<CloudVendor> result = svc.getAllVendor();
		
		assertThat(result).isEqualTo(users);
	}
	
	@Test
	void testCreateVendor() {
		
		// CloudVendor savedVendor = repo.save(vendor);
		
		mock(CloudVendor.class);
		mock(CloudVendorRepositoryInterface.class);
		
		when(repo.save(cloudVendor)).thenReturn(cloudVendor);
		
		CloudVendor newcloudVendor = svc.createVendor(new CloudVendor((long) 1, "MYDIN", "Jln Masjid India", "03-989002"));
		assertEquals(newcloudVendor,cloudVendor);
		
		
	}	
}




