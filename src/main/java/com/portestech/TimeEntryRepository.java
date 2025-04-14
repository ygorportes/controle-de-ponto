package com.portestech;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeEntryRepository {

    private static final String FILE_NAME = "time_entries.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static List<TimeEntry> loadEntries() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<List<TimeEntry>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveEntries(List<TimeEntry> entries) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(entries, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os registros: " + e.getMessage());
        }
    }
}
