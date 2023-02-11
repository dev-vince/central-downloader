import dev.vince.maven.manager.artifact.MavenArtifact;
import dev.vince.maven.manager.artifact.MavenArtifactType;
import dev.vince.maven.manager.bean.artifact.MavenVersionBean;
import dev.vince.maven.manager.bean.search.MavenSearchBean;

import java.io.FileOutputStream;
import java.io.IOException;

public class MavenManager {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            final MavenArtifact artifact = new MavenArtifact("io.github.dev-vince", "colorify", MavenArtifactType.JAR);
            final MavenSearchBean search = artifact.searchMaven();

            try {
                System.out.println("Downloading " + search.getArtifact().getArtifactId() + "...");
                final FileOutputStream outputStream = new FileOutputStream("colorify" + Math.random() * 10 + ".jar");

                search.getArtifact().getVersions().forEach(version -> {
                    System.out.println(version.getVersion());
                });

                //Gets the version specified in the MavenArtifact object (if no version is specified, it will get the latest version)
                final MavenVersionBean version = search.getArtifact().getVersion();
                System.out.println("Version: " + version.getVersion());
                System.out.println("Relative path: " + version.getRelativePath());

                outputStream.write(search.getResponseData());
                outputStream.close();
                System.out.println("Downloaded " + search.getArtifact().getArtifactId() + "!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
