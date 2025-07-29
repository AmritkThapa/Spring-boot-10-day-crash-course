package com.amrit.springBoot.service;

public interface MailSenderService {
    public void sendEmail(String to, String subject, String body);
}
