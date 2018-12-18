package oa.util;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/***
 * 弹出框
 * @author asd25
 *
 */
public class Dialog {
	
    public static void f_alert_ErrorDialog(String p_header, String p_message){
        Alert _alert = new Alert(Alert.AlertType.ERROR);
        _alert.initStyle(StageStyle.TRANSPARENT);
        _alert.setHeaderText(p_header);
        _alert.setContentText(p_message);
        _alert.show();
    }
    
    public static void f_alert_informationDialog(String p_header, String p_message){
        Alert _alert = new Alert(Alert.AlertType.CONFIRMATION);
        _alert.initStyle(StageStyle.TRANSPARENT);
        _alert.setHeaderText(p_header);
        _alert.setContentText(p_message);
        _alert.show();
    }
}
