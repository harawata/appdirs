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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WindowsAppDirTest {
  private static String origFileSeparator;

  private WindowsAppDirs appDirs;

  @BeforeAll
  static void setUp() {
    origFileSeparator = System.setProperty("file.separator", "\\");
  }

  @BeforeEach
  void pre() {
    appDirs = new WindowsAppDirs(new MockWindowsFolderResolver());
  }

  @Test
  void testGetUserDataDir() {
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data",
        appDirs.getUserDataDir(null, null, null));
    assertEquals("C:\\Documents and Settings\\harawata\\Application Data",
        appDirs.getUserDataDir(null, null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp",
        appDirs.getUserDataDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Application Data\\myapp",
        appDirs.getUserDataDir("myapp", null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp\\1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Application Data\\myapp\\1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", null, true));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata"));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  void testGetUserConfigDir() {
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data",
        appDirs.getUserConfigDir(null, null, null));
    assertEquals("C:\\Documents and Settings\\harawata\\Application Data",
        appDirs.getUserConfigDir(null, null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp",
        appDirs.getUserConfigDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Application Data\\myapp",
        appDirs.getUserConfigDir("myapp", null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp\\1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Application Data\\myapp\\1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null, true));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  void testGetUserCacheDir() {
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\Cache",
        appDirs.getUserCacheDir(null, null, null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp\\Cache",
        appDirs.getUserCacheDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp\\Cache\\1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\harawata\\myapp\\Cache\\1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  void testGetUserLogDir() {
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\Logs",
        appDirs.getUserLogDir(null, null, null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp\\Logs",
        appDirs.getUserLogDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\myapp\\Logs\\1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\harawata\\Local Settings\\Application Data\\harawata\\myapp\\Logs\\1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  void testGetUserDownloadsDir() {
    assertEquals("C:\\Users\\ave\\Downloads",
        appDirs.getUserDownloadsDir(null, null, null));
    assertEquals("C:\\Users\\ave\\Downloads\\myapp",
        appDirs.getUserDownloadsDir("myapp", null, null));
    assertEquals("C:\\Users\\ave\\Downloads\\myapp\\1.2.3",
        appDirs.getUserDownloadsDir("myapp", "1.2.3", null));
    assertEquals("C:\\Users\\ave\\Downloads\\harawata\\myapp\\1.2.3",
        appDirs.getUserDownloadsDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  void testSiteDataDir() {
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSiteDataDir(null, null, null));
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSiteDataDir(null, null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp",
        appDirs.getSiteDataDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp",
        appDirs.getSiteDataDir("myapp", null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp\\1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp\\1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null, true));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", "harawata"));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  void testSiteConfigDir() {
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSiteConfigDir(null, null, null));
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSiteConfigDir(null, null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp",
        appDirs.getSiteConfigDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp",
        appDirs.getSiteConfigDir("myapp", null, null, true));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp\\1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp\\1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null, true));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  void testGetSharedDir() {
    assertEquals("C:\\Documents and Settings\\All Users\\Application Data",
        appDirs.getSharedDir(null, null, null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp",
        appDirs.getSharedDir("myapp", null, null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\myapp\\1.2.3",
        appDirs.getSharedDir("myapp", "1.2.3", null));
    assertEquals(
        "C:\\Documents and Settings\\All Users\\Application Data\\harawata\\myapp\\1.2.3",
        appDirs.getSharedDir("myapp", "1.2.3", "harawata"));
  }

  @AfterAll
  static void tearDown() {
    if (origFileSeparator == null)
      System.clearProperty("file.separator");
    else
      System.setProperty("file.separator", origFileSeparator);
  }
}
