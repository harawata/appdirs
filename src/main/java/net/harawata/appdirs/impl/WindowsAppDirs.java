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

public class WindowsAppDirs extends AppDirs {
  private WindowsFolderResolver folderResolver;

  public enum FolderId {
    APPDATA, LOCAL_APPDATA, COMMON_APPDATA;
  }

  public WindowsAppDirs(WindowsFolderResolver folderResolver) {
    super();
    this.folderResolver = folderResolver;
  }

  public String getUserDataDir(String appName, String appVersion,
      String appAuthor, boolean roaming) {
    String dir = roaming ? getAppData() : getLocalAppData();
    return buildPath(dir, appAuthor, appName, appVersion);
  }

  public String getUserConfigDir(String appName, String appVersion,
      String appAuthor, boolean roaming) {
    return getUserDataDir(appName, appVersion, appAuthor, roaming);
  }

  public String getUserCacheDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath(getLocalAppData(), appAuthor, appName, "\\Cache",
        appVersion);
  }

  public String getSiteDataDir(String appName, String appVersion,
      String appAuthor, boolean multiPath) {
    return buildPath(getCommonAppData(), appAuthor, appName, appVersion);
  }

  public String getSiteConfigDir(String appName, String appVersion,
      String appAuthor, boolean multiPath) {
    return getSiteDataDir(appName, appVersion, appAuthor, multiPath);
  }

  public String getUserLogDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath(getLocalAppData(), appAuthor, appName, "\\Logs",
        appVersion);
  }

  @Override
  public String getSharedDir(String appName, String appVersion,
      String appAuthor) {
    return buildPath(getCommonAppData(), appAuthor, appName, appVersion);
  }

  protected String getAppData() {
    return folderResolver.resolveFolder(FolderId.APPDATA);
  }

  protected String getLocalAppData() {
    return folderResolver.resolveFolder(FolderId.LOCAL_APPDATA);
  }

  protected String getCommonAppData() {
    return folderResolver.resolveFolder(FolderId.COMMON_APPDATA);
  }
}
