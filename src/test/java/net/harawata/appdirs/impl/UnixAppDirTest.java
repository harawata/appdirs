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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnixAppDirTest
{
	private static String origOs;

	private static String origHome;

	private static String origFileSeparator;

	private static String origPathSeparator;

	private static String xdgDataHome;

	private static String xdgConfigHome;

	private static String xdgCacheHome;

	private static String xdgDataDirs;

	private static String xdgConfigDirs;

	private AppDirs appDirs;

	@BeforeClass
	public static void setUp()
	{
		origOs = System.setProperty(AppDirs.OS_NAME, "OpenBSD");
		origHome = System.setProperty(AppDirs.USER_HOME, "/home/somebody");
		origFileSeparator = System.setProperty(AppDirs.FILE_SEPARATOR, "/");
		origPathSeparator = System.setProperty(AppDirs.PATH_SEPARATOR, ":");

		xdgDataHome = System.clearProperty(UnixAppDirs.XDG_DATA_HOME);
		xdgConfigHome = System.clearProperty(UnixAppDirs.XDG_CONFIG_HOME);
		xdgCacheHome = System.clearProperty(UnixAppDirs.XDG_CACHE_HOME);
		xdgDataDirs = System.clearProperty(UnixAppDirs.XDG_DATA_DIRS);
		xdgConfigDirs = System.clearProperty(UnixAppDirs.XDG_CONFIG_DIRS);
	}

	@Before
	public void pre()
	{
		appDirs = AppDirsFactory.getInstance();
	}

	@Test
	public void testGetUserDataDir()
	{
		assertEquals("/home/somebody/.local/share", appDirs.getUserDataDir(null, null, null));
		assertEquals("/home/somebody/.local/share", appDirs.getUserDataDir(null, null, null, true));
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

		System.setProperty(UnixAppDirs.XDG_DATA_HOME, "/data_home");
		assertEquals("/data_home/myapp/1.2.3",
			appDirs.getUserDataDir("myapp", "1.2.3", "harawata", true));
	}

	@Test
	public void testGetUserConfigDir()
	{
		assertEquals("/home/somebody/.config", appDirs.getUserConfigDir(null, null, null));
		assertEquals("/home/somebody/.config", appDirs.getUserConfigDir(null, null, null, true));
		assertEquals("/home/somebody/.config/myapp", appDirs.getUserConfigDir("myapp", null, null));
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

		System.setProperty(UnixAppDirs.XDG_CONFIG_HOME, "/config_home");
		assertEquals("/config_home/myapp/1.2.3",
			appDirs.getUserConfigDir("myapp", "1.2.3", "harawata", true));
	}

	@Test
	public void testGetUserCacheDir()
	{
		assertEquals("/home/somebody/.cache", appDirs.getUserCacheDir(null, null, null));
		assertEquals("/home/somebody/.cache/myapp", appDirs.getUserCacheDir("myapp", null, null));
		assertEquals("/home/somebody/.cache/myapp/1.2.3",
			appDirs.getUserCacheDir("myapp", "1.2.3", null));
		assertEquals("/home/somebody/.cache/myapp/1.2.3",
			appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));

		System.setProperty(UnixAppDirs.XDG_CACHE_HOME, "/cache_home");
		assertEquals("/cache_home/myapp/1.2.3",
			appDirs.getUserCacheDir("myapp", "1.2.3", "harawata"));
	}

	@Test
	public void testGetUserLogDir()
	{
		assertEquals("/home/somebody/.cache/logs", appDirs.getUserLogDir(null, null, null));
		assertEquals("/home/somebody/.cache/myapp/logs", appDirs.getUserLogDir("myapp", null, null));
		assertEquals("/home/somebody/.cache/myapp/logs/1.2.3",
			appDirs.getUserLogDir("myapp", "1.2.3", null));
		assertEquals("/home/somebody/.cache/myapp/logs/1.2.3",
			appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));

		System.setProperty(UnixAppDirs.XDG_CACHE_HOME, "/cache_home");
		assertEquals("/cache_home/myapp/logs/1.2.3",
			appDirs.getUserLogDir("myapp", "1.2.3", "harawata"));
	}

	@Test
	public void testSiteDataDir()
	{
		assertEquals("/usr/local/share", appDirs.getSiteDataDir(null, null, null));
		assertEquals("/usr/local/share:/usr/share", appDirs.getSiteDataDir(null, null, null, true));
		assertEquals("/usr/local/share/myapp", appDirs.getSiteDataDir("myapp", null, null));
		assertEquals("/usr/local/share/myapp:/usr/share/myapp", appDirs.getSiteDataDir("myapp", null, null, true));
		assertEquals("/usr/local/share/myapp/1.2.3", appDirs.getSiteDataDir("myapp", "1.2.3", null));
		assertEquals("/usr/local/share/myapp/1.2.3:/usr/share/myapp/1.2.3",
			appDirs.getSiteDataDir("myapp", "1.2.3", null, true));
		assertEquals("/usr/local/share/myapp/1.2.3",
			appDirs.getSiteDataDir("myapp", "1.2.3", "harawata"));
		assertEquals("/usr/local/share/myapp/1.2.3:/usr/share/myapp/1.2.3",
			appDirs.getSiteDataDir("myapp", "1.2.3", "harawata", true));

		System.setProperty(UnixAppDirs.XDG_DATA_DIRS, "/data_dir:/opt/data_dir");
		assertEquals("/data_dir/myapp/1.2.3", appDirs.getSiteDataDir("myapp", "1.2.3", null));
		assertEquals("/data_dir/myapp/1.2.3:/opt/data_dir/myapp/1.2.3",
			appDirs.getSiteDataDir("myapp", "1.2.3", null, true));
	}

	@Test
	public void testSiteConfigDir()

	{
		assertEquals("/etc/xdg", appDirs.getSiteConfigDir(null, null, null));
		assertEquals("/etc/xdg", appDirs.getSiteConfigDir(null, null, null, true));
		assertEquals("/etc/xdg/myapp", appDirs.getSiteConfigDir("myapp", null, null));
		assertEquals("/etc/xdg/myapp", appDirs.getSiteConfigDir("myapp", null, null, true));
		assertEquals("/etc/xdg/myapp/1.2.3", appDirs.getSiteConfigDir("myapp", "1.2.3", null));
		assertEquals("/etc/xdg/myapp/1.2.3", appDirs.getSiteConfigDir("myapp", "1.2.3", null, true));
		assertEquals("/etc/xdg/myapp/1.2.3", appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata"));
		assertEquals("/etc/xdg/myapp/1.2.3",
			appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));

		System.setProperty(UnixAppDirs.XDG_CONFIG_DIRS, "/config_dir:/opt/config_dir");
		assertEquals("/config_dir/myapp/1.2.3", appDirs.getSiteConfigDir("myapp", "1.2.3", null));
		assertEquals("/config_dir/myapp/1.2.3:/opt/config_dir/myapp/1.2.3",
			appDirs.getSiteConfigDir("myapp", "1.2.3", "harawata", true));
	}

	@After
	public void clearXdgVars()
	{
		System.clearProperty(UnixAppDirs.XDG_DATA_HOME);
		System.clearProperty(UnixAppDirs.XDG_CONFIG_HOME);
		System.clearProperty(UnixAppDirs.XDG_CACHE_HOME);
		System.clearProperty(UnixAppDirs.XDG_DATA_DIRS);
		System.clearProperty(UnixAppDirs.XDG_CONFIG_DIRS);
	}

	@AfterClass
	public static void tearDown()
	{
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

		if (origPathSeparator == null)
			System.clearProperty(AppDirs.PATH_SEPARATOR);
		else
			System.setProperty(AppDirs.PATH_SEPARATOR, origPathSeparator);

		if (xdgDataHome == null)
			System.clearProperty(UnixAppDirs.XDG_DATA_HOME);
		else
			System.setProperty(UnixAppDirs.XDG_DATA_HOME, xdgDataHome);

		if (xdgConfigHome == null)
			System.clearProperty(UnixAppDirs.XDG_CONFIG_HOME);
		else
			System.setProperty(UnixAppDirs.XDG_CONFIG_HOME, xdgConfigHome);

		if (xdgCacheHome == null)
			System.clearProperty(UnixAppDirs.XDG_CACHE_HOME);
		else
			System.setProperty(UnixAppDirs.XDG_CACHE_HOME, xdgCacheHome);

		if (xdgDataDirs == null)
			System.clearProperty(UnixAppDirs.XDG_DATA_DIRS);
		else
			System.setProperty(UnixAppDirs.XDG_DATA_DIRS, xdgDataDirs);

		if (xdgConfigDirs == null)
			System.clearProperty(UnixAppDirs.XDG_CONFIG_DIRS);
		else
			System.setProperty(UnixAppDirs.XDG_CONFIG_DIRS, xdgConfigDirs);
	}
}
