package com.aza.crud.app.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name ="VENDOR_ADDRESS")
@Data
@AllArgsConstructor
@Table(name ="VENDOR_ADDRESS", schema="SUHAZA")
public class VendorAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ADDRESS_LOCATION")
	private String location;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDOR_id")
	private CloudVendor vendor;
	
	public VendorAddress() {
	}
	
	public String toString() {
		return "Vendor Address: [id=" + id + ", address="+ location + ", vendor=" + vendor.toString();
	}
}
