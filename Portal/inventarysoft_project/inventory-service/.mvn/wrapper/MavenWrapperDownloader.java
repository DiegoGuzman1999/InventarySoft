/*
 * Lightweight Maven Wrapper downloader.
 *
 * Downloads the wrapper jar defined in .mvn/wrapper/maven-wrapper.properties
 * when it is missing. This lets the project work even if the jar is not committed.
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class MavenWrapperDownloader {
  private static final String WRAPPER_PROPERTIES_PATH =
      ".mvn/wrapper/maven-wrapper.properties";
  private static final String WRAPPER_URL_PROPERTY = "wrapperUrl";
  private static final String DEFAULT_WRAPPER_URL =
      "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar";

  public static void main(String[] args) {
    try {
      String baseDir = args != null && args.length > 0 ? args[0] : ".";
      Path basePath = new File(baseDir).toPath();
      Path wrapperJarPath = basePath.resolve(".mvn/wrapper/maven-wrapper.jar");
      if (Files.exists(wrapperJarPath)) return;

      Properties props = new Properties();
      Path propsPath = basePath.resolve(WRAPPER_PROPERTIES_PATH);
      if (Files.exists(propsPath)) {
        try (FileInputStream fis = new FileInputStream(propsPath.toFile())) {
          props.load(fis);
        }
      }
      String url = props.getProperty(WRAPPER_URL_PROPERTY, DEFAULT_WRAPPER_URL);

      Files.createDirectories(wrapperJarPath.getParent());
      try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
           FileOutputStream out = new FileOutputStream(wrapperJarPath.toFile())) {
        byte[] dataBuffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = in.read(dataBuffer, 0, 8192)) != -1) {
          out.write(dataBuffer, 0, bytesRead);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}

