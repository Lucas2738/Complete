package it.sisal.model;

import it.sisal.json.deserialize.JsonDeserializable;
import it.sisal.json.serialize.JsonSerializable;
import it.sisal.json.serialize.SisalSerializer;

import java.util.List;
import java.util.Map;
import java.util.Set;

@SisalSerializer
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
        if (((it.sisal.model.Model) this).getMap() != null) {
            java.util.Iterator it1 = ((it.sisal.model.Model) this).getMap().entrySet().iterator();
            while (it1.hasNext()) {
                java.lang.Object next = it1.next();
                builder.append("\"");
                builder.append(((java.util.Map.Entry<java.lang.String, it.sisal.model.Event>) next).getKey());
                builder.append("\": ");
                builder.append(((it.sisal.model.Event) ((java.util.Map.Entry<java.lang.String, it.sisal.model.Event>) next).getValue()).toJson());
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