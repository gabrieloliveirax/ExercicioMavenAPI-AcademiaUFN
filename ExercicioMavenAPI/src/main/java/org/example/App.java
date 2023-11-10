package org.example;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        // Exemplo de conjunto de números
        double[] numbers = {4.5, 3.2, 7.8, 1.2, 6.4, 2.9};

        // Calcular a média e o desvio padrão
        double mean = calculateMean(numbers);
        double standardDeviation = calculateStandardDeviation(numbers);

        // Log dos resultados
        logger.info("Conjunto de números: {}", numbers);
        logger.info("Média: {}", mean);
        logger.info("Desvio Padrão: {}", standardDeviation);

        // Escrever os resultados em um arquivo de texto com data e hora
        writeResultsToFile("resultados.txt", numbers, mean, standardDeviation);
    }

    private static double calculateMean(double[] numbers) {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (double number : numbers) {
            descriptiveStatistics.addValue(number);
        }
        return descriptiveStatistics.getMean();
    }

    private static double calculateStandardDeviation(double[] numbers) {
        DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
        for (double number : numbers) {
            descriptiveStatistics.addValue(number);
        }
        return descriptiveStatistics.getStandardDeviation();
    }

    private static void writeResultsToFile(String fileName, double[] numbers, double mean, double standardDeviation) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Adiciona a data e hora ao conteúdo
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String timestamp = dateFormat.format(new Date());
            writer.write("Data e Hora: " + timestamp + "\n\n");

            // Escreve os resultados
            writer.write("Conjunto de números: ");
            for (double number : numbers) {
                writer.write(number + " ");
            }
            writer.write("\n");
            writer.write("Média: " + mean + "\n");
            writer.write("Desvio Padrão: " + standardDeviation + "\n");

            logger.info("Resultados gravados em '{}'", fileName);
        } catch (IOException e) {
            logger.error("Erro ao escrever no arquivo '{}': {}", fileName, e.getMessage());
        }
    }
}