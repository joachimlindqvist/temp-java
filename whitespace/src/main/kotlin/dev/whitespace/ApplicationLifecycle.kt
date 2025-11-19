package dev.whitespace

import io.quarkus.logging.Log
import io.quarkus.runtime.Shutdown
import io.quarkus.runtime.Startup
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty

@ApplicationScoped
class ApplicationLifecycle(
    @field:ConfigProperty(name = "quarkus.http.port") private var httpPort: Int = -1,
    @field:ConfigProperty(name = "quarkus.http.host") private var httpHost: String = "<none>"
) {

    @Startup
    fun startUp() {
        Log.info("http listening $httpHost:$httpPort")
    }

    @Shutdown
    fun shutdown() {
        Log.info("shutting down")
    }
}