package View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewData {

    private ViewMsgConsole currentView;
    private final SimpleDateFormat date;

    public ViewData() {
        this.currentView =  new ViewMsgConsole();
        String datePattern = "dd.MM.yyyy HH:mm:ss";
        date = new SimpleDateFormat(datePattern);
    }

    /** Вывод сообщения с указанием даты
     *
     * @param message сообщение
     */
    public void writeLog(String message) {
        String currentDate = date.format(new Date());
        currentView.writeMessage(currentDate+ " " + message);
    }
    /** Вывод сообщения
     *
     * @param message сообщение для вывода
     */
    public void writeMessage(String message) {
        currentView.writeMessage(message);
    }

}
