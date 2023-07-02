package com.amalitech.fileserver.Services.Impl;

import com.amalitech.fileserver.Services.Interfaces.FirebaseService;
import com.amalitech.fileserver.Services.Interfaces.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final FirebaseService firebaseService;

    @Override
    public boolean sendMail(String recipientAddress,String fileName ) throws MessagingException, IOException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setFrom("steven.attipoe@amalitech.org");
        helper.setTo(recipientAddress);
        helper.setSubject("Forwarded Attachment from File Server");
        helper.setText("The file was shared with you via the FileServer");
        helper.addAttachment(fileName, firebaseService.downloadFileFromFirebase(fileName));
        mailSender.send(mail);
        return true;
    }

}
