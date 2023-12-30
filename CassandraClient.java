import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

public class CassandraClient {

    private CqlSession session;

    public CassandraClient(String contactPoint, int port, String keyspace) {
        this.session = CqlSession.builder()
                .addContactPoint(contactPoint)
                .withLocalDatacenter("datacenter1")
                .withKeyspace(keyspace)
                .build();
    }

    public void createTable(String tableName, String schema) {
        SimpleStatement statement = SimpleStatement.builder("CREATE TABLE IF NOT EXISTS " + tableName + " (" + schema + ")")
                .build();
        session.execute(statement);
    }

    public void insertData(String tableName, String columns, String values) {
        SimpleStatement statement = SimpleStatement.builder("INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")")
                .build();
        session.execute(statement);
    }

    public ResultSet selectData(String tableName, String condition) {
        SimpleStatement statement = SimpleStatement.builder("SELECT * FROM " + tableName + " WHERE " + condition)
                .build();
        return session.execute(statement);
    }

    public void updateData(String tableName, String setClause, String condition) {
        SimpleStatement statement = SimpleStatement.builder("UPDATE " + tableName + " SET " + setClause + " WHERE " + condition)
                .build();
        session.execute(statement);
    }

    public void deleteData(String tableName, String condition) {
        SimpleStatement statement = SimpleStatement.builder("DELETE FROM " + tableName + " WHERE " + condition)
                .build();
        session.execute(statement);
    }

    public void close() {
        session.close();
    }
}
