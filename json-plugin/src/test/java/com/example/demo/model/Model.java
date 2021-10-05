package com.example.demo.model;

import com.example.demo.json.deserialize.JsonDeserializable;
import com.example.demo.json.serialize.JsonSerializable;
import com.example.demo.json.serialize.Serializer;

import java.util.Map;

@Serializer
public class Model extends JsonDeserializable implements JsonSerializable {
    Map<String, Map<String, Map<String, Event>>> map;

    public Map<String, Map<String, Map<String, Event>>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Map<String, Event>>> map) {
        this.map = map;
    }

    public String a() {
        java.lang.StringBuilder builder = new java.lang.StringBuilder();
        builder.append("{");
        builder.append("\"map\":");
        builder.append("{");
        if (((Model) this).getMap() != null) {
            java.util.Iterator it1 = ((Model) this).getMap().entrySet().iterator();
            while (it1.hasNext()) {
                java.lang.Object next = it1.next();
                builder.append("\"");
                builder.append(((java.util.Map.Entry<java.lang.String, Event>) next).getKey());
                builder.append("\": ");
                builder.append(((Event) ((java.util.Map.Entry<java.lang.String, Event>) next).getValue()).toJson());
                builder.append(",");
                builder.append(",");
            }
        }
        builder.append("}");
        builder.append("}");
        if (!"this".equals("this")) {
            builder.append(",");
        }
        return builder.toString().replace(",}", "}").replace(",]", "]").replace(",,", ",");
    }
}