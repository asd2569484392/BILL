package oa.main;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oa.control.MenuControl;
import oa.util.CreateMySqlDatabase;
import oa.util.DragUtil;

/**
 * 主程序入口
 * 
 * @author asd25
 *
 */
public class MainApp extends Application {

	private static final int WIDTH = 892;

	private static final int HEIGTH = 702;

	public static Parent parent;

	boolean isError = true;
	
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws InterruptedException {

		try {
			DriverManager.getConnection("jdbc:mysql://localhost:3306/utility", "root", "1234");
			isError = false;
		} catch (MySQLSyntaxErrorException e) {
			primaryStage.setTitle("正在创建Utility数据库……");
			Label error = new Label("正在创建Utility数据库……");
			error.setFont(new Font("Cambria", 100));
			Pane pane = new Pane();
			pane.getChildren().add(error);
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
			try {
				CreateMySqlDatabase.createDatabase();
				CreateMySqlDatabase.executeSql();
				isError = false;
				primaryStage.close();
			} catch (SQLException | IOException e1) {
				primaryStage.close();
				e1.printStackTrace();
			}

		} catch (SQLException se) {
			Thread.sleep(3000);
			primaryStage.close();
			se.printStackTrace();
		}

		if (!isError) {
			run();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {
		try {
			primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getClassLoader().getResource("view/Menu.fxml"));
			parent = loader.load();
			Pane hBox = (Pane) parent.lookup("#menu");
			DragUtil.addDragListener(primaryStage, hBox);
			primaryStage.setWidth(WIDTH);
			primaryStage.setHeight(HEIGTH);
			primaryStage.setResizable(false);
			primaryStage.setTitle("手工账单");
			primaryStage.getIcons().add(new Image("/resource/rec.png"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			Scene main = new Scene(parent);
			primaryStage.setScene(main);
			// 获取控制器
			MenuControl menuControl = loader.getController();
			// 初始化库存事务
			menuControl.initITView("");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
