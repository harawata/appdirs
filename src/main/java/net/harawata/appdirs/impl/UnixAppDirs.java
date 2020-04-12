/*-
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.harawata.appdirs.impl;

import java.util.Map;

import net.harawata.appdirs.AppDirs;

public class UnixAppDirs extends AppDirs {
  public static final String XDG_CONFIG_DIRS = "XDG_CONFIG_DIRS";

  public static final String XDG_DATA_DIRS = "XDG_DATA_DIRS";

  public static final String XDG_CACHE_HOME = "XDG_CACHE_HOME";

  public static final String XDG_CONFIG_HOME = "XDG_CONFIG_HOME";

  public static final String XDG_DATA_HOME = "XDG_DATA_HOME";

  protected final Map<String, String> sysEnv;

  public String getUserDataDir(String appName, String appVersion,
      String appAuthor, boolean roaming) {
    String dir = getOrDefault(XDG_DATA_HOME,
        buildPath(home(), "/.local/share"));
    return buildPath(dir, appName, appVersion);
  }

  public String getUserConfigDir(String appName, String appVersion,
      String appAuthor, boolean roaming) {
    String dir = getOrDefault(XDG_CONFIG_HOME, buildPath(home(), "/.config"));
    return buildPath(dir, appName, appVersion);
  }

  public String getUserCacheDir(String appName, String appVersion,
      String appAuthor) {
    String dir = getOrDefault(XDG_CACHE_HOME, buildPath(home(), "/.cache"));
    return buildPath(dir, appName, appVersion);
  }

  public String getSiteDataDir(String appName, String appVersion,
      String appAuthor, boolean multiPath) {
    String xdgDirs = sysEnv.get(XDG_DATA_DIRS);
    if (xdgDirs == null) {
      String primary = buildPath("/usr/local/share", appName, appVersion);
      String secondary = buildPath("/usr/share", appName, appVersion);
      return multiPath ? joinPaths(primary, secondary) : primary;
    }

    String[] xdgDirArr = splitPaths(xdgDirs);
    if (multiPath) {
      return buildMultiPaths(appName, appVersion, xdgDirArr);
    } else {
      return buildPath(xdgDirArr[0], appName, appVersion);
    }
  }

  public String getSiteConfigDir(String appName, String appVersion,
      String appAuthor, boolean multiPath) {
    String xdgDirs = sysEnv.get(XDG_CONFIG_DIRS);
    if (xdgDirs == null) {
      return buildPath("/etc/xdg", appName, appVersion);
    }

    String[] xdgDirArr = splitPaths(xdgDirs);
    if (multiPath) {
      return buildMultiPaths(appName, appVersion, xdgDirArr);
    } else {
      return buildPath(xdgDirArr[0], appName, appVersion);
    }
  }

  protected String buildMultiPaths(String appName, String appVersion,
      String[] xdgDirArr) {
    int dirNum = xdgDirArr.length;
    String[] newDirs = new String[dirNum];
    for (int i = 0; i < dirNum; i++) {
      newDirs[i] = buildPath(xdgDirArr[i], appName, appVersion);
    }
    return joinPaths(newDirs);
  }

  public String getUserLogDir(String appName, String appVersion,
      String appAuthor) {
    String dir = getOrDefault(XDG_CACHE_HOME, buildPath(home(), "/.cache"));
    return buildPath(dir, appName, "/logs", appVersion);
  }

  @Override
  public String getSharedDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath("/srv", appName, appVersion);
  }

  public String getOrDefault(String key, String def) {
    String val = sysEnv.get(key);
    return val == null ? def : val;
  }

  public UnixAppDirs(Map<String, String> sysEnv) {
    super();
    this.sysEnv = sysEnv;
  }

  public UnixAppDirs() {
    super();
    this.sysEnv = System.getenv();
  }
}
