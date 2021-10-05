package com.example.demo.json.deserialize;

import com.example.demo.AbstractJson;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import it.sisal.exceptions.ParsingException;
import javassist.*;
import javassist.bytecode.DuplicateMemberException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Mojo(name = Deserialize.JSON_PREFIX)
public class Deserialize extends AbstractJson {

    public static final String JSON_PREFIX = "deserialize";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Set<Class> classes = findAllMatchingTypes(Deserializer.class);

        classes.forEach(c -> {
            try {
                writeClass(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void writeClass(Class c) throws Exception {
        Field[] fields = c.getDeclaredFields();

        Map<String, Method> methods = new HashMap<>();
        Arrays.asList(c.getMethods()).stream().filter(m -> m.getName().startsWith("set")).forEach(m -> methods.put(m.getName().replaceFirst("set", "").toLowerCase(), m));

        Map<Field, Method> fieldSetterMap = new HashMap<>();
        for (Field field : fields) {
            fieldSetterMap.put(field, methods.get(field.getName().toLowerCase()));
        }


        StringBuilder string = new StringBuilder();
        string.append("jParser.nextToken();\n");
        string.append("String fieldname = jParser.getCurrentName() != null ? java.lang.String.valueOf(jParser.getCurrentName()).toLowerCase() : null;\n");
        parse(c, string, "result");

        string.append("return result;");


        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(new LoaderClassPath(getClassLoader(this.project)));
        pool.appendClassPath(new LoaderClassPath(JsonFactory.class.getClassLoader()));
        pool.appendClassPath(new LoaderClassPath(JSONObject.class.getClassLoader()));
        CtClass ctClass = pool.get(c.getName());

        try {
            CtMethod method3 = CtNewMethod.make("public static " + c.getName() + " toObject(" + JsonParser.class.getName() + " jParser) throws " + Exception.class.getName() + "{" +
                    string.toString() +
                    "}", ctClass);

            ctClass.addMethod(method3);
            ctClass.writeFile(targetDir);

            ctClass.toClass(new ClassLoader() {

                @Override
                public Class<?> loadClass(String s) throws ClassNotFoundException {
                    return getClassLoader(project).loadClass(s);
                }
            });
            ctClass.defrost();
        } catch (DuplicateMemberException e) {
            System.err.println(c + " has already a builder method than it will not be overwrite!");
        }

        try {
            System.out.println(c);
            CtMethod method1 = CtNewMethod.make("public static " + c.getName() + " toObject(" + JsonFactory.class.getName() + " jsonFactory, java.lang.String json) throws " + Exception.class.getName() + "{" +
                    JsonParser.class.getName() + " jParser = jsonFactory.createParser(json);" +
                    "return toObject(jParser);" +
                    "}", ctClass);
            ctClass.addMethod(method1);
            ctClass.writeFile(targetDir);
            ctClass.defrost();
        } catch (DuplicateMemberException e) {
            System.err.println(c + " has already a builder method than it will not be overwrite!");
        }
        try {
            CtMethod method2 = CtNewMethod.make("public static " + c.getName() + " toObject(" + String.class.getName() + " json) throws " + Exception.class.getName() + "{" +
                    JsonFactory.class.getName() + " jsonFactory = new com.fasterxml.jackson.core.JsonFactory();" +
                    JsonParser.class.getName() + " jParser = jsonFactory.createParser(json);" +
                    "return toObject(jsonFactory, json);" +
                    "}", ctClass);

            ctClass.addMethod(method2);
            ctClass.writeFile(targetDir);

            ctClass.toClass(new ClassLoader() {

                @Override
                public Class<?> loadClass(String s) throws ClassNotFoundException {
                    return getClassLoader(project).loadClass(s);
                }
            });
            ctClass.defrost();
        } catch (DuplicateMemberException e) {
            System.err.println(c + " has already a builder method than it will not be overwrite!");
        }


    }


    public StringBuilder parse(Class c, final StringBuilder string, String fieldName) {

        Field[] fields = c.getDeclaredFields();


        Map<String, Method> methods = new HashMap<>();
        Arrays.asList(c.getMethods()).stream().filter(m -> m.getName().startsWith("set")).forEach(m -> methods.put(m.getName().replaceFirst("set", "").toLowerCase(), m));

        Map<Field, Method> fieldSetterMap = new HashMap<>();
        for (Field field : fields) {
            fieldSetterMap.put(field, methods.get(field.getName().toLowerCase()));
        }

        string.append(c.getName() + " " + fieldName + " = new " + c.getName() + "();" +
                "try{" +
                "while (jParser.nextToken() != " + JsonToken.class.getName() + ".END_OBJECT && jParser.getCurrentToken() != null) {\n" +
                "   fieldname = jParser.getCurrentName() != null ? " + String.class.getName() + ".valueOf(jParser.getCurrentName()).toLowerCase() : null;\n");
        fieldSetterMap.forEach((f, m) -> {
            string.append("if(\"" + f.getName().toLowerCase() + "\".equals(fieldname)){\n");

            if (Integer.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n" +
                        fieldName + "." + m.getName() + "(new " + f.getType().getName() + "(jParser.getIntValue()));\n");
                string.append("continue;");

            } else if (Long.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n" +
                        fieldName + "." + m.getName() + "(new " + f.getType().getName() + "(jParser.getLongValue()));\n");
                string.append("continue;");

            } else if (Short.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n" +
                        fieldName + "." + m.getName() + "(new " + f.getType().getName() + "(jParser.getShortValue()));\n");
                string.append("continue;");

            } else if (Float.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n" +
                        fieldName + "." + m.getName() + "(new " + f.getType().getName() + "(jParser.getFloatValue()));\n");
                string.append("continue;");

            } else if (Boolean.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n" +
                        fieldName + "." + m.getName() + "(new " + f.getType().getName() + "(jParser.getBooleanValue()));\n");
                string.append("continue;");

            } else if (String.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n" +
                        fieldName + "." + m.getName() + "(jParser.getText());\n");
                string.append("continue;");

            } else if (LocalDateTime.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n");
                JsonParser a;
                string.append("if(jParser.getCurrentValue() != null){");
                try {
                    TimeFormat timeFormatAnn = f.getAnnotation(TimeFormat.class);
                    JsonTimeType type = timeFormatAnn.type();
                    switch(type){
                        case STRING:
                            String dateFormat = timeFormatAnn.format();
                            string.append(fieldName + "." + m.getName() + "(" + LocalDateTime.class.getName() + ".parse(jParser.getText(), "+ DateTimeFormatter.class.getName()+".ofPattern(\""+dateFormat+"\")));\n");
                            break;
                        case LONG:
                            string.append(fieldName + "." + m.getName() + "(" + LocalDateTime.class.getName() + ".ofInstant(" + Instant.class.getName() + ".ofEpochMilli(jParser.getLongValue()), " + TimeZone.class.getName() + ".getDefault().toZoneId()));\n");
                            break;
                    }
                }catch(NullPointerException e){
                    System.err.println("Error deserialize LocalDateTime field " + f.getName());
                }
                string.append("}");
                string.append("continue;");

            }  else if (Collection.class.isAssignableFrom(f.getType())) {
                Type listType = f.getGenericType();
                parseCollection(listType, string, f.getName() + "List");
                string.append(fieldName + "." + m.getName() + "(" + f.getName() + "List);\n");
                string.append("continue;");

            } else if (Map.class.isAssignableFrom(f.getType())) {
                Type mapType = f.getGenericType();
                parseMap(mapType, string, f.getName() + "Map");
                string.append(fieldName + "." + m.getName() + "(" + f.getName() + "Map);\n");
                string.append("continue;");

            } else if (!Collection.class.isAssignableFrom(f.getType())) {
                string.append("jParser.nextToken();\n");
                parse(f.getType(), string, f.getName() + "Obj");
                string.append(fieldName + "." + m.getName() + "(" + f.getName() + "Obj" + ");\n");
                string.append("continue;");

            }
            string.append("}\n");
        });
        string.append("}\n");
        string.append("}catch(" + JsonParseException.class.getName() + " e){\n" +
                "throw new " + ParsingException.class.getName() + "(\"the provided Json to parse has a different structure than destination Class\", e);\n" +
                "}");
        return string;
    }

    public void parseCollection(Type clazz, StringBuilder builder, String listToAdd) {
        if (clazz instanceof ParameterizedType && Collection.class.isAssignableFrom((Class) ((ParameterizedType) clazz).getRawType())) {
            if(List.class.isAssignableFrom((Class) ((ParameterizedType) clazz).getRawType())){
                builder.append(List.class.getName() + " " + listToAdd + " = new " + ArrayList.class.getName() + "();");
            }else if(Set.class.isAssignableFrom((Class) ((ParameterizedType) clazz).getRawType())){
                builder.append(Set.class.getName() + " " + listToAdd + " = new " + HashSet.class.getName() + "();");
            }
            Type c = ((ParameterizedType) clazz).getActualTypeArguments()[0];

            if (c instanceof Class && (Number.class.isAssignableFrom((Class) c) || String.class.isAssignableFrom((Class) c) || Boolean.class.isAssignableFrom((Class) c))) {
                builder.append("jParser.nextToken();");
            }
            builder.append("while (jParser.nextToken() != " + JsonToken.class.getName() + ".END_ARRAY && jParser.getCurrentToken() != null) {");

            if (c instanceof Class && Integer.class.isAssignableFrom((Class) c)) {
                builder.append(listToAdd + ".add(new " + Integer.class.getName() + "(jParser.getIntValue()));\n");
            } else if (c instanceof Class && Long.class.isAssignableFrom((Class) c)) {
                builder.append(listToAdd + ".add(new " + Long.class.getName() + "(jParser.getLongValue()));\n");
            } else if (c instanceof Class && Short.class.isAssignableFrom((Class) c)) {
                builder.append(listToAdd + ".add(new " + Short.class.getName() + "(jParser.getShortValue()));\n");
            } else if (c instanceof Class && Float.class.isAssignableFrom((Class) c)) {
                builder.append(listToAdd + ".add(new " + Float.class.getName() + "(jParser.getIntFloat()));\n");
            } else if (c instanceof Class && Boolean.class.isAssignableFrom((Class) c)) {
                builder.append(listToAdd + ".add(new " + Boolean.class.getName() + "(jParser.getBooleanValue()));\n");
            } else if (c instanceof Class && String.class.isAssignableFrom((Class) c)) {
                builder.append(listToAdd + ".add(jParser.getText());\n");
            } else if (c instanceof Class) {
                parse((Class) c, builder, listToAdd + "Obj");
                builder.append(listToAdd + ".add(" + listToAdd + "Obj" + ");\n");
                builder.append("continue;");
            } else if (c instanceof Collection) {
                parseCollection(c, builder, listToAdd + "List");
                builder.append(listToAdd + ".add(" + listToAdd + "List);\n");
            } else if (Map.class.isAssignableFrom((Class) ((ParameterizedType) c).getRawType())) {
                parseMap(c, builder, listToAdd + "Map");
                builder.append(listToAdd + ".add(" + listToAdd + "Map);\n");
            }
            builder.append("}");

        }

    }

    public void parseMap(Type clazz, StringBuilder builder, String mapToAdd) {
        if (clazz instanceof ParameterizedType && Map.class.isAssignableFrom((Class) ((ParameterizedType) clazz).getRawType())) {
            builder.append(Map.class.getName() + " " + mapToAdd + " = new " + HashMap.class.getName() + "();");
            builder.append("while (jParser.nextToken() != " + JsonToken.class.getName() + ".END_OBJECT && jParser.getCurrentToken() != null) {");
            builder.append("if(jParser.getText() != null && jParser.getText().equals(\"{\")){jParser.nextToken();}\n");
            builder.append("String key = jParser.getText();");
            builder.append("jParser.nextToken();");
            Type c = ((ParameterizedType) clazz).getActualTypeArguments()[1];
            if (c instanceof Class && Integer.class.isAssignableFrom((Class) c)) {
                builder.append(mapToAdd + ".put(key, new " + Integer.class.getName() + "(jParser.getIntValue()));\n");
            } else if (c instanceof Class && Long.class.isAssignableFrom((Class) c)) {
                builder.append(mapToAdd + ".put(key, new " + Long.class.getName() + "(jParser.getLongValue()));\n");
            } else if (c instanceof Class && Short.class.isAssignableFrom((Class) c)) {
                builder.append(mapToAdd + ".put(key, new " + Short.class.getName() + "(jParser.getShortValue()));\n");
            } else if (c instanceof Class && Float.class.isAssignableFrom((Class) c)) {
                builder.append(mapToAdd + ".put(key, new " + Float.class.getName() + "(jParser.getFloatValue()));\n");
            } else if (c instanceof Class && Boolean.class.isAssignableFrom((Class) c)) {
                builder.append(mapToAdd + ".put(key, new " + Boolean.class.getName() + "(jParser.getBooleanValue()));\n");
            } else if (c instanceof Class && String.class.isAssignableFrom((Class) c)) {
                builder.append(mapToAdd + ".put(key, jParser.getText());\n");
            } else if (c instanceof Class) {
                parse((Class) c, builder, mapToAdd + "Obj");
                builder.append(mapToAdd + ".put(key, " + mapToAdd + "Obj" + ");\n");
                builder.append("continue;");
            } else if (c instanceof Map) {
                parseMap(c, builder, mapToAdd + "Map");
                builder.append(mapToAdd + ".put(key, " + mapToAdd + "Map);\n");
            } else if (Collection.class.isAssignableFrom((Class) ((ParameterizedType) c).getRawType())) {
                parseCollection(c, builder, mapToAdd + "List");
                builder.append(mapToAdd + ".put(key, " + mapToAdd + "List);\n");
            }
            builder.append("}");

        }

    }
}
