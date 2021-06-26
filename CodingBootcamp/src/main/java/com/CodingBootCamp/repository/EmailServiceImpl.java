package com.CodingBootCamp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.CodingBootCamp.dao.UserDAO;
import com.CodingBootCamp.model.EmailTemplate;
import com.CodingBootCamp.model.User;
import com.CodingBootCamp.service.EmailService;
import com.CodingBootCamp.service.UserService;
@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender mailSender;

	
	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	

	public boolean sendEmail(EmailTemplate email,Long meeting_id) {
		logger.info("Email Sent");
 System.out.println("meeting_id"+meeting_id);
		email.setSubject(email.getSubject());
		email.setMsgBody(email.getMsgBody());

		List<User> recipientList = new ArrayList<User>();
		List<User> user =userService.getUserByMId(meeting_id);
		if(user.size()==0)
			return false;
		for (User list : user)
				recipientList.add(list);
		
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				InternetAddress[] recipientAddress = new InternetAddress[recipientList.size()];
				int counter = 0;
				for (User recipient : recipientList) {
					recipientAddress[counter] = new InternetAddress(recipient.getEmail().trim());
					counter++;
				}
				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAddress);
				//mimeMessage.setFrom(new InternetAddress(email.getFrom()));
				mimeMessage.setSubject(email.getSubject());
				
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				String mid="http://localhost:3000/login/"+meeting_id;
				String content= "<html><body><div style='white-space: pre-wrap'>"+email.getMsgBody()+"</div><br/>";
				content+="Kindly click on below link to access bootcamp"+"<br/>";
				
				content+="<a href='"+mid+"'>Click here to join bootcamp</a>"+"<br/><br/><br/>";
				content+="<div style='width:400px'>"+"<div style='float:left'><img style='width:80px;height:80px' src='cid:logo'></div>"
				+"<div style='float:left'>Regards"+"<br/>"+"Realcoderz Pvt. Ltd."+"<br/>"+"815, Tower 2, Plot No 22,<br/>"+" Assotech Business Cresterra,<br/>"+" Sector 135," + 
						"Noida, Uttar Pradesh 201305."+"</div></div>";
				content+="</body></html>";
				helper.setText(content, true);
				
				helper.addInline("logo", new ClassPathResource("image/rclogo1.jfif"));
			}
		};

		try {
			mailSender.send(preparator);
			return true;
		} catch (MailException ex) {

			System.err.println(ex.getMessage());
			return false;
		}
	}

}