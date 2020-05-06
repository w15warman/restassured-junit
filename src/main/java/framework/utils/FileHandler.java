package framework.utils;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class FileHandler {

  public static void downloadLocally(byte[] pdfFile) {
    FileOutputStream fileOutputStream;
    try {
      fileOutputStream = new FileOutputStream("src/test/resources/temp" + "/" + "file001" + ".pdf");
      fileOutputStream.write(pdfFile);
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String extractContent(byte[] pdf) throws IOException {
    try (PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(pdf))) {
      return new PDFTextStripper().getText(pdfDocument);
    }
  }
}
