package org.hl7.chcs.data;

import com.intersys.jdbc.CacheDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 *  CacheBaseDAO provides base data access services.
 *
 * @author <a href="mailto:karl.a.weller@leidos.com">karl.a.weller</a>
 * @version 1.0
 */
public class CacheBaseDAO {

    /**
     * Logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(CacheBaseDAO.class.getName());

    /**
     * Exception string.
     */
    private static final String EXCEPTION = "Exception: ";

    /**
     * Default Connection string.
     */
    private String defaultConnect = "jdbc:Cache://127.0.0.1:1972/CHCS";
//    private String defaultConnect = "jdbc:Cache://172.18.12.70:1972/CHCS";

    /**
     * Default user.
     */
    private String defaultUser = "JXP";

    /**
     * Default password.
     */
    private String defaultPassword = "JXPPASSWORD";

    /**
     * Connection to the database.
     */
    private Connection conn;

    /**
     *  Connection pool.
     */
    private DataSource pool;

    /**
     * Constructor.
     *
     */
    public CacheBaseDAO() {
        super();
    }

    /**
     * Constructor.
     *
     * @param connect connect
     * @param user user
     * @param password password
     */
    public CacheBaseDAO(String connect, String user, String password) {
        super();
        this.defaultConnect = connect;
        this.defaultUser = user;
        this.defaultPassword = password;
    }

    /**
     * Connects to database.
     * 
     * @throws SQLException exception
     */
    public void connect() throws SQLException {
        if (this.conn != null) {
            close();
        }

        try {
            Context env;

            String dsource = "jdbc/cacheDS";
            env = new InitialContext();
            this.pool = (DataSource) env.lookup(dsource);
            this.conn = this.pool.getConnection();
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION + ex.getMessage(), ex);
        }

        if (this.conn == null) {
//            try {
            DriverManager.registerDriver(new CacheDriver());
            this.conn = DriverManager.getConnection(
                    defaultConnect, defaultUser, defaultPassword);
//            } catch (Exception ex) {
//                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
//                throw new JXPTransformMapException(ex);
//            }
        }
    }

    /**
     * Connect to database using user and password.
     *
     * @param user User used to connect to the database
     * @param password Password used to connect to the database
     * @throws SQLException exception
     */
    public void connect(String user, String password) throws SQLException {
        if (this.conn != null) {
            close();
        }

        try {
            Context env;

            String dsource = "jdbc/cacheDS";
            env = new InitialContext();
            this.pool = (DataSource) env.lookup(dsource);
            this.conn = this.pool.getConnection(user, password);
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION + ex.getMessage(), ex);
        }

        if (this.conn == null) {
//            try {
            DriverManager.registerDriver(new CacheDriver());
            this.conn = DriverManager.getConnection(
                    defaultConnect, user, password);
//            } catch (Exception ex) {
//                LOGGER.error(EXCEPTION + ex.getMessage(), ex);
//                throw new JXPTransformMapException(ex);
//            }
        }
    }

    /**
     * Close connection to database.
     */
    public void close() {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (Exception ex) {
            LOGGER.error(EXCEPTION + ex.getMessage(), ex);
        }
        this.conn = null;
        this.pool = null;
    }

    /**
     * Get connection.
     *
     * @return the connection
     */
    protected Connection getConn() {
        return conn;
    }

    /**
     * Set connection.
     *
     * @param inconn the connection to set
     */
    protected void setConn(Connection inconn) {
        this.conn = inconn;
    }

    /**
     * Make sure things get cleaned up.
     *
     * @throws Throwable exception
     */
    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    /**
     * Returns a text description of the class.
     *
     * @return Class description
     */
    @Override
    public String toString() {
        return getClass().getName() + '@' + Integer.toHexString(hashCode());
    }
    
//    public static void main(String[] args) throws JXPTransformMapException, ParserConfigurationException {
//        CacheBaseDAO dao = new CacheBaseDAO();
//        dao.connect();
//        String sql = "select * from chcs.drug_50";
//        PreparedStatement statement;
//        ResultSet resultset;
//        
//        javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
//        javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
//        org.w3c.dom.Document doc = builder.newDocument();
//        org.w3c.dom.Element results = doc.createElement("Results");
//        doc.appendChild(results);
//
//
//        statement = null;
//        resultset = null;
//
//        try {
//            statement = dao.getConn().prepareStatement(sql);
//
//            resultset = statement.executeQuery();
//            java.sql.ResultSetMetaData rsmd = resultset.getMetaData();
//            int colCount = rsmd.getColumnCount();
////            ByteArrayOutputStream baos = new ByteArrayOutputStream();
////            ObjectOutputStream oos = new ObjectOutputStream(baos);
////            oos.writeObject(resultset);
////            byte[] out = baos.toByteArray();
////            String str = out.toString();
//
//            while (resultset != null && resultset.next()) {
//                org.w3c.dom.Element row = doc.createElement("Row");
//                results.appendChild(row);
//                for (int i = 1; i <= colCount; i++) {
//                    String columnName = rsmd.getColumnName(i);
//                    Object value = resultset.getObject(i);
//                    if (value != null) {
//                        org.w3c.dom.Element node = doc.createElement(columnName);
//                        node.appendChild(doc.createTextNode(value.toString()));
//                        row.appendChild(node);
//                    }
//                  }
//            }
//
////            javax.xml.transform.dom.DOMSource domSource = new javax.xml.transform.dom.DOMSource(doc);
////            javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
////            javax.xml.transform.Transformer transformer = tf.newTransformer();
////            transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
////            transformer.setOutputProperty(javax.xml.transform.OutputKeys.METHOD, "xml");
////            transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "ISO-8859-1");
////            java.io.StringWriter sw = new java.io.StringWriter();
////            javax.xml.transform.stream.StreamResult sr = new javax.xml.transform.stream.StreamResult(sw);
////            transformer.transform(domSource, sr);
////
////            System.out.println(sw.toString());
//            
//            DOMImplementationLS domImplLS = (DOMImplementationLS) doc.getImplementation(); 
//            LSSerializer serializer = domImplLS.createLSSerializer(); 
//            String str = serializer.writeToString(doc.getFirstChild()); 
//            System.out.println(str);
//            
//
//        } catch (Exception ex) {
//            System.out.println(EXCEPTION + ex.getMessage());
//            ex.printStackTrace();
//            throw new JXPTransformMapException(ex);
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
//        }
//        dao.close();
//    }
}
