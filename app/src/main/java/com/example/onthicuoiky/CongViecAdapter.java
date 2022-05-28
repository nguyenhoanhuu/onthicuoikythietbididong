package com.example.onthicuoiky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<CongViec> danhSachCongViec;

    public CongViecAdapter(Context context, int layout, List<CongViec> danhSachCongViec) {
        this.context = context;
        this.layout = layout;
        this.danhSachCongViec = danhSachCongViec;
    }

    @Override
    public int getCount() {
        return danhSachCongViec.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView tvTenCongViec = view.findViewById(R.id.tvTenCongViec);
        TextView tvMucDo = view.findViewById(R.id.tvMucDo);
        TextView tvNgay = view.findViewById(R.id.tvNgay);

        CongViec congViec = danhSachCongViec.get(i);

        tvTenCongViec.setText(congViec.getTenCongViec());
        tvMucDo.setText(congViec.getMucDo());
        tvNgay.setText(congViec.getNgay().toString());
        return view;
    }
}
