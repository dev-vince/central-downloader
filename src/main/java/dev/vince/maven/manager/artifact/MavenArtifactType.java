package dev.vince.maven.manager.artifact;

/**
 * The MavenArtifactType enum is used to represent the different types of artifacts.
 * This class is used internally by the central-maven library.
 * There is no need to implement this class.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
public enum MavenArtifactType {
    JAR(".jar"),
    POM(".pom"),
    SOURCES("-sources.jar"),
    JAVADOC("-javadoc.jar");
    
    private final String extension;

    MavenArtifactType(final String extension) {
        this.extension = extension;
    }

    public final String getExtension() {
        return extension;
    }

    @Override
    public final String toString() {
        return extension;
    }
}
