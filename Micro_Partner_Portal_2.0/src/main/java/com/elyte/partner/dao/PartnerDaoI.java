package com.elyte.partner.dao;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.elyte.partner.vo.request.PartnerRequest;
import com.elyte.partner.vo.responce.CreatePartnerResponse;

public interface PartnerDaoI {
	public PartnerRequest retrivePartner(String partnerReferanceId) ;
	public Long validateDeletedPosition(String partnerReferenceId); 
	public Long createPartner(PartnerRequest partnerdetails,  Long adminId);
	public Long validateMobileNumber(Long mobileNo); 
	public Long validatePartnerReferenceId(String partnerReferenceId);
	public Long validateMobileForUpdatePartner( Long mobileNumber, String partnerReferanceId) ;
	public String updatePartner( PartnerRequest updatedPartnerDetails, String partnerReferanceId) ;
	public String deletePartner( String partnerReferenceId) ;
	public List<Map<String, Object>> fetchAllPartner();
}
