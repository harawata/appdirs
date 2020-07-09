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

package net.harawata.appdirs.it;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.apache.commons.lang3.SystemUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

public class RealPathTest {
  private static AppDirs appDirs;
  private static String home;

  @BeforeClass
  public static void init() {
    appDirs = AppDirsFactory.getInstance();
    home = System.getProperty("user.home");
  }

  @Test
  public void testRealPathMacUserDataDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals(home + "/Library/Application Support",
        appDirs.getUserDataDir(null, null, null));
  }

  @Test
  public void testRealPathMacUserConfigDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals(home + "/Library/Preferences",
        appDirs.getUserConfigDir(null, null, null));
  }

  @Test
  public void testRealPathMacUserCacheDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals(home + "/Library/Caches",
        appDirs.getUserCacheDir(null, null, null));
  }

  @Test
  public void testRealPathMacUserLogDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals(home + "/Library/Logs",
        appDirs.getUserLogDir(null, null, null));
  }

  @Test
  public void testRealPathMacSiteDataDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals("/Library/Application Support",
        appDirs.getSiteDataDir(null, null, null));
  }

  @Test
  public void testRealPathMacSiteConfigDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals("/Library/Preferences",
        appDirs.getSiteConfigDir(null, null, null));
  }

  @Test
  public void testRealPathMacSharedDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    assertEquals("/Users/Shared/Library/Application Support",
        appDirs.getSharedDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserDataDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals(home + "/.local/share",
        appDirs.getUserDataDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserConfigDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals(home + "/.config", appDirs.getUserConfigDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserCacheDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals(home + "/.cache", appDirs.getUserCacheDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserLogDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals(home + "/.cache/logs",
        appDirs.getUserLogDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxSiteDataDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals("/usr/local/share", appDirs.getSiteDataDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxSiteConfigDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals("/etc/xdg", appDirs.getSiteConfigDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxSharedDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    assertEquals("/srv", appDirs.getSharedDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserDataDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(home + "\\AppData\\Local",
        appDirs.getUserDataDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserConfigDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(home + "\\AppData\\Local",
        appDirs.getUserConfigDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserCacheDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(home + "\\AppData\\Local\\Cache",
        appDirs.getUserCacheDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserLogDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(home + "\\AppData\\Local\\Logs",
        appDirs.getUserLogDir(null, null, null));
  }

  @Test
  public void testRealPathWinSiteDataDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals("C:\\ProgramData", appDirs.getSiteDataDir(null, null, null));
  }

  @Test
  public void testRealPathWinSiteConfigDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals("C:\\ProgramData", appDirs.getSiteConfigDir(null, null, null));
  }

  @Test
  public void testRealPathWinSharedDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals("C:\\ProgramData", appDirs.getSharedDir(null, null, null));
  }
}
