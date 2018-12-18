package oa.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import oa.bean.Inventorytransaction;
import oa.bean.Location;
import oa.bean.Materiel;

public class ExcelUtil {

	public static List<Materiel> importExcel(Workbook wb) {

		Materiel materiel;

		List<Materiel> BOM = new ArrayList<>();

		if (wb instanceof HSSFWorkbook) {
			return null;
		} else if (wb instanceof XSSFWorkbook) {

			XSSFWorkbook xs = (XSSFWorkbook) wb;
			for (int s = 0; s < xs.getNumberOfSheets(); s++) {
				XSSFSheet sheet = xs.getSheetAt(s);
				int lastRowNum = sheet.getLastRowNum();
				for (int i = 1; i < lastRowNum; i++) {
					XSSFRow row = sheet.getRow(i);
					materiel = new Materiel();
					for (int j = 0; j < row.getLastCellNum(); j++) {
						XSSFCell cell = row.getCell(j);
						switch (cell.getColumnIndex()) {
						case 0:
							materiel.setMateriel_id(cell.getRawValue().toString());
							break;
						case 1:
							materiel.setMateriel_detail(cell.toString());
							break;
						case 2:
							materiel.setMateriel_package(Float.valueOf(cell.getRawValue()));
							break;
						case 3:
							materiel.setMateriel_supplier(cell.toString());
							break;
						default:
							break;
						}
					}
					materiel.setMateriel_type(sheet.getSheetName());
					BOM.add(materiel);
					System.out.println("[" + i + "]" + materiel.toString());
				}
			}
		}
		return BOM;
	}

	public static List<Location> importLocationExcel(Workbook wb) {

		Location location;

		List<Location> locations = new ArrayList<>();

		if (wb instanceof HSSFWorkbook) {
			return null;
		} else if (wb instanceof XSSFWorkbook) {
			XSSFWorkbook xs = (XSSFWorkbook) wb;
			for (int s = 0; s < xs.getNumberOfSheets(); s++) {
				XSSFSheet sheet = xs.getSheetAt(s);
				int lastRowNum = sheet.getLastRowNum();
				for (int i = 1; i < lastRowNum + 1; i++) {
					XSSFRow row = sheet.getRow(i);
					location = new Location();
					for (int j = 0; j < row.getLastCellNum(); j++) {
						XSSFCell cell = row.getCell(j);
						switch (cell.getColumnIndex()) {
						case 0:
							location.setLocationId(Integer.parseInt(cell.toString()));
							break;
						case 1:
							location.setMaterielNumber(cell.toString());
							break;
						case 2:
							location.setMaterielDetail(cell.toString());
							break;
						case 3:
							location.setMaterielGroupCode(cell.toString());
							break;
						case 4:
							location.setLocationDetail(cell.toString());
							break;
						case 5:
							location.setMaterielUnit(cell.toString());
							break;
						case 6:
							location.setMaterielNRO(Double.valueOf(cell.toString()));
							break;
						case 7:
							location.setMaterielUO(Double.valueOf(cell.toString()));
							break;
						case 8:
							location.setMaterielPRC(Double.valueOf(cell.toString()));
							break;
						case 9:
							location.setMaterielFO(Double.valueOf(cell.toString()));
							break;
						case 10:
							location.setMaterielFC(Double.valueOf(cell.toString()));
							break;
						case 11:
							location.setMaterielFCO(Double.valueOf(cell.toString()));
							break;
						case 12:
							location.setMaterielCS(Double.valueOf(cell.toString()));
							break;
						case 13:
							location.setMaterielCN(Double.valueOf(cell.toString()));
							break;
						case 14:
							location.setMaterielOIC(Double.valueOf(cell.toString()));
							break;
						case 15:
							location.setRemarks(cell.toString());
							break;
						default:
							break;
						}

					}
					locations.add(location);
				}
			}
		}
		return locations;
	}

