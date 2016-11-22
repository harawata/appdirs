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

import net.harawata.appdirs.AppDirsException;
import net.harawata.appdirs.impl.WindowsAppDirs.FolderId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.ShlObj;
import com.sun.jna.platform.win32.W32Errors;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT.HRESULT;

public class ShellFolderResolver implements WindowsFolderResolver {
  private static final Logger logger = LoggerFactory
      .getLogger(ShellFolderResolver.class);

  public String resolveFolder(FolderId folderId) {
    int folder = convertFolderId(folderId);

    char[] pszPath = new char[WinDef.MAX_PATH];
    HRESULT result = Shell32.INSTANCE.SHGetFolderPath(null, folder, null, null,
        pszPath);
    if (W32Errors.S_OK.equals(result)) {
      return Native.toString(pszPath);
    }

    logger.error("SHGetFolderPath returns an error: {}", result.intValue());
    throw new AppDirsException(
        "SHGetFolderPath returns an error: " + result.intValue());
  }

  protected int convertFolderId(FolderId folderId) {
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
