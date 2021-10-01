package karbanovich.fit.bstu.calculcal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private String[] gender = {"Мужской", "Женский"};
    private String selectedGender = null;
    private double physicalActivity = 1.2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Gender
        Spinner spinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        //Physical activity
        RadioGroup PhysActivRadioGroup = (RadioGroup) findViewById(R.id.physicalActivity);
        PhysActivRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.zeroPerWeek:
                        physicalActivity = 1.2;
                        break;
                    case R.id.oneThreePerWeek:
                        physicalActivity = 1.375;
                        break;
                    case R.id.threeFivePerWeek:
                        physicalActivity = 1.55;
                        break;
                    case R.id.sixSevenPerWeek:
                        physicalActivity = 1.725;
                        break;
                    case R.id.sevenPerWeek:
                        physicalActivity = 1.9;
                        break;
               }
           }});
    }

    public void CalculateCalories(View view){
        EditText age = (EditText) findViewById(R.id.age);
        EditText weight = (EditText) findViewById(R.id.weight);
        EditText height = (EditText) findViewById(R.id.height);
        TextView CaloriesResult = (TextView) findViewById(R.id.calculatedCalories);

        double BMR = 0;
        double AMR;
        double w = Double.parseDouble(weight.getText().toString());
        double h = Double.parseDouble(height.getText().toString());
        double a = Double.parseDouble(age.getText().toString());

        if(selectedGender == "Мужской"){
            BMR = 88.362 + (13.397 * w) + (4.799 * h) - (5.677 * a);
        }
        else if(selectedGender == "Женский"){
            BMR = 447.593 + (9.247 * w) + (3.098 * h) - (4.330 * a);
        }

        int result =  (int) (BMR * physicalActivity);

        CaloriesResult.setText("Суточная норма калорий: " + Integer.toString(result));
    }
}
