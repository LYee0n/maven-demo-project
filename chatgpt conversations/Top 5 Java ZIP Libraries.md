# Top 5 Java ZIP Libraries
**You**: can you tell me top 5 zip java library ?

**ChatGPT**: Sure, here are the top 5 Java libraries for working with ZIP files:

Apache Commons Compress: It is a widely used library for handling various compression formats including ZIP. It provides high-level APIs for compression and decompression and supports reading and writing ZIP files and ZIP-based archive formats like TAR and GZIP.

Java Zip: This is the standard library provided by Java for working with ZIP files. It has basic support for reading and writing ZIP files but it is not as feature-rich as the other libraries.

Zip4j:

**You**: continue

**ChatGPT**: This is a popular library for working with ZIP files in Java. It provides a comprehensive set of features for working with ZIP files, including support for AES encryption, file compression/decompression, and support for reading and writing ZIP files.

JavaArchiveUtils: This library provides a simple and straightforward API for working with ZIP files. It is well-suited for applications that need to extract or create ZIP files, but do not require advanced features like encryption or compression.

Java Unzip: This library is a simple and lightweight library for working with ZIP files. It provides support for reading and writing ZIP files, including support for reading and writing individual entries within the ZIP file.

Note that these libraries can be used in a wide range of applications, from simple file archiving to more complex data processing applications.

**You**: how to use each of them?

**ChatGPT**: Here's a brief overview of how to use each of the 5 libraries mentioned:Apache Commons Compress:

Here's an example of how to read and extract the contents of a ZIP file using Apache Commons Compress:java
```java
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UnzipFileExample {
  public static void main(String[] args) throws IOException, ArchiveException {
    try (FileInputStream fileInputStream = new FileInputStream("example.zip");
         ArchiveInputStream archiveInputStream = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ZIP, fileInputStream)) {
      ZipArchiveEntry entry;
      while ((entry = (ZipArchiveEntry) archiveInputStream.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          try (FileOutputStream fileOutputStream = new FileOutputStream(entry.getName())) {
            IOUtils.copy(archiveInputStream, fileOutputStream);
          }
        }
      }
    }
  }
}
```Java Zip:

Here's an example of how to read and extract the contents of a ZIP file using the Java ```java.util.zip``` package:java
```java
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFileExample {
  public static void main(String[] args) throws IOException {
    try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("example.zip"))) {
      ZipEntry entry;
      while ((entry = zipInputStream.getNextEntry()) != null) {
        if (!entry.isDirectory()) {
          try (FileOutputStream fileOutputStream = new FileOutputStream(entry.getName())) {
            byte[] buffer = new byte[1024];
            int count;
            while ((count = zipInputStream.read(buffer)) != -1) {
              fileOutputStream.write(buffer, 0, count);
            }
          }
        }
      }
    }
  }
}
```Zip4j:

Here's an example of how to extract the contents of a ZIP file using the Zip4j library:java
```java
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class UnzipFileExample {
  public static void main(String[] args) throws ZipException {
    ZipFile zipFile = new ZipFile("example.zip");
    zipFile.extractAll("destination_folder");
  }
}
```JavaArchiveUtils:

Here's an example of how to extract the contents of a ZIP

**You**: continue

**ChatGPT**: file using the JavaArchiveUtils library:java
```java
import com.javasampleapproach.javaunzipping.utils.UnzipUtility;

import java.io.IOException;

public class UnzipFileExample {
  public static void main(String[] args) {
    String zipFilePath = "example.zip";
    String destDirectory = "destination_folder";
    try {
      UnzipUtility.unzip(zipFilePath, destDirectory);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
```Java Unzip:

