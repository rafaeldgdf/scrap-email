package senac.aps.scrap.email.scrap_email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

@SpringBootApplication
public class ScrapEmailApplication implements CommandLineRunner {

    // Lendo as configurações do application.properties
    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Value("${email.to}")
    private String to;

    @Value("${email.from}")
    private String from;

    @Value("${scrap.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(ScrapEmailApplication.class, args);
    }

    @Override
    public void run(String... args) {
    	
        System.out.println("Email Username: " + username);
        System.out.println("Email To: " + to);
        System.out.println("Scrap URL: " + url);
        try {
            Document doc = Jsoup.connect(url).get();


            Elements titles = doc.select(".product_pod h3 a");
            StringBuilder scrapedData = new StringBuilder("Livros Disponíveis:\n");
            for (Element title : titles) {
                scrapedData.append("- ").append(title.attr("title")).append("\n");
            }

          
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

      
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

       
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Resultados do Scrap - Livros Disponíveis");
            message.setText(scrapedData.toString());

            Transport.send(message);
            System.out.println("Email enviado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro durante o processo de scrap ou envio de email: " + e.getMessage());
        }
    }
    
    
    
}
