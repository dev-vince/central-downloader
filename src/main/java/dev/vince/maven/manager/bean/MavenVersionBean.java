package dev.vince.maven.manager.bean;

import lombok.Data;

@Data
public class MavenVersionBean {
    MavenVersionBean parent;
    String version;
    String relativePath;
}
