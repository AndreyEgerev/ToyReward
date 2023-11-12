import Model.ToyMarket;
import View.InputData;
import View.Menu;
import View.ViewData;
import View.WorkWithFile;

import java.io.IOException;

public class Presenter {
    private ViewData console;
    private WorkWithFile fileRewards;
    private ToyMarket toyMarket;
    private static String path;
    private static final String nameRewardFile = "Reward.txt";
    public void onStart() {
        this.console = new ViewData();
        this.path = System.getProperty("user.dir") + "\\src\\";
        this.fileRewards = new WorkWithFile(path);
        this.toyMarket = new ToyMarket();
        toyMarket.addToy(1,"Конструктор",10,1);
        toyMarket.addToy(2,"Робот",10,1);
        toyMarket.addToy(3,"Кукла",60,1);
        onRun();

    }
    private void onRun(){
        boolean runFlag = true;
        String choiceUser;
        while (runFlag) {
            Menu.mainMenu();
            choiceUser = InputData.getData();
            switch (choiceUser) {
                case "1"://1. Показать игрушки в магазине
                    console.writeMessage(toyMarket.showToys());
                    break;
                case "2"://2. Разыграть игрушку
                    console.writeMessage(toyMarket.createRewardToy());
                    break;
                case "3"://3. Показать призы
                    console.writeMessage(toyMarket.showRewards());
                    break;
                case "4"://4. Забрать выигрыш
                    String reward = toyMarket.getToy();
                    console.writeMessage(reward);
                    try {
                        fileRewards.writeMessage(path,nameRewardFile,reward);
                    } catch (IOException e) {
                        console.writeMessage(e.getMessage());
                    }
                    break;
                case "5"://5. Настройка магазина
                    settingRun();
                    break;
                case "6"://6. Выход
                    runFlag = onStop("Завершение работы");
                    break;
                default:
                    console.writeMessage("Неверный ввод");
            }
        }
    }
    private boolean onStop(String message){
        console.writeMessage(message);
        return false;
    }
    private void settingRun(){
        boolean runSettingFlag = true;
        String choiceUser;
        while (runSettingFlag){
            Menu.settingMenu();
            choiceUser = InputData.getData();
            switch (choiceUser){
                case "1":
                    addNewToy();
                    break;
                case "2":
                    settingToy();
                    break;
                case "3":
                    viewToyDetail();
                    break;
                case "4":
                    removeToy();
                    break;
                case "5":
                    runSettingFlag = false;
                    break;
                default:
                    console.writeMessage("Неверный ввод");
            }
        }
    }
    private void addNewToy() {
        boolean inputRun = true;
        int id = 0, weightDrop = 0, qt = 0;
        String name;
        while (inputRun) {
            console.writeMessage("Введите данные новой игрушки \nВведите id игрушки: ");
            String data = InputData.getData();
            try {
                id = Integer.parseInt(data);
                inputRun = false;
            } catch (NumberFormatException e) {
                console.writeMessage("Неправильный формат id игрушки");
            }
        }
        console.writeMessage("Введите название игрушки");
        name = InputData.getData();
        inputRun = true;
        while (inputRun){
            console.writeMessage("Введите частоту выпадения игрушки");
            String data = InputData.getData();
            try {
                weightDrop = Integer.parseInt(data);
                inputRun = false;
            } catch (NumberFormatException e) {
                console.writeMessage("Неправильный формат частоты выпадения игрушки");
            }
        }
        inputRun = true;
        while (inputRun){
            console.writeMessage("Введите количество игрушек");
            String data = InputData.getData();
            try {
                qt = Integer.parseInt(data);
                inputRun = false;
            } catch (NumberFormatException e) {
                console.writeMessage("Введено неправильное количество");
            }
        }
        console.writeMessage(toyMarket.addToy(id,name,weightDrop,qt));
    }
    private void settingToy(){
        boolean choise = true;
        int numberSettingToy = 0;
        while (choise){
            console.writeMessage("Выберите № игрушки для настройки");
            console.writeMessage(toyMarket.showToys());
            console.writeMessage((toyMarket.getToyList().size()+1)+". Возврат в предыдущее меню");
            String userChoise = InputData.getData();
            try {
                numberSettingToy = Integer.parseInt(userChoise);
                choise = false;
            } catch (NumberFormatException e) {
                console.writeMessage("Введено неправильное количество");
            }
            if (numberSettingToy > toyMarket.getToyList().size()+1 || numberSettingToy < 1) {
                choise = true;
                console.writeMessage("Введен неправильный номер игрушки");
            } else choise = false;
        }
        if (numberSettingToy == toyMarket.getToyList().size()+1) return;
        numberSettingToy--;
        console.writeMessage("Выберете что хотите настроить\n1. Вес выпадения\n2. Количество\n3. Возврат в предыдущее меню");
        String userChoise = InputData.getData();
        int value;
        switch (userChoise){
            case "1":
                console.writeMessage("Текущее значение " + toyMarket.getToyList().get(numberSettingToy).getWeightDrop());
                console.writeMessage("Введите новое значение веса выпадения ");
                value = Integer.parseInt(InputData.getData());
                console.writeMessage(toyMarket.setupToyWeightDrop(numberSettingToy,value));
                break;
            case "2":
                console.writeMessage("Текущее значение " + toyMarket.getToyList().get(numberSettingToy).getQuantity());
                console.writeMessage("Введите новое значение количества ");
                value = Integer.parseInt(InputData.getData());
                console.writeMessage(toyMarket.setupToyQuantity(numberSettingToy,value));
                break;
            case "3":
                return;
            default:
                console.writeMessage("Некоректный ввод");
        }
    }
    private void removeToy(){
        boolean choise = true;
        int numberRemoveToy = 0;
        while (choise){
            console.writeMessage("Выберете № игрушки, которую хотите удалить:");
            console.writeMessage(toyMarket.showToys());
            console.writeMessage((toyMarket.getToyList().size()+1)+". Возврат в предыдущее меню");
            String userChoise = InputData.getData();
            try {
                numberRemoveToy = Integer.parseInt(userChoise);
                choise = false;
            } catch (NumberFormatException e) {
                console.writeMessage("Введен не номер игрушки");
            }
            if (numberRemoveToy > toyMarket.getToyList().size()+1 || numberRemoveToy < 1) {
                choise = true;
                console.writeMessage("Введен неправильный номер игрушки");
            } else choise = false;
        }
        if (numberRemoveToy == toyMarket.getToyList().size()+1) return;
        console.writeMessage(toyMarket.removeToy(numberRemoveToy));
    }
    private void viewToyDetail(){
        boolean choise = true;
        int numberToyDetail = 0;
        while (choise){
            console.writeMessage("Выберете № игрушки, для показа деталей:");
            console.writeMessage(toyMarket.showToys());
            console.writeMessage((toyMarket.getToyList().size()+1)+". Возврат в предыдущее меню");
            String userChoise = InputData.getData();
            try {
                numberToyDetail = Integer.parseInt(userChoise);
            } catch (NumberFormatException e) {
                console.writeMessage("Введен не номер игрушки");
            }
            if (numberToyDetail > toyMarket.getToyList().size()+1 || numberToyDetail < 1) {
                console.writeMessage("Введен неправильный номер игрушки");
            }
            if (numberToyDetail == toyMarket.getToyList().size()+1) return;
            console.writeMessage(toyMarket.getToyList().get(numberToyDetail-1).getDetailedData());
        }
    }
}
