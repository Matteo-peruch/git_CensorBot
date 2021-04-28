/*import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException; queste vengono usate con le versioni aggiornate tipo la <version> 5.1.1 </version>*/
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot; //<- questa usata in tutte le versioni (in teoria)
import org.telegram.telegrambots.api.objects.Update; //<- questa usata nelle versioni vecchie come la <version> 3.5 </version>
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.*;

class EchoBot extends TelegramLongPollingBot {


    public String getBotUsername() {
        return "EchoBot";
    }

    public String getBotToken() {
        return "1614812899:AAE4wyFXyBou62dDtAjNFwtZ_iTuqO9UdkQ";
    }

    public void onUpdateReceived(Update update) {
        /* System.out.println(update.getMessage().getText()); prende il messaggio scritto dall'utente e con il getText()
                                                             lo stampa sul terminale di intellij  */

        // We check if the update has a message and the message has text
      /*  if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);

                try {
                   if(message.equals("Stronzo"))
                        message.setText("********");


                   execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }*/
        String message_text = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message_text);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage(); //messaggio preso dall'Update
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId); //id dell'user

        /*-------*/
        final SendMessage sendMessage1 = sendMessage.setText(s); //testo da utilizzare per confrontare
        sendMessage1.enableHtml(true); //permette di utilizzare caratteri "strani come gli * o _" attivando questo metodo


        String[] word_list = sendMessage1.getText().split("\\s+");

        String res = "";
        try {

            try{
                BufferedReader br = new BufferedReader(
                        new FileReader("C:\\Users\\Matteo\\IdeaProjects\\EchoBot\\src\\main\\java\\Parole_censurare"));
                String parola;
                while((parola = br.readLine()) != null){
                  /*  if(sendMessage1.getText().equals(parola)){
                        sendMessage1.setText("*****");
                    }*/
                    int index = 0;
                    for(String i : word_list){
                        if(i.equals(parola)){
                            word_list[index] = String.valueOf(sendMessage1.setText("*****"));
                        }
                        index++;
                    }
                }

                for (String i : word_list)
                    res += i + ' ';

            }catch (Exception ex) {
                ex.printStackTrace();
            }

           System.out.println(res.toString());
            SendMessage r1 = new SendMessage();
            r1.setChatId(chatId);
            r1.enableHtml(true);
            sendMessage.enableMarkdown(true);
            final SendMessage r2 = r1.setText(res);
            //execute(r1);
            sendMessage(r2);
         } catch (TelegramApiException e) {
            e.printStackTrace();
         }

    }


}

 /*
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage(); //messaggio preso dall'Update
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId); //id dell'user

        final SendMessage sendMessage1 = sendMessage.setText(s);
        sendMessage1.enableHtml(true); //permette di utilizzare caratteri "strani come gli * o _" attivando questo metodo
        SendMessage parolaScurrile = new SendMessage(); //esempio parola scurrile Oggetto

        parolaScurrile.setText("scemo");
        /*Rimpiazzare questo setText con l'apertura di un file di testo in lettura

        try{
            BufferedReader br = new BufferedReader(
                new FileReader("C:\\Users\\Matteo\\IdeaProjects\\EchoBot\\src\\main\\java\\Parole_censurare"));
            String parola;
            while((parola = br.readLine()) != null){
                if(sendMessage1.getText().equals(parola)){

                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }


        try {
            if((sendMessage1.getText()).equals(parolaScurrile.getText())){
                sendMessage1.setText("*****");
            }

            execute(sendMessage1);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
*/



