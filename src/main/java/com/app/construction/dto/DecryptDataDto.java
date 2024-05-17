package com.app.construction.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DecryptDataDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String encryptedData;
	private String secretKey;

}