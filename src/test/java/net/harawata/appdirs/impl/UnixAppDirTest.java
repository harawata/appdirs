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

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.harawata.appdirs.AppDirs;

public class UnixAppDirTest {
  private static String origHome;

  private static String origFileSeparator;

  private static String origPathSeparator;

  @BeforeClass
  public static void setUp() {
    origHome = System.setProperty("user.home", "/home/somebody");
    origFileSeparator = System.setProperty("file.separator", "/");
    origPathSeparator = System.setProperty("path.separator", ":");
  }

  @Test
  public void testGetUserDataDir() {
    AppDirs appDirs = getAppDirs();
    assertEquals("/home/somebody/.local/share",
        appDirs.getUserDataDir(null, null, null));
    assertEquals("/home/somebody/.local/share",
        appDirs.getUserDataDir(null, null, null, true));
    assertEquals("/home/somebody/.local/share/myapp",
        appDirs.getUserDataDir("myapp", null, null));
    assertEquals("/home/somebody/.local/share/myapp",
        appDirs.getUserDataDir("myapp", null, null, true));
    assertEquals("/home/somebody/.local/share/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", null));
    assertEquals("/home/somebody/.local/share/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", null, true));
    assertEquals("/home/somebody/.local/share/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata"));
    assertEquals("/home/somebody/.local/share/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testGetUserConfigDir() {
    AppDirs appDirs = getAppDirs();
    assertEquals("/home/somebody/.config",
        appDirs.getUserConfigDir(null, null, null));
    assertEquals("/home/somebody/.config",
        appDirs.getUserConfigDir(null, null, null, true));
    assertEquals("/home/somebody/.config/myapp",
        appDirs.getUserConfigDir("myapp", null, null));
    assertEquals("/home/somebody/.config/myapp",
        appDirs.getUserConfigDir("myapp", null, null, true));
    assertEquals("/home/somebody/.config/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null));
    assertEquals("/home/somebody/.config/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", null, true));
    assertEquals("/home/somebody/.config/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals("/home/somebody/.config/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testGetUserCacheDir() {
    AppDirs appDirs = getAppDirs();
    assertEquals("/home/somebody/.cache",
        appDirs.getUserCacheDir(null, null, null));
    assertEquals("/home/somebody/.cache/myapp",
        appDirs.getUserCacheDir("myapp", null, null));
    assertEquals("/home/somebody/.cache/myapp/1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", null));
    assertEquals("/home/somebody/.cache/myapp/1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  public void testGetUserLogDir() {
    AppDirs appDirs = getAppDirs();
    assertEquals("/home/somebody/.cache/logs",
        appDirs.getUserLogDir(null, null, null));
    assertEquals("/home/somebody/.cache/myapp/logs",
        appDirs.getUserLogDir("myapp", null, null));
    assertEquals("/home/somebody/.cache/myapp/logs/1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", null));
    assertEquals("/home/somebody/.cache/myapp/logs/1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));
  }

  @Test
  public void testSiteDataDir() {
    AppDirs appDirs = getAppDirs();
    assertEquals("/usr/local/share", appDirs.getSiteDataDir(null, null, null));
    assertEquals("/usr/local/share:/usr/share",
        appDirs.getSiteDataDir(null, null, null, true));
    assertEquals("/usr/local/share/myapp",
        appDirs.getSiteDataDir("myapp", null, null));
    assertEquals("/usr/local/share/myapp:/usr/share/myapp",
        appDirs.getSiteDataDir("myapp", null, null, true));
    assertEquals("/usr/local/share/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null));
    assertEquals("/usr/local/share/myapp/1.2.3:/usr/share/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null, true));
    assertEquals("/usr/local/share/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", "harawata"));
    assertEquals("/usr/local/share/myapp/1.2.3:/usr/share/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testSiteConfigDir() {
    AppDirs appDirs = getAppDirs();
    assertEquals("/etc/xdg", appDirs.getSiteConfigDir(null, null, null));
    assertEquals("/etc/xdg", appDirs.getSiteConfigDir(null, null, null, true));
    assertEquals("/etc/xdg/myapp",
        appDirs.getSiteConfigDir("myapp", null, null));
    assertEquals("/etc/xdg/myapp",
        appDirs.getSiteConfigDir("myapp", null, null, true));
    assertEquals("/etc/xdg/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null));
    assertEquals("/etc/xdg/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null, true));
    assertEquals("/etc/xdg/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata"));
    assertEquals("/etc/xdg/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
  }

  @Test
  public void testEnvironmentVariables() {
    Map<String, String> vars = new HashMap<String, String>();
    vars.put(UnixAppDirs.XDG_DATA_HOME, "/data_home");
    vars.put(UnixAppDirs.XDG_CONFIG_HOME, "/config_home");
    vars.put(UnixAppDirs.XDG_CACHE_HOME, "/cache_home");
    vars.put(UnixAppDirs.XDG_DATA_DIRS, "/data_dir:/opt/data_dir");
    vars.put(UnixAppDirs.XDG_CONFIG_DIRS, "/config_dir:/opt/config_dir");
    AppDirs appDirs = getAppDirs(vars);

    assertEquals("/data_home/myapp/1.2.3",
        appDirs.getUserDataDir("myapp", "1.2.3", "harawata", true));
    assertEquals("/config_home/myapp/1.2.3",
        appDirs.getUserConfigDir("myapp", "1.2.3", "harawata", true));
    assertEquals("/cache_home/myapp/1.2.3",
        appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));
    assertEquals("/cache_home/myapp/logs/1.2.3",
        appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));
    assertEquals("/data_dir/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null));
    assertEquals("/data_dir/myapp/1.2.3:/opt/data_dir/myapp/1.2.3",
        appDirs.getSiteDataDir("myapp", "1.2.3", null, true));
    assertEquals("/config_dir/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", null));
    assertEquals("/config_dir/myapp/1.2.3:/opt/config_dir/myapp/1.2.3",
        appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
  }

  private AppDirs getAppDirs() {
    return getAppDirs(new HashMap<String, String>());
  }

  private AppDirs getAppDirs(Map<String, String> envVars) {
    return new UnixAppDirs(envVars);
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

    if (origPathSeparator == null)
      System.clearProperty("path.separator");
    else
      System.setProperty("path.separator", origPathSeparator);
  }
}
