package software.ulpgc.ImageViewer;

import software.ulpgc.ImageViewer.ImageDisplay.*;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((Displacement) this::displace);
        this.display.on((Releasement) this::release);
    }

    private void displace(int offset) {
        display.clear();
        display.draw(image.name(), offset);
        if (offset > 0) display.draw(image.previous().name(), offset - display.getWidth());
        else display.draw(image.next().name(), display.getWidth() + offset);
    }

    private void release(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2)
            image = offset > 0 ? image.previous() : image.next();
        redraw();
    }

    public void show(Image image) {
        this.image = image;
        redraw();
    }

    private void redraw() {
        this.display.clear();
        this.display.draw(image.name(), 0);
    }
}