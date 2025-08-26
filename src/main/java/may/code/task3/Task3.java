package may.code.task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Task3 {
    public static void main(String[] args) {
        String pathOfResultsFile = args[0];
        String pathOfTestsFile = args[1];
        String pathOfReportFile = args[2];

        createReport(pathOfTestsFile, pathOfResultsFile, pathOfReportFile);
    }

    private static void createReport(String pathOfTestsFile, String pathOfResultsFile, String pathOfReportFile) {
        Map<Integer, String> results = getTestsResult(pathOfResultsFile);
        JsonObject testData = readJsonFile(pathOfTestsFile);

        if (testData.has("tests")) {
            JsonArray testsArray = testData.getAsJsonArray("tests");
            for (JsonElement testElement : testsArray) {
                setTestValues(testElement.getAsJsonObject(), results);
            }
        }

        writeJsonFile(pathOfReportFile, testData);
    }

    private static void setTestValues(JsonObject testData, Map<Integer, String> results) {
        if (testData.has("id")) {
            int id = testData.get("id").getAsInt();
            if (results.containsKey(id)) {
                testData.addProperty("value", results.get(id));
            }
        }

        if (testData.has("values")) {
            JsonArray valuesArray = testData.getAsJsonArray("values");
            for (JsonElement valueElement : valuesArray) {
                setTestValues(valueElement.getAsJsonObject(), results);
            }
        }
    }

    private static Map<Integer, String> getTestsResult(String pathOfResultsFile) {
        Map<Integer, String> values = new HashMap<>();
        JsonObject data = readJsonFile(pathOfResultsFile);
        JsonArray listOfValues = data.getAsJsonArray("values");

        for (JsonElement elem : listOfValues) {
            JsonObject obj = elem.getAsJsonObject();
            int id = obj.get("id").getAsInt();
            String value = obj.get("value").getAsString();
            values.put(id, value);
        }

        return values;
    }

    private static JsonObject readJsonFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file: " + filename, e);
        }
    }

    private static void writeJsonFile(String filename, JsonObject jsonObject) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file: " + filename, e);
        }
    }
}