package dev.vince.maven.manager.cache;

public final class MavenCache {
    private final MavenSearchCache searchCache;
    private final MavenArtifactCache artifactCache;

    public MavenCache() {
        this.searchCache = new MavenSearchCache();
        this.artifactCache = new MavenArtifactCache();
    }

    public MavenSearchCache getSearchCache() {
        return searchCache;
    }

    public MavenArtifactCache getArtifactCache() {
        return artifactCache;
    }
}
