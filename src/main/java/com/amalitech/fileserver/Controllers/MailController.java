package com.amalitech.fileserver.Controllers;

import com.amalitech.fileserver.Dto.Request.MailDto;
import com.amalitech.fileserver.Services.Interfaces.MailService;
import com.amalitech.fileserver.Utils.RequestResponse;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<Object> sendMail(@RequestBody MailDto mailDto ) throws MessagingException, IOException {
        System.err.println(mailDto.toString());
        if (mailService.sendMail(mailDto.getRecipientAddress(), mailDto.getFileName())) {
            return RequestResponse.success("Successfully sent mail to" + "Steven");
        } else {
            return RequestResponse.error(HttpStatus.BAD_REQUEST, "Unable to send mail");
        }
    }
}
