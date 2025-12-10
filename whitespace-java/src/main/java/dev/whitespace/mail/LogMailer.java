package dev.whitespace.mail;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Priority(2)
public class LogMailer implements Mailer {
    @Override
    public void send(Mail mail) {
        IO.println("Sent \"%s\" to \"%s\" from \"%s\" with bodies:\n\n%s\n\n%s\n".formatted(mail.subject(), mail.to(), mail.from(), mail.textBody(), mail.htmlBody()));
    }
}
