package dev.vince.maven.manager.cache;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.bean.cache.SearchCacheEntryBean;
import dev.vince.maven.manager.bean.search.MavenSearchBean;
import dev.vince.maven.manager.exception.CacheCorruptedException;
import dev.vince.maven.manager.util.ArtifactUtil;

/**
 * The MavenSearchCache class is used to cache search results to avoid making multiple requests to the maven repository for the same search.
 * This class is used internally by the central-maven library.
 * There is no need to implement this class.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public final class MavenSearchCache {
    private final HashMap<String, SearchCacheEntryBean> cache = new HashMap<>();

    /**
     * Adds a MavenSearchBean to the cache.
     * @param search the MavenSearchBean to add to the cache
     * @since 1.0.0
     */
    public void add(final MavenSearchBean search) {
        final SearchCacheEntryBean entry = new SearchCacheEntryBean();
        entry.setSearch(search);
        entry.setBase64Data(Base64.getEncoder().encode(search.getResponseData()));
        cache.put(ArtifactUtil.formatArtifactKey(search.getArtifact().getGroupId(), search.getArtifact().getArtifactId(), search.getVersion().getVersion()), entry);
    }

    /**
     * Checks if the cache contains a given artifact.
     * @param artifact the artifact to check against
     * @return true if the cache contains the artifact, false otherwise
     * @since 1.0.0
     */
    public boolean contains(final MavenArtifactBean artifact) {
        return cache.containsKey(ArtifactUtil.formatArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion().getVersion()));
    }
    
    /**
     * Gets a MavenSearchBean from the cache and checks if the data is the same as the original data (to check if the cache is corrupted or modified before returning the data).
     * @param artifact the artifact to get the search from
     * @return the MavenSearchBean if it exists in the cache, null otherwise
     * @throws CacheCorruptedException if the cache is corrupted
     * @since 1.0.0
     */
    public SearchCacheEntryBean get(final MavenArtifactBean artifact) {
        final String key = ArtifactUtil.formatArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion().getVersion());
        final SearchCacheEntryBean entry = cache.get(key);

        if (Arrays.equals(entry.getBase64Data(), Base64.getEncoder().encode(entry.getSearch().getResponseData())))
            return entry;
        else
            throw new CacheCorruptedException("The cache entry for " + key + " is corrupted! The data is not the same as the original data!");
    }
}
