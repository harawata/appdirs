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

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import net.harawata.appdirs.impl.MacOSXAppDirs;
import net.harawata.appdirs.impl.UnixAppDirs;
import net.harawata.appdirs.impl.WindowsAppDirs;

public class AppDirsFactoryTest {
  private static String origOs;

  @Test
  public void testGetInstance_MacOSX() {
    origOs = System.setProperty("os.name", "Mac OS X");
    AppDirs appDirs = AppDirsFactory.getInstance();
    assertEquals(MacOSXAppDirs.class, appDirs.getClass());
  }

  @Test
  public void testGetInstance_Unix() {
    origOs = System.setProperty("os.name", "OpenBSD");
    AppDirs appDirs = AppDirsFactory.getInstance();
    assertEquals(UnixAppDirs.class, appDirs.getClass());
  }

  @Test
  public void testGetInstance_Windows() {
    origOs = System.setProperty("os.name", "Windows 7");
    AppDirs appDirs = AppDirsFactory.getInstance();
    assertEquals(WindowsAppDirs.class, appDirs.getClass());
  }

  @After
  public void resetProperty() {
    System.setProperty("os.name", origOs);
  }
}
