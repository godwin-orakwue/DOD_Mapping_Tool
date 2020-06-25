package org.hl7.chcs.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 *  PatientLookup queries CHCS to retrieve patient id's.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class PatientLookup extends CacheBaseDAO {

    /**
     * Class UID.
     */
    private static final long serialVersionUID = -4580012113840891138L;

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(PatientLookup.class.getName());

    /**
     * Exception string.
     */
    private static final String EXCEPTION = "Exception: ";

    /**
     * Constructor.
     */
    public PatientLookup() {
        super();
    }

    /**
     * Constructor.
     *
     * @param connect connect
     * @param user user
     * @param password password
     */
    public PatientLookup(String connect, String user, String password) {
        super(connect, user, password);
    }

    /**
     * Retrieve the IEN Based on the EDIPN.
     *
     * @param edipn EDIPN
     * 
     * @return IEN
     * @throws SQLException mapping exception
     */
    public String retrieveIEN(String edipn) throws SQLException {
        String ret = "";
        
        String sql = "select ien from chcs.patient_2 where patient_identifier = ?";
        PreparedStatement statement;
        ResultSet resultset;

        statement = null;
        resultset = null;

        try {
            connect();
            statement = this.getConn().prepareStatement(sql);
            statement.setString(1, edipn);

            resultset = statement.executeQuery();
            if (resultset != null && resultset.next()) {
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
        if (ret == null || ret.length() == 0) {
            sql = "select ien from chcs.patient_2 where dod_id_ = ?";

            statement = null;
            resultset = null;

            try {
                connect();
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, edipn);

                resultset = statement.executeQuery();
                if (resultset != null && resultset.next()) {
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
     * Retrieve the EDIPN Based on the IEN.
     *
     * @param ien IEN
     * 
     * @return EDIPN
     * @throws SQLException mapping exception
     */
    public String retrieveEDIPN(String ien) throws SQLException {
        String ret = "";
        String sql = "select ien,name,dod_id_ from chcs.patient_2 where ien=?";
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
                ret = resultset.getString(3);
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
        if (ret == null || ret.length() == 0) {
            sql = "select ien,name,patient_identifier from chcs.patient_2 where ien=?";

            statement = null;
            resultset = null;

            try {
                connect();
                statement = this.getConn().prepareStatement(sql);
                statement.setString(1, ien);

                resultset = statement.executeQuery();
                while (resultset != null && resultset.next()) {
                    ret = resultset.getString(3);
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
     * Test method.
     *
     */
//    public void retrieveTest() {
////        String sql = "select rowid,patient_identifier from chcs.patient_2 where patient_identifier = '1028813151'  order by rowid";
//        String sql =
//            "select rowid,ien,name,patient_identifier from chcs.patient_2 where patient_identifier like '1%' order by ien";
//        PreparedStatement statement;
//        ResultSet resultset;
//
//        statement = null;
//        resultset = null;
//
//        try {
//            connect();
//            statement = this.getConn().prepareStatement(sql);
//
//            resultset = statement.executeQuery();
//            while (resultset.next()) {
//                String ret1 = resultset.getString(1);
//                String ret2 = resultset.getString(2);
//                String ret3 = resultset.getString(3);
//                String ret4 = resultset.getString(4);
//                System.out.println("." + ret1 + ". = ."
//                        + ret2 + ". = ." + ret3 + ". = " + ret4);
//            }
//        } catch (Exception ex) {
//            LOGGER.error(EXCEPTION + ex.getMessage(), ex);
//        } finally {
//            if (resultset != null) {
//                try {
//                    resultset.close();
//                } catch (SQLException e) {
//                    LOGGER.error(EXCEPTION + e.getMessage(), e);
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    LOGGER.error(EXCEPTION + e.getMessage(), e);
//                }
//            }
//            close();
//        }
//    }

    /**
     * Test entry point.
     *
     * @param args arguments
     */
//    public static void main(final String[] args) {
//        try {
//            PatientLookup plookup = new PatientLookup();
//            String ret = plookup.retrieveEDIPN("1137118");
//            System.out.println("Ret = " + ret);
//
//            plookup.retrieveTest();
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
