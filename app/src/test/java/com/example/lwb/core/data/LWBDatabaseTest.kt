package com.example.lwb.core.data
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.sql.DriverManager
import java.sql.Connection
import java.sql.SQLException

class LWBDatabaseTest {

    @Test
    fun `createCloudSqlConnection returns valid connection`() {
        val connection = LWBDatabase.createCloudSqlConnection()
        assertNotNull(connection)
        assertTrue(connection.isValid(1))
        connection.close()
    }

    @Test
    fun `createCloudSqlConnection throws exception when connection fails`() {
        LWBDatabase.hikariConfig.jdbcUrl = "jdbc:mysql://invalid:3306/LWB"
        assertThrows<SQLException> {
            LWBDatabase.createCloudSqlConnection().use { conn ->
                conn.createStatement().use { it.execute("SELECT 1") }
            }
        }
    }
}