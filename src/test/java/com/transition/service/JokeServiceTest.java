package com.transition.service;

import com.transition.data.Joke;
import com.transition.data.Jokes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JokeServiceTest {
    JokeService jokeService;
    Joke joke1;
    Joke joke2;
    Joke joke3;

    @BeforeEach
    void setUp() {
        jokeService = new JokeService();
        joke1 = new Joke("Express Joke");
        joke1.setId(1);
        joke1.setComments(List.of("Funny","Splendid"));
        joke1.setDateCreated(new Date());

        joke2 = new Joke("Transition vision");
        joke2.setId(2);
        joke2.setComments(List.of("Superb","Excellent"));
        joke2.setDateCreated(new Date());

        joke3 = new Joke("Basket mouth");
        joke3.setId(3);
        joke3.setComments(List.of("Ugly","Excellent"));
        joke3.setDateCreated(new Date());

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test that Joke Class can be created")
    void testThatJokeCanBeCreated(){
        Joke joke = new Joke("zee");
        joke.setId(1);
        joke.setComments(List.of("good","better"));
        joke.setDateCreated(new Date());
        assertNotNull(joke);
        assertEquals("zee",joke.getContent());
        assertEquals(1,joke.getId());
        assertEquals(2,joke.getComments().size());
        assertEquals("good",joke.getComments().get(0));
        assertEquals("better",joke.getComments().get(1));
        Date date = new Date();
        int result = date.compareTo(joke.getDateCreated());
        assertEquals(1,result);
    }
    @Test
    @DisplayName("Test that List of Jokes can be created")
    void testThatListOfJokesCanBeCreated(){
        Jokes jokes = new Jokes();
        jokes.setJokes(List.of(joke1,joke2,joke3));
        assertEquals(3,jokes.getJokes().size());
        assertEquals("Express Joke",jokes.getJokes().get(0).getContent());
        assertEquals("Transition vision",jokes.getJokes().get(1).getContent());
        assertEquals("Basket mouth",jokes.getJokes().get(2).getContent());
    }


}