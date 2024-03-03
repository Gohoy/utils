package com.gohoy.controller;

import com.gohoy.entities.TransactionData;
import com.gohoy.service.TransactionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/transaction")
public class TransactionDataController {

    @Autowired
    private TransactionDataService transactionDataService;


    @GetMapping("/import")
    public String importTransactionData() {
        try {
            // Read Excel data and create TransactionData objects
            List<TransactionData> transactionDataList = readExcelData();

            // Insert data into database using service
            transactionDataService.saveBatch(transactionDataList);

            return "Data imported successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error importing data from Excel";
        }
    }

    private List<TransactionData> readExcelData() throws IOException {
        List<TransactionData> transactionDataList = new ArrayList<>();

        Resource resource = new ClassPathResource("D:\\documents\\ideaprojects\\excel-importer\\src\\main\\resources\\data.xlt");
        InputStream inputStream = resource.getInputStream();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }
                TransactionData transactionData = createTransactionDataFromRow(row);
                transactionDataList.add(transactionData);
            }
        }

        return transactionDataList;
    }

    private TransactionData createTransactionDataFromRow(Row row) {
        TransactionData transactionData = new TransactionData();

        Cell cell;

        // 交易时间
        cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setTransactionTime(cell.getDateCellValue());

        // 公众账号ID
        cell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setPublicAccountId(cell.getStringCellValue());

        // 商户号
        cell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setMerchantNumber(cell.getStringCellValue());

        // 特约商户号
        cell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setSubMerchantNumber(cell.getStringCellValue());

        // 设备号
        cell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setDeviceNumber(cell.getStringCellValue());

        // 微信订单号
        cell = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setWechatOrderNumber(cell.getStringCellValue());

        // 商户订单号
        cell = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setMerchantOrderNumber(cell.getStringCellValue());

        // 用户标识
        cell = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setUserIdentifier(cell.getStringCellValue());

        // 交易类型
        cell = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setTransactionType(cell.getStringCellValue());

        // 交易状态
        cell = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setTransactionStatus(cell.getStringCellValue());

        // 付款银行
        cell = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setPaymentBank(cell.getStringCellValue());

        // 货币种类
        cell = row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setCurrency(cell.getStringCellValue());

        // 应结订单金额
        cell = row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setSettlementOrderAmount(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 代金券金额
        cell = row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setCouponAmount(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 微信退款单号
        cell = row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setWechatRefundNumber(cell.getStringCellValue());

        // 商户退款单号
        cell = row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setMerchantRefundNumber(cell.getStringCellValue());

        // 退款金额
        cell = row.getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRefundAmount(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 充值券退款金额
        cell = row.getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRechargeRefundAmount(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 退款类型
        cell = row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRefundType(cell.getStringCellValue());

        // 退款状态
        cell = row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRefundStatus(cell.getStringCellValue());

        // 商品名称
        cell = row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setProductName(cell.getStringCellValue());

        // 商户数据包
        cell = row.getCell(21, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setMerchantDataPackage(cell.getStringCellValue());

        // 手续费
        cell = row.getCell(22, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setServiceFee(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 费率
        cell = row.getCell(23, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRate(cell.getStringCellValue());

        // 订单金额
        cell = row.getCell(24, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setOrderAmount(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 申请退款金额
        cell = row.getCell(25, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRefundRequestAmount(BigDecimal.valueOf(cell.getNumericCellValue()));

        // 费率备注
        cell = row.getCell(26, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        transactionData.setRateRemark(cell.getStringCellValue());

        return transactionData;
    }

}
