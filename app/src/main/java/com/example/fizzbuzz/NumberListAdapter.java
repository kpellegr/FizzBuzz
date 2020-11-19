package com.example.fizzbuzz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class NumberListAdapter extends RecyclerView.Adapter<NumberListAdapter.NumberViewHolder> {
    private final LinkedList<Integer> mNumberList;
    private LayoutInflater mInflater;

    public NumberListAdapter(Context context,
                             LinkedList<Integer> numberList) {
        mInflater = LayoutInflater.from(context);
        this.mNumberList = numberList;
    }


    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.numberview,
                parent, false);
        return new NumberViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        Integer mCurrentNumber = mNumberList.get(position);
        holder.numberView.setText(mCurrentNumber.toString());

        holder.fizzbuzzView.setText(String.format("%s%s",
                (mCurrentNumber % 3 == 0) ? "Fizz" : "",
                (mCurrentNumber % 5 == 0) ? "Buzz" : ""));

    }

    @Override
    public int getItemCount() {
        return mNumberList.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {
        public final TextView numberView;
        public final TextView fizzbuzzView;
        final NumberListAdapter mAdapter;

        public NumberViewHolder(@NonNull View itemView, @NonNull NumberListAdapter adapter) {
            super(itemView);
            numberView = itemView.findViewById(R.id.number_tv);
            fizzbuzzView = itemView.findViewById(R.id.fizzbuzz_tv);
            mAdapter = adapter;
        }
    }

}