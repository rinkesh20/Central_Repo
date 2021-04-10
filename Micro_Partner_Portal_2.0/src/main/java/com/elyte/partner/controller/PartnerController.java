package com.elyte.partner.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elyte.partner.constant.CodeConstant;
import com.elyte.partner.exception.ValidationExcep;

import com.elyte.partner.response.ValidationExceptionResponse;
import com.elyte.partner.service.PartnerServiceI;
import com.elyte.partner.vo.request.PartnerRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class PartnerController {
	@Autowired
	PartnerServiceI partnerService;

	@Operation(summary = "Get a partner  by its partnerReferenceId", description = "This will give all details of that partner")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PartnerRequest.class)) }),
			@ApiResponse(responseCode = "400", description = "Validation error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ValidationExcep.class)) }),
			@ApiResponse(responseCode = "500", description = "Server error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ValidationExcep.class)) }) })
	@GetMapping("retrivePartner/{partnerReferenceId}")
	public ResponseEntity<?> retrivePartner(
			@Parameter(description = "id of book to be searched") @PathVariable("partnerReferenceId") String partnerReferanceId) {

		try {
			Long count = partnerService.validateDeletedPosition(partnerReferanceId);
			if (count == 0) {
				throw new ValidationExcep("partner reference id not present");
			} else {
				PartnerRequest p = partnerService.retrivePartner(partnerReferanceId);
				return new ResponseEntity<>(p, HttpStatus.OK);

			}
		} catch (ValidationExcep e) {
			ValidationExceptionResponse v = new ValidationExceptionResponse();
			v.setResponceCode(CodeConstant.validatioErrror);
			v.setResponceMsg(e.getMessage());
			v.setTimeStamp(new Date().toString());
			return new ResponseEntity<>(v, HttpStatus.BAD_REQUEST);

		}

	}

	@Operation(summary = "Create a partner", description = "it will create a partner in database and returns partner reference id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "partner has been created successfully", content = {
					@Content(mediaType = "Application/json", schema = @Schema(implementation = PartnerRequest.class)) }),
			@ApiResponse(responseCode = "400", description = "Partner Validation Error", content = {
					@Content(mediaType = "Application/json", schema = @Schema(implementation = ValidationExcep.class)) }),
			@ApiResponse(responseCode = "500", description = "Server  Error", content = {
					@Content(mediaType = "Application/json", schema = @Schema(implementation = ValidationExcep.class)) }) })
	@PostMapping("addPartner")
	public ResponseEntity<?> createPartner(
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Partner to add", required = true, content = @Content(schema = @Schema(implementation = PartnerRequest.class))) @RequestBody PartnerRequest partnerdetails) {
		return partnerService.createPartner(partnerdetails);

	}

	@PutMapping("updatePartner/{partnerReferanceId}")
	public ResponseEntity<?> updatePartner(@RequestBody PartnerRequest updatedPartnerDetails,
			@PathVariable("partnerReferanceId") String partnerReferanceId) {
		return partnerService.updatePartner(updatedPartnerDetails, partnerReferanceId);

	}
	
	@DeleteMapping ("deletePartner/{partnerReferenceId}")
	public ResponseEntity<?> deletePartner(@PathVariable ("partnerReferenceId") String partnerReferanceId ) 
	{
		return partnerService.deletePartner(partnerReferanceId);
		
	}
	
	@GetMapping ("/fetchAllPartner")
	public List<Map<String, Object>> fetchPartner() 
	{
		return partnerService.fetchAllPartner();
		
	}

}
