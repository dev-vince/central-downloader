package dev.vince.maven.manager.artifact;

import dev.vince.maven.manager.bean.search.MavenSearchBean;
import dev.vince.maven.manager.bean.artifact.MavenVersionBean;
import dev.vince.maven.manager.cache.MavenCache;
import org.w3c.dom.Document;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.util.ArtifactUtil;

import java.util.ArrayList;

public class MavenArtifact {
    protected static final MavenCache cache = new MavenCache();
    private final MavenArtifactBean data;

    public MavenArtifact(final String groupId, final String artifactId, final MavenArtifactType type) {
        final Document mavenMetadata = ArtifactUtil.getMavenMetadata(groupId, artifactId);
        final String latestVersion = mavenMetadata.getElementsByTagName("latest").item(0).getTextContent();

        if (cache.getArtifactCache().contains(groupId, artifactId, latestVersion))
            this.data = cache.getArtifactCache().get(groupId, artifactId, latestVersion);
        else
            this.data = createArtifactBean(groupId, artifactId, latestVersion, type, mavenMetadata);
    }

    public MavenArtifact(final String groupId, final String artifactId, final String version, final MavenArtifactType type) {
        final Document mavenMetadata = ArtifactUtil.getMavenMetadata(groupId, artifactId);

        if (cache.getArtifactCache().contains(groupId, artifactId, version))
            this.data = cache.getArtifactCache().get(groupId, artifactId, version);
        else
            this.data = createArtifactBean(groupId, artifactId, version, type, mavenMetadata);
    }

    public MavenArtifactBean getData() {
        return data;
    }

    public MavenSearchBean searchMaven() {
        if (cache.getSearchCache().contains(this.data)) {
            return cache.getSearchCache().get(this.data).getSearch();
        }

        final MavenSearchBean searchBean = new MavenSearchBean();

        searchBean.setArtifact(this.data);
        searchBean.setVersion(this.data.getVersion());
        searchBean.setResponseData(ArtifactUtil.getArtifactData(this.data));

        cache.getSearchCache().add(searchBean);
        return searchBean;
    }

    private void addVersionsToBean(final MavenArtifactBean bean, final Document document) {
        for (int i = 0; i < document.getElementsByTagName("version").getLength(); i++) {
            final MavenVersionBean versionBean = new MavenVersionBean();
            final String version = document.getElementsByTagName("version").item(i).getTextContent();

            versionBean.setVersion(version);
            versionBean.setRelativePath(ArtifactUtil.generatePathFromData(bean, version));

            bean.getVersions().add(versionBean);
        }
    }

    private MavenArtifactBean createArtifactBean(final String groupId, final String artifactId, final String version, final MavenArtifactType type, final Document mavenMetadata) {
        final MavenArtifactBean bean = new MavenArtifactBean();
        bean.setGroupId(groupId);
        bean.setArtifactId(artifactId);
        bean.setType(type);

        final MavenVersionBean versionBean = new MavenVersionBean();
        versionBean.setVersion(version);
        versionBean.setRelativePath(ArtifactUtil.generatePathFromData(bean, version));

        bean.setVersion(versionBean);
        bean.setVersions(new ArrayList<>());

        this.addVersionsToBean(bean, mavenMetadata);
        cache.getArtifactCache().add(bean);
        return bean;
    }
}
