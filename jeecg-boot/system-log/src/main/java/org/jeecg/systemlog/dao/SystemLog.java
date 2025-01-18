package org.jeecg.systemlog.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "test_log")
public class SystemLog {
    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text)
    private String message; // JSON 字符串形式的日志内容

    @Field(name = "@version", type = FieldType.Text)
    @JsonProperty("@version")
    private String version; // @version

    @Field(type = FieldType.Nested, includeInParent = true)
    private Event event;    // 嵌套对象 event

    @Field(name = "@timestamp", type = FieldType.Text)
    @JsonProperty("@timestamp")
    private String timestamp; // @timestamp

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", version='" + version + '\'' +
                ", event=" + event +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    // 内部类 Event
    public static class Event {
        private String original; // JSON 字符串形式的原始日志内容

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "original='" + original + '\'' +
                    '}';
        }
    }
}
