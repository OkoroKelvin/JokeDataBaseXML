package com.transition.service;

import com.transition.data.Comment;
import com.transition.data.Joke;
import com.transition.data.Jokes;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JokeService {


    public void createNewJoke(Joke joke) throws JAXBException {
        Jokes newJoke = new Jokes();
        newJoke.setJokes(List.of(joke));
        writeJokeToXml(newJoke);

    }

    public void writeJokeToXml(Jokes joke) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Jokes.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(joke, new File("vision.xml"));
    }

    public Jokes readJokeFromXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Jokes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        JAXBElement<Jokes> rootElement = unmarshaller.unmarshal(
                new StreamSource(new File("vision.xml")),
                Jokes.class);

        return rootElement.getValue();
    }

    public List<Joke> getAllSortedJokesByLikes(boolean fromLeast) throws JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
       if(fromLeast) {
           listOfJoke .sort(Comparator.comparing(Joke::getLikes));
       }else {
           listOfJoke .sort(Comparator.comparing(Joke::getLikes).reversed());
       }

        return listOfJoke ;
    }

    public void addCommentToJoke(int id,String comment) throws JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        listOfJoke.stream().peek(joke -> {
            if(joke.getId() == id)joke.addComment(comment);
        }).toList();

        Jokes newJokes = new Jokes();
        newJokes.setJokes(listOfJoke);
        writeJokeToXml(newJokes);
    }

    public void removeCommentsFromJoke(int id) throws IOException, JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        listOfJoke.stream().peek(joke -> {
            if (joke.getId() == id) joke.setComments(new ArrayList<String>());
        }).toList();

        Jokes newJokes = new Jokes();
        newJokes.setJokes(listOfJoke);
        writeJokeToXml(newJokes);
    }

    public void likeJoke(int id) throws IOException, JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        listOfJoke.stream().peek(joke -> {
            if (joke.getId() == id) joke.like();
        }).toList();
        Jokes newJokes = new Jokes();
        newJokes.setJokes(listOfJoke);
        writeJokeToXml(newJokes);
    }

    public void removeJoke(int id) throws IOException, JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        listOfJoke.stream().filter(joke -> joke.getId() != id).toList();
        Jokes newJokes = new Jokes();
        newJokes.setJokes(listOfJoke);
        writeJokeToXml(newJokes);
    }

    public void addJokesToXmlFile(Joke joke) throws JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        listOfJoke.add(joke);
        Jokes newJokes = new Jokes();
        newJokes.setJokes(listOfJoke);
        writeJokeToXml(newJokes);
    }

    public List<Joke> readAllJokes() throws JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        return listOfJoke;
    }

    public List<Joke> getAllSortedJokesByDate() throws JAXBException {
        Jokes allJoke = readJokeFromXml();
        List<Joke> listOfJoke = allJoke.getJokes();
        listOfJoke .sort(Comparator.comparing(Joke::getDateCreated));
        return listOfJoke;
    }
}
