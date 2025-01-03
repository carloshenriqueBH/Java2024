package enviando_mail.ernviando_mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userName = "cazenriquejdev@gmail.com";
	private String senha = "apsw gwuy cvui reht";

	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";

	public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean ehHTML) throws Exception {

		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*"); /* em 22/10/2024 - autenticação com segurança ssl */
		properties.put("mail.smtp.auth", "true"); /* autorização */
		properties.put("mail.smtp.starttls", "true"); /* autenticação */
		properties.put("mail.smtp.host", "smtp.gmail.com"); /* servidor gmail Google */
		properties.put("mail.smtp.port", "465"); /* porta do servidor */
		properties.put("mail.smtp.socketFactory.port", "465"); /* Porta a ser conectada pelo socket */
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão a SMTP */

		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
//		Address[] toUsers = InternetAddress.parse("cazenribhjdev@gmail.com,carlhenrique@gmail.com,carloshenriquebh1007@yahoo.com,cazenriquebh@gmail.com");
		Address[] toUsers = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUsers);
		message.setSubject(assuntoEmail);
		if (ehHTML) {
			message.setContent(textoEmail, "text/html; charset=utf8");
		} else {
			message.setText(textoEmail);
		}
		Transport.send(message);
	}

	public void enviarEmailComAnexo(boolean ehHTML) throws Exception {

		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*"); /* em 22/10/2024 - autenticação com segurança ssl */
		properties.put("mail.smtp.auth", "true"); /* autorização */
		properties.put("mail.smtp.starttls", "true"); /* autenticação */
		properties.put("mail.smtp.host", "smtp.gmail.com"); /* servidor gmail Google */
		properties.put("mail.smtp.port", "465"); /* porta do servidor */
		properties.put("mail.smtp.socketFactory.port", "465"); /* Porta a ser conectada pelo socket */
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão a SMTP */

		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
//		Address[] toUsers = InternetAddress.parse("cazenribhjdev@gmail.com,carlhenrique@gmail.com,carloshenriquebh1007@yahoo.com,cazenriquebh@gmail.com");
		Address[] toUsers = InternetAddress.parse(listaDestinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUsers);
		message.setSubject(assuntoEmail);

		/* 1a parte: definido o corpo do email */
		MimeBodyPart corpoEmail = new MimeBodyPart();

		if (ehHTML) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf8");
		} else {
			corpoEmail.setText(textoEmail);
		}
		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorDePDF());
		arquivos.add(simuladorDePDF());
		arquivos.add(simuladorDePDF());

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);

		int contaArquivos = 1;
		for (FileInputStream fileInputStream : arquivos) {

			/* 2a parte: definido o anexo do email */

			MimeBodyPart anexoEmail = new MimeBodyPart();
			anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
			anexoEmail.setFileName("anexoEmail" + contaArquivos + ".pdf");
			multipart.addBodyPart(anexoEmail);
			contaArquivos++;
		}
		message.setContent(multipart);
		Transport.send(message);
	}

	

	private FileInputStream simuladorDePDF() throws Exception {
		Document document = new Document();
		File file = new File("fileAnexo.PDF");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteúdo do PDF Anexo com Java Mail"));
		document.close();
		return new FileInputStream(file);
	}
}
