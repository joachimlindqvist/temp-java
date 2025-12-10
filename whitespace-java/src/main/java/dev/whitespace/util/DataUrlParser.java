package dev.whitespace.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUrlParser {

    private final static Pattern DATA_URL_PATTERN =
            Pattern.compile("^data:([a-zA-Z0-9/\\-+\\.]+)?(?:;[a-zA-Z0-9/\\-+=:;\\s]+)?(;base64)?,(.*)$");

    private final String mimeType;
    private final boolean isBase64Encoded;
    private final byte[] data;

    public DataUrlParser(String dataUrl) throws IllegalArgumentException {
        Matcher matcher = DATA_URL_PATTERN.matcher(dataUrl.trim());

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid Data URL format.");
        }

        // 1. Extract MIME Type (Group 1)
        String mimeGroup = matcher.group(1);
        this.mimeType = (mimeGroup != null) ? mimeGroup.toLowerCase() : "text/plain";

        // 2. Check Encoding (Group 2)
        String encodingGroup = matcher.group(2);
        this.isBase64Encoded = (encodingGroup != null && encodingGroup.toLowerCase().contains("base64"));

        // 3. Extract Raw Data String (Group 3)
        String rawDataString = matcher.group(3);

        if (rawDataString == null || rawDataString.isEmpty()) {
            this.data = new byte[0];
            return;
        }

        // 4. Decode the data
        if (this.isBase64Encoded) {
            // Base64 decoding
            this.data = Base64.getMimeDecoder().decode(rawDataString.getBytes(StandardCharsets.UTF_8));
        } else {
            // URL decoding (if not Base64, assume URL-encoded text data)
            // Note: Data URLs often use simple text or URL-encoding, though Base64 is most common for files.
            // For simplicity, we just convert to bytes here, assuming text/plain default or proper URL decoding if needed.
            this.data = rawDataString.getBytes(StandardCharsets.UTF_8);
        }
    }

    // Getters for the parsed components
    public String getMimeType() {
        return mimeType;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public byte[] getData() {
        return data;
    }

    // A convenience method to get data as a String, useful for text data
    public String getDataAsString() {
        return new String(data, StandardCharsets.UTF_8);
    }
}