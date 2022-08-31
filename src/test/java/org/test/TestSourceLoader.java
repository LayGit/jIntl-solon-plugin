package org.test;

import com.google.gson.*;
import com.laylib.jintl.config.LocalProviderConfig;
import com.laylib.jintl.formatter.SourceNameFormatter;
import com.laylib.jintl.loader.LocalSourceLoader;
import lombok.SneakyThrows;

import java.lang.reflect.Type;

/**
 * Test Source Loader
 *
 * @author Lay
 */
public class TestSourceLoader extends LocalSourceLoader {
    public TestSourceLoader(TestProviderConfig testProviderConfig) {
        super(copyConfig(testProviderConfig));
    }

    private static LocalProviderConfig copyConfig(TestProviderConfig testProviderConfig) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Class.class, new ClassCodec()).create();
        return gson.fromJson(gson.toJson(testProviderConfig), LocalProviderConfig.class);
    }

    private static class ClassCodec implements JsonSerializer<Class<? extends SourceNameFormatter>>, JsonDeserializer<Class<? extends  SourceNameFormatter>> {

        @SuppressWarnings("unchecked")
        @SneakyThrows
        @Override
        public Class<? extends SourceNameFormatter> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String clazz = jsonElement.getAsString();
            return (Class<? extends SourceNameFormatter>) Class.forName(clazz);
        }

        @Override
        public JsonElement serialize(Class<? extends SourceNameFormatter> aClass, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(aClass.getName());
        }
    }
}
