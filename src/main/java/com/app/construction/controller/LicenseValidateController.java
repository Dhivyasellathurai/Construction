package com.app.construction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.construction.dto.DecryptDataDto;
import com.app.construction.service.LicenseValidateService;
import com.app.construction.service.RemoteCallService;
import com.example.demo.dto.EncryptDataDto;

@RestController
@RequestMapping("/api/admin")
public class LicenseValidateController {

	@Autowired
	private LicenseValidateService validateService;

	@Autowired
	RemoteCallService remoteCallService;

	@GetMapping("/generate/{companyName}")
	private DecryptDataDto getEncryptedData(@PathVariable("companyName") String companyName) {
		return remoteCallService.getEncryptedData(companyName);
	}

	@PostMapping("/decrypt")
	public EncryptDataDto decrypt(@RequestParam String companyName) throws Exception {
		DecryptDataDto encrypt = getEncryptedData(companyName);
		return validateService.getDecryptData(encrypt);
	}

	@PutMapping("/validate")
	public String validateLicense(@RequestParam String licenseKey) {
		try {
			return validateService.validateLicense(licenseKey);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
