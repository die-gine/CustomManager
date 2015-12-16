package de.wild.ejb.jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import de.wild.persistence.entities.Communication;
import de.wild.persistence.entities.Customer;
import de.wild.persistence.enums.CommunicationType;

//MessageListener, um Inhalt der Nachricht auszulesen
@MessageDriven(activationConfig={
		@ActivationConfigProperty(
				propertyName = "destination",
				propertyValue = "java:global/jms/birthdayMailQueue")
})
public class BirthdayNotificationsReceiver implements MessageListener{
	
	@Resource(name="java:jboss/mail/GoogleMail")
	private javax.mail.Session session;

	@Override
	public void onMessage(Message message) {
		if(message instanceof ObjectMessage){
			try {
				Customer customer = message.getBody(Customer.class);
				
				Communication email = null;
				for(Communication com : customer.getCommunication()){
					if(com.getCommunicationType()== CommunicationType.Email){
						email = com;
						break;
					}
				}
				
				if(email != null){
					javax.mail.Message mail = new MimeMessage(session);
					
					mail.setRecipients(javax.mail.Message.RecipientType.TO, 
							InternetAddress.parse(email.getValue()));
					 					
					mail.setSubject("Happy B-Day");
					mail.setText("Hallo, alles Gute");
					
					Transport.send(mail);
				}
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
