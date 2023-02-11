package dev.vince.maven.manager.cache;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.util.ArtifactUtil;

import java.util.HashMap;

public final class MavenArtifactCache {
    private final HashMap<String, MavenArtifactBean> cache = new HashMap<>();

    public void add(final MavenArtifactBean artifact) {
        cache.put(ArtifactUtil.formatArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion().getVersion()), artifact);
    }

    public boolean contains(final String groupId, final String artifactId, final String version) {
        return cache.containsKey(ArtifactUtil.formatArtifactKey(groupId, artifactId, version));
    }

    public MavenArtifactBean get(final String groupId, final String artifactId, final String version) {
        return cache.get(ArtifactUtil.formatArtifactKey(groupId, artifactId, version));
    }
}
