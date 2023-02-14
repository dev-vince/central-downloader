package dev.vince.maven.manager.exception;

/**
 * The CacheCorruptedException class is used to throw an exception when the cache is corrupted.
 * This class is used internally by the central-maven library.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public final class CacheCorruptedException extends RuntimeException{
    /**
     * Constructs a new CacheCorruptedException with the specified detail message.
     * @param message the detail message
     * @since 1.0.0
     */
    public CacheCorruptedException(final String message) {
        super(message);
    }
}
