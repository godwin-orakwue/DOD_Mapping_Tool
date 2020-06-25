package org.hl7.chcs.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 *  ProviderLookup queries CHCS to retrieve provider id's.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class ProviderLookup extends CacheBaseDAO {

    /**
     * Class UID.
     */
    private static final long serialVersionUID = 6073663554208283082L;

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ProviderLookup.class.getName());

    /**
     * Exception string.
     */
    private static final String EXCEPTION = "Exception: ";

    /**
     * Constructor.
     *
     */
    public ProviderLookup() {
        super();
    }

    /**
     * Constructor.
     *
     * @param connect connect
     * @param user user
     * @param password password
     */
    public ProviderLookup(String connect, String user, String password) {
        super(connect, user, password);
    }

    /**
     * Retrieve the NPI Based on the IEN.
     *
     * @param ien IEN
     * 
     * @return NPI
     * @throws SQLException exception
     */
    public String retrieveNPI(String ien) throws SQLException {
        String ret = "";
        String sql =
            "select npi_id,ien from chcs.provider_6 where ien = ?";
        PreparedStatement statement;
        ResultSet resultset;

        statement = null;
        resultset = null;

        try {
            connect();
            statement = this.getConn().prepareStatement(sql);
            statement.setString(1, ien);

            resultset = statement.executeQuery();
            while (resultset != null && resultset.next()) {
                ret = resultset.getString(1);
            }
//        } catch (Exception ex) {
//            LOGGER.error(EXCEPTION + ex.getMessage(), ex);
//            throw new JXPTransformMapException(ex);
        } finally {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    LOGGER.error(EXCEPTION + e.getMessage(), e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(EXCEPTION + e.getMessage(), e);
                }
            }
            close();
        }
        return ret;
    }

    /**
     * Retrieve the IEN Based on the NPI.
     *
     * @param innpi NPI 
     * 
     * @return IEN
     * @throws SQLException exception
     */
    @SuppressWarnings("all")
    public String retrieveIEN(String innpi) throws SQLException {
        String npi = innpi;
        String ret = "";
        String userid = "";
        int userien = 0;
        boolean idnumber = false;
        String sql = "select ien,npi_id from chcs.provider_6 where npi_id = ?";
        PreparedStatement statement;
        ResultSet resultset;

        statement = null;
        resultset = null;
        if (sql.length() > 0) {
            try {
                connect();
                statement = this.getConn().prepareStatement(sql);
                if (idnumber) {
                    statement.setInt(1, userien);
                } else {
                    statement.setString(1, npi);
                }

                resultset = statement.executeQuery();
                while (resultset != null && resultset.next()) {
                    ret = resultset.getString(1);
                }
//            } catch (Exception ex) {
//                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
//                throw new JXPTransformMapException(ex);
            } finally {
                if (resultset != null) {
                    try {
                        resultset.close();
                    } catch (SQLException e) {
                        LOGGER.error(EXCEPTION + e.getMessage(), e);
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        LOGGER.error(EXCEPTION + e.getMessage(), e);
                    }
                }
                close();
            }
        }
        return ret;
    }

    /**
     * Test entry point.
     *
     * @param args arguments
     */
//    public static void main(String[] args) {
//        try {
////            userien   ssn          provien           npi           provssn
////            25        398564466    1035     1035     1356321608
////            111127    000100000    42796    42796    8977974954    000100000
////            111129    555560505    42798    42798    8977974962    555560505
////            111137    000003000    42808    42808    8977974970    000003000
////            111141    555900476    42813    42813    8977974988    555900476
//            
//            ProviderLookup plookup = new ProviderLookup();
//            String ret = plookup.retrieveNPI("42796");
//            System.out.println("npi Ret = " + ret);
//            
//            ret = plookup.retrieveIEN("8977974954", null, true); // npi
//            System.out.println("provien npi Ret = " + ret);
//
//            ret = plookup.retrieveIEN("000100000", "2.16.840.1.113883.4.1", true); //ssn
//            System.out.println("provien ssn Ret = " + ret);
//
//            ret = plookup.retrieveIEN("8977974954", "2.16.840.1.113883.4.6", false); // npi
//            System.out.println("userien npi Ret = " + ret);
//
//            ret = plookup.retrieveIEN("000100000", "2.16.840.1.113883.4.1", false); //ssn
//            System.out.println("userien ssn Ret = " + ret);
//
//            ret = plookup.retrieveIEN("83196", "4.3.7.1", true); // va user ien
//            System.out.println("provien vauser Ret = " + ret);
//            
//        } catch (Exception ex) {
//            System.out.println(EXCEPTION + ex);
//        }
//    }

   /**
    * Returns a text description of the class.
    *
    * @return Class description
    */
    @Override
    public String toString() {
        return getClass().getName() + '@' + Integer.toHexString(hashCode());
    }
}
