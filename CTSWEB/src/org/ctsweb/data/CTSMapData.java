package org.ctsweb.data;

/**
 * CTS Map data class.
 * 
 * @author wellerk
 *
 */
public class CTSMapData {

    /**
     * Map name.
     */
    private String mapName;
    
    /**
     * From OID.
     */
    private String fromOid;
    
    /**
     * To OID.
     */
    private String toOid;
    
    /**
     * From vocabulary.
     */
    private String fromVocab;
    
    /**
     * To vocabulary.
     */
    private String toVocab;
    
    /**
     * Constructor.
     * 
     * @param mapName name
     * @param fromOid from OID
     * @param toOid to OID
     * @param fromVocab from vocabulary
     * @param toVocab to vocabulary
     */
    public CTSMapData(String mapName, String fromOid, String toOid, String fromVocab, String toVocab) {
        this.mapName = mapName;
        this.fromOid = fromOid;
        this.toOid = toOid;
        this.fromVocab = fromVocab;
        this.toVocab = toVocab;
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
     * Get from OID.
     * 
     * @return the fromOid
     */
    public String getFromOid() {
        return fromOid;
    }

    /**
     * Set from OID.
     * 
     * @param fromOid the fromOid to set
     */
    public void setFromOid(String fromOid) {
        this.fromOid = fromOid;
    }

    /**
     * Get to OID.
     * 
     * @return the toOid
     */
    public String getToOid() {
        return toOid;
    }

    /**
     * Set to OID.
     * 
     * @param toOid the toOid to set
     */
    public void setToOid(String toOid) {
        this.toOid = toOid;
    }

    /**
     * Get from vocabulary.
     * 
     * @return the fromVocab
     */
    public String getFromVocab() {
        return fromVocab;
    }

    /**
     * Set from vocabulary.
     * 
     * @param fromVocab the fromVocab to set
     */
    public void setFromVocab(String fromVocab) {
        this.fromVocab = fromVocab;
    }

    /**
     * Get to vocabulary.
     * 
     * @return the toVocab
     */
    public String getToVocab() {
        return toVocab;
    }

    /**
     * Set to vocabulary.
     * 
     * @param toVocab the toVocab to set
     */
    public void setToVocab(String toVocab) {
        this.toVocab = toVocab;
    }
}
