package com.example.doConnect.request;

import com.example.doConnect.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest {
	private String username;
	private String name;
	private String password;
	private String email;
	private Role role;
}
