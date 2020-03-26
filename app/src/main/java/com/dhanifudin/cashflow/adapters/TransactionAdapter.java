package com.dhanifudin.cashflow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dhanifudin.cashflow.R;
import com.dhanifudin.cashflow.models.Transaction;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Locale localeID = new Locale("in","ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public  static int  DEBITS =1;
    public static int CREDITS =2;
    private List<Transaction> items;

    public TransactionAdapter(List<Transaction> items, OnItemTransactionListener listener) {
        this.items = items;
        this.listener = listener;
    }

    private  OnItemTransactionListener listener;

    public interface OnItemTransactionListener{
        void onActivityResult();

        void onTransactionClicked(int index, Transaction item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        if(viewType == DEBITS){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction,parent,false);
        }
        else if(viewType == CREDITS){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction2,parent,false);
        }
        return new ViewHolder(view);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           Transaction item = items.get(position);
           holder.bind(position, item);
    }

    @Override
    public int getItemViewType(int position){
        return (items.get(position).getType()==Transaction.Type.CREDIT ? DEBITS:CREDITS);
    }
    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionText;
        TextView amountText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionText = itemView.findViewById(R.id.text_description);
            amountText = itemView.findViewById(R.id.text_amount);
        }
        public void bind(final int index, final  Transaction item){
            descriptionText.setText(item.getDescription());
            amountText.setText(formatRupiah.format(item.getAmount()));

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.onTransactionClicked(index, item);
                }
            });
        }
    }


//
//    private final List items;
//    private final OnItemClickListener listener;
//
//
}
