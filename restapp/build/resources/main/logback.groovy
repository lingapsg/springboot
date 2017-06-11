import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import net.logstash.logback.encoder.LogstashEncoder
import net.logstash.logback.fieldnames.LogstashFieldNames

import static ch.qos.logback.classic.Level.INFO

appender("Console-Appender", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    }
}
appender("File-Appender", FileAppender) {
    file = "logs/restapp.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        outputPatternAsHeader = true
    }
}

appender("Logstash-Appender", FileAppender) {
    file = "logs/logstash.log"
    encoder(LogstashEncoder) {
        fieldNames(LogstashFieldNames) {
            message = 'msg'
            timestamp = '@time'
        }
    }
}

root(INFO, ["Console-Appender", "File-Appender", "Logstash-Appender"])