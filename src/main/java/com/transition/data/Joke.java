package com.transition.data;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlType(propOrder ={"id","content","comments","dateCreated","likes"} )
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Joke {

    private static int count;
    private int id;

    private String content;

    @XmlElementWrapper(name = "comments")
    //@XmlElement(name = "comment")
    private List<String> comments;


    @XmlAttribute
    @XmlSchemaType(name = "date")
    private Date dateCreated;
    private int likes;


    public Joke(){

    }
    public Joke(String content) {
        this(content,new ArrayList<>(), new Date(),0 );
    }

    public Joke(String content, List<String> comments, Date dateCreated, int likes) {
        this.id = ++count;
        this.content = content;
        this.comments = comments;
        this.dateCreated = dateCreated;
        this.likes = likes;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Joke.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getLikes() {
        return likes;
    }

    public void like() {
        this.likes +=1;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                ", dateCreated=" + dateCreated +
                ", likes=" + likes +
                '}';
    }
}
