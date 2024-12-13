package com.aza.crud.app.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.aza.crud.app.model.CloudVendor;
import com.aza.crud.app.service.CloudVendorService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@WebMvcTest(CloudVendorAPIService.class)
public class CloudVendorAPIServiceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private CloudVendorAPIService api;
	
	@MockBean
	private CloudVendorService svc;
	
	CloudVendor cloudVendor1;
	CloudVendor cloudVendor2;
	List<CloudVendor> vendorList = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		cloudVendor1 = new CloudVendor((long) 1, "MYDIN", "Jln Masjid India", "03-989002");
		
		cloudVendor2 = new CloudVendor((long) 2, "AMZON", "KLCC", "03-6989002");
		vendorList.add(cloudVendor1);
		vendorList.add(cloudVendor2);
	}
	
	@AfterEach
	void tearDown(){
		cloudVendor1 =null;
		cloudVendor2 =null;
		api.deleteVendorById(1L);
		api.deleteVendorById(2L);
	}
	
	@Test
	void testGetAllVendors() {
		//svc.getAllVendor(),HttpStatus.OK
		
		mock(CloudVendorService.class);
		
		when(svc.getAllVendor()).thenReturn(vendorList);
		ResponseEntity<List<CloudVendor>> result = api.getAllVendors();
		assertThat(result).isEqualTo(vendorList);
	}
	
	@Test
	void testCreateNewVendor() {
		
	}
	
	@Test
	void testDeleteVendorById() {
		
	}
	
	@Test
	void testUpdateVendor() {
		
	}
	
	@Test
	void testGetVendorById() {
		
	}
	
}
