package dev.vince.maven.manager.cache;

/**
 * The MavenCache class implements both the MavenSearchCache and the MavenArtifactCache classes.
 * This class is used internally by the central-maven library.
 * There is no need to implement this class.
 * This class is responsible for caching search results and artifacts to avoid making multiple requests to the maven repository for the same artifact.
 * This class is a singleton in the MavenArtifact class.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MavenCache {
    private final MavenSearchCache searchCache;
    private final MavenArtifactCache artifactCache;

    /**
     * Constructs a new MavenCache.
     * @since 1.0.0
     */
    public MavenCache() {
        this.searchCache = new MavenSearchCache();
        this.artifactCache = new MavenArtifactCache();
    }

    /**
     * Gets the MavenSearchCache.
     * @return the MavenSearchCache
     * @since 1.0.0
     */
    public MavenSearchCache getSearchCache() {
        return searchCache;
    }

    /**
     * Gets the MavenArtifactCache.
     * @return the MavenArtifactCache
     * @since 1.0.0
     */
    public MavenArtifactCache getArtifactCache() {
        return artifactCache;
    }
}
