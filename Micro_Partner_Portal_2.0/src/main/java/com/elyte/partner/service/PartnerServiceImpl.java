package com.elyte.partner.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

import com.elyte.partner.constant.CodeConstant;
import com.elyte.partner.dao.PartnerDaoI;
import com.elyte.partner.dao.UserDao;
import com.elyte.partner.exception.ValidationExcep;
import com.elyte.partner.response.ValidationExceptionResponse;
import com.elyte.partner.utility.ValidatorUtility;
import com.elyte.partner.vo.request.PartnerRequest;
import com.elyte.partner.vo.responce.CreatePartnerResponse;

@Service
public class PartnerServiceImpl implements PartnerServiceI {
	@Autowired
	PartnerDaoI partnerDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public PartnerRequest retrivePartner(String partnerReferanceId) {

		return partnerDao.retrivePartner(partnerReferanceId);
	}

	@Override
	public Long validateDeletedPosition(String partnerReferenceId) {
		return partnerDao.validateDeletedPosition(partnerReferenceId);
	}

	@Override
	public ResponseEntity<?> createPartner(PartnerRequest partnerdetails) {

		try {

			if (Boolean.TRUE == ValidatorUtility.validatePartnerRequest(partnerdetails)) {
				Long countforRefId = partnerDao.validatePartnerReferenceId(partnerdetails.getPartnerReferanceId());
				if (countforRefId > 0) {
					CreatePartnerResponse response = new CreatePartnerResponse();
					response.setResponseCode(CodeConstant.validatioErrror);
					response.setResponseMsg("Duplicate Partner Referance Id");
					response.setTimeStamp(new Date().toString());
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
				Long count = partnerDao.validateMobileNumber(partnerdetails.getMobileNumber());
				if (count > 0) {
					CreatePartnerResponse response = new CreatePartnerResponse();
					response.setResponseCode(CodeConstant.validatioErrror);
					response.setResponseMsg("Duplicate Mobile Number");
					response.setTimeStamp(new Date().toString());
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

				}
				
				Long adminId=userDao.createPartner(partnerdetails.getAdmin());
				partnerDao.createPartner(partnerdetails,adminId );
				CreatePartnerResponse response = new CreatePartnerResponse();
				response.setResponseCode("200");
				response.setResponseMsg("SUCCESS");
				response.setTimeStamp(new Date().toString());
				return new ResponseEntity<>(response, HttpStatus.OK);

			} else {
				return null;
			}

		} catch (ValidationExcep e) {
			ValidationExceptionResponse v = new ValidationExceptionResponse();
			v.setResponceCode(CodeConstant.validatioErrror);
			v.setResponceMsg(e.getMessage());
			v.setTimeStamp(new Date().toString());
			return new ResponseEntity<>(v, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> updatePartner(PartnerRequest updatedPartnerDetails, String partnerReferanceId) {

		try {

			if (ValidatorUtility.validatePartnerRequest(updatedPartnerDetails) == true) {
				Long count = partnerDao.validateMobileForUpdatePartner(updatedPartnerDetails.getMobileNumber(), partnerReferanceId);
				if (count == 0) 
				{
					Long count1 = partnerDao.validateMobileNumber(updatedPartnerDetails.getMobileNumber());

					if (count1 > 1) {
						CreatePartnerResponse response = new CreatePartnerResponse();
						response.setResponseCode(CodeConstant.validatioErrror);
						response.setResponseMsg("Duplicate Mobile Number");
						response.setTimeStamp(new Date().toString());
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

					}
				}

				if (!updatedPartnerDetails.getPartnerReferanceId().equals(partnerReferanceId)) 
				{
					Long countforRefId = partnerDao.validatePartnerReferenceId(updatedPartnerDetails.getPartnerReferanceId());
					if (countforRefId > 1) {
						CreatePartnerResponse response = new CreatePartnerResponse();
						response.setResponseCode("400");
						response.setResponseMsg("Duplicate Partner Referance Id");
						response.setTimeStamp(new Date().toString());
						return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
					}

					String partnerRefId = partnerDao.updatePartner(updatedPartnerDetails, partnerReferanceId);
					CreatePartnerResponse response = new CreatePartnerResponse();
					response.setResponseCode("200");
					response.setResponseMsg("SUCCESS");
					response.setTimeStamp(new Date().toString());
					response.setPartnerReferanceId(partnerRefId);
					return new ResponseEntity<>(response, HttpStatus.OK);

				}
			} else {
				return null;
			}

		} catch (ValidationExcep e) {
			ValidationExceptionResponse v = new ValidationExceptionResponse();
			v.setResponceCode(CodeConstant.validatioErrror);
			v.setResponceMsg(e.getMessage());
			v.setTimeStamp(new Date().toString());
			return new ResponseEntity<>(v, HttpStatus.BAD_REQUEST);
		}
		return null;

	}

	@Override
	public ResponseEntity<?> deletePartner(String partnerReferenceId) 
	{
		try {
		Long countforRefId =partnerDao.validatePartnerReferenceId(partnerReferenceId);
		if (countforRefId > 0)
		{
		 partnerDao.deletePartner(partnerReferenceId);
		}
		CreatePartnerResponse response = new CreatePartnerResponse();
		response.setResponseCode("200");
		response.setResponseMsg("SUCCESS");
		response.setTimeStamp(new Date().toString());
		response.setPartnerReferanceId(partnerReferenceId);
		return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (ValidationExcep e) {
			ValidationExceptionResponse v = new ValidationExceptionResponse();
			v.setResponceCode(CodeConstant.validatioErrror);
			v.setResponceMsg(e.getMessage());
			v.setTimeStamp(new Date().toString());
			return new ResponseEntity<>(v, HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public List<Map<String, Object>> fetchAllPartner() {
		
		
		return 	partnerDao.fetchAllPartner();
		
				 
	}

}
