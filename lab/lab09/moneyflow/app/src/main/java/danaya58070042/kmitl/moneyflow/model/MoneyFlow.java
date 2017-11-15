package danaya58070042.kmitl.moneyflow.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.util.StringUtil;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

/**
 * Created by HP_PC01 on 8/11/2560.
 */

@Entity(tableName = "MONEY_FLOW")
public class MoneyFlow implements Parcelable {

    public MoneyFlow() {
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "TYPE")
    private String type;

    @ColumnInfo(name = "DESCRIBE")
    private String describe;

    @ColumnInfo(name = "AMOUNT")
    private int amount;


    protected MoneyFlow(Parcel in) {
        id = in.readInt();
        type = in.readString();
        describe = in.readString();
        amount = in.readInt();
    }

    public static final Creator<MoneyFlow> CREATOR = new Creator<MoneyFlow>() {
        @Override
        public MoneyFlow createFromParcel(Parcel in) {
            return new MoneyFlow(in);
        }

        @Override
        public MoneyFlow[] newArray(int size) {
            return new MoneyFlow[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String detail) {
        this.describe = detail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(type);
        parcel.writeString(describe);
        parcel.writeInt(amount);
    }
}
