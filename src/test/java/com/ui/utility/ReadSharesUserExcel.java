package com.ui.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.Logger;

import com.ui.pojo.SharesUserData;

/**
 * Utility to read Shares user details from test data Excel sheets.
 */
public class ReadSharesUserExcel {

	private static final Logger logger = LoggerUtility.getLogger(ReadSharesUserExcel.class);

	public static Iterator<SharesUserData> readExcelFile(String fileName) {
		File loginDataFile = new File(
				System.getProperty("user.dir") + File.separator + "UserData" + File.separator + fileName);

		List<SharesUserData> userList = new ArrayList<>();
		DataFormatter formatter = new DataFormatter();

		logger.info("Reading Shares user data from Excel file: " + loginDataFile.getAbsolutePath());

		try (XSSFWorkbook workbook = new XSSFWorkbook(loginDataFile)) {
			XSSFSheet sheet = workbook.getSheet("Shares");
			if (sheet == null) {
				logger.error("Sheet 'Shares' not found in excel file: " + fileName);
				return userList.iterator();
			}

			Iterator<Row> rowIterator = sheet.iterator();
			if (rowIterator.hasNext()) {
				rowIterator.next(); // skipping header row
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row == null) {
					logger.warn("Null row found, skipping.");
					continue;
				}

				String mobileNo = formatter.formatCellValue(row.getCell(0));
				String otp = formatter.formatCellValue(row.getCell(1));
				String panNo = formatter.formatCellValue(row.getCell(2));
				String panName = formatter.formatCellValue(row.getCell(3));
				String emailID = formatter.formatCellValue(row.getCell(4));
				String dob = formatter.formatCellValue(row.getCell(5));
				String loanAmount = formatter.formatCellValue(row.getCell(6));
				String dpId = formatter.formatCellValue(row.getCell(7));
				String clientId = formatter.formatCellValue(row.getCell(8));
				String securityName = formatter.formatCellValue(row.getCell(9));
				String sharesName = formatter.formatCellValue(row.getCell(10));
				String sharesQty = formatter.formatCellValue(row.getCell(11));
				String gender = formatter.formatCellValue(row.getCell(12));
				String fatherName = formatter.formatCellValue(row.getCell(13));
				String motherName = formatter.formatCellValue(row.getCell(14));
				String maritalStatus = formatter.formatCellValue(row.getCell(15));
				String annualIncome = formatter.formatCellValue(row.getCell(16));
				String occupation = formatter.formatCellValue(row.getCell(17));
				String natureOfBusiness = formatter.formatCellValue(row.getCell(18));

				SharesUserData sharesFoundData = new SharesUserData(
						mobileNo, otp, panNo, panName, emailID, dob, loanAmount, dpId,
						clientId, securityName, sharesName, sharesQty,
						gender, fatherName, motherName, maritalStatus,
						annualIncome, occupation, natureOfBusiness
				);

				userList.add(sharesFoundData);
			}
			logger.info("Successfully loaded " + userList.size() + " Shares user records.");
		} catch (IOException | InvalidFormatException e) {
			logger.error("Error reading Shares User Excel file: " + fileName, e);
		}
		return userList.iterator();
	}
}
