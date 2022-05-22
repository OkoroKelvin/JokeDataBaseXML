package com.transition.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Jokes {
    @XmlElement(name = "joke")
    private List<Joke> jokes = new ArrayList<>();

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }

    @Override
    public String toString() {
        return "Jokes{" +
                "jokes=" + jokes +
                '}';
    }
}
