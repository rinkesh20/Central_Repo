package com.elyte.partner.dao;

import com.elyte.partner.vo.request.Admin;
import com.elyte.partner.vo.request.PartnerRequest;

public interface UserDao 
{
	public Long createPartner( Admin partnerdetails);
}
