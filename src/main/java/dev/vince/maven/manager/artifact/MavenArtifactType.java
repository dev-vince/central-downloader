package dev.vince.maven.manager.artifact;

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
