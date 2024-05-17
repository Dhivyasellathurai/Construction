package com.app.construction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.construction.dto.DecryptDataDto;
import com.app.construction.service.LicenseValidateService;

@RestController
@RequestMapping("/api/admin")
public class LicenseValidateController {

	@Autowired
	private LicenseValidateService validateService;

	@PostMapping("/decrypt")
	public Object decrypt(@RequestBody DecryptDataDto encrypt) throws Exception {
		return validateService.getDecryptData(encrypt);
	}

//	@PutMapping("/validate")
//	public String validateLicense(@RequestParam String licenseKey) {
//		try {
//			return validateService.validateLicense(licenseKey);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

}
