package com.ui.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.SharesUserData;

public class ReadSharesUserExcel {

	public static Iterator<SharesUserData> readExcelFile(String fileName) {

		File loginDatafile = new File(
				System.getProperty("user.dir") + File.separator + "UserData" + File.separator + fileName);

		XSSFWorkbook workbook = null;
		XSSFSheet sheet;
		Iterator<Row> rowIterator;
		Row row = null;

		String MobileNo, OTP, PanNo, PanName, EmailID, DOB, LoanAmount, DPID, ClientID, SecurityName, SharesName,
				SharesQTY, Gender, Fathername, MotherName, MartialStatus, AnnualIncome, Occupation, NatureOfBusiness;
		SharesUserData sharesFoundData;
		List<SharesUserData> userList = new ArrayList<SharesUserData>();
		DataFormatter formmater = new DataFormatter();

		try {
			workbook = new XSSFWorkbook(loginDatafile);
			sheet = workbook.getSheet("Shares");

			rowIterator = sheet.iterator();
			rowIterator.next(); // skipping row 0 because it is header

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				if (row == null) {
					System.out.println("Null Row found");
					continue;
				}
				MobileNo = formmater.formatCellValue(row.getCell(0));
				OTP = formmater.formatCellValue(row.getCell(1));
				PanNo = formmater.formatCellValue(row.getCell(2));
				PanName = formmater.formatCellValue(row.getCell(3));
				EmailID = formmater.formatCellValue(row.getCell(4));
				DOB = formmater.formatCellValue(row.getCell(5));
				LoanAmount = formmater.formatCellValue(row.getCell(6));
				DPID = formmater.formatCellValue(row.getCell(7));
				ClientID = formmater.formatCellValue(row.getCell(8));
				SecurityName = formmater.formatCellValue(row.getCell(9));
				SharesName = formmater.formatCellValue(row.getCell(10));
				SharesQTY = formmater.formatCellValue(row.getCell(11));
				Gender = formmater.formatCellValue(row.getCell(12));
				Fathername = formmater.formatCellValue(row.getCell(13));
				MotherName = formmater.formatCellValue(row.getCell(14));
				MartialStatus = formmater.formatCellValue(row.getCell(15));
				AnnualIncome = formmater.formatCellValue(row.getCell(16));
				Occupation = formmater.formatCellValue(row.getCell(17));
				NatureOfBusiness = formmater.formatCellValue(row.getCell(18));

				sharesFoundData = new SharesUserData(MobileNo.toString(), OTP.toString(), PanNo.toString(),
						PanName.toString(), EmailID.toString(), DOB.toString(), LoanAmount.toString(), DPID.toString(),
						ClientID.toString(), SecurityName.toString(), SharesName.toString(), SharesQTY.toString(),
						Gender.toString(), Fathername.toString(), MotherName.toString(), MartialStatus.toString(),
						AnnualIncome.toString(), Occupation.toString(), NatureOfBusiness.toString());

				userList.add(sharesFoundData);
				workbook.close();
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();

	}

}
