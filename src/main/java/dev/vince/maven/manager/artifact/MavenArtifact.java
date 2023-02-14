package dev.vince.maven.manager.artifact;

import java.util.ArrayList;

import org.w3c.dom.Document;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.bean.artifact.MavenVersionBean;
import dev.vince.maven.manager.bean.search.MavenSearchBean;
import dev.vince.maven.manager.cache.MavenCache;
import dev.vince.maven.manager.util.ArtifactUtil;

/**
 * The MavenArtifact class represents a maven artifact on the Maven Central Repository.
 * This class will be responsible for managing the data on the Maven Central Repository for the specified artifact.
 * This class should always be initialized with a groupId and artifactId along with a MavenArtifactType specifying what resource to get from the Maven Central Repository.
 * This class can also be initialized with a version to get a specific version of the artifact.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public class MavenArtifact {
    protected static final MavenCache cache = new MavenCache();
    private final MavenArtifactBean data;

    /**
     * Initializes a new MavenArtifact with the specified groupId, artifactId, and type.
     * This will get the latest version of the artifact from the Maven Central Repository and set the version of the artifact to the latest version.
     * @param groupId the groupId of the artifact
     * @param artifactId the artifactId of the artifact
     * @param type the type of the artifact
     * @since 1.0.0
     */
    public MavenArtifact(final String groupId, final String artifactId, final MavenArtifactType type) {
        final Document mavenMetadata = ArtifactUtil.getMavenMetadata(groupId, artifactId);
        final String latestVersion = mavenMetadata.getElementsByTagName("latest").item(0).getTextContent();

        if (cache.getArtifactCache().contains(groupId, artifactId, latestVersion))
            this.data = cache.getArtifactCache().get(groupId, artifactId, latestVersion);
        else
            this.data = createArtifactBean(groupId, artifactId, latestVersion, type, mavenMetadata);
    }

    /**
     * Initializes a new MavenArtifact with the specified groupId, artifactId, version, and type.
     * This will get the specified version of the artifact from the Maven Central Repository.
     * @param groupId the groupId of the artifact
     * @param artifactId the artifactId of the artifact
     * @param version the version of the artifact
     * @param type the type of the artifact
     * @since 1.0.0
     */
    public MavenArtifact(final String groupId, final String artifactId, final String version, final MavenArtifactType type) {
        final Document mavenMetadata = ArtifactUtil.getMavenMetadata(groupId, artifactId);

        if (cache.getArtifactCache().contains(groupId, artifactId, version))
            this.data = cache.getArtifactCache().get(groupId, artifactId, version);
        else
            this.data = createArtifactBean(groupId, artifactId, version, type, mavenMetadata);
    }

    /**
     * Gets the MavenArtifactBean for this MavenArtifact instance.
     * @return the MavenArtifactBean for this MavenArtifact instance
     * @since 1.0.0
     */
    public MavenArtifactBean getData() {
        return data;
    }

    /**
     * Searches the Maven Central Repository for the specified artifact set in the constructor.
     * @return the MavenSearchBean for the specified artifact
     * @since 1.0.0
     */
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
