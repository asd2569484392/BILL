package oa.util;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import oa.bean.Materiel;

public class TableViewUtil {
	
    @SuppressWarnings("rawtypes")
	private static TableView table = new TableView();
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Scene CreateTableView(ObservableList<Materiel> list) {
		ScrollPane scrollPane = new ScrollPane();
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("物料编号");
        TableColumn lastNameCol = new TableColumn("物料描述");
        TableColumn emailCol = new TableColumn("单包装");
        TableColumn materiel_supplier = new TableColumn("供应商");
        TableColumn materiel_type = new TableColumn("类型");
        
        
        
        
        firstNameCol.setPrefWidth(200);
        lastNameCol.setPrefWidth(200);
        emailCol.setPrefWidth(200);
        materiel_supplier.setPrefWidth(200);
        materiel_type.setPrefWidth(200);
      
        firstNameCol.setCellValueFactory(new PropertyValueFactory("materiel_id"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("materiel_detail"));
        emailCol.setCellValueFactory(new PropertyValueFactory("materiel_package"));
        materiel_supplier.setCellValueFactory(new PropertyValueFactory("materiel_supplier"));
        materiel_type.setCellValueFactory(new PropertyValueFactory("materiel_type"));

        
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,materiel_supplier,materiel_type);
        table.setPrefWidth(1000);
        table.setPrefHeight(600);
        table.setItems(list);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(table);
        vbox.setPrefWidth(1000);
        vbox.setPrefHeight(600);
        scrollPane.setContent(vbox);
        Scene scene = new Scene(scrollPane);

        return scene;
	}
	
	
}
