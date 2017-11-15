package danaya58070042.kmitl.moneyflow;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import danaya58070042.kmitl.moneyflow.adapter.ItemAdapter;
import danaya58070042.kmitl.moneyflow.database.MoneyFlowDB;
import danaya58070042.kmitl.moneyflow.model.MoneyFlow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ItemAdapter.MoneyFlowAdapterListener {

    private MoneyFlowDB moneyFlowDB;
    private ItemAdapter itemAdepter;

    private Button btn_add;
    private RecyclerView recyc_list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyc_list = findViewById(R.id.list);

        moneyFlowDB = Room.databaseBuilder(this,
                MoneyFlowDB.class, "Moneyflow")
                .fallbackToDestructiveMigration()
                .build();

//        itemAdepter.setListener(listener);
    itemAdepter = new ItemAdapter(this, this);
        recyc_list.setAdapter(itemAdepter);
        recyc_list.setLayoutManager(new LinearLayoutManager(this));

        new AsyncTask<Void, Void, List<MoneyFlow>>() {
            @Override
            protected List<MoneyFlow> doInBackground(Void... voids) {
                List<MoneyFlow> result = moneyFlowDB.getMoneyFlowDAO().findAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<MoneyFlow> moneyFlows) {
                itemAdepter.setMoneyFlowList(moneyFlows);
                itemAdepter.notifyDataSetChanged();
            }
        }.execute();

        btn_add = findViewById(R.id.btn_add);

        new AsyncTask<Void, Void, Balance>() {

            @Override
            protected Balance doInBackground(Void... voids) {

                Balance balance = moneyFlowDB.getMoneyFlowDAO().findBalance();
                return balance;
            }

            @Override
            protected void onPostExecute(Balance balance) {
                super.onPostExecute(balance);
                TextView txt_balance = findViewById(R.id.balance);

                int amount = balance.getBalance();
                try {
                    float ratio = (float)amount / balance.getIncome();
                    if (ratio > 0.5) txt_balance.setTextColor(Color.parseColor("#33FF00"));
                    else if (ratio >= 0.25) txt_balance.setTextColor(Color.parseColor("#FF9900"));
                    else txt_balance.setTextColor(Color.parseColor("#FF0000"));
                } catch(Exception e){
                    txt_balance.setTextColor(Color.parseColor("#FF0000"));
                }
                txt_balance.setText(NumberFormat.getNumberInstance().format(amount));
            }
        }.execute();


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddData.class);
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onClick(View view) {
//        if (R.id.btn_add == view.getId()) {
//
//        }

    }

    @Override
    public void onItemTouched(final MoneyFlow moneyFlow) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Edit or delete?");
        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                startIntent(moneyFlow);
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new AsyncTask<Void, Void, MoneyFlow>() {
                    @Override
                    protected MoneyFlow doInBackground(Void... voids) {
                        moneyFlowDB.getMoneyFlowDAO().delete(moneyFlow);
                        finish();
                        startActivity(getIntent());
                        return null;
                    }
                }.execute();
            }
        });
        builder.show();

    }

    private void startIntent(MoneyFlow moneyFlow) {
        Intent intent = new Intent(this, AddData.class);
        intent.putExtra("moneyFlow", moneyFlow);
        finish();
        startActivity(intent);
    }
}
