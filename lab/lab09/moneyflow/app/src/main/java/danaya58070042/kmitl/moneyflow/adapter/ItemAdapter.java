package danaya58070042.kmitl.moneyflow.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import danaya58070042.kmitl.moneyflow.R;
import danaya58070042.kmitl.moneyflow.model.MoneyFlow;

/**
 * Created by Danaya on 11/14/2017.
 */
class ViewHolder extends RecyclerView.ViewHolder{

    TextView type, desc, amount;

    public ViewHolder(View itemView) {
        super(itemView);

        type = itemView.findViewById(R.id.txt_type);
        desc = itemView.findViewById(R.id.txt_describe);
        amount = itemView.findViewById(R.id.txt_amount);
    }
}

public class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<MoneyFlow> moneyFlowList;
    MoneyFlowAdapterListener listener;

    public ItemAdapter(Context context, MoneyFlowAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        moneyFlowList = new ArrayList<>();
    }


    public void setMoneyFlowList(List<MoneyFlow> moneyFlowList) {
        this.moneyFlowList = moneyFlowList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_transaction, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemTouched(moneyFlowList.get(position));
            }
        });
        holder.type.setText(moneyFlowList.get(position).getType());
        holder.desc.setText(moneyFlowList.get(position).getDescribe());
        holder.amount.setText(moneyFlowList.get(position).getAmount()+"");

        if(position%2!=0){
            holder.itemView.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

    }

    @Override
    public int getItemCount() {
        return moneyFlowList.size();
    }

    public interface MoneyFlowAdapterListener {
        public void onItemTouched(MoneyFlow moneyFlow);
    }
}
