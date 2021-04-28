import org.telegram.telegrambots.ApiContextInitializer; //questa devo capire cosa cambia dalle versioni > 3.5 ?!?!?!
/*import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException; queste vengono usate per <version> 5.1.1 </version> */
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Main {
    public static void main(String[] args){

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new EchoBot());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


}
