package com.CodingBootCamp;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.CodingBootCamp.model.LoggedInUsers;
import com.CodingBootCamp.model.User;
import com.CodingBootCamp.repository.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
class UserServiceTest {
@Autowired
private UserServiceImpl usi;
	@Test
	void testLogin() {
	User u=new User();
	Long mId=12L;
	
	u.setEmail("user@gmail.com");
	u.setContact("8130520044");
	u.setUserName("abc");
	u.setEnabled(true);
	assertEquals("abc", u.getUserName());
	assertEquals("user@gmail.com", u.getEmail());
	usi.login(u,mId);
	}
	@Test
	void testLogin1() {
	User u=new User();
	Long mId=12L;
	
	u.setEmail("user@gmail.com");
	//u.setContact("8130520044");
	u.setUserName("abc");
	u.setEnabled(true);
	//u.setMeeting_id(mId);
	assertEquals(12L, mId);
	assertEquals("abc", u.getUserName());
	assertEquals("user@gmail.com", u.getEmail());
	usi.login(u,mId);
	}
	
	@Test
	void testNonVerifiedLogin() {
		Long mId=12L;
	User u=new User();
	
	u.setEmail("user@gmail.com");
	u.setContact("8130520044");
	u.setUserName("abc");
	u.setEnabled(false);
	assertEquals("abc", u.getUserName());
	assertEquals("user@gmail.com", u.getEmail());
	usi.login(u,mId);
	}
	
	
	@Test
	void testInvalidEmailLogin() {
		Long mId=12L;
		User u=new User();
		u.setId(77L);
		u.setEmail("user.gmail.com");
		u.setContact("8130520044");
		u.setUserName("abc");
		usi.login(u,mId);
		}
		
	@Test
	void testNullLogin() {
		Long mId=12L;
		User u=new User();
		//u.setId(77L);
		u.setEmail("");
		u.setUserName("");
		assertEquals("", u.getEmail());
		usi.login(u,mId);
		}
	@Test
	void testInvalidusernameLogin() {
		Long mId=12L;
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("@#$^&^$#");
		assertEquals("user@gmail.com", u.getEmail());
		usi.login(u,mId);
		}
	
	
	
	@Test
	void registerTest() {
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		//u.setMeeting_id(mId);
		u.setContact("8130520044");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		assertEquals("user@gmail.com", u.getEmail());
		
		//String url="htttp://localhost8080";
		//usi.register(u, url);
		
	}
	@Test
	void registernegativeContactTest() {
		User u=new User();
		//u.setId(77L);
		Long meetId=12L;
		u.setEmail("user@gmail.com");
		u.setContact("89999");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		assertEquals("user@gmail.com", u.getEmail());
		
		String url="htttp://localhost8080";
		usi.register(u, url, meetId);
		
	}
	@Test
	void registerAlphaContactTest() {
		Long meetId=12L;
		User u=new User();
		u.setEmail("user@gmail.com");
		u.setContact("abc");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		assertEquals("user@gmail.com", u.getEmail());
		
		String url="htttp://localhost8080";
		usi.register(u, url, meetId);
		
	}
	
	@Test
	void registerEmptyTest() {
		Long meetId=12L;
		User u=new User();
		//u.setId(77L);
		u.setVerificationCode("12we");
		u.setEnabled(true);
		u.setEmail("");
		u.setContact("");
		u.setUserName("");
		
		String url="htttp://localhost8080";
		usi.register(u, url, meetId);
		
	}
	
	@Test
	void registerInvalidUsernameTest() {
		Long meetId=12L;
		User u=new User();
		//u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("#@$%");
		String url="htttp://localhost8080";
		usi.register(u, url,meetId);
		
	}
	@Test
	void registerInvalidEmailTest() {
		Long meetId=12L;
		User u=new User();
		//u.setId(77L);
		u.setEmail("user,gmail..com");
		u.setContact("8130520044");
		u.setUserName("abc");
		
		String url="htttp://localhost8080";
		usi.register(u, url, meetId);
		
	}
	


	
	@Test
	void testsaveLoggedInUser() {
		Long mId=2L;
		User u=new User();
		u.setId(77L);
		u.setEmail("user@gmail.com");
		u.setContact("8130520044");
		u.setUserName("abc");
		u.setVerificationCode("12we");
		u.setEnabled(true);
		usi.saveLoggedInUser(u,mId);
	}

	@Test
	void testEmailExist() {
		Long mId=123L;
		User u=new User();
		u.setEmail("abc@gmail.com");
		assertEquals("abc@gmail.com",u.getEmail());
		usi.emailExist(u, mId);
	
	}

	@Test
	void testverify() {
		User u=new User();
		u.setEnabled(true);
		u.setVerificationCode("wegd223");
		usi.verify(u.getVerificationCode());
	}
	@Test
	void testverifyNull() {
		User u=new User();
		u.setEnabled(true);
		u.setVerificationCode(null);
		usi.verify(u.getVerificationCode());
	}
	
	@Test
	void getLoggedInUsertest() {
		usi.getLoggedInUser();
	}


	
	@Test
	void getLoggedInUserTest(){
		usi.getLoggedInUser();
		
	}

	@Test
	void testGetRegisteredUsers() {
		User u=new User();
		u.setEnabled(true);
		assertEquals(true, u.isEnabled());
		usi.getRegisteredUsers();
	}
	
	@Test
	void getFeedbacksTest() {
		usi.getFeedbacks();
	}
	
	@Test
	void submitFeedbackTest() {
		Long eventId=4L;
		LoggedInUsers loguser=new LoggedInUsers();
		loguser.setEmail("rah895@gmail.com");
		loguser.setUserName("RAHUL SACHAN");
		loguser.setFeedback("eventful");
		usi.submitFeedback(loguser, eventId);
	}
//	@Ignore
//	@Test
//	void invalidUnamesubmitFeedbackTest() {
//		Long eventId=3L;
//		LoggedInUsers loguser=new LoggedInUsers();
//		loguser.setEmail("rah895@gmail.com");
//		loguser.setUserName("rahuls");
//		loguser.setFeedback("Interesting");
//		usi.submitFeedback(loguser, eventId);
//	}
//	
}
