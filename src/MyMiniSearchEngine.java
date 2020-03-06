
import java.util.*;

public class MyMiniSearchEngine {

    // default solution. OK to change.
    // do not change the signature of index()
    /**
     * Container type</br>
     * String is the words in sentence</br>
     * Integer is the line of words</br>
     * TreeSet<Integer> is the index of the word in current line</br>
     */
    private TreeMap<String, TreeMap<Integer, TreeSet<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {

        indexes = new TreeMap<String, TreeMap<Integer, TreeSet<Integer>>>();
        for (int i = 0; i < texts.size(); i++) {
            // split the sentence into words
            String[] tokens = texts.get(i).toLowerCase().split(" ");
            for (int j = 0; j < tokens.length; j++) {
                // loop through each word in sentence
                // save the line index and the index in sentence
                String word = tokens[j];
                if (!indexes.containsKey(word)) {
                    indexes.put(word, new TreeMap<Integer, TreeSet<Integer>>());
                }

                if (!indexes.get(word).containsKey(i)) {
                    indexes.get(word).put(i, new TreeSet<Integer>());
                }
                indexes.get(word).get(i).add(j);
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        List<Integer> lineList = new ArrayList<>(); // place holder

        keyPhrase = keyPhrase.toLowerCase().trim();
        String[] words = keyPhrase.split("\\s+");

        // (1) find the line contains all these words
        TreeSet<Integer> commonLineSet = new TreeSet<Integer>();

        // collect all the line bumber
        for (String word : indexes.keySet()) {
            commonLineSet.addAll(indexes.get(word).keySet());
        }

        for (String word : words) {
            Set<Integer> lineSet = new TreeSet<Integer>();
            if (indexes.containsKey(word)) {
                lineSet.addAll(indexes.get(word).keySet());
            }
            // make intersection
            commonLineSet.retainAll(lineSet);
        }
        if (words.length > 0) {
            // use a set to store the line number so that remove duplicate
            TreeSet<Integer> result = new TreeSet<Integer>();
            // (2) for each of the line, whether the index is increasing
            for (int lineNumber : commonLineSet) {
                // check this line:

                for (int wordIndex : indexes.get(words[0]).get(lineNumber)) {

                    // if word1 is the n-th word in sentence, 
                    //    word2 is the (n+1)-th word in sentence: 
                    //    word3 is the (n+2)-th word in sentence: 
                    //    ...
                    // then, we find a match
                    boolean flag = true;
                    for (int j = 1; j < words.length; j++) {
                        if (!indexes.get(words[j]).get(lineNumber).contains(wordIndex + j)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        result.add(lineNumber);
                    }
                }
            }
            lineList.addAll(result);
        }

        return lineList;
    }

}
