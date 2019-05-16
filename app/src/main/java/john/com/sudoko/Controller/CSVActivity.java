package john.com.sudoko.Controller;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import java.io.FileReader;

import john.com.sudoko.R;
public class CSVActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    private File file;
    private Intent intent;

    private String[] chapterName;
    private String[] Kword;
    private String[] Eword;


    Button btnUpDirectory,btnSDCard;

    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;
    ListView lvInternalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.import_csv);
        lvInternalStorage = (ListView) findViewById(R.id.lvInternalStorage);
        btnUpDirectory = (Button) findViewById(R.id.btnUpDirectory);
        btnSDCard = (Button) findViewById(R.id.btnViewSDCard);
        //need to check the permissions
        checkFilePermissions();

        lvInternalStorage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if(lastDirectory.equals(adapterView.getItemAtPosition(i))){

                    //Execute method for reading the excel data.
                    readCSVData(lastDirectory);

                    onClick(view);
                }else
                {
                    count++;
                    pathHistory.add(count,(String) adapterView.getItemAtPosition(i));
                    checkInternalStorage();
                }
            }
        });

        //Goes up one directory level
        btnUpDirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == 0){
                    finish();
                }else{
                    pathHistory.remove(count);
                    count--;
                    checkInternalStorage();
                }
            }
        });

        //Opens the SDCard or phone memory
        btnSDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                pathHistory = new ArrayList<String>();
                pathHistory.add(count,System.getenv("EXTERNAL_STORAGE"));
                checkInternalStorage();
            }
        });


    }

    private void readCSVData(String filePath) {
        File csvfile = new File(filePath);

        try {
            CSVReader reader = new CSVReader(new FileReader(csvfile));
            String[] nextLine;
            int i = 0;
            while ((nextLine = reader.readNext()) != null) {
                i++;
            }
            chapterName = new String[i-1];
            Kword = new String[i-1];
            Eword = new String[i-1];
            i=0;

            reader = new CSVReader(new FileReader(csvfile));
            while ((nextLine = reader.readNext()) != null) {

                if(i==0){

                } else {
                    chapterName[i-1] = nextLine[0];
                    Kword[i-1] = nextLine[1];
                    Eword[i-1] = nextLine[2];

                }
                i++;
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkInternalStorage() {
        try{
            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                toastMessage("No SD card found.");
            }
            else{
                // Locate the image folder in your SD Car;d
                file = new File(pathHistory.get(count));
            }

            listFile = file.listFiles();

            // Create a String array for FilePathStrings
            FilePathStrings = new String[listFile.length];

            // Create a String array for FileNameStrings
            FileNameStrings = new String[listFile.length];

            for (int i = 0; i < listFile.length; i++) {
                // Get the path of the image file
                FilePathStrings[i] = listFile[i].getAbsolutePath();
                // Get the name image file
                FileNameStrings[i] = listFile[i].getName();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FilePathStrings);
            lvInternalStorage.setAdapter(adapter);

        }catch(NullPointerException e){
            Log.e("TAG", "checkInternalStorage: NULLPOINTEREXCEPTION " + e.getMessage() );
        }
    }

    //CheckSelfPermission
    //requestPermissions
    //have red lines because of difference of versions
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
            }
        }else{
            Log.d("TAG", "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {

        intent = new Intent(this, CSV_VocabsActivity.class);
        intent.putExtra("CHNAME",chapterName);
        intent.putExtra("EWORD",Eword);
        intent.putExtra("KWORD",Kword);
        startActivity(intent);

    }
}
