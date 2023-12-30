# CassandraClient
Detaila client for java

## Usage
```Java
public class Main {
    public static void main(String[] args) {
        CassandraClient cassandraClient = new CassandraClient("127.0.0.1", 9042, "mykeyspace");

        // Örnek bir tablo oluştur
        cassandraClient.createTable("users", "id UUID PRIMARY KEY, name TEXT, age INT");

        // Veri ekle
        cassandraClient.insertData("users", "id, name, age", "uuid(), 'John Doe', 30");

        // Veri seç
        ResultSet resultSet = cassandraClient.selectData("users", "name = 'John Doe'");
        resultSet.forEach(row -> System.out.printf("User: %s, Age: %d\n", row.getString("name"), row.getInt("age")));

        // Veriyi güncelle
        cassandraClient.updateData("users", "age = 31", "name = 'John Doe'");

        // Güncellenmiş veriyi getir
        resultSet = cassandraClient.selectData("users", "name = 'John Doe'");
        resultSet.forEach(row -> System.out.printf("Updated User: %s, Age: %d\n", row.getString("name"), row.getInt("age")));

        // Veriyi sil
        cassandraClient.deleteData("users", "name = 'John Doe'");

        // Silinmiş veriyi getir (boş olmalı)
        resultSet = cassandraClient.selectData("users", "name = 'John Doe'");
        resultSet.forEach(row -> System.out.println("User not found."));

        // Kapat
        cassandraClient.close();
    }
}
```

Driver for mave
```Markdown
<dependencies>
    <!-- Cassandra Driver -->
    <dependency>
        <groupId>com.datastax.oss</groupId>
        <artifactId>java-driver-core</artifactId>
        <version>4.14.0</version> <!-- Güncel sürümü kontrol edin -->
    </dependency>
</dependencies>
```

Driver for Gradlle
```Markdown
dependencies {
    // Cassandra Driver
    implementation 'com.datastax.oss:java-driver-core:4.14.0' // Güncel sürümü kontrol edin
}
```
