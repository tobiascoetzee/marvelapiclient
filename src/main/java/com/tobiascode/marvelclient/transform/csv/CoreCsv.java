package com.tobiascode.marvelclient.transform.csv;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoreCsv<T> {
    private final Logger logger = LoggerFactory.getLogger(CoreCsv.class);
    private final Object[] fileHeader;
    private Function<T, List<List<Object>>> buildCsvRecord;

    public CoreCsv(Object[] fileHeader, Function<T, List<List<Object>>> buildCsvRecord) {
        this.fileHeader = fileHeader;
        this.buildCsvRecord = buildCsvRecord;
    }

    public String getCsv(List<T> records) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator("\n").withQuoteMode(QuoteMode.NON_NUMERIC);
        StringBuilder csvFile = new StringBuilder();

        try {
            CSVPrinter csvPrinter = new CSVPrinter(csvFile, csvFormat);

            csvPrinter.printRecord(fileHeader);

            for (T record : records) {
                for (List<Object> line : buildCsvRecord.apply(record)) {
                    csvPrinter.printRecord(line);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        logger.debug(csvFile.toString());
        return csvFile.toString();
    }
}
