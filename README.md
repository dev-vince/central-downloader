# central-manager
Central Manager is a Java library that provides a simple way to manage multiple libraries uploaded to the Maven Central Repository and find the location of artifacts on the repository.

## Usage
To use Central Manager, you need to add the following dependency to your project:

```xml
<dependency>
    <groupId>io.github.dev-vince</groupId>
    <artifactId>central-manager</artifactId>
    <version>1.0.0</version>
</dependency>
```

Then, you can use the following code to get the location of an artifact on the Maven Central Repository:

```java
//Gets the latest version of the artifact
final MavenArtifact artifact = new MavenArtifact(groupId, artifactId, MavenArtifactType.JAR);

//Gets a specific version of the artifact
final MavenArtifact artifact = new MavenArtifact(groupId, artifactId, version, MavenArtifactType.JAR);

//Requests the maven central repository to get the location of the artifact
final MavenSearchBean search = artifact.searchMaven();

//Gets all the versions of the artifact
search.getArtifact().getVersions().forEach(version -> {
    System.out.println(version.getVersion());
});

//Gets the version specified in the MavenArtifact object (if no version is specified, it will get the latest version)
final MavenVersionBean version = search.getArtifact().getVersion();
System.out.println("Version: " + version.getVersion());
System.out.println("Relative path: " + version.getRelativePath());
```
As you can see, the MavenArtifact class is very simple to use and allows you to get the location of an artifact on the Maven Central Repository.
Also, you can use the MavenArtifact class to get the location of a specific version of an artifact.
Example:

```java
final MavenArtifact artifact = new MavenArtifact("io.github.dev-vince", "central-manager", "1.0.0", MavenArtifactType.JAR);
final MavenSearchBean search = artifact.searchMaven();
final MavenVersionBean version = search.getArtifact().getVersion();

System.out.println("Version: " + version.getVersion());
System.out.println("Relative path: " + version.getRelativePath());
```

## Maven Central Repository
The Maven Central Repository is a repository that contains all the artifacts uploaded to the Maven Central Repository.
You can find the Maven Central Repository at the following URL: https://repo1.maven.org/maven2/

## MavenArtifactType
The MavenArtifactType enum contains all the types of artifacts that can be uploaded to the Maven Central Repository.
The following types are available:
```java
JAR, POM, SOURCES, JAVADOC
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
This was originally made for a project of mine, but I decided to make it public so that other people can use it. If you have any suggestions, feel free to open an issue and I will try to implement it as soon as possible. Not all of the features of the Maven Central Repository are implemented, but I will try to add them as soon as possible or when requested to enable more features.

## License
[GPL-3.0](https://choosealicense.com/licenses/gpl-3.0/)