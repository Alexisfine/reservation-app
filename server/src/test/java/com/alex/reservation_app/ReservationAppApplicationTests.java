package com.alex.reservation_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest
class ReservationAppApplicationTests {
	@Autowired
	UserDetailsService userDetailsService;

	@Test
	void contextLoads() {
	}

	@Test
	void checkRoles() {
		UserDetails details = userDetailsService.loadUserByUsername("alex");
		boolean admin = details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
		System.out.println("test " + admin);

		UserDetails details1 = userDetailsService.loadUserByUsername("sunny");
		boolean admin1 = details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
		System.out.println("test" + admin);
	}

}
