package utilities;

import pages.constants.GeneralConstants;
import pages.constants.GeneralPaths;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {

    private Path getConfigurationFilePath(String configurationFileName) {
        return Paths.get(System.getProperty(GeneralConstants.USER_DIR),
                GeneralPaths.CONFIGURATION_DIR_PATH,
                configurationFileName);
    }

    public Properties loadPropertiesFromFile(String fileName) {
        Properties properties = new Properties();
        Path filePath = getConfigurationFilePath(fileName);

        if (!Files.exists(filePath)) {
            System.err.println("Error: File '" + fileName + "' does not exist.");
            return properties; // return empty properties
        }

        try (InputStream input = Files.newInputStream(filePath)) {
            // Load properties from the input stream
            properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Error loading properties from file: " + e.getMessage());
        }
        return properties;
    }


}
