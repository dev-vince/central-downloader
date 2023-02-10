package dev.vince.maven.manager;

import dev.vince.maven.manager.artifact.MavenArtifact;
import dev.vince.maven.manager.artifact.MavenArtifactType;

public class MavenManager {
    public static void main(String[] args) {
        final MavenArtifact artifact = new MavenArtifact("io.github.dev-vince", "colorify");
        artifact.searchMaven(MavenArtifactType.JAR);
    }
}
