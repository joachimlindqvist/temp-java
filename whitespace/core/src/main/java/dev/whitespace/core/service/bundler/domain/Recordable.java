package dev.whitespace.core.service.bundler.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jspecify.annotations.Nullable;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

public class Recordable {
    public Meta meta;
    public @Nullable Cursor cursor;
    public @Nullable Dom dom;
    public @Nullable Location location;
    public @Nullable Log log;
    public @Nullable Storage storage;
	public @Nullable Stylesheet stylesheet;
	public @Nullable UserInput userInput;
	public @Nullable WindowSize windowSize;
	public @Nullable Scroll scroll;
	public @Nullable Http http;
	public Http.Request httpRequest;
	public Http.Response httpResponse;

    public static class Cursor {
        public String target;
        public Float x;
        public Float y;
    }

    public static class Dom {
        public String property;
        public String target;
        public List<Argument> arguments;

        public static class Argument {
            public String string;
            public Float number;
            public String json;
            @JsonProperty("boolean")
            public Boolean bool;
            public String undefined;
            public String ref;
            public Html.Node node;
        }

        public Boolean isProperty(String prop) {
            return property.equalsIgnoreCase(prop);
        }

        public Boolean hasArgumentSize(Integer size) {
            return arguments.size() == size;
        }
    }

    public static class Meta {
        public Integer frame;
        public Integer transpiredAt;
    }

    public static class Location {
        public String location;
        public URI base;
    }

    public static class Log {
        public String level;
        public String message;
    }

    public static class Storage {
        public String property;
        public String type;
        public List<String> arguments;
    }

    public static class Stylesheet {
        public String action;
        public Integer atIndex;
        public List<Argument> arguments;

        public static class Argument {
            public @Nullable String string;
            public @Nullable Integer number;
        }
    }

    public static class UserInput {
        public String action;
        public String target;
        public List<String> arguments;
        public String targetDisplayName;
        public Html.Node targetNode;
    }

    public static class WindowSize {
        public Float width;
        public Float height;
    }

    public static class Scroll {
        public String target;
        public Float x;
        public Float y;
    }


    public class Http {
        public @Nullable Meta requestMeta;
        public @Nullable Request request;
        public @Nullable Meta responseMeta;
        public @Nullable Response response;

        public class Request {
            public HashMap<String, List<String>> headers;
            public String method;
            public String url;
            public String requestId;
            public String body;
        }

        public class Response {
            public HashMap<String, List<String>> headers;
            public String method;
            public String url;
            public String requestId;
            public String body;
            public Integer status;
        }
    }
}


//@Serializable
//data class HttpRequest(
//        val headers: Map<String, List<String>>,
//        var method: String,
//        var url: String,
//        var requestId: String,
//        var body: String?
//)
//
//@Serializable
//data class HttpResponse(
//        val headers: Map<String, List<String>>,
//        var method: String,
//        var url: String,
//        var requestId: String,
//        var status: Int,
//        var body: String?
//)
//
//@Serializable
//data class HttpTransaction(
//        val request: HttpRequest?,
//        val requestMeta: Meta?,
//        val response: HttpResponse?,
//        val responseMeta: Meta?,
//        )