	public static XSSFWorkbook createBomWorkBook(String sheetName, String[] title, List<Materiel> list) {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
		XSSFRow header = sheet.createRow(0);
		XSSFCell cell = null;
		XSSFCellStyle style = xssfWorkbook.createCellStyle();
		style.setFillForegroundColor((short) 13);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		for (int i = 0; i < title.length; i++) {
			cell = header.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);

		}
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < title.length; j++) {
				switch (j) {
				case 0:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMateriel_id());
					break;
				case 1:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMateriel_detail());
					break;
				case 2:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMateriel_package());
					break;
				case 3:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMateriel_supplier());
					break;
				default:
					break;
				}
			}
		}

		return xssfWorkbook;
	}

	public static XSSFWorkbook createLocationWorkBook(String sheetName, String[] title, List<Location> list) {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet sheet = xssfWorkbook.createSheet(sheetName);
		XSSFRow header = sheet.createRow(0);
		XSSFCell cell = null;
		XSSFCellStyle style = xssfWorkbook.createCellStyle();
		style.setFillForegroundColor((short) 13);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < title.length; i++) {
			cell = header.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < title.length; j++) {
				switch (j) {
				case 0:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getLocationId());
					break;
				case 1:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielNumber());
					break;
				case 2:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielDetail());
					break;
				case 3:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielGroupCode());
					break;
				case 4:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getLocationDetail());
					break;
				case 5:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielUnit());
					break;
				case 6:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielNRO());
					break;
				case 7:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielUO());
					break;
				case 8:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielPRC());
					break;
				case 9:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielFO());
					break;
				case 10:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielFC());
					break;
				case 11:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielFCO());
					break;
				case 12:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielCS());
					break;
				case 13:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielCN());
					break;
				case 14:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getMaterielOIC());
					break;
				case 15:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getRemarks());
					break;
				default:
					break;
				}
			}
		}

		return xssfWorkbook;
	}

	public static XSSFWorkbook createInventoryWorkBook(String[] sheetName, String[] head_1, String[] header_2,
			List<Inventorytransaction> list, String[][] values) {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		XSSFSheet sheet = xssfWorkbook.createSheet(sheetName[0]);
		XSSFRow header = sheet.createRow(0);
		XSSFCell cell = null;
		XSSFCellStyle style = xssfWorkbook.createCellStyle();

		style.setFillForegroundColor((short) 13);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		for (int i = 0; i < head_1.length; i++) {
			cell = header.createCell(i);
			cell.setCellValue(head_1[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < list.size(); i++) {
			XSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < head_1.length; j++) {
				switch (j) {
				case 0:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getOrderCode());
					break;
				case 1:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getActionType());
					break;
				case 2:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getItem());
					break;
				case 3:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getNumber());
					break;
				case 4:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getDate());
					break;
				case 5:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getCurrentLocation());
					break;
				case 6:
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).getDestinationLocation());
					break;
				default:
					break;
				}
			}
		}

		XSSFSheet sheet_2 = xssfWorkbook.createSheet(sheetName[1]);
		XSSFRow head_2 = sheet_2.createRow(0);
		XSSFCell cell_2 = null;
		XSSFCellStyle style_2 = xssfWorkbook.createCellStyle();

		style_2.setFillForegroundColor((short) 13);// 设置背景色
		style_2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		for (int i = 0; i < header_2.length; i++) {
			cell_2 = head_2.createCell(i);
			cell_2.setCellValue(header_2[i]);
			cell_2.setCellStyle(style);
		}

		for (int i = 0; i < values.length; i++) {
			XSSFRow row = sheet_2.createRow(i + 1);
			for (int j = 0; j < header_2.length; j++) {
				switch (j) {
				case 0:
					cell_2 = row.createCell(j);
					cell_2.setCellValue(values[i][j]);
					break;
				case 1:
					cell_2 = row.createCell(j);
					cell_2.setCellValue(values[i][j]);
					break;
				case 2:
					cell_2 = row.createCell(j);
					cell_2.setCellValue(values[i][j]);
					break;
				case 3:
					cell_2 = row.createCell(j);
					cell_2.setCellValue(values[i][j]);
					break;
				case 4:
					cell_2 = row.createCell(j);
					cell_2.setCellValue(values[i][j]);
					break;
				case 5:
					cell_2 = row.createCell(j);
					cell_2.setCellValue(values[i][j]);
					break;
				default:
					break;
				}
			}
		}

		return xssfWorkbook;
	}

}
