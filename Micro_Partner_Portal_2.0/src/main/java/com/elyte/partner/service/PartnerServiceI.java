package com.elyte.partner.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.elyte.partner.vo.request.PartnerRequest;
import com.elyte.partner.vo.responce.CreatePartnerResponse;

public interface PartnerServiceI {
public PartnerRequest  retrivePartner(String partnerReferanceId); 
public Long validateDeletedPosition(String partnerReferenceId); 
public ResponseEntity<?> createPartner( PartnerRequest partnerdetails);
public ResponseEntity<?> deletePartner(String partnerReferenceId); 
public ResponseEntity<?> updatePartner( PartnerRequest updatedPartnerDetails, String partnerReferanceId) ;
public List<Map<String, Object>> fetchAllPartner() ;
}
