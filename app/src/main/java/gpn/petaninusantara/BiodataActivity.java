package gpn.petaninusantara;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import java.util.HashMap;

import fr.ganfra.materialspinner.MaterialSpinner;

public class BiodataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    MaterialSpinner genderSpinner, provinceSpinner, citySpinner;
    RelativeLayout parent;
    ArrayAdapter<String> cityAdapter;

    String[] province = {"Aceh", "Bali", "Banten", "Bengkulu",
            "DI Yogyakarta", "DKI Jakarta", "Gorontalo", "Jambi",
            "Jawa Barat", "Jawa Tengah", "Jawa Timur", "Kalimantan Barat",
            "Kalimantan Selatan", "Kalimantan Tengah", "Kalimantan Timur",
            "Kalimantan Utara", "Kep. Bangka Belitung", "Kep. Riau",
            "Lampung", "Maluku", "Maluku Utara", "Nusa Tenggara Barat",
            "Nusa Tenggara Timur", "Papua", "Papua Barat", "Riau",
            "Sulawesi Barat", "Sulawesi Selatan", "Sulawesi Tengah",
            "Sulawesi Tenggara", "Sulawesi Utara", "Sumatera Barat",
            "Sumatera Selatan", "Sumatera Utara"};
    HashMap<String, String []> hash_province = new HashMap<String, String []>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        generateData();
        parent = (RelativeLayout) findViewById(R.id.parentlayout);

        String[] gender = {"Laki-Laki", "Perempuan"};
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner = (MaterialSpinner) findViewById(R.id.genderSpinner);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String> (this,
                android.R.layout.simple_spinner_item, province);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner = (MaterialSpinner) findViewById(R.id.provinceSpinner);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setOnItemSelectedListener(this);

        cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                new String[]{});
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner = (MaterialSpinner) findViewById(R.id.citySpinner);
        citySpinner.setAdapter(cityAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position >= 0){
            fillCitySpinner(province[position]);
        } else {
            cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    new String[]{});
            cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            citySpinner = (MaterialSpinner) findViewById(R.id.citySpinner);
            citySpinner.setAdapter(cityAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Snackbar.make(parent, "Mohon pilih provinsi terlebih dahulu", Snackbar.LENGTH_SHORT).show();
    }

    private void generateData(){
        hash_province.put("Aceh", new String[]{"Kab. Aceh Barat", "Kab. Aceh Barat Daya",
                "Kab. Aceh Besar", "Kab. Aceh Jaya", "Kab. Aceh Selatan", "Kab. Aceh Singkil",
                "Kab. Aceh Tamiang", "Kab. Aceh Tengah", "Kab. Aceh Tenggara", "Kab. Aceh Timur",
                "Kab. Aceh Utara", "Kab. Bener Meriah", "Kab. Bireuen", "Kab. Gayo Lues",
                "Kab. Nagan Raya", "Kab. Pidie", "Kab. Pidie Jaya", "Kab. Simeulue",
                "Kota Banda Aceh", "Kota Langsa", "Kota Lhokseumawe", "Kota Sabang",
                "Kota Subulussalam"});
    }

    private void fillCitySpinner(String strProvince){
        String[] city = null;
        cityAdapter = null;
        try {
            city = hash_province.get(strProvince);
            cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    city);
        } catch (NullPointerException e){
            cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    new String[]{});
        }
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
    }
}
