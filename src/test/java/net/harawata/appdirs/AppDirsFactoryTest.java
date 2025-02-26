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
import static org.junit.Assume.*;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import net.harawata.appdirs.impl.MacOSXAppDirs;
import net.harawata.appdirs.impl.UnixAppDirs;
import net.harawata.appdirs.impl.WindowsAppDirs;

public class AppDirsFactoryTest {

  @Test
  public void testGetInstance_MacOSX() {
    assumeTrue(SystemUtils.IS_OS_MAC_OSX);
    AppDirs appDirs = AppDirsFactory.INSTANCE.getAppDirs();
    assertEquals(MacOSXAppDirs.class, appDirs.getClass());
  }

  @Test
  public void testGetInstance_Unix() {
    assumeFalse(SystemUtils.IS_OS_MAC_OSX || SystemUtils.IS_OS_WINDOWS);
    AppDirs appDirs = AppDirsFactory.INSTANCE.getAppDirs();
    assertEquals(UnixAppDirs.class, appDirs.getClass());
  }

  @Test
  public void testGetInstance_Windows() {
    assumeTrue(SystemUtils.IS_OS_WINDOWS);
    AppDirs appDirs = AppDirsFactory.INSTANCE.getAppDirs();
    assertEquals(WindowsAppDirs.class, appDirs.getClass());
  }

  @Test
  public void verifySingleton() {
    AppDirs appDirs1 = AppDirsFactory.INSTANCE.getAppDirs();
    AppDirs appDirs2 = AppDirsFactory.INSTANCE.getAppDirs();
    assertSame(appDirs1, appDirs2);
  }
}
