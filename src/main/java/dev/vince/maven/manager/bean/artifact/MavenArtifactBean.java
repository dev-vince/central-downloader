package dev.vince.maven.manager.bean.artifact;

import java.util.ArrayList;

import dev.vince.maven.manager.artifact.MavenArtifactType;
import lombok.Data;

/**
 * The MavenArtifactBean class is used to represent a maven artifact.
 * This class is used internally by the central-maven library.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public final class MavenArtifactBean {
    String groupId;
    String artifactId;
    MavenVersionBean version;
    MavenArtifactType type;
    ArrayList<MavenVersionBean> versions;
}
