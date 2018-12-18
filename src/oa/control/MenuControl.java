package oa.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oa.bean.Inventorytransaction;
import oa.bean.Location;
import oa.bean.Materiel;
import oa.dao.BomDAO;
import oa.dao.InventoryTransactionDAO;
import oa.dao.LocationDAO;
import oa.main.MainApp;
import oa.util.Dialog;
import oa.util.DragUtil;
import oa.util.ExcelUtil;
import oa.util.TableViewLocationUtil;
import oa.util.TableViewUtil;
import oa.util.Util;

public class MenuControl {

	@FXML
	private Pane menu;

	@FXML
	private Pane cancel;

	@FXML
	private ImageView exit;

	@FXML
	private Pane exitmask;

	@FXML
	private Pane alertMessage;

	@FXML
	private Pane mask;

	@FXML
	private Pane ok;

	@FXML
	private Pane Bom;

	@FXML
	private Pane Import;

	@FXML
	private Pane bomPane;

	@FXML
	private Label alertTitle;

	@FXML
	private Pane alert_override;

	@FXML
	Label excel_name;

	@FXML
	Label enter_ok;

	@FXML
	Label databases;

	@FXML
	Label update_time;

	@FXML
	Pane esc;

	@FXML
	Pane ship;

	@FXML
	TableView<Inventorytransaction> ITTable;

	@FXML
	Pane rec;

	@FXML
	TextField queryItem;

	@FXML
	MenuButton dateQuery;

	static boolean isOverride = false;

	static boolean isAppend = false;

