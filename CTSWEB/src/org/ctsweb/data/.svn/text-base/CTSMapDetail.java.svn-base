package org.ctsweb.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hl7.data.CTSMaps;

/**
 * Map detail.
 * 
 * @author wellerk
 *
 */
public class CTSMapDetail {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CTSMapDetail.class.getName());

    /**
     * Map name.
     */
    private String mapName;
    
    /**
     * Details.
     */
    private List<CTSMapDetailData>  details;
    
    /**
     * Constructor.
     * 
     * @param mapName map name
     */
    public CTSMapDetail(String mapName) {
        this.mapName = mapName;
        CTSMap map = new CTSMap();
        CTSMapData mapdata = map.getData(mapName);
        CTSMaps maps = CTSMaps.getInstance();
        details = maps.getDetails(mapdata);
    }

    /**
     * Get name.
     * 
     * @return the mapName
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Set name.
     * 
     * @param mapName the mapName to set
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    /**
     * Get details.
     * 
     * @return the details
     */
    public List<CTSMapDetailData> getDetails() {
        return details;
    }

    /**
     * Get details.
     * 
     * @param filter filter
     * @return the details
     */
    public List<CTSMapDetailData> getDetails(String filter) {
        LOGGER.error("getDetails filter = " + filter);
        List<CTSMapDetailData> rtc = details;
        if (filter != null && filter.length() > 0) {
            rtc = new ArrayList<CTSMapDetailData>();
            Iterator<CTSMapDetailData> iter = details.iterator();
            while (iter.hasNext()) {
                CTSMapDetailData data = iter.next();
                if (data != null && data.getFromCode() != null && data.getFromCode().toUpperCase().indexOf(filter.toUpperCase()) != -1) {
                    rtc.add(data);
                } else if (data != null && data.getFromDescription() != null && data.getFromDescription().toUpperCase().indexOf(filter.toUpperCase()) != -1) {
                    rtc.add(data);
                } else if (data != null && data.getToCode() != null && data.getToCode().toUpperCase().indexOf(filter.toUpperCase()) != -1) {
                    rtc.add(data);
                } else if (data != null && data.getToDescription() != null && data.getToDescription().toUpperCase().indexOf(filter.toUpperCase()) != -1) {
                    rtc.add(data);
                }
            }
        }
        return rtc;
    }

    /**
     * Set details.
     * 
     * @param details the details to set
     */
    public void setDetails(List<CTSMapDetailData> details) {
        this.details = details;
    }
}
