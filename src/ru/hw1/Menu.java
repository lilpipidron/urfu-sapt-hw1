package ru.hw1;

import ru.hw1.model.Car;
import ru.hw1.service.CarFileReader;
import ru.hw1.service.CarService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private final CarService carService = new CarService();
    private final CarFileReader fileReader = new CarFileReader();

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("""
                    ===== МЕНЮ =====
                    1. Добавить автомобиль вручную
                    2. Загрузить автомобили из файла
                    3. Показать все автомобили
                    4. Обработать данные
                    5. Получить автомобиль по номеру
                    6. Удалить автомобиль по номеру
                    0. Выход""");

            int command = readInt("Выберите пункт меню: ");

            switch (command) {
                case 1 -> addCar();
                case 2 -> loadFromFile();
                case 3 -> printAll();
                case 4 -> processData();
                case 5 -> getCar();
                case 6 -> removeCar();
                case 0 -> {
                    System.out.println("Завершение работы программы...");
                    return;
                }
                default -> System.out.println("Неизвестная команда.");
            }
        }
    }

    private void addCar() {
        System.out.print("Модель: ");
        String model = scanner.nextLine();
        int power = readInt("Мощность (л.с.): ");
        int year = readInt("Год выпуска: ");

        try {
            Car car = new Car(model, power, year);
            carService.add(car);
            System.out.println("Автомобиль добавлен.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        System.out.print("Введите путь к файлу: ");
        String fileName = scanner.nextLine().trim();

        try {
            List<Car> loaded = fileReader.read(fileName);
            carService.addAll(loaded);
            System.out.println("Загружено автомобилей: " + loaded.size());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private void printAll() {
        if (carService.size() == 0) {
            System.out.println("Список пуст.");
            return;
        }
        List<Car> cars = carService.getCars();
        for (int i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + ". " + cars.get(i));
        }
    }

    private void processData() {
        int minPower = readInt("Минимальная мощность: ");
        List<String> result = carService.process(minPower);

        if (result.isEmpty()) {
            System.out.println("Нет автомобилей с такой мощностью.");
            return;
        }
        System.out.println("Автомобили с мощностью от " + minPower + " л.с. (по году выпуска):");
        result.forEach(System.out::println);
    }

    private void getCar() {
        int number = readNumber();
        if (number == -1) {
            return;
        }
        System.out.println(carService.get(number - 1));
    }

    private void removeCar() {
        int number = readNumber();
        if (number == -1) {
            return;
        }
        Car removed = carService.remove(number - 1);
        System.out.println("Удален: " + removed);
    }

    private int readNumber() {
        if (carService.size() == 0) {
            System.out.println("Список пуст.");
            return -1;
        }
        int number = readInt("Номер автомобиля (1-" + carService.size() + "): ");
        if (number < 1 || number > carService.size()) {
            System.out.println("Ошибка: нет автомобиля с таким номером.");
            return -1;
        }
        return number;
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: необходимо ввести число.");
            }
        }
    }
}
