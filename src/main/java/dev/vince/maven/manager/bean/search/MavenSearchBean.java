package dev.vince.maven.manager.bean.search;

import dev.vince.maven.manager.bean.artifact.MavenArtifactBean;
import dev.vince.maven.manager.bean.artifact.MavenVersionBean;
import lombok.Data;

/**
 * The MavenSearchBean class is used to represent a maven search.
 * This class is used internally by the central-maven library.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public final class MavenSearchBean {
    MavenArtifactBean artifact;
    MavenVersionBean version;
    byte[] responseData;
}
