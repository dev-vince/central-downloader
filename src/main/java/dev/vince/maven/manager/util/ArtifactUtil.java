package dev.vince.maven.manager.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

/**
 * The ArtifactUtil class contains methods to help with the management of artifacts.
 * This class is a utility class and should not be initialized.
 * This class is used internally by the central-maven library.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ArtifactUtil {
    private ArtifactUtil() {}

    public static final String MAVEN_REPOSTORY = "https://repo1.maven.org/maven2";

    /**
     * Converts a groupId to a path format (ex: com.vince -> com/vince)
     * @param groupId The groupId to convert
     * @return The converted groupId
     * @since 1.0.0
     */
    public static String convertGroupIdToPath(final String groupId) {
        return groupId.replace(".", "/");
    }

    /**
     * Generates the path to the artifact from the MavenArtifactBean and the version provided.
     * @param data the MavenArtifactBean of the artifact to generate the path for
     * @param version the target version of the artifact
     * @return the url path to the artifact
     * @since 1.0.0
     */
    public static String generatePathFromData(final MavenArtifactBean data, final String version) {
        return MAVEN_REPOSTORY + "/" + convertGroupIdToPath(data.getGroupId()) + "/" + data.getArtifactId() + "/" + version + "/" + data.getArtifactId() + "-" + version + data.getType().getExtension();
    }

    /**
     * Gets all the metadata for a given maven artifact and returns it as a Document.
     * @param groupId the groupId of the artifact
     * @param artifactId the artifactId of the artifact
     * @return the metadata of the artifact as a Document
     * @since 1.0.0
     */
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

    /**
     * Sends a GET request to the url provided and returns the response as a byte array.
     * @param data the MavenArtifactBean of the artifact to get the data for
     * @return the data of the artifact as a byte array
     */
    public static byte[] getArtifactData(final MavenArtifactBean data){
        final HttpResponse<byte[]> response = Unirest.get(data.getVersion().getRelativePath()).asBytes();

        return response.getBody();
    }

    /**
     * FOrmats the artifact key for the artifact provided.
     * @param groupId the groupId of the artifact
     * @param artifactId the artifactId of the artifact
     * @param version the version of the artifact
     * @return the formatted artifact key
     * @since 1.0.0
     */
    public static String formatArtifactKey(final String groupId, final String artifactId, final String version) {
        return groupId + ":" + artifactId + ":" + version;
    }
}
