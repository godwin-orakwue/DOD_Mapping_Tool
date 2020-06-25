/**
 * 
 */
package org.hl7.chcs.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

/**
 * CHCS reference data.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class CHCSRefData extends CacheBaseDAO {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CHCSRefData.class.getName());
    
    /**
     * Exception.
     */
    private static final String EXCEPTION = "Exception: ";

    /**
     * 
     */
    public CHCSRefData() {
        super();
    }

    /**
     * @param connect connect
     * @param user user
     * @param password password
     */
    public CHCSRefData(String connect, String user, String password) {
        super(connect, user, password);
    }

    /**
     * Get lookup values.
     * 
     * @param oid OID
     * @return values
     */
    public List<SelectItem> getLookupValues(String oid) {
        List<SelectItem> rtc = new ArrayList<SelectItem>();
        PreparedStatement statement = null;
        ResultSet resultset = null;
        String sql = "";
        
        if ("2.16.840.1.113883.3.42.127.100001.289".equals(oid)) {
            // collection method
//            sql = "select ien,name from chcs.";
        } else if ("2.16.840.1.113883.3.42.127.56.247".equals(oid)) {
            // collection sample
            sql = "select ien,name||ifnull(tube_top_color,' ',' ('||tube_top_color||')') from chcs.collection_sample_62 order by upper(name),ien";
        } else if ("2.16.840.1.113883.3.42.127.56.255.99".equals(oid)) {
            // consult procedure
            sql = "select ien,name from chcs.ancillary_procedure_108_1 order by UPPER(name),ien";
        } else if ("2.16.840.1.113883.3.42.127.56.246".equals(oid)) {
            // etiology
            sql = "select ien,name from chcs.etiology_field_61_2 order by upper(name),ien";
        } else if ("2.16.840.1.113883.3.42.127.56.281".equals(oid)) {
            // lab test
            sql = "select ien,name from chcs.lab_test_60 where inactive_flag is null order by upper(name),ien";
        } else if ("2.16.840.1.113883.3.42.127.56.282".equals(oid)) {
            // location
            sql = "select ien,name from chcs.hospital_location_44 order by upper(name),ien";
        } else if ("2.16.840.1.113883.3.42.127.56.296".equals(oid)) {
            // micro
//            sql = "select ien,name from chcs.";
//        } else if ("2.16.840.1.113883.3.42.127.100001.256".equals(oid)) {
            // priority
//            sql = "select ien,name from chcs.";
        } else if ("2.16.840.1.113883.3.42.127.56.255".equals(oid)) {
            // rad procedures
            sql = "select ien,name from chcs.radiology_procedures_71 where DBA_INACTIVATION_FLAG is null and suppress='DO NOT SUPPRESS' order by upper(name)";
        } else if ("2.16.840.1.113883.3.42.127.100001.293".equals(oid)) {
            // result category codes
            sql = "select ien,diagnostic_code from chcs.result_category_78_3 order by diagnostic_code,ien";
        } else if ("2.16.840.1.113883.3.42.127.56.295".equals(oid)) {
            // specimen (topology)
            sql = "select ien,name from chcs.topography_field_61 order by upper(name),ien";
        } else if ("2.16.840.1.113883.3.42.127.56.232".equals(oid)) {
            // users
            sql = "select ien,name from chcs.user_3 where termination_date is null order by upper(name),ien";
        }
        if (sql.length() > 0) {
            try {
                connect();
                statement = this.getConn().prepareStatement(sql);
                resultset = statement.executeQuery();
        
                while (resultset.next()) {
                    SelectItem ddata = new SelectItem();
                    int fcount = 1;
                    String ien = resultset.getString(fcount++);
                    String name = resultset.getString(fcount++);
                    ddata.setLabel(name + " (" + ien + ")");
                    ddata.setValue(ien + "|" + name);
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
    
}
