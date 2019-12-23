package com.rashmin.miniapp.ui.product_list_action_bar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rashmin.miniapp.R;
import com.rashmin.miniapp.util.Product;
import com.rashmin.miniapp.util.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class Action_Bar_ProductActivity extends AppCompatActivity implements View.OnClickListener, ProductAdapter.MyAdapterItemClickListener {



    private RecyclerView recyclerView;
    private Button product_add;
    private EditText product_id;
    private EditText product_name;
    private EditText product_category;
    private EditText product_description;
    private EditText product_price;


    private final String TAG = "firstactivity";
    List<Product> products;
    private SqliteHelper dbHelper;
    private int toUpdate=0;
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action__bar_product);

        product_add = findViewById(R.id.buttonAdd);
        product_id = findViewById(R.id.editId);
        product_name = findViewById(R.id.editName);
        product_category = findViewById(R.id.editCategory);
        product_description = findViewById(R.id.editDescription);
        product_price = findViewById(R.id.editPrice);


        products = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("List of Product");

        productAdapter = new ProductAdapter(Action_Bar_ProductActivity.this, products);
        recyclerView.setAdapter(productAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(Action_Bar_ProductActivity.this));

        product_add.setOnClickListener(this);
        productAdapter.setListener(this);

        dbHelper=new SqliteHelper(this);
        showList();

    }

    @Override
    public void onClick(View view) {

        String id = product_id.getText().toString().trim();
        String name = product_name.getText().toString().trim();
        String category = product_category.getText().toString().trim();
        String description = product_description.getText().toString().trim();
        String price = product_price.getText().toString().trim();

        if(toUpdate==0)
        {
            dbHelper.save(name,category,description,Integer.parseInt(price));
        }
        else {

            dbHelper.update(new Product(toUpdate,name,category,description,Integer.parseInt(price)));
            toUpdate=0;

        }
        showList();
        clearAll();
        Toast.makeText(Action_Bar_ProductActivity.this,"Data Saved!", Toast.LENGTH_SHORT).show();

    }
    public void showList(){
        products.clear();
        products.addAll(dbHelper.getAllProducts());
        productAdapter.notifyDataSetChanged();
    }
    public void clearAll()
    {

        product_name.setText("");
        product_category.setText("");
        product_description.setText("");
        product_price.setText("");
        toUpdate=0;
    }

    @Override
    public void onItemClick(Product item, int position) {

        product_name.setText(""+item.getName());
        product_category.setText(""+item.getCategory());
        product_description.setText(""+item.getDescription());
        product_price.setText(""+item.getPrice());
        toUpdate=item.getId();
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemLongClick(Product item, int position) {
        dbHelper.delete(item.getId());
        showList();
    }
}