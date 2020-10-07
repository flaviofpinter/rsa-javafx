/*
 * Copyright (c) 2020. Emerson Pinter - All rights reserved.
 */

package dev.pinter.jfxapp.core;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinReg;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    public static boolean isUseDarkTheme() {
        if (SystemUtils.IS_OS_WINDOWS) {
            return !isWindowsAppsUseLightTheme();
        } else {
            return true;
        }
    }

    public static boolean isWindowsAppsUseLightTheme() {
        int appsUsesLightTheme = 1;

        try {
            appsUsesLightTheme = Advapi32Util.registryGetIntValue(
                    WinReg.HKEY_CURRENT_USER,
                    "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", "AppsUseLightTheme");
        } catch (Win32Exception e) {
            logger.error("Error retrieving registry key 'AppsUseLightTheme'", e);
        }

        return appsUsesLightTheme == 1;
    }

    public static boolean isWindowsSystemUsesLightTheme() {
        int systemUsesLightTheme = 0;

        try {
            systemUsesLightTheme = Advapi32Util.registryGetIntValue(
                    WinReg.HKEY_CURRENT_USER,
                    "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", "SystemUsesLightTheme");
        } catch (Win32Exception e) {
            logger.error("Error retrieving registry key 'SystemUsesLightTheme'", e);
        }

        return systemUsesLightTheme == 1;
    }

}
