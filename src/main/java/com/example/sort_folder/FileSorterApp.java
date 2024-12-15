package com.example.sort_folder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileSorterApp {

    @FXML
    private TextField folderPathField; // Поле для ввода пути

    @FXML
    private Button sortButton; // Кнопка запуска

    @FXML
    private Text resultText; // Текст для вывода результата

    @FXML
    private void onSortButtonClick() {
        String folderPath = folderPathField.getText();
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            resultText.setText("Указанный путь не является папкой или не существует.");
            return;
        }

        File[] files = folder.listFiles();
        if (files == null) {
            resultText.setText("Ошибка чтения файлов в папке.");
            return;
        }

        // Сортировка файлов по расширению
        for (File file : files) {
            if (file.isFile()) {
                String fileExtension = getFileExtension(file.getName());

                if (!fileExtension.isEmpty()) {
                    File typeFolder = new File(folder.getAbsolutePath() + File.separator + fileExtension);
                    if (!typeFolder.exists()) typeFolder.mkdir();

                    try {
                        Path source = Paths.get(file.getAbsolutePath());
                        Path destination = Paths.get(typeFolder.getAbsolutePath(), file.getName());
                        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        resultText.setText("Ошика при перемещении файла: " + e.getMessage());
                        return;
                    }
                }
            }
        }
        resultText.setText("Файлы успешно отсортированны!");
        }

        private String getFileExtension (String filename){
            int lastDotIndex = filename.lastIndexOf('.');
            if (lastDotIndex > 0 && lastDotIndex < filename.length() - 1) {
                return filename.substring(lastDotIndex + 1).toLowerCase();
            }
            return "";
        }
    }
