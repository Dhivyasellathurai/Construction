package com.app.construction.service;

import org.springframework.stereotype.Service;

import com.app.construction.dto.DecryptDataDto;
import com.app.construction.dto.EncryptData;
import com.app.construction.security.DecryptionUtil;

@Service
public class LicenseValidateService {

	public Object getDecryptData(DecryptDataDto dataDto) throws Exception {
		try {
			Object decryptData = DecryptionUtil.decrypt(dataDto);
			return decryptData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public String validateLicense(String licenseKey) {
//		Optional<LicenseRequest> optional = licenseRequestRepo.findByLicenseKey(licenseKey);
//		if (optional.isPresent()) {
//			LicenseRequest licenseRequest = optional.get();
//			licenseRequest.setStatus(Status.APPROVED);
//			licenseRequest.setActivationDate(new Date());
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(licenseRequest.getActivationDate());
//			calendar.add(Calendar.YEAR, 1);
//			Date expiryDate = calendar.getTime();
//			licenseRequest.setExpiryDate(expiryDate);
//			licenseRequestRepo.save(licenseRequest);
//			return "license validate successfully and approved";
//		} else {
//			return "License Rejected";
//		}
//	}
}