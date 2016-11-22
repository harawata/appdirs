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
import net.harawata.appdirs.AppDirsFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MacOSXAppDirTest {
  private static String origOs;

  private static String origHome;

  private static String origFileSeparator;

  private AppDirs appDirs;

  @BeforeClass
  public static void setUp() {
    origOs = System.setProperty(AppDirs.OS_NAME, "Mac OS X");
    origHome = System.setProperty(AppDirs.USER_HOME, "/Users/somebody");
    origFileSeparator = System.setProperty(AppDirs.FILE_SEPARATOR, "/");
  }

  @Before
  public void pre() {
    appDirs = AppDirsFactory.getInstance();
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
    assertEquals("/Users/somebody/Library/Application Support",
        appDirs.getUserConfigDir(null, null, null));
    assertEquals("/Users/somebody/Library/Application Support",
        appDirs.getUserConfigDir(null, null, null, true));
    assertEquals("/Users/somebody/Library/Application Support/myapp",
        appDirs.getUserConfigDir("myapp", null, null));
    assertEquals("/Users/somebody/Library/Application Support/myapp",
        appDirs.getUserConfigDir("myapp", null, null, true));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null, true));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals("/Users/somebody/Library/Application Support/myapp/1.2.3",
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
    assertEquals("/Library/Application Support",
        appDirs.getSiteConfigDir(null, null, null));
    assertEquals("/Library/Application Support",
        appDirs.getSiteConfigDir(null, null, null, true));
    assertEquals("/Library/Application Support/myapp",
        appDirs.getSiteConfigDir("myapp", null, null));
    assertEquals("/Library/Application Support/myapp",
        appDirs.getSiteConfigDir("myapp", null, null, true));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null, true));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals("/Library/Application Support/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @AfterClass
  public static void tearDown() {
    if (origOs == null)
      System.clearProperty(AppDirs.OS_NAME);
    else
      System.setProperty(AppDirs.OS_NAME, origOs);

    if (origHome == null)
      System.clearProperty(AppDirs.USER_HOME);
    else
      System.setProperty(AppDirs.USER_HOME, origHome);

    if (origFileSeparator == null)
      System.clearProperty(AppDirs.FILE_SEPARATOR);
    else
      System.setProperty(AppDirs.FILE_SEPARATOR, origFileSeparator);
  }
}
