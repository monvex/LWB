package com.example.lwb.core.data

import java.sql.Connection
import java.sql.DriverManager

class LWBDatabase {
    companion object {
        fun createCloudSqlConnection(): Connection {
            val url = "jdbc:mysql://158.160.12.90:3306/LWB"
            val user = "root"
            val password = "test"
            return DriverManager.getConnection(url, user, password)
        }
    }
}