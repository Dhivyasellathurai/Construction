package com.app.construction.service;

import org.springframework.stereotype.Service;

import com.app.construction.dto.DecryptDataDto;
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

}