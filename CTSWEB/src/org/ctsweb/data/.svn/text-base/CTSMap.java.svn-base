package org.ctsweb.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * CTS Map class.
 * 
 * @author wellerk
 *
 */
public class CTSMap {
    
    /**
     * Constructor.
     */
    public CTSMap() {
        super();
    }

    /**
     * Get list.
     * 
     * @return list
     */
    public List<CTSMapData> getList() {
        List<CTSMapData> rtc = new ArrayList<CTSMapData>();
        rtc.add(new CTSMapData("Map_Collection-Method-Codes_CHCS-Chicago_to_Std", "2.16.840.1.113883.3.42.127.100001.289", "2.16.840.1.113883.12.488", "Local_Collection-Method-Codes_CHCS-Chicago", "Collection-Method-Codes"));
        rtc.add(new CTSMapData("Map_Collection-Method-Codes_Std_to_CHCS-Chicago", "2.16.840.1.113883.12.488", "2.16.840.1.113883.3.42.127.100001.289", "Collection-Method-Codes", "Local_Collection-Method-Codes_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Collection-Sample-IENs_Vista-Chicago_to_CHCS-Chicago", "4.3.2.1.14", "2.16.840.1.113883.3.42.127.56.247", "Local_Collection-Sample-IENs_VistA-Chicago", "Local_Collection-Sample-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Consult-Procedure-IENs_VistA-Chicago_to_CHCS-Chicago", "2.16.840.1.113883.3.42.127.56.850", "2.16.840.1.113883.3.42.127.56.255.99", "Local_Consult-Procedure-IENs_VistA-Chicago", "Local_Consult-Procedure-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Etiology-IENs_VistA-Chicago_to_CHCS-Chicago", "4.3.2.1.17", "2.16.840.1.113883.3.42.127.56.246", "Local_Etiology-IENs_VistA-Chicago", "Local_Etiology-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Lab-Test-IENs_VistA-Chicago_to_CHCS-Chicago", "4.3.2.1.9", "2.16.840.1.113883.3.42.127.56.281", "Local_Lab-Test-IENs_VistA-Chicago", "Local_Lab-Test-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Location-IENs_VistA-Chicago_to_CHCS-Chicago", "4.3.2.1.8", "2.16.840.1.113883.3.42.127.56.282", "Local_Location-IENs_VistA-Chicago", "Local_Location-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Micro-Result-Codes_VistA-Chicago_to_CHCS-Chicago", "4.3.7.18", "2.16.840.1.113883.3.42.127.56.296", "Local_Micro-Result-Codes_VistA-Chicago", "Local_Micro-Result-Codes_CHCS-Chicago"));
//        rtc.add(new CTSMapData("Map_Priority-Codes_CHCS-Chicago_to_Std", "2.16.840.1.113883.3.42.127.100001.256", "2.16.840.1.113883.12.168", "Local_Priority-Codes_CHCS-Chicago", "Priority-Codes"));
//        rtc.add(new CTSMapData("Map_Priority-Codes_Std_to_CHCS-Chicago", "2.16.840.1.113883.12.168", "2.16.840.1.113883.3.42.127.100001.256", "Priority-Codes", "Local_Priority-Codes_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_RAD-Procedure-IENs_VistA-Chicago_to_CHCS-Chicago", "4.3.2.1.12", "2.16.840.1.113883.3.42.127.56.255", "Local_RAD-Procedure-IENs_VistA-Chicago", "Local_RAD-Procedure-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Result-Category-Codes_VistA-Chicago_to_CHCS-Chicago", "4.3.2.1.6", "2.16.840.1.113883.3.42.127.100001.293", "Local_Result-Category-Codes_VistA-Chicago", "Local_Result-Category-Codes_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_Site-Specimen-IENs_VistA-Chicago_to_CHCS-Chicago", "4.3.5.3", "2.16.840.1.113883.3.42.127.56.295", "Local_Site-Specimen-IENs_VistA-Chicago", "Local_Site-Specimen-IENs_CHCS-Chicago"));
        rtc.add(new CTSMapData("Map_User-IENs_VistA-Chicago_to_CHCS-Chicago", "4.3.2.1.13", "2.16.840.1.113883.3.42.127.56.232", "Local_User-IENs_VistA-Chicago", "Local_User-IENs_CHCS-Chicago"));
        return rtc;
    }
    
    /**
     * Get data.
     * 
     * @param mapName name
     * @return data
     */
    public CTSMapData getData(String mapName) {
        CTSMapData rtc = null;
        List<CTSMapData> list = getList();
        Iterator<CTSMapData> iter = list.iterator();
        while (iter.hasNext()) {
            CTSMapData curr = iter.next();
            if (curr != null && curr.getMapName() != null && curr.getMapName().equalsIgnoreCase(mapName)) {
                rtc = curr;
                break;
            }
        }
        return rtc;
    }
    
    /**
     * Get description.
     * 
     * @param oid OID
     * @return description
     */
    public String getOidDesc(String oid) {
        String rtc = "";
        List<CTSMapData> list = getList();
        Iterator<CTSMapData> iter = list.iterator();
        while (iter.hasNext()) {
            CTSMapData curr = iter.next();
            if (curr != null && curr.getFromOid() != null && curr.getFromOid().equalsIgnoreCase(oid)) {
                rtc = curr.getFromVocab();
                break;
            }
            if (curr != null && curr.getToOid() != null && curr.getToOid().equalsIgnoreCase(oid)) {
                rtc = curr.getToVocab();
                break;
            }
        }
        return rtc;
    }
}
