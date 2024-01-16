package software.ulpgc.ImageViewer;

import software.ulpgc.ImageViewer.Swing.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay());
        presenter.show(image());
        frame.setVisible(true);
    }

    private static Image image() {
        return new FileImageLoader().load();
    }
}
