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

import net.harawata.appdirs.AppDirs;

public class MacOSXAppDirs extends AppDirs {
  public MacOSXAppDirs() {
    super();
  }

  public String getUserDataDir(String appName, String appVersion,
      String appAuthor, boolean roaming) {
    return buildPath(home(), "/Library/Application Support", appName,
        appVersion);
  }

  public String getUserConfigDir(String appName, String appVersion,
      String appAuthor, boolean roaming) {
    return buildPath(home(), "/Library/Preferences", appName, appVersion);
  }

  public String getUserCacheDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath(home(), "/Library/Caches", appName, appVersion);
  }

  public String getSiteDataDir(String appName, String appVersion,
      String appAuthor, boolean multiPath) {
    return buildPath("/Library/Application Support", appName, appVersion);
  }

  public String getSiteConfigDir(String appName, String appVersion,
      String appAuthor, boolean multiPath) {
    return buildPath("/Library/Preferences", appName, appVersion);
  }

  public String getUserLogDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath(home(), "/Library/Logs", appName, appVersion);
  }

  @Override
  public String getSharedDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath("/Users/Shared/Library/Application Support", appName,
        appVersion);
  }
}
