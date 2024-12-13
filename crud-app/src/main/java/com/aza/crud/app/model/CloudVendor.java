package com.aza.crud.app.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity(name="VENDOR")
@Data
@AllArgsConstructor
@Table(name="VENDOR", schema="SUHAZA")
public class CloudVendor {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="VENDOR_NAME")
	private String vendorName;
	
	@OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private VendorAddress vendorAddress;
	
	@Column(name="VENDOR_PHONE")
	private String vendorPhoneNumber;
	
	public CloudVendor() {
	}
	
}
