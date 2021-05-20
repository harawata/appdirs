AppDirs
=======

## Overview

__AppDirs__ is a small java library which provides a path to the platform dependent special folder/directory. 

For example, here are the common paths of the folder/directory that is used to store application specific user data on each platform.

On Mac OS X : ```/Users/<Account>/Library/Application Support/<AppName>```  
On Windows XP : ```C:\Documents and Settings\<Account>\Application Data\Local Settings\<AppAuthor>\<AppName>```  
On Windows 7 : ```C:\Users\<Account>\AppData\<AppAuthor>\<AppName>```  
On Unix/Linux : ```/home/<account>/.local/share/<AppName>```  

With __AppDirs__, you can get the path depending on the runtime platform with the following code.

``` java
AppDirs appDirs = AppDirsFactory.getInstance();
appDirs.getUserDataDir("<AppName>", null, "<AppAuthor>");
```
__AppDirs__ is loosely based on [a python module](https://github.com/ActiveState/appdirs) with the same name.  
Please use the issue tracker for bug reports or suggestions.

## Supported directories.

Currently, __AppDirs__ has the following methods.

- getUserDataDir
- getUserConfigDir
- getUserCacheDir
- getUserLogDir
- getSiteDataDir
- getSiteConfigDir
- getSharedDir

Here is a test program and the output on some platforms.

```java
import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

public class AppDirTest {
  public static void main(String[] args) {
    AppDirs appDirs = AppDirsFactory.getInstance();
    System.out.println("User data dir: " + appDirs.getUserDataDir("myapp", "1.2.3", "harawata"));
    System.out.println("User data dir (roaming): "
      + appDirs.getUserDataDir("myapp", "1.2.3", "harawata", true));
    System.out.println("User config dir: "
      + appDirs.getUserConfigDir("myapp", "1.2.3", "harawata"));
    System.out.println("User config dir (roaming): "
      + appDirs.getUserConfigDir("myapp", "1.2.3", "harawata", true));
    System.out.println("User cache dir: "
      + appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));
    System.out.println("User log dir: "
      + appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));
    System.out.println("Site data dir: "
      + appDirs.getSiteDataDir("myapp", "1.2.3", "harawata"));
    System.out.println("Site data dir (multi path): "
      + appDirs.getSiteDataDir("myapp", "1.2.3", "harawata", true));
    System.out.println("Site config dir: "
      + appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata"));
    System.out.println("Site config dir (multi path): "
      + appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
    System.out.println("Shared dir: "
      + appDirs.getSharedDir("myapp", "1.2.3", "harawata"));
  }
}
```

### Output on Mac OS X (username = ave)

```
User data dir: /Users/ave/Library/Application Support/myapp/1.2.3
User data dir (roaming): /Users/ave/Library/Application Support/myapp/1.2.3
User config dir: /Users/ave/Library/Preferences/myapp/1.2.3
User config dir (roaming): /Users/ave/Library/Preferences/myapp/1.2.3
User cache dir: /Users/ave/Library/Caches/myapp/1.2.3
User log dir: /Users/ave/Library/Logs/myapp/1.2.3
Site data dir: /Library/Application Support/myapp/1.2.3
Site data dir (multi path): /Library/Application Support/myapp/1.2.3
Site config dir: /Library/Preferences/myapp/1.2.3
Site config dir (multi path): /Library/Preferences/myapp/1.2.3
Shared dir: /Users/Shared/Library/Application Support/myapp/1.2.3
```
- _appAuthor_ parameter is not used on Mac OS X.
- _roaming_ and _multiPath_ parameters have no effect on Mac OS X.

### Output on Windows 7 (username = ave)
```
User data dir: C:\Users\ave\AppData\Local\harawata\myapp\1.2.3
User data dir (roaming): C:\Users\ave\AppData\Roaming\harawata\myapp\1.2.3
User config dir: C:\Users\ave\AppData\Local\harawata\myapp\1.2.3
User config dir (roaming): C:\Users\ave\AppData\Roaming\harawata\myapp\1.2.3
User cache dir: C:\Users\ave\AppData\Local\harawata\myapp\Cache\1.2.3
User log dir: C:\Users\ave\AppData\Local\harawata\myapp\Logs\1.2.3
Site data dir: C:\ProgramData\harawata\myapp\1.2.3
Site data dir (multi path): C:\ProgramData\harawata\myapp\1.2.3
Site config dir: C:\ProgramData\harawata\myapp\1.2.3
Site config dir (multi path): C:\ProgramData\harawata\myapp\1.2.3
Shared dir: C:\ProgramData\harawata\myapp\1.2.3
```
- Internally calls [SHGetFolderPath](http://msdn.microsoft.com/en-us/library/bb762181%28VS.85%29.aspx) via [Java Native Access (JNA)](https://github.com/twall/jna).
 - Returns CSIDL_LOCAL_APPDATA or CSIDL_APPDATA for user directories.
 - Returns CSIDL_COMMON_APPDATA for site directories.
- _multiPath_ parameter has no effect on Windows.

### Output on Linux (username = ave, with no XDG environment variables defined)
```
User data dir: /home/ave/.local/share/myapp/1.2.3
User data dir (roaming): /home/ave/.local/share/myapp/1.2.3
User config dir: /home/ave/.config/myapp/1.2.3
User config dir (roaming): /home/ave/.config/myapp/1.2.3
User cache dir: /home/ave/.cache/myapp/1.2.3
User log dir: /home/ave/.cache/myapp/logs/1.2.3
Site data dir: /usr/local/share/myapp/1.2.3
Site data dir (multi path): /usr/local/share/myapp/1.2.3:/usr/share/myapp/1.2.3
Site config dir: /etc/xdg/myapp/1.2.3
Site config dir (multi path): /etc/xdg/myapp/1.2.3
Shared dir: /srv/myapp/1.2.3
```

- __AppDirs__ respects [XDG Base Directory Specification](http://standards.freedesktop.org/basedir-spec/basedir-spec-latest.html) if variables are defined.
 - Returns XDG_DATA_HOME for user data directory.
 - Returns XDG_CONFIG_HOME for user config directory.
 - Returns XDG_CACHE_HOME for user cache directory.
 - Returns XDG_DATA_DIRS for site data directory.
 - Returns XDG_CONFIG_DIRS for site config directory.
- _appAuthor_ parameter is not used on Unix/Linux.
- _roaming_ parameter has no effect on Unix/Linux.

## Requirements

__AppDirs__ requires Java SE 6 or later and [Java Native Access (JNA)](https://github.com/twall/jna) as its dependency.

## Quickstart

### With a dependency management tool ([Maven](https://maven.apache.org/), [Gradle](https://gradle.org/), etc.)

1. Choose the latest version on the [MvnRepository](https://mvnrepository.com/artifact/net.harawata/appdirs).
2. From the tabs of dependency management tools, select the one you are using.
3. Copy the snippet and add it to the dependency list of your project file (`pom.xml`, `build.gradle`, etc.).

### Without a dependency management tool

1. Choose the latest version on the [release page](https://github.com/harawata/appdirs/releases).
2. Download the `appdirs-(version).jar` file.
3. You may also need to download [`jna-(version).jar`](https://mvnrepository.com/artifact/net.java.dev.jna/jna) and [`jna-platform-(version).jar`](https://mvnrepository.com/artifact/net.java.dev.jna/jna-platform).
4. Put all .jar files into the classpath of your application.

## Bug report, feature request, question.

Please create an issue on the [tracker](https://github.com/harawata/appdirs/issues).

## License

__AppDirs__ is released under Apache Software License 2.0.
