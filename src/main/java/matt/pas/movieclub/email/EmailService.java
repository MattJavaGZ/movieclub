package matt.pas.movieclub.email;

import matt.pas.movieclub.domain.user.User;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EmailService {

    private final String url;

    public EmailService(@Value("${app.email.url}") String url) {
        this.url = url;
    }

    public void sendActivEmail(User user) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName("smtp.poczta.onet.pl");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("matekjava@onet.pl", "Jakieshaslo123@"));
        email.setSSLOnConnect(true);
        email.setFrom("matekjava@onet.pl");
        email.setSubject("Aktywacja konta w portalu movieclub");
        String emailText = """
                Witaj,
                
                zarejestrowałeś konto w Movieclub.
                
                Aktywuj konto poniższym linkie:
                %s/aktywacja/%d?activKey=%s
                
                Pozdrawiamy,
                """;
        email.setMsg(emailText.formatted(url, user.getId(), user.getActivKey()));
        email.addTo(user.getEmail());
        email.send();
    }
}
