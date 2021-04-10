package com.elyte.partner.dao;


import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.elyte.partner.vo.request.PartnerRequest;

@Repository
public class PartnerDaoImpl implements PartnerDaoI {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PartnerRequest retrivePartner(String partnerReferanceId) {
		String query = "SELECT * FROM spring_boot_jdbctemplate.partner WHERE DELETED=0 AND PARTNER_REF_ID =? ";
		Map<String, Object> map = jdbcTemplate.queryForMap(query, partnerReferanceId);
		PartnerRequest partner = new PartnerRequest();
		//partner.setName(map.get("NAME").toString());
	//	partner.setEmail(map.get("EMAIL").toString());
		//partner.setPassword(map.get("PASSWORD").toString());
		partner.setMobileNumber(Long.parseLong(map.get("MOBILE_NO").toString()));
		partner.setPartnerReferanceId((map.get("PARTNER_REF_ID").toString()));
		return partner;
	}

	@Override
	public Long validateDeletedPosition(String partnerReferenceId) {
		String queryToValidatePartnerRefId = "SELECT COUNT(p.ID) FROM spring_boot_jdbctemplate.partner p WHERE DELETED=0 AND PARTNER_REF_ID =? ";
		return jdbcTemplate.queryForObject(queryToValidatePartnerRefId, Long.class, partnerReferenceId);

		 
	}

	@Override
	public Long createPartner(PartnerRequest partnerdetails,  Long adminId) {

		KeyHolder instKeyHolder = new GeneratedKeyHolder();

		String query = "INSERT INTO client1.institute  (PARTNER_NAME, PARTNER_REF_ID, ADMIN_ID, MOBILE_NUMBER, CITY, STATE, COUNTRY ) values (?,?,?,?,?,?,?)";

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, partnerdetails.getPartnerName());
			ps.setString(2, partnerdetails.getPartnerReferanceId());
			ps.setLong(3,  adminId);
			ps.setLong(4, partnerdetails.getMobileNumber());
			ps.setString(5, partnerdetails.getCity());
			ps.setString(6, partnerdetails.getState());
			ps.setString(7, partnerdetails.getCountry());
			return ps;

		}, instKeyHolder);

		return (Long) instKeyHolder.getKey();
	}

	@Override
	public Long validateMobileNumber(Long mobileNo) {
		String queryToValidateMobileNo = "SELECT COUNT(p.MOBILE_NO) FROM spring_boot_jdbctemplate.partner p WHERE DELETED=0 AND MOBILE_NO =? ";
		return jdbcTemplate.queryForObject(queryToValidateMobileNo, Long.class, mobileNo);
		 
	}

	@Override
	public Long validatePartnerReferenceId(String partnerReferenceId) {
		String queryToValidatePartnerRefId = "SELECT COUNT(p.PARTNER_REF_ID) FROM spring_boot_jdbctemplate.partner p WHERE DELETED=0 AND PARTNER_REF_ID =? ";
		return jdbcTemplate.queryForObject(queryToValidatePartnerRefId, Long.class, partnerReferenceId);
	}

	@Override
	public String updatePartner(PartnerRequest partnerdetails, String partnerReferanceId) {

		String query = "UPDATE spring_boot_jdbctemplate.partner set NAME=?, EMAIL=?, USERNAME=?, PASSWORD=?, MOBILE_NO=?, PARTNER_REF_ID=?, UPDATED_ON=NOW()   WHERE DELETED=0 AND PARTNER_REF_ID =?";
	//	jdbcTemplate.update(query, partnerdetails.getName(), partnerdetails.getEmail(), partnerdetails.getEmail(),
	//			partnerdetails.getPassword(), partnerdetails.getMobileNumber(), partnerdetails.getPartnerReferanceId(),
			//	partnerReferanceId);

		return partnerdetails.getPartnerReferanceId();

	}

	@Override
	public Long validateMobileForUpdatePartner(Long mobileNumber, String partnerReferanceId) {
		String queryToValidateMobileNum = "SELECT COUNT(p.MOBILE_NO) FROM spring_boot_jdbctemplate.partner p WHERE p.DELETED=0 AND p.MOBILE_NO=? AND p.PARTNER_REF_ID =?";
		return jdbcTemplate.queryForObject(queryToValidateMobileNum, Long.class, mobileNumber, partnerReferanceId);

	}

	@Override
	public String deletePartner(String partnerReferenceId) {
		String query = "UPDATE spring_boot_jdbctemplate.partner set DELETED=1, DELETED_ON=NOW() WHERE PARTNER_REF_ID =?";
		jdbcTemplate.update(query, partnerReferenceId);
		return partnerReferenceId;
	}

	@Override
	public List<Map<String, Object>> fetchAllPartner() {
		String query="Select * from cloud_core.user u INNER JOIN client1.institute i ON u.ID=i.ADMIN_ID ";
		return 	 jdbcTemplate.queryForList(query);		
	}

}
