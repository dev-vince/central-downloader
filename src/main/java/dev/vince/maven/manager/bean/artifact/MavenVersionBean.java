package dev.vince.maven.manager.bean.artifact;

import lombok.Data;

/**
 * The MavenVersionBean class is used to represent a maven artifact version.
 * This class is used internally by the central-maven library.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public final class MavenVersionBean {
    String version;
    String relativePath;
}
