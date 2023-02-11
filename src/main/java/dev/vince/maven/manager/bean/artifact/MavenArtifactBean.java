package dev.vince.maven.manager.bean.artifact;

import java.util.ArrayList;

import dev.vince.maven.manager.artifact.MavenArtifactType;
import lombok.Data;

@Data
public final class MavenArtifactBean {
    String groupId;
    String artifactId;
    MavenVersionBean version;
    MavenArtifactType type;
    ArrayList<MavenVersionBean> versions;
}
