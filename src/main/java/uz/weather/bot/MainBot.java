package uz.weather.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.weather.util.Bot;

public class MainBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        
    }

    @Override
    public String getBotUsername() {
        return Bot.USERNAME;
    }

    @Override
    public String getBotToken() {
        return Bot.TOKEN;
    }
}
