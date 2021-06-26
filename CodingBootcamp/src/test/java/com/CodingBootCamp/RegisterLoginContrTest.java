package com.CodingBootCamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootCamp.controller.RegistrationAndLoginController;
import com.CodingBootCamp.model.LoggedInUsers;
import com.CodingBootCamp.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class RegisterLoginContrTest {

	@Autowired
	private RegistrationAndLoginController rlcon;
	
	
	@Test
	void processRegister() {
	User u=new User();
	Long event_id=10L;
//	MockHttpServletRequest request= new MockHttpServletRequest();
	rlcon.processRegister(event_id, u, new MockHttpServletRequest());
		
	}
	
	@Test
	void getSiteUrl() {
		MockHttpServletRequest request= new MockHttpServletRequest();

		rlcon.getSiteURL(request);
	}
	
	@Test
	void getById() {
		rlcon.getById(12L);
	}
	
	@Test
	void getAllRegisteredUser() {
		rlcon.getAllRegisteredUsers();
	}
	
	@Test
	void testLogin() {
		Long mId=123L;
		User u=new User();
		u.setEmail("user@gmail.com");
		u.setUserName("abc");
		assertEquals("abc",u.getUserName());
		assertEquals("user@gmail.com", u.getEmail());
		rlcon.login(u,mId); 
	}

//	@Ignore
//	@Test
//	void testProcessRegister() {
//		User u=new User();
//		u.setContact("9876543210");
//		u.setUserName("abc");
//		u.setEmail("user@gmail.com");
//		MockHttpServletRequest request= new MockHttpServletRequest();
//		rlcon.processRegister(u, request);
//	}


	@Test
	void testGetURL() {
		rlcon.getSiteURL(new MockHttpServletRequest());
	
	}
	
	@Test
	void testEmailExist() {
		Long mId=123L;
		User u=new User();
		u.setEmail("abc@gmail.com");
		assertEquals("abc@gmail.com",u.getEmail());
		rlcon.emailExist(u,mId);
	}
//	@Ignore
//	@Test
//	void testGetUserList() {
//	rlcon.getUserList();
////	}
//@Test
//	void testGetRegisteredUser() {
//		rlcon.getRegisteredUser();
//		}
	

	@Test
	void getLoggedInUsertest() {
		
		rlcon.getLoggedInUser();
		}
//	@Test
//	void getLoggedIinUserByIdtest() {
//		Long mId=12l;
//		rlcon.getLoggedInUserByMId(mId);
//		}
//	


	@Test
	void submitFeedback() {
		LoggedInUsers u=new LoggedInUsers();
		Long eventId=4L;
		LoggedInUsers loguser=new LoggedInUsers();
		loguser.setEmail("rah895@gmail.com");
		loguser.setUserName("RAHUL SACHAN");
		loguser.setFeedback("eventful");
		rlcon.submitFeedback(eventId, loguser);
	}
	
	@Test
	void getFeedback() {
		rlcon.getFeedback();
	}
}