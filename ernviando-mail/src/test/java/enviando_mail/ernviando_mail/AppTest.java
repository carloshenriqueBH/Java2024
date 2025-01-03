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
	public void testeEmail() throws Exception {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		stringBuilderTextoEmail.append("Olá, <br/> <br/>");
		stringBuilderTextoEmail.append("<h2>Você está recebendo o acesso ao curso JDEV Treinamentos</h2> <br/>Outra linha<br/>Acessar página<a/>");
		stringBuilderTextoEmail.append("<br/>Clique no botão abaixo para acesso ao curso.<br/>");
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"https://bhzdigital.com.br/\" "
				+ "style=\"color:#2525a7;"
				+ "        padding: 14px 25px; "
				+ "        text-align:center;"
				+ "        text-decoration:none; "
				+ "        display:inline-block;"
				+ "        border-radius:30px;"
				+ "        font-size:20px;"
				+ "        font-family:courier;"
				+ "        border:3px solid green;"
				+ "        background-color:#99DA39;\">Acessar página</a><br/><br/>");

		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("carlhenrique@gmail.com", 
				                                           "Carlos Henrique - projeto JDEV", 
				                                           "Chegou email enviado pelo java!",
				                                           stringBuilderTextoEmail.toString());
		enviaEmail.enviarEmailComAnexo(true);
		enviaEmail.enviarEmail(false);
	}  
}
