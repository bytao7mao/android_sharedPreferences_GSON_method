package com.example.taolen.gson_demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView txvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvDisplay = findViewById(R.id.dispData);

    }

    public void saveAObjectType(View view) {
        Employee getEmployee = getEmployeeObject();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Serialisation -- converting Employee class into json format
        Gson gson = new Gson();
        String jsonString = gson.toJson(getEmployee, Employee.class);
        Log.i(TAG + "Save", jsonString);
        editor.putString("employee_key", jsonString);
        editor.apply();
    }

    public void loadAObjectType(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("employee_key", "N/A");
        Log.i(TAG + " Load", jsonString);

        // Deseriliasation -- read from json file
        Gson gson = new Gson();
        Employee employee = gson.fromJson(jsonString, Employee.class);
        displayText(employee);
    }

    private void displayText(Employee employee) {
        if (employee == null)
            return;
        String displayText = employee.getName()
                + "\n" + employee.getProfession()
                + "\n" + employee.getProfId()
                + "\n" + employee.getRoles().toString();
        txvDisplay.setText(displayText);
    }

    public void saveGenericType(View view) {
        Employee employee = getEmployeeObject();
        Foo<Employee> foo = new Foo<>();
        foo.setObject(employee);
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Serialisation -- converting Employee class into json format
        Gson gson = new Gson();
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        String jsonString = gson.toJson(foo, type);
        Log.i(TAG, jsonString);
        editor.putString("foo_key", jsonString);
        editor.apply();

    }

    public void loadGenericType(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("foo_key", "N/A");
        Log.i(TAG + " Load", jsonString);

        // Deseriliasation -- read from json file
        Gson gson = new Gson();
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        Foo<Employee> employeeFoo = gson.fromJson(jsonString, type);
        Employee employee = employeeFoo.getObject();
        displayText(employee);
    }
    private Employee getEmployeeObject() {
        Employee employee = new Employee();
        employee.setName("Marius Nicolae");
        employee.setProfession("Android Developer");
        employee.setProfId(123);
        employee.setRoles(Arrays.asList("Developer", "Admin"));

        return employee;
    }
}
