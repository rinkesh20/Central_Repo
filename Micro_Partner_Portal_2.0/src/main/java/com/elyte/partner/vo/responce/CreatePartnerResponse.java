package com.elyte.partner.vo.responce;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class CreatePartnerResponse extends ResponseDTO
{
	
private String partnerReferanceId;

public String getPartnerReferanceId() {
	return partnerReferanceId;
}

public void setPartnerReferanceId(String partnerReferanceId) {
	this.partnerReferanceId = partnerReferanceId;
}




}
