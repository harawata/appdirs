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

import net.harawata.appdirs.impl.MacOSXAppDirs;
import net.harawata.appdirs.impl.ShellFolderResolver;
import net.harawata.appdirs.impl.UnixAppDirs;
import net.harawata.appdirs.impl.WindowsAppDirs;
import net.harawata.appdirs.impl.WindowsFolderResolver;

public class AppDirsFactory {
  private AppDirsFactory() {
    super();
  }

  public static AppDirs getInstance() {
    String os = System.getProperty("os.name").toLowerCase();
    if (os.startsWith("mac os x")) {
      return new MacOSXAppDirs();
    } else if (os.startsWith("windows")) {
      WindowsFolderResolver folderResolver = new ShellFolderResolver();
      return new WindowsAppDirs(folderResolver);
    } else {
      // Assume other *nix.
      return new UnixAppDirs();
    }
  }
}
