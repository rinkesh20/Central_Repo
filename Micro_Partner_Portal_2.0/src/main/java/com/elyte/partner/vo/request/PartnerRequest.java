package com.elyte.partner.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class PartnerRequest 
{
	@Schema (description = "name of the partner ", example = "Rinkesh Satpute" , required = true)
	 private String partnerName;
	@Schema (description = "partnerReferanceId of the partner should be in alphanumeric ", example = "root2021" , required = true)
	 private String partnerReferanceId;
	@Schema (description = "mobile number of the partner, maximum 12 digits, start with 0/91 is permisible ", example = "9876543210" , required = true)
	 private Long mobileNumber;
	private Admin admin;
	private String city;
	private String state;
	private String country;
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerReferanceId() {
		return partnerReferanceId;
	}
	public void setPartnerReferanceId(String partnerReferanceId) {
		this.partnerReferanceId = partnerReferanceId;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
	
}
