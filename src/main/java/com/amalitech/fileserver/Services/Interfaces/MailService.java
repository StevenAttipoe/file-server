package com.amalitech.fileserver.Services.Interfaces;

import com.amalitech.fileserver.Dto.Request.MailDto;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface MailService {
    public boolean sendMail(String recipientAddress,String fileUrl) throws MessagingException, IOException;
}
