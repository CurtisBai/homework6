
import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] myinput = new String[]{
            "AA BB CC heLLo worlD Fine Today DD EE FF",
            "AA bb heLLo worlD cc DD Fine Today EE FF",// split by other
            "AA today hello world fine BB CC dd EE FF",// not in order
            "AA BB CC hello world fine todayT DD ee FF",// today -> rodayT
            "AA BB CC DD EE ff world fine today",// miss 1 word
            "heLLo worlD Fine Today heLLo worlD Fine today Aa BB CC DD EE FF",// duplicate part
            "AA Bb heLLo worlD Fine Today CC heLLo worlD Fine Today DD heLLo worlD Fine Today EE FF",// repeat 3 time
        };

        MyMiniSearchEngine engine = new MyMiniSearchEngine(new ArrayList<String>(Arrays.asList(myinput)));
        List<Integer> result = engine.search("hello world fine today");
        System.out.println(result);

    }

}
