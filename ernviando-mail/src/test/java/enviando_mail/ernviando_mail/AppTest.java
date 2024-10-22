package enviando_mail.ernviando_mail;

//import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;


/**
 * Unit test for simple App.
 */
public class AppTest {
	
	private String userName = "cazenriquejdev@gmail.com";
	private String senha = "apsw gwuy cvui reht";	
	
	
	@org.junit.jupiter.api.Test
	public void testeEmail() {
		
		/*
		 * verificar antes as configurações de SMTP do seu email
		 * continuando 17/10/2024
		 * POP E SMTP do gmail
		 * Descrição do SMTP gmail: um nome genérico - ex.: "SMTP"
		 * Nome do servidor SMTP gmail: smtp.gmail.com
		 * Usuário SMTP gmail     : o seu endereço de email (cazenribhjdev@gmail.com)
		 * Password SMTP gmail    : a sua password
		 * Porta SMTP do gmail    : 465
		 */		
		
		try {
			
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*"); /* em 22/10/2024 - autenticação com segurança ssl */
			properties.put("mail.smtp.auth", "true"); /* autorização */
			properties.put("mail.smtp.starttls", "true"); /* autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com"); /* servidor gmail Google */
			properties.put("mail.smtp.port", "465"); /* porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465"); /* Porta a ser conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão a SMTP */
			
			Session session = Session.getInstance(properties, new Authenticator() {
				
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(userName, senha);					
				}				
			});
			Address[] toUsers = InternetAddress.parse("cazenribhjdev@gmail.com,carlhenrique@gmail.com,carloshenriquebh1007@yahoo.com,cazenriquebh@gmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO,toUsers);
			message.setSubject("Chegou email enviado pelo java!!!!");
			message.setText("Testando o envio de email pelo java!");
			
			Transport.send(message);
			
			
			System.out.println("Email enviado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}  
}