Here's an example of how to extract the contents of a ZIP file using the Java Unzip library:java
```java
import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;

import java.io.*;

public class UnzipFileExample {
  public static void main(String[] args) throws IOException, RarException {
    Archive archive = new Archive(new File("example.zip"));
    FileHeader fileHeader = archive.nextFileHeader();
    while (fileHeader != null) {
      String fileName = fileHeader.getFileNameString().trim();
      if (!fileHeader.isDirectory()) {
        File out = new File("destination_folder/" + fileName);
        File parent = out.getParentFile();
        if (!parent.exists()) {
          parent.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(out)) {
          archive.extractFile(fileHeader, fos);
        }
      }
      fileHeader = archive.nextFileHeader();
    }
  }
}
```

These are just brief examples of how to use each of the libraries for extracting the contents of a ZIP file. Each library has its own features and methods for working with ZIP files, so I recommend you consult the official documentation for more information and additional examples.

**You**: how about compress example

**ChatGPT**: Sure, here are examples of how to compress a file using each of the five libraries:java.util.zip:java
```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFileExample {
  public static void main(String[] args) {
    String sourceFile = "example.txt";
    String zipFile = "compressed.zip";
    try (FileOutputStream fos = new FileOutputStream(zipFile);
         ZipOutputStream zos = new ZipOutputStream(fos)) {
      ZipEntry entry = new ZipEntry(sourceFile);
      zos.putNextEntry(entry);
      try (FileInputStream fis = new FileInputStream(sourceFile)) {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
          zos.write(buffer, 0, length);
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
```Apache Commons Compress:java
```java
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CompressFileExample {
  public static void main(String[] args) {
    String sourceFile = "example.txt";
    String zipFile = "compressed.zip";
    try (FileOutputStream fos = new FileOutputStream(zipFile);
         ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(fos)) {
      File fileToZip = new File(sourceFile);
      try (FileInputStream fis = new FileInputStream(fileToZip)) {
        zaos.putArchiveEntry(fileToZip);
        IOUtils.copy(fis, zaos);
        zaos.closeArchiveEntry();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
```Zip4j:java
```java
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class CompressFileExample {
  public static void main(String[] args) {
    String sourceFile = "example.txt";
    String zipFile = "compressed.zip";
    try {
      ZipFile zip = new ZipFile(zipFile);
      ZipParameters parameters = new ZipParameters();
      parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
      parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
      zip.addFile(new File(sourceFile), parameters);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
```

**You**: continue

**ChatGPT**: markdown
```markdown
    }
}
```TrueZIP:java
```java
import de.schlichtherle.truezip.file.TFile;
import de.schlichtherle.truezip.file.TFileInputStream;
import de.schlichtherle.truezip.file.TFileOutputStream;
import de.schlichtherle.truezip.zip.ZipOutputStream;

public class CompressFileExample {
  public static void main(String[] args) {
    String sourceFile = "example.txt";
    String zipFile = "compressed.zip";
    try (TFileOutputStream fos = new TFileOutputStream(zipFile);
         ZipOutputStream zos = new ZipOutputStream(fos)) {
      TFile fileToZip = new TFile(sourceFile);
      TFileInputStream fis = new TFileInputStream(fileToZip);
      zos.putNextEntry(fileToZip);
      byte[] buffer = new byte[1024];
      int length;
      while ((length = fis.read(buffer)) > 0) {
        zos.write(buffer, 0, length);
      }
      zos.closeEntry();
      fis.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
```JavaFX:java
```java
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFileExample extends Application {
  @Override
  public void start(Stage primaryStage) {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(primaryStage);
    if (file != null) {
      String sourceFile = file.getAbsolutePath();
      String zipFile = "compressed.zip";
      try (FileOutputStream fos = new FileOutputStream(zipFile);
           ZipOutputStream zos = new ZipOutputStream(fos)) {
        ZipEntry entry = new ZipEntry(sourceFile);
        zos.putNextEntry(entry);
        try (FileInputStream fis = new FileInputStream(sourceFile)) {
          byte[] buffer = new byte[1024];
          int length;
          while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
          }
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
```

These examples demonstrate the basic usage of each library for compressing a file. Keep in mind that there are more advanced features and options available in each library, so it's recommended to consult their documentation for more information.

