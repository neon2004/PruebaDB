
package pruebadb.example.dj.com.pruebadb.common.rest;

import java.util.HashMap;
import java.util.Map;

public class Location1 {

    private String latitude;
    private String humanAddress;
    private Boolean needsRecoding;
    private String longitude;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHumanAddress() {
        return humanAddress;
    }

    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

    public Boolean getNeedsRecoding() {
        return needsRecoding;
    }

    public void setNeedsRecoding(Boolean needsRecoding) {
        this.needsRecoding = needsRecoding;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
