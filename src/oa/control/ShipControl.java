package oa.control;

import org.apache.ibatis.session.SqlSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import oa.bean.Inventorytransaction;
import oa.bean.Location;
import oa.bean.Materiel;
import oa.dao.BomDAO;
import oa.dao.InventoryTransactionDAO;
import oa.dao.LocationDAO;
import oa.main.MainApp;
import oa.util.Dialog;
import oa.util.StrUtil;
import oa.util.TimeUtil;
import oa.util.Util;

public class ShipControl {

	@FXML
	TextField item;

	@FXML
	Label materiel_detail;

	@FXML
	Label materiel_qty;

	@FXML
	Label materiel_supplier;

	@FXML
	Label materiel_type;

	@FXML
	TextField shipNumber;

	@FXML
	TextField order_code;

	@FXML
	Label NRN;

	@FXML
	Label locationDetail;

	@FXML
	TextField currentLocation;

	@FXML
	TextField destinationLocation;
	
	@FXML
	public void queryMateriel(KeyEvent event) {
		if (item.getText().length() == 8 && Util.isInteger(item.getText())) {
			try {
				SqlSession session = Util.getSession();
				BomDAO bom = session.getMapper(BomDAO.class);
				LocationDAO locationDAO = session.getMapper(LocationDAO.class);

				Location location = locationDAO.queryLocationById(item.getText());
				Materiel materiel = bom.queryMaterielByItem(item.getText());
				System.out.println(location.toString());
				System.out.println(materiel.toString());

				materiel_detail.setText(materiel.getMateriel_detail());
				materiel_qty.setText(String.valueOf(materiel.getMateriel_package()));
				materiel_supplier.setText(materiel.getMateriel_supplier());
				materiel_type.setText(materiel.getMateriel_type());
				NRN.setText(String.valueOf(location.getMaterielNRO()));
				locationDetail.setText(location.getLocationDetail());

				session.commit();
				session.close();
			} catch (NullPointerException e) {
				materiel_detail.setText("请检查Bom数据及库存");
				materiel_qty.setText("请检查Bom数据及库存");
				materiel_supplier.setText("请检查Bom数据及库存");
				materiel_type.setText("请检查Bom数据及库存");
				NRN.setText("请检查Bom数据及库存");
				locationDetail.setText("请检查Bom数据及库存");

			}
		} else if (item.getText().length() > 8) {
			materiel_detail.setText("无查询结果");
			materiel_qty.setText("无查询结果");
			materiel_supplier.setText("无查询结果");
			materiel_type.setText("无查询结果");
			NRN.setText("无查询结果");
			locationDetail.setText("无查询结果");

		} else {
			materiel_detail.setText("无查询结果");
			materiel_qty.setText("无查询结果");
			materiel_supplier.setText("无查询结果");
			materiel_type.setText("无查询结果");
			NRN.setText("无查询结果");
			locationDetail.setText("无查询结果");
		}

	}

	@SuppressWarnings("unchecked")
	@FXML
	public void applyORD(MouseEvent event) {
		SqlSession session = Util.getSession();
		if (StrUtil.isNotEmpty(NRN) && StrUtil.isNotEmpty(item.getText()) && StrUtil.isNotEmpty(order_code.getText())
				&& StrUtil.isNotEmpty(shipNumber.getText()) && Long.parseLong(shipNumber.getText()) > 0) {
			if (Util.isInteger(shipNumber.getText()) && Util.isDouble(NRN.getText())
					&& Util.isDouble(materiel_qty.getText())) {
				if (Float.parseFloat(shipNumber.getText()) > Float.parseFloat(NRN.getText())) {
					Dialog.f_alert_informationDialog("错误", "库存不足");
					return;
				} else {
					// if(Float.parseFloat(shipNumber.getText()) %
					// Float.parseFloat(materiel_qty.getText()) ==0) {
					try {
						InventoryTransactionDAO dao = session.getMapper(InventoryTransactionDAO.class);
						dao.addInventoryTransaction(new Inventorytransaction(order_code.getText(), "SHIP",
								TimeUtil.getyyyyMMdd(), Integer.parseInt(shipNumber.getText()), item.getText(),currentLocation.getText(),destinationLocation.getText()));
						LocationDAO locationDAO = session.getMapper(LocationDAO.class);
						locationDAO.subNRO(item.getText(), Integer.parseInt(shipNumber.getText()));
						session.commit();
						clear();
						Dialog.f_alert_informationDialog("发货成功", "已扣减生成事务");
						MenuControl.initITView((TableView<Inventorytransaction>) MainApp.parent.lookup("#ITTable"));
						// MenuControl.initITView(itView,null);
					} catch (Exception e) {
						session.rollback();
						e.printStackTrace();
						Dialog.f_alert_ErrorDialog("错误，事务已回滚。", e.getMessage());
					} finally {
						session.close();
					}
					// }else {
					// Dialog.f_alert_ErrorDialog("错误", "整包收发");
					// }
				}
			} else {
				Dialog.f_alert_ErrorDialog("错误", "请检查数据");
			}
		} else {
			Dialog.f_alert_ErrorDialog("错误", "请检查表单");

		}
	}

	private void clear() {
		materiel_detail.setText("无数据");
		materiel_qty.setText("无数据");
		materiel_supplier.setText("无数据");
		materiel_type.setText("无数据");
		NRN.setText("无数据");
		locationDetail.setText("无数据");
		item.setText("");
		shipNumber.setText("");
		order_code.setText("");
	}

	@FXML
	public void closeShipView(MouseEvent event) {

	}
}
