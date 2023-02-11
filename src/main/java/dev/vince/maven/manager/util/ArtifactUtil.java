package dev.vince.maven.manager.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;

public final class ArtifactUtil {
    private ArtifactUtil() {}

    public static final String MAVEN_REPOSTORY = "https://repo1.maven.org/maven2";

    public static String convertGroupIdToPath(final String groupId) {
        return groupId.replace(".", "/");
    }

    public static String generatePathFromData(final MavenArtifactBean data, final String version) {
        return MAVEN_REPOSTORY + "/" + convertGroupIdToPath(data.getGroupId()) + "/" + data.getArtifactId() + "/" + version + "/" + data.getArtifactId() + "-" + version + data.getType().getExtension();
    }

    public static Document getMavenMetadata(final String groupId, final String artifactId){
        try {
            final String mavenUrl = MAVEN_REPOSTORY + "/" + convertGroupIdToPath(groupId) + "/" + artifactId + "/" + "maven-metadata.xml";

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();

            final Document document = builder.parse(mavenUrl);
            
            document.getDocumentElement().normalize();
            return document;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getArtifactData(final MavenArtifactBean data){
        final HttpResponse<byte[]> response = Unirest.get(data.getVersion().getRelativePath()).asBytes();

        return response.getBody();
    }

    public static String formatArtifactKey(final String groupId, final String artifactId, final String version) {
        return groupId + ":" + artifactId + ":" + version;
    }
}
