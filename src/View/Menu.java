package View;

public class Menu {
    public static void mainMenu(){
        ViewData view = new ViewData();
        view.writeMessage("Розыгрыш игрушек");
        view.writeMessage("Выберите действие\n1. Показать игрушки в магазине\n2. Разыграть игрушку\n3. Показать призы\n4. Забрать выигрыш\n5. Настройки розыграша\n6. Выход");
    }
    public static void settingMenu(){
        ViewData view = new ViewData();
        view.writeMessage("Настройки магазина");
        view.writeMessage("Выберети действие\n1. Добавить игрушку\n2. Настроить игрушку\n3. Показать детальные данные игрушки\n4. Удалить игрушку\n5. Возврат в предыдущее меню");
    }

}
