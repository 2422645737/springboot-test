package com.shiyueoe.springdemo.export.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.shiyueoe.springdemo.export.entity.DemoData;
import com.shiyueoe.springdemo.export.mapper.DemoMapper;
import com.shiyueoe.springdemo.export.service.ExportService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class ExportServiceImpl implements ExportService {

//    @Resource
//    private DemoMapper demoMapper;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String exportExcel(){
//        int pageSize = 10000; // 每次查询批量大小，分批读写，避免内存爆炸
//        int dataPerFile = 20000; // 每个文件数据量
//        int pagesPerFile = dataPerFile / pageSize;
//
//        int total = demoMapper.countAll();
//        int totalPages = (int) Math.ceil((double) total / pageSize);
//
//        int totalFiles = (int) Math.ceil((double) total / dataPerFile);
//
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//
//        CountDownLatch latch = new CountDownLatch(totalFiles);
//
//        long start = System.currentTimeMillis();
//        for (int fileIndex = 0; fileIndex < totalFiles; fileIndex++) {
//            final int currentFileIndex = fileIndex;
//
//            executor.submit(() -> {
//                String filePath = "./export/export_" + currentFileIndex + ".xlsx";
//
//                ExcelWriter writer = EasyExcel.write(filePath, DemoData.class).build();
//                WriteSheet sheet = EasyExcel.writerSheet("Sheet1").build();
//
//                try {
//                    for (int page = 0; page < pagesPerFile; page++) {
//                        int globalPage = currentFileIndex * pagesPerFile + page;
//                        if (globalPage >= totalPages) break;
//
//                        int offset = globalPage * pageSize;
//                        List<DemoData> dataBatch = demoMapper.get(pageSize, offset, (long) offset);
//                        if (!dataBatch.isEmpty()) {
//                            writer.write(dataBatch, sheet);
//                        }
//                    }
//                } finally {
//                    writer.finish();
//                    latch.countDown();
//                }
//            });
//        }
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        executor.shutdown();
//
//        System.out.println("耗时" + (System.currentTimeMillis() - start) + "ms");
//        System.out.println("✅ 多文件导出完成！");
        return null;
    }

    @Override
    public String exportCSV() {
//        int pageSize = 20000;
//        int dataPerFile = 20000;
//        int pagesPerFile = dataPerFile / pageSize;
//
//        int total = demoMapper.countAll();
//        int totalPages = (int) Math.ceil((double) total / pageSize);
//        int totalFiles = (int) Math.ceil((double) total / dataPerFile);
//
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CountDownLatch latch = new CountDownLatch(totalFiles);
//
//        long start = System.currentTimeMillis();
//
//        for (int fileIndex = 0; fileIndex < totalFiles; fileIndex++) {
//            final int currentFileIndex = fileIndex;
//
//            int finalFileIndex = fileIndex;
//            executor.submit(() -> {
//                String filePath = "./export/export_" + currentFileIndex + ".csv";
//
//                try (
//                        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
//                        CSVPrinter csvPrinter = new CSVPrinter(writer,
//                                CSVFormat.DEFAULT.withHeader("id", "name", "category", "createdAt", "updatedAt"))
//                ) {
//                    for (int page = 0; page < pagesPerFile; page++) {
//                        int globalPage = currentFileIndex * pagesPerFile + page;
//                        if (globalPage >= totalPages) break;
//
//                        int offset = globalPage * pageSize;
//                        List<DemoData> dataBatch = demoMapper.get(pageSize, offset, (long) offset);
//                        System.out.println("file" + finalFileIndex + ": " + dataBatch.size());
//                        //这里得加锁，不然存在线程安全问题
////                        synchronized (latch){
////                            for (DemoData data : dataBatch) {
////                                csvPrinter.printRecord(
////                                        data.getId(),
////                                        data.getName(),
////                                        data.getCategory(),
////                                        formatDate(data.getCreatedAt()),
////                                        formatDate(data.getUpdatedAt())
////                                );
////                            }
////                        }
//                        for (DemoData data : dataBatch) {
//                            csvPrinter.printRecord(
//                                    data.getId(),
//                                    data.getName(),
//                                    data.getCategory(),
//                                    formatDate(data.getCreatedAt()),
//                                    formatDate(data.getUpdatedAt())
//                            );
//                        }
//                    }
//                    csvPrinter.flush();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        executor.shutdown();
//
//        System.out.println("耗时" + (System.currentTimeMillis() - start) + "ms");
//        System.out.println("✅ 多文件CSV导出完成！");
        return null;
    }

    private static String formatDate(Date date) {
        return date == null ? "" : dateFormat.format(date);
    }

}