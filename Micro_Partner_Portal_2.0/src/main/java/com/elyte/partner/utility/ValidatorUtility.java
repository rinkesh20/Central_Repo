package com.elyte.partner.utility;

import java.util.regex.Pattern;

import com.elyte.partner.exception.ValidationExcep;
import com.elyte.partner.vo.request.PartnerRequest;

public class ValidatorUtility 
{
	
	
	
	public static Boolean validatePartnerRequest(PartnerRequest request)
	{
		/*
		 * if(null ==request.getName() ) { throw new ValidationExcep("Name is null"); }
		 * 
		 * if(null ==request.getEmail()) { throw new ValidationExcep("Email is null"); }
		 * if(request.getEmail().isEmpty()) { throw new
		 * ValidationExcep("email is Empty"); } Pattern
		 * emailRegEx=Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
		 * if(!emailRegEx.matcher(request.getEmail()).matches()) { throw new
		 * ValidationExcep("email is invalid"); } //if(null ==request.getUserName())
		 * //{throw new ValidationExcep("UserName is null");}
		 * //if(request.getUserName().isEmpty()) //{throw new
		 * ValidationExcep("UserName is Blank");}
		 * //if(!request.getUserName().contentEquals(request.getEmail())) //{throw new
		 * ValidationExcep("UserName and Email Must be Same");} if(null
		 * ==request.getPassword()) { throw new ValidationExcep("Password is null"); }
		 * if(request.getPassword().isEmpty()) { throw new
		 * ValidationExcep("Password is Empty"); } Pattern
		 * partenrpassword=Pattern.compile(
		 * "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
		 * if(!partenrpassword.matcher(request.getPassword()).matches()) { throw new
		 * ValidationExcep("Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
		 * ); }
		 */
		if(null ==request.getMobileNumber())
		{
			throw new ValidationExcep("Mobile Number is null");
		}
		if(request.getMobileNumber().toString().isEmpty())
		{
			throw new ValidationExcep("Mobile Number is Blank");
		}
		if(request.getMobileNumber().toString().contains(" "))
		{
			throw new ValidationExcep("Invalid Mobile Number");
		}
		Pattern partenrMobileNo=Pattern.compile("(0/91)?[7-9][0-9]{9}");
		if(! partenrMobileNo.matcher(request.getMobileNumber().toString()).matches())
		{
			throw new ValidationExcep("Invalid Mobile Number");
		}
		if(null ==request.getPartnerReferanceId())
		{
			throw new ValidationExcep("Partner Refernce ID is null");
		}
		if(request.getPartnerReferanceId().isEmpty())
		{
			throw new ValidationExcep("Partner Refernce ID is Empty");
		}
		Pattern partenrRefId=Pattern.compile("^[a-zA-Z0-9]+$");
		if(!partenrRefId.matcher(request.getPartnerReferanceId()).matches())
		{
			throw new ValidationExcep("partnerRefId invalid");
		}
		
		return true;
		
	}

}
