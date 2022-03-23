package com.gmail.arthurstrokov.plugin

import com.gmail.arthurstrokov.plugin.tasks.DatabaseCreateTable
import com.gmail.arthurstrokov.plugin.tasks.DatabaseDropTable
import com.gmail.arthurstrokov.plugin.tasks.DatabaseInsertData
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestGradleDatabaseInitializationPluginPlugin implements Plugin<Project> {

    void apply(Project project) {
        // Register a task
        project.tasks.register("testGradleDatabaseInitializationPlugin") {
            doLast {
                println("Hello from plugin 'testGradleDatabaseInitializationPlugin'")
            }
        }
        project.tasks.register("databaseCreateTable", DatabaseCreateTable) {
            setGroup("test database")
        }
        project.tasks.register("databaseInsertData", DatabaseInsertData) {
            setGroup("test database")
        }
        project.tasks.register("databaseDropTable", DatabaseDropTable) {
            setGroup("test database")
        }
    }
}
