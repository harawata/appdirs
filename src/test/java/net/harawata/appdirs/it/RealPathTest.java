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
import org.junit.Before;
import org.junit.Test;

import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

public class RealPathTest {
  private AppDirs appDirs;

  @Before
  public void init() {
    appDirs = AppDirsFactory.getInstance();
  }

  @Test
  public void testRealPathMacUserDataDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    final String home = System.getProperty("user.home");
    assertEquals(home + "/Library/Application Support",
        appDirs.getUserDataDir(null, null, null));
  }

  @Test
  public void testRealPathMacUserConfigDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    final String home = System.getProperty("user.home");
    assertEquals(home + "/Library/Application Support",
        appDirs.getUserConfigDir(null, null, null));
  }

  @Test
  public void testRealPathMacUserCacheDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    final String home = System.getProperty("user.home");
    assertEquals(home + "/Library/Caches",
        appDirs.getUserCacheDir(null, null, null));
  }

  @Test
  public void testRealPathMacUserLogDir() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    final String home = System.getProperty("user.home");
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
    assertEquals("/Library/Application Support",
        appDirs.getSiteConfigDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserDataDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    final String home = System.getProperty("user.home");
    assertEquals(home + "/.local/share",
        appDirs.getUserDataDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserConfigDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    final String home = System.getProperty("user.home");
    assertEquals(home + "/.config", appDirs.getUserConfigDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserCacheDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    final String home = System.getProperty("user.home");
    assertEquals(home + "/.cache", appDirs.getUserCacheDir(null, null, null));
  }

  @Test
  public void testRealPathLinuxUserLogDir() {
    assumeTrue(SystemUtils.IS_OS_LINUX);
    final String home = System.getProperty("user.home");
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
  public void testRealPathWinUserDataDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data",
        appDirs.getUserDataDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserConfigDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data",
        appDirs.getUserConfigDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserCacheDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\Cache",
        appDirs.getUserCacheDir(null, null, null));
  }

  @Test
  public void testRealPathWinUserLogDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\Logs",
        appDirs.getUserLogDir(null, null, null));
  }

  @Test
  public void testRealPathWinSiteDataDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSiteDataDir(null, null, null));
  }

  @Test
  public void testRealPathWinSiteConfigDir() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSiteConfigDir(null, null, null));
  }
}
