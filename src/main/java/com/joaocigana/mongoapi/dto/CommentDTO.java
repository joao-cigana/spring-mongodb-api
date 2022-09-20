package com.joaocigana.mongoapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CommentDTO implements Serializable {

    private String texto;
    private LocalDateTime date;
    private AuthorDTO author;

    public CommentDTO() {
    }

    public CommentDTO(String texto, LocalDateTime date, AuthorDTO author) {
        this.texto = texto;
        this.date = date;
        this.author = author;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
