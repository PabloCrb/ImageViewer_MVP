package software.ulpgc.ImageViewer;

public interface ImageDisplay {
    void draw(String id, int offset);
    int getWidth();
    void clear();
    void on(Displacement displacement);
    void on(Releasement release);

    interface Displacement {
        void offset(int offset);
        Displacement Null = offset -> {};
    }
    interface Releasement {
        void offset(int offset);
        Releasement Null = offset -> {};
    }
}