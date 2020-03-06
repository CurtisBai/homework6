import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MyMiniSearchEngineTest {
    private List<String> documents() {
        return new ArrayList<String>(
                Arrays.asList(
                        "hello world",
                        "hello",
                        "world",
                        "world world hello",
                        "seattle rains hello abc world",
                        "sunday hello world fun"));
    }

    @Test
    public void testOneWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("seattle");

        assertEquals(1, result.size());

        assertEquals(Integer.valueOf(4), result.get(0));
    }

    @Test
    public void testTwoWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("hello world");

        assertEquals(2, result.size());

        assertEquals(List.of(0, 5), result);
    }

    @Test
    public void testThreeWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "rains hello abc",
                "rains Hello abc",
        };

        for (String input : inputs) {
            List<Integer> result = engine.search(input);
            assertEquals(1, result.size());
            assertEquals(List.of(4), result);
        }
    }

    @Test
    public void testFourWord() {
        // homework
        MyMiniSearchEngine engine = new MyMiniSearchEngine(new ArrayList<String>(Arrays.asList(myinput)));
        List<Integer> result = engine.search("hello world fine today");
        assertEquals(List.of(0,5,6), result);
    }

    @Test
    public void testWordNotFound() {
        // homework
        MyMiniSearchEngine engine = new MyMiniSearchEngine(new ArrayList<String>(Arrays.asList(myinput)));
        List<Integer> result;
        result = engine.search("A");// single character
        assertEquals(0, result.size());
        result = engine.search("CCC");
        assertEquals(0, result.size());
        result = engine.search("hello no world");// insert 1 word
        assertEquals(0, result.size());
        result = engine.search("hello world fine today today");// append 1 word
        assertEquals(0, result.size());
        result = engine.search("hello www fine today");// replace 1 word
        assertEquals(0, result.size());
    }

    @Test
    public void testTricky() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(new ArrayList<String>(Arrays.asList(myinput)));
        List<Integer> result;
        result = engine.search("");// empty word
        assertEquals(0, result.size());
    }

    String[] myinput = new String[]{
            "AA BB CC heLLo worlD Fine Today DD EE FF",
            "AA bb heLLo worlD cc DD Fine Today EE FF",// split by other
            "AA today hello world fine BB CC dd EE FF",// not in order
            "AA BB CC hello world fine todayT DD ee FF",// today -> rodayT
            "AA BB CC DD EE ff world fine today",// miss 1 word
            "heLLo worlD Fine Today heLLo worlD Fine today Aa BB CC DD EE FF",// duplicate part
            "AA Bb heLLo worlD Fine Today CC heLLo worlD Fine Today DD heLLo worlD Fine Today EE FF",// repeat 3 time
    };

}