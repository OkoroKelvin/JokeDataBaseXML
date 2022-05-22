package com.transition.data;

import com.transition.service.JokeService;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JokeMain {
    public static void main(String[] args) throws JAXBException {
        JokeService service = new JokeService();
        Joke joke = new Joke("The better");
        joke.like();
       // service.createNewJoke(joke);
        Joke second = new Joke("My second joke");
        second.like();
        second.like();
        service.addJokesToXmlFile(second);
    }
}
