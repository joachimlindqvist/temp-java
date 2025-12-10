package dev.whitespace.mail;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Priority(1)
public class SendgridMailer implements Mailer {
    @Override
    public void send(Mail mail) {

    }
}
