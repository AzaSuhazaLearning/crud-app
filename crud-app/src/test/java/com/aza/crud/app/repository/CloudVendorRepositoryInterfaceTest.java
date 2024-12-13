package com.aza.crud.app.repository;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.aza.crud.app.model.CloudVendor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CloudVendorRepositoryInterfaceTest {

	@Autowired
	private CloudVendorRepositoryInterface cloudRepo;
	CloudVendor cloudVendor;
	
	@BeforeEach
	void setUp() {
		cloudVendor = new CloudVendor((long) 1, "MYDIN", "Jln Masjid India", "03-989002");
		cloudRepo.save(cloudVendor);
	}
	
	@AfterEach
	void tearDown(){
		cloudVendor =null;
		cloudRepo.deleteAll();
	}
	
	//success scenario
	@Test
	void testFindByVendorName_Found() {
		List<CloudVendor> vendorList = cloudRepo.findByVendorName("MYDIN");

		assertNotNull(vendorList);
		assertEquals(vendorList.get(0).getId(), cloudVendor.getId());
	}
	
	//failed scenario
	@Test
	void testFindVendorByName_NotFound() {
		
		List<CloudVendor> vendorLst = cloudRepo.findByVendorName("AZA");

		assertThat(vendorLst.isEmpty()).isTrue();
		assertNotEquals(vendorLst.get(0).getId(), cloudVendor.getId());
	}
}
