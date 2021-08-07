package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception{
        ArrayList<String> powers = new ArrayList<String>();
        ArrayList<String> weaknesses = new ArrayList<String>();
        powers.add("fly");
        powers.add("super strength");
        weaknesses.add("destructive temper");
        weaknesses.add("kryptonite");

        Hero hero = new Hero("supergirl",23,powers,weaknesses);
        assertEquals(true, hero instanceof Hero);
    }

    public Hero setupNewHero() throws Exception{
        ArrayList<String> powers = new ArrayList<String>();
        ArrayList<String> weaknesses = new ArrayList<String>();
        powers.add("fly");
        powers.add("super strength");
        weaknesses.add("destructive temper");
        weaknesses.add("kryptonite");

        Hero hero = new Hero("supergirl",23,powers,weaknesses);
        return  hero;
    }

    @Test
    public void HeroInstanceWithName_true() throws Exception{
        Hero hero = setupNewHero();
        assertEquals("supergirl",hero.getName());
    }


    @Test
    public void HeroInstanceWithAge_true() throws Exception{
        Hero hero = setupNewHero();
        assertEquals(23,hero.getAge());
    }
}