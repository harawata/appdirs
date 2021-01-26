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

import com.sun.jna.platform.win32.Guid.GUID;
import com.sun.jna.platform.win32.KnownFolders;
import com.sun.jna.platform.win32.Shell32Util;
import com.sun.jna.platform.win32.ShlObj;
import com.sun.jna.platform.win32.Win32Exception;

import net.harawata.appdirs.AppDirsException;
import net.harawata.appdirs.impl.WindowsAppDirs.FolderId;

public class ShellFolderResolver implements WindowsFolderResolver {

  public String resolveFolder(FolderId folderId) {
    try {
      return Shell32Util.getKnownFolderPath(convertFolderIdToGuid(folderId));
    } catch (Win32Exception e) {
      throw new AppDirsException(
          "SHGetKnownFolderPath returns an error: " + e.getErrorCode());
    } catch (UnsatisfiedLinkError e) {
      // Fallback for pre-vista OSes. #5
      try {
        int folder = convertFolderIdToCsidl(folderId);
        return Shell32Util.getFolderPath(folder);
      } catch (Win32Exception e2) {
        throw new AppDirsException(
            "SHGetFolderPath returns an error: " + e2.getErrorCode());
      }
    }
  }

  private GUID convertFolderIdToGuid(FolderId folderId) {
    switch (folderId) {
    case APPDATA:
      return KnownFolders.FOLDERID_RoamingAppData;
    case LOCAL_APPDATA:
      return KnownFolders.FOLDERID_LocalAppData;
    case COMMON_APPDATA:
      return KnownFolders.FOLDERID_ProgramData;
    default:
      throw new AppDirsException(
          "Unknown folder ID " + folderId + " was specified.");
    }
  }

  protected int convertFolderIdToCsidl(FolderId folderId) {
    switch (folderId) {
    case APPDATA:
      return ShlObj.CSIDL_APPDATA;
    case LOCAL_APPDATA:
      return ShlObj.CSIDL_LOCAL_APPDATA;
    case COMMON_APPDATA:
      return ShlObj.CSIDL_COMMON_APPDATA;
    default:
      throw new AppDirsException(
          "Unknown folder ID " + folderId + " was specified.");
    }
  }
}
