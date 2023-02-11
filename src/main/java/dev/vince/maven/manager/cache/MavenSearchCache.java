package dev.vince.maven.manager.cache;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.bean.cache.SearchCacheEntryBean;
import dev.vince.maven.manager.bean.search.MavenSearchBean;
import dev.vince.maven.manager.util.ArtifactUtil;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public final class MavenSearchCache {
    private final HashMap<String, SearchCacheEntryBean> cache = new HashMap<>();

    public void add(final MavenSearchBean search) {
        final SearchCacheEntryBean entry = new SearchCacheEntryBean();
        entry.setSearch(search);
        entry.setBase64Data(Base64.getEncoder().encode(search.getResponseData()));
        cache.put(ArtifactUtil.formatArtifactKey(search.getArtifact().getGroupId(), search.getArtifact().getArtifactId(), search.getVersion().getVersion()), entry);
    }

    public boolean contains(final MavenArtifactBean artifact) {
        return cache.containsKey(ArtifactUtil.formatArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion().getVersion()));
    }

    public SearchCacheEntryBean get(final MavenArtifactBean artifact) {
        final String key = ArtifactUtil.formatArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion().getVersion());
        final SearchCacheEntryBean entry = cache.get(key);

        if (Arrays.equals(entry.getBase64Data(), Base64.getEncoder().encode(entry.getSearch().getResponseData())))
            return entry;
        else
            throw new RuntimeException("The cache entry for " + key + " is corrupted! The data is not the same as the original data!");
    }
}
