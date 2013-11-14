package game;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import races.*;

import java.io.IOException;

import races.Bonzoid;
import races.Buzzite;
import races.Flapper;
import races.Human;
import races.Race;
import races.Ugaite;

 class RaceTypeAdapterFactory implements TypeAdapterFactory {

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!Race.class.isAssignableFrom(type.getRawType())) {
                return null; // this class only serializes 'Race' and its subtypes
            }
            
            /*
             * Lookup type adapters to do the actual work. We use getNextAdapter
             * to avoid getting 'this' on the types that this factory supports.
             */
            final TypeAdapter<Race> raceAdapter
                    = gson.getDelegateAdapter(this, TypeToken.get(Race.class));
            final TypeAdapter<Human> humanAdapter
                    = gson.getDelegateAdapter(this, TypeToken.get(Human.class));
            final TypeAdapter<Bonzoid> bonzoidAdapter
            = gson.getDelegateAdapter(this, TypeToken.get(Bonzoid.class));
            final TypeAdapter<Buzzite> buzziteAdapter
            = gson.getDelegateAdapter(this, TypeToken.get(Buzzite.class));
            final TypeAdapter<Flapper> flapperAdapter
            = gson.getDelegateAdapter(this, TypeToken.get(Flapper.class));
            final TypeAdapter<Ugaite> ugaiteAdapter
            = gson.getDelegateAdapter(this, TypeToken.get(Ugaite.class));
            /*
             * The JsonElement type adapter is always handy when we want to
             * tweak what our delegate type adapter created.
             */
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

            /**
             * Now that we have some helpers, create the tweaked type adapter.
             */
            TypeAdapter<Race> result = new TypeAdapter<Race>() {
                @Override public void write(JsonWriter out, Race value) throws IOException {
                    if (value instanceof Bonzoid) {
                        bonzoidAdapter.write(out, (Bonzoid) value);
                    }else if(value instanceof Buzzite) {
                    	buzziteAdapter.write(out, (Buzzite) value);
                        
                    }else if(value instanceof Flapper) {
                    	flapperAdapter.write(out, (Flapper) value);
                        
                    }else if(value instanceof Human) {
                    	humanAdapter.write(out, (Human) value);
                        
                    }else if(value instanceof Ugaite) {
                    	ugaiteAdapter.write(out, (Ugaite) value);
                        
                    } else {
                        
                        JsonObject object = raceAdapter.toJsonTree(value).getAsJsonObject();
                        object.add("no_race", new JsonPrimitive(true));
                        elementAdapter.write(out, object);
                    }
                }

                @Override public Race read(JsonReader in) throws IOException {
                    /*
                     * Use the appropriate type adapter based on the contents
                     * of the stream.
                     */
                    JsonObject object = elementAdapter.read(in).getAsJsonObject();
                    if (object.has("human")) {
                        return humanAdapter.fromJsonTree(object);
                    } else if(object.has("bonzoid")){
                    	return bonzoidAdapter.fromJsonTree(object);
                    }else if(object.has("flapper")){
                    	return flapperAdapter.fromJsonTree(object);
                    }else if(object.has("ugaite")){
                    	return ugaiteAdapter.fromJsonTree(object);
                    }else if(object.has("buzzite")){
                    	return buzziteAdapter.fromJsonTree(object);
                    } else {
                        return raceAdapter.fromJsonTree(object);
                    }
                }
            }.nullSafe(); // so we don't have to check for null on the stream
            
            return (TypeAdapter<T>) result;
        }
    }
