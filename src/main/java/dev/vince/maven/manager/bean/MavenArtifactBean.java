package dev.vince.maven.manager.bean;

import java.util.ArrayList;

import dev.vince.maven.manager.artifact.MavenArtifactType;
import lombok.Data;

@Data
public class MavenArtifactBean {
    String groupId;
    String artifactId;
    String version;
    MavenArtifactType type;
    ArrayList<MavenVersionBean> versions;
}
