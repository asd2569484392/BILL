package oa.util;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import oa.bean.Location;

public class TableViewLocationUtil {

	@SuppressWarnings("rawtypes")
	private static TableView table = new TableView();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Scene CreateTableView(ObservableList<Location> list) {
		ScrollPane scrollPane = new ScrollPane();

		table.setEditable(true);

		TableColumn location_id = new TableColumn("库位");
		TableColumn materiel_number = new TableColumn("零件号");
		TableColumn materiel_detail = new TableColumn("零件描述");
		TableColumn materiel_group_code = new TableColumn("物料组代码");
		TableColumn location_detail = new TableColumn("库位详情");
		TableColumn materiel_unit = new TableColumn("单位");
		TableColumn materiel_NRO = new TableColumn("非限制正常");
		TableColumn materiel_UO = new TableColumn("非限制委外");
		TableColumn materiel_PRC = new TableColumn("非限制寄售");
		TableColumn materiel_FO = new TableColumn("冻结正常");
		TableColumn materiel_FC = new TableColumn("冻结委外");
		TableColumn materiel_FCO = new TableColumn("冻结寄售");
		TableColumn materiel_CS = new TableColumn("待检正常");
		TableColumn materiel_CN = new TableColumn("待检委外");
		TableColumn materiel_OIC = new TableColumn("待检寄售");
		TableColumn remarks = new TableColumn("备注");

		location_id.setCellValueFactory(new PropertyValueFactory("locationId"));
		materiel_number.setCellValueFactory(new PropertyValueFactory("materielNumber"));
		materiel_detail.setCellValueFactory(new PropertyValueFactory("materielDetail"));
		materiel_group_code.setCellValueFactory(new PropertyValueFactory("materielGroupCode"));
		location_detail.setCellValueFactory(new PropertyValueFactory("locationDetail"));
		materiel_unit.setCellValueFactory(new PropertyValueFactory("materielUnit"));
		materiel_NRO.setCellValueFactory(new PropertyValueFactory("materielNRO"));
		materiel_UO.setCellValueFactory(new PropertyValueFactory("materielUO"));
		materiel_PRC.setCellValueFactory(new PropertyValueFactory("materielPRC"));
		materiel_FO.setCellValueFactory(new PropertyValueFactory("materielFO"));
		materiel_FC.setCellValueFactory(new PropertyValueFactory("materielFC"));
		materiel_FCO.setCellValueFactory(new PropertyValueFactory("materielFCO"));
		materiel_CS.setCellValueFactory(new PropertyValueFactory("materielCS"));
		materiel_CN.setCellValueFactory(new PropertyValueFactory("materielCN"));
		materiel_OIC.setCellValueFactory(new PropertyValueFactory("materielOIC"));
		remarks.setCellValueFactory(new PropertyValueFactory("remarks"));

		table.getColumns().addAll(location_id, materiel_number, materiel_detail, materiel_group_code, location_detail,
				materiel_unit, materiel_NRO, materiel_UO, materiel_PRC, materiel_FO, materiel_FC, materiel_FCO,
				materiel_CS, materiel_CN, materiel_OIC, remarks);

		table.setItems(list);
		table.setMinHeight(550);
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.getChildren().addAll(table);
		scrollPane.setContent(vbox);
		Scene scene = new Scene(scrollPane);

		return scene;
	}

}
