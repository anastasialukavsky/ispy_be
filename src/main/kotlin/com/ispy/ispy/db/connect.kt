package com.ispy.ispy.db

import java.sql.DriverManager

fun main() {
    val userName = "postgres"
    val password = "postgrespass"
    val url = "jdbc:postgresql://ispy-db.c10m0gowa0gw.us-east-1.rds.amazonaws.com:5432/postgres"


    try {
        DriverManager.getConnection(url, userName, password).use { conn ->

        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}