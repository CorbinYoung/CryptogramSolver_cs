package dm;

import dm.storage.DataStorage;
import dm.storage.Dictionary;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

/**
 * This class is in charge of reading in data from different files
 *
 * @author Corbin Young
 */
public final class DataReader {
    
    /**
     * This method reads an encrypted message from a file
     *
     * @param dataFile name of the file containing encrypted message
     */
    public static void readData(final String dataFile) {
        try {
            final Path path = FileSystems.getDefault().getPath(dataFile);
            Files.lines(path).collect(Collectors.toList()).forEach(line -> DataStorage.getInstance().addData(line.toUpperCase()));
        }
        catch(IOException ioe) {
            System.out.println("Error reading file");
        }
    }
    
    /**
     * This method read in all of the english words from a file
     */
    public static void readWords() {
        try {
            final Path path = FileSystems.getDefault().getPath("words.txt");
            Files.lines(path).collect(Collectors.toList()).forEach(line -> Dictionary.getInstance().addWord(Punctuation.removeBadPunctuation(line.toUpperCase())));
        }
        catch(IOException ioe) {
            System.out.println("Error reading file");
        }
    }
}
