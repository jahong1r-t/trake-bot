package uz.weather.service;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.weather.bot.MainBot;
import uz.weather.entity.User;
import uz.weather.entity.enums.State;
import uz.weather.util.Util;
import uz.weather.util.Button;

import java.io.IOException;
import java.util.ArrayList;

import static uz.weather.db.Datasource.*;

public class UserService extends MainBot {
    public void service(Update update) throws IOException {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        var from = update.getMessage().getFrom();

        Gson gson = new Gson();


        if (!userMap.containsKey(chatId)) {
            User build = User.builder()
                    .chatId(chatId)
                    .fullName(from.getFirstName())
                    .userName(from.getUserName())
                    .state(State.MAIN_PANEL)
                    .categoryIds(new ArrayList<>())
                    .build();

            userMap.putIfAbsent(chatId, build);

        }


        writUserToJson();

        User user = userMap.get(chatId);
        State currentState = userMap.get(chatId).getState();

        if (currentState == State.MAIN_PANEL) {
            switch (text) {
                case "/start" -> {
                    sendMessage(chatId, "Assalomu alaykum", keyboard(Util.mainPanel));
                }
                case Button.track -> {
                    user.setState(State.TRACK_PANEL);
                }
                case Button.manage -> {
                    user.setState(State.MANAGE_PANEL);

                }
                case Button.record -> {
                    user.setState(State.RECORD_PANEL);
                }
            }
        }
    }
}
