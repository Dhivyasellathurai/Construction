package com.app.construction.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncryptData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String licenseKey;
	private String email;
}
