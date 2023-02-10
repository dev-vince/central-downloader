package dev.vince.maven.manager.artifact;

import org.w3c.dom.Document;

import dev.vince.maven.manager.bean.MavenArtifactBean;
import dev.vince.maven.manager.util.ArtifactParser;

public class MavenArtifact {
    private final MavenArtifactBean data;

    public MavenArtifact(final String groupId, final String artifactId){
        this.data = new MavenArtifactBean();
        this.data.setGroupId(groupId);
        this.data.setArtifactId(artifactId);

        final Document mavenMetadata = ArtifactParser.getMavenMetadata(this.data);

        final String latestVersion = mavenMetadata.getElementsByTagName("latest").item(0).getTextContent();
        
        this.data.setVersion(latestVersion);
        System.out.println(latestVersion);
    }

    public MavenArtifact(final String groupId, final String artifactId, final String version){
        this(groupId, artifactId);
        this.data.setVersion(version);
    }


    public MavenArtifactBean getData() {
        return data;
    }

    public MavenArtifactBean searchMaven(final MavenArtifactType type){
        this.data.setType(type);
        System.out.println(ArtifactParser.generatePathFromData(this.data));
        return this.data;
    }

    private final void addVersionsToBean(final MavenArtifactBean bean, final Document document){
        for(Element e : document.getElementsByTagName("version")){
            //TODO: Parse all versions and add them to the bean
        }
    }
}
