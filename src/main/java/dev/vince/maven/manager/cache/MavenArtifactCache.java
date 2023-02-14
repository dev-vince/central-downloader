package dev.vince.maven.manager.cache;

import java.util.HashMap;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.util.ArtifactUtil;

/**
 * The MavenArtifactCache class is used to cache artifacts to avoid making multiple requests to the maven repository for the same artifact.
 * This class is used internally by the central-maven library.
 * There is no need to implement this class.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MavenArtifactCache {
    private final HashMap<String, MavenArtifactBean> cache = new HashMap<>();

    /**
     * Adds a MavenArtifactBean to the cache.
     * @param artifact the artifact to add to the cache
     * @since 1.0.0
     */
    public void add(final MavenArtifactBean artifact) {
        cache.put(ArtifactUtil.formatArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion().getVersion()), artifact);
    }

    /**
     * Checks if the cache contains a given artifact.
     * @param groupId the groupId of the artifact
     * @param artifactId the artifactId of the artifact
     * @param version the version of the artifact
     * @return true if the cache contains the artifact, false otherwise
     * @since 1.0.0
     */
    public boolean contains(final String groupId, final String artifactId, final String version) {
        return cache.containsKey(ArtifactUtil.formatArtifactKey(groupId, artifactId, version));
    }

    /**
     * Gets a MavenArtifactBean from the cache.
     * @param groupId the groupId of the artifact
     * @param artifactId the artifactId of the artifact
     * @param version the version of the artifact
     * @return the MavenArtifactBean if it exists in the cache, null otherwise
     * @since 1.0.0
     */
    public MavenArtifactBean get(final String groupId, final String artifactId, final String version) {
        return cache.get(ArtifactUtil.formatArtifactKey(groupId, artifactId, version));
    }
}
