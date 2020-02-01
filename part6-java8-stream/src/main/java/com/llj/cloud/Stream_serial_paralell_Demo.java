package com.llj.cloud;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * PackageName:   com.llj.cloud
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/14 18:39
 * Updater:     by luolinjie
 * Update_Date: 2019/9/14
 * Update_Description: luolinjie 补充
 **/
public class Stream_serial_paralell_Demo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月");

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\linjie.luo\\Desktop\\device_activation.xls";


        // Use an InputStream, needs more memory
        Workbook wb = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("error path");
            e.printStackTrace();
        }
        try {
//            wb = WorkbookFactory.create(new FileInputStream(filePath));
            wb = new HSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 获取第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // getLastRowNum，获取最后一行的行标
        int lastRowNum = sheet.getLastRowNum();
        System.out.println("max row num is:" + lastRowNum);
        Iterator<Row> rowIterator = sheet.rowIterator();
        ArrayList<RowModel> bigList = new ArrayList<RowModel>();
        int i = 0;
        while (rowIterator.hasNext()) {
            RowModel rowModel = new RowModel();
            Row row = sheet.getRow(i++);
            if (row == null) break;
            String col1 = "";
            Date col2 = null;
            int col3 = 0;
            int col4 = 0;
            if (null != row.getCell(0) && !"".equals(row.getCell(0))) {
                col1 = row.getCell(0).getStringCellValue();
            }
            if (null != row.getCell(1) && !"".equals(row.getCell(1))) {
                col2 = (Date) getTypedDate(row.getCell(1).getStringCellValue());
            }
            if (null != row.getCell(2) && !"".equals(row.getCell(2))) {
                col3 = Integer.valueOf(row.getCell(2).getStringCellValue());
            }
            if (null != row.getCell(4) && !"".equals(row.getCell(4))) {
                col4 = Integer.valueOf(row.getCell(4).getStringCellValue());
            }

            rowModel.setClassifyType(col1);
            rowModel.setCountDate(col2);
            rowModel.setCountValue(col3);
            rowModel.setId(col4);
            bigList.add(rowModel);
        }

//        List<String> collect1 = getTypeList_serial(bigList);
        // 1.输出品类集合 -- 并行计算
        List<String> classTypeList = getTypeList_parallel(bigList);

        // 2.统计时间起始值
        LongSummaryStatistics stats = summarizeStatistics(bigList);
        System.out.println("最小 Date : " + sdf.format(new Date(stats.getMin())));
        System.out.println("最大 Date : " + sdf.format(new Date(stats.getMax())));

        // 3.获取时间列表集合--day
//        List dateList = getSortedDateList(stats.getMin(),stats.getMax(),Calendar.DAY_OF_MONTH);
        List<Date> dateList = getSortedDateList(stats.getMin(), stats.getMax(), Calendar.MONTH);
        //打印预览
//        dateList.forEach(x -> System.out.println(sdf.format(x)));
        //4.遍历时间列表集合
            /*
                内循环：按照品类进行分类统计(品类一般是不变的，因此可以用来作基础参考，需要排序后输出List/按照map输出)
                内循环输出的集合样式如下：
                    | day1
                ————|——————
                AA  |
                AB  |
                AC  |

                外循环，将内循环中的输出组合成一个list
             */
////        //单项统计举例：统计时间为2019 年4月的所有型号为AB的数值
//        Date dateUnit = new Date(2018 - 1900, 4, 1);
//        Map<String, List<RowModel>> collect = getGroupedValue(bigList,dateUnit);
//        System.out.println(collect.toString());
//
//        //统计单项总值
//        String typeUnit = "TV";
//        IntSummaryStatistics collect2 = getSummarizeInt(bigList, typeUnit);
//        System.out.println(collect2.toString());

        //利用2层循环进行统计
        ArrayList<List<RowModel>> listAll = new ArrayList<>();

        for (String type : classTypeList) {
            List<RowModel> innerList = new ArrayList();
            for (Date dateUnit : dateList) {
                LongSummaryStatistics statistics = summarizeStatisticsByClassAndDate(bigList, type, dateUnit);
                innerList.add(new RowModel(dateUnit, type, statistics.getSum()));
            }
            listAll.add(innerList);
        }

        //将列表输出至Excel
        String exportExcelPath = "d:\\output";
        boolean b = exportExcel(listAll, exportExcelPath, dateList);
        if (b) {
            System.out.println("export finished!");
        }

        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean exportExcel(ArrayList<List<RowModel>> listAll, String exportFilePath, List<Date> dateList) throws IOException {
        //创建一个文件夹
        File folder = new File(exportFilePath);
        boolean mkdir = folder.mkdirs();
        if (mkdir) {
            System.out.println("create folder ： " + exportFilePath + " success!");
        }
        //创建excel文件
        File file = new File(exportFilePath + "/" + sdf.format(new Date()) + ".xls");
        boolean newFile = false;
        try {
            newFile = file.createNewFile();
        } catch (IOException e) {
            System.out.println("create newFile:" + file.getAbsolutePath() + " Failed!");
        }
        if (newFile) {
            System.out.println("create newFile:" + file.getAbsolutePath() + " Success!");
        }
        int row = 0;
        //创建excel 工作簿文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(sdf.format(new Date()));
        sheet.setDefaultColumnWidth(15);
        HSSFFont font = workbook.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        short o = 200;
        font.setFontHeight(o);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        //写入表头
        Row rows0 = sheet.createRow(row);
        Cell cell0 = rows0.createCell(0);
        cell0.setCellStyle(cellStyle);
        cell0.setCellValue("classType");
        int startColumn = 1;
        for (Date singleDate : dateList) {
            String formatedDate = sdf1.format(singleDate);
            Cell cell = rows0.createCell(startColumn++);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(formatedDate);
        }
        System.out.println("dateList.size():" + dateList.size());
        //按照品类写出value
        for (List<RowModel> innerList : listAll) {
            Row rows = sheet.createRow(++row);
            Cell cell1 = rows.createCell(0);
            cell1.setCellValue(innerList.get(0).getClassifyType());
            int startCol = 1;
            for (int col = startCol - 1; col < innerList.size(); col++) {
                // 向工作表中添加数据
                Cell cell = rows.createCell(col+1);
                cell.setCellValue(innerList.get(col).getCountValue());
            }
        }
        FileOutputStream xlsStream = new FileOutputStream(file);
        workbook.write(xlsStream);
        xlsStream.close();
        return true;

    }


    private static LongSummaryStatistics summarizeStatisticsByClassAndDate(ArrayList<RowModel> bigList, String type, Date dateUnit) {
        return bigList
                .stream()
                .filter(x -> x.getCountDate().getYear() == (dateUnit).getYear())
                .filter(x -> x.getCountDate().getMonth() == (dateUnit).getMonth())
                .filter(x -> x.getClassifyType().equals(type))
                .collect(Collectors.summarizingLong(RowModel::getCountValue));
    }

    private static Map<String, List<RowModel>> getGroupedValue_byClassifyType(ArrayList<RowModel> bigList, Date dateUnit) {
        return bigList
                .stream()
                .filter(x -> x.getCountDate().getYear() == (dateUnit).getYear())
                .filter(x -> x.getCountDate().getMonth() == (dateUnit).getMonth())
                .collect(Collectors.groupingBy(RowModel::getClassifyType));
    }

    private static Map<Integer, List<RowModel>> getGroupedValue_byMonth(ArrayList<RowModel> bigList, String classifyType) {
        Map<Integer, List<RowModel>> collect = bigList
                .stream()
                .filter(x -> x.getClassifyType().equals(classifyType))
                .collect(Collectors.groupingBy(RowModel::getMonth));
        return collect;
    }

    private static LongSummaryStatistics getSummarizeInt(ArrayList<RowModel> bigList, String typeUnit) {
        return bigList
                .stream()
                .filter(x -> x.getClassifyType().equals(typeUnit))
                .collect(Collectors.summarizingLong(RowModel::getCountValue));
    }

    private static List getSortedDateList(long min, long max, int dimention) {
        ArrayList<Date> dateArrayList = new ArrayList<>();
        Calendar nowDate = Calendar.getInstance();
        nowDate.setTimeInMillis(min);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(max);
        while (!nowDate.after(endDate)) {
            dateArrayList.add(nowDate.getTime());
            switch (dimention) {
                case Calendar.DAY_OF_MONTH:
                    nowDate.add(Calendar.DAY_OF_MONTH, 1);
                    break;
                case Calendar.MONTH:
                    nowDate.add(Calendar.MONTH, 1);
                    break;
                default:
                    return null;
            }

        }

        return dateArrayList;
    }

    private static LongSummaryStatistics summarizeStatistics(ArrayList<RowModel> bigList) {
        LongSummaryStatistics stats = bigList.stream().mapToLong((x) -> x.getCountDate().getTime()).summaryStatistics();

        return stats;
    }

    private static List<String> getTypeList_parallel(ArrayList<RowModel> bigList) {

        long start2 = System.currentTimeMillis();
        System.out.println("Start parallel：" + start2);
        List<String> collect2 = bigList.parallelStream().map(rowModel -> rowModel.getClassifyType()).distinct().collect(Collectors.toList());
        long end2 = System.currentTimeMillis();
        System.out.println("End parallel：" + end2);
        System.out.println("delta：" + (end2 - start2));

        return collect2;
    }

    private static List<String> getTypeList_serial(ArrayList<RowModel> bigList) {
        // 输出品类集合 -- 串行计算
        long start1 = System.currentTimeMillis();
        System.out.println("Start serial：" + start1);
        List<String> collect = bigList.stream().map(rowModel -> rowModel.getClassifyType()).distinct().collect(Collectors.toList());
        long end1 = System.currentTimeMillis();
        System.out.println("End serial：" + end1);
        System.out.println("delta：" + (end1 - start1));
        return collect;
    }

    /**
     * Date格式转换
     *
     * @return
     */
    public static Date getTypedDate(String dateStr) {
        if (null == dateStr || "".equals(dateStr)) return null;

        String[] s = dateStr.split(" ");
        String date = null;
        if (s.length == 2) {
            date = s[0];
        }
        if (!(null == date) && !("".equals(date))) {
            String[] split = date.split("/");
            int month = Integer.valueOf(split[0]) - 1;
            int day = Integer.valueOf(split[1]);
            int year = Integer.valueOf(split[2]);
            Calendar instance = Calendar.getInstance();
            instance.set(year, month, day);
            return instance.getTime();
        }

        return null;
    }
}
