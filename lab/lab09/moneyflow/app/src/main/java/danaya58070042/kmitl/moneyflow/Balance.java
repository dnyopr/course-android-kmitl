package danaya58070042.kmitl.moneyflow;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

/**
 * Created by Danaya on 11/15/2017.
 */
@Entity(tableName = "BALANCE")
public class Balance {

    @ColumnInfo(name = "income")
    private int income;

    @ColumnInfo(name = "expense")
    private int expense;

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getBalance(){
        return income - expense;
    }
}
