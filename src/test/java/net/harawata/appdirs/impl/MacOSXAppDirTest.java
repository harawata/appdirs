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

import static org.junit.Assert.*;
import net.harawata.appdirs.AppDirs;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MacOSXAppDirTest {
  private static String origHome;

  private static String origFileSeparator;

  private AppDirs appDirs;

  @BeforeClass
  public static void setUp() {
    origHome = System.setProperty("user.home", "/Users/somebody");
    origFileSeparator = System.setProperty("file.separator", "/");
  }

  @Before
  public void pre() {
    appDirs = new MacOSXAppDirs();
  }

  @Test
  public void testGetUserDataDir() {
    assertEquals("/Users/somebody/Library/Application Support",
        appDirs.getUserDataDir(null, null, null));
    assertEquals("/Users/somebody/Library/Application Support",
        appDirs.getUserDataDir(null, null, null, true));
    assertEquals("/Users/somebody/Library/Application Support/myapp",
        appDirs.getUserDataDir("myapp", null, null));
    assertEquals("/Users/somebody/Library/Application Support/myapp",
        appDirs.getUserDataDir("myapp", null, null, true));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", null));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", null, true));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata"));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testGetUserConfigDir() {
    assertEquals("/Users/somebody/Library/Preferences",
        appDirs.getUserConfigDir(null, null, null));
    assertEquals("/Users/somebody/Library/Preferences",
        appDirs.getUserConfigDir(null, null, null, true));
    assertEquals("/Users/somebody/Library/Preferences/myapp",
        appDirs.getUserConfigDir("myapp", null, null));
    assertEquals("/Users/somebody/Library/Preferences/myapp",
        appDirs.getUserConfigDir("myapp", null, null, true));
    assertEquals("/Users/somebody/Library/Preferences/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null));
    assertEquals("/Users/somebody/Library/Preferences/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null, true));
    assertEquals("/Users/somebody/Library/Preferences/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals("/Users/somebody/Library/Preferences/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testGetUserCacheDir() {
    assertEquals("/Users/somebody/Library/Caches",
        appDirs.getUserCacheDir(null, null, null));
    assertEquals("/Users/somebody/Library/Caches/myapp",
        appDirs.getUserCacheDir("myapp", null, null));
    assertEquals("/Users/somebody/Library/Caches/myapp/1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", null));
    assertEquals("/Users/somebody/Library/Caches/myapp/1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  public void testGetUserLogDir() {
    assertEquals("/Users/somebody/Library/Logs",
        appDirs.getUserLogDir(null, null, null));
    assertEquals("/Users/somebody/Library/Logs/myapp",
        appDirs.getUserLogDir("myapp", null, null));
    assertEquals("/Users/somebody/Library/Logs/myapp/1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", null));
    assertEquals("/Users/somebody/Library/Logs/myapp/1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  public void testGetUserDownloadsDir() {
    assertEquals("/Users/somebody/Downloads",
        appDirs.getUserDownloadsDir(null, null, null));
    assertEquals("/Users/somebody/Downloads/myapp",
        appDirs.getUserDownloadsDir("myapp", null, null));
    assertEquals("/Users/somebody/Downloads/myapp/1.2.3",
        appDirs.getUserDownloadsDir("myapp", "1.2.3", null));
    assertEquals("/Users/somebody/Downloads/myapp/1.2.3",
        appDirs.getUserDownloadsDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  public void testSiteDataDir() {
    assertEquals("/Library/Application Support",
        appDirs.getSiteDataDir(null, null, null));
    assertEquals("/Library/Application Support",
        appDirs.getSiteDataDir(null, null, null, true));
    assertEquals("/Library/Application Support/myapp",
        appDirs.getSiteDataDir("myapp", null, null));
    assertEquals("/Library/Application Support/myapp",
        appDirs.getSiteDataDir("myapp", null, null, true));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null, true));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", "harawata"));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testSiteConfigDir() {
    assertEquals("/Library/Preferences",
        appDirs.getSiteConfigDir(null, null, null));
    assertEquals("/Library/Preferences",
        appDirs.getSiteConfigDir(null, null, null, true));
    assertEquals("/Library/Preferences/myapp",
        appDirs.getSiteConfigDir("myapp", null, null));
    assertEquals("/Library/Preferences/myapp",
        appDirs.getSiteConfigDir("myapp", null, null, true));
    assertEquals("/Library/Preferences/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null));
    assertEquals("/Library/Preferences/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null, true));
    assertEquals("/Library/Preferences/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals("/Library/Preferences/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testgetSharedDir() {
    assertEquals("/Users/Shared/Library/Application Support",
        appDirs.getSharedDir(null, null, null));
    assertEquals("/Users/Shared/Library/Application Support/myapp",
        appDirs.getSharedDir("myapp", null, null));
    assertEquals("/Users/Shared/Library/Application Support/myapp/1.2.3",
        appDirs.getSharedDir("myapp", "1.2.3", null));
    assertEquals("/Users/Shared/Library/Application Support/myapp/1.2.3",
        appDirs.getSharedDir("myapp", "1.2.3", "harawata"));
  }

  @AfterClass
  public static void tearDown() {
    if (origHome == null)
      System.clearProperty("user.home");
    else
      System.setProperty("user.home", origHome);

    if (origFileSeparator == null)
      System.clearProperty("file.separator");
    else
      System.setProperty("file.separator", origFileSeparator);
  }
}
