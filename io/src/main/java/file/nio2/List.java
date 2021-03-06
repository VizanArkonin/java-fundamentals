package file.nio2;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class List {

  public static void main(String[] args) throws Exception {
    Path dir = Paths.get("/Users/rein");

    try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
      ds.forEach(p -> System.out.println(p));
    }

    System.out.println("Only dirs:");
    try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, Files::isDirectory)) {
      ds.forEach(p -> System.out.println(p));
    }

    System.out.println("Hidden");
    try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir, ".*")) {
      ds.forEach(p -> System.out.println(p));
    }

    System.out.println("Stream 1");
    try (Stream<Path> s = Files.list(dir)) {
      s.forEach(p -> System.out.println(p));
    }

    System.out.println("Stream 2");
    try (Stream<Path> s = Files.list(dir)) {
      s.filter(Files::isDirectory).forEach(p -> System.out.println(p));
    }
  }

}
