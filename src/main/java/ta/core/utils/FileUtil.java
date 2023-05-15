package ta.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.assertj.core.util.Arrays;

import java.io.File;

@Slf4j
public class FileUtil {

    public static File findFile(String name, String extension) {
        var userDir = System.getProperty("user.dir");
        var fullName = name.concat(".").concat(extension);
        log.info("trying to find <{}.{}> file recursively in <{}>", name, extension, userDir);
        var searchedFile = FileUtils
                .listFiles(new File(userDir), Arrays.array(extension), true)
                .stream()
                .filter(file -> file.getPath().endsWith(fullName))
                .findFirst();
        searchedFile.ifPresent(file ->
                log.info("<{}.{}> file found: <{}>", name, extension, file.getAbsolutePath()));
        return searchedFile.orElseThrow();
    }

}
