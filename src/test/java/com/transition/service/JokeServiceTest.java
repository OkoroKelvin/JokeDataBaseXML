package com.transition.service;

import com.transition.data.Joke;
import com.transition.data.Jokes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JokeServiceTest {
    JokeService jokeService;
    Joke joke1;
    Joke joke2;
    Joke joke3;

    @BeforeEach
    void setUp() throws JAXBException {
        jokeService = new JokeService();
        joke1 = new Joke("Express Joke");
        joke1.setId(1);
        joke1.setComments(List.of("Funny", "Splendid"));
        joke1.like();
        joke1.like();
        joke1.setDateCreated(new Date());

        joke2 = new Joke("Transition vision");
        joke2.setId(2);
        joke2.setComments(List.of("Superb", "Excellent"));
        joke2.like();
        joke2.like();
        joke2.like();
        joke2.like();
        joke2.setDateCreated(new Date());

        joke3 = new Joke("Basket mouth");
        joke3.setId(3);
        joke3.setComments(List.of("Ugly", "Excellent"));
        joke3.like();
        joke3.like();
        joke3.setDateCreated(new Date());

        jokeService.createNewJoke(joke1);
        jokeService.addJokesToXmlFile(joke2);
        jokeService.addJokesToXmlFile(joke3);

    }

    @AfterEach
    void tearDown() throws JAXBException, IOException {
        Jokes jokes = jokeService.readJokeFromXml();
        jokeService.removeJoke(1);
        jokeService.removeJoke(2);
        jokeService.removeJoke(3);

    }

    @Test
    @DisplayName("Test that Joke Class can be created")
    void testThatJokeCanBeCreated() {
        Joke joke = new Joke("zee");
        joke.setId(1);
        joke.setComments(List.of("good", "better"));
        joke.setDateCreated(new Date());
        assertNotNull(joke);
        assertEquals("zee", joke.getContent());
        assertEquals(1, joke.getId());
        assertEquals(2, joke.getComments().size());
        assertEquals("good", joke.getComments().get(0));
        assertEquals("better", joke.getComments().get(1));
    }

    @Test
    @DisplayName("Test that List of Jokes can be created")
    void testThatListOfJokesCanBeCreated() {
        Jokes jokes = new Jokes();
        jokes.setJokes(List.of(joke1, joke2, joke3));
        assertEquals(3, jokes.getJokes().size());
        assertEquals("Express Joke", jokes.getJokes().get(0).getContent());
        assertEquals("Transition vision", jokes.getJokes().get(1).getContent());
        assertEquals("Basket mouth", jokes.getJokes().get(2).getContent());
    }

    @Test
    @DisplayName("Test that Joke can be saved to an XML file ")
    void testThatJokeCanBeSavedInAnXmlFile() throws JAXBException {
        Jokes savedJoke = jokeService.readJokeFromXml();
        assertEquals(joke1.getContent(), savedJoke.getJokes().get(0).getContent());
        assertEquals(joke2.getComments(), savedJoke.getJokes().get(1).getComments());
        assertEquals(3, savedJoke.getJokes().size());
    }

    @Test
    @DisplayName("Test that Multiple Joke can be saved to an XML file ")
    void testThatMultipleJokeCanBeSavedInAnXmlFile() throws JAXBException {
        Jokes savedJoke = jokeService.readJokeFromXml();
        assertEquals(3, savedJoke.getJokes().size());
    }

    @Test
    @DisplayName("Test that Joke can be removed from an XML file")
    void testThatJokeCanBeRemovedFromAnXmlFile() throws JAXBException, IOException {
        jokeService.removeJoke(1);
        Jokes savedJoke = jokeService.readJokeFromXml();
        assertEquals(2, savedJoke.getJokes().size());
    }

    @Test
    @DisplayName("Test that Joke can sorted with likes")
    void testThatJokeCanBeSortedByLikesAndDisplayed() throws JAXBException, IOException {
        boolean best = true;
        boolean worst = false;
        jokeService.getAllSortedJokesByLikes(worst).forEach(System.out::println);
        System.out.println("-------------------------------------------------------------------------------------------------------");
        jokeService.getAllSortedJokesByLikes(true).forEach(System.out::println);
    }

    @Test
    @DisplayName("Test that Joke can be sorted with date of published")
    void testThatJokeCanBeSortedWithDates() throws JAXBException {
        jokeService.getAllSortedJokesByDate().forEach(System.out::println);
    }
    @Test
    @DisplayName("Test that comment can be added and removed")
    void testThatCommentCanBeAddedToJokes() throws JAXBException, IOException {
        jokeService.addCommentToJoke(2,"I like it");
        Jokes savedJoke = jokeService.readJokeFromXml();
        savedJoke.getJokes().forEach(System.out::println);
        assertEquals(2,savedJoke.getJokes().get(0).getComments().size());
        jokeService.removeCommentsFromJoke(1);
        Jokes savedJoke2 = jokeService.readJokeFromXml();
        savedJoke2.getJokes().forEach(System.out::println);
        assertEquals(0,savedJoke2.getJokes().get(0).getComments().size());
    }

    @Test
    @DisplayName("Test that can read all jokes")
    void testThatAllJokesCanBeRead() throws JAXBException {
        List<Joke> allJokes = jokeService.readAllJokes();
        assertEquals(3,allJokes.size());
        allJokes.forEach(System.out::println);
    }

    @Test
    @DisplayName("Test that Jokes can be liked")
    void testThatJokesCanBeLiked() throws JAXBException, IOException {
        jokeService.likeJoke(2);
        List<Joke> allJokes = jokeService.readAllJokes();
        assertEquals(5,allJokes.get(1).getLikes());
    }
}