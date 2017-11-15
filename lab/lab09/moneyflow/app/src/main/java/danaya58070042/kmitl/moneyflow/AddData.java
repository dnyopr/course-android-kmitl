package danaya58070042.kmitl.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import danaya58070042.kmitl.moneyflow.database.MoneyFlowDB;
import danaya58070042.kmitl.moneyflow.model.MoneyFlow;

public class AddData extends AppCompatActivity implements View.OnClickListener {
    private Button btn_income, btn_expense, btn_save;
    private EditText describe, amount;
    private String type;
    private int update;
    private MoneyFlowDB moneyFlowDB;
    private MoneyFlow moneyFlow;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        moneyFlowDB = Room.databaseBuilder(this,
                MoneyFlowDB.class, "Moneyflow")
                .fallbackToDestructiveMigration()
                .build();

        btn_income = findViewById(R.id.btn_income);
        btn_expense = findViewById(R.id.btn_expense);
        btn_save = findViewById(R.id.btn_save);
        describe = findViewById(R.id.edit_detail);
        amount = findViewById(R.id.edit_amount);

        btn_income.setOnClickListener(this);
        btn_expense.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        intent = getIntent();
        moneyFlow = intent.getParcelableExtra("moneyFlow");
        update = 0;

        if (moneyFlow != null) {
            Log.wtf("where", "update");
            update = 1;
            moneyFlow = intent.getParcelableExtra("moneyFlow");
            describe.setText(moneyFlow.getDescribe());
            amount.setText(moneyFlow.getAmount() + "");
            if (moneyFlow.getType().equals("+")) {
                type="+";
                btn_income.setBackgroundColor(Color.parseColor("#6699FF"));
                btn_expense.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {
                type="-";
                btn_expense.setBackgroundColor(Color.parseColor("#6699FF"));
                btn_income.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        } else {
            moneyFlow = new MoneyFlow();
        }
        showData();
    }

    private void showData() {
        new AsyncTask<Void, Void, List<MoneyFlow>>() {
            @Override
            protected List<MoneyFlow> doInBackground(Void... voids) {
                List<MoneyFlow> result = moneyFlowDB.getMoneyFlowDAO().findAll();
                return result;
            }
        }.execute();
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_income == view.getId()) {
            type = "+";
            btn_income.setBackgroundColor(Color.parseColor("#5C6BC0"));
            btn_expense.setBackgroundColor(Color.parseColor("#eeeeee"));
        } else if (R.id.btn_expense == view.getId()) {
            type = "-";
            btn_income.setBackgroundColor(Color.parseColor("#eeeeee"));
            btn_expense.setBackgroundColor(Color.parseColor("#5C6BC0"));
        } else if (R.id.btn_save == view.getId()) {
            onSave();
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void onSave() {
        try {
            new AsyncTask<Void, Void, MoneyFlow>() {
                @Override
                protected MoneyFlow doInBackground(Void... voids) {
                    String desc = describe.getText().toString();
                    int amut = Integer.valueOf(amount.getText().toString());

                    moneyFlow.setDescribe(desc);
                    moneyFlow.setAmount(amut);
                    moneyFlow.setType(type);
                    if (update == 1) {
                        moneyFlowDB.getMoneyFlowDAO().update(moneyFlow);
                    } else {
                        moneyFlowDB.getMoneyFlowDAO().insert(moneyFlow);
                    }

                    return null;
                }
            }.execute();
        } catch (Exception e) {
            Toast.makeText(this, "Please fill up this form.", Toast.LENGTH_LONG).show();
        }
    }
}
