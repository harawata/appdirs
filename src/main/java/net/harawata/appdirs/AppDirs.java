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

package net.harawata.appdirs;

public abstract class AppDirs {
  public String getUserDataDir(String appName, String appVersion,
      String appAuthor) {
    return getUserDataDir(appName, appVersion, appAuthor, false);
  }

  public abstract String getUserDataDir(String appName, String appVersion,
      String appAuthor, boolean roaming);

  public String getUserConfigDir(String appName, String appVersion,
      String appAuthor) {
    return getUserConfigDir(appName, appVersion, appAuthor, false);
  }

  public abstract String getUserConfigDir(String appName, String appVersion,
      String appAuthor, boolean roaming);

  public abstract String getUserCacheDir(String appName, String appVersion,
      String appAuthor);

  public String getSiteDataDir(String appName, String appVersion,
      String appAuthor) {
    return getSiteDataDir(appName, appVersion, appAuthor, false);
  }

  public abstract String getSiteDataDir(String appName, String appVersion,
      String appAuthor, boolean multiPath);

  public String getSiteConfigDir(String appName, String appVersion,
      String appAuthor) {
    return getSiteConfigDir(appName, appVersion, appAuthor, false);
  }

  public abstract String getSiteConfigDir(String appName, String appVersion,
      String appAuthor, boolean multiPath);

  public abstract String getUserLogDir(String appName, String appVersion,
      String appAuthor);

  public abstract String getSharedDir(String appName, String appVersion,
      String appAuthor);

  protected String home() {
    return System.getProperty("user.home");
  }

  protected String buildPath(String... elems) {
    String separator = System.getProperty("file.separator");
    StringBuilder buffer = new StringBuilder();
    String lastElem = null;
    for (String elem : elems) {
      if (elem == null) {
        continue;
      }

      if (lastElem == null) {
        buffer.append(elem);
      } else if (lastElem.endsWith(separator)) {
        buffer.append(elem.startsWith(separator) ? elem.substring(1) : elem);
      } else {
        if (!elem.startsWith(separator)) {
          buffer.append(separator);
        }
        buffer.append(elem);
      }
      lastElem = elem;
    }
    return buffer.toString();
  }

  protected String joinPaths(String... paths) {
    String separator = System.getProperty("path.separator");
    StringBuilder buffer = new StringBuilder();
    for (String path : paths) {
      if (path == null) {
        continue;
      }

      if (buffer.length() > 0) {
        buffer.append(separator);
      }
      buffer.append(path);
    }
    return buffer.toString();
  }

  protected String[] splitPaths(String paths) {
    String separator = System.getProperty("path.separator");
    return paths.split(separator);
  }
}
