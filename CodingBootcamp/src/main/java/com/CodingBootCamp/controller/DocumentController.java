package com.CodingBootCamp.controller;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CodingBootCamp.dao.DocumentDAO;
import com.CodingBootCamp.model.Document;

@CrossOrigin(origins="http://localhost:3000",exposedHeaders = {"Content-Disposition"})
@RestController
@RequestMapping("/files")
public class DocumentController{


	@Autowired
	private DocumentDAO repo;
	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);




	@PostMapping("/upload/{mid}")
	public String uploadFile(@RequestParam("document") MultipartFile multipartfile,@PathVariable("mid") Long mid) throws IOException {

		logger.info("File Uploaded");

		System.out.println(multipartfile);
		String fileName=StringUtils.cleanPath(multipartfile.getOriginalFilename());  
System.out.println("file "+fileName);
		Document document=new Document();
		document.setName(fileName);
		document.setContent(multipartfile.getBytes());
		document.setSize(multipartfile.getSize());
		document.setMeeting_id(mid);
		repo.save(document);
		return "redirect:/";
	}

	@GetMapping("/download/{mid}")
	public ResponseEntity<byte[]> getRandomFile(@PathVariable("mid") Long mid) {
		
		logger.info("File Downloaded");

		long amountOfFiles = repo.count();
		System.out.println(amountOfFiles);


		if (amountOfFiles == 0) {
			return ResponseEntity.ok(new byte[0]);
		}
		/*List<Document> list= repo.findAll();
		id=list.get(list.size()-1).getId();
		Document fileEntity = repo.findById(id).get();*/
		List<Document> fileEntity = repo.findByMeetingId(mid);
		Document file=fileEntity.get(fileEntity.size()-1);
		System.out.println(file.getName());
		HttpHeaders header = new HttpHeaders();

		header.setContentLength(file.getContent().length);
		header.set("Content-Disposition", "attachment; filename=" + file.getName());

		return new ResponseEntity<>(file.getContent(), header, HttpStatus.OK);
	}
}

