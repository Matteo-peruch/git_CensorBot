import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CensorBot extends TelegramLongPollingBot {

    File file= new File("/users/stafa/desktop/paroleDaCensurare.txt");
    Scanner scan;
    {
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    String[] paroleBannate= scan.nextLine().split(" ");

    public void onUpdateReceived(Update update) {

        for(int i=0; i<paroleBannate.length;i++){
            if(update.getMessage().getText().contains(paroleBannate[i]) || update.getMessage().getText().equalsIgnoreCase(paroleBannate[i])) {
                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setChatId(update.getMessage().getChatId().toString());
                deleteMessage.setMessageId(update.getMessage().getMessageId());
                try {
                        execute(deleteMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


    }



    public String getBotUsername() {
        return "TestCensorBot";
    }

    public String getBotToken() {
        return "1893728185:AAERhtt4jQOhmaX-cd4-JM9ftcJCwgmHwVY";
    }
}
