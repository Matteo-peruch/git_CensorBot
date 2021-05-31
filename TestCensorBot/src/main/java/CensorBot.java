import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;
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
    //metto le parole da bannare in un array
    String[] paroleBannate= scan.nextLine().split(",");

    public void onUpdateReceived(Update update) {
        //controlla se nel messaggio scritto è contenuta una delle parole da eliminare
        for(int i=0; i<paroleBannate.length;i++){
            if(update.getMessage().getText().contains(paroleBannate[i]) || update.getMessage().getText().equalsIgnoreCase(paroleBannate[i])) {

                DeleteMessage deleteMessage = new DeleteMessage();
                deleteMessage.setChatId(update.getMessage().getChatId().toString());
                deleteMessage.setMessageId(update.getMessage().getMessageId());

                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("il messaggio di @" + update.getMessage().getFrom().getUserName() + " è stato eliminato da CensorBot");
                sendMessage.setChatId(update.getMessage().getChatId());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

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
