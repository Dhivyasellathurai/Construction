package com.app.construction.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.construction.dto.DecryptDataDto;
import com.app.construction.dto.EncryptData;
import com.app.construction.security.DecryptionUtil;
import com.example.demo.dto.EncryptDataDto;

@Service
public class LicenseValidateService {

	public EncryptDataDto getDecryptData(DecryptDataDto dataDto) throws Exception {
		try {
			EncryptDataDto decryptData = DecryptionUtil.decrypt(dataDto);
			return decryptData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String validateLicense(String licenseKey) {
		Optional<LicenseRequest> optional = licenseRequestRepo.findByLicenseKey(licenseKey);
		if (optional.isPresent()) {
			LicenseRequest licenseRequest = optional.get();
			licenseRequest.setStatus(Status.APPROVED);
			licenseRequest.setActivationDate(new Date());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(licenseRequest.getActivationDate());
			calendar.add(Calendar.YEAR, 1);
			Date expiryDate = calendar.getTime();
			licenseRequest.setExpiryDate(expiryDate);
			licenseRequestRepo.save(licenseRequest);
			return "license validate successfully and approved";
		} else {
			return "License Rejected";
		}
	}
}