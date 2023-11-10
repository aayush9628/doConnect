package com.example.doConnect.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
	private String name;
	private String email;
	private String userType;
}
