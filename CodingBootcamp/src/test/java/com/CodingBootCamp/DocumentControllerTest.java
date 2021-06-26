package com.CodingBootCamp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.CodingBootCamp.controller.DocumentController;
import com.CodingBootCamp.model.Document;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class DocumentControllerTest {
	@Autowired
	private DocumentController dc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@SuppressWarnings("deprecation")
	@Test
	void testuploadFile() throws Exception {
		Long mid=12L;
		MockMultipartFile mockMultipartFile = new MockMultipartFile("document", "project.txt", "text/plain",
				"This is a Test".getBytes());

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/fileupload").file(mockMultipartFile));
		
		dc.uploadFile(mockMultipartFile, mid);
	}

	@Test
	void testGetRandomFile() {
		byte[] data=new byte[12];
		Document d = new Document();
		d.setId(0);
		d.setContent(data);
		d.setMeeting_id(12L);
		d.setName("Document");
		d.setSize(123L);
		assertEquals(data, d.getContent());
		assertEquals(12L, d.getMeeting_id());
		assertEquals("Document",d.getName());
		assertEquals(123L,d.getSize());
		// d.setContent(d.getContent());
		// d.setId(d.getId());
		// d.setName(d.getName());
		// d.setSize(d.getSize());

		dc.getRandomFile(d.getMeeting_id());
	}

}
