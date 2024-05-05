package com.example.lwb.core.data

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection

class LWBDatabase {
    companion object {
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://158.160.12.90:3306/LWB"
            username = "root"
            password = "test"
            driverClassName = "com.mysql.cj.jdbc.Driver"
        }

        private val dataSource = HikariDataSource(hikariConfig)

        fun createCloudSqlConnection(): Connection {
            return dataSource.connection
        }
    }
}