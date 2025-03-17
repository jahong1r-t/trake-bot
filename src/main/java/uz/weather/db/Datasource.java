package uz.weather.db;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.weather.entity.Category;
import uz.weather.entity.Spend;
import uz.weather.entity.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datasource {
    public static Map<Long, User> userMap = new HashMap<>();
    public static Map<String, Category> categoryMap = new HashMap<>();
    public static Map<String, Spend> spendMap = new HashMap<>();
    static Gson gson = new Gson();

    static {
        try {
            User[] users = gson.fromJson(new BufferedReader(new FileReader("src/main/resources/users.json")), User[].class);
            for (User user : users) {
                userMap.put(user.getChatId(), user);
            }

            Category[] categories = gson.fromJson(new BufferedReader(new FileReader("src/main/resources/spend.json")), Category[].class);
            for (Category category : categories) {
                categoryMap.put(category.getId(), category);
            }

            Spend[] spends = gson.fromJson(new BufferedReader(new FileReader("src/main/resources/category.json")), Spend[].class);
            for (Spend spend : spends) {
                spendMap.put(spend.getId(), spend);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