	public static void initDataList(List<String> dateList) {
		MenuButton dateQuery = (MenuButton) MainApp.parent.lookup("#dataList");
		dateQuery.getItems().clear();
		MenuItem reset = new MenuItem("重置");
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				initITView((TableView<Inventorytransaction>) MainApp.parent.lookup("#ITTable"));
			}
		});
		dateQuery.getItems().add(reset);
		dateList.forEach(d -> {
			MenuItem m = new MenuItem(d);
			m.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					queryItByDate(m.getText());
				}
			});
			dateQuery.getItems().add(m);
		});
	}

	public void initITView(String itemQuery) {

		SqlSession mSession = Util.getSession();
		InventoryTransactionDAO inventoryTransactionDAO = mSession.getMapper(InventoryTransactionDAO.class);
		List<Inventorytransaction> liInventorytransactions = null;
		if (itemQuery == null || itemQuery == "") {
			liInventorytransactions = inventoryTransactionDAO.queryAllInventoryTransaction();
		} else {
			liInventorytransactions = inventoryTransactionDAO.queryInventoryTransactionByItem(itemQuery);
		}

		initDataList(inventoryTransactionDAO.queryAllDate());

		initTable(liInventorytransactions);

		mSession.commit();
		mSession.close();
	}

	@SuppressWarnings("unchecked")
	public static void queryItByDate(String data) {

		SqlSession mSession = Util.getSession();
		InventoryTransactionDAO inventoryTransactionDAO = mSession.getMapper(InventoryTransactionDAO.class);
		List<Inventorytransaction> liInventorytransactions = inventoryTransactionDAO
				.queryInventoryTransactionByDate(data);
		;
		System.out.println(liInventorytransactions);
		initTable(liInventorytransactions, (TableView<Inventorytransaction>) MainApp.parent.lookup("#ITTable"));
		mSession.commit();
		mSession.close();
	}

	public void queryDate(String itemQuery) {

		SqlSession mSession = Util.getSession();
		InventoryTransactionDAO inventoryTransactionDAO = mSession.getMapper(InventoryTransactionDAO.class);
		List<Inventorytransaction> liInventorytransactions = null;
		if (itemQuery == null || itemQuery == "") {
			liInventorytransactions = inventoryTransactionDAO.queryAllInventoryTransaction();
		} else {
			liInventorytransactions = inventoryTransactionDAO.queryInventoryTransactionByItem(itemQuery);
		}
		System.out.println(liInventorytransactions);

		initDataList(inventoryTransactionDAO.queryAllDate());

		initTable(liInventorytransactions);

		mSession.commit();
		mSession.close();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initTable(List<Inventorytransaction> liInventorytransactions) {
		ObservableList<Inventorytransaction> list = FXCollections.observableArrayList();

		liInventorytransactions.forEach(i -> {
			list.add(i);
		});
		ITTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("orderCode"));
		ITTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("actionType"));
		ITTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("item"));
		ITTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("number"));
		ITTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("date"));
		ITTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("currentLocation"));
		ITTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("destinationLocation"));

		ITTable.setItems(list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void initTable(List<Inventorytransaction> liInventorytransactions,
			TableView<Inventorytransaction> table) {
		ObservableList<Inventorytransaction> list = FXCollections.observableArrayList();

		liInventorytransactions.forEach(i -> {
			list.add(i);
		});
		table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("orderCode"));
		table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("actionType"));
		table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("item"));
		table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("number"));
		table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("date"));
		table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("currentLocation"));
		table.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("destinationLocation"));
		table.setItems(list);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initITView(TableView<Inventorytransaction> ITTable) {

		SqlSession mSession = Util.getSession();
		InventoryTransactionDAO inventoryTransactionDAO = mSession.getMapper(InventoryTransactionDAO.class);
		List<Inventorytransaction> liInventorytransactions = null;
		liInventorytransactions = inventoryTransactionDAO.queryAllInventoryTransaction();

		ObservableList<Inventorytransaction> list = FXCollections.observableArrayList();

		liInventorytransactions.forEach(i -> {
			list.add(i);
		});

		ITTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("orderCode"));
		ITTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("actionType"));
		ITTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("item"));
		ITTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("number"));
		ITTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("date"));
		ITTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("currentLocation"));
		ITTable.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("destinationLocation"));
		ITTable.setItems(list);
		mSession.commit();
		mSession.close();
	}

	@FXML
	public void exitApp(MouseEvent event) {

		showMessage("确认退出?");
		ok.setLayoutX(400);
		ok.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				MainApp.primaryStage.close();
			}
		});

		esc.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				hideMessage();
			}
		});

	}

	@FXML
	public void enter(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void showBomInfo(MouseEvent event) {
		SqlSession mSession = Util.getSession();
		BomDAO mBom = mSession.getMapper(BomDAO.class);
		ObservableList<Materiel> list = FXCollections.observableArrayList();
		List<Materiel> mList = mBom.queryAllBomData();
		mSession.commit();
		mList.forEach(m -> {
			list.add(m);
		});
		System.out.println(mList.toString());
		Stage info = new Stage();
		info.setTitle("BOM信息");
		info.getIcons().add(new Image("/resource/table.png"));
		info.setWidth(1000);
		info.setHeight(600);

		info.setScene(TableViewUtil.CreateTableView(list));
		info.show();
		mSession.close();
	}

	@FXML
	public void mainMenuOnClick(MouseEvent event) {

	}

	public void showMessage(String title) {
		alertMessage.setVisible(true);
		mask.setVisible(true);
		ok.setVisible(true);
		alertTitle.setText(title);
		esc.setVisible(true);
		alert_override.setVisible(false);
		enter_ok.setText("确定");
	}

	public void showLoading() {
		alertMessage.setFocusTraversable(true);
		alertMessage.setVisible(true);
		mask.setVisible(true);
		alertTitle.setText("正在初始化数据……");
		alertTitle.setTextAlignment(TextAlignment.CENTER);
		alertTitle.setLayoutX(340);
		alertTitle.setLayoutY(45);

		ok.setVisible(false);
		alert_override.setVisible(false);
		esc.setVisible(false);
	}

	public void showMessageDataImport() {
		alertMessage.setVisible(true);
		mask.setVisible(true);
		alertTitle.setText("当前已有基础数据!");
		alertTitle.setTextAlignment(TextAlignment.CENTER);
		ok.setVisible(false);
		esc.setVisible(true);
		alert_override.setVisible(true);
		enter_ok.setText("覆蓋");
		alert_override.setLayoutX(400);
		esc.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				hideMessage();
			}
		});
	}

	public void hideMessage() {
		alertMessage.setVisible(false);
		mask.setVisible(false);
	}

	@FXML
	public void selectExcel(MouseEvent event) {
		SqlSession session = Util.getSession();
		BomDAO bom = session.getMapper(BomDAO.class);

		if (bom.queryCurrentMaterielLength() > 0) {
			showMessageDataImport();
			alert_override.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					// 隐藏消息
					hideMessage();
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("选择Excel文件");
					Stage selectFile = new Stage();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
							new FileChooser.ExtensionFilter("XLS", "*.xls"),
							new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
					File file = fileChooser.showOpenDialog(selectFile);
					if (file != null) {
						try {

							bom.insertOrUpdate(ExcelUtil.importExcel(Util.getWorkbok(new FileInputStream(file), file)));
							;
							session.commit();
							session.close();
						} catch (FileNotFoundException e) {
							Dialog.f_alert_ErrorDialog("错误", "文件没有找到");
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (Exception e) {
							e.printStackTrace();
							Dialog.f_alert_ErrorDialog("错误", "请检查Excel数据及格式");
						}
					}
				}

			});

			esc.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					hideMessage();
				}
			});
		} else {
			hideMessage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("选择Excel文件");
			Stage selectFile = new Stage();
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
					new FileChooser.ExtensionFilter("XLS", "*.xls"), new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
			File file = fileChooser.showOpenDialog(selectFile);
			if (file != null) {
				try {
					bom.initBOM(ExcelUtil.importExcel(Util.getWorkbok(new FileInputStream(file), file)));
					session.commit();
					session.close();
				} catch (FileNotFoundException e) {
					Dialog.f_alert_ErrorDialog("错误", "文件没有找到");
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
					Dialog.f_alert_ErrorDialog("错误", "请检查Excel数据及格式");
				}
			}
		}
	}

	public void shipAction(MouseEvent mouseEvent) {
		try {
			Stage info = new Stage();
			info.setTitle("发货");
			info.getIcons().add(new Image("/resource/ship.png"));
			info.setWidth(600);
			info.setHeight(600);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getClassLoader().getResource("view/ship.fxml"));
			Parent parent = loader.load();
			Pane hBox = (Pane) parent.lookup("#vbox_1");
			DragUtil.addDragListener(info, hBox);
			info.initStyle(StageStyle.TRANSPARENT);
			Button close = (Button) parent.lookup("#close_ship");
			close.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					info.close();
				}
			});
			info.setScene(new Scene(parent));
			info.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recAction(MouseEvent mouseEvent) {
		try {
			Stage info = new Stage();
			info.setTitle("收货");
			info.getIcons().add(new Image("/resource/rec.png"));
			info.setWidth(600);
			info.setHeight(600);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getClassLoader().getResource("view/rec.fxml"));
			Parent parent = loader.load();
			Pane hBox = (Pane) parent.lookup("#vbox_1");
			DragUtil.addDragListener(info, hBox);
			info.initStyle(StageStyle.UNDECORATED);
			Button close = (Button) parent.lookup("#close_ship");
			close.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					info.close();
				}
			});
			info.setScene(new Scene(parent));
			info.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void initLocation(MouseEvent Event) {
		SqlSession session = Util.getSession();
		LocationDAO lcoation_DAO = session.getMapper(LocationDAO.class);
		if (lcoation_DAO.queryLocationCount() > 0) {
			showMessageDataImport();

			alert_override.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					// 隐藏消息
					hideMessage();
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("选择Excel文件");
					Stage selectFile = new Stage();
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
							new FileChooser.ExtensionFilter("XLS", "*.xls"),
							new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
					File file = fileChooser.showOpenDialog(selectFile);
					if (file != null) {
						try {

							lcoation_DAO.initLocation(ExcelUtil
									.importLocationExcel(((Util.getWorkbok(new FileInputStream(file), file)))));

							session.commit();
							session.close();
						} catch (FileNotFoundException e) {
							Dialog.f_alert_ErrorDialog("错误", "文件没有找到");
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}catch (Exception e) {
							e.printStackTrace();
							Dialog.f_alert_ErrorDialog("错误", "请检查Excel数据及格式");
						}
					}
				}

			});
		} else {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("选择Excel文件");
			Stage selectFile = new Stage();
			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Excel", "*.xlsx"),
					new FileChooser.ExtensionFilter("XLS", "*.xls"), new FileChooser.ExtensionFilter("XLSX", "*.xlsx"));
			File file = fileChooser.showOpenDialog(selectFile);
			if (file != null) {
				try {
					lcoation_DAO.initLocation(
							ExcelUtil.importLocationExcel(((Util.getWorkbok(new FileInputStream(file), file)))));
					session.commit();
					session.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					e.printStackTrace();
					Dialog.f_alert_ErrorDialog("错误", "请检查Excel数据及格式");
				}
			}
		}
	}

	@FXML
	public void queryLocation(MouseEvent event) throws IOException {
		SqlSession mSession = Util.getSession();
		LocationDAO location_DAO = mSession.getMapper(LocationDAO.class);
		ObservableList<Location> list = FXCollections.observableArrayList();
		List<Location> mList = location_DAO.queryAll();
		mSession.commit();
		mSession.close();
		mList.forEach(m -> {
			list.add(m);
		});
		System.out.println("[queryLocation]:" + mList.toString());
		Stage info = new Stage();
		info.setTitle("库存信息");
		info.getIcons().add(new Image("/resource/table.png"));
		info.setWidth(1000);
		info.setHeight(650);
		info.setScene(TableViewLocationUtil.CreateTableView(list));
		info.show();
	}
	// FXMLLoader loader = new FXMLLoader();
	// loader.setLocation(MainApp.class.getClassLoader().getResource("view/LocationQuery.fxml"));
	// Parent parent = loader.load();
	// Stage info = new Stage();
	// info.setTitle("库存信息");
	// info.getIcons().add(new Image("/resource/table.png"));
	// info.setWidth(1200);
	// info.setHeight(800);
	// info.setScene(new Scene(parent));
	// info.show();

	@FXML
	public <V> void queryIT(KeyEvent event) {
		// Platform.runLater(new Runnable() {
		// @Override
		// public void run() {
		//
		// }
		// });
		new Thread(new Task<V>() {

			@Override
			protected V call() throws Exception {
				return null;
			}

			@Override
			protected void succeeded() {
				initITView(queryItem.getText());
				super.succeeded();
			}

		}).start();
	}

	/***
	 * 打开导出Excel面板
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void openExportPane(MouseEvent event) throws IOException {
		Stage info = new Stage();
		info.setMinHeight(445);
		info.setMinWidth(406);
		info.setTitle("导出Excel");
		
		info.getIcons().add(new Image("/resource/Export.png"));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getClassLoader().getResource("view/Export.fxml"));
		Parent parent = loader.load();
		ExportControl exportControl = loader.getController();

		info.setResizable(false);
		info.initStyle(StageStyle.DECORATED);
		info.setScene(new Scene(parent));
		exportControl.initView();
		info.show();
	}

}
