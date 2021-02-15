package com.example.lottoexam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LottoNumberAdapter extends BaseAdapter {

    private List<LottoNumber> mData;

    public LottoNumberAdapter(List<LottoNumber> Data) {

        this.mData = Data;
    }

    @Override
    //아이템 갯수
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lottonumber, parent, false);

            TextView LottoNo = convertView.findViewById(R.id.tv_lottoNo);
            TextView LottoColor = convertView.findViewById(R.id.tv_lottoColor);
            TextView clickFlag = convertView.findViewById(R.id.tv_clickFlag);

            holder.LottoNo = LottoNo;
            holder.color = LottoColor;
            holder.clickFlag = clickFlag;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LottoNumber lottoNumber = mData.get(position);
        holder.LottoNo.setText( Integer.toString(lottoNumber.getmNumber()));
        holder.LottoNo.setBackgroundResource(R.drawable.circle_normal);
        holder.color.setText(Integer.toString(lottoNumber.getmColor()));
        holder.clickFlag.setText("N");

//        switch (lottoNumber.getmColor())
//        {
//            case 0:
//                holder.LottoNo.setBackgroundResource(R.drawable.circle_yellow);
//                break;
//            case 1:
//                holder.LottoNo.setBackgroundResource(R.drawable.circle_skyblue);
//                break;
//            case 2:
//                holder.LottoNo.setBackgroundResource(R.drawable.circle_red);
//                break;
//            case 3:
//                holder.LottoNo.setBackgroundResource(R.drawable.circle_red);
//                break;
//            case 4:
//                holder.LottoNo.setBackgroundResource(R.drawable.circle_gray);
//                break;
//            default:
//                holder.LottoNo.setBackgroundResource(R.drawable.circle_green);
//                break;
//        }

        //LayoutInflater -> 외부에서 layout.xml 가져오기
        return convertView;
    }

    static class ViewHolder {
        TextView LottoNo;
        TextView color;
        TextView clickFlag;
    }

}
