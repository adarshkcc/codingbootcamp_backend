package com.CodingBootCamp;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootCamp.controller.EmailVerifyController;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class EmailVerifyControllerTest {

	@Autowired
	EmailVerifyController evcon;
	
	@Test
	void Verifytest() {
		String code="'t4ExQh8g7MkayATy983GQD4qvYgTQ9u7hEA4S2csSFzRkWsNC93qRyqENn3BMGWu'";
		evcon.verifyUser(code);
		
	}
	

	

}
