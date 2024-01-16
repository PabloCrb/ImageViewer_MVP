package software.ulpgc.ImageViewer;

public class FileImageLoader implements ImageLoader {
    private final static String fileRoot = "C:\\Users\\Pablo\\IdeaProjects\\Images\\";
    private final static String extJPEG = ".JPEG";
    private final static String extJPG = ".JPG";

    private final String[] imageNames = new String[] {
            fileRoot + "CapybaraSameSize" + extJPG,
            fileRoot + "VerticalImageDog" + extJPG,
            fileRoot + "HorizontalImageCat" + extJPEG,
            fileRoot + "SmallImageSwan" + extJPEG,
            fileRoot + "BigImageGibon" + extJPEG};

    @Override
    public Image load() {
        return createImageAtIndex(0);
    }

    private Image createImageAtIndex(int index) {
        return new Image() {
            @Override
            public String name() {
                return imageNames[index];
            }

            @Override
            public Image next() {
                return createImageAtIndex((index + 1) % imageNames.length);
            }

            @Override
            public Image previous() {
                return createImageAtIndex(index > 0 ? index -1 : imageNames.length-1);
            }
        };
    }
}