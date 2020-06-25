package org.hl7.data;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.ctsweb.data.CTSMapData;
import org.ctsweb.data.CTSMapDetailData;
import org.hl7.CodeMap;
import org.hl7.GetSupportedMapsResponse;
import org.hl7.chcs.data.PatientLookup;
import org.hl7.chcs.data.ProviderLookup;
import org.jxp.data.JXPMapVersionsFlat;
import org.jxp.data.JXPMapsFlat;
import org.jxp.util.LogHelper;

/**  Mapping data retrieval class.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class CTSMaps extends CTSBaseDAO {

    /**
     * Class UID.
     */
    private static final long serialVersionUID = -217553300300807405L;

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CTSMaps.class.getName());

    /**
     * Exception string.
     */
    private static final String EXCEPTION = "Exception: ";
    
    /**
     * Quote comma quote.
     */
    private static final String QCQ = "\",\"";
    
    /**
     * Line separator.
     */
    private static final String LINESEP = "line.separator";

    /**
     * Flat file object.
     */
    private static JXPMapsFlat mapsflat = JXPMapsFlat.getInstance(); 
    
    /**
     * VA flag.
     */
    private boolean vaflag;

    /**
     * Flat flag.
     */
    private boolean ctsflat;
    
    /**
     * Constructor.
     */
    private CTSMaps() {
        super();
        if ("true".equalsIgnoreCase(System.getProperty("ctsflat"))) {
            ctsflat = true;
            try {
                mapsflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
        }
    }

    /**
     * Get instance.
     * 
     * @return instance
     */
    @SuppressWarnings("all")
    public static synchronized CTSMaps getInstance() {
        return CTSMapsHolder.INSTANCE;
    }
    
    /**
     * Clone - prevent cloning.
     *
     * @return nothing
     * @throws CloneNotSupportedException exception
     */
    @SuppressWarnings("all")
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

//    /**
//     * Constructor.
//     * 
//     * @param vaflag VA flag
//     */
//    public CTSMaps(boolean vaflag) {
//        super();
//        this.vaflag = vaflag;
//        if ("true".equalsIgnoreCase(System.getProperty("ctsflat"))) {
//            ctsflat = true;
//            try {
//                mapsflat.openFile();
//            } catch (IOException ex) {
//                LOGGER.error(EXCEPTION, ex);
//            }
//        }
//    }
//
//    /**
//     * Constructor.
//     *
//     * @param connect connect
//     * @param user user
//     * @param password password
//     */
//    public CTSMaps(final String connect,
//            final String user, final String password) {
//        super(connect, user, password);
//        if ("true".equalsIgnoreCase(System.getProperty("ctsflat"))) {
//            ctsflat = true;
//            try {
//                mapsflat.openFile();
//            } catch (IOException ex) {
//                LOGGER.error(EXCEPTION, ex);
//            }
//        }
//    }

    /**
     * Retrieve code map.
     *
     * @param fromOID from OID
     * @param fromCode from code
     * @param toOID to OID
     * @return code
     * @throws SQLException SQL exception
     */
    public final String mapCode(final String fromOID,
           final String fromCode, final String toOID) throws SQLException {
        String retCode = null;

        PreparedStatement statement = null;
        ResultSet resultset = null;

        if (!vaflag && "2.16.840.1.113883.4.6".equals(fromOID) && "2.16.840.1.113883.3.42.127.56.3".equals(toOID)) {
            ProviderLookup prov = new ProviderLookup();
            retCode = prov.retrieveIEN(fromCode);
        } else if (!vaflag && "2.16.840.1.113883.3.42.127.56.3".equals(fromOID) && "2.16.840.1.113883.4.6".equals(toOID)) {
            ProviderLookup prov = new ProviderLookup();
            retCode = prov.retrieveNPI(fromCode);
        } else if (!vaflag && "2.16.840.1.113883.3.42.127.56.2".equals(fromOID) && "2.16.840.1.113883.3.42.10001.100001.12".equals(toOID)) {
            PatientLookup prov = new PatientLookup();
            retCode = prov.retrieveEDIPN(fromCode);
        } else if (!vaflag && "2.16.840.1.113883.3.42.10001.100001.12".equals(fromOID) && "2.16.840.1.113883.3.42.127.56.2".equals(toOID)) {
            PatientLookup prov = new PatientLookup();
            retCode = prov.retrieveIEN(fromCode);
        } else {
            if (ctsflat) {
                retCode = mapsflat.getMapCode(fromOID, fromCode, toOID);
            } else {
                try {
                // DOD-NC
                    String sql = "";
                    if (vaflag) {
//                  select cs1.coding_scheme_oid fromOID, csc1.code fromCode, cs2.coding_scheme_oid toOID, csc2.code toCode
                        sql = "select csc2.code toCode "
                            + "from translationmapset tms "
                            + "left outer join translationmaps tm on tms.map_id = tm.map_id "
                            + "left outer join codingscheme cs1 on tm.src_coding_scheme_id = cs1.coding_scheme_id "
                            + "left outer join codingscheme cs2 on tm.trg_coding_scheme_id = cs2.coding_scheme_id "
                            + "left outer join codingschemecodes csc1 on tms.src_code_id = csc1.code_id "
                            + "left outer join codingschemecodes csc2 on tms.trg_code_id = csc2.code_id "
                            + "where cs1.coding_scheme_oid = ? and "
                            + "      csc1.code = ? and "
                            + "      cs2.coding_scheme_oid = ? ";
                    } else {
//                    sql = "select to_concept_code from mapping_relations "
//                        + "where from_version_oid = ? and from_concept_code = ? "
//                        + "and to_version_oid = ? and active=1 order by "
//                        + "to_relation_sequence";
                        sql = "select to_concept_code from jxp_map_data "
                            + "where from_version_oid = ? and from_concept_code = ? "
                            + "and to_version_oid = ? and active=1 ";
                    }

                    connect();
    
                    statement = this.getConn().prepareStatement(sql);
                    statement.setString(1, fromOID);
                    statement.setString(2, fromCode);
                    statement.setString(3, toOID);
    
                    resultset = statement.executeQuery();
    
                    if (resultset.next()) {
                        retCode = resultset.getString(1);
                        LOGGER.debug(fromCode + " = " + retCode);
                    }
//            } catch (Exception ex) {
//                LOGGER.error(EXCEPTION, ex);
                } finally {
                    try {
                        if (resultset != null) {
                            resultset.close();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(EXCEPTION, ex);
                    }
        
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(EXCEPTION, ex);
                    }
                    close();
                }
            }
        }
        return retCode;
    }

    /**
     * Get supported maps.
     *
     * @return maps
     * @throws SQLException SQL exception
     */
    public final GetSupportedMapsResponse getMaps() throws SQLException {
        final GetSupportedMapsResponse retMaps = new GetSupportedMapsResponse();

        if (ctsflat) {
            try {
                JXPMapVersionsFlat versions = JXPMapVersionsFlat.getInstance();
                versions.openFile();
                ConcurrentHashMap<String, String> rows = versions.getRows();
                Set<String> keyset = rows.keySet();
                String[] keys = keyset.toArray(new String[keyset.size()]);
                for (int i = 0; i < keys.length; ++i) {
                    CodeMap codemap = new CodeMap();
                    String value = rows.get(keys[i]);
                    String[] values = value.split("\\|");
                    codemap.setMapName(keys[i]);
                    codemap.setFromCodeSystemId(values[0]);
                    codemap.setFromCodeSystemName(values[1]);
                    codemap.setToCodeSystemId(values[2]);
                    codemap.setToCodeSystemName(values[3]);
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                }
                if (!vaflag) {
                    CodeMap codemap = new CodeMap();
                    codemap.setMapName("Map_Provider-NPI_to_CHCS-Chicago");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.4.6");
                    codemap.setFromCodeSystemName("National_Provider-NPIs");
                    codemap.setToCodeSystemId("2.16.840.1.113883.3.42.127.56.3");
                    codemap.setToCodeSystemName("Local_Provider-IENs_CHCS_Chicago");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                    codemap = new CodeMap();
                    codemap.setMapName("Map_Provider-CHCS-Chicago_to_NPI");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.3.42.127.56.3");
                    codemap.setFromCodeSystemName("Local_Provider-IENs_CHCS_Chicago");
                    codemap.setToCodeSystemId("2.16.840.1.113883.4.6");
                    codemap.setToCodeSystemName("National_Provider-NPIs");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                    codemap = new CodeMap();
                    codemap.setMapName("Map_Patient-EDIPN_to_CHCS-Chicago");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.3.42.10001.100001.12");
                    codemap.setFromCodeSystemName("National_Patient-EDIPNs");
                    codemap.setToCodeSystemId("2.16.840.1.113883.3.42.127.56.2");
                    codemap.setToCodeSystemName("Local_Patient-IENs_CHCS_Chicago");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                    codemap = new CodeMap();
                    codemap.setMapName("Map_Patient-CHCS-Chicago_to_EDIPN");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.3.42.127.56.2");
                    codemap.setFromCodeSystemName("Local_Patient-IENs_CHCS_Chicago");
                    codemap.setToCodeSystemId("2.16.840.1.113883.3.42.10001.100001.12");
                    codemap.setToCodeSystemName("National_Patient-EDIPNs");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                }
            } catch (Exception e) {
                LOGGER.error(EXCEPTION + e.getLocalizedMessage(), e);
            }
        } else {
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                // DOD-NC
                String sql = "";
                if (vaflag) {
                    sql = "select c.map_name, a.coding_scheme_oid as src_oid,a.coding_scheme_name as src_name, b.coding_scheme_oid as trg_oid, b.coding_scheme_name as trg_name "
                            + "from nc_dev.codingscheme a, nc_dev.codingscheme b, nc_dev.translationmaps c "
                            + "   where a.coding_scheme_id = c.src_coding_scheme_id "
                            + "   and b.coding_scheme_id = c.trg_coding_scheme_id";
                } else {
    //                sql = "select distinct code,from_version_oid,"
    //                    + "from_version_code,to_version_oid,to_version_code "
    //                    + "from mapping_relations where active = 1";
    
                    //TODO: implement jxp_maps
                    
                    sql = "select distinct code,from_version_oid,"
                            + "from_version_code,to_version_oid,to_version_code "
                            + "from jxp_map_data where active = 1";
                }
                connect();
    
                statement = this.getConn().prepareStatement(sql);
                resultset = statement.executeQuery();
    
                while (resultset.next()) {
                    CodeMap codemap = new CodeMap();
                    int fcount = 1;
    
                    codemap.setMapName(resultset.getString(fcount++));
                    codemap.setFromCodeSystemId(resultset.getString(fcount++));
                    codemap.setFromCodeSystemName(resultset.getString(fcount++));
                    codemap.setToCodeSystemId(resultset.getString(fcount++));
                    codemap.setToCodeSystemName(resultset.getString(fcount++));
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                }
                if (!vaflag) {
                    CodeMap codemap = new CodeMap();
                    codemap.setMapName("Map_Provider-NPI_to_CHCS-Chicago");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.4.6");
                    codemap.setFromCodeSystemName("National_Provider-NPIs");
                    codemap.setToCodeSystemId("2.16.840.1.113883.3.42.127.56.3");
                    codemap.setToCodeSystemName("Local_Provider-IENs_CHCS_Chicago");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                    codemap = new CodeMap();
                    codemap.setMapName("Map_Provider-CHCS-Chicago_to_NPI");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.3.42.127.56.3");
                    codemap.setFromCodeSystemName("Local_Provider-IENs_CHCS_Chicago");
                    codemap.setToCodeSystemId("2.16.840.1.113883.4.6");
                    codemap.setToCodeSystemName("National_Provider-NPIs");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                    codemap = new CodeMap();
                    codemap.setMapName("Map_Patient-EDIPN_to_CHCS-Chicago");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.3.42.10001.100001.12");
                    codemap.setFromCodeSystemName("National_Patient-EDIPNs");
                    codemap.setToCodeSystemId("2.16.840.1.113883.3.42.127.56.2");
                    codemap.setToCodeSystemName("Local_Patient-IENs_CHCS_Chicago");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                    codemap = new CodeMap();
                    codemap.setMapName("Map_Patient-CHCS-Chicago_to_EDIPN");
                    codemap.setFromCodeSystemId("2.16.840.1.113883.3.42.127.56.2");
                    codemap.setFromCodeSystemName("Local_Patient-IENs_CHCS_Chicago");
                    codemap.setToCodeSystemId("2.16.840.1.113883.3.42.10001.100001.12");
                    codemap.setToCodeSystemName("National_Patient-EDIPNs");
                    retMaps.getGetSupportedMapsReturn().add(codemap);
                }
                resultset.close();
                resultset = null;
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
        return retMaps;
    }

    /**
     * @param vaflag the vaflag to set
     */
    public void setVaflag(boolean vaflag) {
        this.vaflag = vaflag;
    }

    /**
     * @return the vaflag
     */
    public boolean isVaflag() {
        return vaflag;
    }

    /**
     * Get details.
     * 
     * @param mapdata map data
     * @return list
     */
    public List<CTSMapDetailData> getDetails(CTSMapData mapdata) {
        List<CTSMapDetailData> rtc = new ArrayList<CTSMapDetailData>();
        if (ctsflat) {
            try {
                long rows = mapsflat.getNumberOfRows();
                for (long i = 0; i < rows; ++i) {
                    String[] fields = mapsflat.getRow(i);
                    if (fields != null && fields.length >= 9 && fields[0].equalsIgnoreCase(mapdata.getFromOid()) && fields[3].equalsIgnoreCase(mapdata.getToOid())) {
                        CTSMapDetailData ddata = new CTSMapDetailData();
                        ddata.setFromOid(fields[0]);
                        ddata.setFromCode(fields[1]);
                        ddata.setFromDescription(fields[2]);
                        ddata.setToOid(fields[3]);
                        ddata.setToCode(fields[4]);
                        ddata.setToDescription(fields[5]);
                        if ("A".equalsIgnoreCase(fields[6]) || "1".equalsIgnoreCase(fields[6])) {
                            ddata.setActive(true);
                        } else {
                            ddata.setActive(false);                    
                        }
                        rtc.add(ddata);
                    }
                }
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {    
            PreparedStatement statement = null;
            ResultSet resultset = null;
//        String sql = "select mr.from_version_oid,mr.from_concept_code,fd.name,mr.to_version_oid,mr.to_concept_code,td.name,mr.active from mapping_relations mr "
//            + "left outer join code_system_concepts fd on mr.from_concept_code = fd.code and mr.from_version_oid = fd.code_system_oid "
//            + "left outer join code_system_concepts td on mr.to_concept_code = td.code and mr.to_version_oid = td.code_system_oid "
//            + "where mr.from_version_oid = ? and mr.to_version_oid = ?"
//            + "order by mr.from_version_oid,mr.from_concept_code";
            String sql = "select from_version_oid,from_concept_code,from_description,to_version_oid,to_concept_code,to_description,active from jxp_map_data where from_version_oid=? and to_version_oid=? order by 1,2 ";
    
            try {
                connect();
        
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, mapdata.getFromOid());
                statement.setString(2, mapdata.getToOid());
                resultset = statement.executeQuery();
        
                while (resultset.next()) {
                    CTSMapDetailData ddata = new CTSMapDetailData();
                    int fcount = 1;
                    ddata.setFromOid(resultset.getString(fcount++));
                    ddata.setFromCode(resultset.getString(fcount++));
                    ddata.setFromDescription(resultset.getString(fcount++));
                    ddata.setToOid(resultset.getString(fcount++));
                    ddata.setToCode(resultset.getString(fcount++));
                    ddata.setToDescription(resultset.getString(fcount++));
                    int act = resultset.getInt(fcount++);
                    if (act >= 1) {
                        ddata.setActive(true);
                    } else {
                        ddata.setActive(false);                    
                    }
                    rtc.add(ddata);
                }
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
        return rtc;
    }
    
    /**
     * Delete map.
     * 
     * @param mapdata data
     */
    public void delMap(CTSMapDetailData mapdata)  {
        if (ctsflat) {
            try {
                mapsflat.delMap(mapdata.getFromOid(), mapdata.getFromCode(), mapdata.getToOid());
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {
            PreparedStatement statement = null;
            String sql = "delete from jxp_map_data where from_version_oid = ? and to_version_oid = ? and from_concept_code = ?";
    
            try {
                connect();
    
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, mapdata.getFromOid());
                statement.setString(2, mapdata.getToOid());
                statement.setString(3, mapdata.getFromCode());
                statement.execute();
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
    }
    
    /**
     * Add map.
     * 
     * @param mapdata map data
     * @param currFromCode from code.
     */
    public void addMap(CTSMapDetailData mapdata, String currFromCode) {
        if (ctsflat) {
            try {
                String active = "I";
                if (mapdata.isActive()) {
                    active = "A";
                }
                mapsflat.addMap(mapdata.getFromOid(), mapdata.getFromCode(), mapdata.getFromDescription(), mapdata.getToOid(), mapdata.getToCode(), mapdata.getToDescription(), active);
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {
            PreparedStatement statement = null;
            if (currFromCode != null && currFromCode.length() > 0) {
                CTSMapDetailData oldmap = new CTSMapDetailData(mapdata);
                oldmap.setFromCode(currFromCode);
                delMap(oldmap);
            }
            delMap(mapdata);
            try {
                connect();
                String sql = "insert into jxp_map_data (from_version_oid, from_concept_code, from_description, to_version_oid, to_concept_code, to_description, active) values (?,?,?,?,?,?,?)";
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, mapdata.getFromOid());
                statement.setString(2, mapdata.getFromCode());
                statement.setString(3, mapdata.getFromDescription());
                statement.setString(4, mapdata.getToOid());
                statement.setString(5, mapdata.getToCode());
                statement.setString(6, mapdata.getToDescription());
                if (mapdata.isActive()) {
                    statement.setInt(7, 1);
                } else {
                    statement.setInt(7, 0);
                }
                statement.execute();
            } catch (Exception e) {
                LOGGER.error(EXCEPTION + e.getMessage(), e);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION, ex);
                }
                close();
            }
        }
    }
    
    /**
     * Get CSV data.
     *
     * @return CSV data
     */
    public byte[] getMapsCSV() {
        StringBuffer sb = new StringBuffer();
        byte[] rtc = null;
        if (ctsflat) {
            try {
                long rows = mapsflat.getNumberOfRows();
                sb.append("\"" + "FROM_OID" + QCQ + "FROM_CODE" + QCQ + "FROM_DESCRIPTION" + QCQ + "TO_OID" + QCQ + "TO_CODE" + QCQ + "TO_DESCRIPTION" + QCQ + "ACTIVE" + "\"\n");
                for (long i = 0; i < rows; ++i) {
                    String[] fields = mapsflat.getRow(i);
//                    if (fields != null && fields.length >= 9 && fields[0].equalsIgnoreCase(mapdata.getFromOid()) && fields[3].equalsIgnoreCase(mapdata.getToOid())) {
                    if (fields != null && fields.length >= 9) {
                        sb.append("\"" + fields[0] + QCQ + fields[1] + QCQ + fields[2] + QCQ + fields[3] + QCQ + fields[4] + QCQ + fields[5] + QCQ + fields[6] + "\"\n");
                    }
                }
                rtc = sb.toString().getBytes("utf-8");
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                rtc = sb.toString().getBytes("utf-8");
                String[] sql = {
//                        "select mr.from_version_oid, mr.from_concept_code, fcsc.name, mr.to_version_oid, mr.to_concept_code, tcsc.name, mr.active from "
//                            + tmsuser + ".mapping_relations mr left outer join "
//                            + tmsuser + ".CODE_SYSTEM_CONCEPTS fcsc on mr.from_version_oid = fcsc.code_system_oid and mr.from_concept_code = fcsc.code left outer join "
//                            + tmsuser + ".CODE_SYSTEM_CONCEPTS tcsc on mr.to_version_oid = tcsc.code_system_oid and mr.to_concept_code = tcsc.code order by mr.from_version_oid,mr.from_concept_code" 
                    "select from_version_oid,from_concept_code,from_description,to_version_oid,to_concept_code,to_description,active from jxp_map_data order by 1,2 "
                };
        
                connect();
    
                for (int i = 0; i < sql.length; ++i) {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                    }
                    statement = this.getConn().prepareStatement(sql[i]);
                    resultset = statement.executeQuery();
                    sb.append("\"" + "FROM_OID" + QCQ + "FROM_CODE" + QCQ + "FROM_DESCRIPTION" + QCQ + "TO_OID" + QCQ + "TO_CODE" + QCQ + "TO_DESCRIPTION" + QCQ + "ACTIVE" + "\"\n");
                    while (resultset != null && resultset.next()) {
    
                        int fpos = 1;
    
                        String foid = resultset.getString(fpos++);
                        String fcode = resultset.getString(fpos++);
                        String fname = resultset.getString(fpos++);
                        String toid = resultset.getString(fpos++);
                        String tcode = resultset.getString(fpos++);
                        String tname = resultset.getString(fpos++);
                        String tactive = resultset.getString(fpos++);
                        if (foid == null) {
                            foid = "";
                        }
                        if (fcode == null) {
                            fcode = "";
                        }
                        if (fname == null) {
                            fname = "";
                        }
                        if (toid == null) {
                            toid = "";
                        }
                        if (tcode == null) {
                            tcode = "";
                        }
                        if (tname == null) {
                            tname = "";
                        }
                        if (tactive == null) {
                            tactive = "";
                        }
                        sb.append("\"" + foid + QCQ + fcode + QCQ + fname + QCQ + toid + QCQ + tcode + QCQ + tname + QCQ + tactive + "\"\n");
                    }
                }
                rtc = sb.toString().getBytes("utf-8");
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                }
                close();
            }
        }
        return rtc;
    }

    // TODO: refactor,  combine method w/ above.
    
    /**
     * Get CSV.
     * 
     * @param fromoid from OID
     * @return CSV array
     */
    public byte[] getMapsCSV(String fromoid) {
        String linesep = System.getProperty(LINESEP);
        StringBuffer sb = new StringBuffer();
        byte[] rtc = null;
        

        if (ctsflat) {
            try {
                long rows = mapsflat.getNumberOfRows();
                sb.append("\"" + "FROM_OID" + QCQ + "FROM_CODE" + QCQ + "FROM_DESCRIPTION" + QCQ + "TO_OID" + QCQ + "TO_CODE" + QCQ + "TO_DESCRIPTION" + QCQ + "ACTIVE" + "\"\n");
                for (long i = 0; i < rows; ++i) {
                    String[] fields = mapsflat.getRow(i);
                    if (fields != null && fields.length >= 9 && fields[0].equalsIgnoreCase(fromoid)) {
                        sb.append("\"" + fields[0] + QCQ + fields[1] + QCQ + fields[2] + QCQ + fields[3] + QCQ + fields[4] + QCQ + fields[5] + QCQ + fields[6] + "\"\n");
                    }
                }
                rtc = sb.toString().getBytes("utf-8");
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                rtc = sb.toString().getBytes("utf-8");
                String[] sql = {
//                        "select mr.from_version_oid, mr.from_concept_code, fcsc.name, mr.to_version_oid, mr.to_concept_code, tcsc.name, mr.active from "
//                            + tmsuser + ".mapping_relations mr left outer join "
//                            + tmsuser + ".CODE_SYSTEM_CONCEPTS fcsc on mr.from_version_oid = fcsc.code_system_oid and mr.from_concept_code = fcsc.code left outer join "
//                            + tmsuser + ".CODE_SYSTEM_CONCEPTS tcsc on mr.to_version_oid = tcsc.code_system_oid and mr.to_concept_code = tcsc.code order by mr.from_version_oid,mr.from_concept_code" 
                    "select from_version_oid,from_concept_code,from_description,to_version_oid,to_concept_code,to_description,active from jxp_map_data where from_version_oid = ? order by 1,2 "
                };
        
                connect();
    
                for (int i = 0; i < sql.length; ++i) {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                    }
                    statement = this.getConn().prepareStatement(sql[i]);
                    statement.setString(1, fromoid);
                    resultset = statement.executeQuery();
                    sb.append("\"" + "FROM_OID" + QCQ + "FROM_CODE" + QCQ + "FROM_DESCRIPTION" + QCQ + "TO_OID" + QCQ + "TO_CODE" + QCQ + "TO_DESCRIPTION" + QCQ + "ACTIVE" + "\"" + linesep);
                    while (resultset != null && resultset.next()) {
    
                        int fpos = 1;
    
                        String foid = resultset.getString(fpos++);
                        String fcode = resultset.getString(fpos++);
                        String fname = resultset.getString(fpos++);
                        String toid = resultset.getString(fpos++);
                        String tcode = resultset.getString(fpos++);
                        String tname = resultset.getString(fpos++);
                        String tactive = resultset.getString(fpos++);
                        if (foid == null) {
                            foid = "";
                        }
                        if (fcode == null) {
                            fcode = "";
                        }
                        if (fname == null) {
                            fname = "";
                        }
                        if (toid == null) {
                            toid = "";
                        }
                        if (tcode == null) {
                            tcode = "";
                        }
                        if (tname == null) {
                            tname = "";
                        }
                        if (tactive == null) {
                            tactive = "";
                        }
                        sb.append("\"" + foid + QCQ + fcode + QCQ + fname + QCQ + toid + QCQ + tcode + QCQ + tname + QCQ + tactive + "\"" + linesep);
                    }
                }
                rtc = sb.toString().getBytes("utf-8");
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                }
                close();
            }
        }
        return rtc;
    }
    
    /**
     * Get data as sql insert.
     *
     */
    public void getMapsInsert() {
//        StringBuffer sb = new StringBuffer();
//        byte[] rtc = null;
        System.out.println("-- ctsflat=" + ctsflat);
        if (ctsflat) {
            try {
                long rows = mapsflat.getNumberOfRows();
                System.out.println("-- rows=" + rows);
//                sb.append("\"" + "FROM_OID" + QCQ + "FROM_CODE" + QCQ + "FROM_DESCRIPTION" + QCQ + "TO_OID" + QCQ + "TO_CODE" + QCQ + "TO_DESCRIPTION" + QCQ + "ACTIVE" + "\"\n");
                for (long i = 0; i < rows; ++i) {
                    String[] fields = mapsflat.getRow(i);
//                    if (fields != null && fields.length >= 9 && fields[0].equalsIgnoreCase(mapdata.getFromOid()) && fields[3].equalsIgnoreCase(mapdata.getToOid())) {
                    if (fields != null && fields.length >= 9) {
                        System.out.print("insert into JXP_MAP_DATA (from_version_oid,from_concept_code,from_description,to_version_oid,to_concept_code,to_description,active) values (");
                        System.out.print("'");
                        System.out.print(fields[0]);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fields[1]);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fields[2]);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fields[3]);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fields[4]);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fields[5]);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fields[6]);
                        System.out.print("'");
                        System.out.println(");\ncommit;");
                        //"\"" + fields[0] + QCQ + fields[1] + QCQ + fields[2] + QCQ + fields[3] + QCQ + fields[4] + QCQ + fields[5] + QCQ + fields[6] + "\"\n");
                    }
                }
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getLocalizedMessage(), ex);
            }
        } else {
            PreparedStatement statement = null;
            ResultSet resultset = null;
    
            try {
                String[] sql = {
//                        "select mr.from_version_oid, mr.from_concept_code, fcsc.name, mr.to_version_oid, mr.to_concept_code, tcsc.name, mr.active from "
//                            + tmsuser + ".mapping_relations mr left outer join "
//                            + tmsuser + ".CODE_SYSTEM_CONCEPTS fcsc on mr.from_version_oid = fcsc.code_system_oid and mr.from_concept_code = fcsc.code left outer join "
//                            + tmsuser + ".CODE_SYSTEM_CONCEPTS tcsc on mr.to_version_oid = tcsc.code_system_oid and mr.to_concept_code = tcsc.code order by mr.from_version_oid,mr.from_concept_code" 
                    "select from_version_oid,from_concept_code,from_description,to_version_oid,to_concept_code,to_description,active from jxp_map_data order by 1,2 "
                };
        
                connect();
    
                for (int i = 0; i < sql.length; ++i) {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                    } catch (Exception ex) {
                        LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                    }
                    statement = this.getConn().prepareStatement(sql[i]);
                    resultset = statement.executeQuery();
//                    sb.append("\"" + "FROM_OID" + QCQ + "FROM_CODE" + QCQ + "FROM_DESCRIPTION" + QCQ + "TO_OID" + QCQ + "TO_CODE" + QCQ + "TO_DESCRIPTION" + QCQ + "ACTIVE" + "\"\n");
                    while (resultset != null && resultset.next()) {
    
                        int fpos = 1;
    
                        String foid = resultset.getString(fpos++);
                        String fcode = resultset.getString(fpos++);
                        String fname = resultset.getString(fpos++);
                        String toid = resultset.getString(fpos++);
                        String tcode = resultset.getString(fpos++);
                        String tname = resultset.getString(fpos++);
                        String tactive = resultset.getString(fpos++);
                        if (foid == null) {
                            foid = "";
                        }
                        if (fcode == null) {
                            fcode = "";
                        }
                        if (fname == null) {
                            fname = "";
                        }
                        if (toid == null) {
                            toid = "";
                        }
                        if (tcode == null) {
                            tcode = "";
                        }
                        if (tname == null) {
                            tname = "";
                        }
                        if (tactive == null) {
                            tactive = "";
                        }
                        System.out.print("insert into JXP_MAP_DATA (from_version_oid,from_concept_code,from_description,to_version_oid,to_concept_code,to_description,active) values (");
                        System.out.print("'");
                        System.out.print(foid);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fcode);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(fname);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(toid);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(tcode);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(tname);
                        System.out.print("',");
                        System.out.print("'");
                        System.out.print(tactive);
                        System.out.print("'");
                        System.out.println(");\ncommit;");
//                        sb.append("\"" + foid + QCQ + fcode + QCQ + fname + QCQ + toid + QCQ + tcode + QCQ + tname + QCQ + tactive + "\"\n");
                    }
                }
