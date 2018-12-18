package oa.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oa.bean.Inventorytransaction;
import oa.bean.Location;
import oa.bean.Materiel;
import oa.bean.Sum;
import oa.dao.BomDAO;
import oa.dao.InventoryTransactionDAO;
import oa.dao.LocationDAO;
import oa.util.Dialog;
import oa.util.ExcelUtil;
import oa.util.Util;

public class ExportControl {

	@FXML
	Button exportBOM;

	@FXML
	Button exportLocation;

	@FXML
	Button exportIT;

	@FXML
	VBox exportVbox;

	private DatePicker checkInDatePicker;
	private DatePicker checkOutDatePicker;

	public void initView() {

		checkInDatePicker = new DatePicker();
		checkOutDatePicker = new DatePicker();
		checkInDatePicker.setValue(LocalDate.now());
		checkOutDatePicker.setValue(checkInDatePicker.getValue().plusDays(1));
		checkInDatePicker.setMinWidth(370);
		checkOutDatePicker.setMinWidth(370);

		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		Label checkInlabel = new Label("开始时间:");

		checkInlabel
				.setStyle("-fx-font-color: red;\r\n" + "    -fx-font-size: 20px;\r\n" + "    -fx-font-weight: bolder;");

		gridPane.add(checkInlabel, 0, 0);
		GridPane.setHalignment(checkInlabel, HPos.LEFT);

		gridPane.add(checkInDatePicker, 0, 1);

		Label checkOutlabel = new Label("结束时间:");

		checkOutlabel
				.setStyle("-fx-font-color: red;\r\n" + "    -fx-font-size: 20px;\r\n" + "    -fx-font-weight: bolder;");

		gridPane.add(checkOutlabel, 0, 2);
		GridPane.setHalignment(checkOutlabel, HPos.LEFT);

		gridPane.add(checkOutDatePicker, 0, 3);
		exportVbox.getChildren().add(gridPane);
		Button button = new Button();
		button.setStyle("-fx-background-radius: 0;  \r\n" + "-fx-background-color: #0097a7;");
		button.setText("导出库存事务");
		button.setMinWidth(370);
		button.setMinHeight(57);
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				DirectoryChooser folderChooser = new DirectoryChooser();
				folderChooser.setTitle("选择导出目录");
				Stage selectFile = new Stage();

				File path = folderChooser.showDialog(selectFile);
				if (path != null) {

					SqlSession session = Util.getSession();
					InventoryTransactionDAO transactionDAO = session.getMapper(InventoryTransactionDAO.class);
					LocationDAO locationDAO = session.getMapper(LocationDAO.class);

					// 流水数据
					List<Inventorytransaction> inventorytransactions = transactionDAO
							.queryInventoryTransactionByDateOnBetween(checkInDatePicker.getValue().toString(),
									checkOutDatePicker.getValue().toString());
					// 获取不重复item
					List<String> groupByItem = transactionDAO.queryInventoryTransactionGroupByItem();
					// 定义Location
					List<Location> locations = new ArrayList<>();
					// 定义汇总集合
					List<Sum> sums = new ArrayList<>();

					// 查询所有不重复item库位信息
					groupByItem.forEach(item -> {
						locations.add(locationDAO.queryLocationById(item));
						System.out.println("[SUM]"+transactionDAO.queryInventoryTransactionByDateAndSun(
								checkInDatePicker.getValue().toString(), checkOutDatePicker.getValue().toString(),
								item));
						Sum sum = transactionDAO.queryInventoryTransactionByDateAndSun(
								checkInDatePicker.getValue().toString(), checkOutDatePicker.getValue().toString(),
								item);
						if(sum!=null) {
							sums.add(sum);	
						}
					});
					
					System.out.println("[SUMS]:"+sums.toString());
					
					// System.out.println(checkInDatePicker.getValue().toString()+"
					// "+checkOutDatePicker.getValue().toString());
					System.out.println(inventorytransactions.toString());
					// Sheet表头
					String[] header_1 = { "订单号", "收发", "物料号", "收发数", "日期" ,"当前库位","目的库位" };
					String[] header_2 = { "零件号", "描述", "期初", "当前时间段总发出", "当前时间段总入库", "期末" };
					String[][] values = new String[sums.size()][header_2.length];
					for(int i = 0; i < values.length; i++) {
						for (int j = 0; j < values[i].length; j++) {
							switch (j) {
							case 0:
								values[i][j] = sums.get(i).getItem();
								break;
							case 1:
								values[i][j] = locations.get(i).getMaterielDetail();
								break;
							case 2:
								Sum s = sums.get(i);
								values[i][j] = String.valueOf(locations.get(i).getMaterielNRO()-Integer.parseInt(s.getRec())+Integer.parseInt(s.getShip()));
								break;
							case 3:
								values[i][j] = sums.get(i).getShip();
								break;
							case 4:
								values[i][j] = sums.get(i).getRec();
								break;
							case 5:
								values[i][j] = String.valueOf(locations.get(i).getMaterielNRO());
								break;
							default:
								break;
							}
						}
					}
					
					File bomExcel = new File(path.getPath() + "/库存事务-" + new Date().getTime() + ".xlsx");
					try {
						bomExcel.createNewFile();
						FileOutputStream stream = FileUtils.openOutputStream(bomExcel);
						String[] sheetPage = {"流水","汇总"};

						XSSFWorkbook xss = ExcelUtil.createInventoryWorkBook(sheetPage, header_1,header_2,inventorytransactions,values);

						xss.write(stream);
						stream.close();
						Dialog.f_alert_informationDialog("导出成功", "已生成Excel在" + path.getPath());
					} catch (IOException e) {
						Dialog.f_alert_ErrorDialog("错误", "IO错误");
						e.printStackTrace();
					}
				} else {
					Dialog.f_alert_ErrorDialog("错误", "未选择路径");
				}

			}
		});
		Pane pane = new Pane();
		pane.setMinHeight(50);
		exportVbox.getChildren().add(pane);
		exportVbox.getChildren().add(button);
	}

	@FXML
	public void exportBomToExcel() {
		String[] header = { "物料号", "描述", "单包装", "供货商" };
		SqlSession session = Util.getSession();
		BomDAO bomDAO = session.getMapper(BomDAO.class);
		List<Materiel> list = bomDAO.queryAllBomData();
		session.commit();
		session.close();
		Stage selectFile = new Stage();
		DirectoryChooser folderChooser = new DirectoryChooser();
		folderChooser.setTitle("选择导出目录");
		File path = folderChooser.showDialog(selectFile);
		if (path != null) {
			File bomExcel = new File(path.getPath() + "/BOM-" + new Date().getTime() + ".xlsx");
			try {
				bomExcel.createNewFile();
				FileOutputStream stream = FileUtils.openOutputStream(bomExcel);

				XSSFWorkbook xss = ExcelUtil.createBomWorkBook("All", header, list);

				xss.write(stream);
				stream.close();
				Dialog.f_alert_informationDialog("导出成功", "已生成Excel在" + path.getPath());
			} catch (IOException e) {
				Dialog.f_alert_ErrorDialog("错误", "IO错误");
				e.printStackTrace();
			}
		} else {
			Dialog.f_alert_ErrorDialog("错误", "未选择路径");
		}

	}

	@FXML
	public void exportLocationToExcel() {
		String[] header = { "库位代码", "零件号", "零件描述", "物料组代码", "库位", "单位", "非限制正常", "非限制委外", "非限制寄售", "冻结正常", "冻结委外",
				"冻结寄售", "待检正常", "待检委外", "待检寄售", "备注", };
		SqlSession session = Util.getSession();
		LocationDAO locationDAO = session.getMapper(LocationDAO.class);
		List<Location> list = locationDAO.queryAll();
		session.commit();
		session.close();
		Stage selectFile = new Stage();
		DirectoryChooser folderChooser = new DirectoryChooser();
		folderChooser.setTitle("选择导出目录");
		File path = folderChooser.showDialog(selectFile);
		if (path != null) {
			File bomExcel = new File(path.getPath() + "/Location-" + new Date().getTime() + ".xlsx");
			try {
				bomExcel.createNewFile();
				FileOutputStream stream = FileUtils.openOutputStream(bomExcel);

				XSSFWorkbook xss = ExcelUtil.createLocationWorkBook("sheet1", header, list);

				xss.write(stream);
				stream.close();
				Dialog.f_alert_informationDialog("导出成功", "已生成Excel在" + path.getPath());
			} catch (IOException e) {
				Dialog.f_alert_ErrorDialog("错误", "IO错误");
				e.printStackTrace();
			}
		} else {
			Dialog.f_alert_ErrorDialog("错误", "未选择路径");
		}
	}

}
