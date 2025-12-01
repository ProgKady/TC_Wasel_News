import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main_WindowController implements Initializable {

    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextArea message;

    @FXML
    private JFXTextField imgname;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXTextField repo;

    @FXML
    private JFXTextField branch;

    @FXML
    private JFXTextArea fullmode;

    @FXML
    private JFXTextField folder;
    
    
     @FXML
    void generateaction(ActionEvent event) {

        CodeGeneratorApp ffd=new CodeGeneratorApp();
        ffd.start(new Stage());
        
    }
    
    

    /***
     * دالة لتحويل النص لأي نص آمن داخل JSON (Escape للأحرف الخاصة)
     */
    private String escapeJson(String text) {
        if (text == null) return "";
        return text
                .replace("\\", "\\\\")  // لازم الأول
                .replace("\"", "\\\"")
                .replace("\r", "")       // إزالة CR لو موجود
                .replace("\n", "\\n")    // تحويل الأسطر
                .replace("\t", "\\t")
                .replace("\u00A0", " "); // تحويل المسافات غير القابلة للكسر
    }

    @FXML
    void addaction(ActionEvent event) {

        String titlee = escapeJson(title.getText().trim());
        String messagee = escapeJson(message.getText().trim());
        String image = "https://raw.githubusercontent.com/" + user.getText().trim() + "/" +
                repo.getText().trim() + "/" + branch.getText().trim() + "/" +
                folder.getText().trim() + "/" + imgname.getText().trim();
        String datee = escapeJson(date.getText().trim());

        String jsonBlock = "{\n" +
                "      \"title\": \"" + titlee + "\",\n" +
                "      \"message\": \"" + messagee + "\",\n" +
                "      \"image\": \"" + image + "\",\n" +
                "      \"date\": \"" + datee + "\"\n" +
                "    }";

        if (fullmode.getText().trim().isEmpty()) {
            fullmode.setText(jsonBlock);
        } else {
            // نضيف فاصلة قبل الكائن التالي
            fullmode.appendText(",\n" + jsonBlock);
        }
    }

    @FXML
    void copyaction(ActionEvent event) {

        String textt = fullmode.getText().trim();

        // إزالة أي فاصلة زائدة لو المستخدم نسخها يدويًا
        if (textt.endsWith(",")) {
            textt = textt.substring(0, textt.length() - 1);
        }

        String full = "{\n" +
                "  \"notifications\": [\n" + textt + "\n  ]\n" +
                "}";

        fullmode.setText(full);
        fullmode.selectAll();
        fullmode.copy();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            String fontPath = "Fonts\\Cairo.ttf";
            Font cairoSemiBold = Font.loadFont(new FileInputStream(fontPath), 18);
        } catch (FileNotFoundException ex) {
            // تجاهل لو الخط مش موجود
        }

        Date currentDate = GregorianCalendar.getInstance().getTime();
        DateFormat df = DateFormat.getDateInstance();
        String dateString = df.format(currentDate);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = sdf.format(d);
        date.setText(timeString);
    }
}