//                rtc = sb.toString().getBytes("utf-8");
            } catch (Exception ex) {
                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
            } finally {
                try {
                    if (resultset != null) {
                        resultset.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                }
    
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (Exception ex) {
                    LOGGER.error(EXCEPTION + ex.getMessage(), ex);
                }
                close();
            }
        }
    }
    
    public void setFlat(boolean flat) {
    	ctsflat = flat;
    	if (ctsflat) {
            try {
                mapsflat.openFile();
            } catch (IOException ex) {
                LOGGER.error(EXCEPTION, ex);
            }
    	}
    }
    
    public static void main(String[] args) {
        LogHelper.reset();
        LogHelper.addConsoleLogger(Level.ERROR, "%d [%t] %-5p %c - %m%n", ConsoleAppender.SYSTEM_OUT);
    	CTSMaps cts = CTSMaps.getInstance();
    	cts.setFlat(true);
    	cts.getMapsInsert();
    }
    
    /**
     * Singleton helper class.
     *
     */
    private static class CTSMapsHolder {
        /**
         * CTSMaps object.
         */
        private static final CTSMaps INSTANCE = new CTSMaps();

        /**
         * Returns a text description of the class.
         *
         * @return Class description
         */
        @Override
        public String toString() {
            return getClass().getName() + '@'
                    + Integer.toHexString(hashCode());
        }
    }
}
