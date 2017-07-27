# gpodder directory API

[![Maven Central badge](https://maven-badges.herokuapp.com/maven-central/be.ceau/gpodder-directory-api/badge.svg)](https://mvnrepository.com/artifact/be.ceau/gpodder-directory-api)  [![Javadocs](https://javadoc.io/badge/be.ceau/gpodder-directory-api.svg)](https://javadoc.io/doc/be.ceau/gpodder-directory-api)  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

Java library implementing directory API calls for [gpodder.net](https://gpodder.net/)

Supported calls:

  * Retrieve Top Tags
  * Retrieve Podcasts for Tag
  * Podcast Toplist
  * Podcast Search
 
These calls are public content and require no authentication or API key.

### Usage

```Java
List<Podcast> podcasts = new DirectoryAPI().search("keyword");
```

### Maven Central
Include this project directly from Maven Central
```XML
<groupId>be.ceau</groupId>
<artifactId>gpodder-directory-api</artifactId>
<version>${project.version}</version>
```

### GnuPG public key
Verify signature files with my [GnuPG public key](https://www.ceau.be/pubkey.gpg).

### License
Licensed under [the Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.txt).
