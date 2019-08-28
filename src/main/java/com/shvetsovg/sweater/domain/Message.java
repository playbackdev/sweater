package com.shvetsovg.sweater.domain;

import com.shvetsovg.sweater.domain.util.MessageHelper;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2048 chars)")
    private String text;
    @Length(max = 255, message = "Tag too long (more than 255 chars)")
    private String tag;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany
    @JoinTable(
            name = "message_likes",
            joinColumns = { @JoinColumn(name="message_id")},
            inverseJoinColumns = { @JoinColumn(name="user_id")}
    )
    private Set<User> likes = new HashSet<>();

    public Message() {//если наш класс имеет аннотацию Entity, то нужен конструктор без параметров. обязательно.
    }

    public Message(String text, String tag, User author) {
        this.text = text;
        this.tag = tag;
        this.author = author;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    public Long getId() { return id; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public String getFilename() {return filename;}

    public void setFilename(String filename) {this.filename = filename;}

    public Set<User> getLikes() {return likes;}

    public void setLikes(Set<User> likes) {this.likes = likes;}
}