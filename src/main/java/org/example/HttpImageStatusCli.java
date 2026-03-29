package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {

    private final HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

    public void askStatus() {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Введіть HTTP код статусу (або 'exit'): ");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Програму завершено");
                break;
            }

            try {
                int code = Integer.parseInt(input);

                downloader.downloadStatusImage(code);

            } catch (NumberFormatException e) {
                System.out.println("Не валідний код");

            } catch (Exception e) {
                System.out.println("Немає картинки для HTTP status " + input);
            }
        }
    }
}
