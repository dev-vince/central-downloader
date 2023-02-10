package dev.vince.maven.manager.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import dev.vince.maven.manager.bean.MavenArtifactBean;

public final class ArtifactParser {
    private ArtifactParser() {}

    public static final String MAVEN_REPOSTORY = "https://repo1.maven.org/maven2";

    public static String convertGroupIdToPath(final String groupId) {
        return groupId.replace(".", "/");
    }

    public static String generatePathFromData(final MavenArtifactBean data){
        return MAVEN_REPOSTORY + "/" + convertGroupIdToPath(data.getGroupId()) + "/" + data.getArtifactId() + "/" + data.getVersion() + "/" + data.getArtifactId() + "-" + data.getVersion() + data.getType().getExtension();
    }

    public static Document getMavenMetadata(final MavenArtifactBean data){    
        try {
            final String mavenUrl = MAVEN_REPOSTORY + "/" + convertGroupIdToPath(data.getGroupId()) + "/" + data.getArtifactId() + "/" + "maven-metadata.xml";
            System.out.println(mavenUrl);

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();

            //final HttpResponse<String> response = Unirest.get(mavenUrl).asString();

            //final ByteArrayInputStream input = new ByteArrayInputStream(response.getBody().getBytes(StandardCharsets.UTF_8));
            final Document document = builder.parse(mavenUrl);
            
            document.getDocumentElement().normalize();
            return document;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
