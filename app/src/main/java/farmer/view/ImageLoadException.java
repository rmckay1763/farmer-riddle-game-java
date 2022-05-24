package farmer.view;

/**
 * Thrown to indicate a failed attempt to load a image resource file.
 */
public class ImageLoadException extends RuntimeException {

    /**
     * Default constructor.
     */
    public ImageLoadException() {
        super();
    }

    /**
     * Overloaded constructor.
     * @param message Description of the error that occurred.
     */
    public ImageLoadException(String message) {
        super(message);
    }
}
