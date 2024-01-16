package software.ulpgc.ImageViewer;

public interface Image {
    String name();
    Image previous();
    Image next();
}