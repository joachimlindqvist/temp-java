package dev.whitespace.mail;

public record Mail(String from, String to, String subject, String textBody, String htmlBody) {
}
