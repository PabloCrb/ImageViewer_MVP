package software.ulpgc.ImageViewer.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import software.ulpgc.ImageViewer.ImageDisplay;
import static javax.imageio.ImageIO.*;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Displacement displacement = Displacement.Null;
    private Releasement releasement = Releasement.Null;
    private int coordX;
    private final List<ImageInfo> imageInfo = new ArrayList<>();

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                coordX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                releasement.offset(e.getX() - coordX);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                displacement.offset(e.getX() - coordX);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public void draw(String id, int offset) {
        imageInfo.add(new ImageInfo(id, offset));
        repaint();
    }

    @Override
    public void clear() {
        imageInfo.clear();
    }

    @Override
    public void paint(Graphics g) {
        for (ImageInfo info : imageInfo) {
            try {
                BufferedImage originalImage = read(new File(info.id));
                BufferedImage resizedImage = resizeImage(originalImage, 1200, 800);
                g.drawImage(resizedImage, info.offset, 0, this);
            } catch (IOException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image resultingImage = originalImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        resizedImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return resizedImage;
    }

    @Override
    public void on(Displacement displacement) {
        this.displacement = displacement != null ? displacement : Displacement.Null;
    }

    @Override
    public void on(Releasement released) {
        this.releasement = releasement != null ? released : Releasement.Null;
    }

    private record ImageInfo(String id, int offset) {
    }
}