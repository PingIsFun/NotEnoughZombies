package si.pingisfun.nez.updater;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Objects;
import java.util.Optional;

public class CheckForModUpdate {
    private static Optional<String> getLatestVersion() {
        Optional<String> latestURL = getLatestVersionURL();
        return latestURL.map(s -> s.replace("https://github.com/PingIsFun/NotEnoughZombies/releases/tag/", ""));
    }

    private static Optional<String> getLatestVersionURL() {
        String githubUrl = "https://github.com/PingIsFun/NotEnoughZombies/releases/latest";
        int responseCode;
        HttpURLConnection connection;
        try {
            // Create a URL object from the GitHub URL
            URL url = new URL(githubUrl);

            // Open a connection to the URL
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);

            // Send the GET request
            responseCode = connection.getResponseCode();
        } catch (IOException error) {
            return Optional.empty();
        }

        if (responseCode != HttpURLConnection.HTTP_MOVED_TEMP) {
            return Optional.empty();
        }

        // Check if the response contains a "Location" header
        String locationHeader = connection.getHeaderField("Location");

        if (Objects.isNull(locationHeader)) {
            return Optional.empty();
        }


        // Close the connection
        connection.disconnect();
        return Optional.of(locationHeader);
    }


    /**
     * Is version 1 newer than version 2.
     *
     * @param version1 the version 1 (SemVer)
     * @param version2 the version 2 (SemVer)
     * @return true if version 1 is newer else false
     */
    private static boolean isNewer(String version1, String version2) {
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");

        int major1 = Integer.parseInt(parts1[0]);
        int minor1 = Integer.parseInt(parts1[1]);
        int patch1 = Integer.parseInt(parts1[2]);

        int major2 = Integer.parseInt(parts2[0]);
        int minor2 = Integer.parseInt(parts2[1]);
        int patch2 = Integer.parseInt(parts2[2]);

        if (major1 > major2) {
            return true;
        } else if (major1 < major2) {
            return false;
        } else if (minor1 > minor2) {
            return true;
        } else if (minor1 < minor2) {
            return false;
        } else {
            return patch1 > patch2;
        }
    }

    public static boolean isModUpToDate(String installedModVersion) {
        Optional<String> latestVersion = getLatestVersion();
        if (!latestVersion.isPresent()) {
            return true;
        }

        return !isNewer(latestVersion.get(), installedModVersion);
    }
}
