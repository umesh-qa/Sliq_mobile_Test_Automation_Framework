package com.ui.utility;

import java.io.File;
import java.io.FileReader;
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

import com.ui.pojo.MutualFoundUserData;

public class ReadMutualFoundUserExcel {

	public static Iterator<MutualFoundUserData> readExcelFile(String fileName) {

		File loginDatafile = new File(
				System.getProperty("user.dir") + File.separator + "UserData" + File.separator + fileName);

		XSSFWorkbook workbook = null;
		XSSFSheet sheet;
		Iterator<Row> rowIterator;
		Row row;

		String MobileNo, OTP, PanNo, PanName, EmailID, DOB, LoanAmount, Gender, Fathername, MotherName, MartialStatus,
				AnnualIncome, Occupation, NatureOfBusiness;
		MutualFoundUserData mutualFoundData;
		List<MutualFoundUserData> userList = new ArrayList<MutualFoundUserData>();
		DataFormatter formatter = new DataFormatter();

		try {
			workbook = new XSSFWorkbook(loginDatafile);
			sheet = workbook.getSheet("MutualFound");

			rowIterator = sheet.iterator();
			rowIterator.next(); // skipping row 0 because it is header

			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				if (row == null) {
					System.out.println("Null Row found");
					continue;
				}
				MobileNo =formatter.formatCellValue(row.getCell(0));
				OTP = formatter.formatCellValue(row.getCell(1));
				PanNo = formatter.formatCellValue(row.getCell(2));
				PanName = formatter.formatCellValue(row.getCell(3));
				EmailID = formatter.formatCellValue(row.getCell(4));
				DOB = formatter.formatCellValue(row.getCell(5));
				LoanAmount = formatter.formatCellValue(row.getCell(6));
				Gender = formatter.formatCellValue(row.getCell(7));
				Fathername = formatter.formatCellValue(row.getCell(8));
				MotherName = formatter.formatCellValue(row.getCell(9));
				MartialStatus = formatter.formatCellValue(row.getCell(10));
				AnnualIncome = formatter.formatCellValue(row.getCell(11));
				Occupation = formatter.formatCellValue(row.getCell(12));
				NatureOfBusiness = formatter.formatCellValue(row.getCell(13));

				mutualFoundData = new MutualFoundUserData(MobileNo.toString(), OTP.toString(), PanNo.toString(),
						PanName.toString(), EmailID.toString(), DOB.toString(), LoanAmount.toString(),
						Gender.toString(), Fathername.toString(), MotherName.toString(), MartialStatus.toString(),
						AnnualIncome.toString(), Occupation.toString(), NatureOfBusiness.toString());

				userList.add(mutualFoundData);
				workbook.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();

	}

}
