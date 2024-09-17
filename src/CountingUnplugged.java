import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountingUnplugged {
  public static void main(String[] args) throws IOException {
    // Read common words and split by ', ' (to get each word without whitespace or punctuation)
    String[] words = new String(Files.readAllBytes(Path.of("commonWords.txt")))
        .split(", ");

    // Store common words in ArrayList (for contains() method convenience)
    // and transform all words to lowercase.
    ArrayList<String> commonWords = new ArrayList<>(List.of(words));
    commonWords.replaceAll(String::toLowerCase);

    // Read text file and replace all new lines, punctuation, and repeated spaces with a single space.
    String[] file = new String(Files.readAllBytes(Path.of("textOne.txt")))
        .replaceAll("[\n”“?!,.]", " ")
        .replaceAll("([ ]+)", " ").split(" ");

    // Store an ArrayList of Word objects that represents distinct words and their number of occurrences
    ArrayList<Word> wordFrequencies = new ArrayList<>();

    // Loop through words from file and add to distinct words occurrence array
    for (String word : file) {
      // If the case-insensitive word is in the list of common words, skip it.
      if (commonWords.contains(word.toLowerCase())) continue;

      // If our list of case-insensitive distinct words contains the word, then increment the occurrence count.
      if (wordFrequencies.contains(new Word(word.toLowerCase())))
        wordFrequencies.get(wordFrequencies.indexOf(new Word(word.toLowerCase()))).count++;
      else // Otherwise add the lower-case representation of the word to our list
        wordFrequencies.add(new Word(word.toLowerCase()));
    }

    // Sort ArrayList of Word objects, using compareTo function from the Word class
    Collections.sort(wordFrequencies);

    // Print word occurrence array
    System.out.println(wordFrequencies);
  }
}
