package uz.weather.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.weather.entity.Category;
import uz.weather.entity.Spend;
import uz.weather.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datasource {
    public static Map<Long, User> userMap = new HashMap<>();
    public static Map<String, Category> categoryMap = new HashMap<>();
    public static Map<String, Spend> spendMap = new HashMap<>();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static {
        try {
            User[] users = gson.fromJson(new BufferedReader(new FileReader("src/main/resources/users.json")), User[].class);
            for (User user : users) {
                userMap.put(user.getChatId(), user);
            }

            Category[] categories = gson.fromJson(new BufferedReader(new FileReader("src/main/resources/category.json")), Category[].class);
            for (Category category : categories) {
                categoryMap.put(category.getId(), category);
            }

            Spend[] spends = gson.fromJson(new BufferedReader(new FileReader("src/main/resources/spend.json")), Spend[].class);
            for (Spend spend : spends) {
                spendMap.put(spend.getId(), spend);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static void writUserToJson() throws IOException {
        ArrayList<User> users = new ArrayList<>(userMap.values());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/users.json"))) {
            gson.toJson(users, writer);
        }
    }

    @SneakyThrows
    public static void writSpendToJson() throws IOException {
        ArrayList<Spend> users = new ArrayList<>(spendMap.values());

        writCategoryToJson();
        writUserToJson();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/spend.json"))) {
            gson.toJson(users, writer);
        }
    }

    @SneakyThrows
    public static void writCategoryToJson() throws IOException {
        ArrayList<Category> users = new ArrayList<>(categoryMap.values());

        writUserToJson();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/category.json"))) {
            gson.toJson(users, writer);
        }
    }


    public static ReplyKeyboardMarkup keyboard(String[][] buttons) {
        List<KeyboardRow> rows = new ArrayList<>();

        for (String[] button : buttons) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (String s : button) {
                keyboardRow.add(s);
            }
            rows.add(keyboardRow);
        }
        ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
        reply.setResizeKeyboard(true);
        reply.setKeyboard(rows);

        return reply;
    }
}
