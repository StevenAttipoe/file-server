package com.amalitech.fileserver.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "File")
@Data
public class File {
    public File (String name, int numberOfDownloads, int numberOfSentEmails) {
        this.name = name;
        this.numberOfDownloads = numberOfDownloads;
        this.numberOfSentEmails = numberOfSentEmails;
    }

    File () {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String name;

    private int numberOfDownloads;
    private int numberOfSentEmails;

}
