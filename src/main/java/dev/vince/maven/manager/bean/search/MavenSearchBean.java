package dev.vince.maven.manager.bean.search;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.bean.artifact.MavenVersionBean;
import lombok.Data;

@Data
public final class MavenSearchBean {
    MavenArtifactBean artifact;
    MavenVersionBean version;
    byte[] responseData;
}
