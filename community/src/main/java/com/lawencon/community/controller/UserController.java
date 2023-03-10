package com.lawencon.community.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.service.JWTService;
import com.lawencon.community.service.UserService;
import com.lawencon.community.pojo.PojoRes;
import com.lawencon.community.pojo.login.PojoLoginReq;
import com.lawencon.community.pojo.login.PojoLoginRes;
import com.lawencon.community.pojo.user.PojoUserReq;
import com.lawencon.community.model.User;

@RestController
@RequestMapping("users")
public class UserController {
	private final UserService userService;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;

	public UserController(AuthenticationManager authenticationManager, UserService userService, JWTService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;

	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody final PojoLoginReq user) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPass());

		authenticationManager.authenticate(auth);
		final Optional<User> userOptional = userService.getByEmail(user.getEmail());

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", userOptional.get().getId());

		final PojoLoginRes loginRes = new PojoLoginRes();
		loginRes.setToken(jwtService.generateJWT(claims));
		loginRes.setUserId(userOptional.get().getId());
		loginRes.setRoleCode(userOptional.get().getRole().getRoleCode());
		if(loginRes.getFile()!=null) {
			loginRes.setFile(userOptional.get().getFile().getId());			
		}

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
	
	@PostMapping("register")
	public ResponseEntity<PojoRes> register(@RequestBody PojoUserReq data) throws Exception{
		final PojoRes res = userService.register(data);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
}
